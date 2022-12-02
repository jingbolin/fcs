// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UniUser.java

package com.yinhe.ec.cpps.model;


public class UniUser
{

    public UniUser()
    {
    }

    public int getUniId()
    {
        return uniId;
    }

    public void setUniId(int uniId)
    {
        this.uniId = uniId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getUniName()
    {
        return uniName;
    }

    public void setUniName(String uniName)
    {
        this.uniName = uniName;
    }

    public int getUniPrint()
    {
        return uniPrint;
    }

    public void setUniPrint(int uniPrint)
    {
        this.uniPrint = uniPrint;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int uniId;
    private int custId;
    private String uniName;
    private int uniPrint;
    private String remark;
}
