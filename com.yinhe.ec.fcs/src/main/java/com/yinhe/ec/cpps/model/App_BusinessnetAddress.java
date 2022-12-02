// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_BusinessnetAddress.java

package com.yinhe.ec.cpps.model;


public class App_BusinessnetAddress
{

    public App_BusinessnetAddress()
    {
    }

    public String getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(String businessId)
    {
        this.businessId = businessId;
    }

    public String getBusinessName()
    {
        return businessName;
    }

    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }

    public String getBusinessTime()
    {
        return businessTime;
    }

    public void setBusinessTime(String businessTime)
    {
        this.businessTime = businessTime;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getCustId()
    {
        return custId;
    }

    public void setCustId(Integer custId)
    {
        this.custId = custId;
    }

    private String businessId;
    private String businessName;
    private String businessTime;
    private String address;
    private String tel;
    private Integer sort;
    private Integer custId;
}
