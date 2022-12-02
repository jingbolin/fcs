// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorLog.java

package com.yinhe.ec.cpps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

// Referenced classes of package com.yinhe.ec.cpps.model:
//            Operator
@TableName("OPERATORLOG")
@ApiModel(value = "OperatorLog对象", description = "登录日志")
public class OperatorLog
{

    public OperatorLog()
    {
    }

    public String getOlId()
    {
        return olId;
    }

    public void setOlId(String olId)
    {
        this.olId = olId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(String operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getOlDate()
    {
        return olDate;
    }

    public void setOlDate(String olDate)
    {
        this.olDate = olDate;
    }

    public String getOlFmName()
    {
        return olFmName;
    }

    public void setOlFmName(String olFmName)
    {
        this.olFmName = olFmName;
    }

    public String getOlDesc()
    {
        return olDesc;
    }

    public void setOlDesc(String olDesc)
    {
        this.olDesc = olDesc;
    }

    public String getOlIp()
    {
        return olIp;
    }

    public void setOlIp(String olIp)
    {
        this.olIp = olIp;
    }

    public String getOlArea()
    {
        return olArea;
    }

    public void setOlArea(String olArea)
    {
        this.olArea = olArea;
    }

    public String getOlRemark()
    {
        return olRemark;
    }

    public void setOlRemark(String olRemark)
    {
        this.olRemark = olRemark;
    }

    public Operator getOperator()
    {
        return operator;
    }

    public void setOperator(Operator operator)
    {
        this.operator = operator;
    }

    private String olId;
    private int custId;
    private String operatorId;
    private String olDate;
    private String olFmName;
    private String olDesc;
    private String olIp;
    private String olArea;
    private String olRemark;
    private Operator operator;
}
