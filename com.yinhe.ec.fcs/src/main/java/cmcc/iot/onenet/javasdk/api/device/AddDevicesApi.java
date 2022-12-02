//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.device;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.model.Location;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.NewDeviceResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDevicesApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String title;
    private String protocol;
    private String desc;
    private String idsn;
    private List<String> tags;
    private Location location;
    private Boolean isPrivate;
    private Object authInfo;
    private Map<String, Object> other;
    private Integer interval;
    private HttpPostMethod HttpMethod;

    public AddDevicesApi(String title, String protocol, String desc, List<String> tags, Location location, Boolean isPrivate, Object authInfo, Map<String, Object> other, Integer interval, String key) {
        this.title = title;
        this.protocol = protocol;
        this.desc = desc;
        this.tags = tags;
        this.location = location;
        this.isPrivate = isPrivate;
        this.authInfo = authInfo;
        this.other = other;
        this.interval = interval;
        this.key = key;
        this.method = Method.POST;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPostMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices";
        Map<String, Object> bodymap = new HashMap();
        if (title != null) {
            bodymap.put("title", title);
        }

        if (desc != null) {
            bodymap.put("desc", desc);
        }

        if (tags != null) {
            bodymap.put("tags", tags);
        }

        if (this.idsn != null) {
            bodymap.put("idsn", this.idsn);
        }

        if (location != null) {
            bodymap.put("location", location.toMap());
        }

        if (protocol != null) {
            bodymap.put("protocol", protocol);
        }

        if (authInfo != null) {
            bodymap.put("auth_info", authInfo);
        }

        if (interval != null) {
            bodymap.put("interval", interval);
        }

        if (other != null) {
            bodymap.put("other", other);
        }

        if (isPrivate != null) {
            bodymap.put("private", isPrivate);
        }

        String json = null;
        ObjectMapper remapper = new ObjectMapper();

        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception var16) {
            this.logger.error("json error", var16.getMessage());
            throw new OnenetApiException(var16.getMessage());
        }

        this.HttpMethod.setEntity(json);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
    }

    public BasicResponse<NewDeviceResponse> executeApi() {
        ObjectMapper mapper = new ObjectMapper();
        BasicResponse response = null;

        BasicResponse var6;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(mapper.writeValueAsString(response));
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), NewDeviceResponse.class);
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
