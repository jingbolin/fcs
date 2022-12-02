// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterTypeController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.MeterTypeService;
import com.yinhe.ec.cpps.model.MeterType;
import com.yinhe.ec.cpps.system.service.GenPkIdService;

import java.util.List;

public class MeterTypeController
{

    public MeterTypeController()
    {
    }

    public List getMeterTypeByCustId(int custId)
    {
        return meterTypeService.getMeterTypeByCustId(custId);
    }

    public String addMeterType(MeterType meterType)
    {
        meterType.setTypeId(genPkIdservice.getPkNoForInt("meterType", "typeId"));
        return meterTypeService.addMeterType(meterType);
    }

    public String editMeterType(MeterType meterType)
    {
        return meterTypeService.editMeterType(meterType);
    }

    public String delMeterTypeById(int typeId)
    {
        return meterTypeService.delMeterTypeById(typeId);
    }

    private MeterTypeService meterTypeService;
    private GenPkIdService genPkIdservice;
}
