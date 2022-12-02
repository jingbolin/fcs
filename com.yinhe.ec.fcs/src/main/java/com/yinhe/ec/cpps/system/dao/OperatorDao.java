// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.Operator;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OperatorDao
{

    public abstract Operator getOperatorByAccount(String s);

    public abstract Operator getOperatorById(int i);

    public abstract void editOperatorPwdById(int i, String s)
        throws DataAccessException;

    public abstract int getTotal(String s);

    public abstract List getOperator(int i, int j, String s);

    public abstract void addOperator(Operator operator, String s)
        throws DataAccessException;

    public abstract void editOperator(Operator operator, String s)
        throws DataAccessException;

    public abstract void deleteOperatorById(int i)
        throws DataAccessException;

    public abstract void setIsLogin(int i, int j)
        throws DataAccessException;

    public abstract void setInitPwd(int i, String s)
        throws DataAccessException;

    public abstract Integer getOperatorResultSetByAccount(String s);

    public abstract void editOperatorAccount(int i, String s)
        throws DataAccessException;

    public abstract List getOperatorListByCondition(String s);

    public abstract String getOperatorIdsById(int i);
}
