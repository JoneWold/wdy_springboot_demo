package com.wdy.springboot.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wgch
 * @Description
 * @date 2019/12/25 18:11
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/")
    public String say() {
        return "Hello Spring Boot";
    }

}
