// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterProtocol.java

package com.yinhe.ec.cpps.model;


public class MeterProtocol
{

    public MeterProtocol()
    {
    }

    public int getMeterPtl()
    {
        return meterPtl;
    }

    public void setMeterPtl(int meterPtl)
    {
        this.meterPtl = meterPtl;
    }

    public String getPtlName()
    {
        return ptlName;
    }

    public void setPtlName(String ptlName)
    {
        this.ptlName = ptlName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int meterPtl;
    private String ptlName;
    private String remark;
}
