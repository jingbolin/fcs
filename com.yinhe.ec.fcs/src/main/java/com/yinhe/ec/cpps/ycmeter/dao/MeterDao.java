// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import com.yinhe.ec.cpps.model.MeterChange;
import com.yinhe.ec.cpps.model.Meters;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MeterDao
{

    public abstract Integer getTotal(String s);

    public abstract List getMeterInfo(int i, int j, String s);

    public abstract List getMeterListByClause(String s);

    public abstract Integer getMaxRecNo(String s);

    public abstract void changeMeterInfo(MeterChange meterchange)
        throws DataAccessException;

    public abstract int getMeterCount(MeterChange meterchange);

    public abstract List getDayDosageByMeterNo(String s);

    public abstract void updateMaxdayDosage(String s, Double double1)
        throws DataAccessException;

    public abstract void modDayDosageSum(String s, String s1, Double double1, String s2)
        throws DataAccessException;

    public abstract String getResultStrForUpdateDosageDays(String s, String s1, Double double1, String s2);

    public abstract List getMeterCountForFooter(String s);

    public abstract void batchUpdateMuster(String as[], String s);

    public abstract void addMessageInfo(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            int i, String s7)
        throws DataAccessException;

    public abstract List getSendMsgInfo(int i, int j, String s);

    public abstract Integer getMsgTotal(String s);

    public abstract Meters getMetersByClause(String s);

    public abstract String getLastMsgDt(String s);

    public abstract void addAliMessageInfo(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            int i)
        throws DataAccessException;

    public abstract String getLeftInfoByDosageSum(String s, Double double1);

    public abstract String getDosageSumByLeftDosage(String s, Double double1);
}
