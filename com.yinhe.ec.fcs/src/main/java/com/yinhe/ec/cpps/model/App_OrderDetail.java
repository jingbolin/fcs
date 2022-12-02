// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_OrderDetail.java

package com.yinhe.ec.cpps.model;


public class App_OrderDetail
{

    public App_OrderDetail()
    {
    }

    public String getPayFeeDetailId()
    {
        return payFeeDetailId;
    }

    public void setPayFeeDetailId(String payFeeDetailId)
    {
        this.payFeeDetailId = payFeeDetailId;
    }

    public String getPayFeeId()
    {
        return payFeeId;
    }

    public void setPayFeeId(String payFeeId)
    {
        this.payFeeId = payFeeId;
    }

    public Double getRcvblAmt()
    {
        return rcvblAmt;
    }

    public void setRcvblAmt(Double rcvblAmt)
    {
        this.rcvblAmt = rcvblAmt;
    }

    public Double getLateFeeAmt()
    {
        return lateFeeAmt;
    }

    public void setLateFeeAmt(Double lateFeeAmt)
    {
        this.lateFeeAmt = lateFeeAmt;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public String getPayItem()
    {
        return payItem;
    }

    public void setPayItem(String payItem)
    {
        this.payItem = payItem;
    }

    public String getPayItemName()
    {
        return payItemName;
    }

    public void setPayItemName(String payItemName)
    {
        this.payItemName = payItemName;
    }

    private String payFeeDetailId;
    private String payFeeId;
    private Double rcvblAmt;
    private Double lateFeeAmt;
    private Double amount;
    private String payItem;
    private String payItemName;
}
