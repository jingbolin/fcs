// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BillInfo.java

package com.yinhe.ec.cpps.model;


public class BillInfo
{

    public BillInfo()
    {
    }

    public int getBillId()
    {
        return billId;
    }

    public void setBillId(int billId)
    {
        this.billId = billId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public int getBillKind()
    {
        return billKind;
    }

    public void setBillKind(int billKind)
    {
        this.billKind = billKind;
    }

    public String getBillNo1()
    {
        return billNo1;
    }

    public void setBillNo1(String billNo1)
    {
        this.billNo1 = billNo1;
    }

    public String getBillNo2()
    {
        return billNo2;
    }

    public void setBillNo2(String billNo2)
    {
        this.billNo2 = billNo2;
    }

    public int getBillCnt()
    {
        return billCnt;
    }

    public void setBillCnt(int billCnt)
    {
        this.billCnt = billCnt;
    }

    public String getBillOper()
    {
        return billOper;
    }

    public void setBillOper(String billOper)
    {
        this.billOper = billOper;
    }

    public String getBillDate()
    {
        return billDate;
    }

    public void setBillDate(String billDate)
    {
        this.billDate = billDate;
    }

    public String getSysOper()
    {
        return sysOper;
    }

    public void setSysOper(String sysOper)
    {
        this.sysOper = sysOper;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int billId;
    private int custId;
    private int billKind;
    private String billNo1;
    private String billNo2;
    private int billCnt;
    private String billOper;
    private String billDate;
    private String sysOper;
    private String remark;
}
