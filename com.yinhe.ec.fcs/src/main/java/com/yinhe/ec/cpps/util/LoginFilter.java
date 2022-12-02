// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginFilter.java

package com.yinhe.ec.cpps.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter
    implements Filter
{

    public LoginFilter()
    {
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession();
        System.out.println((new StringBuilder("-------------")).append(request.getServletPath()).toString());
        if(!request.getServletPath().equals("/login.jsp") && !request.getServletPath().equals("/login/operatorLogin") && !request.getServletPath().equals("/regist.jsp") && !request.getServletPath().equals("/systemManager/registerInfo") && !request.getServletPath().equals("/login/cancelLogin") && !request.getServletPath().equals("/index") && !request.getServletPath().equals("/js/jquery-1.9.1.min.js") && !request.getServletPath().equals("/js/jquery-easyui/jquery.easyui.min.js") && !request.getServletPath().equals("/js/jquery-easyui/themes/default/easyui.css") && !request.getServletPath().equals("/js/common-js.js") && !request.getServletPath().equals("/images/login_bg_yinhe.png") && !request.getServletPath().equals("/images/favicon.ico") && !request.getServletPath().equals("/images/logo_login_yinhe.png") && !request.getServletPath().equals("/images/login_icon.jpg"))
        {
            if(session.getAttribute("Operator") == null)
            {
                session.invalidate();
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript' type='text/javascript'>");
                out.println((new StringBuilder("window.top.location.href='")).append(request.getContextPath()).append("/'").toString());
                out.println("</script>");
            } else
            {
                chain.doFilter(request, response);
            }
        } else
        {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }
}
