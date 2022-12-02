// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpGetMethod.java

package cmcc.iot.onenet.javasdk.http;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package cmcc.iot.onenet.javasdk.http:
//            BasicHttpMethod

public class HttpGetMethod extends BasicHttpMethod
{

    public HttpGetMethod(Method method)
    {
        super(method);
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
    HttpGet httpGet;
}
