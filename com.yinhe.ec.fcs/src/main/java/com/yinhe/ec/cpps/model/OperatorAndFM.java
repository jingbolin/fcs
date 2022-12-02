// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorAndFM.java

package com.yinhe.ec.cpps.model;


public class OperatorAndFM
{

    public OperatorAndFM()
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

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getFmId()
    {
        return fmId;
    }

    public void setFmId(String fmId)
    {
        this.fmId = fmId;
    }

    private int operatorId;
    private int custId;
    private String fmId;
}
