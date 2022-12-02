// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MusterChange.java

package com.yinhe.ec.cpps.model;


public class MusterChange
{

    public MusterChange()
    {
    }

    public String getMusterNo()
    {
        return musterNo;
    }

    public void setMusterNo(String musterNo)
    {
        this.musterNo = musterNo;
    }

    public String getChangeDate()
    {
        return changeDate;
    }

    public void setChangeDate(String changeDate)
    {
        this.changeDate = changeDate;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getChangeUser()
    {
        return changeUser;
    }

    public void setChangeUser(String changeUser)
    {
        this.changeUser = changeUser;
    }

    public int getChangeFlag()
    {
        return changeFlag;
    }

    public void setChangeFlag(int changeFlag)
    {
        this.changeFlag = changeFlag;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String musterNo;
    private String changeDate;
    private int custId;
    private String changeUser;
    private int changeFlag;
    private String reason;
    private String remark;
}
