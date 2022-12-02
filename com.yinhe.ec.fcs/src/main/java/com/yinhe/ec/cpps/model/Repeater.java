// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Repeater.java

package com.yinhe.ec.cpps.model;


public class Repeater
{

    public Repeater()
    {
    }

    public String getRepNo()
    {
        return repNo;
    }

    public void setRepNo(String repNo)
    {
        this.repNo = repNo;
    }

    public String getMusterNo()
    {
        return musterNo;
    }

    public void setMusterNo(String musterNo)
    {
        this.musterNo = musterNo;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public int getManuId()
    {
        return manuId;
    }

    public void setManuId(int manuId)
    {
        this.manuId = manuId;
    }

    public String getRepAddr()
    {
        return repAddr;
    }

    public void setRepAddr(String repAddr)
    {
        this.repAddr = repAddr;
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

    private String repNo;
    private String musterNo;
    private int custId;
    private int manuId;
    private String repAddr;
    private String createDate;
    private String remark;
}
