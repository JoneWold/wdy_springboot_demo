package com.wdy.springboot.biz.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author HHL
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("/")
    public String say() {
        return "Hello Spring Boot";
    }


}
