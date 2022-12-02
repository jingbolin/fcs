// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CmdDao.java

package com.yinhe.ec.cpps.base.dao;

import com.yinhe.ec.cpps.model.Cmd;
import com.yinhe.ec.cpps.model.NbMeterPwd;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CmdDao
{

    public abstract List getCmd(int i);

    public abstract Cmd getCmdByCmdId(int i);

    public abstract List getNbMeterPwd();

    public abstract NbMeterPwd getNbMeterPwdByGroupNo(int i);

    public abstract void addNbMeterPwd(NbMeterPwd nbmeterpwd)
        throws DataAccessException;

    public abstract void editMeterPwd(NbMeterPwd nbmeterpwd)
        throws DataAccessException;

    public abstract void deleteMeterPwdByGroupNo(int i)
        throws DataAccessException;

    public abstract boolean getMeterPwdIsUsed(int i);

    public abstract List getMeterCmd(String s);

    public abstract void addMeterCmd(Cmd cmd);

    public abstract void editMeterCmd(Cmd cmd);

    public abstract void stopMeterCmdById(int i, int j);
}
