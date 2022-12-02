// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterTypeService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.MeterTypeDao;
import com.yinhe.ec.cpps.model.MeterType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class MeterTypeService
{

    public MeterTypeService()
    {
    }

    public List getMeterTypeByCustId(int custId)
    {
        return meterTypeDao.getMeterTypeByCustId(custId);
    }

    public String addMeterType(MeterType meterType)
    {
        try
        {
            meterTypeDao.addMeterType(meterType);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public String editMeterType(MeterType meterType)
    {
        try
        {
            meterTypeDao.editMeterType(meterType);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String delMeterTypeById(int typeId)
    {
        try
        {
            meterTypeDao.delMeterTypeById(typeId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public MeterType getMeterType(int typeId)
    {
        return meterTypeDao.getMeterType(typeId);
    }

    private MeterTypeDao meterTypeDao;
}
