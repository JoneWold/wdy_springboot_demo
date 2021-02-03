package com.wdy.springboot.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdy.springboot.entity.SysUserEntity;
import com.wdy.springboot.service.ISysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 测试类
 *
 * @author wgch
 * @date 2020/6/19
 */
@SpringBootTest
class TestUserService {

    @Autowired
    ISysUserService userService;

    @Test
    void getList() {
        Page<SysUserEntity> list = userService.getList(1, 1);
        System.err.println(list.getRecords());
    }

}
