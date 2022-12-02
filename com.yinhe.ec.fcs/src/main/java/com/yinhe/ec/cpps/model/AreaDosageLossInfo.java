// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaDosageLossInfo.java

package com.yinhe.ec.cpps.model;


public class AreaDosageLossInfo
{

    public AreaDosageLossInfo()
    {
    }

    public AreaDosageLossInfo(String areaId, int custId, String areaName, String areaPid, String createDate, String remark)
    {
        this.areaId = areaId;
        this.custId = custId;
        this.areaName = areaName;
        this.areaPid = areaPid;
        this.createDate = createDate;
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

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getAreaPid()
    {
        return areaPid;
    }

    public void setAreaPid(String areaPid)
    {
        this.areaPid = areaPid;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getAreaFullName()
    {
        return areaFullName;
    }

    public void setAreaFullName(String areaFullName)
    {
        this.areaFullName = areaFullName;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
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

    public String toString()
    {
        return (new StringBuilder("AreaInfo [areaId=")).append(areaId).append(", areaName=").append(areaName).append(", areaPid=").append(areaPid).append(", createDate=").append(createDate).append(", custId=").append(custId).append(", remark=").append(remark).append("]").toString();
    }

    private String areaId;
    private int custId;
    private String areaName;
    private String areaPid;
    private String createDate;
    private String remark;
    private String areaFullName;
    private String meterNo;
    private String zDosage;
    private String hDosage;
    private String lossDosage;
    private String lossPercent;
}
