// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StreamClosedHttpResponse.java

package com.yinhe.ec.cpps.iot.utils;

import org.apache.http.*;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.Locale;

// Referenced classes of package com.yinhe.ec.cpps.iot.utils:
//            StreamUtil

public class StreamClosedHttpResponse
    implements HttpResponse
{

    public StreamClosedHttpResponse(HttpResponse original)
        throws UnsupportedOperationException, IOException
    {
        this.original = original;
        HttpEntity entity = original.getEntity();
        if(entity != null && entity.isStreaming())
        {
            String encoding = entity.getContentEncoding() == null ? null : entity.getContentEncoding().getValue();
            content = StreamUtil.inputStream2String(entity.getContent(), encoding);
        } else
        {
            content = null;
        }
    }

    public StatusLine getStatusLine()
    {
        return original.getStatusLine();
    }

    public void setStatusLine(StatusLine statusline)
    {
        original.setStatusLine(statusline);
    }

    public void setStatusLine(ProtocolVersion ver, int code)
    {
        original.setStatusLine(ver, code);
    }

    public void setStatusLine(ProtocolVersion ver, int code, String reason)
    {
        original.setStatusLine(ver, code, reason);
    }

    public void setStatusCode(int code)
        throws IllegalStateException
    {
        original.setStatusCode(code);
    }

    public void setReasonPhrase(String reason)
        throws IllegalStateException
    {
        original.setReasonPhrase(reason);
    }

    public HttpEntity getEntity()
    {
        return original.getEntity();
    }

    public void setEntity(HttpEntity entity)
    {
        original.setEntity(entity);
    }

    public Locale getLocale()
    {
        return original.getLocale();
    }

    public void setLocale(Locale loc)
    {
        original.setLocale(loc);
    }

    public ProtocolVersion getProtocolVersion()
    {
        return original.getProtocolVersion();
    }

    public boolean containsHeader(String name)
    {
        return original.containsHeader(name);
    }

    public Header[] getHeaders(String name)
    {
        return original.getHeaders(name);
    }

    public Header getFirstHeader(String name)
    {
        return original.getFirstHeader(name);
    }

    public Header getLastHeader(String name)
    {
        return original.getLastHeader(name);
    }

    public Header[] getAllHeaders()
    {
        return original.getAllHeaders();
    }

    public void addHeader(Header header)
    {
        original.addHeader(header);
    }

    public void addHeader(String name, String value)
    {
        original.addHeader(name, value);
    }

    public void setHeader(Header header)
    {
        original.setHeader(header);
    }

    public void setHeader(String name, String value)
    {
        original.setHeader(name, value);
    }

    public void setHeaders(Header headers[])
    {
        original.setHeaders(headers);
    }

    public void removeHeader(Header header)
    {
        original.removeHeader(header);
    }

    public void removeHeaders(String name)
    {
        original.removeHeaders(name);
    }

    public HeaderIterator headerIterator()
    {
        return original.headerIterator();
    }

    public HeaderIterator headerIterator(String name)
    {
        return original.headerIterator(name);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("HttpResponseProxy{");
        sb.append(original);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @deprecated Method getParams is deprecated
     */

    public HttpParams getParams()
    {
        return original.getParams();
    }

    /**
     * @deprecated Method setParams is deprecated
     */

    public void setParams(HttpParams params)
    {
        original.setParams(params);
    }

    public String getContent()
    {
        return content;
    }

    private final HttpResponse original;
    private final String content;
}
