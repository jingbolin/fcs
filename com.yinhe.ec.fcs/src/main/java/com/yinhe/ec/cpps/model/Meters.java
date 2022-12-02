// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Meters.java

package com.yinhe.ec.cpps.model;


public class Meters
{

    public Meters()
    {
    }

    public Integer getMeterCount()
    {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount)
    {
        this.meterCount = meterCount;
    }

    public Integer getHasReadCount()
    {
        return hasReadCount;
    }

    public void setHasReadCount(Integer hasReadCount)
    {
        this.hasReadCount = hasReadCount;
    }

    public Integer getNotReadCount()
    {
        return notReadCount;
    }

    public void setNotReadCount(Integer notReadCount)
    {
        this.notReadCount = notReadCount;
    }

    public Integer meterCount;
    public Integer hasReadCount;
    public Integer notReadCount;
}
