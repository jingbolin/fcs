// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleWebSessionManager.java

package com.yinhe.ec.cpps.util;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;

import java.util.Collection;
import java.util.Iterator;

public class SimpleWebSessionManager extends DefaultWebSessionManager
    implements WebSessionManager
{

    public SimpleWebSessionManager()
    {
    }

    public void validateSessions()
    {
        int invalidCount = 0;
        Collection activeSessions = getActiveSessions();
        if(activeSessions != null && !activeSessions.isEmpty())
        {
            for(Iterator i$ = activeSessions.iterator(); i$.hasNext();)
            {
                Session session = (Session)i$.next();
                try
                {
                    org.apache.shiro.session.mgt.SessionKey key = new DefaultSessionKey(session.getId());
                    validate(session, key);
                }
                catch(InvalidSessionException e)
                {
                    if(cacheManager != null)
                    {
                        SimpleSession s = (SimpleSession)session;
                        if(s.getAttribute("Operator") != null)
                            cacheManager.getCache(null).remove(s.getAttribute("Operator"));
                    }
                    invalidCount++;
                }
            }

        }
    }

    public void setCacheManager(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    private CacheManager cacheManager;
}
