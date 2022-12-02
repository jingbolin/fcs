// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ceb_duizhang.java

package com.yinhe.ec.cpps.model;


public class Ceb_duizhang
{

    public Ceb_duizhang()
    {
        userId = "";
        orderNo = "";
        trxDate = "";
        amount = Double.valueOf(0.0D);
        bankRspNo = "";
        feetypeMark = 0;
        userName = "";
        billState = 0;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getTrxDate()
    {
        return trxDate;
    }

    public void setTrxDate(String trxDate)
    {
        this.trxDate = trxDate;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public String getBankRspNo()
    {
        return bankRspNo;
    }

    public void setBankRspNo(String bankRspNo)
    {
        this.bankRspNo = bankRspNo;
    }

    public int getFeetypeMark()
    {
        return feetypeMark;
    }

    public void setFeetypeMark(int feetypeMark)
    {
        this.feetypeMark = feetypeMark;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getBillState()
    {
        return billState;
    }

    public void setBillState(int billState)
    {
        this.billState = billState;
    }

    private String userId;
    private String orderNo;
    private String trxDate;
    private Double amount;
    private String bankRspNo;
    private int feetypeMark;
    private String userName;
    private int billState;
}
