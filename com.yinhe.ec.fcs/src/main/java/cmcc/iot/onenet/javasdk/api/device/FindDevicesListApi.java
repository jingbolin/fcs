//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.device;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.DeviceList;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FindDevicesListApi extends AbstractAPI {
    private String keywords;
    private Object authinfo;
    private String devid;
    private Date begin;
    private Date end;
    private String tag;
    private Boolean isPrivate;
    private Boolean isOnline;
    private Integer page;
    private Integer perpage;
    private HttpGetMethod HttpMethod;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public FindDevicesListApi(String keywords, Object authinfo, String devid, Date begin, Date end, String tags, Boolean isPrivate, Integer page, Integer perpage, Boolean isOnline, String key) {
        this.keywords = keywords;
        this.authinfo = authinfo;
        this.devid = devid;
        this.begin = begin;
        this.end = end;
        this.tag = tags;
        this.isPrivate = isPrivate;
        this.page = page;
        this.perpage = perpage;
        this.method = Method.GET;
        this.key = key;
        this.isOnline = isOnline;
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        this.HttpMethod = new HttpGetMethod(this.method);
        this.url = Config.getString("test.url") + "/devices";
        if (keywords != null) {
            urlmap.put("key_words", keywords);
        }

        if (authinfo != null) {
            urlmap.put("auth_info", authinfo);
        }

        if (tags != null) {
            urlmap.put("tag", tags);
        }

        if (isOnline != null) {
            urlmap.put("online", isOnline);
        }

        if (isPrivate != null) {
            urlmap.put("private", isPrivate);
        }

        if (page != null) {
            urlmap.put("page", page);
        }

        if (perpage != null) {
            urlmap.put("per_page", perpage);
        }

        if (devid != null) {
            urlmap.put("device_id", devid);
        }

        if (begin != null) {
            urlmap.put("begin", begin);
        }

        if (end != null) {
            urlmap.put("end", end);
        }

        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<DeviceList> executeApi() {
        BasicResponse response = null;
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        BasicResponse var7;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            InputStream instreams = httpResponse.getEntity().getContent();
            String str = IOUtils.toString(instreams, "UTF-8");
            response = (BasicResponse)this.mapper.readValue(str, BasicResponse.class);
            response.setJson(str);
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), DeviceList.class);
            response.setData(newData);
            var7 = response;
        } catch (Exception var14) {
            this.logger.error("error: {}", var14.getMessage());
            throw new OnenetApiException(var14.getMessage());
        } finally {
            try {
                this.HttpMethod.httpClient.close();
            } catch (Exception var13) {
                this.logger.error("http close error: {}", var13.getMessage());
                throw new OnenetApiException(var13.getMessage());
            }
        }

        return var7;
    }
}
