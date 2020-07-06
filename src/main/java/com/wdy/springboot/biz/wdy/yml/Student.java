package com.wdy.springboot.biz.wdy.yml;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wgch
 * @date 2020/7/3
 */
@Component
@ConfigurationProperties(prefix = "student")
@Data
@ToString
@Validated
public class Student {
    @Value("李四")
    private String name;
    private int age;
    private boolean sex;
    private Date birthday;
    private Map<String, Object> location;
    private String[] hobbies;
    private List<String> skills;
    private Pet pet;
    @Email
    private String email;
    @Value("$(student.userName)")
    private String userName;

    //1、 @Component
    // 将此Javabean放入spring容器

    //2、 @ConfigurationProperties
    // 绑定yaml配置的值，支持批量注入值，支持松散语法(如：nickName写成nick-name)，不支持SpEL，jsr303数据校验
    // 支持注入复杂类型，简单类型（8个基本类型/String/Date）

    //3、 @Value
    // 支持单个值注入，不支持松散语法，支持SpEL（"${student.name}"），不支持jsr303数据校验，不支持注入复杂类型

    //4、 @Validated
    // 开启jsr303数据校验的注解，然后@Email等注解才会生效


    //5、 @PropertySource
    // 默认会加载application.properties/application.yml配置文件中的数据。
    // @PropertySource(value={"classpath:conf.properties"}) 会加载conf.properties文件中的数据，
    // 但是其中value的值只能加载.properties为后缀的配置数据，不支持.yml

    //6、@ImportResource
    // spring boot 自动装配/自动配置：spring等配置文件默认会被springboot自动给配置好。
    //如果要自己编写spring等配置文件，springboot 默认是不识别的。
    //如果需要识别，则需要在springboot主配置类上通过@ImportResource(locations = {"classpath:wdySpring.xml"})指定配置文件的路径。
    // 但是不推荐手写spring配置文件
    // 配置：xml配置文件，通过注解配置
    // springboot推荐注解方式进行配置：@Configuration，@Bean

}
