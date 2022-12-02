// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TempParkFee.java

package com.yinhe.ec.cpps.model;


public class TempParkFee
{

    public TempParkFee()
    {
    }

    public String getCarNo()
    {
        return carNo;
    }

    public void setCarNo(String carNo)
    {
        this.carNo = carNo;
    }

    public int getParkId()
    {
        return parkId;
    }

    public void setParkId(int parkId)
    {
        this.parkId = parkId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getInTime()
    {
        return inTime;
    }

    public void setInTime(String inTime)
    {
        this.inTime = inTime;
    }

    public String getOutTime()
    {
        return outTime;
    }

    public void setOutTime(String outTime)
    {
        this.outTime = outTime;
    }

    public int getParkTime()
    {
        return parkTime;
    }

    public void setParkTime(int parkTime)
    {
        this.parkTime = parkTime;
    }

    public int getUseTime()
    {
        return useTime;
    }

    public void setUseTime(int useTime)
    {
        this.useTime = useTime;
    }

    public Double getPayTotal()
    {
        return payTotal;
    }

    public void setPayTotal(Double payTotal)
    {
        this.payTotal = payTotal;
    }

    public Double getFeeTotal()
    {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal)
    {
        this.feeTotal = feeTotal;
    }

    public Double getChangeTotal()
    {
        return changeTotal;
    }

    public void setChangeTotal(Double changeTotal)
    {
        this.changeTotal = changeTotal;
    }

    public Double getPayFee()
    {
        return payFee;
    }

    public void setPayFee(Double payFee)
    {
        this.payFee = payFee;
    }

    public int getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(int operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getPayDate()
    {
        return payDate;
    }

    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String carNo;
    private int parkId;
    private int custId;
    private String inTime;
    private String outTime;
    private int parkTime;
    private int useTime;
    private Double payTotal;
    private Double feeTotal;
    private Double changeTotal;
    private Double payFee;
    private int operatorId;
    private String payDate;
    private String remark;
}
