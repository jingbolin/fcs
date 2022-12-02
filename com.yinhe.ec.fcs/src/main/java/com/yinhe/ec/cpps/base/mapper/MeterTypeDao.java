// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterTypeDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.MeterType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MeterTypeDao
{

    public abstract List getMeterTypeByCustId(int i);

    public abstract void addMeterType(MeterType metertype)
        throws DataAccessException;

    public abstract void editMeterType(MeterType metertype)
        throws DataAccessException;

    public abstract void delMeterTypeById(int i)
        throws DataAccessException;

    public abstract MeterType getMeterType(int i);
}
