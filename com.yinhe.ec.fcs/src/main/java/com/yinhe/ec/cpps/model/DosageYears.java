// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DosageYears.java

package com.yinhe.ec.cpps.model;


public class DosageYears
{

    public DosageYears()
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

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public Double getYearDosage()
    {
        return yearDosage;
    }

    public void setYearDosage(Double yearDosage)
    {
        this.yearDosage = yearDosage;
    }

    public Double getDosageSum()
    {
        return dosageSum;
    }

    public void setDosageSum(Double dosageSum)
    {
        this.dosageSum = dosageSum;
    }

    public String getReadDate()
    {
        return readDate;
    }

    public void setReadDate(String readDate)
    {
        this.readDate = readDate;
    }

    public Double getYearFee()
    {
        return yearFee;
    }

    public void setYearFee(Double yearFee)
    {
        this.yearFee = yearFee;
    }

    public Double getYearTotalFee()
    {
        return yearTotalFee;
    }

    public void setYearTotalFee(Double yearTotalFee)
    {
        this.yearTotalFee = yearTotalFee;
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
    private String startDate;
    private String endDate;
    private Double yearDosage;
    private Double dosageSum;
    private String readDate;
    private Double yearFee;
    private Double yearTotalFee;
    private String remark;
}
