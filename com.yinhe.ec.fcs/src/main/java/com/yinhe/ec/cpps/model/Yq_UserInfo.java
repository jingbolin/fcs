// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Yq_UserInfo.java

package com.yinhe.ec.cpps.model;


public class Yq_UserInfo
{

    public Yq_UserInfo()
    {
        code = 0;
        msg = "";
        custNo = "";
        custName = "";
        custAddress = "";
        phoneNumber = "";
        rcvMerchantId = "";
        rcvMerchantName = "";
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getCustNo()
    {
        return custNo;
    }

    public void setCustNo(String custNo)
    {
        this.custNo = custNo;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getCustAddress()
    {
        return custAddress;
    }

    public void setCustAddress(String custAddress)
    {
        this.custAddress = custAddress;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getRcvMerchantId()
    {
        return rcvMerchantId;
    }

    public void setRcvMerchantId(String rcvMerchantId)
    {
        this.rcvMerchantId = rcvMerchantId;
    }

    public String getRcvMerchantName()
    {
        return rcvMerchantName;
    }

    public void setRcvMerchantName(String rcvMerchantName)
    {
        this.rcvMerchantName = rcvMerchantName;
    }

    private int code;
    private String msg;
    private String custNo;
    private String custName;
    private String custAddress;
    private String phoneNumber;
    private String rcvMerchantId;
    private String rcvMerchantName;
}
