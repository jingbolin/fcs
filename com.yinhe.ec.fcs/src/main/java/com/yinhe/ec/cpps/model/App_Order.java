// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_Order.java

package com.yinhe.ec.cpps.model;


public class App_Order
{

    public App_Order()
    {
    }

    public String getPayFeeId()
    {
        return payFeeId;
    }

    public void setPayFeeId(String payFeeId)
    {
        this.payFeeId = payFeeId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getPayKind()
    {
        return payKind;
    }

    public void setPayKind(int payKind)
    {
        this.payKind = payKind;
    }

    public Double getTotalAmt()
    {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt)
    {
        this.totalAmt = totalAmt;
    }

    public Double getTotalRcvblAmt()
    {
        return totalRcvblAmt;
    }

    public void setTotalRcvblAmt(Double totalRcvblAmt)
    {
        this.totalRcvblAmt = totalRcvblAmt;
    }

    public Double getCouponAmt()
    {
        return couponAmt;
    }

    public void setCouponAmt(Double couponAmt)
    {
        this.couponAmt = couponAmt;
    }

    public Double getPointAmt()
    {
        return pointAmt;
    }

    public void setPointAmt(Double pointAmt)
    {
        this.pointAmt = pointAmt;
    }

    public Double getLateFeeAmt()
    {
        return lateFeeAmt;
    }

    public void setLateFeeAmt(Double lateFeeAmt)
    {
        this.lateFeeAmt = lateFeeAmt;
    }

    public String getPayDate()
    {
        return payDate;
    }

    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
    }

    public int getPayState()
    {
        return payState;
    }

    public void setPayState(int payState)
    {
        this.payState = payState;
    }

    public String getSysUserId()
    {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId)
    {
        this.sysUserId = sysUserId;
    }

    public int getUsePoint()
    {
        return usePoint;
    }

    public void setUsePoint(int usePoint)
    {
        this.usePoint = usePoint;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getPayKindName()
    {
        return payKindName;
    }

    public void setPayKindName(String payKindName)
    {
        this.payKindName = payKindName;
    }

    public String getRegAccount()
    {
        return regAccount;
    }

    public void setRegAccount(String regAccount)
    {
        this.regAccount = regAccount;
    }

    public String getBillerName()
    {
        return billerName;
    }

    public void setBillerName(String billerName)
    {
        this.billerName = billerName;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    private String payFeeId;
    private String orderId;
    private String orderDate;
    private int payKind;
    private Double totalAmt;
    private Double totalRcvblAmt;
    private Double couponAmt;
    private Double pointAmt;
    private Double lateFeeAmt;
    private String payDate;
    private int payState;
    private String sysUserId;
    private int usePoint;
    private int custId;
    private String billerName;
    private String payKindName;
    private String regAccount;
    private String regId;
}
