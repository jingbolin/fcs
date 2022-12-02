// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AliyunSendSms.java

package com.yinhe.ec.cpps.util;

//import com.aliyuncs.*;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.exceptions.ServerException;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AliyunSendSms
{

    public AliyunSendSms()
    {
    }

    public static String SendSms(int custId, String phoneNo, String templateCode, String templateParam)
    {
        List list = Arrays.asList(MSG_CUSTOMER);
        if(list.contains(Integer.valueOf(custId)))
        {
//            DefaultProfile profile = DefaultProfile.getProfile("default", ((String)aliMap.get("YHSD")).split(",")[0], ((String)aliMap.get("YHSD")).split(",")[1]);
//            IAcsClient client = new DefaultAcsClient(profile);
//            CommonRequest request = new CommonRequest();
//            request.setMethod(MethodType.POST);
//            request.setDomain("dysmsapi.aliyuncs.com");
//            request.setVersion("2017-05-25");
//            request.setAction("SendSms");
//            request.putQueryParameter("PhoneNumbers", phoneNo);
//            request.putQueryParameter("SignName", ((String)aliMap.get("YHSD")).split(",")[2]);
//            request.putQueryParameter("TemplateCode", templateCode);
//            request.putQueryParameter("TemplateParam", templateParam);
//            try
//            {
//                CommonResponse response = client.getCommonResponse(request);
//                System.out.println(response.getData());
//                return response.getData();
//            }
//            catch(ServerException e)
//            {
//                e.printStackTrace();
//            }
//            catch(ClientException e)
//            {
//                e.printStackTrace();
//            }
            return "{\"Message\": \"\u53D1\u9001\u5931\u8D25\", \"Code\": \"ERROR\" }";
        } else
        {
            return "{\"Message\": \"\u5BA2\u6237\u672A\u5F00\u542F\u77ED\u4FE1\u670D\u52A1\", \"Code\": \"ERROR\" }";
        }
    }

    public static void main(String args[])
    {
        SendSms(0, "17792076859", "SMS_218549112", "{\"name\":\"\u6D4B\u8BD5\",\"number\":\"61001234567800\"}");
    }

    public static final Map aliMap;
    public static Integer MSG_CUSTOMER[] = {
        Integer.valueOf(5)
    };

    static 
    {
        aliMap = new HashMap();
        aliMap.put("YHSD", "LTAI5tQgEDTQBVWcrd5ApxCn,YtH8f16xKkLTdM5NTjE2nSDwVbyg4M,\u94F6\u6CB3\u65F6\u4EE3");
    }
}
