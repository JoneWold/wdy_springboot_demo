package com.wdy.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.springboot.entity.SysUserEntity;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wdy
 * @since 2021-02-03
 */
public interface ISysUserService extends IService<SysUserEntity> {

    Page<SysUserEntity> getList(int pageNum, int pageSize);

    SysUserEntity getOne(int id);
}
