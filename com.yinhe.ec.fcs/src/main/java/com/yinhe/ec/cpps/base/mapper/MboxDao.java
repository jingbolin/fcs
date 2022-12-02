// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MboxDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.MboxInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MboxDao
{

    List getMboxInfo(int i);

    void addMboxInfo(MboxInfo mboxinfo)
        throws DataAccessException;

    void editMboxInfo(MboxInfo mboxinfo)
        throws DataAccessException;

    void deleteMboxInfo(int i)
        throws DataAccessException;

    MboxInfo getMboxInfoById(int i);
}
