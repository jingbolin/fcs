//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.triggers;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifyTriggersApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String tirggerId;
    private String title;
    private String dsid;
    private List<String> devids;
    private List<String> dsuuids;
    private String desturl;
    private String type;
    private Integer threshold;
    private HttpPutMethod HttpMethod;

    public ModifyTriggersApi(String tirggerId, String title, String dsid, List<String> devids, List<String> dsuuids, String desturl, String type, Integer threshold, String key) {
        this.tirggerId = tirggerId;
        this.title = title;
        this.dsid = dsid;
        this.devids = devids;
        this.dsuuids = dsuuids;
        this.desturl = desturl;
        this.type = type;
        this.threshold = threshold;
        this.key = key;
        this.method = Method.PUT;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPutMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/triggers/" + tirggerId;
        Map<String, Object> bodymap = new HashMap();
        if (title != null) {
            bodymap.put("title", title);
        }

        if (dsid != null) {
            bodymap.put("ds_id", dsid);
        }

        if (devids != null) {
            bodymap.put("dev_ids", devids);
        }

        if (dsuuids != null) {
            bodymap.put("ds_uuids", dsuuids);
        }

        if (desturl != null) {
            bodymap.put("url", desturl);
        }

        if (type != null) {
            bodymap.put("type", type);
        }

        if (threshold != null) {
            bodymap.put("threshold", threshold);
        }

        String json = null;

        try {
            json = this.mapper.writeValueAsString(bodymap);
        } catch (Exception var14) {
            this.logger.error("json error {}", var14.getMessage());
            throw new OnenetApiException(var14.getMessage());
        }

        this.HttpMethod.setEntity(json);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
    }

    public BasicResponse<Void> executeApi() {
        ObjectMapper mapper = new ObjectMapper();
        BasicResponse response = null;

        BasicResponse var5;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(mapper.writeValueAsString(response));
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
