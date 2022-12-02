// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadMService.java

package com.yinhe.ec.cpps.ycmeter.service;

import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.ReadMDao;

public class ReadMService
{

    public ReadMService()
    {
    }

    public String getData(String musterNo, String meterNo, String musterPtl, String commItem[], String connPara, String mPara, String controlInfo, 
            String module)
    {
        return readmDao.getData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, controlInfo, module);
    }

    public String getData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, 
            int custId)
    {
        if("2007".equals(commItem))
            mPara = Tools.getNow();
        return readmDao.getData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, opName, custId);
    }

    public String getTryData(String musterNo, String meterNo, String musterPtl, String commItem[], String connPara, String mPara)
    {
        return readmDao.getTryData(musterNo, meterNo, musterPtl, commItem, connPara, mPara);
    }

    public String getSendData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, 
            int sId)
    {
        return readmDao.getSendData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, opName, sId);
    }

    public String insertSendData(String musterID, String meterNO, String cmd, int sendType, String opName, int custId, int readStyle)
    {
        readmDao.insertSendData(musterID, meterNO, cmd, sendType, opName, custId, readStyle);
        return "\u547D\u4EE4\u4E0B\u53D1\u5DF2\u6210\u529F";
    }

    public String updateSendDataManageFlag(String sid, int manageFlag)
    {
        readmDao.updateSendDataManageFlag(sid, manageFlag);
        return "\u6267\u884C\u6210\u529F";
    }

    public String updateMeterOnOff(String meterNo, String commItem, String opName, int custId)
    {
        readmDao.updateMeterOnOff(meterNo, commItem, opName, custId);
        return "\u6267\u884C\u6210\u529F";
    }

    private ReadMDao readmDao;
}
