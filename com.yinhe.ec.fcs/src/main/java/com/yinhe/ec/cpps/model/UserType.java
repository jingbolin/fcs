// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserType.java

package com.yinhe.ec.cpps.model;


public class UserType
{

    public UserType()
    {
    }

    public int getUserTypeId()
    {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId)
    {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName()
    {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName)
    {
        this.userTypeName = userTypeName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int userTypeId;
    private String userTypeName;
    private String remark;
}
