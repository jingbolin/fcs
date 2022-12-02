// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeTypeDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.FeeType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FeeTypeDao
{

    public abstract List getAllFeeType();

    public abstract void addFeeType(FeeType feetype)
        throws DataAccessException;

    public abstract void editFeeType(FeeType feetype)
        throws DataAccessException;

    public abstract void delFeeTypeById(String s)
        throws DataAccessException;

    public abstract FeeType getFeeType(String s);

    public abstract boolean getFeeTypeIsused(String s);
}
