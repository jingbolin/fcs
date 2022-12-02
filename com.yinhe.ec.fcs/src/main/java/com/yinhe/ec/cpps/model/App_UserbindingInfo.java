// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_UserbindingInfo.java

package com.yinhe.ec.cpps.model;


public class App_UserbindingInfo
{

    public App_UserbindingInfo()
    {
    }

    public String getBindingId()
    {
        return bindingId;
    }

    public void setBindingId(String bindingId)
    {
        this.bindingId = bindingId;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getSysUserId()
    {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId)
    {
        this.sysUserId = sysUserId;
    }

    public int getIsbinding()
    {
        return isbinding;
    }

    public void setIsbinding(int isbinding)
    {
        this.isbinding = isbinding;
    }

    public String getBindDate()
    {
        return bindDate;
    }

    public void setBindDate(String bindDate)
    {
        this.bindDate = bindDate;
    }

    public int getIsDefault()
    {
        return isDefault;
    }

    public void setIsDefault(int isDefault)
    {
        this.isDefault = isDefault;
    }

    private String bindingId;
    private String regId;
    private String sysUserId;
    private int isbinding;
    private String bindDate;
    private int isDefault;
}
