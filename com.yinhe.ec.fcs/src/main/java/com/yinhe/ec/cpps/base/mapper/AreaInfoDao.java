// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaInfoDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.AreaInfo;
import com.yinhe.ec.cpps.model.ReadUser;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AreaInfoDao
{

    public abstract List getAreaInfoByClause(int i);

    public abstract AreaInfo getAreaInfoById(String s);

    public abstract void addAreaInfo(AreaInfo areainfo)
        throws DataAccessException;

    public abstract void editAreaInfo(AreaInfo areainfo)
        throws DataAccessException;

    public abstract void deleteAreaInfo(String s)
        throws DataAccessException;

    public abstract void changeAreaInfoRoot(String s, String s1)
        throws DataAccessException;

    public abstract List getReadUserByCustId(int i);

    public abstract void addReadUser(ReadUser readuser);

    public abstract void editReadUser(ReadUser readuser);

    public abstract void deleteReadUser(String s);

    public abstract List getAreaInfoByOrders(int i, String s);

    public abstract void editAreaMeterInfo(AreaInfo areainfo);
}
