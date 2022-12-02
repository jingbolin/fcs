// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_InformationType.java

package com.yinhe.ec.cpps.model;


public class App_InformationType
{

    public App_InformationType()
    {
    }

    public String getInformationTypeId()
    {
        return informationTypeId;
    }

    public void setInformationTypeId(String informationTypeId)
    {
        this.informationTypeId = informationTypeId;
    }

    public String getInformationType()
    {
        return informationType;
    }

    public void setInformationType(String informationType)
    {
        this.informationType = informationType;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    private String informationTypeId;
    private String informationType;
    private String createDate;
}
