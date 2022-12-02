//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.http;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.request.RequestInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BasicHttpMethod implements RequestInfo {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public CloseableHttpClient httpClient;
    public HttpRequestBase httpRequestBase;
    public String url;
    public Method method;
    boolean upload;
    public ObjectMapper mapper = new ObjectMapper();

    public BasicHttpMethod(Method method) {
        this.method = method;
//        switch ($SWITCH_TABLE$cmcc$iot$onenet$javasdk$request$RequestInfo$Method()[method.ordinal()]) {
//            case 1:
//                this.httpRequestBase = new HttpPost();
//                break;
//            case 2:
//            default:
//                this.httpRequestBase = new HttpGet();
//                break;
//            case 3:
//                this.httpRequestBase = new HttpDelete();
//                break;
//            case 4:
//                this.httpRequestBase = new HttpPut();
//        }

    }

    public HttpRequestBase setHttpRequestBase() {
        return null;
    }

    public void setHttpRequestBase(HttpRequestBase httpRequestBase) {
        this.httpRequestBase = httpRequestBase;
    }

    public void setcompleteUrl(String url, Map params) {
        if (params != null) {
            url = url + "?";
            Set<Map.Entry<String, Object>> entrys = params.entrySet();
            int size = entrys.size();
            int index = 0;
            Iterator var7 = entrys.iterator();

            while(var7.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var7.next();
                url = url + (String)entry.getKey() + "=" + entry.getValue();
                ++index;
                if (index < size) {
                    url = url + "&";
                }
            }
        }

        try {
            this.httpRequestBase.setURI(new URI(url));
        } catch (URISyntaxException var8) {
            this.logger.error("url error: {}", var8.getMessage());
            throw new OnenetApiException(var8.getMessage());
        }
    }

    @Override
    public void sethttpMethod(Method method) {

    }

    public void setType(boolean upload) {
        if (!upload) {
            this.httpRequestBase.addHeader("Content-type", "application/json; charset=utf-8");
            this.httpRequestBase.addHeader("Accept", "application/json");
        }

    }


    public void setHeader(Map params) {
        if (params != null) {
            Set<Map.Entry<String, Object>> entrys = params.entrySet();
            Iterator var4 = entrys.iterator();

            while(var4.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var4.next();
                this.httpRequestBase.setHeader((String)entry.getKey(), (String)entry.getValue());
            }
        }

    }

    @Override
    public void setEntity(Object obj) {

    }

    @Override
    public void setEntity(Map map, Map map1) {

    }
}
