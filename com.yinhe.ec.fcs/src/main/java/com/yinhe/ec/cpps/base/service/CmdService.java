// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CmdService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.CmdDao;
import com.yinhe.ec.cpps.model.Cmd;
import com.yinhe.ec.cpps.model.NbMeterPwd;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class CmdService
{

    public CmdService()
    {
    }

    public List getCmd(int meterPtl)
    {
        return cmdDao.getCmd(meterPtl);
    }

    public List getNbMeterPwd()
    {
        return cmdDao.getNbMeterPwd();
    }

    public NbMeterPwd getNbMeterPwdByGroupNo(int pwdGroupNo)
    {
        return cmdDao.getNbMeterPwdByGroupNo(pwdGroupNo);
    }

    public String addNbMeterPwd(NbMeterPwd nbMeterPwd)
    {
        try
        {
            cmdDao.addNbMeterPwd(nbMeterPwd);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u5BC6\u7801\u6210\u529F";
    }

    public String editMeterPwd(NbMeterPwd nbMeterPwd)
    {
        try
        {
            cmdDao.editMeterPwd(nbMeterPwd);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u5BC6\u7801\u6210\u529F";
    }

    public String deleteMeterPwdByGroupNo(int pwdGroupNo)
    {
        if(cmdDao.getMeterPwdIsUsed(pwdGroupNo))
            return "\u8BE5\u5BC6\u7801\u5DF2\u4F7F\u7528\uFF0C\u4E0D\u5141\u8BB8\u5220\u9664";
        try
        {
            cmdDao.deleteMeterPwdByGroupNo(pwdGroupNo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u5BC6\u7801\u6210\u529F";
    }

    public List getMeterCmd(String orders)
    {
        return cmdDao.getMeterCmd(orders);
    }

    public String addMeterCmd(Cmd cmd)
    {
        try
        {
            cmdDao.addMeterCmd(cmd);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u6570\u636E\u6807\u8BC6\u6210\u529F";
    }

    public String editMeterCmd(Cmd cmd)
    {
        try
        {
            cmdDao.editMeterCmd(cmd);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6570\u636E\u6807\u8BC6\u6210\u529F";
    }

    public String stopMeterCmdById(int cmdId, int isuse)
    {
        try
        {
            cmdDao.stopMeterCmdById(cmdId, isuse);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u6570\u636E\u6807\u8BC6\u6210\u529F";
    }

    private CmdDao cmdDao;
}
