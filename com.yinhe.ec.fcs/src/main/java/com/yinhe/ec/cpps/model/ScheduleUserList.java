// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScheduleUserList.java

package com.yinhe.ec.cpps.model;


public class ScheduleUserList
{

    public ScheduleUserList()
    {
    }

    public ScheduleUserList(String scheduleId, String userId, String userName, String userAddr)
    {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.userName = userName;
        this.userAddr = userAddr;
    }

    public String getScheduleId()
    {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserAddr()
    {
        return userAddr;
    }

    public void setUserAddr(String userAddr)
    {
        this.userAddr = userAddr;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    private String scheduleId;
    private String userId;
    private String userName;
    private String userAddr;
    private int custId;
}
