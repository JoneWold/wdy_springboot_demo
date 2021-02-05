package com.ibeetl.admin.core.util.beetl;

import com.ibeetl.admin.core.util.UUIDUtil;
import org.beetl.core.Context;
import org.beetl.core.Function;

public class UUIDFunction implements Function {

    @Override
    public String call(Object[] arg0, Context arg1) {
        return UUIDUtil.uuid();
    }

}
