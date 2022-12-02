//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.triggers;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.triggers.TriggersResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GetTriggersApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HttpGetMethod HttpMethod;
    private String tirggerId;

    public GetTriggersApi(String tirggerId, String key) {
        this.tirggerId = tirggerId;
        this.key = key;
        this.method = Method.GET;
        this.HttpMethod = new HttpGetMethod(this.method);
        Map<String, Object> headmap = new HashMap();
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/triggers" + "/" + tirggerId;
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
    }

    public BasicResponse<TriggersResponse> executeApi() {
        ObjectMapper mapper = new ObjectMapper();
        BasicResponse response = null;
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        BasicResponse var6;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(mapper.writeValueAsString(response));
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), TriggersResponse.class);
            response.setData(newData);
            var6 = response;
        } catch (Exception var13) {
            this.logger.error("json error {}", var13.getMessage());
            throw new OnenetApiException(var13.getMessage());
        } finally {
            try {
                this.HttpMethod.httpClient.close();
            } catch (Exception var12) {
                this.logger.error("http close error: {}", var12.getMessage());
                throw new OnenetApiException(var12.getMessage());
            }
        }

        return var6;
    }
}
