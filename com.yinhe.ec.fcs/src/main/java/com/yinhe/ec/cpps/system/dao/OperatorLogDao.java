// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorLogDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.OperatorLog;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface OperatorLogDao
{

    public abstract List getOperatorLog(int i, int j, String s);

    public abstract Integer getTotal(String s);

    public abstract Integer addOperatorLog(OperatorLog operatorlog)
        throws DataAccessException;
}
