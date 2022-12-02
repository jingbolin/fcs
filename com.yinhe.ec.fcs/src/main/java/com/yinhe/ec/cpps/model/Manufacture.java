// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Manufacture.java

package com.yinhe.ec.cpps.model;


public class Manufacture
{

    private Integer manuId;
    private String manuName;
    private String manuUser;
    private String manuPhone;
    private String remark;
    private Integer custId;

    public Manufacture()
    {
    }

    public Integer getManuId() {
        return manuId;
    }

    public void setManuId(Integer manuId) {
        this.manuId = manuId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getManuName()
    {
        return manuName;
    }

    public void setManuName(String manuName)
    {
        this.manuName = manuName;
    }

    public String getManuUser()
    {
        return manuUser;
    }

    public void setManuUser(String manuUser)
    {
        this.manuUser = manuUser;
    }

    public String getManuPhone()
    {
        return manuPhone;
    }

    public void setManuPhone(String manuPhone)
    {
        this.manuPhone = manuPhone;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }


}
