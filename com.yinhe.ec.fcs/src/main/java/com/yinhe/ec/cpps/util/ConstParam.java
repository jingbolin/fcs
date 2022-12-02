// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConstParam.java

package com.yinhe.ec.cpps.util;


public class ConstParam
{

    public ConstParam(String sqlType, String socketIp, int socketPort)
    {
        SQLTYPE = sqlType;
        SOCKETIP = socketIp;
        SOCKETPORT = socketPort;
    }

    public static String SQLTYPE = "mysql";
    public static String SOCKETIP = "127.0.0.1";
    public static int SOCKETPORT = 9999;

}
