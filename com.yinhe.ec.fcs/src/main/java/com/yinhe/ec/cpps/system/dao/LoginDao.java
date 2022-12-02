// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.Operator;
import org.apache.shiro.dao.DataAccessException;

public interface LoginDao
{

    public abstract Operator userLogin(String s, String s1, String s2)
        throws DataAccessException;
}
