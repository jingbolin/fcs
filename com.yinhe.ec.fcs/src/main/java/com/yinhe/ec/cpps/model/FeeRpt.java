// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeRpt.java

package com.yinhe.ec.cpps.model;


public class FeeRpt
{

    public FeeRpt()
    {
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

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    public String getPayKindName()
    {
        return payKindName;
    }

    public void setPayKindName(String payKindName)
    {
        this.payKindName = payKindName;
    }

    public Double getPayFee()
    {
        return payFee;
    }

    public void setPayFee(Double payFee)
    {
        this.payFee = payFee;
    }

    public int getPayCount()
    {
        return payCount;
    }

    public void setPayCount(int payCount)
    {
        this.payCount = payCount;
    }

    public String getPayDate()
    {
        return payDate;
    }

    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
    }

    public Double getPayTotal()
    {
        return payTotal;
    }

    public void setPayTotal(Double payTotal)
    {
        this.payTotal = payTotal;
    }

    public Double getFineRemit()
    {
        return fineRemit;
    }

    public void setFineRemit(Double fineRemit)
    {
        this.fineRemit = fineRemit;
    }

    public String getPayKindDesc()
    {
        return payKindDesc;
    }

    public void setPayKindDesc(String payKindDesc)
    {
        this.payKindDesc = payKindDesc;
    }

    public Double getPayFeeXj()
    {
        return payFeeXj;
    }

    public void setPayFeeXj(Double payFeeXj)
    {
        this.payFeeXj = payFeeXj;
    }

    public Double getPayFeePos()
    {
        return payFeePos;
    }

    public void setPayFeePos(Double payFeePos)
    {
        this.payFeePos = payFeePos;
    }

    public Double getPayFeeCard()
    {
        return payFeeCard;
    }

    public void setPayFeeCard(Double payFeeCard)
    {
        this.payFeeCard = payFeeCard;
    }

    public Double getPayFeeAlipay()
    {
        return payFeeAlipay;
    }

    public void setPayFeeAlipay(Double payFeeAlipay)
    {
        this.payFeeAlipay = payFeeAlipay;
    }

    public Double getPayFeeWechat()
    {
        return payFeeWechat;
    }

    public void setPayFeeWechat(Double payFeeWechat)
    {
        this.payFeeWechat = payFeeWechat;
    }

    public Double getPayFeeFree()
    {
        return payFeeFree;
    }

    public void setPayFeeFree(Double payFeeFree)
    {
        this.payFeeFree = payFeeFree;
    }

    private String userId;
    private String userName;
    private String userAddr;
    private String feeItemName;
    private String areaName;
    private String operatorName;
    private String payKindName;
    private Double payFee;
    private int payCount;
    private String payDate;
    private Double payTotal;
    private Double fineRemit;
    private String payKindDesc;
    private Double payFeeXj;
    private Double payFeeCard;
    private Double payFeeAlipay;
    private Double payFeeWechat;
    private Double payFeePos;
    private Double payFeeFree;
}
