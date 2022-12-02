// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuncMethodDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.FuncMethod;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface FuncMethodDao
{

    public abstract List getFuncMethod();

    public abstract List getFuncMethodByOrId(String s);

    public abstract FuncMethod getFuncMethodRoot();

    public abstract void addFuncMethod(FuncMethod funcmethod)
        throws DataAccessException;

    public abstract void editFuncMethodByFmId(FuncMethod funcmethod)
        throws DataAccessException;

    public abstract void deleteFuncMethod(String s, int i)
        throws DataAccessException;

    public abstract List getFuncMethodByOperatorAccount(String s);

    public abstract void initFuncMethodToAdmin(List list);

    public abstract List getKJFuncMethodByOperatorId(int i);

    public abstract List getFuncListByOperatorAccount(String s);

    public abstract List getFuncMethodByOperatorId(int i);
}
