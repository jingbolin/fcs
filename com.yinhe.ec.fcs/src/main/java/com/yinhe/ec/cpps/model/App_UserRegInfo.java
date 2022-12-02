// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_UserRegInfo.java

package com.yinhe.ec.cpps.model;

import java.util.ArrayList;
import java.util.List;

public class App_UserRegInfo
{

    public App_UserRegInfo()
    {
        userIds = new ArrayList();
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getRegAccount()
    {
        return regAccount;
    }

    public void setRegAccount(String regAccount)
    {
        this.regAccount = regAccount;
    }

    public String getRegNickName()
    {
        return regNickName;
    }

    public void setRegNickName(String regNickName)
    {
        this.regNickName = regNickName;
    }

    public String getRegPwd()
    {
        return regPwd;
    }

    public void setRegPwd(String regPwd)
    {
        this.regPwd = regPwd;
    }

    public String getRegPhoto()
    {
        return regPhoto;
    }

    public void setRegPhoto(String regPhoto)
    {
        this.regPhoto = regPhoto;
    }

    public String getRegDate()
    {
        return regDate;
    }

    public void setRegDate(String regDate)
    {
        this.regDate = regDate;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getMobileCodeAddr()
    {
        return mobileCodeAddr;
    }

    public void setMobileCodeAddr(String mobileCodeAddr)
    {
        this.mobileCodeAddr = mobileCodeAddr;
    }

    public String getOperatingSystems()
    {
        return operatingSystems;
    }

    public void setOperatingSystems(String operatingSystems)
    {
        this.operatingSystems = operatingSystems;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public String getDevice()
    {
        return device;
    }

    public void setDevice(String device)
    {
        this.device = device;
    }

    public int getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(int loginTime)
    {
        this.loginTime = loginTime;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public List getUserIds()
    {
        return userIds;
    }

    public void setUserIds(List userIds)
    {
        this.userIds = userIds;
    }

    private String regId;
    private String regAccount;
    private String regNickName;
    private String regPwd;
    private String regPhoto;
    private String regDate;
    private String area;
    private String address;
    private String mobileCodeAddr;
    private String operatingSystems;
    private String vendor;
    private String device;
    private int loginTime;
    private int custId;
    private List userIds;
}
