// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnInfo.java

package com.yinhe.ec.cpps.model;

import org.springframework.stereotype.Service;

@Service
public class ConnInfo
{

    public ConnInfo()
    {
    }

    public String getConnNo()
    {
        return connNo;
    }

    public void setConnNo(String connNo)
    {
        this.connNo = connNo;
    }

    public int getConnStyle()
    {
        return connStyle;
    }

    public void setConnStyle(int connStyle)
    {
        this.connStyle = connStyle;
    }

    public String getConnPara()
    {
        return connPara;
    }

    public void setConnPara(String connPara)
    {
        this.connPara = connPara;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    private String connNo;
    private int connStyle;
    private String connPara;
    private int custId;
}
