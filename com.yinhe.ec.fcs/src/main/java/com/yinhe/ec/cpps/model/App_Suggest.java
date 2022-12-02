// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App_Suggest.java

package com.yinhe.ec.cpps.model;

import java.util.ArrayList;
import java.util.List;

public class App_Suggest
{

    public App_Suggest()
    {
        suggestId = "";
        suggestContent = "";
        suggestDate = "";
        suggestPid = "";
        regId = "";
        phoneNo = "";
        pictures = "";
        suggestType = 0;
        reSuggest = new ArrayList();
    }

    public String getSuggestId()
    {
        return suggestId;
    }

    public void setSuggestId(String suggestId)
    {
        this.suggestId = suggestId;
    }

    public String getSuggestContent()
    {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent)
    {
        this.suggestContent = suggestContent;
    }

    public String getSuggestDate()
    {
        return suggestDate;
    }

    public void setSuggestDate(String suggestDate)
    {
        this.suggestDate = suggestDate;
    }

    public String getSuggestPid()
    {
        return suggestPid;
    }

    public void setSuggestPid(String suggestPid)
    {
        this.suggestPid = suggestPid;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getPictures()
    {
        return pictures;
    }

    public void setPictures(String pictures)
    {
        this.pictures = pictures;
    }

    public int getSuggestType()
    {
        return suggestType;
    }

    public void setSuggestType(int suggestType)
    {
        this.suggestType = suggestType;
    }

    public List getReSuggest()
    {
        return reSuggest;
    }

    public void setReSuggest(List reSuggest)
    {
        this.reSuggest = reSuggest;
    }

    private String suggestId;
    private String suggestContent;
    private String suggestDate;
    private String suggestPid;
    private String regId;
    private String phoneNo;
    private String pictures;
    private int suggestType;
    List reSuggest;
}
