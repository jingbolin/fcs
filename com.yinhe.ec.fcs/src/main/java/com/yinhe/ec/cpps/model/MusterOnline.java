// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MusterOnline.java

package com.yinhe.ec.cpps.model;


public class MusterOnline
{

    public MusterOnline()
    {
    }

    public String getMusterNo()
    {
        return musterNo;
    }

    public void setMusterNo(String musterNo)
    {
        this.musterNo = musterNo;
    }

    public String getOnline()
    {
        return online;
    }

    public void setOnline(String online)
    {
        this.online = online;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getLastDt()
    {
        return lastDt;
    }

    public void setLastDt(String lastDt)
    {
        this.lastDt = lastDt;
    }

    public String getAliveDt()
    {
        return aliveDt;
    }

    public void setAliveDt(String aliveDt)
    {
        this.aliveDt = aliveDt;
    }

    public String getAliveTimes()
    {
        return aliveTimes;
    }

    public void setAliveTimes(String aliveTimes)
    {
        this.aliveTimes = aliveTimes;
    }

    public String getOfflineDt()
    {
        return offlineDt;
    }

    public void setOfflineDt(String offlineDt)
    {
        this.offlineDt = offlineDt;
    }

    public String getOfflineTimes()
    {
        return offlineTimes;
    }

    public void setOfflineTimes(String offlineTimes)
    {
        this.offlineTimes = offlineTimes;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String musterNo;
    private String online;
    private String ip;
    private String port;
    private String lastDt;
    private String aliveDt;
    private String aliveTimes;
    private String offlineDt;
    private String offlineTimes;
    private String remark;
}
