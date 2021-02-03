package com.wdy.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdy.springboot.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.springboot.entity.User;
import com.wdy.springboot.mapper.SysUserMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wdy
 * @since 2021-02-03
 */
public interface ISysUserService extends IService<SysUserEntity> {

    Page<User> getList(int pageNum, int pageSize);

    User getOne(int id);
}
