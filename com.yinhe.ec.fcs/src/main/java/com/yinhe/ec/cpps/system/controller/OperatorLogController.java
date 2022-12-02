// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorLogController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.system.service.OperatorLogService;

import java.util.Map;

public class OperatorLogController
{

    public OperatorLogController()
    {
    }

    public Map getOperatorLog(int page, int rows, String condition, int operatorId, int custId)
    {
        return operatorLogService.getOperatorLog(page, rows, condition, operatorId, custId);
    }

    private OperatorLogService operatorLogService;
}
