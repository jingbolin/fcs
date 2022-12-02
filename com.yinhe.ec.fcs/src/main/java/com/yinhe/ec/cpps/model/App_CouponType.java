// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_CouponType.java

package com.yinhe.ec.cpps.model;


public class App_CouponType
{

    public App_CouponType()
    {
    }

    public String getCouponTypeId()
    {
        return couponTypeId;
    }

    public void setCouponTypeId(String couponTypeId)
    {
        this.couponTypeId = couponTypeId;
    }

    public String getCouponTypeName()
    {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName)
    {
        this.couponTypeName = couponTypeName;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    private String couponTypeId;
    private String couponTypeName;
    private String createDate;
}
