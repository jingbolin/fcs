// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenPkIdService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.system.dao.GenPkIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenPkIdService
{

    @Autowired
    private GenPkIdDao genPkIdDao;

    public GenPkIdService()
    {

    }

    public String getPkIdByTable(String tbName, String pkName)
    {
        return genPkIdDao.getPkIdByTable(tbName, pkName);
    }

    public String getPkNoByTable(String tbName, String pkName, Integer n)
    {
        return genPkIdDao.getPkNoByTable(tbName, pkName, n);
    }

    public int getPkNoForInt(String tbName, String pkName)
    {
        return genPkIdDao.getPkNoForInt(tbName, pkName);
    }


}
