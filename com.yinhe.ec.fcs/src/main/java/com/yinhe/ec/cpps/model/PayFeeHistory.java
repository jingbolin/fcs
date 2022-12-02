// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PayFeeHistory.java

package com.yinhe.ec.cpps.model;


public class PayFeeHistory
{

    public PayFeeHistory()
    {
    }

    public String getPayNo()
    {
        return payNo;
    }

    public void setPayNo(String payNo)
    {
        this.payNo = payNo;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
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

    public int getPayKind()
    {
        return payKind;
    }

    public void setPayKind(int payKind)
    {
        this.payKind = payKind;
    }

    public String getTransDesc()
    {
        return transDesc;
    }

    public void setTransDesc(String transDesc)
    {
        this.transDesc = transDesc;
    }

    public String getTransNo()
    {
        return transNo;
    }

    public void setTransNo(String transNo)
    {
        this.transNo = transNo;
    }

    public Double getLastBalance()
    {
        return lastBalance;
    }

    public void setLastBalance(Double lastBalance)
    {
        this.lastBalance = lastBalance;
    }

    public Double getFeeTotal()
    {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal)
    {
        this.feeTotal = feeTotal;
    }

    public Double getFineTotal()
    {
        return fineTotal;
    }

    public void setFineTotal(Double fineTotal)
    {
        this.fineTotal = fineTotal;
    }

    public Double getFineRemit()
    {
        return fineRemit;
    }

    public void setFineRemit(Double fineRemit)
    {
        this.fineRemit = fineRemit;
    }

    public Double getPayTotal()
    {
        return payTotal;
    }

    public void setPayTotal(Double payTotal)
    {
        this.payTotal = payTotal;
    }

    public Double getPayFee()
    {
        return payFee;
    }

    public void setPayFee(Double payFee)
    {
        this.payFee = payFee;
    }

    public Double getLeftBalance()
    {
        return leftBalance;
    }

    public void setLeftBalance(Double leftBalance)
    {
        this.leftBalance = leftBalance;
    }

    public int getPayState()
    {
        return payState;
    }

    public void setPayState(int payState)
    {
        this.payState = payState;
    }

    public int getPrintState()
    {
        return printState;
    }

    public void setPrintState(int printState)
    {
        this.printState = printState;
    }

    public int getBillKind()
    {
        return billKind;
    }

    public void setBillKind(int billKind)
    {
        this.billKind = billKind;
    }

    public String getBillNo()
    {
        return billNo;
    }

    public void setBillNo(String billNo)
    {
        this.billNo = billNo;
    }

    public int getBillKind1()
    {
        return billKind1;
    }

    public void setBillKind1(int billKind1)
    {
        this.billKind1 = billKind1;
    }

    public String getBillNo1()
    {
        return billNo1;
    }

    public void setBillNo1(String billNo1)
    {
        this.billNo1 = billNo1;
    }

    public int getBillKind2()
    {
        return billKind2;
    }

    public void setBillKind2(int billKind2)
    {
        this.billKind2 = billKind2;
    }

    public String getBillNo2()
    {
        return billNo2;
    }

    public void setBillNo2(String billNo2)
    {
        this.billNo2 = billNo2;
    }

    public String getUnOperId()
    {
        return unOperId;
    }

    public void setUnOperId(String unOperId)
    {
        this.unOperId = unOperId;
    }

    public String getUnPayDate()
    {
        return unPayDate;
    }

    public void setUnPayDate(String unPayDate)
    {
        this.unPayDate = unPayDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String payNo;
    private String userId;
    private int custId;
    private int operatorId;
    private String payDate;
    private int payKind;
    private String transDesc;
    private String transNo;
    private Double lastBalance;
    private Double feeTotal;
    private Double fineTotal;
    private Double fineRemit;
    private Double payTotal;
    private Double payFee;
    private Double leftBalance;
    private int payState;
    private int printState;
    private int billKind;
    private String billNo;
    private int billKind1;
    private String billNo1;
    private int billKind2;
    private String billNo2;
    private String unOperId;
    private String unPayDate;
    private String remark;
}
