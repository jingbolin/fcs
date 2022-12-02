// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IotI.java

package com.yinhe.ec.cpps.iot.dao;


public interface IotI
{

    public abstract String RegisterDevice(String s, String s1)
        throws Exception;

    public abstract String ModifyDevice(String s, String s1, String s2)
        throws Exception;

    public abstract String DeleteDevice(String s, String s1)
        throws Exception;

    public abstract String QueryDeviceStatus(String s, String s1)
        throws Exception;

    public abstract String QueryDeviceCapabilities(String s, String s1)
        throws Exception;

    public abstract String QueryDeviceData(String s, String s1)
        throws Exception;

    public abstract String QueryDeviceHistoryData(String s, String s1, String s2, String s3)
        throws Exception;

    public abstract String QueryDevices(String s, String s1, String s2, String s3, String s4)
        throws Exception;

    public abstract String PostAsynCommandV4(String s, String s1, String s2, String s3)
        throws Exception;

    public abstract void SubscribeNotification(String s)
        throws Exception;

    public abstract void DelSubscribeNotification(String s)
        throws Exception;

    public abstract String CreateDeviceCmdCancelTask(String s, String s1)
        throws Exception;

    public abstract String UpdateAsynCommand(String s, String s1)
        throws Exception;
}
