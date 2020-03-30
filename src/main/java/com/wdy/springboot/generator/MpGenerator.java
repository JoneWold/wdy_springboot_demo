package com.wdy.springboot.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 代码生成器(待完善)
 *
 * @author TMW
 * @date 2019/6/28 9:19
 */
public class MpGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("TMW");
        gc.setIdType(IdType.AUTO);
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(false);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sServiceImpl");
        gc.setServiceImplName("%sService");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/wdy?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=PRC");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix("sys_", "cpm_");
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 排除生成的表
//        strategy.setExclude("");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.entityTableFieldAnnotationEnable(true);
        // 自定义 service 父类
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wdy.spt");
        pc.setController("biz.controller");
        pc.setServiceImpl("biz.service");
        pc.setMapper("biz.mapper");
        pc.setXml("xml");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

}
