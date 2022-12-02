// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbSendData.java

package com.yinhe.ec.cpps.model;


public class NbSendData
{

    public NbSendData()
    {
        manuCode = "";
        createUser = "";
    }

    public String getSendId()
    {
        return sendId;
    }

    public void setSendId(String sendId)
    {
        this.sendId = sendId;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public int getSendType()
    {
        return sendType;
    }

    public void setSendType(int sendType)
    {
        this.sendType = sendType;
    }

    public String getSendDetail()
    {
        return sendDetail;
    }

    public void setSendDetail(String sendDetail)
    {
        this.sendDetail = sendDetail;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public int getManageFlag()
    {
        return manageFlag;
    }

    public void setManageFlag(int manageFlag)
    {
        this.manageFlag = manageFlag;
    }

    public String getManageDate()
    {
        return manageDate;
    }

    public void setManageDate(String manageDate)
    {
        this.manageDate = manageDate;
    }

    public int getManageCount()
    {
        return manageCount;
    }

    public void setManageCount(int manageCount)
    {
        this.manageCount = manageCount;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getManuCode()
    {
        return manuCode;
    }

    public void setManuCode(String manuCode)
    {
        this.manuCode = manuCode;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCommandId()
    {
        return commandId;
    }

    public void setCommandId(String commandId)
    {
        this.commandId = commandId;
    }

    public String getSendFrame()
    {
        return sendFrame;
    }

    public void setSendFrame(String sendFrame)
    {
        this.sendFrame = sendFrame;
    }

    public String getReciveFrame()
    {
        return reciveFrame;
    }

    public void setReciveFrame(String reciveFrame)
    {
        this.reciveFrame = reciveFrame;
    }

    public String getReciveDesc()
    {
        return reciveDesc;
    }

    public void setReciveDesc(String reciveDesc)
    {
        this.reciveDesc = reciveDesc;
    }

    public String getSendName()
    {
        return sendName;
    }

    public void setSendName(String sendName)
    {
        this.sendName = sendName;
    }

    private String sendId;
    private String meterNo;
    private String deviceId;
    private int sendType;
    private String sendDetail;
    private String createDate;
    private int manageFlag;
    private String manageDate;
    private int manageCount;
    private String remark;
    private int custId;
    private String manuCode;
    private String createUser;
    private String commandId;
    private String sendFrame;
    private String reciveFrame;
    private String reciveDesc;
    private String sendName;
}
