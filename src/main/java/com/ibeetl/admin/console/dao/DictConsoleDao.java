package com.ibeetl.admin.console.dao;

import com.ibeetl.admin.core.entity.CoreDict;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * CoreDict Dao
 */
@SqlResource("console.dict")
public interface DictConsoleDao extends BaseMapper<CoreDict>{
    public PageQuery<CoreDict> queryByCondition(PageQuery query);
    public void batchDelCoreDictByIds( List<Long> ids);
}