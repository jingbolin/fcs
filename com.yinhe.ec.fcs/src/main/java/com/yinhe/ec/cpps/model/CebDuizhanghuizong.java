// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CebDuizhanghuizong.java

package com.yinhe.ec.cpps.model;


public class CebDuizhanghuizong
{

    public CebDuizhanghuizong()
    {
        markDay = "";
        cebPaytotal = Double.valueOf(0.0D);
        cebCount = 0;
        sysPaytotal = Double.valueOf(0.0D);
        sysCount = 0;
    }

    public String getMarkDay()
    {
        return markDay;
    }

    public void setMarkDay(String markDay)
    {
        this.markDay = markDay;
    }

    public Double getCebPaytotal()
    {
        return cebPaytotal;
    }

    public void setCebPaytotal(Double cebPaytotal)
    {
        this.cebPaytotal = cebPaytotal;
    }

    public int getCebCount()
    {
        return cebCount;
    }

    public void setCebCount(int cebCount)
    {
        this.cebCount = cebCount;
    }

    public Double getSysPaytotal()
    {
        return sysPaytotal;
    }

    public void setSysPaytotal(Double sysPaytotal)
    {
        this.sysPaytotal = sysPaytotal;
    }

    public int getSysCount()
    {
        return sysCount;
    }

    public void setSysCount(int sysCount)
    {
        this.sysCount = sysCount;
    }

    private String markDay;
    private Double cebPaytotal;
    private int cebCount;
    private Double sysPaytotal;
    private int sysCount;
}
