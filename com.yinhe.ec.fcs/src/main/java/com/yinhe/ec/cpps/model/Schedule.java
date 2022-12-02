// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Schedule.java

package com.yinhe.ec.cpps.model;


public class Schedule
{

    public Schedule()
    {
    }

    public int getScheduleId()
    {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public int getScheduleType()
    {
        return scheduleType;
    }

    public void setScheduleType(int scheduleType)
    {
        this.scheduleType = scheduleType;
    }

    public String getOldFeeItemId()
    {
        return oldFeeItemId;
    }

    public void setOldFeeItemId(String oldFeeItemId)
    {
        this.oldFeeItemId = oldFeeItemId;
    }

    public String getNewFeeItemId()
    {
        return newFeeItemId;
    }

    public void setNewFeeItemId(String newFeeItemId)
    {
        this.newFeeItemId = newFeeItemId;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getYearDosageDt()
    {
        return yearDosageDt;
    }

    public void setYearDosageDt(String yearDosageDt)
    {
        this.yearDosageDt = yearDosageDt;
    }

    public Integer getScheduleFlag()
    {
        return scheduleFlag;
    }

    public void setScheduleFlag(Integer scheduleFlag)
    {
        this.scheduleFlag = scheduleFlag;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getOldFeeItemName()
    {
        return oldFeeItemName;
    }

    public void setOldFeeItemName(String oldFeeItemName)
    {
        this.oldFeeItemName = oldFeeItemName;
    }

    public String getNewFeeItemName()
    {
        return newFeeItemName;
    }

    public void setNewFeeItemName(String newFeeItemName)
    {
        this.newFeeItemName = newFeeItemName;
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

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    private int scheduleId;
    private int scheduleType;
    private String oldFeeItemId;
    private String newFeeItemId;
    private String startDate;
    private String yearDosageDt;
    private Integer scheduleFlag;
    private String createDate;
    private String createUser;
    private String oldFeeItemName;
    private String newFeeItemName;
    private String areaId;
    private String areaName;
    private int custId;
}
