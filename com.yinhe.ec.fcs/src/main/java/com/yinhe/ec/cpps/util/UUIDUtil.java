// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UUIDUtil.java

package com.yinhe.ec.cpps.util;

import java.util.UUID;

public class UUIDUtil
{

    public UUIDUtil()
    {
    }

    public static String getUUID()
    {
        String s = UUID.randomUUID().toString();
        return (new StringBuilder(String.valueOf(s.substring(0, 8)))).append(s.substring(9, 13)).append(s.substring(14, 18)).append(s.substring(19, 23)).append(s.substring(24)).toString();
    }
}
