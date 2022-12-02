// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Nb_Application.java

package com.yinhe.ec.cpps.model;

import org.springframework.stereotype.Component;

@Component
public class Nb_Application
{

    public Nb_Application()
    {
        appId = "";
        secret = "";
        appName = "";
        appuseFlag = Integer.valueOf(0);
        selfcertPath = "";
        trustcaPath = "";
        selfcertPwd = "";
        trustcaPwd = "";
        callbackUrl = "";
        createDate = "";
        accessTime = "";
        accessToken = "";
    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public Integer getAppuseFlag()
    {
        return appuseFlag;
    }

    public void setAppuseFlag(Integer appuseFlag)
    {
        this.appuseFlag = appuseFlag;
    }

    public String getSelfcertPath()
    {
        return selfcertPath;
    }

    public void setSelfcertPath(String selfcertPath)
    {
        this.selfcertPath = selfcertPath;
    }

    public String getTrustcaPath()
    {
        return trustcaPath;
    }

    public void setTrustcaPath(String trustcaPath)
    {
        this.trustcaPath = trustcaPath;
    }

    public String getSelfcertPwd()
    {
        return selfcertPwd;
    }

    public void setSelfcertPwd(String selfcertPwd)
    {
        this.selfcertPwd = selfcertPwd;
    }

    public String getTrustcaPwd()
    {
        return trustcaPwd;
    }

    public void setTrustcaPwd(String trustcaPwd)
    {
        this.trustcaPwd = trustcaPwd;
    }

    public String getCallbackUrl()
    {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl)
    {
        this.callbackUrl = callbackUrl;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getAccessTime()
    {
        return accessTime;
    }

    public void setAccessTime(String accessTime)
    {
        this.accessTime = accessTime;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    private String appId;
    private String secret;
    private String appName;
    private Integer appuseFlag;
    private String selfcertPath;
    private String trustcaPath;
    private String selfcertPwd;
    private String trustcaPwd;
    private String callbackUrl;
    private String createDate;
    private String accessTime;
    private String accessToken;
}
