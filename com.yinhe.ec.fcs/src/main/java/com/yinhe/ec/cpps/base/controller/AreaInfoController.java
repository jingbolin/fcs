// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AreaInfoController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.AreaInfoService;
import com.yinhe.ec.cpps.model.AreaInfo;
import com.yinhe.ec.cpps.model.ReadUser;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("片区API")
@RestController
@RequestMapping("/yinhe/base")
public class AreaInfoController
{

    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public AreaInfoController()
    {
    }

    /**
     * 查询片区列表
     */
    @ApiOperation(value = "查询片区列表", notes = "查询片区列表", response = ApiResult.class)
    @PostMapping("/getAreaInfoByClause")
    public String getAreaInfoByClause(Integer custId, String areaId)
    {
        return areaInfoService.getAreaInfoByClause(custId, areaId);
    }

    /**
     * 片区新增
     */
    @ApiOperation(value = "片区新增", notes = "片区新增", response = ApiResult.class)
    @PostMapping("/addAreaInfo")
    public String addAreaInfo(@RequestBody AreaInfo areaInfo)
    {
        areaInfo.setAreaId(genPkIdservice.getPkNoByTable("AREAINFO", "AreaId", Integer.valueOf(4)));
        areaInfo.setCreateDate(Tools.getNow());
        return areaInfoService.addAreaInfo(areaInfo);
    }

    /**
     * 片区更新
     */
    @ApiOperation(value = "片区更新", notes = "片区更新", response = ApiResult.class)
    @PostMapping("/editAreaInfo")
    public String editAreaInfo(@RequestBody AreaInfo areaInfo)
    {
        return areaInfoService.editAreaInfo(areaInfo);
    }

    /**
     * 片区删除
     */
    @ApiOperation(value = "片区删除", notes = "片区删除", response = ApiResult.class)
    @PostMapping("/deleteAreaInfo")
    public String deleteAreaInfo(String areaId)
    {
        return areaInfoService.deleteAreaInfo(areaId);
    }


    public String changeAreaInfoRoot(String targetId, String sourceId)
    {
        return areaInfoService.changeAreaInfoRoot(targetId, sourceId);
    }

    public List getAreaInfoByCustId(int custId)
    {
        return areaInfoService.getAreaInfoByCustId(custId);
    }

    public String getAreaInfoNoRoot(int custId, String areaId)
    {
        return areaInfoService.getAreaInfoNoRoot(custId, areaId);
    }

    public List getReadUserListByCustId(int custId)
    {
        return areaInfoService.getReadUserListByCustId(custId);
    }

    public String addReadUser(ReadUser readUser)
    {
        readUser.setReadUserId(genPkIdservice.getPkNoByTable("ReadUser", "ReadUserId", Integer.valueOf(4)));
        readUser.setCreateDate(Tools.getNow());
        return areaInfoService.addReadUser(readUser);
    }

    public String editReadUser(ReadUser readUser)
    {
        return areaInfoService.editReadUser(readUser);
    }

    public String deleteReadUser(String readUserId)
    {
        return areaInfoService.deleteReadUser(readUserId);
    }

    public List getAreaInfoForFeeItem(int custId, String orders)
    {
        orders = (new StringBuilder(String.valueOf(orders))).append(" and areapid='0008'").toString();
        return areaInfoService.getAreaInfoByOrders(custId, orders);
    }

    public List getAreaInfoForBusiness(int custId, String orders)
    {
        orders = (new StringBuilder(String.valueOf(orders))).append(" and businessFlag=1 ").toString();
        return areaInfoService.getAreaInfoByOrders(custId, orders);
    }

    /**
     * 片区设置总表
     */
    @ApiOperation(value = "片区设置总表", notes = "片区设置总表", response = ApiResult.class)
    @PostMapping("/editAreaMeterInfo")
    public String editAreaMeterInfo(@RequestBody AreaInfo areaInfo)
    {
        return areaInfoService.editAreaMeterInfo(areaInfo);
    }


}
