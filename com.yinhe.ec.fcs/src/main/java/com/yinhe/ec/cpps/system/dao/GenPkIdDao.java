// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenPkIdDao.java

package com.yinhe.ec.cpps.system.dao;


public interface GenPkIdDao
{

    public abstract String getPkIdByTable(String s, String s1);

    public abstract String getPkNoByTable(String s, String s1, Integer integer);

    public abstract String getPkNoByTable(String s, String s1, String s2, Integer integer);

    public abstract int getPkNoForInt(String s, String s1);
}
