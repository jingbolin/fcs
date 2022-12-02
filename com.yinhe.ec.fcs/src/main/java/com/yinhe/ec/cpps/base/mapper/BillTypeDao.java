// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BillTypeDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.BillType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BillTypeDao
{

    public abstract List getBillTypeByCustId(int i);

    public abstract void addBillType(BillType billtype)
        throws DataAccessException;

    public abstract void editBillType(BillType billtype)
        throws DataAccessException;

    public abstract void delBillTypeById(String s)
        throws DataAccessException;

    public abstract BillType getBillType(String s);
}
