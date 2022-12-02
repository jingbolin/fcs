// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseParamDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.BaseParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BaseParamDao
{

    public abstract BaseParam getBaseParam(int i);

    public abstract List getBaseParamList(int i);

    public abstract void EditBaseParam(BaseParam baseparam)
        throws DataAccessException;

    public abstract void AddBaseParam(BaseParam baseparam)
        throws DataAccessException;

    public abstract void DelBaseParam(int i)
        throws DataAccessException;

    public abstract List getMeterType(int i);

    public abstract List getUsedMeterTypes(int i);
}
