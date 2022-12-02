// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.base.service.AreaInfoService;
import com.yinhe.ec.cpps.dto.OperatorAccountDTO;
import com.yinhe.ec.cpps.dto.OperatorDTO;
import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.system.service.OperatorService;
import com.yinhe.ec.cpps.util.MD5;
import com.yinhe.ec.cpps.util.Tools;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class OperatorController
{

    public OperatorController()
    {
    }

    public String editOperatorPwd(OperatorAccountDTO operatorAccountDTO)
    {
        return operatorService.editOperatorPwdById(operatorAccountDTO);
    }

    public Map getOperator(int page, int rows, String condition, int operatorId, int custId, String areaId)
    {
        if(operatorId > 0){
            condition = (new StringBuilder(String.valueOf(condition))).append(" and CustId=").append(custId).append(" and areaId in(").append(areaInfoService.getChildAreaInfoByAreaId(custId, areaId)).append(")").toString();
        }
        return operatorService.getOperator(page, rows, condition);
    }

    public String addOperator(OperatorDTO operatorDTO)
        throws IllegalAccessException, InvocationTargetException
    {
        BeanUtils.copyProperties(operator, operatorDTO);
        operator.setOperatorId(genPkIdservice.getPkNoForInt("operator", "operatorId"));
        operator.setOperatorPwd(MD5.getDigestedString("pt1234"));
        operator.setIsLogin(1);
        operator.setCreateDate(Tools.getNow());
        String roleId = "";
        if(operatorDTO.getRoleId() != null){
            roleId = operatorDTO.getRoleId();
        }
        return operatorService.addOperator(operator, roleId);
    }

    public String editOperator(OperatorDTO operatorDTO)
        throws IllegalAccessException, InvocationTargetException
    {
        BeanUtils.copyProperties(operator, operatorDTO);
        String roleId = "";
        if(operatorDTO.getRoleId() != null){
            roleId = operatorDTO.getRoleId();
        }
        return operatorService.editOperator(operator, roleId);
    }

    public String deleteOperatorById(int operatorId)
    {
        return operatorService.deleteOperatorById(operatorId);
    }

    public String setIsLogin(int operatorId, int status)
    {
        return operatorService.setIsLogin(operatorId, status);
    }

    public String setInitPwd(int operatorId)
    {
        return operatorService.setInitPwd(operatorId);
    }

    public boolean validUserAccount(String operatorAccount)
    {
        return operatorService.getOperatorResultSetByAccount(operatorAccount);
    }

    public String editOperatorAccount(OperatorAccountDTO operatorAccountDTO)
    {
        return operatorService.editOperatorAccount(operatorAccountDTO);
    }

    public List getOperatorListByCondition(int custId, String areaId)
    {
        String condition = "";
        condition = (new StringBuilder(" and custId=")).append(custId).append(" and areaId in(").append(areaInfoService.getChildAreaInfoByAreaId(custId, areaId)).append(")").toString();
        return operatorService.getOperatorListByCondition(condition);
    }

    public String app_editOperatorPwd(int operatorId, String oldPwd, String newPwd, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String msg = "";
        msg = operatorService.app_editOperatorPwd(operatorId, oldPwd, newPwd);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        return null;
    }

    private OperatorService operatorService;
    private Operator operator;
    private GenPkIdService genPkIdservice;
    private AreaInfoService areaInfoService;
}
