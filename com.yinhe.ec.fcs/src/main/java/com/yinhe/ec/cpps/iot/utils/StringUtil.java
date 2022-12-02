// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringUtil.java

package com.yinhe.ec.cpps.iot.utils;


public class StringUtil
{

    public StringUtil()
    {
    }

    public static boolean strIsNullOrEmpty(String s)
    {
        return s == null || s.trim().length() < 1;
    }
}
