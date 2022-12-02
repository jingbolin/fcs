// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_ActivityRecord.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            App_PublishInformation

public class App_ActivityRecord
{

    public App_ActivityRecord()
    {
    }

    public String getInformationId()
    {
        return informationId;
    }

    public void setInformationId(String informationId)
    {
        this.informationId = informationId;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Integer getUserCount()
    {
        return userCount;
    }

    public void setUserCount(Integer userCount)
    {
        this.userCount = userCount;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getJoinDate()
    {
        return joinDate;
    }

    public void setJoinDate(String joinDate)
    {
        this.joinDate = joinDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public App_PublishInformation getPublishinformation()
    {
        return publishinformation;
    }

    public void setPublishinformation(App_PublishInformation publishinformation)
    {
        this.publishinformation = publishinformation;
    }

    private String informationId;
    private String regId;
    private String userName;
    private Integer userCount;
    private String userPhone;
    private String joinDate;
    private String remark;
    private App_PublishInformation publishinformation;
}
