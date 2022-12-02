// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DepartDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.DepartInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DepartDao
{

    public abstract List getDepartInfoByCompanyId(int i);

    public abstract List getDepartInfoAll();

    public abstract void addDepartInfo(DepartInfo departinfo)
        throws DataAccessException;

    public abstract void editDepartInfo(DepartInfo departinfo)
        throws DataAccessException;

    public abstract void deleteDepartInfo(String s)
        throws DataAccessException;

    public abstract DepartInfo getDepartInfoById(String s);

    public abstract DepartInfo getDepartInfoByPid(String s);

    public abstract List getDepartInfoListByPid(String s);

    public abstract void changeDepartInfoRoot(String s, String s1)
        throws DataAccessException;
}
