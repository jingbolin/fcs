// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SysListener.java

package com.yinhe.ec.cpps.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            ConstParam

public class SysListener extends HttpServlet
    implements ServletContextListener
{

    public SysListener()
    {
    }

    public void contextDestroyed(ServletContextEvent servletcontextevent)
    {
    }

    public void contextInitialized(ServletContextEvent sce)
    {
        String sqlType = sce.getServletContext().getInitParameter("sqlType");
        String socketIp = sce.getServletContext().getInitParameter("socketIp");
        int socketPort = Integer.parseInt(sce.getServletContext().getInitParameter("socketPort"));
        new ConstParam(sqlType, socketIp, socketPort);
    }
}
