// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StepPriceList.java

package com.yinhe.ec.cpps.model;


public class StepPriceList
{

    public StepPriceList()
    {
    }

    public String getStepPriceId()
    {
        return stepPriceId;
    }

    public void setStepPriceId(String stepPriceId)
    {
        this.stepPriceId = stepPriceId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public int getStepStyle()
    {
        return stepStyle;
    }

    public void setStepStyle(int stepStyle)
    {
        this.stepStyle = stepStyle;
    }

    public String getStepDate()
    {
        return stepDate;
    }

    public void setStepDate(String stepDate)
    {
        this.stepDate = stepDate;
    }

    public Double getStep1Price()
    {
        return step1Price;
    }

    public void setStep1Price(Double step1Price)
    {
        this.step1Price = step1Price;
    }

    public int getStep1Dosage()
    {
        return step1Dosage;
    }

    public void setStep1Dosage(int step1Dosage)
    {
        this.step1Dosage = step1Dosage;
    }

    public Double getStep2Price()
    {
        return step2Price;
    }

    public void setStep2Price(Double step2Price)
    {
        this.step2Price = step2Price;
    }

    public int getStep2Dosage()
    {
        return step2Dosage;
    }

    public void setStep2Dosage(int step2Dosage)
    {
        this.step2Dosage = step2Dosage;
    }

    public Double getStep3Price()
    {
        return step3Price;
    }

    public void setStep3Price(Double step3Price)
    {
        this.step3Price = step3Price;
    }

    public int getStep3Dosage()
    {
        return step3Dosage;
    }

    public void setStep3Dosage(int step3Dosage)
    {
        this.step3Dosage = step3Dosage;
    }

    public Double getStep4Price()
    {
        return step4Price;
    }

    public void setStep4Price(Double step4Price)
    {
        this.step4Price = step4Price;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String stepPriceId;
    private int custId;
    private int stepStyle;
    private String stepDate;
    private Double step1Price;
    private int step1Dosage;
    private Double step2Price;
    private int step2Dosage;
    private Double step3Price;
    private int step3Dosage;
    private Double step4Price;
    private String remark;
}
