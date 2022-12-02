// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadMController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.ycmeter.service.ReadMService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadMController
{

    public ReadMController()
    {
    }

    public String getData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String controlInfo, 
            String module)
    {
        String tmpCmdArr[] = commItem.split(",");
        return readMService.getData(musterNo, meterNo, musterPtl, tmpCmdArr, connPara, mPara, controlInfo, module);
    }

    public String getData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, 
            int custId)
    {
        return readMService.getData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, opName, custId);
    }

    public String getTryData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara)
    {
        String tmpCmdArr[] = commItem.split(",");
        return readMService.getTryData(musterNo, meterNo, musterPtl, tmpCmdArr, connPara, mPara);
    }

    public String getSendData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, 
            int sId)
    {
        return readMService.getSendData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, opName, sId);
    }

    public String insertSendData(String musterID, String meterNO, String cmd, int sendType, String opName, int custId, int readStyle)
    {
        return readMService.insertSendData(musterID, meterNO, cmd, sendType, opName, custId, readStyle);
    }

    public String getData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, 
            int iotFlag, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String msg = readMService.getData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, opName, iotFlag);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        return null;
    }

    public String updateSendDataManageFlag(String sid, int manageFlag)
    {
        return readMService.updateSendDataManageFlag(sid, manageFlag);
    }

    public String updateMeterOnOff(String meterNo, String commItem, String opName, int custId)
    {
        return readMService.updateMeterOnOff(meterNo, commItem, opName, custId);
    }

    private ReadMService readMService;
}
