// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_Coupon.java

package com.yinhe.ec.cpps.model;


public class App_Coupon
{

    public App_Coupon()
    {
    }

    public String getCouponId()
    {
        return couponId;
    }

    public void setCouponId(String couponId)
    {
        this.couponId = couponId;
    }

    public String getCouponTypeId()
    {
        return couponTypeId;
    }

    public void setCouponTypeId(String couponTypeId)
    {
        this.couponTypeId = couponTypeId;
    }

    public String getCouponName()
    {
        return couponName;
    }

    public void setCouponName(String couponName)
    {
        this.couponName = couponName;
    }

    public String getUseStartDate()
    {
        return useStartDate;
    }

    public void setUseStartDate(String useStartDate)
    {
        this.useStartDate = useStartDate;
    }

    public String getUseEndDate()
    {
        return useEndDate;
    }

    public void setUseEndDate(String useEndDate)
    {
        this.useEndDate = useEndDate;
    }

    public Double getCouponMoney()
    {
        return couponMoney;
    }

    public void setCouponMoney(Double couponMoney)
    {
        this.couponMoney = couponMoney;
    }

    public Integer getIsAccumulateUse()
    {
        return isAccumulateUse;
    }

    public void setIsAccumulateUse(Integer isAccumulateUse)
    {
        this.isAccumulateUse = isAccumulateUse;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public Double getLimitationMoney()
    {
        return limitationMoney;
    }

    public void setLimitationMoney(Double limitationMoney)
    {
        this.limitationMoney = limitationMoney;
    }

    public String getCouponTypeName()
    {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName)
    {
        this.couponTypeName = couponTypeName;
    }

    private String couponId;
    private String couponTypeId;
    private String couponName;
    private String useStartDate;
    private String useEndDate;
    private Double couponMoney;
    private Integer isAccumulateUse;
    private String imgUrl;
    private String createDate;
    private Integer state;
    private Double limitationMoney;
    private String couponTypeName;
}
