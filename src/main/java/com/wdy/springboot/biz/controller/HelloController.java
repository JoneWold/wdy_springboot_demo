package com.wdy.springboot.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdy.springboot.biz.service.UserService;
import com.wdy.springboot.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wgch
 * @Description
 * @date 2019/12/25 18:11
 */
@RestController
public class HelloController {

    @Resource
    private UserService userService;

    @RequestMapping("/getList")
    public Page<User> getList() {
        return userService.getList(1, 10);
    }

    @RequestMapping("/getOne")
    public User getOne() {
        return userService.getOne(1);
    }

}
