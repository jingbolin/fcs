// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeTypeController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.FeeTypeService;
import com.yinhe.ec.cpps.model.FeeType;
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

@Api("收费性质API")
@RestController
@RequestMapping("/yinhe/base")
public class FeeTypeController
{

    @Autowired
    private FeeTypeService feeTypeService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public FeeTypeController()
    {
    }

    /**
     * 查询收费性质列表
     */
    @ApiOperation(value = "查询收费性质列表", notes = "查询收费性质列表", response = ApiResult.class)
    @PostMapping("/getAllFeeType")
    public List getAllFeeType()
    {
        return feeTypeService.getAllFeeType();
    }

    /**
     * 新增收费性质
     */
    @ApiOperation(value = "新增收费性质", notes = "新增收费性质", response = ApiResult.class)
    @PostMapping("/addFeeType")
    public String addFeeType(@RequestBody FeeType feeType)
    {
        feeType.setFeeTypeId(genPkIdservice.getPkNoByTable("FEETYPE", "FEETYPEID", Integer.valueOf(4)));
        return feeTypeService.addFeeType(feeType);
    }

    /**
     * 更新收费性质
     */
    @ApiOperation(value = "更新收费性质", notes = "更新收费性质", response = ApiResult.class)
    @PostMapping("/editFeeType")
    public String editFeeType(@RequestBody FeeType feeType)
    {
        return feeTypeService.editFeeType(feeType);
    }

    /**
     * 删除收费性质
     */
    @ApiOperation(value = "删除收费性质", notes = "删除收费性质", response = ApiResult.class)
    @PostMapping("/delFeeTypeById")
    public String delFeeTypeById(String feeTypeId)
    {
        return feeTypeService.delFeeTypeById(feeTypeId);
    }


}
