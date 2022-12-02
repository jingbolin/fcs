package com.yinhe.ec.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.yinhe.ec.core.base.BaseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成
 */
public class Generator {
    
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D://solar");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        // gc.setEnableCache(true);// XML 二级缓存
        gc.setBaseResultMap(false);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setOpen(true);
        gc.setAuthor("wangshilei");
        gc.setDateType(DateType.ONLY_DATE);
        gc.setIdType(IdType.INPUT);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("111111");
        // dsc.setSchemaName("pv_asset");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/tbea_solar?serverTimezone=Asia/Shanghai");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setTablePrefix("mon_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setEntityTableFieldAnnotationEnable(true);// 生成字段注解
        strategy.setEnableSqlFilter(false);
        strategy.setInclude(new String[] {
            "mon_enum_value",
            "mon_frozen_value",
            "mon_virtual_point_formula_param"
        }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        strategy.setSuperEntityClass(BaseModel.class);
        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(new String[]{/*"id", */"enable", "create_by", "create_time", "update_by", "update_time"});
        // 自定义 mapper 父类
        strategy.setSuperMapperClass("BaseMapper");
        // 自定义 service 父类
        strategy.setSuperServiceClass("BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("BaseServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        // 【实体】Boolean类型字段是否移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 【实体】表字段逻辑处理注解（逻辑删除）
        // strategy.setLogicDeleteFieldName("enable");
        mpg.setStrategy(strategy);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("RequestMappingPrefix", "mon");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yinhe.ec.cpps.mon");
        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setXml("mapper/xml");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("web");
        mpg.setPackageInfo(pc);
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("tpl/entity.java.vm");
        tc.setMapper("tpl/mapper.java.vm");
        tc.setXml("tpl/mapper.xml.vm");
        tc.setService("tpl/iservice.java.vm");
        tc.setServiceImpl("tpl/serviceImpl.java.vm");
        tc.setController("tpl/controller.java.vm");
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
}
