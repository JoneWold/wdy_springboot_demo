package com.wdy.springboot.biz;

import com.wdy.springboot.biz.wdy.yml.Student;
import com.wdy.springboot.biz.wdy.yml.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


/**
 * @author wgch
 * @Description 配置文件及.yml使用
 * @date 2020/7/3
 */
@SpringBootTest
public class TestConfigurationYml {

    @Autowired
    Student student;
    @Autowired
    ApplicationContext context; //spring ioc容器

    @Test
    void contextLoads() {
        System.out.println(student);
    }


    @Test
    void testImpResource() {
        // 从spring容器中获取 自定义配置中的数据
        StudentService service = (StudentService) context.getBean("stuService2");
        System.out.println(service);
    }

}
