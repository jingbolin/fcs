// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorLogService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.system.dao.OperatorLogDao;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.yinhe.ec.cpps.system.service:
//            OperatorService

public class OperatorLogService
{

    public OperatorLogService()
    {
    }

    public Map getOperatorLog(int page, int rows, String condition, int operatorId, int custId)
    {
        condition = (new StringBuilder("OperatorLog.OperatorID = Operator.OperatorID AND Operator.CustID='")).append(custId).append("' and Operator.OperatorId in (").append(operatorService.getOperatorIdsById(operatorId)).append(") ").append(condition).toString();
        Map map = new HashMap();
        map.put("total", operatorLogDao.getTotal(condition));
        map.put("rows", operatorLogDao.getOperatorLog(page, rows, condition));
        return map;
    }

    private OperatorLogDao operatorLogDao;
    private OperatorService operatorService;
}
