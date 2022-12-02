// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DosageDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import com.yinhe.ec.cpps.model.AlarmInfo;

import java.util.List;
import java.util.Map;

public interface DosageDao
{

    public abstract List getDosageByClause(String s, String s1, String s2);

    public abstract Integer getYDosageTotal(String s);

    public abstract String getMReadRatio();

    public abstract List getDateDosageByYear(String s);

    public abstract List getMonthDosageByClause(String s, String s1);

    public abstract List getDosageDeatilByClause(int i, int j, String s);

    public abstract Integer getDosageDetailTotal(String s);

    public abstract AlarmInfo getAlarmInfo(String s);

    public abstract AlarmInfo getMainReportInfo(String s, String s1, String s2);

    public abstract List getYearFeeByOperator(String s, String s1);

    public abstract List getHbDosageByCustId(String s);

    public abstract List getYearFeeKindByCustId(String s);

    public abstract List getUserByYear(String s);

    public abstract List getReadRatioByCustId(String s);

    public abstract Map getMainErrorCount(String s);
}
