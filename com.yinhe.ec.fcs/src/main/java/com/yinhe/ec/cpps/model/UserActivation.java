// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserActivation.java

package com.yinhe.ec.cpps.model;


public class UserActivation
{

    public UserActivation()
    {
    }

    public String getActivationKey()
    {
        return activationKey;
    }

    public void setActivationKey(String activationKey)
    {
        this.activationKey = activationKey;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    private String activationKey;
    private String loginId;
    private String email;
    private String createTime;
}
