package com.ibeetl.admin.core.dao;

import com.ibeetl.admin.core.entity.CoreRole;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("core.coreRole")
public interface CoreRoleDao extends BaseMapper<CoreRole> {



}
