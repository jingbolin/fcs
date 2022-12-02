// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserScheduleController.java

package com.yinhe.ec.cpps.user.controller;

import com.yinhe.ec.cpps.model.Schedule;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.user.service.UserScheduleService;
import com.yinhe.ec.cpps.util.Tools;

import java.util.List;

public class UserScheduleController
{

    public UserScheduleController()
    {
    }

    public List getUserScheduleHistory(String condition, String custId)
    {
        condition = (new StringBuilder(String.valueOf(condition))).append(" and CustId='").append(custId).append("'").toString();
        return userScheduleService.getUserScheduleHistory(condition);
    }

    public List getUserScheduleById(String scheduleId)
    {
        return userScheduleService.getUserScheduleById(scheduleId);
    }

    public String addUserSchedule(Schedule schedule, String oldFeeItemId, String newFeeItemId, String startDate, String createUser, String areaId, String userIds, 
            int custId, String meterNos)
    {
        schedule.setScheduleId(genPkIdservice.getPkNoForInt("Schedule", "scheduleId"));
        schedule.setOldFeeItemId(oldFeeItemId);
        schedule.setNewFeeItemId(newFeeItemId);
        schedule.setStartDate(startDate);
        schedule.setCreateUser(createUser);
        schedule.setAreaId(areaId);
        schedule.setYearDosageDt(startDate.substring(0, 10));
        schedule.setCreateDate(Tools.getNow());
        schedule.setScheduleType(1);
        schedule.setScheduleFlag(Integer.valueOf(0));
        schedule.setCustId(custId);
        return userScheduleService.addUserSchedule(schedule, userIds, meterNos);
    }

    public String delUserScheduleById(String scheduleId)
    {
        return userScheduleService.delUserScheduleById(scheduleId);
    }

    private UserScheduleService userScheduleService;
    private GenPkIdService genPkIdservice;
}
