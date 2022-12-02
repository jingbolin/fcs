//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.http;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
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

public class HttpPutMethod extends BasicHttpMethod {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public HttpPutMethod(Method method) {
        super(method);
    }

    public void setEntity(String json) {
        if (json != null) {
            StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
            ((HttpPut)this.httpRequestBase).setEntity(entity);
        }

    }

    public void setEntity(Map stringMap, Map fileMap) {
        this.upload = true;
        Set<Map.Entry<String, String>> stringSet = stringMap.entrySet();
        Set<Map.Entry<String, String>> fileSet = fileMap.entrySet();
        Map<String, StringBody> stringEntityMap = new HashMap();
        Map<String, FileBody> fileBodyMap = new HashMap();
        Iterator var8 = stringSet.iterator();

        Map.Entry entry;
        while(var8.hasNext()) {
            entry = (Map.Entry)var8.next();

            try {
                stringEntityMap.put((String)entry.getKey(), new StringBody((String)entry.getValue(), Charset.forName("UTF-8")));
            } catch (UnsupportedEncodingException var12) {
                this.logger.error("error:" + var12.getMessage());
                throw new OnenetApiException(var12.getMessage());
            }
        }

        var8 = fileSet.iterator();

        while(var8.hasNext()) {
            entry = (Map.Entry)var8.next();
            fileBodyMap.put((String)entry.getKey(), new FileBody(new File((String)entry.getValue())));
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        Set<Map.Entry<String, FileBody>> fileBodySet = fileBodyMap.entrySet();
        Set<Map.Entry<String, StringBody>> stringBodySet = stringEntityMap.entrySet();
        Iterator var11 = fileBodySet.iterator();

        while(var11.hasNext()) {
            entry = (Map.Entry)var11.next();
            builder.addPart((String)entry.getKey(), (ContentBody)entry.getValue());
        }

        var11 = stringBodySet.iterator();

        while(var11.hasNext()) {
            entry = (Map.Entry)var11.next();
            builder.addPart((String)entry.getKey(), (ContentBody)entry.getValue());
        }

        HttpEntity reqEntity = builder.build();
        ((HttpPut)this.httpRequestBase).setEntity(reqEntity);
    }

    public void sethttpMethod(Method method) {
    }

    public HttpResponse execute() throws Exception {
        HttpResponse httpResponse = null;
        this.httpClient = HttpClients.createDefault();
        httpResponse = this.httpClient.execute(this.httpRequestBase);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200 && statusCode != 221) {
            String response = EntityUtils.toString(httpResponse.getEntity());
            this.logger.error("request failed  status:{}, response::{}", statusCode, response);
            throw new OnenetApiException("request failed: " + response);
        } else {
            return httpResponse;
        }
    }
}
