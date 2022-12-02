// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SendMessage.java

package com.yinhe.ec.cpps.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SendMessage extends HttpServlet
{

    public SendMessage()
    {
    }

    public void init()
        throws ServletException
    {
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String sn = request.getParameter("uid");
        String mobile = request.getParameter("mobile");
        String tpl_id = request.getParameter("tpl_id");
        String leftsum = request.getParameter("leftsum");
        String comp = URLEncoder.encode((new StringBuilder("\u3010")).append(request.getParameter("comp")).append("\u3011").toString(), "UTF-8");
        String tpl_value = (new StringBuilder("#leftsum#=")).append(leftsum).append("&#comp#=").append(comp).toString();
        String key = "6e25091063907f368ae2d102287f9a95";
        String url = "http://v.juhe.cn/sms/send";
        String dtype = "json";
        String path = "";
        String jsonStr = "";
        if(!"".equals(sn) && "PNKJ".equals(sn))
        {
            try
            {
                path = (new StringBuilder(String.valueOf(url))).append("?mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(URLEncoder.encode(tpl_value, "UTF-8")).append("&key=").append(key).append("&dtype=").append(dtype).toString();
                URL urls = new URL(path);
                InputStream is = urls.openStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                for(String line = ""; (line = br.readLine()) != null;)
                    jsonStr = line;

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
    }

    private static final long serialVersionUID = 1L;
}
