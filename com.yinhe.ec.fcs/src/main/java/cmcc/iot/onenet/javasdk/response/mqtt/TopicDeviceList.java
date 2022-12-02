// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TopicDeviceList.java

package cmcc.iot.onenet.javasdk.response.mqtt;

import java.util.ArrayList;

public class TopicDeviceList
{

    public TopicDeviceList(int totalcount, int perpage, int page, ArrayList devices)
    {
        this.totalcount = totalcount;
        this.perpage = perpage;
        this.page = page;
        this.devices = devices;
    }

    private int totalcount;
    private int perpage;
    private int page;
    private ArrayList devices;
}
