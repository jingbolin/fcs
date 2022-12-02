//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.mqtt;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SendMqttApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String topic;
    private Object contents;
    private HttpPostMethod HttpMethod;

    public SendMqttApi(String topic, Object contents, String key) {
        this.topic = topic;
        this.contents = contents;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/mqtt";
        if (topic != null) {
            urlmap.put("topic", topic);
        }

        if (contents instanceof byte[]) {
            try {
                String s = new String((byte[])contents, "UTF-8");
                this.HttpMethod.setEntity(s);
            } catch (UnsupportedEncodingException var7) {
                this.logger.error("bytes[]  error {}", var7.getMessage());
                throw new OnenetApiException(var7.getMessage());
            }
        }

        if (contents instanceof String) {
            this.HttpMethod.setEntity(contents);
        }

        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<Void> executeApi() {
        BasicResponse response = null;

        BasicResponse var4;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            var4 = response;
        } catch (Exception var11) {
            this.logger.error("json error {}", var11.getMessage());
            throw new OnenetApiException(var11.getMessage());
        } finally {
            try {
                this.HttpMethod.httpClient.close();
            } catch (Exception var10) {
                this.logger.error("http close error: {}", var10.getMessage());
                throw new OnenetApiException(var10.getMessage());
            }
        }

        return var4;
    }
}
