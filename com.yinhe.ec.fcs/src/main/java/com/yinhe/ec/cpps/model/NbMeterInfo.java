// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbMeterInfo.java

package com.yinhe.ec.cpps.model;


public class NbMeterInfo
{

    public NbMeterInfo()
    {
        meterNo = "";
        nodeId = "";
        custId = 0;
        regState = 0;
        regTime = "";
        deviceId = "";
        dosageSum = Double.valueOf(0.0D);
        batVolt = Double.valueOf(0.0D);
        temperature = Double.valueOf(0.0D);
        switchState = 0;
        sigValue = Double.valueOf(0.0D);
        errDesc = "";
        readTime = "";
        createTime = "";
        createUser = 0;
        remark = "";
        onOffState = "";
        manuCode = "";
        tmodel = 0;
        imsi = "";
        useFlag = 0;
        meterAddr = "";
        custName = "";
        batchNo = "";
        pwdGroupNo = 0;
        uploadDeviceId = "";
        uploadNodeId = "";
        typeId = 1;
        buyCount = 0;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public String getNodeId()
    {
        return nodeId;
    }

    public void setNodeId(String nodeId)
    {
        this.nodeId = nodeId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public int getRegState()
    {
        return regState;
    }

    public void setRegState(int regState)
    {
        this.regState = regState;
    }

    public String getRegTime()
    {
        return regTime;
    }

    public void setRegTime(String regTime)
    {
        this.regTime = regTime;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public Double getDosageSum()
    {
        return dosageSum;
    }

    public void setDosageSum(Double dosageSum)
    {
        this.dosageSum = dosageSum;
    }

    public Double getBatVolt()
    {
        return batVolt;
    }

    public void setBatVolt(Double batVolt)
    {
        this.batVolt = batVolt;
    }

    public Double getTemperature()
    {
        return temperature;
    }

    public void setTemperature(Double temperature)
    {
        this.temperature = temperature;
    }

    public int getSwitchState()
    {
        return switchState;
    }

    public void setSwitchState(int switchState)
    {
        this.switchState = switchState;
    }

    public Double getSigValue()
    {
        return sigValue;
    }

    public void setSigValue(Double sigValue)
    {
        this.sigValue = sigValue;
    }

    public String getErrDesc()
    {
        return errDesc;
    }

    public void setErrDesc(String errDesc)
    {
        this.errDesc = errDesc;
    }

    public String getReadTime()
    {
        return readTime;
    }

    public void setReadTime(String readTime)
    {
        this.readTime = readTime;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public int getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(int createUser)
    {
        this.createUser = createUser;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getOnOffState()
    {
        return onOffState;
    }

    public void setOnOffState(String onOffState)
    {
        this.onOffState = onOffState;
    }

    public String getManuCode()
    {
        return manuCode;
    }

    public void setManuCode(String manuCode)
    {
        this.manuCode = manuCode;
    }

    public int getTmodel()
    {
        return tmodel;
    }

    public void setTmodel(int tmodel)
    {
        this.tmodel = tmodel;
    }

    public String getImsi()
    {
        return imsi;
    }

    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    public int getUseFlag()
    {
        return useFlag;
    }

    public void setUseFlag(int useFlag)
    {
        this.useFlag = useFlag;
    }

    public String getMeterAddr()
    {
        return meterAddr;
    }

    public void setMeterAddr(String meterAddr)
    {
        this.meterAddr = meterAddr;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public int getPwdGroupNo()
    {
        return pwdGroupNo;
    }

    public void setPwdGroupNo(int pwdGroupNo)
    {
        this.pwdGroupNo = pwdGroupNo;
    }

    public String getUploadDeviceId()
    {
        return uploadDeviceId;
    }

    public void setUploadDeviceId(String uploadDeviceId)
    {
        this.uploadDeviceId = uploadDeviceId;
    }

    public String getUploadNodeId()
    {
        return uploadNodeId;
    }

    public void setUploadNodeId(String uploadNodeId)
    {
        this.uploadNodeId = uploadNodeId;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public int getBuyCount()
    {
        return buyCount;
    }

    public void setBuyCount(int buyCount)
    {
        this.buyCount = buyCount;
    }

    private String meterNo;
    private String nodeId;
    private int custId;
    private int regState;
    private String regTime;
    private String deviceId;
    private Double dosageSum;
    private Double batVolt;
    private Double temperature;
    private int switchState;
    private Double sigValue;
    private String errDesc;
    private String readTime;
    private String createTime;
    private int createUser;
    private String remark;
    private String onOffState;
    private String manuCode;
    private int tmodel;
    private String imsi;
    private int useFlag;
    private String meterAddr;
    private String custName;
    private String batchNo;
    private int pwdGroupNo;
    private String uploadDeviceId;
    private String uploadNodeId;
    private int typeId;
    private int buyCount;
}
