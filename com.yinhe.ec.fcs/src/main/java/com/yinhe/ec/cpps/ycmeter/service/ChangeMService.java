// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChangeMService.java

package com.yinhe.ec.cpps.ycmeter.service;

import com.yinhe.ec.cpps.ycmeter.dao.ChangeMInfoDao;

import java.util.HashMap;
import java.util.Map;

public class ChangeMService
{

    public ChangeMService()
    {
    }

    public Map getChangeMInfo(int page, int rows, String order, String areaIds)
    {
        if(!"".equals(areaIds))
            order = (new StringBuilder(String.valueOf(order))).append(" and AreaId in (").append(areaIds).append(") ").toString();
        Map map = new HashMap();
        map.put("total", changeMDao.getTotal(order));
        map.put("rows", changeMDao.getChangeMInfo(page, rows, order));
        return map;
    }

    private ChangeMInfoDao changeMDao;
}
