package com.wdy.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.springboot.mapper.UserMapper;
import com.wdy.springboot.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
        List<User> list = userMapper.getList();
        Page<User> page = new Page<>(pageNum, pageSize, list.size());
        // 数据分页
        page.setRecords(list.stream().skip((long) (pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));
        return page;
    }

    public User getOne(int id) {
        return userMapper.getOne(id);
    }

}
