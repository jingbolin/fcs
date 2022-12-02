// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaInfoService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.AreaInfoDao;
import com.yinhe.ec.cpps.model.AreaInfo;
import com.yinhe.ec.cpps.model.ReadUser;
import com.yinhe.ec.cpps.util.GetAreaInfoTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaInfoService
{

    @Autowired
    private AreaInfoDao areaInfoDao;

    public AreaInfoService()
    {
    }

    public String getAreaInfoByClause(int custId, String areaId)
    {
        List list = areaInfoDao.getAreaInfoByClause(custId);
        AreaInfo area = areaInfoDao.getAreaInfoById(areaId);
        return GetAreaInfoTree.getAreaInfoForGrid(list, area);
    }

    public String addAreaInfo(AreaInfo areaInfo)
    {
        try
        {
            areaInfoDao.addAreaInfo(areaInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }

    public String editAreaInfo(AreaInfo areaInfo)
    {
        try
        {
            areaInfoDao.editAreaInfo(areaInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "修改成功";
    }

    public String deleteAreaInfo(String areaId)
    {
        try
        {
            areaInfoDao.deleteAreaInfo(areaId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    public String changeAreaInfoRoot(String targetId, String sourceId)
    {
        try
        {
            areaInfoDao.changeAreaInfoRoot(targetId, sourceId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    public List getAreaInfoByCustId(int custId)
    {
        return areaInfoDao.getAreaInfoByClause(custId);
    }

    public String getAreaInfoNoRoot(int custId, String areaId)
    {
        List list = areaInfoDao.getAreaInfoByClause(custId);
        AreaInfo areaInfo = areaInfoDao.getAreaInfoById(areaId);
        return GetAreaInfoTree.getAreaInfoNoRoot(list, areaInfo);
    }

    public String getChildAreaInfoByAreaId(int custId, String areaId)
    {
        List list = areaInfoDao.getAreaInfoByClause(custId);
        AreaInfo areaInfo = areaInfoDao.getAreaInfoById(areaId);
        String res = GetAreaInfoTree.getChildRegionIds(list, areaInfo);
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public AreaInfo getAreaInfoById(String areaId)
    {
        return areaInfoDao.getAreaInfoById(areaId);
    }

    public List getReadUserListByCustId(int custId)
    {
        return areaInfoDao.getReadUserByCustId(custId);
    }

    public String addReadUser(ReadUser readUser)
    {
        try
        {
            areaInfoDao.addReadUser(readUser);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    public String editReadUser(ReadUser readUser)
    {
        try
        {
            areaInfoDao.editReadUser(readUser);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    public String deleteReadUser(String readUserId)
    {
        try
        {
            areaInfoDao.deleteReadUser(readUserId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    public List getAreaInfoByOrders(int custId, String orders)
    {
        return areaInfoDao.getAreaInfoByOrders(custId, orders);
    }

    public String editAreaMeterInfo(AreaInfo areaInfo)
    {
        try
        {
            areaInfoDao.editAreaMeterInfo(areaInfo);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "修改成功";
    }


}
