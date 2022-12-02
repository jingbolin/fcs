// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MboxService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.MboxDao;
import com.yinhe.ec.cpps.model.MboxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MboxService
{

    @Autowired
    private MboxDao mboxDao;

    public MboxService()
    {
    }

    public List getMboxInfo(int custId)
    {
        return mboxDao.getMboxInfo(custId);
    }

    public String addMboxInfo(MboxInfo mboxInfo)
    {
        try
        {
            mboxDao.addMboxInfo(mboxInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }

    public String editMboxInfo(MboxInfo mboxInfo)
    {
        try
        {
            mboxDao.editMboxInfo(mboxInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String deleteMboxInfo(int mboxId)
    {
        try
        {
            mboxDao.deleteMboxInfo(mboxId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    public MboxInfo getMboxInfoById(int mboxId)
    {
        return mboxDao.getMboxInfoById(mboxId);
    }


}
