package com.iwyu.marking.generator;/**
 * Created by Chester on 6/2/2021.
 */


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @ClassName main
 * @Description
 * @Author XiaoMao
 * @Date 6/2/2021 上午10:45
 * @Version 1.0
 **/

public class GeneratorMain {
    public static void main(String[] args){
        AutoGenerator generator = new AutoGenerator();
        //datasource
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/marking_sys");
        generator.setDataSource(dataSourceConfig);
        //global
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("Chester");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setControllerName("%sController");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        generator.setGlobalConfig(globalConfig);
        //Package
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.iwyu.marking");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        generator.setPackageInfo(packageConfig);
        //配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("course_objective");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        generator.setStrategy(strategyConfig);

        generator.execute();

    }
}