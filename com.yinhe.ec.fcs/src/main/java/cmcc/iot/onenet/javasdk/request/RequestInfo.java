//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.request;

import java.util.Map;

public interface RequestInfo {
    void setHeader(Map<String, Object> var1);

    void setEntity(Object var1);

    void setEntity(Map<String, String> var1, Map<String, String> var2);

    void setcompleteUrl(String var1, Map<String, Object> var2);

    void sethttpMethod(Method var1);

    void setType(boolean var1);

    public static enum Method {
        POST,
        GET,
        DELETE,
        PUT;

        private Method() {
        }
    }
}
