// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChangeMController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.base.service.AreaInfoService;
import com.yinhe.ec.cpps.ycmeter.service.ChangeMService;

import java.util.Map;

public class ChangeMController
{

    public ChangeMController()
    {
    }

    public Map getChangeMInfo(int page, int rows, String orders, int custId, String areaId)
    {
        String areaIds = "";
        areaIds = areaInfoService.getChildAreaInfoByAreaId(custId, areaId);
        return changeMService.getChangeMInfo(page, rows, orders, areaIds);
    }

    private ChangeMService changeMService;
    private AreaInfoService areaInfoService;
}
