// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MixPriceList.java

package com.yinhe.ec.cpps.model;


public class MixPriceList
{

    public MixPriceList()
    {
    }

    public int getMixPriceId()
    {
        return mixPriceId;
    }

    public void setMixPriceId(int mixPriceId)
    {
        this.mixPriceId = mixPriceId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getFeeTypeId()
    {
        return feeTypeId;
    }

    public void setFeeTypeId(String feeTypeId)
    {
        this.feeTypeId = feeTypeId;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public int getRatio()
    {
        return ratio;
    }

    public void setRatio(int ratio)
    {
        this.ratio = ratio;
    }

    public int getMixType()
    {
        return mixType;
    }

    public void setMixType(int mixType)
    {
        this.mixType = mixType;
    }

    private int mixPriceId;
    private int custId;
    private String feeTypeId;
    private Double price;
    private int ratio;
    private int mixType;
}
