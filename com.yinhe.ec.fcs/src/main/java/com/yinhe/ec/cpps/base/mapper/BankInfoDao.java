// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BankInfoDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.BankInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BankInfoDao
{

    public abstract List getAllBankInfo();

    public abstract void addBankInfo(BankInfo bankinfo)
        throws DataAccessException;

    public abstract void editBankInfo(BankInfo bankinfo)
        throws DataAccessException;

    public abstract void delBankInfoById(String s)
        throws DataAccessException;

    public abstract BankInfo getBankInfo(String s);
}
