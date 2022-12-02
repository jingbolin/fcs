// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorAndFMService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.system.dao.OperatorAndFMDao;
import org.springframework.dao.DataAccessException;

public class OperatorAndFMService
{

    public OperatorAndFMService()
    {
    }

    public String setOperatorAndFM(int custId, int operatorId, String operatorAndFM)
    {
        if(operatorAndFM != "")
            operatorAndFM = operatorAndFM.substring(0, operatorAndFM.length() - 1);
        try
        {
            operatorAndFMDao.setOperatorAndFM(custId, operatorId, operatorAndFM);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u8BBE\u7F6E\u5FEB\u6377\u83DC\u5355\u6210\u529F\uFF01";
    }

    private OperatorAndFMDao operatorAndFMDao;
}
