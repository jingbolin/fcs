// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbApplicationDAO.java

package com.yinhe.ec.cpps.ycmeter.dao;

import com.yinhe.ec.cpps.model.*;

import java.util.List;

public interface NbApplicationDAO
{

    public abstract List getNbApplication();

    public abstract void addApplicationMgr(Nb_Application nb_application);

    public abstract void editApplicationMgr(Nb_Application nb_application);

    public abstract void delApplicationMgr(String s);

    public abstract Nb_Application getNbApplicationByappId(String s);

    public abstract List getNbMeterParameter(int i);

    public abstract void addNbMeterParameter(Nb_MeterParameter nb_meterparameter);

    public abstract void editNbMeterParameter(Nb_MeterParameter nb_meterparameter);

    public abstract void delNbMeterParameter(String s);

    public abstract int getManuCodeCount(String s);

    public abstract Nb_MeterParameter getNbMeterParameterByManuCode(String s);

    public abstract void updateAccessToken(String s, String s1);

    public abstract List getNbMeterService();

    public abstract void addNbMeterService(Nb_MeterService nb_meterservice);

    public abstract void editNbMeterService(Nb_MeterService nb_meterservice);

    public abstract void delNbMeterService(String s);

    public abstract int getNbmeterPropertyCount(String s);

    public abstract List getNbmeterProperty(String s);

    public abstract void addNbmeterProperty(Nb_MeterProperty nb_meterproperty);

    public abstract void editNbmeterProperty(Nb_MeterProperty nb_meterproperty);

    public abstract void delNbmeterProperty(String s);
}
