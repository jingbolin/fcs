// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScheduleDao.java

package com.yinhe.ec.cpps.user.dao;

import com.yinhe.ec.cpps.model.Schedule;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface ScheduleDao
{

    public abstract List getScheduleAll(String s);

    public abstract Schedule getScheduleById(String s);

    public abstract void addSchedule(Schedule schedule)
        throws DataAccessException;

    public abstract void modSchedule(Schedule schedule)
        throws DataAccessException;

    public abstract Integer delSchedule(int i)
        throws DataAccessException;
}
