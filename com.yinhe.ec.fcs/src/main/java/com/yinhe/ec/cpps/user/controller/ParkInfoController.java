// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParkInfoController.java

package com.yinhe.ec.cpps.user.controller;

import com.yinhe.ec.cpps.dto.UserParkDTO;
import com.yinhe.ec.cpps.model.ParkInfo;
import com.yinhe.ec.cpps.model.UserParkInfo;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.user.service.ParkInfoService;

import java.util.List;
import java.util.Map;

public class ParkInfoController
{

    public ParkInfoController()
    {
    }

    public String AddParkInfo(ParkInfo parkInfo)
    {
        parkInfo.setParkId(genPkIdservice.getPkNoForInt("ParkInfo", "parkId"));
        return parkInfoService.AddParkInfo(parkInfo);
    }

    public String EditParkInfo(ParkInfo parkInfo)
    {
        return parkInfoService.EditParkInfo(parkInfo);
    }

    public String DelParkInfo(int parkId)
    {
        return parkInfoService.DelParkInfo(parkId);
    }

    public List getParkInfo(int custId)
    {
        return parkInfoService.getParkInfo(custId);
    }

    public Map getParkInfoMap(int page, int rows, String condition, int custId)
    {
        condition = (new StringBuilder(" 1=1 ")).append(condition).append(" and parkInfo.custId=").append(custId).toString();
        return parkInfoService.getParkInfoMap(page, rows, condition);
    }

    public ParkInfo getParkInfoById(int custId, int parkId)
    {
        return parkInfoService.getParkInfoById(custId, parkId);
    }

    public String AddUserParkInfo(UserParkInfo userParkInfo)
    {
        return parkInfoService.AddUserParkInfo(userParkInfo);
    }

    public String EditUserParkInfo(UserParkInfo userParkInfo)
    {
        return parkInfoService.EditUserParkInfo(userParkInfo);
    }

    public String DelUserParkInfo(int parkId, String userId)
    {
        return parkInfoService.DelUserParkInfo(parkId, userId);
    }

    public UserParkDTO getUserInfoByParkId(int custId, int parkId)
    {
        return parkInfoService.getUserInfoByParkId(custId, parkId);
    }

    public String EditCarCycle(String userId, String parkId, String startDate, String endDate)
    {
        return parkInfoService.EditCarCycle(userId, parkId, startDate, endDate);
    }

    private ParkInfoService parkInfoService;
    private GenPkIdService genPkIdservice;
}
