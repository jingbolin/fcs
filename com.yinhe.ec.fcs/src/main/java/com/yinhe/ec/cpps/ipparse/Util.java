// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Util.java

package com.yinhe.ec.cpps.ipparse;

import org.apache.log4j.Level;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

// Referenced classes of package com.yinhe.ec.cpps.ipparse:
//            LogFactory

public class Util
{

    public Util()
    {
    }

    public static byte[] getIpByteArrayFromString(String ip)
    {
        byte ret[] = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try
        {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xff);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xff);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xff);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xff);
        }
        catch(Exception e)
        {
            LogFactory.log("\u4ECEip\u7684\u5B57\u7B26\u4E32\u5F62\u5F0F\u5F97\u5230\u5B57\u8282\u6570\u7EC4\u5F62\u5F0F\u62A5\u9519", Level.ERROR, e);
        }
        return ret;
    }

    public static String getIpStringFromBytes(byte ip[])
    {
        sb.delete(0, sb.length());
        sb.append(ip[0] & 0xff);
        sb.append('.');
        sb.append(ip[1] & 0xff);
        sb.append('.');
        sb.append(ip[2] & 0xff);
        sb.append('.');
        sb.append(ip[3] & 0xff);
        return sb.toString();
    }

    public static String getString(byte b[], int offset, int len, String encoding)
    {
        try
        {
            return new String(b, offset, len, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            return new String(b, offset, len);
        }
    }

    private static StringBuilder sb = new StringBuilder();

}
