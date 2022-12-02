// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ManufactureController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.ManufactureService;
import com.yinhe.ec.cpps.model.Manufacture;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("厂家API")
@RestController
@RequestMapping("/yinhe/base")
public class ManufactureController
{
    @Autowired
    private ManufactureService manufactureService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public ManufactureController()
    {
    }

    /**
     * 查询厂家列表
     */
    @ApiOperation(value = "查询厂家列表", notes = "查询厂家列表", response = ApiResult.class)
    @PostMapping("/getManufacture")
    public List getManufacture(int custId)
    {
        return manufactureService.getManufacture(custId);
    }

    /**
     * 新增厂家
     */
    @ApiOperation(value = "新增厂家", notes = "新增厂家", response = ApiResult.class)
    @PostMapping("/addManufacture")
    public String addManufacture(@RequestBody Manufacture manufacture)
    {
        manufacture.setManuId(genPkIdservice.getPkNoForInt("MANUFACTURE", "ManuId"));
        return manufactureService.addManufacture(manufacture);
    }

    /**
     * 更新厂家
     */
    @ApiOperation(value = "更新厂家", notes = "更新厂家", response = ApiResult.class)
    @PostMapping("/modManufacture")
    public String modManufacture(@RequestBody Manufacture manufacture)
    {
        return manufactureService.modManufacture(manufacture);
    }

    /**
     * 删除厂家
     */
    @ApiOperation(value = "删除厂家", notes = "删除厂家", response = ApiResult.class)
    @PostMapping("/delManufacture")
    public String delManufacture(int manuId)
    {
        return manufactureService.delManufacture(manuId);
    }


}
