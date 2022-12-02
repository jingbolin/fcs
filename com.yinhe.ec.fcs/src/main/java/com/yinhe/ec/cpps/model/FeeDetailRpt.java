// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeDetailRpt.java

package com.yinhe.ec.cpps.model;


public class FeeDetailRpt
{

    public FeeDetailRpt()
    {
        remark = "";
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

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
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

    public String getTransNo()
    {
        return transNo;
    }

    public void setTransNo(String transNo)
    {
        this.transNo = transNo;
    }

    public Double getPayTotal()
    {
        return payTotal;
    }

    public void setPayTotal(Double payTotal)
    {
        this.payTotal = payTotal;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }

    public Double getFineRemit()
    {
        return fineRemit;
    }

    public void setFineRemit(Double fineRemit)
    {
        this.fineRemit = fineRemit;
    }

    public Double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Double quantity)
    {
        this.quantity = quantity;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Double getLjFee()
    {
        return ljFee;
    }

    public void setLjFee(Double ljFee)
    {
        this.ljFee = ljFee;
    }

    private String userId;
    private String userName;
    private String userAddr;
    private String areaName;
    private String feeItemName;
    private String meterNo;
    private String operatorName;
    private String payKindName;
    private Double payFee;
    private int payCount;
    private String payDate;
    private String transNo;
    private Double payTotal;
    private Double total;
    private Double fineRemit;
    private Double quantity;
    private String remark;
    private Double ljFee;
}
