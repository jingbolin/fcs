// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbDataDetail.java

package com.yinhe.ec.cpps.model;


public class NbDataDetail
{

    public NbDataDetail()
    {
        meterNo = "";
        deviceId = "";
        custId = 0;
        dosageSum = Double.valueOf(0.0D);
        batVolt = Double.valueOf(0.0D);
        temperature = Double.valueOf(0.0D);
        switchState = 0;
        sigValue = Double.valueOf(0.0D);
        errDesc = "";
        readTime = "";
        remark = "";
        actPower = "";
        reactPower = "";
        powerFac = "";
        volt = "";
        current = "";
        zlCurrent = "";
        appPower = "";
        gridsFreq = "";
        currentCsq = "";
        positiveActPower = "";
        unPositiveActPower = "";
        dataType = "";
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

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getActPower()
    {
        return actPower;
    }

    public void setActPower(String actPower)
    {
        this.actPower = actPower;
    }

    public String getReactPower()
    {
        return reactPower;
    }

    public void setReactPower(String reactPower)
    {
        this.reactPower = reactPower;
    }

    public String getPowerFac()
    {
        return powerFac;
    }

    public void setPowerFac(String powerFac)
    {
        this.powerFac = powerFac;
    }

    public String getVolt()
    {
        return volt;
    }

    public void setVolt(String volt)
    {
        this.volt = volt;
    }

    public String getCurrent()
    {
        return current;
    }

    public void setCurrent(String current)
    {
        this.current = current;
    }

    public String getZlCurrent()
    {
        return zlCurrent;
    }

    public void setZlCurrent(String zlCurrent)
    {
        this.zlCurrent = zlCurrent;
    }

    public String getAppPower()
    {
        return appPower;
    }

    public void setAppPower(String appPower)
    {
        this.appPower = appPower;
    }

    public String getGridsFreq()
    {
        return gridsFreq;
    }

    public void setGridsFreq(String gridsFreq)
    {
        this.gridsFreq = gridsFreq;
    }

    public String getCurrentCsq()
    {
        return currentCsq;
    }

    public void setCurrentCsq(String currentCsq)
    {
        this.currentCsq = currentCsq;
    }

    public String getPositiveActPower()
    {
        return positiveActPower;
    }

    public void setPositiveActPower(String positiveActPower)
    {
        this.positiveActPower = positiveActPower;
    }

    public String getUnPositiveActPower()
    {
        return unPositiveActPower;
    }

    public void setUnPositiveActPower(String unPositiveActPower)
    {
        this.unPositiveActPower = unPositiveActPower;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    private String meterNo;
    private String deviceId;
    private int custId;
    private Double dosageSum;
    private Double batVolt;
    private Double temperature;
    private int switchState;
    private Double sigValue;
    private String errDesc;
    private String readTime;
    private String remark;
    private String actPower;
    private String reactPower;
    private String powerFac;
    private String volt;
    private String current;
    private String zlCurrent;
    private String appPower;
    private String gridsFreq;
    private String currentCsq;
    private String positiveActPower;
    private String unPositiveActPower;
    private String dataType;
}
