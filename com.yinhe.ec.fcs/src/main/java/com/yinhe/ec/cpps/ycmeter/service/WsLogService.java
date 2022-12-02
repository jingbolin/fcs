// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WsLogService.java

package com.yinhe.ec.cpps.ycmeter.service;

import com.yinhe.ec.cpps.ycmeter.dao.WsLogDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WsLogService
{

    public WsLogService()
    {
    }

    public Map getWsInfo(int page, int rows, String orders)
    {
        orders = (new StringBuilder("1=1 ")).append(orders).toString();
        Map map = new HashMap();
        map.put("total", wslogDao.getTotal(orders));
        map.put("rows", wslogDao.getWsInfo(page, rows, orders));
        return map;
    }

    public Map getSendDataInfo(int page, int rows, String orders)
    {
        orders = (new StringBuilder("1=1 ")).append(orders).toString();
        Map map = new HashMap();
        map.put("total", wslogDao.getTotalSendData(orders));
        map.put("rows", wslogDao.getSendDataInfo(page, rows, orders));
        return map;
    }

    public Map getUploadListByClause(int page, int rows, String orders)
    {
        orders = (new StringBuilder("1=1 ")).append(orders).toString();
        Map map = new HashMap();
        map.put("total", wslogDao.getDosageDetailTotal(orders));
        map.put("rows", wslogDao.getUploadListByClause(page, rows, orders));
        return map;
    }

    public List getNbSendDataInfo(String orders)
    {
        return wslogDao.getNbSendDataInfo(orders);
    }

    private WsLogDao wslogDao;
}
