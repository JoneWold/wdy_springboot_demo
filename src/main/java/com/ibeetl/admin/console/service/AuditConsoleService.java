package com.ibeetl.admin.console.service;

import com.ibeetl.admin.console.dao.AuditConsoleDao;
import com.ibeetl.admin.core.entity.CoreAudit;
import com.ibeetl.admin.core.service.CoreBaseService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditConsoleService extends CoreBaseService<CoreAudit> {

	@Autowired
	AuditConsoleDao auditConsoleDao;

	/**
	 * 根据条件查询
	 *
	 * @param query
	 */
	public void queryByCondtion(PageQuery<CoreAudit> query) {
		PageQuery<CoreAudit> ret = auditConsoleDao.queryByCondtion(query);
		queryListAfter(ret.getList());
	}

	

}
