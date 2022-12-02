// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_PayWays.java

package com.yinhe.ec.cpps.model;


public class App_PayWays
{

    public App_PayWays()
    {
    }

    public String getPayWayId()
    {
        return payWayId;
    }

    public void setPayWayId(String payWayId)
    {
        this.payWayId = payWayId;
    }

    public String getPayWayName()
    {
        return payWayName;
    }

    public void setPayWayName(String payWayName)
    {
        this.payWayName = payWayName;
    }

    public Integer getIsUse()
    {
        return isUse;
    }

    public void setIsUse(Integer isUse)
    {
        this.isUse = isUse;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getEnglishName()
    {
        return englishName;
    }

    public void setEnglishName(String englishName)
    {
        this.englishName = englishName;
    }

    private String payWayId;
    private String payWayName;
    private Integer isUse;
    private String imgUrl;
    private String englishName;
}
