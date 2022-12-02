// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeviceList.java

package cmcc.iot.onenet.javasdk.response.device;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DeviceList
{
    public static class DeviceItem
    {
        public static class Location
        {

            public double getEle()
            {
                return ele;
            }

            public void setEle(double ele)
            {
                this.ele = ele;
            }

            public double getLat()
            {
                return lat;
            }

            public void setLat(double lat)
            {
                this.lat = lat;
            }

            public double getLon()
            {
                return lon;
            }

            public void setLon(double lon)
            {
                this.lon = lon;
            }

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


        public Location getLocation()
        {
            return location;
        }

        public void setLocation(Location location)
        {
            this.location = location;
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

        public Boolean getIsPrivate()
        {
            return isPrivate;
        }

        public void setIsPrivate(Boolean isPrivate)
        {
            this.isPrivate = isPrivate;
        }

        public Object getAuthInfo()
        {
            return authInfo;
        }

        public void setAuthInfo(Object authInfo)
        {
            this.authInfo = authInfo;
        }

        public Boolean getIsonline()
        {
            return isonline;
        }

        public void setIsonline(Boolean isonline)
        {
            this.isonline = isonline;
        }

        public Date getCreateTime()
        {
            return createTime;
        }

        public void setCreateTime(Date createTime)
        {
            this.createTime = createTime;
        }

        public String getRgid()
        {
            return rgid;
        }

        public void setRgid(String rgid)
        {
            this.rgid = rgid;
        }

        public Integer getInterval()
        {
            return interval;
        }

        public void setInterval(Integer interval)
        {
            this.interval = interval;
        }

        public List getTags()
        {
            return tags;
        }

        public void setTags(List tags)
        {
            this.tags = tags;
        }

        public Map getOther()
        {
            return other;
        }

        public void setOther(Map other)
        {
            this.other = other;
        }

        private String id;
        private String title;
        private String protocol;
        private String desc;
        private Boolean isPrivate;
        private Object authInfo;
        private Boolean isonline;
        private Date createTime;
        private Location location;
        private String rgid;
        private Integer interval;
        private List tags;
        private Map other;

        public DeviceItem(String id, String title, String protocol, String desc, Boolean isPrivate, Object authInfo, Boolean isonline, 
                Date createTime, Location location, String rgid, Integer interval, List tags, Map other)
        {
            this.id = id;
            this.title = title;
            this.protocol = protocol;
            this.desc = desc;
            this.isPrivate = isPrivate;
            this.authInfo = authInfo;
            this.isonline = isonline;
            this.createTime = createTime;
            this.location = location;
            this.rgid = rgid;
            this.interval = interval;
            this.tags = tags;
            this.other = other;
        }
    }


    public DeviceList(int totalcount, int perpage, int page, ArrayList devices)
    {
        this.totalcount = totalcount;
        this.perpage = perpage;
        this.page = page;
        this.devices = devices;
    }

    public int getTotalcount()
    {
        return totalcount;
    }

    public void setTotalcount(int totalcount)
    {
        this.totalcount = totalcount;
    }

    public int getPerpage()
    {
        return perpage;
    }

    public void setPerpage(int perpage)
    {
        this.perpage = perpage;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public List getDevices()
    {
        return devices;
    }

    public void setDevices(ArrayList devices)
    {
        this.devices = devices;
    }

    private int totalcount;
    private int perpage;
    private int page;
    private ArrayList devices;
}
