// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DosageController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.base.service.AreaInfoService;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.service.DosageService;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DosageController
{

    public DosageController()
    {
    }

    public List getDosageByClause(String meterNo, String startDt, String endDt)
    {
        return dosageservice.getDosageByClause(meterNo, startDt, endDt);
    }

    public String getMReadRatio()
    {
        return dosageservice.getMReadRatio();
    }

    public List getDateDosageByYear(Integer n)
    {
        String year = String.valueOf(Integer.parseInt(Tools.getCurDateY()) - n.intValue());
        return dosageservice.getDateDosageByYear(year);
    }

    public List getMonthDosageByClause(String clauses, String markMonth)
    {
        return dosageservice.getMonthDosageByClause(clauses, markMonth);
    }

    public Map getDosageDeatilByClause(int page, int rows, String orders)
    {
        return dosageservice.getDosageDeatilByClause(page, rows, orders);
    }

    public String getAlarmInfo(String operatorId, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        com.yinhe.ec.cpps.model.AlarmInfo dd = dosageservice.getAlarmInfo(operatorId);
        JSONArray json = JSONArray.fromObject(dd);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        return null;
    }

    public String getMainReportInfo(String operatorId, String custId, String areaId, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        com.yinhe.ec.cpps.model.AlarmInfo dd = dosageservice.getMainReportInfo(operatorId, custId, "");
        JSONArray json = JSONArray.fromObject(dd);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        return null;
    }

    public Map getMainFeeAnalysis(String operatorId, String custId, String areaId)
    {
        return dosageservice.getMainFeeAnalysis(operatorId, custId, "");
    }

    public Map getMainDosageAnalysis(String operatorId, String custId, String areaId)
    {
        return dosageservice.getMainDosageAnalysis(operatorId, custId, "");
    }

    public Map getMainErrorCount(String custId)
    {
        return dosageservice.getMainErrorCount(custId);
    }

    private DosageService dosageservice;
    private AreaInfoService areaInfoService;
}
