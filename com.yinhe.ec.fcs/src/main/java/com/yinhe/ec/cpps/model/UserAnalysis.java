// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserAnalysis.java

package com.yinhe.ec.cpps.model;


public class UserAnalysis
{

    public UserAnalysis()
    {
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public int getUserCount()
    {
        return userCount;
    }

    public void setUserCount(int userCount)
    {
        this.userCount = userCount;
    }

    public int getUseFeeCount()
    {
        return useFeeCount;
    }

    public void setUseFeeCount(int useFeeCount)
    {
        this.useFeeCount = useFeeCount;
    }

    public int getUnUseFeeCount()
    {
        return unUseFeeCount;
    }

    public void setUnUseFeeCount(int unUseFeeCount)
    {
        this.unUseFeeCount = unUseFeeCount;
    }

    public int getWxCount()
    {
        return wxCount;
    }

    public void setWxCount(int wxCount)
    {
        this.wxCount = wxCount;
    }

    private String areaName;
    private int userCount;
    private int useFeeCount;
    private int unUseFeeCount;
    private int wxCount;
}
