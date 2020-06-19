package com.wdy.springboot.biz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdy.springboot.biz.service.UserService;
import com.wdy.springboot.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author HHL
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getList")
    public Page<User> getList() {
        return userService.getList(1, 10);
    }

    @GetMapping("/getOne")
    public User getOne() {
        return userService.getOne(1);
    }


}
