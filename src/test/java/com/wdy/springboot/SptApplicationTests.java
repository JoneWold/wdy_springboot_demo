package com.wdy.springboot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ResourceBundle;

@SpringBootTest
@RunWith(SpringRunner.class)
class SptApplicationTests {

    @Test
    void contextLoads() {

        //用来获取Mybatis-Plus.properties文件的配置信息
        final ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus");

        //1、全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//开启AR模式
                .setAuthor(rb.getString("author"))//设置作者
                //生成路径(一般都是生成在此项目的src/main/java下面)
                .setOutputDir(rb.getString("OutputDir"))
                .setFileOverride(false)//第二次生成会把第一次生成的覆盖掉(是否覆盖已有文件)
                .setIdType(IdType.ASSIGN_UUID)//主键策略
                .setServiceName("I%sService")//生成的service接口名字首字母是否为I，这样设置就没有I
                .setBaseResultMap(true)//生成resultMap
                .setBaseColumnList(true)//在xml中生成基础列
                .setOpen(false)
                .setEntityName("%sEntity");//实体类xxxEntity
        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//数据库类型
                .setDriverName(rb.getString("driver"))
                .setUrl(rb.getString("url"))
                .setSchemaName(rb.getString("schemaName"))
                .setUsername(rb.getString("userName"))
                .setPassword(rb.getString("password"));
        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//开启全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)//下划线到驼峰的命名方式
                .setEntityLombokModel(false)//使用lombok
                .setInclude("blog")
                .setLogicDeleteFieldName("is_delete_")
                .setEntityTableFieldAnnotationEnable(true)//逆向工程使用的表
        ;

        //4、包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(rb.getString("parent"))//设置包名的parent
                .setMapper("mapper")
                .setController("")
                .setService("service")
                .setEntity("entity")
                .setServiceImpl("service.impl");

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //控制 不生成 controller  空字符串就行
        templateConfig.setController(null);

        //5、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        //6、执行
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.execute();
    }

}
