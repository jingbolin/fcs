// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DepartService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.model.DepartInfo;
import com.yinhe.ec.cpps.system.dao.DepartDao;
import com.yinhe.ec.cpps.util.GetDepartInfoTree;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class DepartService
{

    public DepartService()
    {
    }

    public String getDepartInfoForGrid(int custId, String departId)
    {
        List list = departDao.getDepartInfoByCompanyId(custId);
        DepartInfo depart = departDao.getDepartInfoById(departId);
        return GetDepartInfoTree.getDepartInfoForGrid(list, depart);
    }

    public String addDepartInfo(DepartInfo departInfo)
    {
        try
        {
            departDao.addDepartInfo(departInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public String editDepartInfo(DepartInfo departInfo)
    {
        try
        {
            departDao.editDepartInfo(departInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String deleteDepartInfo(String departId)
    {
        try
        {
            departDao.deleteDepartInfo(departId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u6210\u529F\uFF01";
    }

    public List getDepartInfoAll()
    {
        return departDao.getDepartInfoAll();
    }

    public DepartInfo getDepartInfoById(String departId)
    {
        return departDao.getDepartInfoById(departId);
    }

    public DepartInfo getDepartInfoByPid(String departPid)
    {
        return departDao.getDepartInfoByPid(departPid);
    }

    public List getDepartInfoListByPid(String departPid)
    {
        return departDao.getDepartInfoListByPid(departPid);
    }

    public String changeDepartInfoRoot(String targetId, String sourceId)
    {
        try
        {
            departDao.changeDepartInfoRoot(targetId, sourceId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u8282\u70B9\u8C03\u6574\u6210\u529F\uFF01";
    }

    public List getDepartInfoByCompanyId(int custId)
    {
        return departDao.getDepartInfoByCompanyId(custId);
    }

    public String getDepartNoRoot(int custId, String departId)
    {
        List list = departDao.getDepartInfoByCompanyId(custId);
        DepartInfo depart = departDao.getDepartInfoById(departId);
        return GetDepartInfoTree.getDepartInfoNoRoot(list, depart);
    }

    private DepartDao departDao;
}
