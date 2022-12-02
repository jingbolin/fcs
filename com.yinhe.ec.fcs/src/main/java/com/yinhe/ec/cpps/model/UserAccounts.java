// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserAccounts.java

package com.yinhe.ec.cpps.model;


public class UserAccounts
{

    public UserAccounts()
    {
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }

    public Double getBalance()
    {
        return balance;
    }

    public void setBalance(Double balance)
    {
        this.balance = balance;
    }

    public Double getDeposit()
    {
        return deposit;
    }

    public void setDeposit(Double deposit)
    {
        this.deposit = deposit;
    }

    public Double getCredit()
    {
        return credit;
    }

    public void setCredit(Double credit)
    {
        this.credit = credit;
    }

    public Double getBillBalance()
    {
        return billBalance;
    }

    public void setBillBalance(Double billBalance)
    {
        this.billBalance = billBalance;
    }

    public int getBankId()
    {
        return bankId;
    }

    public void setBankId(int bankId)
    {
        this.bankId = bankId;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getTaxNo()
    {
        return taxNo;
    }

    public void setTaxNo(String taxNo)
    {
        this.taxNo = taxNo;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private String userId;
    private int custId;
    private Double total;
    private Double balance;
    private Double deposit;
    private Double credit;
    private Double billBalance;
    private int bankId;
    private String accountName;
    private String accountNo;
    private String taxNo;
    private String remark;
}
