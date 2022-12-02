// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeviceResponse.java

package cmcc.iot.onenet.javasdk.response.device;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DeviceResponse
{
    public static class KeyItems
    {

        private String title;
        private String keystr;

        public KeyItems(String title, String keystr)
        {
            this.title = title;
            this.keystr = keystr;
        }
    }

    public static class Location
    {

        private double ele;
        private double lat;
        private double lon;

        public Location(double ele, double lat, double lon)
        {
            this.ele = ele;
            this.lat = lat;
            this.lon = lon;
        }
    }


    public DeviceResponse(String id, String title, String protocol, String desc, Date createTime, List tags, Location location, 
            Boolean isPrivate, Object authInfo, Map other, Integer interval, Boolean isonline, List keys)
    {
        this.id = id;
        this.title = title;
        this.protocol = protocol;
        this.desc = desc;
        this.createTime = createTime;
        this.tags = tags;
        this.location = location;
        this.isPrivate = isPrivate;
        this.authInfo = authInfo;
        this.other = other;
        this.interval = interval;
        this.isonline = isonline;
        this.keys = keys;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public List getTags()
    {
        return tags;
    }

    public void setTags(List tags)
    {
        this.tags = tags;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Boolean getIsPrivate()
    {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate)
    {
        this.isPrivate = isPrivate;
    }

    public String getRouteTo()
    {
        return routeTo;
    }

    public void setRouteTo(String routeTo)
    {
        this.routeTo = routeTo;
    }

    public Object getAuthInfo()
    {
        return authInfo;
    }

    public void setAuthInfo(Object authInfo)
    {
        this.authInfo = authInfo;
    }

    public Map getOther()
    {
        return other;
    }

    public void setOther(Map other)
    {
        this.other = other;
    }

    public Integer getInterval()
    {
        return interval;
    }

    public void setInterval(Integer interval)
    {
        this.interval = interval;
    }

    public Boolean getIsonline()
    {
        return isonline;
    }

    public void setIsonline(Boolean isonline)
    {
        this.isonline = isonline;
    }

    public List getKeys()
    {
        return keys;
    }

    public void setKeys(List keys)
    {
        this.keys = keys;
    }

    private String id;
    private String title;
    private String protocol;
    private String desc;
    private Date createTime;
    private List tags;
    private Location location;
    private Boolean isPrivate;
    private String routeTo;
    private Object authInfo;
    private Map other;
    private Integer interval;
    private Boolean isonline;
    private List keys;
}
