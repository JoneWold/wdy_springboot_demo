package com.wdy.springboot.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.springboot.biz.mapper.UserMapper;
import com.wdy.springboot.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HHL
 * @since 2019-12-26
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    UserMapper userMapper;

    public Page<User> getList(int pageNum, int pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        page.setRecords(userMapper.getList());
        return page;
    }

    public User getOne(int id) {
        return userMapper.getOne(id);
    }

}
