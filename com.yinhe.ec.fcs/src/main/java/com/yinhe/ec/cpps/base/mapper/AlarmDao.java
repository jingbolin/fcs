// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlarmDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.AlarmParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AlarmDao
{

    public abstract List getAllAlarmParam();

    public abstract void addAlarmParam(AlarmParam alarmparam)
        throws DataAccessException;

    public abstract void editAlarmParam(AlarmParam alarmparam)
        throws DataAccessException;

    public abstract void delAlarmParamById(int i)
        throws DataAccessException;

    public abstract AlarmParam getAlarmParam(int i);
}
