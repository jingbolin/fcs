// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseSocket.java

package com.yinhe.ec.cpps.util;

import java.net.Socket;

public class BaseSocket
{

    public BaseSocket()
    {
    }

    public static Socket getSocket(String ip, int port)
        throws Exception
    {
        sk = new Socket(ip, port);
        return sk;
    }

    static Socket sk = null;

}
