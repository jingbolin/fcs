// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractAPI.java

package cmcc.iot.onenet.javasdk.api;

import cmcc.iot.onenet.javasdk.request.RequestInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractAPI
{

    public AbstractAPI()
    {
        mapper = new ObjectMapper();
    }

    public String key;
    public String url;
    public RequestInfo.Method method;
    public ObjectMapper mapper;
}
