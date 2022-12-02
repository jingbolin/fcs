// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegDeviceResponse.java

package cmcc.iot.onenet.javasdk.response.device;


public class RegDeviceResponse
{

    public RegDeviceResponse()
    {
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getDevid()
    {
        return devid;
    }

    public void setDevid(String devid)
    {
        this.devid = devid;
    }

    public String key;
    public String devid;
}
