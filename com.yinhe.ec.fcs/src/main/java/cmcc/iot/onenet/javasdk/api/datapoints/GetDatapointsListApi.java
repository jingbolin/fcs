//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.api.datapoints;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GetDatapointsListApi extends AbstractAPI {
    private HttpGetMethod HttpMethod;
    private String datastreamIds;
    private String start;
    private String end;
    private String devId;
    private Integer duration;
    private Integer limit;
    private String cursor;
    private Integer interval;
    private String metd;
    private Integer first;
    private String sort;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GetDatapointsListApi(String datastreamIds, String start, String end, String devId, Integer duration, Integer limit, String cursor, Integer interval, String metd, Integer first, String sort, String key) {
        this.datastreamIds = datastreamIds;
        this.start = start;
        this.end = end;
        this.devId = devId;
        this.duration = duration;
        this.limit = limit;
        this.cursor = cursor;
        this.interval = interval;
        this.metd = metd;
        this.first = first;
        this.sort = sort;
        this.key = key;
        this.method = Method.GET;
        this.HttpMethod = new HttpGetMethod(this.method);
        Map<String, Object> headmap = new HashMap();
        Map<String, Object> urlmap = new HashMap();
        headmap.put("api-key", key);
        this.HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId + "/datapoints";
        if (datastreamIds != null) {
            urlmap.put("datastream_id", datastreamIds);
        }

        if (start != null) {
            urlmap.put("start", start);
        }

        if (end != null) {
            urlmap.put("end", end);
        }

        if (duration != null) {
            urlmap.put("duration", duration);
        }

        if (limit != null) {
            urlmap.put("limit", limit);
        }

        if (cursor != null) {
            urlmap.put("cursor", cursor);
        }

        if (interval != null) {
            urlmap.put("interval", interval);
        }

        if (metd != null) {
            urlmap.put("method", metd);
        }

        if (first != null) {
            urlmap.put("first", first);
        }

        if (sort != null) {
            urlmap.put("sort", sort);
        }

        this.HttpMethod.setcompleteUrl(this.url, urlmap);
    }

    public BasicResponse<DatapointsList> executeApi() {
        BasicResponse response = null;
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try {
            HttpResponse httpResponse = this.HttpMethod.execute();
            response = (BasicResponse)this.mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(this.mapper.writeValueAsString(response));
            Object newData = this.mapper.readValue(this.mapper.writeValueAsString(response.getDataInternal()), DatapointsList.class);
            response.setData(newData);
            return response;
        } catch (Exception var4) {
            var4.printStackTrace();
            this.logger.error("error: {}", var4.getMessage());
            throw new OnenetApiException(var4.getMessage());
        }
    }
}
