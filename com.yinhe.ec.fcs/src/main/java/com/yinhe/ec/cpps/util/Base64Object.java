// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Base64Object.java

package com.yinhe.ec.cpps.util;


// Referenced classes of package com.yinhe.ec.cpps.util:
//            Base64Util

public class Base64Object
{

    public Base64Object()
    {
    }

    public static String base64ToString(String base64)
    {
        byte decode[] = Base64Util.decode(base64);
        String s = new String(decode);
        return s;
    }

    public static String stringToBase64(String ss)
    {
        byte bytes[] = ss.getBytes();
        String encode = Base64Util.encode(bytes);
        return encode;
    }
}
