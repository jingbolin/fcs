// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WsLogDao.java

package com.yinhe.ec.cpps.ycmeter.dao;

import java.util.List;

public interface WsLogDao
{

    public abstract Integer getTotal(String s);

    public abstract List getWsInfo(int i, int j, String s);

    public abstract Integer getTotalSendData(String s);

    public abstract List getSendDataInfo(int i, int j, String s);

    public abstract List getUploadListByClause(int i, int j, String s);

    public abstract Integer getDosageDetailTotal(String s);

    public abstract List getNbSendDataInfo(String s);
}
