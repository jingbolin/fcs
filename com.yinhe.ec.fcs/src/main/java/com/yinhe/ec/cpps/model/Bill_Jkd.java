// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bill_Jkd.java

package com.yinhe.ec.cpps.model;


public class Bill_Jkd
{

    public Bill_Jkd()
    {
        feeItemName = "";
        payFeeXj = Double.valueOf(0.0D);
        payFeeCard = Double.valueOf(0.0D);
        payFeeAlipay = Double.valueOf(0.0D);
        payFeeWechat = Double.valueOf(0.0D);
        payFeePos = Double.valueOf(0.0D);
        payFeeFree = Double.valueOf(0.0D);
        fineRemit = Double.valueOf(0.0D);
        operJkr = "";
        billDate = "";
        remark = "";
    }

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public Double getPayFeeXj()
    {
        return payFeeXj;
    }

    public void setPayFeeXj(Double payFeeXj)
    {
        this.payFeeXj = payFeeXj;
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

    public Double getPayFeePos()
    {
        return payFeePos;
    }

    public void setPayFeePos(Double payFeePos)
    {
        this.payFeePos = payFeePos;
    }

    public Double getPayFeeFree()
    {
        return payFeeFree;
    }

    public void setPayFeeFree(Double payFeeFree)
    {
        this.payFeeFree = payFeeFree;
    }

    public Double getFineRemit()
    {
        return fineRemit;
    }

    public void setFineRemit(Double fineRemit)
    {
        this.fineRemit = fineRemit;
    }

    public String getOperJkr()
    {
        return operJkr;
    }

    public void setOperJkr(String operJkr)
    {
        this.operJkr = operJkr;
    }

    public String getBillDate()
    {
        return billDate;
    }

    public void setBillDate(String billDate)
    {
        this.billDate = billDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String feeItemName;
    private Double payFeeXj;
    private Double payFeeCard;
    private Double payFeeAlipay;
    private Double payFeeWechat;
    private Double payFeePos;
    private Double payFeeFree;
    private Double fineRemit;
    private String operJkr;
    private String billDate;
    private String remark;
}
