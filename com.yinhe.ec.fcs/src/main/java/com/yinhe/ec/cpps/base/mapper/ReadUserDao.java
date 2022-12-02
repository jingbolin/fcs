// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadUserDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.ReadUser;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReadUserDao
{

    public abstract List getReadUserByCustId(int i);

    public abstract void addReadUser(ReadUser readuser)
        throws DataAccessException;

    public abstract void editReadUser(ReadUser readuser)
        throws DataAccessException;

    public abstract void delReadUserById(String s)
        throws DataAccessException;

    public abstract ReadUser getReadUser(String s);
}
