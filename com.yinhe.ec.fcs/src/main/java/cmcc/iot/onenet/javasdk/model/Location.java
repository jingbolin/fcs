// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Location.java

package cmcc.iot.onenet.javasdk.model;

import java.util.HashMap;
import java.util.Map;

public class Location
{

    public Location(double ele, double lat, double lon, boolean eleSet)
    {
        this.ele = ele;
        this.lat = lat;
        this.lon = lon;
        this.eleSet = eleSet;
    }

    public Map toMap()
    {
        Map result = new HashMap();
        if(eleSet)
            result.put("ele", Double.valueOf(ele));
        result.put("lon", Double.valueOf(lon));
        result.put("lat", Double.valueOf(lat));
        result.put("ele", Double.valueOf(ele));
        return result;
    }

    public Location(double lon, double lat, double ele)
    {
        this.ele = ele;
        this.lat = lat;
        this.lon = lon;
    }

    public double getEle()
    {
        return ele;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }

    private double ele;
    private double lat;
    private double lon;
    private boolean eleSet;
}
