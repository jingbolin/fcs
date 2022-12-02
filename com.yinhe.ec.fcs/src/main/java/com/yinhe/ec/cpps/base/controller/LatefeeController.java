// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LatefeeController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.LatefeeService;
import com.yinhe.ec.cpps.model.LateFeeList;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("违约金API")
@RestController
@RequestMapping("/yinhe/base")
public class LatefeeController
{

    @Autowired
    private LatefeeService latefeeService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public LatefeeController()
    {
    }

    /**
     * 查询违约金列表
     */
    @ApiOperation(value = "查询违约金列表", notes = "查询违约金列表", response = ApiResult.class)
    @PostMapping("/getLateFeeListByCustId")
    public List getLateFeeListByCustId(int custId, String orders)
    {
        return latefeeService.getLateFeeListByCustId(custId, orders);
    }

    /**
     * 新增违约金
     */
    @ApiOperation(value = "新增违约金", notes = "新增违约金", response = ApiResult.class)
    @PostMapping("/addLateFeeList")
    public String addLateFeeList(LateFeeList lateFeeList)
    {
        lateFeeList.setLateFeeId(genPkIdservice.getPkNoByTable("LATEFEELIST", "LATEFEEID", Integer.valueOf(4)));
        lateFeeList.setCreateDate(Tools.getNow());
        return latefeeService.addLateFeeList(lateFeeList);
    }

    /**
     * 更新违约金
     */
    @ApiOperation(value = "更新违约金", notes = "更新违约金", response = ApiResult.class)
    @PostMapping("/modLateFeeList")
    public String modLateFeeList(LateFeeList lateFeeList)
    {
        return latefeeService.modLateFeeList(lateFeeList);
    }

    /**
     * 删除违约金
     */
    @ApiOperation(value = "删除违约金", notes = "删除违约金", response = ApiResult.class)
    @PostMapping("/delLateFeeListById")
    public String delLateFeeListById(String lateFeeId)
    {
        return latefeeService.delLateFeeListById(lateFeeId);
    }


}
