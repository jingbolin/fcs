// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FeeItemList.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            FeeType, StepPriceList, MixPriceList

public class FeeItemList
{

    public FeeItemList()
    {
        lateFeeId = "";
        lateFeeName = "";
        paymentMode = 2;
        allowpay = 0;
        cycleType = 12;
        cycleStartDate = "";
        cycleEndDate = "";
    }

    public String getFeeItemId()
    {
        return feeItemId;
    }

    public void setFeeItemId(String feeItemId)
    {
        this.feeItemId = feeItemId;
    }

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public int getChargeMethod()
    {
        return chargeMethod;
    }

    public void setChargeMethod(int chargeMethod)
    {
        this.chargeMethod = chargeMethod;
    }

    public String getFeeTypeId()
    {
        return feeTypeId;
    }

    public void setFeeTypeId(String feeTypeId)
    {
        this.feeTypeId = feeTypeId;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getAreaPrice()
    {
        return areaPrice;
    }

    public void setAreaPrice(Double areaPrice)
    {
        this.areaPrice = areaPrice;
    }

    public Double getBaseFee()
    {
        return baseFee;
    }

    public void setBaseFee(Double baseFee)
    {
        this.baseFee = baseFee;
    }

    public Double getPrice1()
    {
        return price1;
    }

    public void setPrice1(Double price1)
    {
        this.price1 = price1;
    }

    public Double getPrice2()
    {
        return price2;
    }

    public void setPrice2(Double price2)
    {
        this.price2 = price2;
    }

    public Double getPrice3()
    {
        return price3;
    }

    public void setPrice3(Double price3)
    {
        this.price3 = price3;
    }

    public String getStepPriceId()
    {
        return stepPriceId;
    }

    public void setStepPriceId(String stepPriceId)
    {
        this.stepPriceId = stepPriceId;
    }

    public String getMixPriceId()
    {
        return mixPriceId;
    }

    public void setMixPriceId(String mixPriceId)
    {
        this.mixPriceId = mixPriceId;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public FeeType getFeeType()
    {
        return feeType;
    }

    public void setFeeType(FeeType feeType)
    {
        this.feeType = feeType;
    }

    public StepPriceList getStepPrice()
    {
        return stepPrice;
    }

    public void setStepPrice(StepPriceList stepPrice)
    {
        this.stepPrice = stepPrice;
    }

    public MixPriceList getMixPrice()
    {
        return mixPrice;
    }

    public void setMixPrice(MixPriceList mixPrice)
    {
        this.mixPrice = mixPrice;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getRemark1()
    {
        return remark1;
    }

    public void setRemark1(String remark1)
    {
        this.remark1 = remark1;
    }

    public String getLateFeeId()
    {
        return lateFeeId;
    }

    public void setLateFeeId(String lateFeeId)
    {
        this.lateFeeId = lateFeeId;
    }

    public String getLateFeeName()
    {
        return lateFeeName;
    }

    public void setLateFeeName(String lateFeeName)
    {
        this.lateFeeName = lateFeeName;
    }

    public int getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode)
    {
        this.paymentMode = paymentMode;
    }

    public int getAllowpay()
    {
        return allowpay;
    }

    public void setAllowpay(int allowpay)
    {
        this.allowpay = allowpay;
    }

    public int getCycleType()
    {
        return cycleType;
    }

    public void setCycleType(int cycleType)
    {
        this.cycleType = cycleType;
    }

    public String getCycleStartDate()
    {
        return cycleStartDate;
    }

    public void setCycleStartDate(String cycleStartDate)
    {
        this.cycleStartDate = cycleStartDate;
    }

    public String getCycleEndDate()
    {
        return cycleEndDate;
    }

    public void setCycleEndDate(String cycleEndDate)
    {
        this.cycleEndDate = cycleEndDate;
    }

    private String feeItemId;
    private String feeItemName;
    private int custId;
    private int chargeMethod;
    private String feeTypeId;
    private Double price;
    private Double areaPrice;
    private Double baseFee;
    private Double price1;
    private Double price2;
    private Double price3;
    private String stepPriceId;
    private String mixPriceId;
    private String createDate;
    private String remark;
    private FeeType feeType;
    private StepPriceList stepPrice;
    private MixPriceList mixPrice;
    private String areaId;
    private String areaName;
    private String remark1;
    private String lateFeeId;
    private String lateFeeName;
    private int paymentMode;
    private int allowpay;
    private int cycleType;
    private String cycleStartDate;
    private String cycleEndDate;
}
