// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorAndFMDao.java

package com.yinhe.ec.cpps.system.dao;

import org.springframework.dao.DataAccessException;

public interface OperatorAndFMDao
{

    public abstract void setOperatorAndFM(int i, int j, String s)
        throws DataAccessException;
}
