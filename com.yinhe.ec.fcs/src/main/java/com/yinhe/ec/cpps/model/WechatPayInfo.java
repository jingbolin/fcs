// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WechatPayInfo.java

package com.yinhe.ec.cpps.model;


public class WechatPayInfo
{

    public WechatPayInfo()
    {
        usePayFlag = 0;
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

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public String getUseageId()
    {
        return useageId;
    }

    public void setUseageId(String useageId)
    {
        this.useageId = useageId;
    }

    public String getUseageDesc()
    {
        return useageDesc;
    }

    public void setUseageDesc(String useageDesc)
    {
        this.useageDesc = useageDesc;
    }

    public String getFeeItem()
    {
        return feeItem;
    }

    public void setFeeItem(String feeItem)
    {
        this.feeItem = feeItem;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public int getUsePayFlag()
    {
        return usePayFlag;
    }

    public void setUsePayFlag(int usePayFlag)
    {
        this.usePayFlag = usePayFlag;
    }

    private String userId;
    private String userName;
    private String userAddr;
    private String meterNo;
    private String useageId;
    private String useageDesc;
    private String feeItem;
    private String itemDesc;
    private int usePayFlag;
}
