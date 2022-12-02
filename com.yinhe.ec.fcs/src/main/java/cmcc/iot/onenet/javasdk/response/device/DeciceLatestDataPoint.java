// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeciceLatestDataPoint.java

package cmcc.iot.onenet.javasdk.response.device;

import java.util.ArrayList;
import java.util.Date;

public class DeciceLatestDataPoint
{
    public static class DeviceItem
    {
        public static class DatastreamsItem
        {

            public String getId()
            {
                return id;
            }

            public void setId(String id)
            {
                this.id = id;
            }

            public Date getAt()
            {
                return at;
            }

            public void setAt(Date at)
            {
                this.at = at;
            }

            public String getValue()
            {
                return value;
            }

            public void setValue(String value)
            {
                this.value = value;
            }

            private String id;
            private Date at;
            private String value;

            public DatastreamsItem(String id, Date at, String value)
            {
                this.id = id;
                this.at = at;
                this.value = value;
            }
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

        public ArrayList getDatastreams()
        {
            return datastreams;
        }

        public void setDatastreams(ArrayList datastreams)
        {
            this.datastreams = datastreams;
        }

        private String id;
        private String title;
        private ArrayList datastreams;

        public DeviceItem(String id, String title, ArrayList datastreams)
        {
            this.id = id;
            this.title = title;
            this.datastreams = datastreams;
        }
    }


    public DeciceLatestDataPoint(int totalcount, ArrayList devices)
    {
        this.totalcount = totalcount;
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

    public ArrayList getDevices()
    {
        return devices;
    }

    public void setDevices(ArrayList devices)
    {
        this.devices = devices;
    }

    private int totalcount;
    private ArrayList devices;
}
