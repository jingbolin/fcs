// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlarmService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.AlarmDao;
import com.yinhe.ec.cpps.model.AlarmParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class AlarmService
{

    public AlarmService()
    {
    }

    public List getAllAlarmParam()
    {
        return alarmDao.getAllAlarmParam();
    }

    public String addAlarmParam(AlarmParam alarmParam)
    {
        try
        {
            alarmDao.addAlarmParam(alarmParam);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public String editAlarmParam(AlarmParam alarmParam)
    {
        try
        {
            alarmDao.editAlarmParam(alarmParam);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String delAlarmParamById(int alarmId)
    {
        try
        {
            alarmDao.delAlarmParamById(alarmId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u6210\u529F\uFF01";
    }

    public AlarmParam getAlarmParam(int alarmId)
    {
        return alarmDao.getAlarmParam(alarmId);
    }

    private AlarmDao alarmDao;
}
