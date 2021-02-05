package com.ibeetl.admin.core.service;

import com.ibeetl.admin.core.dao.CoreAuditDao;
import com.ibeetl.admin.core.entity.CoreAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoreAuditService extends CoreBaseService<CoreAudit> {
    
    @Autowired
    private CoreAuditDao sysAuditDao;
    

}