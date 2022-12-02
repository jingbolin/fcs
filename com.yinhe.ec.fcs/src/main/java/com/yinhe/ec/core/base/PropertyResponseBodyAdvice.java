package com.yinhe.ec.core.base;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author chenyinghong
 * @since 2020年7月3日下午1:30:06
 */
@ConditionalOnClass(ResponseBodyAdvice.class)
@ControllerAdvice(basePackages = "com.yinhe.ec")
public class PropertyResponseBodyAdvice implements ResponseBodyAdvice<ModelMap> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public ModelMap beforeBodyWrite(ModelMap body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {

        return body;
    }
}
