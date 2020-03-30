package com.wdy.springboot.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdy.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author HHL
 * @since 2019-12-26
 */
@Repository("UserMapper")
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> getList();

    @Select("select * from sys_user where id=#{id}")
    User getOne(int id);
}
