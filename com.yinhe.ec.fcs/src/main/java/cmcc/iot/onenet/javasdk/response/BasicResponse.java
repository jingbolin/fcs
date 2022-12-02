//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class BasicResponse<T> {
    public int errno;
    public String error;
    @JsonProperty("data")
    public Object dataInternal;
    public T data;
    @JsonIgnore
    public String json;

    public BasicResponse() {
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getErrno() {
        return this.errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @JsonGetter("data")
    public Object getDataInternal() {
        return this.dataInternal;
    }

    @JsonSetter("data")
    public void setDataInternal(Object dataInternal) {
        this.dataInternal = dataInternal;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
