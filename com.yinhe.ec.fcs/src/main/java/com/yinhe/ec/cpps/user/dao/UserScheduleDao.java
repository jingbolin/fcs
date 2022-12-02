// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserScheduleDao.java

package com.yinhe.ec.cpps.user.dao;

import com.yinhe.ec.cpps.model.Schedule;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface UserScheduleDao
{

    public abstract List getUserScheduleHistory(String s);

    public abstract List getUserScheduleById(String s);

    public abstract void addUserSchedule(Schedule schedule, String s, String s1)
        throws DataAccessException;

    public abstract Integer delUserScheduleById(String s)
        throws DataAccessException;
}
