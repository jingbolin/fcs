// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScheduleController.java

package com.yinhe.ec.cpps.user.controller;

import com.yinhe.ec.cpps.model.Schedule;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.user.service.ScheduleService;
import com.yinhe.ec.cpps.util.Tools;

import java.util.List;

public class ScheduleController
{

    public ScheduleController()
    {
    }

    public List getScheduleAll(String orders)
    {
        return scheduleService.getScheduleAll(orders);
    }

    public Schedule getScheduleById(String scheduleId)
    {
        return scheduleService.getScheduleById(scheduleId);
    }

    public String addSchedule(Schedule schedule, int custId)
    {
        schedule.setScheduleId(genPkIdservice.getPkNoForInt("Schedule", "scheduleId"));
        schedule.setCreateDate(Tools.getNow());
        schedule.setScheduleType(2);
        schedule.setScheduleFlag(Integer.valueOf(0));
        schedule.setCustId(custId);
        schedule.setYearDosageDt(schedule.getStartDate().substring(0, 10));
        return scheduleService.addSchedule(schedule);
    }

    public String modSchedule(Schedule schedule)
    {
        schedule.setCreateDate(Tools.getNow());
        return scheduleService.modSchedule(schedule);
    }

    public String delSchedule(int scheduleId)
    {
        return scheduleService.delSchedule(scheduleId);
    }

    private ScheduleService scheduleService;
    private GenPkIdService genPkIdservice;
}
