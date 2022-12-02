//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.dtu;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ModifyDtuParser extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String name;
    private String filepath;
    private HttpPutMethod HttpMethod;
    private Integer id;

    public ModifyDtuParser(Integer id, String name, String filepath, String key) {
        this.name = name;
        this.filepath = filepath;
        this.key = key;
        this.method = Method.PUT;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPutMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        if (id == null) {
            throw new OnenetApiException("parser id is required ");
        } else {
            this.url = Config.getString("test.url") + "/dtu/parser/" + id;
            Map<String, String> fileMap = new HashMap();
            fileMap.put("parser", filepath);
            Map<String, String> stringMap = new HashMap();
            stringMap.put("name", name);
            this.HttpMethod.setEntity(stringMap, fileMap);
            this.HttpMethod.setcompleteUrl(this.url, (Map)null);
        }
    }

    public BasicResponse executeApi() {
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
