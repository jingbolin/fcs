// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CebDuizhang.java

package com.yinhe.ec.cpps.model;


public class CebDuizhang
{

    public CebDuizhang()
    {
        userId = "";
        orderNo = "";
        trxDate = "";
        amount = "";
        bankrspNo = "";
        feetypemark = "";
        userName = "";
        billState = "";
        userAddr = "";
        mobilePhone = "";
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

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getBankrspNo()
    {
        return bankrspNo;
    }

    public void setBankrspNo(String bankrspNo)
    {
        this.bankrspNo = bankrspNo;
    }

    public String getFeetypemark()
    {
        return feetypemark;
    }

    public void setFeetypemark(String feetypemark)
    {
        this.feetypemark = feetypemark;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getBillState()
    {
        return billState;
    }

    public void setBillState(String billState)
    {
        this.billState = billState;
    }

    public String getUserAddr()
    {
        return userAddr;
    }

    public void setUserAddr(String userAddr)
    {
        this.userAddr = userAddr;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    private String userId;
    private String orderNo;
    private String trxDate;
    private String amount;
    private String bankrspNo;
    private String feetypemark;
    private String userName;
    private String billState;
    private String userAddr;
    private String mobilePhone;
}
