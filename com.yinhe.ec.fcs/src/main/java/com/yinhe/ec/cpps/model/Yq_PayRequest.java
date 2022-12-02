// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Yq_PayRequest.java

package com.yinhe.ec.cpps.model;


public class Yq_PayRequest
{

    public Yq_PayRequest()
    {
        traceNo = "";
        channel = "MBNK";
        deviceId = "";
        payBillAmt = "0.00";
        custNo = "";
        freeNo = "";
        freeMonth = "";
    }

    public String getTraceNo()
    {
        return traceNo;
    }

    public void setTraceNo(String traceNo)
    {
        this.traceNo = traceNo;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getPayBillAmt()
    {
        return payBillAmt;
    }

    public void setPayBillAmt(String payBillAmt)
    {
        this.payBillAmt = payBillAmt;
    }

    public String getCustNo()
    {
        return custNo;
    }

    public void setCustNo(String custNo)
    {
        this.custNo = custNo;
    }

    public String getFreeNo()
    {
        return freeNo;
    }

    public void setFreeNo(String freeNo)
    {
        this.freeNo = freeNo;
    }

    public String getFreeMonth()
    {
        return freeMonth;
    }

    public void setFreeMonth(String freeMonth)
    {
        this.freeMonth = freeMonth;
    }

    private String traceNo;
    private String channel;
    private String deviceId;
    private String payBillAmt;
    private String custNo;
    private String freeNo;
    private String freeMonth;
}
