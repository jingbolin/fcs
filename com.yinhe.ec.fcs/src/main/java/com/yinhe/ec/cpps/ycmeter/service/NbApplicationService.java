// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbApplicationService.java

package com.yinhe.ec.cpps.ycmeter.service;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.ycmeter.dao.NbApplicationDAO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class NbApplicationService
{

    public NbApplicationService()
    {
    }

    public List getNbApplication()
    {
        return applicationDAO.getNbApplication();
    }

    public String addApplicationMgr(Nb_Application application)
    {
        try
        {
            applicationDAO.addApplicationMgr(application);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u5E94\u7528\u6210\u529F";
    }

    public String editApplicationMgr(Nb_Application application)
    {
        try
        {
            applicationDAO.editApplicationMgr(application);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u5E94\u7528\u6210\u529F";
    }

    public String delApplicationMgr(String appId)
    {
        try
        {
            applicationDAO.delApplicationMgr(appId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u5E94\u7528\u6210\u529F";
    }

    public List getNbMeterParameter(int custId)
    {
        return applicationDAO.getNbMeterParameter(custId);
    }

    public String addNbMeterParameter(Nb_MeterParameter meterParameter)
    {
        if(applicationDAO.getManuCodeCount(meterParameter.getManuCode()) > 0)
            return "\u5382\u5546\u6807\u8BC6\u5DF2\u5B58\u5728\uFF0C\u4E0D\u5141\u8BB8\u91CD\u590D\u6DFB\u52A0";
        try
        {
            applicationDAO.addNbMeterParameter(meterParameter);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u6DFB\u52A0\u4EA7\u54C1\u6210\u529F";
    }

    public String editNbMeterParameter(Nb_MeterParameter meterParameter)
    {
        try
        {
            applicationDAO.editNbMeterParameter(meterParameter);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u4EA7\u54C1\u6210\u529F";
    }

    public String delNbMeterParameter(String projectId)
    {
        try
        {
            applicationDAO.delNbMeterParameter(projectId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u4EA7\u54C1\u6210\u529F";
    }

    public List getNbMeterService()
    {
        return applicationDAO.getNbMeterService();
    }

    public String addNbMeterService(Nb_MeterService nbMeterService)
    {
        try
        {
            applicationDAO.addNbMeterService(nbMeterService);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u670D\u52A1\u6210\u529F";
    }

    public String editNbMeterService(Nb_MeterService nbMeterService)
    {
        try
        {
            applicationDAO.editNbMeterService(nbMeterService);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u670D\u52A1\u6210\u529F";
    }

    public String delNbMeterService(String serviceId)
    {
        if(applicationDAO.getNbmeterPropertyCount(serviceId) > 0)
            return "\u5220\u9664\u670D\u52A1\u5931\u8D25\uFF0C\u8BE5\u670D\u52A1\u4E0B\u5B58\u5728\u5DF2\u4F7F\u7528\u7684\u5C5E\u6027";
        try
        {
            applicationDAO.delNbMeterService(serviceId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u670D\u52A1\u6210\u529F";
    }

    public List getNbmeterProperty(String serviceId)
    {
        return applicationDAO.getNbmeterProperty(serviceId);
    }

    public String addNbmeterProperty(Nb_MeterProperty nbMeterProperty)
    {
        try
        {
            applicationDAO.addNbmeterProperty(nbMeterProperty);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u5C5E\u6027\u6210\u529F";
    }

    public String editNbmeterProperty(Nb_MeterProperty nbMeterProperty)
    {
        try
        {
            applicationDAO.editNbmeterProperty(nbMeterProperty);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u5C5E\u6027\u6210\u529F";
    }

    public String delNbmeterProperty(String propertyId)
    {
        try
        {
            applicationDAO.delNbmeterProperty(propertyId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u5C5E\u6027\u6210\u529F";
    }

    private NbApplicationDAO applicationDAO;
}
