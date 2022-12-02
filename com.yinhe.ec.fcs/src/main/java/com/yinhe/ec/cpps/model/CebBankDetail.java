// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CebBankDetail.java

package com.yinhe.ec.cpps.model;


public class CebBankDetail
{

    public CebBankDetail()
    {
        billno = "";
        yearmonth = "";
        itemname = "";
        state = "";
        fee = "";
        latefeerate = 0;
        latefee = "";
        actualfee = "";
        province = "";
        city = "";
        region = "";
        cname = "";
        unitname = "";
        roomno = "";
        accountname = "";
        uniqueid = "";
        Reserve1 = "";
        Reserve2 = "";
        Reserve3 = "";
        Reserve4 = "";
    }

    public String getBillno()
    {
        return billno;
    }

    public void setBillno(String billno)
    {
        this.billno = billno;
    }

    public String getYearmonth()
    {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth)
    {
        this.yearmonth = yearmonth;
    }

    public String getItemname()
    {
        return itemname;
    }

    public void setItemname(String itemname)
    {
        this.itemname = itemname;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getFee()
    {
        return fee;
    }

    public void setFee(String fee)
    {
        this.fee = fee;
    }

    public int getLatefeerate()
    {
        return latefeerate;
    }

    public void setLatefeerate(int latefeerate)
    {
        this.latefeerate = latefeerate;
    }

    public String getLatefee()
    {
        return latefee;
    }

    public void setLatefee(String latefee)
    {
        this.latefee = latefee;
    }

    public String getActualfee()
    {
        return actualfee;
    }

    public void setActualfee(String actualfee)
    {
        this.actualfee = actualfee;
    }

    public int getCategory()
    {
        return category;
    }

    public void setCategory(int category)
    {
        this.category = category;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getUnitname()
    {
        return unitname;
    }

    public void setUnitname(String unitname)
    {
        this.unitname = unitname;
    }

    public String getRoomno()
    {
        return roomno;
    }

    public void setRoomno(String roomno)
    {
        this.roomno = roomno;
    }

    public String getAccountname()
    {
        return accountname;
    }

    public void setAccountname(String accountname)
    {
        this.accountname = accountname;
    }

    public String getUniqueid()
    {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid)
    {
        this.uniqueid = uniqueid;
    }

    public String getReserve1()
    {
        return Reserve1;
    }

    public void setReserve1(String reserve1)
    {
        Reserve1 = reserve1;
    }

    public String getReserve2()
    {
        return Reserve2;
    }

    public void setReserve2(String reserve2)
    {
        Reserve2 = reserve2;
    }

    public String getReserve3()
    {
        return Reserve3;
    }

    public void setReserve3(String reserve3)
    {
        Reserve3 = reserve3;
    }

    public String getReserve4()
    {
        return Reserve4;
    }

    public void setReserve4(String reserve4)
    {
        Reserve4 = reserve4;
    }

    private String billno;
    private String yearmonth;
    private String itemname;
    private String state;
    private String fee;
    private int latefeerate;
    private String latefee;
    private String actualfee;
    private int category;
    private String province;
    private String city;
    private String region;
    private String cname;
    private String unitname;
    private String roomno;
    private String accountname;
    private String uniqueid;
    private String Reserve1;
    private String Reserve2;
    private String Reserve3;
    private String Reserve4;
}
