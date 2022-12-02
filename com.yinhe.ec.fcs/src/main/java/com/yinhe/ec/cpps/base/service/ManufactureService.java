// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ManufactureService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.model.Manufacture;
import com.yinhe.ec.cpps.base.dao.ManufactureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureService
{

    @Autowired
    private ManufactureDao manufactureDao;

    public ManufactureService()
    {
    }

    public List getManufacture(int custId)
    {
        return manufactureDao.getManufacture(custId);
    }

    public String addManufacture(Manufacture manufacture)
    {
        try
        {
            manufactureDao.addManufacture(manufacture);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "新增成功";
    }

    public String modManufacture(Manufacture manufacture)
    {
        try
        {
            manufactureDao.modManufacture(manufacture);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String delManufacture(int manuId)
    {
        try
        {
            manufactureDao.delManufacture(manuId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    public Manufacture getManufactureById(int manuId)
    {
        return manufactureDao.getManufactureById(manuId);
    }


}
