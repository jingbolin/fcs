// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_WechatParameter.java

package com.yinhe.ec.cpps.model;


public class App_WechatParameter
{

    public App_WechatParameter()
    {
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getAeskey()
    {
        return aeskey;
    }

    public void setAeskey(String aeskey)
    {
        this.aeskey = aeskey;
    }

    public String getAppSecret()
    {
        return appSecret;
    }

    public void setAppSecret(String appSecret)
    {
        this.appSecret = appSecret;
    }

    public String getMchId()
    {
        return mchId;
    }

    public void setMchId(String mchId)
    {
        this.mchId = mchId;
    }

    public String getMchKey()
    {
        return mchKey;
    }

    public void setMchKey(String mchKey)
    {
        this.mchKey = mchKey;
    }

    public String getSslcertPath()
    {
        return sslcertPath;
    }

    public void setSslcertPath(String sslcertPath)
    {
        this.sslcertPath = sslcertPath;
    }

    public String getSslkeyPath()
    {
        return sslkeyPath;
    }

    public void setSslkeyPath(String sslkeyPath)
    {
        this.sslkeyPath = sslkeyPath;
    }

    public String getProgramId()
    {
        return programId;
    }

    public void setProgramId(String programId)
    {
        this.programId = programId;
    }

    public String getProgramSecret()
    {
        return programSecret;
    }

    public void setProgramSecret(String programSecret)
    {
        this.programSecret = programSecret;
    }

    public String getNotifyUrl()
    {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.notifyUrl = notifyUrl;
    }

    public String getRefundNotifyUrl()
    {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl)
    {
        this.refundNotifyUrl = refundNotifyUrl;
    }

    private int custId;
    private String appId;
    private String token;
    private String aeskey;
    private String appSecret;
    private String mchId;
    private String mchKey;
    private String sslcertPath;
    private String sslkeyPath;
    private String programId;
    private String programSecret;
    private String notifyUrl;
    private String refundNotifyUrl;
}
