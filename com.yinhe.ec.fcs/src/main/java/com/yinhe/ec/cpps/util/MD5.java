// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD5.java

package com.yinhe.ec.cpps.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{

    public MD5()
    {
    }

    public static String getDigestedString(String source)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception) { }
        md5.reset();
        md5.update(source.getBytes());
        byte digested[] = md5.digest();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < digested.length; i++)
        {
            byte b = digested[i];
            int value = (b & 0x7f) + (b < 0 ? 128 : '\0');
            buffer.append(value < 16 ? "0" : "");
            buffer.append(Integer.toHexString(value));
        }

        return buffer.toString();
    }

    public static final String SYSKEY = "PengNiaoKJ";
}
