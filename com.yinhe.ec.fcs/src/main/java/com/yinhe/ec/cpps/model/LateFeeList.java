// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LateFeeList.java

package com.yinhe.ec.cpps.model;


public class LateFeeList
{

    public LateFeeList()
    {
        lateFeeId = "";
        custId = 0;
        lateFeeName = "";
        lateFeeType = 0;
        lateFeeRatio = Double.valueOf(0.0D);
        onlineLateFeeRatio = Double.valueOf(0.0D);
        createDate = "";
        lateFeeDesc = "";
        easCode = "";
        areaId = "";
        areaName = "";
        lateDays = 0;
    }

    public String getLateFeeId()
    {
        return lateFeeId;
    }

    public void setLateFeeId(String lateFeeId)
    {
        this.lateFeeId = lateFeeId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getLateFeeName()
    {
        return lateFeeName;
    }

    public void setLateFeeName(String lateFeeName)
    {
        this.lateFeeName = lateFeeName;
    }

    public int getLateFeeType()
    {
        return lateFeeType;
    }

    public void setLateFeeType(int lateFeeType)
    {
        this.lateFeeType = lateFeeType;
    }

    public Double getLateFeeRatio()
    {
        return lateFeeRatio;
    }

    public void setLateFeeRatio(Double lateFeeRatio)
    {
        this.lateFeeRatio = lateFeeRatio;
    }

    public Double getOnlineLateFeeRatio()
    {
        return onlineLateFeeRatio;
    }

    public void setOnlineLateFeeRatio(Double onlineLateFeeRatio)
    {
        this.onlineLateFeeRatio = onlineLateFeeRatio;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getLateFeeDesc()
    {
        return lateFeeDesc;
    }

    public void setLateFeeDesc(String lateFeeDesc)
    {
        this.lateFeeDesc = lateFeeDesc;
    }

    public String getEasCode()
    {
        return easCode;
    }

    public void setEasCode(String easCode)
    {
        this.easCode = easCode;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public int getLateDays()
    {
        return lateDays;
    }

    public void setLateDays(int lateDays)
    {
        this.lateDays = lateDays;
    }

    private String lateFeeId;
    private int custId;
    private String lateFeeName;
    private int lateFeeType;
    private Double lateFeeRatio;
    private Double onlineLateFeeRatio;
    private String createDate;
    private String lateFeeDesc;
    private String easCode;
    private String areaId;
    private String areaName;
    private int lateDays;
}
