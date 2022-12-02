//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.datastreams;

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

public class ModifyDatastramsApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String uuid;
    private String devId;
    private List<String> tags;
    private String unit;
    private String unitSymbol;
    private String cmd;
    private Integer interval;
    private String formula;
    private HttpPutMethod HttpMethod;

    public ModifyDatastramsApi(String uuid, String devId, List<String> tags, String unit, String unitSymbol, String cmd, Integer interval, String formula, String key) {
        this.uuid = uuid;
        this.devId = devId;
        this.tags = tags;
        this.unit = unit;
        this.unitSymbol = unitSymbol;
        this.cmd = cmd;
        this.interval = interval;
        this.formula = formula;
        this.key = key;
        this.method = Method.PUT;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPutMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId + "/datastreams/" + uuid;
        Map<String, Object> bodymap = new HashMap();
        if (this.tags != null) {
            bodymap.put("tags", tags);
        }

        if (this.unit != null) {
            bodymap.put("unit", unit);
        }

        if (this.unitSymbol != null) {
            bodymap.put("unit_symbol", unitSymbol);
        }

        if (this.cmd != null) {
            bodymap.put("cmd", cmd);
        }

        if (this.interval != null) {
            bodymap.put("interval", interval);
        }

        if (this.formula != null) {
            bodymap.put("formula", formula);
        }

        String json = null;
        ObjectMapper remapper = new ObjectMapper();

        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception var15) {
            this.logger.error("json error {}", var15.getMessage());
            throw new OnenetApiException(var15.getMessage());
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
