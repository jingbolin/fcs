// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeItemListController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.FeeItemListService;
import com.yinhe.ec.cpps.model.FeeItemList;
import com.yinhe.ec.cpps.model.StepPriceList;
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

@Api("收费项目API")
@RestController
@RequestMapping("/yinhe/base")
public class FeeItemListController
{

    @Autowired
    private FeeItemListService feeItemListService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public FeeItemListController()
    {
    }

    /**
     * 查询收费项目列表
     */
    @ApiOperation(value = "查询收费项目列表", notes = "查询收费项目列表", response = ApiResult.class)
    @PostMapping("/getFeeItemListByCustId")
    public List getFeeItemListByCustId(int custId, String orders)
    {
        return feeItemListService.getFeeItemListByCustId(custId, orders);
    }

    /**
     * 新增收费项目
     */
    @ApiOperation(value = "新增收费项目", notes = "新增收费项目", response = ApiResult.class)
    @PostMapping("/addFeeItemList")
    public String addFeeItemList(@RequestBody FeeItemList feeItemList)
    {
        feeItemList.setFeeItemId(genPkIdservice.getPkNoByTable("FEEITEMLIST", "FeeItemId", Integer.valueOf(4)));
        feeItemList.setCreateDate(Tools.getNow());
        if(feeItemList.getPrice1() == null)
            feeItemList.setPrice1(Double.valueOf(0.0D));
        if(feeItemList.getPrice2() == null)
            feeItemList.setPrice2(Double.valueOf(0.0D));
        if(feeItemList.getPrice3() == null)
            feeItemList.setPrice3(Double.valueOf(0.0D));
        return feeItemListService.addFeeItemList(feeItemList);
    }

    /**
     * 更新收费项目
     */
    @ApiOperation(value = "更新收费项目", notes = "更新收费项目", response = ApiResult.class)
    @PostMapping("/editFeeItemList")
    public String editFeeItemList(@RequestBody FeeItemList feeItemList)
    {
        if(feeItemList.getPrice1() == null)
            feeItemList.setPrice1(Double.valueOf(0.0D));
        if(feeItemList.getPrice2() == null)
            feeItemList.setPrice2(Double.valueOf(0.0D));
        if(feeItemList.getPrice3() == null)
            feeItemList.setPrice3(Double.valueOf(0.0D));
        return feeItemListService.editFeeItemList(feeItemList);
    }

    /**
     * 更新收费项目
     */
    @ApiOperation(value = "删除收费项目", notes = "删除收费项目", response = ApiResult.class)
    @PostMapping("/delFeeItemListById")
    public String delFeeItemListById(String feeItemId)
    {
        return feeItemListService.delFeeItemListById(feeItemId);
    }

    public List getStepPriceList(int custId)
    {
        return feeItemListService.getStepPriceList(custId);
    }

    public String addStepPriceList(StepPriceList stepPriceList)
    {
        stepPriceList.setStepPriceId(genPkIdservice.getPkNoByTable("STEPPRICELIST", "STEPPRICEID", Integer.valueOf(4)));
        stepPriceList.setStepDate(Tools.getNow());
        return feeItemListService.addStepPriceList(stepPriceList);
    }

    public String editStepPriceList(StepPriceList stepPriceList)
    {
        return feeItemListService.editStepPriceList(stepPriceList);
    }

    public String delStepPriceList(String stepId)
    {
        return feeItemListService.delStepPriceList(stepId);
    }

    public List getPayWays()
    {
        return feeItemListService.getPayWays();
    }


}
