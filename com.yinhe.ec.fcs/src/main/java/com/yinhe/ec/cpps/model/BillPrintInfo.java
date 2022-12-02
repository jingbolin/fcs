// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BillPrintInfo.java

package com.yinhe.ec.cpps.model;


public class BillPrintInfo
{

    public BillPrintInfo()
    {
    }

    public String getBillNo()
    {
        return billNo;
    }

    public void setBillNo(String billNo)
    {
        this.billNo = billNo;
    }

    public int getBillKind()
    {
        return billKind;
    }

    public void setBillKind(int billKind)
    {
        this.billKind = billKind;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getBillOper()
    {
        return billOper;
    }

    public void setBillOper(String billOper)
    {
        this.billOper = billOper;
    }

    public int getBillState()
    {
        return billState;
    }

    public void setBillState(int billState)
    {
        this.billState = billState;
    }

    public String getBillDate()
    {
        return billDate;
    }

    public void setBillDate(String billDate)
    {
        this.billDate = billDate;
    }

    public String getPrintDate()
    {
        return printDate;
    }

    public void setPrintDate(String printDate)
    {
        this.printDate = printDate;
    }

    public Double getBillBalance()
    {
        return billBalance;
    }

    public void setBillBalance(Double billBalance)
    {
        this.billBalance = billBalance;
    }

    public int getBillType()
    {
        return billType;
    }

    public void setBillType(int billType)
    {
        this.billType = billType;
    }

    public Double getBillFee()
    {
        return billFee;
    }

    public void setBillFee(Double billFee)
    {
        this.billFee = billFee;
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

    public int getPayCnt()
    {
        return payCnt;
    }

    public void setPayCnt(int payCnt)
    {
        this.payCnt = payCnt;
    }

    public String getAbateOper()
    {
        return abateOper;
    }

    public void setAbateOper(String abateOper)
    {
        this.abateOper = abateOper;
    }

    public String getAbateDate()
    {
        return abateDate;
    }

    public void setAbateDate(String abateDate)
    {
        this.abateDate = abateDate;
    }

    public int getAbateKind()
    {
        return abateKind;
    }

    public void setAbateKind(int abateKind)
    {
        this.abateKind = abateKind;
    }

    public String getAbateDesc()
    {
        return abateDesc;
    }

    public void setAbateDesc(String abateDesc)
    {
        this.abateDesc = abateDesc;
    }

    public String getSysOper()
    {
        return sysOper;
    }

    public void setSysOper(String sysOper)
    {
        this.sysOper = sysOper;
    }

    public int getCheckState()
    {
        return checkState;
    }

    public void setCheckState(int checkState)
    {
        this.checkState = checkState;
    }

    public int getBillId()
    {
        return billId;
    }

    public void setBillId(int billId)
    {
        this.billId = billId;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String billNo;
    private int billKind;
    private int custId;
    private String billOper;
    private int billState;
    private String billDate;
    private String printDate;
    private Double billBalance;
    private int billType;
    private Double billFee;
    private Double payFee;
    private Double leftBalance;
    private int payCnt;
    private String abateOper;
    private String abateDate;
    private int abateKind;
    private String abateDesc;
    private String sysOper;
    private int checkState;
    private int billId;
    private String remark;
}
