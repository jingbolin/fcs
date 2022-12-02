// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadList.java

package com.yinhe.ec.cpps.model;


public class ReadList
{

    public ReadList()
    {
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getReadDay()
    {
        return readDay;
    }

    public void setReadDay(String readDay)
    {
        this.readDay = readDay;
    }

    public int getReadServer()
    {
        return readServer;
    }

    public void setReadServer(int readServer)
    {
        this.readServer = readServer;
    }

    private int custId;
    private String readDay;
    private int readServer;
}
