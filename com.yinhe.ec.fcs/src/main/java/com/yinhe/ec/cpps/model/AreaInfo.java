// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaInfo.java

package com.yinhe.ec.cpps.model;


public class AreaInfo
{

    private String areaId;
    private Integer custId;
    private String areaName;
    private String areaPid;
    private String createDate;
    private String remark;
    private String areaFullName;
    private Integer businessFlag;
    private String meterNo;

    public AreaInfo()
    {
        businessFlag = 0;
    }

    public AreaInfo(String areaId, int custId, String areaName, String areaPid, String createDate, String remark)
    {
        businessFlag = 0;
        this.areaId = areaId;
        this.custId = custId;
        this.areaName = areaName;
        this.areaPid = areaPid;
        this.createDate = createDate;
        this.remark = remark;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getBusinessFlag() {
        return businessFlag;
    }

    public void setBusinessFlag(Integer businessFlag) {
        this.businessFlag = businessFlag;
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

    public String toString()
    {
        return (new StringBuilder("AreaInfo [areaId=")).append(areaId).append(", custId=").append(custId).append(", areaName=").append(areaName).append(", areaPid=").append(areaPid).append(", createDate=").append(createDate).append(", remark=").append(remark).append(", areaFullName=").append(areaFullName).append(", businessFlag=").append(businessFlag).append(", meterNo=").append(meterNo).append("]").toString();
    }


}
