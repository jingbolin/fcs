// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeType.java

package com.yinhe.ec.cpps.model;


public class FeeType
{

    public FeeType()
    {
    }

    public String getFeeTypeId()
    {
        return feeTypeId;
    }

    public void setFeeTypeId(String feeTypeId)
    {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeCode()
    {
        return feeTypeCode;
    }

    public void setFeeTypeCode(String feeTypeCode)
    {
        this.feeTypeCode = feeTypeCode;
    }

    public String getFeeTypeName()
    {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName)
    {
        this.feeTypeName = feeTypeName;
    }

    public int getFeeTypeMark()
    {
        return feeTypeMark;
    }

    public void setFeeTypeMark(int feeTypeMark)
    {
        this.feeTypeMark = feeTypeMark;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String feeTypeId;
    private String feeTypeCode;
    private String feeTypeName;
    private int feeTypeMark;
    private String remark;
}
