// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuncMethodService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.system.dao.FuncMethodDao;
import com.yinhe.ec.cpps.util.GetFuncMethodTree;
import org.apache.shiro.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FuncMethodService
{

    public FuncMethodService()
    {
    }

    public String getFuncMethod()
    {
        List funcMeterList = funcMethodDao.getFuncMethod();
        FuncMethod funcMethod = funcMethodDao.getFuncMethodRoot();
        return GetFuncMethodTree.getFuncMethod(funcMeterList, funcMethod);
    }

    public List getFuncMethodList()
    {
        return funcMethodDao.getFuncMethod();
    }

    public String addFuncMethod(FuncMethod funcMethod)
    {
        try
        {
            funcMethodDao.addFuncMethod(funcMethod);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public String editFuncMethod(FuncMethod funcMethod)
    {
        try
        {
            funcMethodDao.editFuncMethodByFmId(funcMethod);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String deleteFuncMethod(String fmId, int custId)
    {
        try
        {
            funcMethodDao.deleteFuncMethod(fmId, custId);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u5220\u9664\u6210\u529F\uFF01";
    }

    public List getFuncMethodByOperatorAccount(String operatorAccount)
    {
        return funcMethodDao.getFuncMethodByOperatorAccount(operatorAccount);
    }

    public String initFuncMethodToAdmin()
    {
        try
        {
            List roleFuncMethodList = new ArrayList();
            List list = funcMethodDao.getFuncMethod();
            for(Iterator iterator = list.iterator(); iterator.hasNext(); roleFuncMethodList.add(roleFuncMethod))
            {
                FuncMethod funcMethod = (FuncMethod)iterator.next();
                roleFuncMethod = new RoleFuncMethod();
                operRole.setOrId("0000");
                operRole.setCustId(0);
                roleFuncMethod.setOperRole(operRole);
                roleFuncMethod.setFuncMethod(funcMethod);
            }

            funcMethodDao.initFuncMethodToAdmin(roleFuncMethodList);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u521D\u59CB\u5316\u6210\u529F\uFF01";
    }

    public List getKJFuncMethodByOperatorId(int operatorId)
    {
        return funcMethodDao.getKJFuncMethodByOperatorId(operatorId);
    }

    public String getFuncListByOperatorAccount(String operatorAccount)
    {
        FuncMethod funcMethod = funcMethodDao.getFuncMethodRoot();
        List funcMethodList = funcMethodDao.getFuncListByOperatorAccount(operatorAccount);
        return GetFuncMethodTree.getFuncMethodTreeNoRoot(funcMethodList, funcMethod);
    }

    public String getFuncMethodHtmlByOperatorId(int operatorId)
    {
        List completeFuncMethodList = funcMethodDao.getFuncMethodByOperatorId(operatorId);
        List nowFuncMethodList = funcMethodDao.getKJFuncMethodByOperatorId(operatorId);
        StringBuilder str = new StringBuilder();
        Boolean sign = null;
        str.append("<div class='a'>");
        for(int k = 1; k <= completeFuncMethodList.size(); k++)
        {
            sign = Boolean.valueOf(false);
            FuncMethod completFuncMethod = (FuncMethod)completeFuncMethodList.get(k - 1);
            if(k == 1 || k % 3 == 1)
                str.append("<ul>");
            for(int i = 0; i < nowFuncMethodList.size(); i++)
            {
                FuncMethod nowFuncMethod = (FuncMethod)nowFuncMethodList.get(i);
                if(!completFuncMethod.getFmId().equals(nowFuncMethod.getFmId()))
                    continue;
                str.append((new StringBuilder("<li><input type='checkbox' name='ofm' value='")).append(completFuncMethod.getFmId()).append("' checked='checked' />&nbsp;").append(completFuncMethod.getFmName()).append("</li>").toString());
                sign = Boolean.valueOf(true);
                break;
            }

            if(!sign.booleanValue())
                str.append((new StringBuilder("<li><input type='checkbox' name='ofm' value='")).append(completFuncMethod.getFmId()).append("'  />&nbsp;").append(completFuncMethod.getFmName()).append("</li>").toString());
            if(k % 3 == 0)
                str.append("</ul>");
        }

        str.append("</div>");
        return str.toString();
    }

    private FuncMethodDao funcMethodDao;
    private RoleFuncMethod roleFuncMethod;
    private OperRole operRole;
}
