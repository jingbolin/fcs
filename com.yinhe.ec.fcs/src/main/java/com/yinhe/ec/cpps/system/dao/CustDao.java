// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.Customer;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface CustDao
{

    public abstract List getCustomerInfo(int i);

    public abstract void addCustomer(Customer customer)
        throws DataAccessException;

    public abstract void modCustomer(Customer customer)
        throws DataAccessException;

    public abstract String delCustomerById(int i)
        throws DataAccessException;

    public abstract Customer getCustomerById(int i);

    public abstract int getCountCustomer(String s);

    public abstract String registerInfo(int i, String s, String s1);

    public abstract Customer getCustomerByNo(String s);
}
