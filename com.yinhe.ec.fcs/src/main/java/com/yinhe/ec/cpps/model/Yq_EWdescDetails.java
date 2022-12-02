// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Yq_EWdescDetails.java

package com.yinhe.ec.cpps.model;


public class Yq_EWdescDetails
{

    public Yq_EWdescDetails()
    {
        deviceID = "";
        deviceAddr = "";
        paymentMode = "1";
        expireDate = "";
        balance = "0.00";
        curDosage = "0.00";
        allowpay = "0";
        reason = "";
        remark = "";
    }

    public String getDeviceID()
    {
        return deviceID;
    }

    public void setDeviceID(String deviceID)
    {
        this.deviceID = deviceID;
    }

    public String getDeviceAddr()
    {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr)
    {
        this.deviceAddr = deviceAddr;
    }

    public String getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode)
    {
        this.paymentMode = paymentMode;
    }

    public String getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(String expireDate)
    {
        this.expireDate = expireDate;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
    }

    public String getCurDosage()
    {
        return curDosage;
    }

    public void setCurDosage(String curDosage)
    {
        this.curDosage = curDosage;
    }

    public String getAllowpay()
    {
        return allowpay;
    }

    public void setAllowpay(String allowpay)
    {
        this.allowpay = allowpay;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String deviceID;
    private String deviceAddr;
    private String paymentMode;
    private String expireDate;
    private String balance;
    private String curDosage;
    private String allowpay;
    private String reason;
    private String remark;
}
