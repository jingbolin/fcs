//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.key;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.model.Permissions;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.key.NewKeyResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddKeyApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String title;
    private List<Permissions> permissions;
    private HttpPostMethod HttpMethod;

    public AddKeyApi(String title, List<Permissions> permissions, String key) {
        this.title = title;
        this.permissions = permissions;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/keys";
        Map<String, Object> bodymap = new HashMap();
        if (title != null) {
            bodymap.put("title", title);
        }

        if (permissions != null) {
            bodymap.put("permissions", permissions);
        }

        String json = null;
        ObjectMapper remapper = new ObjectMapper();

        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception var9) {
            this.logger.error("json error {}", var9.getMessage());
            throw new OnenetApiException(var9.getMessage());
        }

        this.HttpMethod.setEntity(json);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
    }

    public BasicResponse<NewKeyResponse> executeApi() {
        BasicResponse response = null;

        BasicResponse var5;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), NewKeyResponse.class);
            response.setData(newData);
            var5 = response;
        } catch (Exception var12) {
            var12.printStackTrace();
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
