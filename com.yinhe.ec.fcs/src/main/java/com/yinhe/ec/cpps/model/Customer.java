// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Customer.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            BaseParam

public class Customer
{

    public Customer()
    {
    }

    public Customer(int custId, String custNo, String custName, String custAddr, String custPerson, String custPhone, int regFlag, 
            String machineCode, String regCode, String regStartDate, String regEndDate, String custLogo, String systemName, String custIp, 
            String createDate, String createUser, String remark)
    {
        this.custId = custId;
        this.custNo = custNo;
        this.custName = custName;
        this.custAddr = custAddr;
        this.custPerson = custPerson;
        this.custPhone = custPhone;
        this.regFlag = regFlag;
        this.machineCode = machineCode;
        this.regCode = regCode;
        this.regStartDate = regStartDate;
        this.regEndDate = regEndDate;
        this.custLogo = custLogo;
        this.systemName = systemName;
        this.custIp = custIp;
        this.createDate = createDate;
        this.createUser = createUser;
        this.remark = remark;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getCustNo()
    {
        return custNo;
    }

    public void setCustNo(String custNo)
    {
        this.custNo = custNo;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getCustAddr()
    {
        return custAddr;
    }

    public void setCustAddr(String custAddr)
    {
        this.custAddr = custAddr;
    }

    public String getCustPerson()
    {
        return custPerson;
    }

    public void setCustPerson(String custPerson)
    {
        this.custPerson = custPerson;
    }

    public String getCustPhone()
    {
        return custPhone;
    }

    public void setCustPhone(String custPhone)
    {
        this.custPhone = custPhone;
    }

    public int getRegFlag()
    {
        return regFlag;
    }

    public void setRegFlag(int regFlag)
    {
        this.regFlag = regFlag;
    }

    public String getMachineCode()
    {
        return machineCode;
    }

    public void setMachineCode(String machineCode)
    {
        this.machineCode = machineCode;
    }

    public String getRegCode()
    {
        return regCode;
    }

    public void setRegCode(String regCode)
    {
        this.regCode = regCode;
    }

    public String getRegStartDate()
    {
        return regStartDate;
    }

    public void setRegStartDate(String regStartDate)
    {
        this.regStartDate = regStartDate;
    }

    public String getRegEndDate()
    {
        return regEndDate;
    }

    public void setRegEndDate(String regEndDate)
    {
        this.regEndDate = regEndDate;
    }

    public String getCustLogo()
    {
        return custLogo;
    }

    public void setCustLogo(String custLogo)
    {
        this.custLogo = custLogo;
    }

    public String getSystemName()
    {
        return systemName;
    }

    public void setSystemName(String systemName)
    {
        this.systemName = systemName;
    }

    public String getCustIp()
    {
        return custIp;
    }

    public void setCustIp(String custIp)
    {
        this.custIp = custIp;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public BaseParam getBaseParam()
    {
        return baseParam;
    }

    public void setBaseParam(BaseParam baseParam)
    {
        this.baseParam = baseParam;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(String merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getMerchantName()
    {
        return merchantName;
    }

    public void setMerchantName(String merchantName)
    {
        this.merchantName = merchantName;
    }

    public String toString()
    {
        return (new StringBuilder("Customer [createDate=")).append(createDate).append(", createUser=").append(createUser).append(", custAddr=").append(custAddr).append(", custId=").append(custId).append(", custIp=").append(custIp).append(", custLogo=").append(custLogo).append(", custName=").append(custName).append(", custNo=").append(custNo).append(", custPerson=").append(custPerson).append(", custPhone=").append(custPhone).append(", machineCode=").append(machineCode).append(", regCode=").append(regCode).append(", regEndDate=").append(regEndDate).append(", regFlag=").append(regFlag).append(", regStartDate=").append(regStartDate).append(", remark=").append(remark).append(", systemName=").append(systemName).append("]").toString();
    }

    private int custId;
    private String custNo;
    private String custName;
    private String custAddr;
    private String custPerson;
    private String custPhone;
    private int regFlag;
    private String machineCode;
    private String regCode;
    private String regStartDate;
    private String regEndDate;
    private String custLogo;
    private String systemName;
    private String custIp;
    private String createDate;
    private String createUser;
    private String remark;
    private BaseParam baseParam;
    private String bankName;
    private String merchantId;
    private String merchantName;
}
