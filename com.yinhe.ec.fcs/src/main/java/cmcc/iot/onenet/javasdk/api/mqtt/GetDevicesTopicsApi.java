//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.mqtt;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDevicesTopicsApi extends AbstractAPI {
    private String devId;
    private HttpGetMethod HttpMethod;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GetDevicesTopicsApi(String devId, String key) {
        this.devId = devId;
        this.key = key;
        this.method = Method.GET;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpGetMethod(this.method);
        this.url = Config.getString("test.url") + "/mqtt" + "/device_topic/" + devId;
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
    }

    public BasicResponse<List<String>> executeApi() {
        BasicResponse response = null;

        BasicResponse var5;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), new TypeReference<List<String>>() {
            });
            response.setData(newData);
            var5 = response;
        } catch (Exception var12) {
            this.logger.error("error: {}", var12.getMessage());
            throw new OnenetApiException(var12.getMessage());
        } finally {
            try {
                this.HttpMethod.httpClient.close();
            } catch (Exception var11) {
                this.logger.error("http close error: {}", var11.getMessage());
                throw new OnenetApiException(var11.getMessage());
            }
        }

        return var5;
    }
}
