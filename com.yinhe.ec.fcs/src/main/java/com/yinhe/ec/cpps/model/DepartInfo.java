// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DepartInfo.java

package com.yinhe.ec.cpps.model;


public class DepartInfo
{

    public DepartInfo()
    {
        remark = "";
    }

    public DepartInfo(String departId, int custId, String departName, String departPid, String createDate, String remark)
    {
        this.remark = "";
        this.departId = departId;
        this.custId = custId;
        this.departName = departName;
        this.departPid = departPid;
        this.createDate = createDate;
        this.remark = remark;
    }

    public String getDepartId()
    {
        return departId;
    }

    public void setDepartId(String departId)
    {
        this.departId = departId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getDepartName()
    {
        return departName;
    }

    public void setDepartName(String departName)
    {
        this.departName = departName;
    }

    public String getDepartPid()
    {
        return departPid;
    }

    public void setDepartPid(String departPid)
    {
        this.departPid = departPid;
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

    private String departId;
    private int custId;
    private String departName;
    private String departPid;
    private String createDate;
    private String remark;
}
