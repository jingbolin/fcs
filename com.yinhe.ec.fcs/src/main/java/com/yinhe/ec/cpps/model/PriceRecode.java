// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PriceRecode.java

package com.yinhe.ec.cpps.model;


public class PriceRecode
{

    public PriceRecode()
    {
        codeDesc = "";
        curPrice = Double.valueOf(0.0D);
        curTotal = Double.valueOf(0.0D);
        curQuantity = Double.valueOf(0.0D);
    }

    public Integer getCodeId()
    {
        return codeId;
    }

    public void setCodeId(Integer codeId)
    {
        this.codeId = codeId;
    }

    public String getCodeDesc()
    {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc)
    {
        this.codeDesc = codeDesc;
    }

    public Double getCurPrice()
    {
        return curPrice;
    }

    public void setCurPrice(Double curPrice)
    {
        this.curPrice = curPrice;
    }

    public Double getCurTotal()
    {
        return curTotal;
    }

    public void setCurTotal(Double curTotal)
    {
        this.curTotal = curTotal;
    }

    public Double getCurQuantity()
    {
        return curQuantity;
    }

    public void setCurQuantity(Double curQuantity)
    {
        this.curQuantity = curQuantity;
    }

    private Integer codeId;
    private String codeDesc;
    private Double curPrice;
    private Double curTotal;
    private Double curQuantity;
}
