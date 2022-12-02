// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MboxController.java

package com.yinhe.ec.cpps.base.controller;

import com.yinhe.ec.cpps.base.service.MboxService;
import com.yinhe.ec.cpps.model.MboxInfo;
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

@Api("表箱API")
@RestController
@RequestMapping("/yinhe/base")
public class MboxController
{

    @Autowired
    private MboxService mboxService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public MboxController()
    {
    }

    /**
     * 查询表箱列表
     */
    @ApiOperation(value = "查询表箱列表", notes = "查询表箱列表", response = ApiResult.class)
    @PostMapping("/getMboxInfo")
    public List getMboxInfo(int custId)
    {
        return mboxService.getMboxInfo(custId);
    }

    /**
     * 新增表箱
     */
    @ApiOperation(value = "新增表箱", notes = "新增表箱", response = ApiResult.class)
    @PostMapping("/addMboxInfo")
    public String addMboxInfo(@RequestBody MboxInfo mboxInfo)
    {
        mboxInfo.setMboxId(genPkIdservice.getPkNoForInt("MBOXINFO", "MboxId"));
        return mboxService.addMboxInfo(mboxInfo);
    }

    /**
     * 更新表箱
     */
    @ApiOperation(value = "更新表箱", notes = "更新表箱", response = ApiResult.class)
    @PostMapping("/editMboxInfo")
    public String editMboxInfo(@RequestBody MboxInfo mboxInfo)
    {
        return mboxService.editMboxInfo(mboxInfo);
    }

    /**
     * 删除表箱
     */
    @ApiOperation(value = "删除表箱", notes = "删除表箱", response = ApiResult.class)
    @PostMapping("/deleteMboxInfo")
    public String deleteMboxInfo(int mboxId)
    {
        return mboxService.deleteMboxInfo(mboxId);
    }

    /**
     * 查询表箱
     */
    @ApiOperation(value = "查询表箱", notes = "查询表箱", response = ApiResult.class)
    @PostMapping("/getMboxInfoById")
    public MboxInfo getMboxInfoById(int mboxId)
    {
        return mboxService.getMboxInfoById(mboxId);
    }


}
