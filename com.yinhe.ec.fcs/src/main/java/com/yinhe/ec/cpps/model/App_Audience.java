// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_Audience.java

package com.yinhe.ec.cpps.model;


public class App_Audience
{

    public App_Audience()
    {
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getBase64Secret()
    {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret)
    {
        this.base64Secret = base64Secret;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getExpiresSecond()
    {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond)
    {
        this.expiresSecond = expiresSecond;
    }

    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}
