// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeterController.java

package com.yinhe.ec.cpps.ycmeter.controller;

import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.model.MeterChange;
import com.yinhe.ec.cpps.model.Meters;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.AliyunSendSms;
import com.yinhe.ec.cpps.ycmeter.service.MeterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class MeterController
{

    public MeterController()
    {
    }

    public Map getMeterInfo(int page, int rows, String orders)
    {
        return meterService.getMeterInfo(page, rows, orders);
    }

    public List getMeterListByClause(String orders)
    {
        return meterService.getMeterListByClause(orders);
    }

    public String changeMeterInfo(MeterChange meterChange)
    {
        return meterService.changeMeterInfo(meterChange);
    }

    public List getDayDosageByMeterNo(String meterNo)
    {
        return meterService.getDayDosageByMeterNo(meterNo);
    }

    public String updateMaxdayDosage(String meterNo, Double maxDosage)
    {
        return meterService.updateMaxdayDosage(meterNo, maxDosage);
    }

    public String modDayDosageSum(String meterNo, String markday, Double dayDosageSum, String checker)
    {
        return meterService.modDayDosageSum(meterNo, markday, dayDosageSum, checker);
    }

    public String getResultStrForUpdateDosageDays(String meterNo, String markday, Double dayDosageSum, String checker)
    {
        return meterService.getResultStrForUpdateDosageDays(meterNo, markday, dayDosageSum, checker);
    }

    public String sendMessage(int custId, String mobile, String tpl_id, String leftsum, String comp, String meterNo, String userId, 
            String userName, String operatorName, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String company = URLEncoder.encode((new StringBuilder("\u3010")).append(comp).append("\u3011").toString(), "UTF-8");
        String name = URLEncoder.encode(userName, "UTF-8");
        String type = meterNo;
        String desc = "";
        String tpl_value = "";
        String tmp_tpl_value = "";
        if("42897".equals(tpl_id))
        {
            if("1".equals(type))
            {
                tmp_tpl_value = "\u7535\u8D39";
                type = URLEncoder.encode("\u7535\u8D39", "UTF-8");
            } else
            if("2".equals(type))
            {
                tmp_tpl_value = "\u6C34\u8D39";
                type = URLEncoder.encode("\u6C34\u8D39", "UTF-8");
            }
            tpl_value = (new StringBuilder("#name#=")).append(name).append("&#type#=").append(type).append("&#money#=").append(leftsum).toString();
        } else
        if("42898".equals(tpl_id))
        {
            if("1".equals(type))
            {
                tmp_tpl_value = (new StringBuilder("\u7535\u8D39")).append(leftsum).toString();
                type = URLEncoder.encode("\u7535\u8D39", "UTF-8");
                desc = URLEncoder.encode("\u4F9B\u7535", "UTF-8");
            } else
            if("2".equals(type))
            {
                tmp_tpl_value = (new StringBuilder("\u6C34\u8D39")).append(leftsum).toString();
                type = URLEncoder.encode("\u6C34\u8D39", "UTF-8");
                desc = URLEncoder.encode("\u4F9B\u6C34", "UTF-8");
            }
            tpl_value = (new StringBuilder("#name#=")).append(name).append("&#type#=").append(type).append("&#money#=").append(leftsum).append("&#desc#=").append(desc).toString();
        } else
        if("42899".equals(tpl_id))
        {
            if("1".equals(type))
            {
                tmp_tpl_value = "\u7535\u8D39";
                type = URLEncoder.encode("\u7535\u8D39", "UTF-8");
                desc = URLEncoder.encode("\u62C9\u95F8\u65AD\u7535", "UTF-8");
            } else
            if("2".equals(type))
            {
                tmp_tpl_value = "\u6C34\u8D39";
                type = URLEncoder.encode("\u6C34\u8D39", "UTF-8");
                desc = URLEncoder.encode("\u5173\u9600\u65AD\u6C34", "UTF-8");
            }
            tpl_value = (new StringBuilder("#name#=")).append(name).append("&#type#=").append(type).append("&#desc#=").append(desc).toString();
        } else
        if("42900".equals(tpl_id))
        {
            tmp_tpl_value = leftsum;
            desc = URLEncoder.encode(leftsum, "UTF-8");
            tpl_value = (new StringBuilder("#name#=")).append(name).append("&#desc#=").append(desc).toString();
        }
        String key = "6e25091063907f368ae2d102287f9a95";
        String url = "http://v.juhe.cn/sms/send";
        String dtype = "json";
        String path = "";
        String jsonStr = "";
        if(!"".equals(mobile) && mobile.length() == 11)
        {
            try
            {
                path = (new StringBuilder(String.valueOf(url))).append("?mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(URLEncoder.encode(tpl_value, "UTF-8")).append("&key=").append(key).append("&dtype=").append(dtype).toString();
                URL urls = new URL(path);
                InputStream is = urls.openStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                for(String line = ""; (line = br.readLine()) != null;){
                    jsonStr = line;
                }
                is.close();
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            if(jsonStr.indexOf("\u64CD\u4F5C\u6210\u529F") != -1)
            {
                String messageId = genPkIdservice.getPkNoByTable("MessageList", "MessageId", Integer.valueOf(9));
                meterService.addMessageInfo(messageId, mobile, tpl_id, tmp_tpl_value, userId, userName, operatorName, custId, leftsum);
            }
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonStr);
        } else
        {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            jsonStr = "{\"reason\":\"\u9A8C\u8BC1\u9519\u8BEF\"}";
            response.getWriter().write(jsonStr);
        }
        return null;
    }

    public Map getSendMsgInfo(int page, int rows, String orders)
    {
        return meterService.getSendMsgInfo(page, rows, orders);
    }

    public String getListForSendmsg(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String orders = " and leftsum<alarm1 and feeState=1 and buysum>0 and smsKind=1 and length(mobilePhone)=11 and instr(MeterNO,'-')=0";
        meterService.getListForSendmsg(orders);
        String jsonStr = "{\"reason\":\"\u64CD\u4F5C\u6210\u529F\"}";
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonStr);
        return null;
    }

    public Meters getMetersByClause(String orders)
    {
        return meterService.getMetersByClause(orders);
    }

    public String sendMessageForWuqiao(int custId, String mobile, String tpl_id, String leftsum, String comp, String meterNo, String userId, 
            String userName, String operatorName, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String company = (new StringBuilder("[")).append(comp).append("]").toString();
        String name = userName;
        String type = meterNo;
        String desc = "";
        String tpl_value = "";
        String tmp_tpl_value = "";
        String jsonStr = "";
        if(custId != 15)
        {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            jsonStr = "{\"reason\":\"\u6682\u4E0D\u652F\u6301\"}";
            response.getWriter().write(jsonStr);
            return null;
        }
        if("WQ001".equals(tpl_id))
        {
            type = "\u6C34\u8D39";
            tpl_value = (new StringBuilder("(")).append(userId).append(")").append(userName).append("\u60A8\u597D\uFF01\u622A\u81F3\u5F53\u524D\uFF0C\u6C34\u8868\u4F59\u989D").append(leftsum).append("\u5143\uFF0C\u4E3A\u907F\u514D\u6B20\u8D39\u505C\u6C34\uFF0C\u8BF7\u53CA\u65F6\u5145\u503C\u30020317-7234011[\u5434\u6865\u81EA\u6765\u6C34]").toString();
        } else
        if("WQ002".equals(tpl_id))
        {
            type = "\u6C34\u8D39";
            tpl_value = (new StringBuilder("(")).append(userId).append(")").append(userName).append("\u60A8\u597D\uFF01\u622A\u81F3\u5F53\u524D\uFF0C\u6C34\u8868\u5DF2\u6B20\u8D39").append(leftsum).append("\u5143\uFF0C\u8BF7\u53CA\u65F6\u5145\u503C\u30020317-7234011[\u5434\u6865\u81EA\u6765\u6C34]").toString();
        } else
        if("WQ003".equals(tpl_id))
        {
            tmp_tpl_value = leftsum;
            tpl_value = (new StringBuilder("(")).append(userId).append(")").append(userName).append(tmp_tpl_value).append("0317-7234011[\u5434\u6865\u81EA\u6765\u6C34]").toString();
        }
        String sn = "DXX-HRM-010-00007";
        String pwd = "DECB72850E455401FB63B397AAA188E3";
        String url = (new StringBuilder("http://sdk2.entinfo.cn:8061/mdsmssend.ashx?sn=")).append(sn).append("&pwd=").append(pwd).append("&mobile=").append(mobile).append("&content=").append(URLEncoder.encode(tpl_value, "UTF-8")).append("&ext=&stime=&rrid=&msgfmt=").toString();
        if(!"".equals(mobile) && mobile.length() == 11)
        {
            try
            {
                URL urls = new URL(url);
                InputStream is = urls.openStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                for(String line = ""; (line = br.readLine()) != null;){
                    jsonStr = line;
                }
                is.close();
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            System.out.println(jsonStr);
            if(jsonStr.indexOf("-") == -1)
            {
                String messageId = genPkIdservice.getPkNoByTable("MessageList", "MessageId", Integer.valueOf(9));
                meterService.addMessageInfo(messageId, mobile, tpl_id, (new StringBuilder(String.valueOf(tmp_tpl_value))).append("\u3010").append(jsonStr).append("\u3011").toString(), userId, userName, operatorName, custId, leftsum);
                jsonStr = "{\"reason\":\"\u53D1\u9001\u6210\u529F\"}";
            } else
            {
                jsonStr = (new StringBuilder("{\"reason\":\"\u5931\u8D25\u3010")).append(jsonStr).append("\u3011\"}").toString();
            }
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonStr);
        } else
        {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            jsonStr = "{\"reason\":\"\u9A8C\u8BC1\u9519\u8BEF\"}";
            response.getWriter().write(jsonStr);
        }
        return null;
    }

    public String getListForSendmsgForWuqiao(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        String orders = " and custId=15 and leftsum<alarm1 and feeState=1 and smsKind=1 and length(mobilePhone)=11 and instr(MeterNO,'-')=0";
        meterService.getListForSendmsgForWuqiao(orders);
        String jsonStr = "{\"reason\":\"\u64CD\u4F5C\u6210\u529F\"}";
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonStr);
        return null;
    }

    public String sendMessageByAli(int custId, String phoneNo, String templateCode, String userId, String userName, String operatorName, HttpServletRequest request, 
            HttpServletResponse response)
        throws IOException
    {
        String resultCode = "";
        String messageContent = "";
        String result = "{\"Message\": \"\u6A21\u677F\u4E0D\u6B63\u786E\", \"Code\": \"---\" }";
        if("SMS_218549112".equals(templateCode))
        {
            String meterNo = request.getParameter("meterNo");
            String templateParam = (new StringBuilder("{\"name\":\"")).append(userName).append("\",\"number\":\"").append(meterNo).append("\"}").toString();
            result = AliyunSendSms.SendSms(custId, phoneNo, templateCode, templateParam);
            JSONObject jsonobj = JSONObject.parseObject(result);
            if(jsonobj != null)
            {
                resultCode = jsonobj.get("Code").toString();
                messageContent = (new StringBuilder("\u5C0A\u656C\u7684")).append(userName).append(", \u7535\u8868").append(meterNo).append("\u4F59\u91CF\u4E0D\u8DB3,\u8BF7\u53CA\u65F6\u7F34\u8D39\uFF01").toString();
            }
            if("OK".equals(resultCode))
            {
                String messageId = genPkIdservice.getPkNoByTable("MessageList", "MessageId", Integer.valueOf(9));
                meterService.addAliMessageInfo(messageId, phoneNo, templateCode, messageContent, userId, userName, operatorName, custId);
            }
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
        return null;
    }

    public String getLeftInfoByDosageSum(String meterNo, Double dosageSum)
    {
        return meterService.getLeftInfoByDosageSum(meterNo, dosageSum);
    }

    public String getDosageSumByLeftDosage(String meterNo, Double leftDosage)
    {
        return meterService.getDosageSumByLeftDosage(meterNo, leftDosage);
    }

    private MeterService meterService;
    private GenPkIdService genPkIdservice;
}
