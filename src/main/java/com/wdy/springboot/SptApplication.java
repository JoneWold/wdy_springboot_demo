package com.wdy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Administrator
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:wdySpring.xml"}) // 加载自定义的配置文件（不推荐）
public class SptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SptApplication.class, args);
    }

}
