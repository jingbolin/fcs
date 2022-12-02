// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CmdController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.CmdService;
import com.yinhe.ec.cpps.model.Cmd;
import com.yinhe.ec.cpps.model.NbMeterPwd;
import com.yinhe.ec.cpps.system.service.GenPkIdService;

import java.util.List;

public class CmdController
{

    public CmdController()
    {
    }

    public List getCmd(int meterPtl)
    {
        return cmdService.getCmd(meterPtl);
    }

    public List getNbMeterPwd()
    {
        return cmdService.getNbMeterPwd();
    }

    public NbMeterPwd getNbMeterPwdByGroupNo(int pwdGroupNo)
    {
        return cmdService.getNbMeterPwdByGroupNo(pwdGroupNo);
    }

    public String addNbMeterPwd(NbMeterPwd nbMeterPwd)
    {
        nbMeterPwd.setPwdGroupNo(genPkIdservice.getPkNoForInt("NbMeterPwd", "pwdGroupNo"));
        return cmdService.addNbMeterPwd(nbMeterPwd);
    }

    public String editMeterPwd(NbMeterPwd nbMeterPwd)
    {
        return cmdService.editMeterPwd(nbMeterPwd);
    }

    public String deleteMeterPwdByGroupNo(int pwdGroupNo)
    {
        return cmdService.deleteMeterPwdByGroupNo(pwdGroupNo);
    }

    public List getMeterCmd(String orders)
    {
        return cmdService.getMeterCmd(orders);
    }

    public String addMeterCmd(Cmd cmd)
    {
        cmd.setCmdId(genPkIdservice.getPkNoForInt("Cmd", "cmdId"));
        return cmdService.addMeterCmd(cmd);
    }

    public String editMeterCmd(Cmd cmd)
    {
        return cmdService.editMeterCmd(cmd);
    }

    public String stopMeterCmdById(int cmdId, int isuse)
    {
        return cmdService.stopMeterCmdById(cmdId, isuse);
    }

    private CmdService cmdService;
    private GenPkIdService genPkIdservice;
}
