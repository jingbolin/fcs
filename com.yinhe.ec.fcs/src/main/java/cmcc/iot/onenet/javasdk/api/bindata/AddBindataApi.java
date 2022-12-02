//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.bindata;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.bindata.NewBindataResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AddBindataApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String devId;
    private String datastreamId;
    private String filename;
    private String filepath;
    private HttpPostMethod HttpMethod;

    public AddBindataApi(String devId, String datastreamId, String key, String filename, String filepath) {
        this.devId = devId;
        this.datastreamId = datastreamId;
        this.key = key;
        this.filename = filename;
        this.filepath = filepath;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/bindata";
        Map<String, Object> urlmap = new HashMap();
        if (devId != null) {
            urlmap.put("device_id", devId);
        }

        if (datastreamId != null) {
            urlmap.put("datastream_id", datastreamId);
        }

        Map<String, String> fileMap = new HashMap();
        fileMap.put(filename, filepath);
        this.HttpMethod.setEntity((Map)null, fileMap);
        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<NewBindataResponse> executeApi() {
        BasicResponse response = null;

        BasicResponse var5;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), NewBindataResponse.class);
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
