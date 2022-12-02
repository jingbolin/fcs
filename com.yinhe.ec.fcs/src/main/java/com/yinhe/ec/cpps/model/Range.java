// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Range.java

package com.yinhe.ec.cpps.model;


public class Range
{

    public Range()
    {
    }

    public int getRangeId()
    {
        return rangeId;
    }

    public void setRangeId(int rangeId)
    {
        this.rangeId = rangeId;
    }

    public Double getRangeValue()
    {
        return rangeValue;
    }

    public void setRangeValue(Double rangeValue)
    {
        this.rangeValue = rangeValue;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int rangeId;
    private Double rangeValue;
    private String remark;
}
