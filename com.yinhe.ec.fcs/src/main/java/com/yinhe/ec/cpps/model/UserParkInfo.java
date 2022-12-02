// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserParkInfo.java

package com.yinhe.ec.cpps.model;


public class UserParkInfo
{

    public UserParkInfo()
    {
        parkNo = "";
        feeItemName = "";
        theFee = Double.valueOf(0.0D);
        carStartDate = "";
        carEndDate = "";
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
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

    public String getFeeItemId()
    {
        return feeItemId;
    }

    public void setFeeItemId(String feeItemId)
    {
        this.feeItemId = feeItemId;
    }

    public int getFeeMode()
    {
        return feeMode;
    }

    public void setFeeMode(int feeMode)
    {
        this.feeMode = feeMode;
    }

    public String getCarNo()
    {
        return carNo;
    }

    public void setCarNo(String carNo)
    {
        this.carNo = carNo;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getParkNo()
    {
        return parkNo;
    }

    public void setParkNo(String parkNo)
    {
        this.parkNo = parkNo;
    }

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public Double getTheFee()
    {
        return theFee;
    }

    public void setTheFee(Double theFee)
    {
        this.theFee = theFee;
    }

    public String getCarStartDate()
    {
        return carStartDate;
    }

    public void setCarStartDate(String carStartDate)
    {
        this.carStartDate = carStartDate;
    }

    public String getCarEndDate()
    {
        return carEndDate;
    }

    public void setCarEndDate(String carEndDate)
    {
        this.carEndDate = carEndDate;
    }

    private String userId;
    private int parkId;
    private int custId;
    private String feeItemId;
    private int feeMode;
    private String carNo;
    private String remark;
    private String parkNo;
    private String feeItemName;
    private Double theFee;
    private String carStartDate;
    private String carEndDate;
}
