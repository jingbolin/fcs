// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DevicesStatusList.java

package cmcc.iot.onenet.javasdk.response.device;

import java.util.ArrayList;

public class DevicesStatusList
{
    public static class DeviceItem
    {

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

        public Boolean getIsonline()
        {
            return isonline;
        }

        public void setIsonline(Boolean isonline)
        {
            this.isonline = isonline;
        }

        private String id;
        private String title;
        private Boolean isonline;

        public DeviceItem(String id, String title, Boolean isonline)
        {
            this.id = id;
            this.title = title;
            this.isonline = isonline;
        }
    }


    public DevicesStatusList(int totalcount, ArrayList devices)
    {
        this.totalcount = totalcount;
        this.devices = devices;
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
