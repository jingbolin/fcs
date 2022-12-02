// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorAndFMController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.system.service.FuncMethodService;
import com.yinhe.ec.cpps.system.service.OperatorAndFMService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class OperatorAndFMController
{

    public OperatorAndFMController()
    {
    }

    public String setOperatorAndFM(int custId, int operatorId, String operatorAndFM)
        throws IllegalAccessException, InvocationTargetException
    {
        return operatorAndFMService.setOperatorAndFM(custId, operatorId, operatorAndFM);
    }

    public List getKJFuncMethodByOperatorId(int operatorId)
    {
        return funcMethodService.getKJFuncMethodByOperatorId(operatorId);
    }

    private OperatorAndFMService operatorAndFMService;
    private FuncMethodService funcMethodService;
}
