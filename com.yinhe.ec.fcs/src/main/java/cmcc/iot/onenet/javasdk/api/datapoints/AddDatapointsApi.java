//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.datapoints;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.model.Datapoints;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDatapointsApi extends AbstractAPI {
    private Map<String, List<Datapoints>> map;
    private String data;
    private Integer type;
    private String devId;
    private HttpPostMethod HttpMethod;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AddDatapointsApi(Map<String, List<Datapoints>> map, String data, Integer type, String devId, String key) {
        this.map = map;
        this.data = data;
        this.type = type;
        this.devId = devId;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId + "/datapoints";
        Map<String, Object> urlmap = new HashMap();
        if (type != null) {
            urlmap.put("type", type);
        }

        String json = null;

        try {
            if (map != null) {
                json = this.mapper.writeValueAsString(map);
            } else {
                json = data;
            }
        } catch (Exception var10) {
            this.logger.error("json error", var10.getMessage());
            throw new OnenetApiException(var10.getMessage());
        }

        this.HttpMethod.setEntity(json);
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
