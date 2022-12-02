// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OneNetNbDataDetail.java

package com.yinhe.ec.cpps.model;


public class OneNetNbDataDetail
{

    public OneNetNbDataDetail()
    {
        meterNo = "";
        deviceId = "";
        custId = 0;
        ds_id = "";
        ds_value = "";
        ds_at = "";
        readTime = "";
        remark = "";
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getDs_id()
    {
        return ds_id;
    }

    public void setDs_id(String ds_id)
    {
        this.ds_id = ds_id;
    }

    public String getDs_value()
    {
        return ds_value;
    }

    public void setDs_value(String ds_value)
    {
        this.ds_value = ds_value;
    }

    public String getDs_at()
    {
        return ds_at;
    }

    public void setDs_at(String ds_at)
    {
        this.ds_at = ds_at;
    }

    public String getReadTime()
    {
        return readTime;
    }

    public void setReadTime(String readTime)
    {
        this.readTime = readTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String meterNo;
    private String deviceId;
    private int custId;
    private String ds_id;
    private String ds_value;
    private String ds_at;
    private String readTime;
    private String remark;
}
