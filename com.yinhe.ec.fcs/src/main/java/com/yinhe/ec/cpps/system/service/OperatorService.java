// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.dto.OperatorAccountDTO;
import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.system.dao.OperatorDao;
import com.yinhe.ec.cpps.util.MD5;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorService
{

    public OperatorService()
    {
    }

    public Operator getOperatorByAccount(String operatorAccount)
    {
        return operatorDao.getOperatorByAccount(operatorAccount);
    }

    public String editOperatorPwdById(OperatorAccountDTO operatorAccountDTO)
    {
        Operator operator = operatorDao.getOperatorById(operatorAccountDTO.getOperatorId());
        if(!operator.getOperatorPwd().equals(MD5.getDigestedString(operatorAccountDTO.getOldPwd())))
            return "\u65E7\u5BC6\u7801\u8F93\u5165\u9519\u8BEF";
        try
        {
            operatorDao.editOperatorPwdById(operatorAccountDTO.getOperatorId(), MD5.getDigestedString(operatorAccountDTO.getNewPwd()));
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u5BC6\u7801\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public Map getOperator(int page, int row, String condition)
    {
        Map map = new HashMap();
        map.put("total", Integer.valueOf(operatorDao.getTotal(condition)));
        map.put("rows", operatorDao.getOperator(page, row, condition));
        return map;
    }

    public String addOperator(Operator operator, String roleId)
    {
        try
        {
            operatorDao.addOperator(operator, roleId);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public String editOperator(Operator operator, String roleId)
    {
        try
        {
            operatorDao.editOperator(operator, roleId);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String deleteOperatorById(int operatorId)
    {
        try
        {
            operatorDao.deleteOperatorById(operatorId);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u5220\u9664\u6210\u529F\uFF01";
    }

    public String setIsLogin(int operatorId, int status)
    {
        try
        {
            operatorDao.setIsLogin(operatorId, status);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u8BBE\u7F6E\u6210\u529F\uFF01";
    }

    public String setInitPwd(int operatorId)
    {
        try
        {
            operatorDao.setInitPwd(operatorId, MD5.getDigestedString("pt1234"));
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u8BBE\u7F6E\u6210\u529F\uFF01";
    }

    public boolean getOperatorResultSetByAccount(String operatorAccount)
    {
        int row = operatorDao.getOperatorResultSetByAccount(operatorAccount).intValue();
        return row == 0;
    }

    public String editOperatorAccount(OperatorAccountDTO operatorAccountDTO)
    {
        Operator operator = operatorDao.getOperatorById(operatorAccountDTO.getOperatorId());
        if(!operator.getOperatorPwd().equals(MD5.getDigestedString(operatorAccountDTO.getOldPwd())))
            return "\u5BC6\u7801\u8F93\u5165\u9519\u8BEF";
        try
        {
            operatorDao.editOperatorAccount(operatorAccountDTO.getOperatorId(), operatorAccountDTO.getOperatorAccount());
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u8D26\u6237\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public List getOperatorListByCondition(String condition)
    {
        return operatorDao.getOperatorListByCondition(condition);
    }

    public String app_editOperatorPwd(int operatorId, String oldPwd, String newPwd)
    {
        Operator operator = operatorDao.getOperatorById(operatorId);
        if(!operator.getOperatorPwd().equals(MD5.getDigestedString(oldPwd)))
            return "editPwd_callback({msg:'\u65E7\u5BC6\u7801\u8F93\u5165\u9519\u8BEF\uFF01'})";
        try
        {
            operatorDao.editOperatorPwdById(operatorId, MD5.getDigestedString(newPwd));
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "editPwd_callback({msg:'true'})";
    }

    public String getOperatorIdsById(int operatorId)
    {
        return operatorDao.getOperatorIdsById(operatorId);
    }

    private OperatorDao operatorDao;
}
