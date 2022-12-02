// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MboxInfo.java

package com.yinhe.ec.cpps.model;


public class MboxInfo
{

    public MboxInfo()
    {
    }

    public int getMboxId()
    {
        return mboxId;
    }

    public void setMboxId(int mboxId)
    {
        this.mboxId = mboxId;
    }

    public String getMboxNo()
    {
        return mboxNo;
    }

    public void setMboxNo(String mboxNo)
    {
        this.mboxNo = mboxNo;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int mboxId;
    private String mboxNo;
    private int custId;
    private String remark;
}
