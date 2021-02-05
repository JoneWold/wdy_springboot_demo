package com.ibeetl.admin.core.dao;

import com.ibeetl.admin.core.entity.CoreUserRole;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("core.coresUserRole")
public interface CoreUserRoleDao extends BaseMapper<CoreUserRole> {


}
