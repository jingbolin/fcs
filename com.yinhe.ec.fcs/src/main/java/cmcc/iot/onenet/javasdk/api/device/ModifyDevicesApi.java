//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.device;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.model.Location;
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

public class ModifyDevicesApi extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String devId;
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
    private HttpPutMethod HttpMethod;
    private Object isPrivateSet;

    public ModifyDevicesApi(String devId, String title, String protocol, String desc, List<String> tags, Location location, Boolean isPrivate, Object authInfo, Map<String, Object> other, Integer interval, String key) {
        this.devId = devId;
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
        this.method = Method.PUT;
        Map<String, Object> headmap = new HashMap();
        this.HttpMethod = new HttpPutMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices" + "/" + devId;
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

        if (location != null) {
            bodymap.put("location", location.toMap());
        }

        if (isPrivate != null) {
            bodymap.put("private", isPrivate);
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

        String json = null;
        ObjectMapper remapper = new ObjectMapper();

        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception var17) {
            this.logger.error("json error", var17.getMessage());
            throw new OnenetApiException(var17.getMessage());
        }

        this.HttpMethod.setEntity(json);
        this.HttpMethod.setcompleteUrl(this.url, (Map)null);
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
