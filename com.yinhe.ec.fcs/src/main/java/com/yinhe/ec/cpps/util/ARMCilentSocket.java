// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ARMCilentSocket.java

package com.yinhe.ec.cpps.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            BaseSocket

public class ARMCilentSocket
{

    public ARMCilentSocket(String ip, int port)
    {
        Data = "";
        socket = null;
        out = null;
        getMessageStream = null;
        this.ip = ip;
        this.port = port;
    }

    public void CreateConnection()
        throws Exception
    {
        try
        {
            socket = BaseSocket.getSocket(ip, port);
            socket.setKeepAlive(true);
        }
        catch(Exception e)
        {
            Data = "\u5E95\u5C42\u8FDE\u63A5\u5931\u8D25";
            System.out.println((new StringBuilder("Socket\u5EFA\u7ACB\u9519\u8BEF:")).append(e.getMessage()).toString());
            if(socket != null)
                socket.close();
            throw e;
        }
    }

    public void sendMessage(byte sendMessage[])
        throws Exception
    {
        try
        {
            if(sendMessage != null)
            {
                out = new DataOutputStream(socket.getOutputStream());
                out.write(sendMessage);
                out.flush();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if(out != null)
                out.close();
            throw e;
        }
    }

    public DataInputStream getMessageStream()
        throws Exception
    {
        getMessageStream = null;
        try
        {
            getMessageStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            return getMessageStream;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if(getMessageStream != null)
                getMessageStream.close();
            throw e;
        }
    }

    public void shutDownConnection()
    {
        try
        {
            if(out != null)
                out.close();
            if(getMessageStream != null)
                getMessageStream.close();
            if(socket != null)
                socket.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private String ip;
    private int port;
    public String Data;
    public Socket socket;
    DataOutputStream out;
    DataInputStream getMessageStream;
}
