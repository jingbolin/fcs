// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewDeviceResponse.java

package cmcc.iot.onenet.javasdk.response.device;


public class NewDeviceResponse
{

    public NewDeviceResponse()
    {
    }

    public String getDeviceId()
    {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId)
    {
        this.DeviceId = DeviceId;
    }

    public String DeviceId;
}
