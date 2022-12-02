// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.system.dao.LoginDao;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{

    public LoginService()
    {
    }

    public Operator operatorLogin(String operatorAccount, String loginIp, String pwd)
        throws DataAccessException
    {
        return loginDao.userLogin(operatorAccount, loginIp, pwd);
    }

    private LoginDao loginDao;
}
