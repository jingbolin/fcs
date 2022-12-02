package com.yinhe.ec.cpps.sys.model;

import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.core.util.DateUtil;
import lombok.Builder;
import org.springframework.ui.ModelMap;

import java.util.Date;

/**
 * 返回数据
 */
@Builder
public class ApiResult extends ModelMap {
    private static final long serialVersionUID = 1L;

    public ApiResult() {
        put("code", ApiCode.SUCCESS.getCode());
        put("msg", ApiCode.SUCCESS.getMsg());
        put("time", DateUtil.format(new Date(), DateUtil.DatePattern.YYYY_MM_DD_HH_MM_SS)).put("success", true);
    }

    public ApiResult(ApiCode apiCode) {
        new ApiResult().put("code", apiCode.getCode());
    }

    public static ApiResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ApiResult error(String msg) {
        return error(500, msg).put("success", false);
    }

    public static ApiResult error(int code, String msg) {
        ApiResult r = new ApiResult();
        r.put("code", code);
        r.put("msg", msg).put("success", false);
        return r;
    }

    public static ApiResult error(int code, Object data) {
        ApiResult r = new ApiResult();
        r.put("code", code).put("success", false);
        r.put("data", data);
        return r;
    }

    public static ApiResult error(ApiCode apiCode) {
        ApiResult r = new ApiResult();
        r.put("code", apiCode.getCode()).put("success", false);
        return r;
    }

    public static ApiResult error(ApiCode apiCode, Object data) {
        ApiResult r = new ApiResult();
        r.put("code", apiCode.getCode());
        r.put("data", data).put("success", false);
        return r;
    }

    public static ApiResult ok(String msg) {
        ApiResult r = new ApiResult();
        r.put("msg", msg);
        return r;
    }

    public static ApiResult ok(Object data) {
        ApiResult r = new ApiResult();
        r.put("data", data);
        return r;
    }

    public static ApiResult ok(String msg, Object data) {
        ApiResult r = new ApiResult();
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    public static ApiResult ok() {
        return new ApiResult();
    }

    public static ApiResult ok(ApiCode apiCode) {
        ApiResult r = new ApiResult();
        r.put("code", apiCode.getCode());
        return r;
    }

    @Override
    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
