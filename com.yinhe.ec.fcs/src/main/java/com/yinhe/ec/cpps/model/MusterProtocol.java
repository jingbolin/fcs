// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MusterProtocol.java

package com.yinhe.ec.cpps.model;


public class MusterProtocol
{

    public MusterProtocol()
    {
    }

    public int getMusterPtl()
    {
        return musterPtl;
    }

    public void setMusterPtl(int musterPtl)
    {
        this.musterPtl = musterPtl;
    }

    public String getPtlName()
    {
        return ptlName;
    }

    public void setPtlName(String ptlName)
    {
        this.ptlName = ptlName;
    }

    public int getBatch()
    {
        return batch;
    }

    public void setBatch(int batch)
    {
        this.batch = batch;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int musterPtl;
    private String ptlName;
    private int batch;
    private String remark;
}
