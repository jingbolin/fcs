// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ManufactureDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.Manufacture;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ManufactureDao
{

    public abstract List getManufacture(int i);

    public abstract void addManufacture(Manufacture manufacture)
        throws DataAccessException;

    public abstract void modManufacture(Manufacture manufacture)
        throws DataAccessException;

    public abstract void delManufacture(int i)
        throws DataAccessException;

    public abstract Manufacture getManufactureById(int i);
}
