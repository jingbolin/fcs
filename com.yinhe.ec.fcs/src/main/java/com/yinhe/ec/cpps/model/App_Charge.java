// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_Charge.java

package com.yinhe.ec.cpps.model;


public class App_Charge
{

    public App_Charge()
    {
    }

    public String getFeeNo()
    {
        return feeNo;
    }

    public void setFeeNo(String feeNo)
    {
        this.feeNo = feeNo;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Integer getPayKind()
    {
        return payKind;
    }

    public void setPayKind(Integer payKind)
    {
        this.payKind = payKind;
    }

    public Double getFeeTotal()
    {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal)
    {
        this.feeTotal = feeTotal;
    }

    public Double getFineTotal()
    {
        return fineTotal;
    }

    public void setFineTotal(Double fineTotal)
    {
        this.fineTotal = fineTotal;
    }

    public Double getFineRemit()
    {
        return fineRemit;
    }

    public void setFineRemit(Double fineRemit)
    {
        this.fineRemit = fineRemit;
    }

    public Double getPayTotal()
    {
        return payTotal;
    }

    public void setPayTotal(Double payTotal)
    {
        this.payTotal = payTotal;
    }

    public Double getPayFee()
    {
        return payFee;
    }

    public void setPayFee(Double payFee)
    {
        this.payFee = payFee;
    }

    public String getBankRspNo()
    {
        return bankRspNo;
    }

    public void setBankRspNo(String bankRspNo)
    {
        this.bankRspNo = bankRspNo;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public Integer getCategory()
    {
        return category;
    }

    public void setCategory(Integer category)
    {
        this.category = category;
    }

    public String getSucFlag()
    {
        return sucFlag;
    }

    public void setSucFlag(String sucFlag)
    {
        this.sucFlag = sucFlag;
    }

    private String feeNo;
    private String userId;
    private Integer payKind;
    private Double feeTotal;
    private Double fineTotal;
    private Double fineRemit;
    private Double payTotal;
    private Double payFee;
    private String bankRspNo;
    private String meterNo;
    private Integer category;
    private String sucFlag;
}
