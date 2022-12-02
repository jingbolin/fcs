// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserInfoDTO.java

package com.yinhe.ec.cpps.dto;

import com.yinhe.ec.cpps.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDTO extends UserInfo
{

    public UserInfoDTO()
    {
        carStartDate = "";
        carEndDate = "";
        meters = new ArrayList();
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getWyfId()
    {
        return wyfId;
    }

    public void setWyfId(String wyfId)
    {
        this.wyfId = wyfId;
    }

    public String getNqfId()
    {
        return nqfId;
    }

    public void setNqfId(String nqfId)
    {
        this.nqfId = nqfId;
    }

    public String getParkId()
    {
        return parkId;
    }

    public void setParkId(String parkId)
    {
        this.parkId = parkId;
    }

    public String getCarNo()
    {
        return carNo;
    }

    public void setCarNo(String carNo)
    {
        this.carNo = carNo;
    }

    public String getTcfId()
    {
        return tcfId;
    }

    public void setTcfId(String tcfId)
    {
        this.tcfId = tcfId;
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

    public String getFzfId()
    {
        return fzfId;
    }

    public void setFzfId(String fzfId)
    {
        this.fzfId = fzfId;
    }

    public List getMeters()
    {
        return meters;
    }

    public void setMeters(List meters)
    {
        this.meters = meters;
    }

    private String areaName;
    private String wyfId;
    private String nqfId;
    private String parkId;
    private String carNo;
    private String tcfId;
    private String carStartDate;
    private String carEndDate;
    private String fzfId;
    private List meters;
}
