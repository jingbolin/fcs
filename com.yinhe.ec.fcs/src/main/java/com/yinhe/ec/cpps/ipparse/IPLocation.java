// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IPLocation.java

package com.yinhe.ec.cpps.ipparse;


public class IPLocation
{

    public IPLocation()
    {
        country = area = "";
    }

    public IPLocation getCopy()
    {
        IPLocation ret = new IPLocation();
        ret.country = country;
        ret.area = area;
        return ret;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        if(area.trim().equals("CZ88.NET"))
            this.area = "\u672C\u673A\u6216\u672C\u7F51\u7EDC";
        else
            this.area = area;
    }

    private String country;
    private String area;
}
