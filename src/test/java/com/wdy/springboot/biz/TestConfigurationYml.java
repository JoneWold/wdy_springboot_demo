package com.wdy.springboot.biz;

import com.wdy.springboot.biz.wdy.yml.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wgch
 * @Description 配置文件及.yml使用
 * @date 2020/7/3
 */
@SpringBootTest
public class TestConfigurationYml {

    @Autowired
    Student student;

    @Test
    void contextLoads() {
        System.out.println(student);
    }


}
