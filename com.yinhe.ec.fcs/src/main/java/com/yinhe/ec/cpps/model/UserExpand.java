// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserExpand.java

package com.yinhe.ec.cpps.model;


public class UserExpand
{

    public UserExpand()
    {
    }

    public UserExpand(String loginId, String loginName, String loginPsw, String departId, String userId, String trueName, String email)
    {
        this.loginId = loginId;
        this.loginName = loginName;
        this.loginPsw = loginPsw;
        this.departId = departId;
        this.userId = userId;
        this.trueName = trueName;
        this.email = email;
    }

    public UserExpand(String loginId, String loginName, String loginPsw, String departId, String userId, String trueName, String email, 
            String regtime, Integer loginCount, String lastLoginDate, String lastReadDay)
    {
        this.loginId = loginId;
        this.loginName = loginName;
        this.loginPsw = loginPsw;
        this.departId = departId;
        this.userId = userId;
        this.trueName = trueName;
        this.email = email;
        this.regtime = regtime;
        this.loginCount = loginCount;
        this.lastLoginDate = lastLoginDate;
        this.lastReadDay = lastReadDay;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginPsw()
    {
        return loginPsw;
    }

    public void setLoginPsw(String loginPsw)
    {
        this.loginPsw = loginPsw;
    }

    public String getDepartId()
    {
        return departId;
    }

    public void setDepartId(String departId)
    {
        this.departId = departId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getTrueName()
    {
        return trueName;
    }

    public void setTrueName(String trueName)
    {
        this.trueName = trueName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRegtime()
    {
        return regtime;
    }

    public void setRegtime(String regtime)
    {
        this.regtime = regtime;
    }

    public Integer getLoginCount()
    {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount)
    {
        this.loginCount = loginCount;
    }

    public String getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastReadDay()
    {
        return lastReadDay;
    }

    public void setLastReadDay(String lastReadDay)
    {
        this.lastReadDay = lastReadDay;
    }

    private int custId;
    private String loginId;
    private String loginName;
    private String loginPsw;
    private String departId;
    private String userId;
    private String trueName;
    private String email;
    private String regtime;
    private Integer loginCount;
    private String lastLoginDate;
    private String lastReadDay;
}
