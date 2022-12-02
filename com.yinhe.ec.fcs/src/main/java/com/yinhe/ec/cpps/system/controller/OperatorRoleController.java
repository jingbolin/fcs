// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorRoleController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.model.OperRole;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.system.service.OperatorRoleService;
import com.yinhe.ec.cpps.util.Tools;

import java.util.List;
import java.util.Map;

public class OperatorRoleController
{

    public OperatorRoleController()
    {
    }

    public String addOperRole(String orName, int custId, String orPid)
    {
        operRole.setOrId(genPkIdservice.getPkNoByTable("OperRole", "ORID", Integer.valueOf(4)));
        operRole.setOrName(orName);
        operRole.setOrCreateDate(Tools.getNow());
        operRole.setCustId(custId);
        operRole.setOrPid(orPid);
        return operatorRoleService.addOperRole(operRole);
    }

    public Map getOperRole(int page, int rows, int custId, String orId)
    {
        return operatorRoleService.getOperRole(page, rows, custId, orId);
    }

    public String editOperRole(String orId, String orName)
    {
        operRole.setOrId(orId);
        operRole.setOrName(orName);
        return operatorRoleService.editOperRole(operRole);
    }

    public String delOperRoleById(String operRoleId)
    {
        return operatorRoleService.delOperRoleById(operRoleId);
    }

    public String setRoleMethod(String orId, String indeterminateId, String checkedId, int custId)
    {
        return operatorRoleService.setRoleMethod(orId, indeterminateId, checkedId, custId);
    }

    public String getRoleMethod(String orId, String operatorAccount)
    {
        List roleList = operatorRoleService.getOperRoleByOperatorAccount(operatorAccount);
        return operatorRoleService.getRoleMethod(orId, roleList);
    }

    public List getAllOperRole(int custId)
    {
        return operatorRoleService.getAllOperRole(custId);
    }

    private OperatorRoleService operatorRoleService;
    private OperRole operRole;
    private GenPkIdService genPkIdservice;
}
