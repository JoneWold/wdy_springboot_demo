package com.ibeetl.admin.core.dao;

import com.ibeetl.admin.core.entity.CoreUser;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("core.coreUser")
public interface CoreUserDao extends BaseMapper<CoreUser> {

    /**
     * 根据角色编码查询用户集合
     * @param roleCode 角色编码
     * @return
     */
    List<CoreUser> getUserByRole( String roleCode);


}
