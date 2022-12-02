// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpPostMethod.java

package cmcc.iot.onenet.javasdk.http;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package cmcc.iot.onenet.javasdk.http:
//            BasicHttpMethod

public class HttpPostMethod extends BasicHttpMethod
{

    public HttpPostMethod(Method method)
    {
        super(method);
    }

    public void setEntity(Object json)
    {
        if(json != null)
        {
            if(json instanceof String)
            {
                StringEntity entity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
                ((HttpPost)httpRequestBase).setEntity(entity);
            }
            if(json instanceof byte[])
                ((HttpPost)httpRequestBase).setEntity(new ByteArrayEntity((byte[])json));
        }
    }

    public void setEntity(Map stringMap, Map fileMap)
    {
        upload = true;
        Set stringBodySet = null;
        if(stringMap != null)
        {
            Set stringSet = stringMap.entrySet();
            Map stringEntityMap = new HashMap();
            for(Iterator iterator = stringSet.iterator(); iterator.hasNext();)
            {
                Map.Entry entry = (Map.Entry)iterator.next();
                try
                {
                    stringEntityMap.put((String)entry.getKey(), new StringBody((String)entry.getValue(), Charset.forName("UTF-8")));
                }
                catch(UnsupportedEncodingException e)
                {
                    logger.error((new StringBuilder("error:")).append(e.getMessage()).toString());
                    throw new OnenetApiException(e.getMessage());
                }
            }

            stringBodySet = stringEntityMap.entrySet();
        }
        Set fileBodySet = null;
        if(fileMap != null)
        {
            Set fileSet = fileMap.entrySet();
            Map fileBodyMap = new HashMap();
            Map.Entry entry;
            for(Iterator iterator3 = fileSet.iterator(); iterator3.hasNext(); fileBodyMap.put((String)entry.getKey(), new FileBody(new File((String)entry.getValue()))))
                entry = (Map.Entry)iterator3.next();

            fileBodySet = fileBodyMap.entrySet();
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if(fileBodySet != null)
        {
            Map.Entry entry;
            for(Iterator iterator1 = fileBodySet.iterator(); iterator1.hasNext(); builder.addPart((String)entry.getKey(), (ContentBody)entry.getValue()))
                entry = (Map.Entry)iterator1.next();

        }
        if(stringBodySet != null)
        {
            Map.Entry entry;
            for(Iterator iterator2 = stringBodySet.iterator(); iterator2.hasNext(); builder.addPart((String)entry.getKey(), (ContentBody)entry.getValue()))
                entry = (Map.Entry)iterator2.next();

        }
        org.apache.http.HttpEntity reqEntity = builder.build();
        ((HttpPost)httpRequestBase).setEntity(reqEntity);
    }

    public HttpResponse execute()
        throws Exception
    {
        HttpResponse httpResponse = null;
        httpClient = HttpClients.createDefault();
        httpResponse = httpClient.execute(httpRequestBase);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode != 200 && statusCode != 221)
        {
            String response = EntityUtils.toString(httpResponse.getEntity());
            logger.error("request failed  status:{}, response::{}", Integer.valueOf(statusCode), response);
            throw new OnenetApiException((new StringBuilder("request failed: ")).append(response).toString());
        } else
        {
            return httpResponse;
        }
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());
}
