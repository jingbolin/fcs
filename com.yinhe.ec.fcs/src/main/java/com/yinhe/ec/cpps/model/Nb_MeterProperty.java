// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Nb_MeterProperty.java

package com.yinhe.ec.cpps.model;


public class Nb_MeterProperty
{

    public Nb_MeterProperty()
    {
        propertyId = "";
        propertyDatatype = "";
        propertyDatalength = 0;
        propertyDataunit = "";
        propertyIsnull = 0;
        propertyType = 0;
        createDate = "";
        serviceId = "";
    }

    public String getPropertyId()
    {
        return propertyId;
    }

    public void setPropertyId(String propertyId)
    {
        this.propertyId = propertyId;
    }

    public String getPropertyDatatype()
    {
        return propertyDatatype;
    }

    public void setPropertyDatatype(String propertyDatatype)
    {
        this.propertyDatatype = propertyDatatype;
    }

    public int getPropertyDatalength()
    {
        return propertyDatalength;
    }

    public void setPropertyDatalength(int propertyDatalength)
    {
        this.propertyDatalength = propertyDatalength;
    }

    public String getPropertyDataunit()
    {
        return propertyDataunit;
    }

    public void setPropertyDataunit(String propertyDataunit)
    {
        this.propertyDataunit = propertyDataunit;
    }

    public int getPropertyIsnull()
    {
        return propertyIsnull;
    }

    public void setPropertyIsnull(int propertyIsnull)
    {
        this.propertyIsnull = propertyIsnull;
    }

    public int getPropertyType()
    {
        return propertyType;
    }

    public void setPropertyType(int propertyType)
    {
        this.propertyType = propertyType;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    private String propertyId;
    private String propertyDatatype;
    private int propertyDatalength;
    private String propertyDataunit;
    private int propertyIsnull;
    private int propertyType;
    private String createDate;
    private String serviceId;
}
