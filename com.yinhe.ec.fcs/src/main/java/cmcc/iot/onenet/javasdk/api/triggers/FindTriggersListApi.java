//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.triggers;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.triggers.TriggersList;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class FindTriggersListApi extends AbstractAPI {
    private String title;
    private Integer page;
    private Integer perpage;
    private HttpGetMethod HttpMethod;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public FindTriggersListApi(String title, Integer page, Integer perpage, String key) {
        this.title = title;
        this.page = page;
        this.perpage = perpage;
        this.method = Method.GET;
        this.key = key;
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        this.HttpMethod = new HttpGetMethod(this.method);
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/triggers";
        if (title != null) {
            urlmap.put("title", title);
        }

        if (page != null) {
            urlmap.put("page", page);
        }

        if (perpage != null) {
            urlmap.put("per_page", perpage);
        }

        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<TriggersList> executeApi() {
        BasicResponse response = null;
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        BasicResponse var5;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), TriggersList.class);
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
