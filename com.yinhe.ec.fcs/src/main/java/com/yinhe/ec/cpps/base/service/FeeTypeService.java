// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeTypeService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.FeeTypeDao;
import com.yinhe.ec.cpps.model.FeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeTypeService
{

    @Autowired
    private FeeTypeDao feeTypeDao;

    public FeeTypeService()
    {
    }

    public List getAllFeeType()
    {
        return feeTypeDao.getAllFeeType();
    }

    public String addFeeType(FeeType feeType)
    {
        try
        {
            feeTypeDao.addFeeType(feeType);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }

    public String editFeeType(FeeType feeType)
    {
        try
        {
            feeTypeDao.editFeeType(feeType);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String delFeeTypeById(String feeTypeId)
    {
        if(feeTypeDao.getFeeTypeIsused(feeTypeId)){
            return "存在关联订单，无法删除";
        }

        try
        {
            feeTypeDao.delFeeTypeById(feeTypeId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    public FeeType getFeeType(String feeTypeId)
    {
        return feeTypeDao.getFeeType(feeTypeId);
    }


}
