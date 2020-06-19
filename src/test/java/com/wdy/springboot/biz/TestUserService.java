package com.wdy.springboot.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdy.springboot.biz.service.UserService;
import com.wdy.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author wgch
 * @Description 测试类
 * @date 2020/6/19
 */
@SpringBootTest
class TestUserService {

    @Autowired
    UserService userService;

    @Test
    void getList() {
        Page<User> list = userService.getList(1, 1);
        System.err.println(list.getRecords());
    }

}
