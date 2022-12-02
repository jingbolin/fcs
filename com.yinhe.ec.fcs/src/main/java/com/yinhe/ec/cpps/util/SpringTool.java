// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpringTool.java

package com.yinhe.ec.cpps.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringTool
    implements ApplicationContextAware
{

    public SpringTool()
    {
    }

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        if(applicationContext == null)
            applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static Object getBean(String name)
    {
        return getApplicationContext().getBean(name);
    }

    private static ApplicationContext applicationContext = null;

}
