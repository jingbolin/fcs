//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.dtu;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.dtu.NewParserResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AddDtuParser extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String name;
    private String filepath;
    private HttpPostMethod HttpMethod;

    public AddDtuParser(String name, String filepath, String key) {
        this.name = name;
        this.filepath = filepath;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/dtu/parser";
        Map<String, String> fileMap = new HashMap();
        fileMap.put("parser", filepath);
        Map<String, String> stringMap = new HashMap();
        stringMap.put("name", name);
        this.HttpMethod.setEntity(stringMap, fileMap);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
    }

    public BasicResponse<NewParserResponse> executeApi() {
        ObjectMapper mapper = new ObjectMapper();
        BasicResponse response = null;

        BasicResponse var6;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(mapper.writeValueAsString(response));
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), NewParserResponse.class);
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
