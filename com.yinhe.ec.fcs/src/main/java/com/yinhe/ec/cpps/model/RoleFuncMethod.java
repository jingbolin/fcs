// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RoleFuncMethod.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            OperRole, FuncMethod

public class RoleFuncMethod
{

    public RoleFuncMethod()
    {
    }

    public String getFmId()
    {
        return fmId;
    }

    public void setFmId(String fmId)
    {
        this.fmId = fmId;
    }

    public String getOrId()
    {
        return orId;
    }

    public void setOrId(String orId)
    {
        this.orId = orId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getRfmStatus()
    {
        return rfmStatus;
    }

    public void setRfmStatus(String rfmStatus)
    {
        this.rfmStatus = rfmStatus;
    }

    public String getRfmRemark()
    {
        return rfmRemark;
    }

    public void setRfmRemark(String rfmRemark)
    {
        this.rfmRemark = rfmRemark;
    }

    public OperRole getOperRole()
    {
        return operRole;
    }

    public void setOperRole(OperRole operRole)
    {
        this.operRole = operRole;
    }

    public FuncMethod getFuncMethod()
    {
        return funcMethod;
    }

    public void setFuncMethod(FuncMethod funcMethod)
    {
        this.funcMethod = funcMethod;
    }

    private String fmId;
    private String orId;
    private int custId;
    private String rfmStatus;
    private String rfmRemark;
    private OperRole operRole;
    private FuncMethod funcMethod;
}
