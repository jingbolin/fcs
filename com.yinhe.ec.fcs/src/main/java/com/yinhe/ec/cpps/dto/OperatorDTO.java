// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorDTO.java

package com.yinhe.ec.cpps.dto;

import com.yinhe.ec.cpps.model.Operator;

public class OperatorDTO extends Operator
{

    public OperatorDTO()
    {
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    private String roleId;
}
