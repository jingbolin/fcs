// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_DosageInfo.java

package com.yinhe.ec.cpps.model;


public class App_DosageInfo
{

    public App_DosageInfo()
    {
        dosageDate = "";
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public String getMarkDate()
    {
        return markDate;
    }

    public void setMarkDate(String markDate)
    {
        this.markDate = markDate;
    }

    public Double getDosage()
    {
        return dosage;
    }

    public void setDosage(Double dosage)
    {
        this.dosage = dosage;
    }

    public String getDosageFee()
    {
        return dosageFee;
    }

    public void setDosageFee(String dosageFee)
    {
        this.dosageFee = dosageFee;
    }

    public String getDosageDate()
    {
        return dosageDate;
    }

    public void setDosageDate(String dosageDate)
    {
        this.dosageDate = dosageDate;
    }

    public Integer getCurrStep()
    {
        return currStep;
    }

    public void setCurrStep(Integer currStep)
    {
        this.currStep = currStep;
    }

    private String meterNo;
    private String markDate;
    private Double dosage;
    private String dosageFee;
    private String dosageDate;
    private Integer currStep;
}
