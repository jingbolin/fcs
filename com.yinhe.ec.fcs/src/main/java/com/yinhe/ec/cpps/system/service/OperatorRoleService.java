// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorRoleService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.model.FuncMethod;
import com.yinhe.ec.cpps.model.OperRole;
import com.yinhe.ec.cpps.system.dao.FuncMethodDao;
import com.yinhe.ec.cpps.system.dao.OperatorRoleDao;
import com.yinhe.ec.cpps.util.GetFuncMethodTree;
import org.springframework.dao.DataAccessException;

import java.util.*;

public class OperatorRoleService
{

    public OperatorRoleService()
    {
    }

    public String addOperRole(OperRole operRole)
    {
        try
        {
            operatorRoleDao.addOperatorRole(operRole);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public Map getOperRole(int page, int rows, int custId, String orId)
    {
        String condition = " 1=1 ";
        Map map = new HashMap();
        if(!"0000".equals(orId))
            condition = (new StringBuilder(String.valueOf(condition))).append(" and custId=").append(custId).append(" and orPId='").append(orId).append("' ").toString();
        else
            condition = (new StringBuilder(String.valueOf(condition))).append(" and orPId='").append(orId).append("' or orId='").append(orId).append("' ").toString();
        map.put("total", Integer.valueOf(operatorRoleDao.getTotal(condition)));
        map.put("rows", operatorRoleDao.getOperRole(page, rows, condition));
        return map;
    }

    public String editOperRole(OperRole operRole)
    {
        try
        {
            operatorRoleDao.editOperRole(operRole);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String delOperRoleById(String orId)
    {
        String condition = " 1=1 and orid in (select orid from operrolelist,operator where operator.operatorId=operrolelist.operatorid and orid='0001' and operator.operatoraccount like '%_admin') ";
        if(operatorRoleDao.getTotal(condition) > 0)
            return "\u7BA1\u7406\u5458\u89D2\u8272\u4E0D\u5141\u8BB8\u5220\u9664\uFF01";
        try
        {
            operatorRoleDao.delRoleMethodByOrId(orId);
            operatorRoleDao.deleteOperRoleById(orId);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "\u5220\u9664\u6210\u529F";
    }

    public String setRoleMethod(String orId, String indeterminateId, String checkedId, int custId)
    {
        if("".equals(indeterminateId) && "".equals(checkedId))
        {
            try
            {
                operatorRoleDao.delRoleMethodByOrId(orId);
            }
            catch(DataAccessException e)
            {
                return e.getMessage();
            }
            return "\u8BBE\u7F6E\u6210\u529F";
        }
        String indeterminateIds[] = new String[0];
        String checkedIds[] = new String[0];
        if(indeterminateId != "")
            indeterminateIds = indeterminateId.split(",");
        if(checkedId != "")
            checkedIds = checkedId.split(",");
        try
        {
            operatorRoleDao.setRoleMethod(orId, indeterminateIds, checkedIds, custId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
        return "\u8BBE\u7F6E\u6210\u529F\uFF01";
    }

    public List getOperRoleByOperatorAccount(String operatorAccount)
    {
        return operatorRoleDao.getOperRoleByOperatorAccount(operatorAccount);
    }

    public String getRoleMethod(String orId, List roleList)
    {
        List temp_funcMethodList = new ArrayList();
        boolean sign = false;
        for(Iterator iterator = roleList.iterator(); iterator.hasNext();)
        {
            OperRole operRole = (OperRole)iterator.next();
            List funcMethodList = funcMethodDao.getFuncMethodByOrId(operRole.getOrId());
            for(Iterator iterator1 = funcMethodList.iterator(); iterator1.hasNext();)
            {
                FuncMethod funcMethod = (FuncMethod)iterator1.next();
                sign = false;
                for(Iterator iterator2 = temp_funcMethodList.iterator(); iterator2.hasNext();)
                {
                    FuncMethod funcMethod2 = (FuncMethod)iterator2.next();
                    if(funcMethod2.getFmId().equals(funcMethod.getFmId()))
                        sign = true;
                }

                if(!sign)
                    temp_funcMethodList.add(funcMethod);
            }

        }

        FuncMethod funcMethod = funcMethodDao.getFuncMethodRoot();
        List list2 = getRoleMethodByOrId(orId);
        return GetFuncMethodTree.getFuncMethodTree(temp_funcMethodList, funcMethod, list2);
    }

    public List getRoleMethodByOrId(String orId)
    {
        return operatorRoleDao.getRoleMethodByOrId(orId);
    }

    public List getAllOperRole(int custId)
    {
        return operatorRoleDao.getAllOperRole(custId);
    }

    private OperatorRoleDao operatorRoleDao;
    private FuncMethodDao funcMethodDao;
}
