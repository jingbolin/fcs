// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnOffLogs.java

package com.yinhe.ec.cpps.model;


public class OnOffLogs
{

    public OnOffLogs()
    {
    }

    public int getOnOffId()
    {
        return onOffId;
    }

    public void setOnOffId(int onOffId)
    {
        this.onOffId = onOffId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public int getOnOffFlag()
    {
        return onOffFlag;
    }

    public void setOnOffFlag(int onOffFlag)
    {
        this.onOffFlag = onOffFlag;
    }

    public int getSuccFlag()
    {
        return succFlag;
    }

    public void setSuccFlag(int succFlag)
    {
        this.succFlag = succFlag;
    }

    public String getOnOffDate()
    {
        return onOffDate;
    }

    public void setOnOffDate(String onOffDate)
    {
        this.onOffDate = onOffDate;
    }

    public String getOnOffUser()
    {
        return onOffUser;
    }

    public void setOnOffUser(String onOffUser)
    {
        this.onOffUser = onOffUser;
    }

    private int onOffId;
    private int custId;
    private String meterNo;
    private int onOffFlag;
    private int succFlag;
    private String onOffDate;
    private String onOffUser;
}
