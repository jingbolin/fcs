// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubInfoService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.SubInfoDao;
import com.yinhe.ec.cpps.model.SubInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubInfoService
{

    @Autowired
    private SubInfoDao subInfoDao;

    public SubInfoService()
    {
    }

    public List getSubInfoByCustId(int custId)
    {
        return subInfoDao.getSubInfoByCustId(custId);
    }

    public String addSubInfo(SubInfo subInfo)
    {
        try
        {
            subInfoDao.addSubInfo(subInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }

    public String editSubInfo(SubInfo subInfo)
    {
        try
        {
            subInfoDao.editSubInfo(subInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String delSubInfoById(String subNo)
    {
        try
        {
            subInfoDao.delSubInfoById(subNo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    public SubInfo getSubInfo(String subNo)
    {
        return subInfoDao.getSubInfo(subNo);
    }


}
