package com.ibeetl.admin.core.dao;

import com.ibeetl.admin.core.entity.CoreOrg;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("core.coreOrg")
public interface CoreOrgDao extends BaseMapper<CoreOrg> {

    List<CoreOrg> queryOrgByUser(Long userId);

 
    List<String> queryAllOrgCode(List<Long> orgIds);
    
    @Sql("select * from core_org where parent_org_id is null")
    CoreOrg getRoot();

  
}
