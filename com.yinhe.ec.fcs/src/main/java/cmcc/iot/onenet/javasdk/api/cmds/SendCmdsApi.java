//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.cmds;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.cmds.NewCmdsResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SendCmdsApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String devId;
    private Integer qos;
    private Integer timeOut;
    private Integer type;
    private Object contents;
    private HttpPostMethod HttpMethod;

    public SendCmdsApi(String devId, Integer qos, Integer timeOut, Integer type, Object contents, String key) {
        this.devId = devId;
        this.qos = qos;
        this.timeOut = timeOut;
        this.type = type;
        this.contents = contents;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/cmds";
        if (devId != null) {
            urlmap.put("device_id", devId);
        }

        if (qos != null) {
            urlmap.put("qos", qos);
        }

        if (timeOut != null) {
            urlmap.put("timeout", timeOut);
        }

        if (type != null) {
            urlmap.put("type", type);
        }

        if (contents instanceof byte[]) {
            this.HttpMethod.setEntity(contents);
        }

        if (contents instanceof String) {
            this.HttpMethod.setEntity(contents);
        }

        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<NewCmdsResponse> executeApi() {
        BasicResponse response = null;

        BasicResponse var5;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), NewCmdsResponse.class);
            response.setData(newData);
            var5 = response;
        } catch (Exception var12) {
            this.logger.error("json error {}", var12.getMessage());
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
