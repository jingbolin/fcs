package com.yinhe.ec.core.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.PropertiesUtil;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;

/**
 * @author ShenHuaJie
 * @since 2019年4月4日 下午2:58:30
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
public class DataSourceConfig {
    
    public static class EnableAspect implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return DataUtil.isNotEmpty(PropertiesUtil.getString("druid.reader.url"));
        }
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
            new ServletRegistrationBean<StatViewServlet>();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        
        // 数据源data中的逻辑表、其中"his_measurement_value"按照ID分成十个物理表后缀0~9
        String[] dataTables = {
            "his_alarm_info", 
            "his_measurement_value", 
            "ana_report_inverter", 
            "ana_report_on_grid_energy", 
            "ana_report_station_month", 
            "ana_report_station_year"
        };
        // 数据源solar中的逻辑表
        String[] solarTables = {
            "ast_model_box_transformer",
            "ast_model_daq",
            "ast_model_inverter",
            "ast_psr",
            "ast_psr_info_array",
            "ast_psr_info_box_transformer",
            "ast_psr_info_environmental_monitor",
            "ast_psr_info_gateway_table",
            "ast_psr_info_inverter",
            "ast_psr_info_station",
            "ast_psr_type",
            "mon_enum_value",
            "mon_frozen_value",
            "mon_inverter_status_formula",
            "mon_inverter_status_formula_param",
            "mon_measurement_attr",
            "mon_measurement_calc",
            "mon_measurement_dd",
            "mon_measurement_yc",
            "mon_measurement_yk",
            "mon_measurement_yt",
            "mon_measurement_yx",
            "mon_virtual_point",
            "mon_virtual_point_formula",
            "mon_virtual_point_formula_param",
            "sa_conf_list",
            "sa_event",
            "sa_menu",
            "sa_menu_favor",
            "sa_org",
            "sa_org_ast_psr",
            "sa_org_type",
            "sa_permission",
            "sa_permission_group",
            "sa_permission_resource",
            "sa_resource",
            "sa_resource_group",
            "sa_role",
            "sa_role_permission",
            "sa_session",
            "sa_user",
            "sa_user_org",
            "sa_user_role"
        };

        // 数据源tbea_solar
        DataSource solar = getDruidDataSource("solar");
        // 数据源tbea_solar_data
        DataSource data = getDruidDataSource("data");
        
        Map<String, DataSource> dataSourceMap = MapUtil.builder("solar", solar).put("data", data).build();
        
        // 配置数据源data中的分片表规则列表
        List<ShardingTableRuleConfiguration> dataTableRule = Arrays.stream(dataTables).filter(StrUtil::isNotBlank).map(table -> {
            if (table.equals("his_measurement_value")) {
                // 配置表规则
                ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration(table, "data." + table + "_${0..9}");
                // 配置分库策略
                // orderTableRuleConfig.setDatabaseShardingStrategy(() -> "data");
                // 配置分表算法
                orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("measurement_id", "hisDataSharding"));
                return orderTableRuleConfig;
            } else {
                // 配置表规则
                ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration(table, "data." + table);
                // 配置分库策略
                // orderTableRuleConfig.setDatabaseShardingStrategy(() -> "data");
                return orderTableRuleConfig;
            }
        }).collect(Collectors.toList());
        
        // 配置数据源solar中的分片表规则列表
        List<ShardingTableRuleConfiguration> solarTableRule = Arrays.stream(solarTables).filter(StrUtil::isNotBlank).map(table -> {
            // 配置表规则
            ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration(table, "solar." + table);
            // 配置分库策略
            orderTableRuleConfig.setDatabaseShardingStrategy(() -> "solar");
            return orderTableRuleConfig;
        }).collect(Collectors.toList());

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 数据源data中的分片表规则列表
        shardingRuleConfig.getTables().addAll(dataTableRule);
        // 数据源solar中的分片表规则列表
        shardingRuleConfig.getTables().addAll(solarTableRule);
        // 配置默认分库策略
        // shardingRuleConfig.setDefaultDatabaseShardingStrategy(() -> "solar");
        // 配置默认分表策略
        // shardingRuleConfig.setDefaultTableShardingStrategy(() -> "");
        
        // 配置分表算法
        Properties hisDataSharding = new Properties();
        hisDataSharding.setProperty("algorithm-expression", "his_measurement_value_${measurement_id % 10}");
        hisDataSharding.setProperty("allow-range-query-with-inline-sharding", "true");
        shardingRuleConfig.getShardingAlgorithms().put("hisDataSharding", new ShardingSphereAlgorithmConfiguration("INLINE", hisDataSharding));
        // 配置雪花算法生成主键
        Properties SNOWFLAKE = new Properties();
        shardingRuleConfig.getKeyGenerators().put("SNOWFLAKE", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", SNOWFLAKE));
        
        // 配置其他参数
        Properties props = new Properties();
        props.put("sql-show", "true");

        // sharding数据源
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), props);

        return dataSource;
    }
    
    @Bean
    public Db db() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(PropertiesUtil.getString(StrUtil.format("{}.driverClassName", "data")));
        datasource.setUrl(PropertiesUtil.getString(StrUtil.format("{}.url", "data")));
        datasource.setUsername(PropertiesUtil.getString(StrUtil.format("{}.username", "data")));
        datasource.setPassword(PropertiesUtil.getString(StrUtil.format("{}.password", "data")));
        datasource.setInitialSize(PropertiesUtil.getInt(StrUtil.format("{}.initialSize", "data")));
        datasource.setMaxActive(PropertiesUtil.getInt(StrUtil.format("{}.maxActive", "data")));
        datasource.setValidationQuery("select 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(true);
        datasource.setTestOnReturn(true);
        return Db.use(datasource);
    }

    private DataSource getDruidDataSource(String database) {
        
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(PropertiesUtil.getString(StrUtil.format("{}.driverClassName", database)));
        datasource.setUrl(PropertiesUtil.getString(StrUtil.format("{}.url", database)));
        datasource.setUsername(PropertiesUtil.getString(StrUtil.format("{}.username", database)));
        datasource.setPassword(PropertiesUtil.getString(StrUtil.format("{}.password", database)));
        datasource.setInitialSize(PropertiesUtil.getInt(StrUtil.format("{}.initialSize", database)));
        datasource.setMaxActive(PropertiesUtil.getInt(StrUtil.format("{}.maxActive", database)));

        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter());
        filters.add(wallFilter());
        datasource.setProxyFilters(filters);

        return datasource;
        
    }

    private StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000);
        return statFilter;
    }

    private WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }
}
