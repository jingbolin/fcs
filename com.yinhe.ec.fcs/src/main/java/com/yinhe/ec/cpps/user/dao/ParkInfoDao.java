// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParkInfoDao.java

package com.yinhe.ec.cpps.user.dao;

import com.yinhe.ec.cpps.dto.UserParkDTO;
import com.yinhe.ec.cpps.model.ParkInfo;
import com.yinhe.ec.cpps.model.UserParkInfo;
import org.apache.shiro.dao.DataAccessException;

import java.util.List;

public interface ParkInfoDao
{

    public abstract List getParkInfo(int i);

    public abstract void AddParkInfo(ParkInfo parkinfo)
        throws DataAccessException;

    public abstract void EditParkInfo(ParkInfo parkinfo)
        throws DataAccessException;

    public abstract int DelParkInfo(int i)
        throws DataAccessException;

    public abstract ParkInfo getParkInfoById(int i, int j);

    public abstract int getTotal(String s);

    public abstract List getParkInfo(int i, int j, String s);

    public abstract void AddUserParkInfo(UserParkInfo userparkinfo)
        throws DataAccessException;

    public abstract void EditUserParkInfo(UserParkInfo userparkinfo)
        throws DataAccessException;

    public abstract void DelUserParkInfo(int i, String s)
        throws DataAccessException;

    public abstract UserParkDTO getUserInfoByParkId(int i, int j);

    public abstract void EditCarCycle(String s, String s1, String s2, String s3)
        throws DataAccessException;
}
