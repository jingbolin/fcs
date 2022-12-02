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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CreateMqttTopicApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HttpPostMethod HttpMethod;
    private String name;

    public CreateMqttTopicApi(String name, String key) {
        this.name = name;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/mqtt" + "/topic";
        Map<String, Object> bodymap = new HashMap();
        if (name != null) {
            bodymap.put("name", name);
        }

        String json = null;
        ObjectMapper remapper = new ObjectMapper();

        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception var8) {
            this.logger.error("json error {}", var8.getMessage());
            throw new OnenetApiException(var8.getMessage());
        }

        this.HttpMethod.setEntity(json);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
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
