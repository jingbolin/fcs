// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   YinheDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import java.util.List;
import java.util.Map;

public interface YinheDao
{

    public abstract Map deviceDatas(String s, String s1, String s2);

    public abstract Map postCommand(String s, String s1, String s2, String s3, int i, String s4, String s5);

    public abstract void updateCommandId(String s, String s1, String s2);

    public abstract void registerDevice(String s, String s1);

    public abstract void deleteDevice(String s);

    public abstract List getYinheMeterData(String s);

    public abstract String modifyDeviceId(String s, String s1, String s2);

    public abstract void updateTmpnbsenddata(String s, int i);

    public abstract List getPostCommand(String s);

    public abstract void updateNbsenddata(String s, int i);
}
