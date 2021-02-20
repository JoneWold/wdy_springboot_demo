package com.wdy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wgch
 * @date 2020/3/30
 */
@EnableScheduling  //开启定时任务
@SpringBootApplication
@ImportResource(locations = {"classpath:wdySpring.xml"}) // 加载自定义的配置文件（不推荐）
@MapperScan("com.wdy.springboot.mapper")
public class SptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SptApplication.class, args);
    }

}
