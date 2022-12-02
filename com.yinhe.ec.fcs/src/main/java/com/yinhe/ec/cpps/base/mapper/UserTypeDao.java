// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserTypeDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.UserType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserTypeDao
{

    public abstract List getAllUserTypeInfo();

    public abstract void addUserType(UserType usertype)
        throws DataAccessException;

    public abstract void editUserType(UserType usertype)
        throws DataAccessException;

    public abstract void delUserTypeById(String s)
        throws DataAccessException;

    public abstract UserType getUserType(String s);
}
