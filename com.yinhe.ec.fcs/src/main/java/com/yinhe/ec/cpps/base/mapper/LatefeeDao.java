// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LatefeeDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.LateFeeList;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface LatefeeDao
{

    public abstract List getLateFeeListByCustId(int i, String s);

    public abstract LateFeeList getLateFeeListById(String s);

    public abstract void addLateFeeList(LateFeeList latefeelist)
        throws DataAccessException;

    public abstract void modLateFeeList(LateFeeList latefeelist)
        throws DataAccessException;

    public abstract void delLateFeeListById(String s)
        throws DataAccessException;

    public abstract boolean getLateFeeIsused(String s);
}
