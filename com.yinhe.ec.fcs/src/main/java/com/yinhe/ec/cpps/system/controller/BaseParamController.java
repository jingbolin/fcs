// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseParamController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.model.BaseParam;
import com.yinhe.ec.cpps.system.service.BaseParamService;

import java.util.List;

public class BaseParamController
{

    public BaseParamController()
    {
    }

    public BaseParam getBaseParam(int custId)
    {
        return baseParamService.getBaseParam(custId);
    }

    public List getBaseParamList(int custId)
    {
        return baseParamService.getBaseParamList(custId);
    }

    public String editBaseParam(BaseParam sps)
    {
        return baseParamService.EditBaseParam(sps);
    }

    public String addBaseParam(BaseParam sps)
    {
        return baseParamService.AddBaseParam(sps);
    }

    public String delBaseParam(int custId)
    {
        return baseParamService.DelBaseParam(custId);
    }

    public List getMeterType(int custId)
    {
        return baseParamService.getMeterType(custId);
    }

    private BaseParamService baseParamService;
}
