package com.yinhe.ec.core.config;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.PropertiesUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;

/**
 * @author ShenHuaJie
 * @since 2018年8月1日 下午2:15:34
 */
@Configuration
@ConditionalOnClass(value = { MapperScannerConfigurer.class, DataSourceTransactionManager.class })
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfig implements EnvironmentAware
{
	private Properties config;

	@Autowired
	private Environment env;

	private String get(String key) throws IOException
	{
		String value = PropertiesUtil.getString(key);
		if (DataUtil.isEmpty(value))
		{
			if (config == null)
			{
				String profile = "dev";
				if (env.getActiveProfiles() != null && env.getActiveProfiles().length > 0)
				{
					profile = env.getActiveProfiles()[0];
				}
//				String path="C:/xiangmu/yinhe/houtai/com.yinhe.ec.fcs/src/main/resources/config/dev";
//				config = PropertiesLoaderUtils.loadAllProperties(path + "/jdbc.properties");
				config = PropertiesLoaderUtils.loadAllProperties("config/" + profile + "/jdbc.properties");

			}
			value = config.getProperty(key);
		}
		return value;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean(name = "sqlSessionFactory")
	// @ConditionalOnBean(DataSource.class)
	public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception
	{
		MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
		// TODO 临时的垃圾代码，后期优化
        Resource[] resourcesAll = resolver.getResources(get("mybatis.mapperLocations"));
        List<Resource> resourceList    = CollUtil.newArrayList();
        List<String> resourceNameList = CollUtil.newArrayList();
        for (Resource resource : resourcesAll) {
            if (!resourceNameList.contains(resource.getFilename())) {
                resourceList.add(resource);
                resourceNameList.add(resource.getFilename());
            }
        }
        
		sessionFactory.setMapperLocations(ArrayUtil.toArray(resourceList, Resource.class));
		sessionFactory.setTypeAliasesPackage(get("mybatis.typeAliasesPackage"));
		PaginationInterceptor page = new PaginationInterceptor();
		page.setLimit(-1);
		page.setDbType(DbType.getDbType(get("mybatis.dialectType")));
		sessionFactory.setPlugins(new Interceptor[] { page });

		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setLogImpl(Slf4jImpl.class);
		configuration.setShrinkWhitespacesInSql(true);
		configuration.setCallSettersOnNulls(true);
		sessionFactory.setConfiguration(configuration);

		String idType = get("mybatis.idType");
		GlobalConfig config = new GlobalConfig();
		config.setDbConfig(new DbConfig().setIdType(DataUtil.isEmpty(idType) ? IdType.AUTO : IdType.valueOf(idType)));
		// 添加InsertBatchSomeColumn方法
		config.setSqlInjector(new DefaultSqlInjector() {
		    @Override
		    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
		        List<String> excludeColumn = CollUtil.list(false, "enable", "createBy", "createTime", "updateBy", "updateTime");
		        methodList.add(new InsertBatchSomeColumn(t -> !excludeColumn.contains(t.getProperty()))); 
		        return methodList;
		    }
		});
		sessionFactory.setGlobalConfig(config);
		return sessionFactory;
	}

	@Bean
	// @ConditionalOnBean(DataSource.class)
	public MapperScannerConfigurer configurer() throws IOException
	{
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		configurer.setBasePackage(get("mybatis.mapperBasePackage"));
		return configurer;
	}

	@Bean
	@ConditionalOnBean(Environment.class)
	public DataSourceTransactionManager transactionManager(DataSource dataSource)
	{
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	public void setEnvironment(Environment environment)
	{
		this.env = environment;
	}
}
