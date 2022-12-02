// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LatefeeService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.LatefeeDao;
import com.yinhe.ec.cpps.model.LateFeeList;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LatefeeService
{

    @Autowired
    private LatefeeDao latefeeDao;

    public LatefeeService()
    {
    }

    public List getLateFeeListByCustId(int custId, String orders)
    {
        return latefeeDao.getLateFeeListByCustId(custId, orders);
    }

    public LateFeeList getLateFeeListById(String lateFeeId)
    {
        return latefeeDao.getLateFeeListById(lateFeeId);
    }

    public String addLateFeeList(LateFeeList lateFeeList)
    {
        try
        {
            latefeeDao.addLateFeeList(lateFeeList);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "新增成功";
    }

    public String modLateFeeList(LateFeeList lateFeeList)
    {
        try
        {
            latefeeDao.modLateFeeList(lateFeeList);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String delLateFeeListById(String lateFeeId)
    {
        if(latefeeDao.getLateFeeIsused(lateFeeId))
            return "存在被使用的对象，无法删除";
        try
        {
            latefeeDao.delLateFeeListById(lateFeeId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }


}
