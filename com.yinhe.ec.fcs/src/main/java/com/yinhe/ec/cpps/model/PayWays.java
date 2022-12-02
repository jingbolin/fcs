// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PayWays.java

package com.yinhe.ec.cpps.model;


public class PayWays
{

    public PayWays()
    {
        payKind = 0;
        payKindName = "";
        isUse = 0;
        imgUrl = "";
    }

    public int getPayKind()
    {
        return payKind;
    }

    public void setPayKind(int payKind)
    {
        this.payKind = payKind;
    }

    public String getPayKindName()
    {
        return payKindName;
    }

    public void setPayKindName(String payKindName)
    {
        this.payKindName = payKindName;
    }

    public int getIsUse()
    {
        return isUse;
    }

    public void setIsUse(int isUse)
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

    private int payKind;
    private String payKindName;
    private int isUse;
    private String imgUrl;
}
