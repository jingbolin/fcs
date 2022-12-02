// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MusterController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.base.service.AreaInfoService;
import com.yinhe.ec.cpps.model.ConnInfo;
import com.yinhe.ec.cpps.model.Muster;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.service.MusterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Api("通讯参数API")
@RestController
@RequestMapping("/yinhe/ycmeter")
public class MusterController
{

    @Autowired
    private MusterService musterService;
    @Autowired
    private ConnInfo connInfo;
    @Autowired
    private GenPkIdService genPkIdservice;
    @Autowired
    private AreaInfoService areaInfoService;

    public MusterController()
    {
    }

    /**
     * 查询通讯方式
     */
    @ApiOperation(value = "查询通讯方式", notes = "查询通讯方式", response = ApiResult.class)
    @PostMapping("/getConnStyle")
    public List getConnStyle()
    {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","串口");

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(map);
        return objects;
    }

    /**
     * 查询通讯参数列表
     */
    @ApiOperation(value = "查询通讯参数列表", notes = "查询通讯参数列表", response = ApiResult.class)
    @PostMapping("/getConnInfo")
    public List getConnInfo(int custId)
    {
        return musterService.getConnInfo(custId);
    }

    /**
     * 新增通讯参数
     */
    @ApiOperation(value = "新增通讯参数", notes = "新增通讯参数", response = ApiResult.class)
    @PostMapping("/addConnInfo")
    public String addConnInfo(Integer connStyle, String connPara, int custId)
        throws IllegalAccessException, InvocationTargetException
    {
        connInfo.setConnNo(genPkIdservice.getPkNoByTable("CONNINFO", "ConnNO", Integer.valueOf(4)));
        connInfo.setConnStyle(connStyle.intValue());
        connInfo.setConnPara(connPara);
        connInfo.setCustId(custId);
        return musterService.addConnInfo(connInfo);
    }

    /**
     * 更新通讯参数
     */
    @ApiOperation(value = "更新通讯参数", notes = "更新通讯参数", response = ApiResult.class)
    @PostMapping("/modConnInfo")
    public String modConnInfo(String connNo, Integer connStyle, String connPara)
        throws IllegalAccessException, InvocationTargetException
    {
        connInfo.setConnNo(connNo);
        connInfo.setConnStyle(connStyle.intValue());
        connInfo.setConnPara(connPara);
        return musterService.modConnInfo(connInfo);
    }

    /**
     * 删除通讯参数
     */
    @ApiOperation(value = "删除通讯参数", notes = "删除通讯参数", response = ApiResult.class)
    @PostMapping("/delConnInfoByNo")
    public String delConnInfoByNo(String connNo)
    {
        return musterService.delConnInfoByNo(connNo);
    }

    /**
     * 查询集中器列表
     */
    @ApiOperation(value = "查询集中器列表", notes = "查询集中器列表", response = ApiResult.class)
    @PostMapping("/getMusterList")
    public List getMusterList(int custId, String areaId)
    {
        String orders = "";
        String areaIds = "";
        areaIds = areaInfoService.getChildAreaInfoByAreaId(custId, areaId);
        orders = (new StringBuilder(" and a.areaId in (")).append(areaIds).append(")").toString();
        return musterService.getMusterList(orders);
    }

    /**
     * 新增集中器
     */
    @ApiOperation(value = "新增集中器", notes = "新增集中器", response = ApiResult.class)
    @PostMapping("/addMuster")
    public String addMuster(@RequestBody Muster muster)
    {
        muster.setCreateDate(Tools.getNow());
        if(muster.getMusterNo().length() == 8 && "99".equals(muster.getMusterNo().substring(0, 2)))
        {
            muster.setInitFlag(1);
            muster.setOnlineState(1);
        } else
        {
            muster.setInitFlag(0);
            muster.setOnlineState(0);
        }
        return musterService.addMuster(muster);
    }

    /**
     * 更新集中器
     */
    @ApiOperation(value = "更新集中器", notes = "更新集中器", response = ApiResult.class)
    @PostMapping("/modMuster")
    public String modMuster(@RequestBody Muster muster)
    {
        return musterService.modMuster(muster);
    }

    /**
     * 删除集中器
     */
    @ApiOperation(value = "删除集中器", notes = "删除集中器", response = ApiResult.class)
    @PostMapping("/delMusterByNo")
    public String delMusterByNo(String musterNo)
    {
        return musterService.delMusterByNo(musterNo);
    }

    public String changeMuster(String oldMusterNo, String newMusterNo, int operatorId, int changeFlag)
    {
        return musterService.changeMuster(oldMusterNo, newMusterNo, operatorId, changeFlag);
    }

    public List getMeterListByMusterNo(String musterNo)
    {
        return musterService.getMeterListByMusterNo(musterNo);
    }

    public List getMusterOnlineInfo()
    {
        return musterService.getMusterOnlineInfo();
    }

    public List getMusterProtocol()
    {
        return musterService.getMusterProtocol();
    }

    public List getMeterProtocol()
    {
        return musterService.getMeterProtocol();
    }

    public String getXuniMusterNo()
    {
        return musterService.getXuniMusterNo();
    }


}
