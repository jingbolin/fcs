// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BankServiceDao.java

package com.yinhe.ec.cpps.user.dao;

import java.util.List;

public interface BankServiceDao
{

    public abstract String userBill(String s);

    public abstract String userPay(String s);

    public abstract void writeCvsToDb(List list);

    public abstract List getCebDuizhangInfo(int i, int j, String s);

    public abstract int getCebDuizhangInfoTotal(String s);

    public abstract List getCebDuizhangHuizong(String s, String s1, String s2);
}
