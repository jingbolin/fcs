// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadMDao.java

package com.yinhe.ec.cpps.ycmeter.dao;


public interface ReadMDao
{

    public abstract String getData(String s, String s1, String s2, String as[], String s3, String s4, String s5, 
            String s6);

    public abstract String getData(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            int i);

    public abstract String getTryData(String s, String s1, String s2, String as[], String s3, String s4);

    public abstract String getSendData(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            int i);

    public abstract void insertSendData(String s, String s1, String s2, int i, String s3, int j, int k);

    public abstract void updateSendDataManageFlag(String s, int i);

    public abstract void updateMeterOnOff(String s, String s1, String s2, int i);
}
