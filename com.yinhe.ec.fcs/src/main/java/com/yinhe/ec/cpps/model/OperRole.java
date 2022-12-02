// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperRole.java

package com.yinhe.ec.cpps.model;


public class OperRole
{

    public OperRole()
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

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getOrName()
    {
        return orName;
    }

    public void setOrName(String orName)
    {
        this.orName = orName;
    }

    public String getOrCreateDate()
    {
        return orCreateDate;
    }

    public void setOrCreateDate(String orCreateDate)
    {
        this.orCreateDate = orCreateDate;
    }

    public String getOrRemark()
    {
        return orRemark;
    }

    public void setOrRemark(String orRemark)
    {
        this.orRemark = orRemark;
    }

    public String getOrPid()
    {
        return orPid;
    }

    public void setOrPid(String orPid)
    {
        this.orPid = orPid;
    }

    private String orId;
    private int custId;
    private String orName;
    private String orCreateDate;
    private String orRemark;
    private String orPid;
}
