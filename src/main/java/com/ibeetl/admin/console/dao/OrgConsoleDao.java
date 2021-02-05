package com.ibeetl.admin.console.dao;

import com.ibeetl.admin.core.entity.CoreOrg;
import com.ibeetl.admin.core.entity.CoreUser;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("console.org")
public interface OrgConsoleDao extends BaseMapper<CoreOrg> {

   
 

    void queryByCondtion(PageQuery<CoreOrg> query);
    @SqlStatement(type=SqlStatementType.SELECT)
    void queryUserByCondtion(PageQuery<CoreUser> query);
    void batchDelByIds(@Param(value = "ids") List<Long> ids);
}
