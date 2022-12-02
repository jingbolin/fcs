//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.key;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.model.Permissions;
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

public class ModifyKeyApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String title;
    private String apikey;
    private List<Permissions> permissions;
    private HttpPutMethod HttpMethod;

    public ModifyKeyApi(String title, String apikey, List<Permissions> permissions, String key) {
        this.title = title;
        this.apikey = apikey;
        this.permissions = permissions;
        this.key = key;
        this.method = Method.PUT;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPutMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/keys/" + apikey;
        Map<String, Object> bodymap = new HashMap();
        if (title != null) {
            bodymap.put("title", title);
        }

        if (permissions != null) {
            bodymap.put("permissions", permissions);
        }

        String json = null;

        try {
            json = this.mapper.writeValueAsString(bodymap);
        } catch (Exception var9) {
            this.logger.error("json error {}", var9.getMessage());
            throw new OnenetApiException(var9.getMessage());
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
