// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubInfoController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.SubInfoService;
import com.yinhe.ec.cpps.model.SubInfo;
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

@Api("台变API")
@RestController
@RequestMapping("/yinhe/base")
public class SubInfoController
{

    @Autowired
    private SubInfoService subInfoService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public SubInfoController()
    {

    }

    /**
     * 查询台变列表
     */
    @ApiOperation(value = "查询台变列表", notes = "查询台变列表", response = ApiResult.class)
    @PostMapping("/getSubInfoByCustId")
    public List getSubInfoByCustId(int custId)
    {
        return subInfoService.getSubInfoByCustId(custId);
    }

    /**
     * 新增台变
     */
    @ApiOperation(value = "新增台变", notes = "新增台变", response = ApiResult.class)
    @PostMapping("/addSubInfo")
    public String addSubInfo(@RequestBody SubInfo subInfo)
    {
        subInfo.setSubNo(genPkIdservice.getPkNoByTable("SUBINFO", "SubNo", Integer.valueOf(6)));
        subInfo.setSubDate(Tools.getNow());
        return subInfoService.addSubInfo(subInfo);
    }

    /**
     * 更新台变
     */
    @ApiOperation(value = "更新台变", notes = "更新台变", response = ApiResult.class)
    @PostMapping("/editSubInfo")
    public String editSubInfo(@RequestBody SubInfo subInfo)
    {
        return subInfoService.editSubInfo(subInfo);
    }

    /**
     * 删除台变
     */
    @ApiOperation(value = "删除台变", notes = "删除台变", response = ApiResult.class)
    @PostMapping("/delSubInfoById")
    public String delSubInfoById(String subNo)
    {
        return subInfoService.delSubInfoById(subNo);
    }


}
