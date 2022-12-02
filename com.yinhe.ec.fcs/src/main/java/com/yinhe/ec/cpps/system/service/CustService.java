// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustService.java

package com.yinhe.ec.cpps.system.service;

import com.yinhe.ec.cpps.model.Customer;
import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.system.dao.CustDao;
import com.yinhe.ec.cpps.system.dao.OperatorDao;
import com.yinhe.ec.cpps.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CustService
{

    @Autowired
    private CustDao custDao;
    @Autowired
    private OperatorDao operatorDao;

    public CustService()
    {
    }

    public List getCustomerInfo(int custId)
    {
        return custDao.getCustomerInfo(custId);
    }

    public Customer getCustomerById(int custId)
    {
        return custDao.getCustomerById(custId);
    }

    public String addCustomer(Customer customer)
    {
        if(custDao.getCountCustomer(customer.getCustNo()) > 0)
            return "已存在的客户";
        try
        {
            custDao.addCustomer(customer);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "新增成功";
    }

    public String modCustomer(Customer customer)
    {
        try
        {
            custDao.modCustomer(customer);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String delCustomerById(int custId)
    {
        try
        {
            return custDao.delCustomerById(custId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String registerInfo(int custId, String regCode)
    {
        String msg = "";
        String regEnd = "";
        String oldRegCode = regCode;
        try
        {
            DESPlus dp = new DESPlus();
            regCode = dp.decrypt(regCode).toUpperCase();
            if(regCode.length() == 23 && regCode.substring(0, 12).equals(Tools.getMacByIp().toUpperCase()) && regCode.substring(21, 23).equals("FE"))
            {
                regEnd = (new StringBuilder()).append(regCode.substring(13, 21)).toString();
                msg = custDao.registerInfo(custId, regEnd, oldRegCode);
                return msg;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "注册失败";
        }
        return "注册成功";
    }

    public Customer getCustomerByNo(String custNo)
    {
        return custDao.getCustomerByNo(custNo);
    }

    public String regCustomerInfo(int custId, int cycle, int operatorId, String pwd)
    {
        String regEnd = "";
        String regCode = "";
        int months = 0;
        switch(cycle)
        {
        case 1: // '\001'
            months = 1;
            break;

        case 2: // '\002'
            months = 3;
            break;

        case 3: // '\003'
            months = 6;
            break;

        case 4: // '\004'
            months = 12;
            break;

        case 5: // '\005'
            months = 24;
            break;

        case 6: // '\006'
            months = 360;
            break;

        default:
            months = 0;
            break;
        }
        if(operatorId == 0)
        {
            Operator operator = operatorDao.getOperatorById(operatorId);
            if(operator != null)
            {
                if(!operator.getOperatorPwd().equals(MD5.getDigestedString(pwd)))
                    return "";
                try
                {
                    DESPlus dp = new DESPlus();
                    if(months == 360)
                    {
                        regEnd = "20991231";
                    } else
                    {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        cal.add(2, months);
                        regEnd = (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
                    }
                    regCode = (new StringBuilder(String.valueOf(Tools.getMacByIp().toUpperCase()))).append("|").append(regEnd).append("FE").toString();
                    String msg = custDao.registerInfo(custId, regEnd, dp.encrypt(regCode).toUpperCase());
                    return msg;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                return "";
            } else
            {
                return "";
            }
        } else
        {
            return "";
        }
    }

}
