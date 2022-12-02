// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorAccountDTO.java

package com.yinhe.ec.cpps.dto;


public class OperatorAccountDTO
{

    public OperatorAccountDTO()
    {
    }

    public int getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(int operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getOperatorAccount()
    {
        return operatorAccount;
    }

    public void setOperatorAccount(String operatorAccount)
    {
        this.operatorAccount = operatorAccount;
    }

    public String getOldPwd()
    {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd()
    {
        return newPwd;
    }

    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }

    private int operatorId;
    private String operatorAccount;
    private String oldPwd;
    private String newPwd;
}
