// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadUser.java

package com.yinhe.ec.cpps.model;


public class ReadUser
{

    public ReadUser()
    {
    }

    public String getReadUserId()
    {
        return readUserId;
    }

    public void setReadUserId(String readUserId)
    {
        this.readUserId = readUserId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getReadUserName()
    {
        return readUserName;
    }

    public void setReadUserName(String readUserName)
    {
        this.readUserName = readUserName;
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

    private String readUserId;
    private int custId;
    private String readUserName;
    private String createDate;
    private String remark;
}
