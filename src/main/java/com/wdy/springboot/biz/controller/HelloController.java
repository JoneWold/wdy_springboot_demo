package com.wdy.springboot.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wgch
 * @Description
 * @date 2019/12/25 18:11
 */
@RestController
@RequestMapping("/")
public class HelloController {

    //    @GetMapping("/")
    public String say() {
        return "Hello Spring Boot";
    }


    @GetMapping("/thyme")
    public String thyme(Map<String, Object> map) {
        // 给request域中方welcome
        map.put("welcome", "少时诵诗书");
        // 转到thymeleaf.html
        return "thymeleaf";
    }
}
