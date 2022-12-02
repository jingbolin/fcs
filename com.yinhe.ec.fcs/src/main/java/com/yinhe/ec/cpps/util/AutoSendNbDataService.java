// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AutoSendNbDataService.java

package com.yinhe.ec.cpps.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            AutoSendNbDataTask

public class AutoSendNbDataService extends HttpServlet
{

    public AutoSendNbDataService()
    {
    }

    public void init()
        throws ServletException
    {
        AutoSendNbDataTask.start();
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
        AutoSendNbDataTask.start();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("AutoSendNbDataTask START OK!");
    }

    private static final long serialVersionUID = 1L;
}
