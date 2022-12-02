// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MusterLogs.java

package com.yinhe.ec.cpps.model;


public class MusterLogs
{

    public MusterLogs()
    {
    }

    public String getMusterNo()
    {
        return musterNo;
    }

    public void setMusterNo(String musterNo)
    {
        this.musterNo = musterNo;
    }

    public String getMusterDay()
    {
        return musterDay;
    }

    public void setMusterDay(String musterDay)
    {
        this.musterDay = musterDay;
    }

    public int getOffLineTimes()
    {
        return offLineTimes;
    }

    public void setOffLineTimes(int offLineTimes)
    {
        this.offLineTimes = offLineTimes;
    }

    public int getAliveTimes()
    {
        return aliveTimes;
    }

    public void setAliveTimes(int aliveTimes)
    {
        this.aliveTimes = aliveTimes;
    }

    public String getMusterAddr()
    {
        return musterAddr;
    }

    public void setMusterAddr(String musterAddr)
    {
        this.musterAddr = musterAddr;
    }

    public Double getOffALive()
    {
        return OffALive;
    }

    public void setOffALive(Double offALive)
    {
        OffALive = offALive;
    }

    private String musterNo;
    private String musterDay;
    private int offLineTimes;
    private int aliveTimes;
    private String musterAddr;
    private Double OffALive;
}
