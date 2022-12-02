// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_WorkFlow.java

package com.yinhe.ec.cpps.model;


public class App_WorkFlow
{

    public App_WorkFlow()
    {
    }

    public String getWorkFlowId()
    {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId)
    {
        this.workFlowId = workFlowId;
    }

    public String getWorkFlowTitle()
    {
        return workFlowTitle;
    }

    public void setWorkFlowTitle(String workFlowTitle)
    {
        this.workFlowTitle = workFlowTitle;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public int getSort()
    {
        return sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    private String workFlowId;
    private String workFlowTitle;
    private String content;
    private String createDate;
    private int sort;
    private int custId;
}
