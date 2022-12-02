// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbApplicationController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.ycmeter.service.NbApplicationService;

import java.util.List;

public class NbApplicationController
{

    public NbApplicationController()
    {
    }

    public List getNbApplication()
    {
        return applicationService.getNbApplication();
    }

    public String addApplicationMgr(Nb_Application application)
    {
        return applicationService.addApplicationMgr(application);
    }

    public String editApplicationMgr(Nb_Application application)
    {
        return applicationService.editApplicationMgr(application);
    }

    public String delApplicationMgr(String appId)
    {
        return applicationService.delApplicationMgr(appId);
    }

    public List getNbMeterParameter(int custId)
    {
        return applicationService.getNbMeterParameter(custId);
    }

    public String addNbMeterParameter(Nb_MeterParameter meterParameter)
    {
        return applicationService.addNbMeterParameter(meterParameter);
    }

    public String editNbMeterParameter(Nb_MeterParameter meterParameter)
    {
        return applicationService.editNbMeterParameter(meterParameter);
    }

    public String delNbMeterParameter(String projectId)
    {
        return applicationService.delNbMeterParameter(projectId);
    }

    public List getNbMeterService()
    {
        return applicationService.getNbMeterService();
    }

    public String addNbMeterService(Nb_MeterService nbMeterService)
    {
        return applicationService.addNbMeterService(nbMeterService);
    }

    public String editNbMeterService(Nb_MeterService nbMeterService)
    {
        return applicationService.editNbMeterService(nbMeterService);
    }

    public String delNbMeterService(String serviceId)
    {
        return applicationService.delNbMeterService(serviceId);
    }

    public List getNbmeterProperty(String serviceId)
    {
        return applicationService.getNbmeterProperty(serviceId);
    }

    public String addNbmeterProperty(Nb_MeterProperty nbMeterProperty)
    {
        return applicationService.addNbmeterProperty(nbMeterProperty);
    }

    public String editNbmeterProperty(Nb_MeterProperty nbMeterProperty)
    {
        return applicationService.editNbmeterProperty(nbMeterProperty);
    }

    public String delNbmeterProperty(String propertyId)
    {
        return applicationService.delNbmeterProperty(propertyId);
    }

    private NbApplicationService applicationService;
}
