package com.wdy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdy.springboot.entity.SysUserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wdy
 * @since 2021-02-03
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    List<SysUserEntity> getList();

    @Select("select * from sys_user where id=#{id}")
    SysUserEntity getOne(int id);
}
