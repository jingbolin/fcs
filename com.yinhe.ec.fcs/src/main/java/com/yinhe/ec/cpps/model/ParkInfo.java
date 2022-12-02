// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParkInfo.java

package com.yinhe.ec.cpps.model;


public class ParkInfo
{

    public ParkInfo()
    {
        areaId = "";
    }

    public int getParkId()
    {
        return parkId;
    }

    public void setParkId(int parkId)
    {
        this.parkId = parkId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getParkNo()
    {
        return parkNo;
    }

    public void setParkNo(String parkNo)
    {
        this.parkNo = parkNo;
    }

    public int getParkProperty()
    {
        return parkProperty;
    }

    public void setParkProperty(int parkProperty)
    {
        this.parkProperty = parkProperty;
    }

    public int getParkState()
    {
        return parkState;
    }

    public void setParkState(int parkState)
    {
        this.parkState = parkState;
    }

    public int getFreeParkTime()
    {
        return freeParkTime;
    }

    public void setFreeParkTime(int freeParkTime)
    {
        this.freeParkTime = freeParkTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    private int parkId;
    private int custId;
    private String parkNo;
    private int parkProperty;
    private int parkState;
    private int freeParkTime;
    private String remark;
    private String areaId;
}
