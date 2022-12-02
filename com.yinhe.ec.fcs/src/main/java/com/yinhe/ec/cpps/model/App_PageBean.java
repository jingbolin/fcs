// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_PageBean.java

package com.yinhe.ec.cpps.model;

import java.util.List;

public class App_PageBean
{

    public App_PageBean()
    {
    }

    public App_PageBean(Integer currentPage, Integer pageSize, Integer totalNum, List list)
    {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        totalPage = Integer.valueOf(totalNum.intValue() % pageSize.intValue() != 0 ? totalNum.intValue() / pageSize.intValue() + 1 : totalNum.intValue() / pageSize.intValue());
        this.list = list;
    }

    public Integer getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum)
    {
        this.totalNum = totalNum;
    }

    public Integer getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }

    public List getList()
    {
        return list;
    }

    public void setList(List list)
    {
        this.list = list;
    }

    private Integer currentPage;
    private Integer pageSize;
    private Integer totalNum;
    private Integer totalPage;
    private List list;
}
