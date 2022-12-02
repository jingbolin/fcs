// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperRoleList.java

package com.yinhe.ec.cpps.model;


public class OperRoleList
{

    public OperRoleList()
    {
    }

    public String getOrId()
    {
        return orId;
    }

    public void setOrId(String orId)
    {
        this.orId = orId;
    }

    public int getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(int operatorId)
    {
        this.operatorId = operatorId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getOrlRemark()
    {
        return orlRemark;
    }

    public void setOrlRemark(String orlRemark)
    {
        this.orlRemark = orlRemark;
    }

    private String orId;
    private int operatorId;
    private int custId;
    private String orlRemark;
}
