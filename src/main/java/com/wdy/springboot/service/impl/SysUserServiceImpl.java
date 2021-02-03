package com.wdy.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.springboot.entity.SysUserEntity;
import com.wdy.springboot.mapper.SysUserMapper;
import com.wdy.springboot.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wdy
 * @since 2021-02-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {

    @Resource
    SysUserMapper mapper;

    @Override
    public Page<SysUserEntity> getList(int pageNum, int pageSize) {
        List<SysUserEntity> list = mapper.getList();
        Page<SysUserEntity> page = new Page<>(pageNum, pageSize, list.size());
        // 数据分页
        page.setRecords(list.stream().skip((long) (pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));
        return page;
    }

    @Override
    public SysUserEntity getOne(int id) {
        return mapper.getOne(id);
    }

}
