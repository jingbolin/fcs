// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterAnalysis.java

package com.yinhe.ec.cpps.model;


public class MeterAnalysis
{

    public MeterAnalysis()
    {
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getMarkDay()
    {
        return markDay;
    }

    public void setMarkDay(String markDay)
    {
        this.markDay = markDay;
    }

    public int getMeterCount()
    {
        return meterCount;
    }

    public void setMeterCount(int meterCount)
    {
        this.meterCount = meterCount;
    }

    public int getReadCount()
    {
        return readCount;
    }

    public void setReadCount(int readCount)
    {
        this.readCount = readCount;
    }

    public int getUnReadCount()
    {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount)
    {
        this.unReadCount = unReadCount;
    }

    private String areaName;
    private String markDay;
    private int meterCount;
    private int readCount;
    private int unReadCount;
}
