// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BillType.java

package com.yinhe.ec.cpps.model;


public class BillType
{

    public BillType()
    {
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

    public int getBillTypeStyle()
    {
        return billTypeStyle;
    }

    public void setBillTypeStyle(int billTypeStyle)
    {
        this.billTypeStyle = billTypeStyle;
    }

    public String getBillTypeName()
    {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName)
    {
        this.billTypeName = billTypeName;
    }

    public String getBillTypeUrl()
    {
        return billTypeUrl;
    }

    public void setBillTypeUrl(String billTypeUrl)
    {
        this.billTypeUrl = billTypeUrl;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int billKind;
    private int custId;
    private int billTypeStyle;
    private String billTypeName;
    private String billTypeUrl;
    private String remark;
}
