// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MusterDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import com.yinhe.ec.cpps.model.ConnInfo;
import com.yinhe.ec.cpps.model.Muster;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MusterDao
{

    public abstract List getConnInfo(int i);

    public abstract void addConnInfo(ConnInfo conninfo)
        throws DataAccessException;

    public abstract void modConnInfo(ConnInfo conninfo)
        throws DataAccessException;

    public abstract void delConnInfoByNo(String s)
        throws DataAccessException;

    public abstract ConnInfo getConnInfoByConnNo(String s);

    public abstract List getMusterList(String s);

    public abstract void addMuster(Muster muster)
        throws DataAccessException;

    public abstract void modMuster(Muster muster)
        throws DataAccessException;

    public abstract void delMusterByNo(String s)
        throws DataAccessException;

    public abstract Muster getMusterByMusterNo(String s);

    public abstract int[] changeMuster(String s, String s1, int i, int j)
        throws DataAccessException;

    public abstract List getMeterListByMusterNo(String s);

    public abstract List getMusterOnlineInfo();

    public abstract List getMusterProtocol();

    public abstract List getMeterProtocol();

    public abstract String getXuniMusterNo();
}
