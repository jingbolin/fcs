// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaListDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.AreaList;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AreaListDao
{

    public abstract List getAreaListByCustId(int i);

    public abstract void addAreaList(AreaList arealist)
        throws DataAccessException;

    public abstract void editAreaList(AreaList arealist)
        throws DataAccessException;

    public abstract void delAreaListById(String s)
        throws DataAccessException;

    public abstract AreaList getAreaList(String s);
}
