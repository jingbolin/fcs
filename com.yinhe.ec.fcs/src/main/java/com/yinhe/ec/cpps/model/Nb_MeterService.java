// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Nb_MeterService.java

package com.yinhe.ec.cpps.model;


public class Nb_MeterService
{

    public Nb_MeterService()
    {
        manuCode = "";
        serviceId = "";
        serviceDesc = "";
        createDate = "";
        methodName = "";
        serviceType = 0;
    }

    public String getManuCode()
    {
        return manuCode;
    }

    public void setManuCode(String manuCode)
    {
        this.manuCode = manuCode;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceDesc()
    {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc)
    {
        this.serviceDesc = serviceDesc;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public int getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(int serviceType)
    {
        this.serviceType = serviceType;
    }

    private String manuCode;
    private String serviceId;
    private String serviceDesc;
    private String createDate;
    private String methodName;
    private int serviceType;
}
