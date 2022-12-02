//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.dtu;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.dtu.DtuParserList;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class FindDtuParserList extends AbstractAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String name;
    private HttpGetMethod HttpMethod;

    public FindDtuParserList(String name, String key) {
        this.name = name;
        this.method = Method.GET;
        this.key = key;
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        this.HttpMethod = new HttpGetMethod(this.method);
        this.url = Config.getString("test.url") + "/dtu/parser";
        if (name != null) {
            urlmap.put("name", name);
        }

        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<DtuParserList> executeApi() {
        BasicResponse response = null;
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        BasicResponse var7;
        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            InputStream instreams = httpResponse.getEntity().getContent();
            String str = IOUtils.toString(instreams, "UTF-8");
            response = (BasicResponse)this.mapper.readValue(str, BasicResponse.class);
            response.setJson(str);
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), DtuParserList.class);
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
