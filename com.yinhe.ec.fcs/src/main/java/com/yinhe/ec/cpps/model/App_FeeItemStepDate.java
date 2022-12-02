// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_FeeItemStepDate.java

package com.yinhe.ec.cpps.model;


public class App_FeeItemStepDate
{

    public App_FeeItemStepDate()
    {
    }

    public String getFeeItemId()
    {
        return feeItemId;
    }

    public void setFeeItemId(String feeItemId)
    {
        this.feeItemId = feeItemId;
    }

    public String getStepDate()
    {
        return stepDate;
    }

    public void setStepDate(String stepDate)
    {
        this.stepDate = stepDate;
    }

    private String feeItemId;
    private String stepDate;
}
