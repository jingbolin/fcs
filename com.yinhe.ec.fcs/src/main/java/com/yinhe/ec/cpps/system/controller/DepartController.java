// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DepartController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.model.DepartInfo;
import com.yinhe.ec.cpps.system.service.DepartService;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;

import java.util.List;

public class DepartController
{

    public DepartController()
    {
    }

    public String getDepartInfoForGrid(int custId, String departId)
    {
        return departService.getDepartInfoForGrid(custId, departId);
    }

    public String addDepartInfo(DepartInfo departInfo)
    {
        departInfo.setDepartId(genPkIdservice.getPkNoByTable("departInfo", "departId", Integer.valueOf(4)));
        departInfo.setCreateDate(Tools.getNow());
        return departService.addDepartInfo(departInfo);
    }

    public String editDepartInfo(DepartInfo departInfo)
    {
        return departService.editDepartInfo(departInfo);
    }

    public String deleteDepartInfo(String departId)
    {
        return departService.deleteDepartInfo(departId);
    }

    public String changeDepartInfoRoot(String targetId, String sourceId)
    {
        return departService.changeDepartInfoRoot(targetId, sourceId);
    }

    public List getDepartByCompanyId(int custId)
    {
        return departService.getDepartInfoByCompanyId(custId);
    }

    public String getDepartNoRoot(int custId, String departId)
    {
        return departService.getDepartNoRoot(custId, departId);
    }

    private DepartService departService;
    private GenPkIdService genPkIdservice;
}
