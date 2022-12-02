// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DatapointsList.java

package cmcc.iot.onenet.javasdk.response.datapoints;

import java.util.ArrayList;
import java.util.List;

public class DatapointsList
{
    public static class DatastreamsItem
    {
        public static class DatapointsItem
        {

            public String getAt()
            {
                return at;
            }

            public void setAt(String at)
            {
                this.at = at;
            }

            public Object getValue()
            {
                return value;
            }

            public void setValue(Object value)
            {
                this.value = value;
            }

            private String at;
            private Object value;

            public DatapointsItem(String at, Object value)
            {
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

        public List getDatapoints()
        {
            return datapoints;
        }

        public void setDatapoints(List datapoints)
        {
            this.datapoints = datapoints;
        }

        public String id;
        public List datapoints;

        public DatastreamsItem(String id, List datapoints)
        {
            this.id = id;
            this.datapoints = datapoints;
        }
    }


    public String getCursor()
    {
        return cursor;
    }

    public void setCursor(String cursor)
    {
        this.cursor = cursor;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public ArrayList getDevices()
    {
        return devices;
    }

    public void setDevices(ArrayList devices)
    {
        this.devices = devices;
    }

    public DatapointsList(String count, ArrayList devices)
    {
        this.count = count;
        this.devices = devices;
    }

    private String count;
    private String cursor;
    private ArrayList devices;
}
