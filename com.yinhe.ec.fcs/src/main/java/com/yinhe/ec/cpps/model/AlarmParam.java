// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlarmParam.java

package com.yinhe.ec.cpps.model;


public class AlarmParam
{

    public AlarmParam()
    {
    }

    public AlarmParam(int alarmId, String alarmName, int alarm1, int alarm2, String remark)
    {
        this.alarmId = alarmId;
        this.alarmName = alarmName;
        this.alarm1 = alarm1;
        this.alarm2 = alarm2;
        this.remark = remark;
    }

    public int getAlarmId()
    {
        return alarmId;
    }

    public void setAlarmId(int alarmId)
    {
        this.alarmId = alarmId;
    }

    public String getAlarmName()
    {
        return alarmName;
    }

    public void setAlarmName(String alarmName)
    {
        this.alarmName = alarmName;
    }

    public int getAlarm1()
    {
        return alarm1;
    }

    public void setAlarm1(int alarm1)
    {
        this.alarm1 = alarm1;
    }

    public int getAlarm2()
    {
        return alarm2;
    }

    public void setAlarm2(int alarm2)
    {
        this.alarm2 = alarm2;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    private int alarmId;
    private String alarmName;
    private int alarm1;
    private int alarm2;
    private String remark;
}
