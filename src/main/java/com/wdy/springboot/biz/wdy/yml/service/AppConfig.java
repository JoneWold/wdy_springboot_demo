package com.wdy.springboot.biz.wdy.yml.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wgch
 * @Description 注解配置类 (等价于wdySpring.xml)
 * @date 2020/7/6
 */
@Configuration
@ComponentScan({"com.wdy.springboot"})
public class AppConfig {


    @Bean
    public StudentService stuService2() {
        StudentService studentService = new StudentService();
//        StudentDao dao = new StudentDao();
//        studentService.setStudentDao(dao);
        // 返回值相当于.xml 配置里面的<bean class="com.wdy.spring...">
        return studentService;
    }

}
