// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DosageService.java

package com.yinhe.ec.cpps.ycmeter.service;

import com.yinhe.ec.cpps.model.AlarmInfo;
import com.yinhe.ec.cpps.ycmeter.dao.DosageDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DosageService
{

    public DosageService()
    {
    }

    public List getDosageByClause(String meterNo, String startDt, String endDt)
    {
        return dosageDao.getDosageByClause(meterNo, startDt, endDt);
    }

    public String getMReadRatio()
    {
        return dosageDao.getMReadRatio();
    }

    public List getDateDosageByYear(String year)
    {
        return dosageDao.getDateDosageByYear(year);
    }

    public List getMonthDosageByClause(String clauses, String markMonth)
    {
        return dosageDao.getMonthDosageByClause(clauses, markMonth);
    }

    public Map getDosageDeatilByClause(int page, int rows, String orders)
    {
        orders = (new StringBuilder("1=1 ")).append(orders).append(" AND USERVIEWINFO.METERNO=DOSAGEDETAIL.METERNO ").toString();
        Map map = new HashMap();
        map.put("total", dosageDao.getDosageDetailTotal(orders));
        map.put("rows", dosageDao.getDosageDeatilByClause(page, rows, orders));
        return map;
    }

    public AlarmInfo getAlarmInfo(String operatorId)
    {
        return dosageDao.getAlarmInfo(operatorId);
    }

    public AlarmInfo getMainReportInfo(String operatorId, String custId, String feeOrders)
    {
        return dosageDao.getMainReportInfo(operatorId, custId, feeOrders);
    }

    public Map getMainFeeAnalysis(String operatorId, String custId, String feeOrders)
    {
        Map map = new HashMap();
        map.put("fee_years_1", dosageDao.getYearFeeByOperator(custId, feeOrders));
        map.put("fee_kind_ratio", dosageDao.getYearFeeKindByCustId(custId));
        return map;
    }

    public Map getMainDosageAnalysis(String operatorId, String custId, String feeOrders)
    {
        Map map = new HashMap();
        map.put("dosage_days_30", dosageDao.getHbDosageByCustId(custId));
        map.put("readRatio_days_30", dosageDao.getReadRatioByCustId(custId));
        return map;
    }

    public Map getMainErrorCount(String custId)
    {
        return dosageDao.getMainErrorCount(custId);
    }

    private DosageDao dosageDao;
}
