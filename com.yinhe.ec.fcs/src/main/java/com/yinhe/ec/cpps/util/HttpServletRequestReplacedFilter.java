// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpServletRequestReplacedFilter.java

package com.yinhe.ec.cpps.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            MAPIHttpServletRequestWrapper

public class HttpServletRequestReplacedFilter
    implements Filter
{

    public HttpServletRequestReplacedFilter()
    {
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        ServletRequest requestWrapper = null;
        if(request instanceof HttpServletRequest)
        {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            if("POST".equals(httpServletRequest.getMethod().toUpperCase()))
                requestWrapper = new MAPIHttpServletRequestWrapper((HttpServletRequest)request);
        }
        if(requestWrapper == null)
            chain.doFilter(request, response);
        else
            chain.doFilter(requestWrapper, response);
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }
}
