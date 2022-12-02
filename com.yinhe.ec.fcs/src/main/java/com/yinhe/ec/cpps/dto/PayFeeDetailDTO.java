// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PayFeeDetailDTO.java

package com.yinhe.ec.cpps.dto;


public class PayFeeDetailDTO
{

    public PayFeeDetailDTO()
    {
    }

    public PayFeeDetailDTO(String feeNo, String userId, String operatorId, String payDt, Double totalFee, Double payFee, Double lastBalance, 
            Double leftBalance, Integer printFlag, String seqId, String feeItem, Integer payStyle, Double lastDosageSum, Double thisDosageSum, 
            Double quantity, Double total, String userName, String userAddr, String itemDesc, String operatorName)
    {
        this.feeNo = feeNo;
        this.userId = userId;
        this.operatorId = operatorId;
        this.payDt = payDt;
        this.totalFee = totalFee;
        this.payFee = payFee;
        this.lastBalance = lastBalance;
        this.leftBalance = leftBalance;
        this.printFlag = printFlag;
        this.seqId = seqId;
        this.feeItem = feeItem;
        this.payStyle = payStyle;
        this.lastDosageSum = lastDosageSum;
        this.thisDosageSum = thisDosageSum;
        this.quantity = quantity;
        this.total = total;
        this.userName = userName;
        this.userAddr = userAddr;
        this.itemDesc = itemDesc;
        this.operatorName = operatorName;
    }

    public PayFeeDetailDTO(String feeNo, String userId, String operatorId, String payDt, Integer payType, Double totalFee, Double lateFee, 
            Double preferentialFee, Double jianMianFee, Double payAmount, Double changeAmount, Double payFee, Double lastBalance, Double leftBalance, 
            String billNo, Integer printFlag, String printDt, String seqId, String feeItem, String markDt, Integer payStyle, 
            String meterNo, Double lastDosageSum, Double thisDosageSum, Double quantity, Double price, Double total, String userName, 
            String userAddr, String itemDesc, String operatorName)
    {
        this.feeNo = feeNo;
        this.userId = userId;
        this.operatorId = operatorId;
        this.payDt = payDt;
        this.payType = payType;
        this.totalFee = totalFee;
        this.lateFee = lateFee;
        this.preferentialFee = preferentialFee;
        this.jianMianFee = jianMianFee;
        this.payAmount = payAmount;
        this.changeAmount = changeAmount;
        this.payFee = payFee;
        this.lastBalance = lastBalance;
        this.leftBalance = leftBalance;
        this.billNo = billNo;
        this.printFlag = printFlag;
        this.printDt = printDt;
        this.seqId = seqId;
        this.feeItem = feeItem;
        this.markDt = markDt;
        this.payStyle = payStyle;
        this.meterNo = meterNo;
        this.lastDosageSum = lastDosageSum;
        this.thisDosageSum = thisDosageSum;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.userName = userName;
        this.userAddr = userAddr;
        this.itemDesc = itemDesc;
        this.operatorName = operatorName;
    }

    public String getFeeNo()
    {
        return feeNo;
    }

    public void setFeeNo(String feeNo)
    {
        this.feeNo = feeNo;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(String operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getPayDt()
    {
        return payDt;
    }

    public void setPayDt(String payDt)
    {
        this.payDt = payDt;
    }

    public Integer getPayType()
    {
        return payType;
    }

    public void setPayType(Integer payType)
    {
        this.payType = payType;
    }

    public Double getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(Double totalFee)
    {
        this.totalFee = totalFee;
    }

    public Double getLateFee()
    {
        return lateFee;
    }

    public void setLateFee(Double lateFee)
    {
        this.lateFee = lateFee;
    }

    public Double getPreferentialFee()
    {
        return preferentialFee;
    }

    public void setPreferentialFee(Double preferentialFee)
    {
        this.preferentialFee = preferentialFee;
    }

    public Double getJianMianFee()
    {
        return jianMianFee;
    }

    public void setJianMianFee(Double jianMianFee)
    {
        this.jianMianFee = jianMianFee;
    }

    public Double getPayAmount()
    {
        return payAmount;
    }

    public void setPayAmount(Double payAmount)
    {
        this.payAmount = payAmount;
    }

    public Double getChangeAmount()
    {
        return changeAmount;
    }

    public void setChangeAmount(Double changeAmount)
    {
        this.changeAmount = changeAmount;
    }

    public Double getPayFee()
    {
        return payFee;
    }

    public void setPayFee(Double payFee)
    {
        this.payFee = payFee;
    }

    public Double getLastBalance()
    {
        return lastBalance;
    }

    public void setLastBalance(Double lastBalance)
    {
        this.lastBalance = lastBalance;
    }

    public Double getLeftBalance()
    {
        return leftBalance;
    }

    public void setLeftBalance(Double leftBalance)
    {
        this.leftBalance = leftBalance;
    }

    public String getBillNo()
    {
        return billNo;
    }

    public void setBillNo(String billNo)
    {
        this.billNo = billNo;
    }

    public Integer getPrintFlag()
    {
        return printFlag;
    }

    public void setPrintFlag(Integer printFlag)
    {
        this.printFlag = printFlag;
    }

    public String getPrintDt()
    {
        return printDt;
    }

    public void setPrintDt(String printDt)
    {
        this.printDt = printDt;
    }

    public String getSeqId()
    {
        return seqId;
    }

    public void setSeqId(String seqId)
    {
        this.seqId = seqId;
    }

    public String getFeeItem()
    {
        return feeItem;
    }

    public void setFeeItem(String feeItem)
    {
        this.feeItem = feeItem;
    }

    public String getMarkDt()
    {
        return markDt;
    }

    public void setMarkDt(String markDt)
    {
        this.markDt = markDt;
    }

    public Integer getPayStyle()
    {
        return payStyle;
    }

    public void setPayStyle(Integer payStyle)
    {
        this.payStyle = payStyle;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public Double getLastDosageSum()
    {
        return lastDosageSum;
    }

    public void setLastDosageSum(Double lastDosageSum)
    {
        this.lastDosageSum = lastDosageSum;
    }

    public Double getThisDosageSum()
    {
        return thisDosageSum;
    }

    public void setThisDosageSum(Double thisDosageSum)
    {
        this.thisDosageSum = thisDosageSum;
    }

    public Double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Double quantity)
    {
        this.quantity = quantity;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserAddr()
    {
        return userAddr;
    }

    public void setUserAddr(String userAddr)
    {
        this.userAddr = userAddr;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    private String feeNo;
    private String userId;
    private String operatorId;
    private String payDt;
    private Integer payType;
    private Double totalFee;
    private Double lateFee;
    private Double preferentialFee;
    private Double jianMianFee;
    private Double payAmount;
    private Double changeAmount;
    private Double payFee;
    private Double lastBalance;
    private Double leftBalance;
    private String billNo;
    private Integer printFlag;
    private String printDt;
    private String seqId;
    private String feeItem;
    private String markDt;
    private Integer payStyle;
    private String meterNo;
    private Double lastDosageSum;
    private Double thisDosageSum;
    private Double quantity;
    private Double price;
    private Double total;
    private String userName;
    private String userAddr;
    private String itemDesc;
    private String operatorName;
}
