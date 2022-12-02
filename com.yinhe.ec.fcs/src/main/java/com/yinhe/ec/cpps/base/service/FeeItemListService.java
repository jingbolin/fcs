// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeItemListService.java

package com.yinhe.ec.cpps.base.service;

import com.yinhe.ec.cpps.base.dao.FeeItemListDao;
import com.yinhe.ec.cpps.model.FeeItemList;
import com.yinhe.ec.cpps.model.StepPriceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeItemListService
{

    @Autowired
    private FeeItemListDao feeItemListDao;

    public FeeItemListService()
    {
    }

    public List getFeeItemListByCustId(int custId, String orders)
    {
        return feeItemListDao.getFeeItemListByCustId(custId, orders);
    }

    public String addFeeItemList(FeeItemList feeItemList)
    {
        try
        {
            feeItemListDao.addFeeItemList(feeItemList);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "新增成功";
    }

    public String editFeeItemList(FeeItemList feeItemList)
    {
        try
        {
            feeItemListDao.editFeeItemList(feeItemList);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    public String delFeeItemListById(String feeItemId)
    {
        if(feeItemListDao.getFeeItemIsused(feeItemId))
            return "项目被使用，无法删除";
        try
        {
            feeItemListDao.delFeeItemListById(feeItemId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    public FeeItemList getFeeItemList(String feeItemId)
    {
        return feeItemListDao.getFeeItemList(feeItemId);
    }

    public List getStepPriceList(int custId)
    {
        return feeItemListDao.getStepPriceList(custId);
    }

    public StepPriceList getStepPrice(String stepId)
    {
        return feeItemListDao.getStepPrice(stepId);
    }

    public String addStepPriceList(StepPriceList stepPriceList)
    {
        try
        {
            feeItemListDao.addStepPriceList(stepPriceList);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u65B0\u589E\u6210\u529F\uFF01";
    }

    public String editStepPriceList(StepPriceList stepPriceList)
    {
        if(feeItemListDao.getStepPriceIsused(stepPriceList.getStepPriceId()))
            return "\u8BE5\u9636\u68AF\u53C2\u6570\u5DF2\u7ECF\u4F7F\u7528\uFF0C\u4E0D\u5141\u8BB8\u4FEE\u6539\uFF01";
        try
        {
            feeItemListDao.editStepPriceList(stepPriceList);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u4FEE\u6539\u6210\u529F\uFF01";
    }

    public String delStepPriceList(String stepId)
    {
        if(feeItemListDao.getStepPriceIsused(stepId))
            return "\u8BE5\u9636\u68AF\u53C2\u6570\u5DF2\u7ECF\u4F7F\u7528\uFF0C\u4E0D\u5141\u8BB8\u5220\u9664\uFF01";
        try
        {
            feeItemListDao.delStepPriceList(stepId);
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "\u5220\u9664\u6210\u529F\uFF01";
    }

    public List getPayWays()
    {
        return feeItemListDao.getPayWays();
    }


}
