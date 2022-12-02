// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RangeDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.Range;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface RangeDao
{

    public abstract List getAllRangeInfo();

    public abstract void addRange(Range range)
        throws DataAccessException;

    public abstract void editRange(Range range)
        throws DataAccessException;

    public abstract void delRangeById(int i)
        throws DataAccessException;

    public abstract Range getRangeInfo(int i);
}
