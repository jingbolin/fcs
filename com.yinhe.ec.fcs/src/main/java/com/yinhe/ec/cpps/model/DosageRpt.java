// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DosageRpt.java

package com.yinhe.ec.cpps.model;


public class DosageRpt
{

    public DosageRpt()
    {
        d1 = Double.valueOf(0.0D);
        d2 = Double.valueOf(0.0D);
        d3 = Double.valueOf(0.0D);
        d4 = Double.valueOf(0.0D);
        f1 = Double.valueOf(0.0D);
        f2 = Double.valueOf(0.0D);
        f3 = Double.valueOf(0.0D);
        f4 = Double.valueOf(0.0D);
    }

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getSubName()
    {
        return subName;
    }

    public void setSubName(String subName)
    {
        this.subName = subName;
    }

    public Double getDosage()
    {
        return dosage;
    }

    public void setDosage(Double dosage)
    {
        this.dosage = dosage;
    }

    public int getMeterCount()
    {
        return meterCount;
    }

    public void setMeterCount(int meterCount)
    {
        this.meterCount = meterCount;
    }

    public String getzDosage()
    {
        return zDosage;
    }

    public void setzDosage(String zDosage)
    {
        this.zDosage = zDosage;
    }

    public String gethDosage()
    {
        return hDosage;
    }

    public void sethDosage(String hDosage)
    {
        this.hDosage = hDosage;
    }

    public String getLossDosage()
    {
        return lossDosage;
    }

    public void setLossDosage(String lossDosage)
    {
        this.lossDosage = lossDosage;
    }

    public String getLossPercent()
    {
        return lossPercent;
    }

    public void setLossPercent(String lossPercent)
    {
        this.lossPercent = lossPercent;
    }

    public Double getDateFee()
    {
        return dateFee;
    }

    public void setDateFee(Double dateFee)
    {
        this.dateFee = dateFee;
    }

    public Double getD1()
    {
        return d1;
    }

    public void setD1(Double d1)
    {
        this.d1 = d1;
    }

    public Double getD2()
    {
        return d2;
    }

    public void setD2(Double d2)
    {
        this.d2 = d2;
    }

    public Double getD3()
    {
        return d3;
    }

    public void setD3(Double d3)
    {
        this.d3 = d3;
    }

    public Double getD4()
    {
        return d4;
    }

    public void setD4(Double d4)
    {
        this.d4 = d4;
    }

    public Double getF1()
    {
        return f1;
    }

    public void setF1(Double f1)
    {
        this.f1 = f1;
    }

    public Double getF2()
    {
        return f2;
    }

    public void setF2(Double f2)
    {
        this.f2 = f2;
    }

    public Double getF3()
    {
        return f3;
    }

    public void setF3(Double f3)
    {
        this.f3 = f3;
    }

    public Double getF4()
    {
        return f4;
    }

    public void setF4(Double f4)
    {
        this.f4 = f4;
    }

    private String feeItemName;
    private String areaName;
    private String subName;
    private Double dosage;
    private Double dateFee;
    private int meterCount;
    private String zDosage;
    private String hDosage;
    private String lossDosage;
    private String lossPercent;
    private Double d1;
    private Double d2;
    private Double d3;
    private Double d4;
    private Double f1;
    private Double f2;
    private Double f3;
    private Double f4;
}
