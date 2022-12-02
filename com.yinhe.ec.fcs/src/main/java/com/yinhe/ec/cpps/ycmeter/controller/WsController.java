// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WsController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.ycmeter.service.WsLogService;

import java.util.List;
import java.util.Map;

public class WsController
{

    public WsController()
    {
    }

    public Map getWsInfo(int page, int rows, String orders)
    {
        return wsLogService.getWsInfo(page, rows, orders);
    }

    public Map getSendDataInfo(int page, int rows, String orders)
    {
        return wsLogService.getSendDataInfo(page, rows, orders);
    }

    public Map getUploadListByClause(int page, int rows, String orders)
    {
        return wsLogService.getUploadListByClause(page, rows, orders);
    }

    public List getNbSendDataInfo(String meterNo, String startDate, String endDate)
    {
        String orders = " ";
        if(!"".equals(meterNo))
            orders = (new StringBuilder(String.valueOf(orders))).append(" and meterno='").append(meterNo).append("'").toString();
        if(!"".equals(startDate))
            orders = (new StringBuilder(String.valueOf(orders))).append(" and substr(createdate,1,10)>='").append(startDate).append("'").toString();
        if(!"".equals(endDate))
            orders = (new StringBuilder(String.valueOf(orders))).append(" and substr(createdate,1,10)<='").append(endDate).append("'").toString();
        return wsLogService.getNbSendDataInfo(orders);
    }

    private WsLogService wsLogService;
}
