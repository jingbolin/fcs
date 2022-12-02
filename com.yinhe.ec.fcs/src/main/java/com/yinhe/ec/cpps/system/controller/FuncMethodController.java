// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuncMethodController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.model.FuncMethod;
import com.yinhe.ec.cpps.system.service.FuncMethodService;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;

public class FuncMethodController
{

    public FuncMethodController()
    {
    }

    public String getFuncMethod()
    {
        return funcMethodService.getFuncMethod();
    }

    public String addFuncMethod(String fmName, String fmUrl, String fmDesc, String fmPid, String fmPicUrl, int isMethod)
    {
        funcMethod.setFmId(genPkIdservice.getPkNoByTable("funcmethod", "FMID", Integer.valueOf(4)));
        funcMethod.setFmName(fmName);
        funcMethod.setFmUrl(fmUrl);
        funcMethod.setFmDesc(fmDesc);
        funcMethod.setFmPid(fmPid);
        funcMethod.setFmCreateDate(Tools.getNow());
        funcMethod.setFmPicUrl(fmPicUrl);
        funcMethod.setIsMethod(isMethod);
        return funcMethodService.addFuncMethod(funcMethod);
    }

    public String editFuncMethod(String fmId, String fmName, String fmUrl, String fmDesc, String fmPicUrl, int isMethod)
    {
        funcMethod.setFmId(fmId);
        funcMethod.setFmName(fmName);
        funcMethod.setFmUrl(fmUrl);
        funcMethod.setFmDesc(fmDesc);
        funcMethod.setFmPicUrl(fmPicUrl);
        funcMethod.setIsMethod(isMethod);
        return funcMethodService.editFuncMethod(funcMethod);
    }

    public String deleteFuncMethod(String fmId, int custId)
    {
        return funcMethodService.deleteFuncMethod(fmId, custId);
    }

    public String initFuncMethodToAdmin()
    {
        return funcMethodService.initFuncMethodToAdmin();
    }

    public String getFuncListByOperatorAccount(String operatorAccount)
    {
        return funcMethodService.getFuncListByOperatorAccount(operatorAccount);
    }

    public String getFuncMethodHtmlByOperatorId(int operatorId)
    {
        return funcMethodService.getFuncMethodHtmlByOperatorId(operatorId);
    }

    private FuncMethodService funcMethodService;
    private FuncMethod funcMethod;
    private GenPkIdService genPkIdservice;
}
