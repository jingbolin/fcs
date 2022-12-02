// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlarmController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.AlarmService;
import com.yinhe.ec.cpps.model.AlarmParam;
import com.yinhe.ec.cpps.system.service.GenPkIdService;

import java.util.List;

public class AlarmController
{

    public AlarmController()
    {
    }

    public List getAllAlarmParam()
    {
        return alarmService.getAllAlarmParam();
    }

    public String addAlarmParam(AlarmParam alarmParam)
    {
        alarmParam.setAlarmId(genPkIdservice.getPkNoForInt("AlarmParam", "AlarmId"));
        return alarmService.addAlarmParam(alarmParam);
    }

    public String editAlarmParam(AlarmParam alarmParam)
    {
        return alarmService.editAlarmParam(alarmParam);
    }

    public String delAlarmParamById(int alarmId)
    {
        return alarmService.delAlarmParamById(alarmId);
    }

    private AlarmService alarmService;
    private GenPkIdService genPkIdservice;
}
