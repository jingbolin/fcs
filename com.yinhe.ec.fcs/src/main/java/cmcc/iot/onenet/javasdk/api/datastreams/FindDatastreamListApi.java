//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.datastreams;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDatastreamListApi extends AbstractAPI {
    private HttpGetMethod HttpMethod;
    private String datastreamids;
    private String devId;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public FindDatastreamListApi(String datastreamids, String devId, String key) {
        this.datastreamids = datastreamids;
        this.devId = devId;
        this.key = key;
        this.method = Method.GET;
        this.HttpMethod = new HttpGetMethod(this.method);
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId + "/datastreams";
        if (datastreamids != null) {
            urlmap.put("datastream_ids", datastreamids);
        }

        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<List<DatastreamsResponse>> executeApi() {
        BasicResponse response = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        BasicResponse var6;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(mapper.writeValueAsString(response));
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), new TypeReference<List<DatastreamsResponse>>() {
            });
            response.setData(newData);
            var6 = response;
        } catch (Exception var13) {
            this.logger.error("error: {}", var13.getMessage());
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
