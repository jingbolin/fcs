// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Yq_PdescDetails.java

package com.yinhe.ec.cpps.model;


public class Yq_PdescDetails
{

    public Yq_PdescDetails()
    {
        freeNo = "";
        freeName = "";
        freeMonth = "";
        balance = "0.00";
        expireDate = "";
        remark = "";
    }

    public String getFreeNo()
    {
        return freeNo;
    }

    public void setFreeNo(String freeNo)
    {
        this.freeNo = freeNo;
    }

    public String getFreeName()
    {
        return freeName;
    }

    public void setFreeName(String freeName)
    {
        this.freeName = freeName;
    }

    public String getFreeMonth()
    {
        return freeMonth;
    }

    public void setFreeMonth(String freeMonth)
    {
        this.freeMonth = freeMonth;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
    }

    public String getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(String expireDate)
    {
        this.expireDate = expireDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String freeNo;
    private String freeName;
    private String freeMonth;
    private String balance;
    private String expireDate;
    private String remark;
}
