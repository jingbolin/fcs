// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   YinheMeterData.java

package com.yinhe.ec.cpps.model;


public class YinheMeterData
{

    public YinheMeterData()
    {
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

    public String getReadTime()
    {
        return readTime;
    }

    public void setReadTime(String readTime)
    {
        this.readTime = readTime;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getMeterData()
    {
        return meterData;
    }

    public void setMeterData(String meterData)
    {
        this.meterData = meterData;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getDatatypeName()
    {
        return datatypeName;
    }

    public void setDatatypeName(String datatypeName)
    {
        this.datatypeName = datatypeName;
    }

    public String getReciveFrame()
    {
        return reciveFrame;
    }

    public void setReciveFrame(String reciveFrame)
    {
        this.reciveFrame = reciveFrame;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getCommandId()
    {
        return commandId;
    }

    public void setCommandId(String commandId)
    {
        this.commandId = commandId;
    }

    private String meterNo;
    private String deviceId;
    private String readTime;
    private int custId;
    private String meterData;
    private String dataType;
    private String datatypeName;
    private String reciveFrame;
    private String remark;
    private String commandId;
}
