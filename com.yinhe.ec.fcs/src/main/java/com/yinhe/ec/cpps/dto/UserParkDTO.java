// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserParkDTO.java

package com.yinhe.ec.cpps.dto;

import com.yinhe.ec.cpps.model.UserInfo;

public class UserParkDTO extends UserInfo
{

    public UserParkDTO()
    {
        carNo = "";
        feeItemId = "";
        carStartDate = "";
        carEndDate = "";
    }

    public String getCarNo()
    {
        return carNo;
    }

    public void setCarNo(String carNo)
    {
        this.carNo = carNo;
    }

    public String getFeeItemId()
    {
        return feeItemId;
    }

    public void setFeeItemId(String feeItemId)
    {
        this.feeItemId = feeItemId;
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

    public String carNo;
    public String feeItemId;
    private String carStartDate;
    private String carEndDate;
}
