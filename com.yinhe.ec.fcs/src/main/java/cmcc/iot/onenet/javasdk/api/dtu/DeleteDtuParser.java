//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.dtu;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpDeleteMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DeleteDtuParser extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer id;
    private HttpDeleteMethod HttpMethod;

    public DeleteDtuParser(Integer id, String key) {
        this.id = id;
        this.key = key;
        this.method = Method.DELETE;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpDeleteMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        if (id == null) {
            throw new OnenetApiException("parser id is required ");
        } else {
            this.url = Config.getString("test.url") + "/dtu/parser/" + id;
            this.HttpMethod.setcompleteUrl(this.url, (Map)null);
        }
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
