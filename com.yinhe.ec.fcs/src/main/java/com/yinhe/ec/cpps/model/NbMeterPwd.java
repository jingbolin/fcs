// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbMeterPwd.java

package com.yinhe.ec.cpps.model;


public class NbMeterPwd
{

    public NbMeterPwd()
    {
        pwdGroupNo = 0;
        pwdGroupName = "";
        operatorPwd = "";
        operatorCode = "";
        remark = "";
    }

    public int getPwdGroupNo()
    {
        return pwdGroupNo;
    }

    public void setPwdGroupNo(int pwdGroupNo)
    {
        this.pwdGroupNo = pwdGroupNo;
    }

    public String getPwdGroupName()
    {
        return pwdGroupName;
    }

    public void setPwdGroupName(String pwdGroupName)
    {
        this.pwdGroupName = pwdGroupName;
    }

    public String getOperatorPwd()
    {
        return operatorPwd;
    }

    public void setOperatorPwd(String operatorPwd)
    {
        this.operatorPwd = operatorPwd;
    }

    public String getOperatorCode()
    {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode)
    {
        this.operatorCode = operatorCode;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int pwdGroupNo;
    private String pwdGroupName;
    private String operatorPwd;
    private String operatorCode;
    private String remark;
}
