// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubInfoDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.SubInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SubInfoDao
{

    public abstract List getSubInfoByCustId(int i);

    public abstract void addSubInfo(SubInfo subinfo)
        throws DataAccessException;

    public abstract void editSubInfo(SubInfo subinfo)
        throws DataAccessException;

    public abstract void delSubInfoById(String s)
        throws DataAccessException;

    public abstract SubInfo getSubInfo(String s);
}
