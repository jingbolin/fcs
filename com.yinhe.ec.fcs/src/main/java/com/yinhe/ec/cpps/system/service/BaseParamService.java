// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseParamService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.model.BaseParam;
import com.yinhe.ec.cpps.system.dao.BaseParamDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseParamService
{

    public BaseParamService()
    {
    }

    public BaseParam getBaseParam(int custId)
    {
        return baseParamDao.getBaseParam(custId);
    }

    public List getBaseParamList(int custId)
    {
        return baseParamDao.getBaseParamList(custId);
    }

    public String EditBaseParam(BaseParam sps)
    {
        try
        {
            baseParamDao.EditBaseParam(sps);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "更新成功";
    }

    public String AddBaseParam(BaseParam sps)
    {
        try
        {
            baseParamDao.AddBaseParam(sps);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "添加成功";
    }

    public String DelBaseParam(int custId)
    {
        try
        {
            baseParamDao.DelBaseParam(custId);
        }
        catch(DataAccessException e)
        {
            return e.getMessage();
        }
        return "删除成功";
    }

    public List getMeterType(int custId)
    {
        return baseParamDao.getMeterType(custId);
    }

    public List getUsedMeterTypes(int custId)
    {
        return baseParamDao.getUsedMeterTypes(custId);
    }

    private BaseParamDao baseParamDao;
}
