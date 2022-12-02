// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChangeMInfoDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import java.util.List;

public interface ChangeMInfoDao
{

    public abstract Integer getTotal(String s);

    public abstract List getChangeMInfo(int i, int j, String s);
}
