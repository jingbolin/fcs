// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeItemListDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.FeeItemList;
import com.yinhe.ec.cpps.model.StepPriceList;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FeeItemListDao
{

    public abstract List getFeeItemListByCustId(int i, String s);

    public abstract void addFeeItemList(FeeItemList feeitemlist)
        throws DataAccessException;

    public abstract void editFeeItemList(FeeItemList feeitemlist)
        throws DataAccessException;

    public abstract void delFeeItemListById(String s)
        throws DataAccessException;

    public abstract FeeItemList getFeeItemList(String s);

    public abstract boolean getFeeItemIsused(String s);

    public abstract List getStepPriceList(int i);

    public abstract StepPriceList getStepPrice(String s);

    public abstract void addStepPriceList(StepPriceList steppricelist)
        throws DataAccessException;

    public abstract void editStepPriceList(StepPriceList steppricelist)
        throws DataAccessException;

    public abstract void delStepPriceList(String s)
        throws DataAccessException;

    public abstract boolean getStepPriceIsused(String s);

    public abstract List getPayWays();
}
