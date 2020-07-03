package com.wdy.springboot.biz.wdy.yml;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
public class Student {
    private String name;
    private int age;
    private boolean sex;
    private Date birthday;
    private Map<String, Object> location;
    private String[] hobbies;
    private List<String> skills;
    private Pet pet;

    //@Component 将此Javabean放入spring容器
}
