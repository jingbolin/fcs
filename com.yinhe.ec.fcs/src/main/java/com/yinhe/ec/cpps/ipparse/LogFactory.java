// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogFactory.java

package com.yinhe.ec.cpps.ipparse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogFactory
{

    public LogFactory()
    {
    }

    public static void log(String info, Level level, Throwable ex)
    {
        logger.log(level, info, ex);
    }

    public static Level getLogLevel()
    {
        return logger.getLevel();
    }

    private static final Logger logger;

    static 
    {
        logger = Logger.getLogger("stdout");
        logger.setLevel(Level.DEBUG);
    }
}
