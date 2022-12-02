// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NbMeterDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import com.yinhe.ec.cpps.model.NbMeterInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface NbMeterDao
{

    public abstract List getNbMeterListByClause(String s);

    public abstract void addNbMeterInfo(NbMeterInfo nbmeterinfo)
        throws DataAccessException;

    public abstract void modifyNbMeterInfo(NbMeterInfo nbmeterinfo)
        throws DataAccessException;

    public abstract void deleteNbMeterInfo(String s, String s1, int i, String s2)
        throws DataAccessException;

    public abstract void uploadNbMeterInfo(String s, String s1, int i, int j, String s2, String s3, int k, 
            int l, String s4, String s5, int i1)
        throws DataAccessException;

    public abstract void registerDevice(String s, String s1, int i, String s2, String s3);

    public abstract void deleteDevice(String s, String s1, int i);

    public abstract void updateNbMeterInfoData(String s, Double double1, Double double2, Double double3, int i, Double double4, String s1, 
            String s2);

    public abstract void postAsynCommand(String s, String s1, String s2, String s3, int i, String s4, String s5, 
            String s6);

    public abstract List getNbDataDetail(String s);

    public abstract void insertNbData(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7);

    public abstract void insertNbDataToDatabase(List list);

    public abstract List getOneNetNbDataDetail(String s);

    public abstract List getOneNetNbSendDataInfo(String s);

    public abstract void checkOnenetRegisterDevice(String s, String s1, String s2, String s3, int i, String s4);

    public abstract List getWillPostData(String s);

    public abstract List getWaterWillPostData(String s);

    public abstract void updateNbsenddataFlag(String s, String s1, String s2);

    public abstract void updateNbMeterSwitchState(String s, String s1);

    public abstract void updateNbUploadData(String s, String s1, String s2, String s3);

    public abstract void insertEleNbData(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9, String s10, String s11, String s12, String s13, 
            String s14, String s15, String s16, String s17, String s18, String s19);

    public abstract void updateEleNbMeterInfoData(String s, Double double1, Double double2, Double double3, int i, Double double4, String s1, 
            String s2, String s3);

    public abstract void updateNbMeterIMSI(String s, String s1);

    public abstract void upLoadFactoryTestExcel(String s, String s1, String s2);

    public abstract void delFactoryTestMeter();

    public abstract void postCommandIntoDb(String s, String s1, String s2, String s3, int i);

    public abstract int getNbMeterCount(String s);
}
