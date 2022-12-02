// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaList.java

package com.yinhe.ec.cpps.model;


public class AreaList
{

    public AreaList()
    {
    }

    public AreaList(String areaListId, int custId, String areaListName, int readCycle, String readUserId, String remark)
    {
        this.areaListId = areaListId;
        this.custId = custId;
        this.areaListName = areaListName;
        this.readCycle = readCycle;
        this.readUserId = readUserId;
        this.remark = remark;
    }

    public String getAreaListId()
    {
        return areaListId;
    }

    public void setAreaListId(String areaListId)
    {
        this.areaListId = areaListId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getAreaListName()
    {
        return areaListName;
    }

    public void setAreaListName(String areaListName)
    {
        this.areaListName = areaListName;
    }

    public int getReadCycle()
    {
        return readCycle;
    }

    public void setReadCycle(int readCycle)
    {
        this.readCycle = readCycle;
    }

    public String getReadUserId()
    {
        return readUserId;
    }

    public void setReadUserId(String readUserId)
    {
        this.readUserId = readUserId;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String areaListId;
    private int custId;
    private String areaListName;
    private int readCycle;
    private String readUserId;
    private String remark;
}
