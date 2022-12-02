// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustController.java

package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.model.Customer;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.system.service.CustService;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api("客户API")
@RestController
@RequestMapping("/yinhe/systemManager")
public class CustController
{

    @Autowired
    private CustService custService;
    @Autowired
    private GenPkIdService genPkIdservice;

    public CustController()
    {
    }

    /**
     * 查询客户
     */
    @ApiOperation(value = "查询客户", notes = "查询客户", response = ApiResult.class)
    @PostMapping("/getCustomerInfo")
    public List getCustomerInfo(int custId)
    {
        return custService.getCustomerInfo(custId);
    }

    /**
     * 新增客户
     */
    @ApiOperation(value = "新增客户", notes = "新增客户", response = ApiResult.class)
    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer)
    {
        customer.setCustId(genPkIdservice.getPkNoForInt("CUSTOMER", "CustID"));
        customer.setCreateDate(Tools.getNow());
        if(customer.getRemark() == null || "".equals(customer.getRemark()))
            customer.setRemark("客户介绍");
        return custService.addCustomer(customer);
    }

    /**
     * 更新客户
     */
    @ApiOperation(value = "更新客户", notes = "更新客户", response = ApiResult.class)
    @PostMapping("/modCustomer")
    public String modCustomer(@RequestBody Customer customer)
    {
        if(customer.getRemark() == null || "".equals(customer.getRemark()))
            customer.setRemark("客户介绍");
        return custService.modCustomer(customer);
    }

    /**
     * 删除客户
     */
    @ApiOperation(value = "删除客户", notes = "删除客户", response = ApiResult.class)
    @PostMapping("/delCustomerById")
    public String delCustomerById(int custId)
    {
        return custService.delCustomerById(custId);
    }


    public Customer getCustomerById(int custId)
    {
        return custService.getCustomerById(custId);
    }

    public Customer getCustomerByNo(String custNo)
    {
        return custService.getCustomerByNo(custNo);
    }

    /**
     * 注册客户
     */
    @ApiOperation(value = "注册客户", notes = "注册客户", response = ApiResult.class)
    @PostMapping("/registerInfo")
    public void registerInfo(int custId, String regCode, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        response.setContentType("text/text");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(custService.registerInfo(custId, regCode));
    }

    /**
     * 客户注册2
     */
    @ApiOperation(value = "客户注册2", notes = "客户注册2", response = ApiResult.class)
    @PostMapping("/regCustomerInfo")
    public void regCustomerInfo(int custId, int cycle, int operatorId, String pwd, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        response.setContentType("text/text");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(custService.regCustomerInfo(custId, cycle, operatorId, pwd));
    }

}
