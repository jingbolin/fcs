//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.iot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

public class HttpUtil {
    public static final String HTTPGET = "GET";
    public static final String HTTPPUT = "PUT";
    public static final String HTTPPOST = "POST";
    public static final String HTTPDELETE = "DELETE";
    public static final String HTTPACCEPT = "Accept";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CHARSET_UTF8 = "UTF-8";

    public HttpUtil() {
    }

    public static HttpResponse doPost(String url, Map<String, String> headerMap, StringEntity stringEntity) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        request.setEntity(stringEntity);
        return executeHttpRequest(request);
    }

    public static HttpResponse doPost(String url, Map<String, String> headerMap, InputStream inStream) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new InputStreamEntity(inStream));
        return executeHttpRequest(request);
    }

    public static HttpResponse doPostJson(String url, Map<String, String> headerMap, String content) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        return executeHttpRequest(request);
    }

    public static String doPostJsonForString(String url, Map<String, String> headerMap, String content) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        HttpResponse response = executeHttpRequest(request);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return ((StreamClosedHttpResponse)response).getContent();
    }

    public static String doPostJsonForString(String url, String content) {
        HttpPost request = new HttpPost(url);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        HttpResponse response = executeHttpRequest(request);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return ((StreamClosedHttpResponse)response).getContent();
    }

    private static List<NameValuePair> paramsConverter(Map<String, String> params) {
        List<NameValuePair> nvps = new LinkedList();
        Set<Map.Entry<String, String>> paramsSet = params.entrySet();
        Iterator var4 = paramsSet.iterator();

        while(var4.hasNext()) {
            Map.Entry<String, String> paramEntry = (Map.Entry)var4.next();
            nvps.add(new BasicNameValuePair((String)paramEntry.getKey(), (String)paramEntry.getValue()));
        }

        return nvps;
    }

    public static String doPostFormUrlEncodedForString(String url, Map<String, String> formParams) throws Exception {
        HttpPost request = new HttpPost(url);
        request.setEntity(new UrlEncodedFormEntity(paramsConverter(formParams)));
        HttpResponse response = executeHttpRequest(request);
        if (response == null) {
            System.out.println("The response body is null.");
            throw new Exception();
        } else {
            return ((StreamClosedHttpResponse)response).getContent();
        }
    }

    public static HttpResponse doPut(String url, Map<String, String> headerMap, InputStream inStream) {
        HttpPut request = new HttpPut(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new InputStreamEntity(inStream));
        return executeHttpRequest(request);
    }

    public static HttpResponse doPutJson(String url, Map<String, String> headerMap, String content) {
        HttpPut request = new HttpPut(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        return executeHttpRequest(request);
    }

    public static String doPutJsonForString(String url, Map<String, String> headerMap, String content) {
        HttpResponse response = doPutJson(url, headerMap, content);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return ((StreamClosedHttpResponse)response).getContent();
    }

    public static HttpResponse doGet(String url, Map<String, String> headerMap) {
        HttpGet request = new HttpGet(url);
        addRequestHeader(request, headerMap);
        return executeHttpRequest(request);
    }

    public static HttpResponse doGetWithParas(String url, Map<String, String> queryParams, Map<String, String> headerMap) throws Exception {
        HttpGet request = new HttpGet();
        addRequestHeader(request, headerMap);

        URIBuilder builder;
        try {
            builder = new URIBuilder(url);
        } catch (URISyntaxException var6) {
            System.out.printf("URISyntaxException: {}", var6);
            throw new Exception(var6);
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            builder.setParameters(paramsConverter(queryParams));
        }

        request.setURI(builder.build());
        return executeHttpRequest(request);
    }

    public static String doGetWithParasForString(String url, Map<String, String> queryParams, Map<String, String> headerMap) throws Exception {
        HttpResponse response = doGetWithParas(url, queryParams, headerMap);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return ((StreamClosedHttpResponse)response).getContent();
    }

    public static HttpResponse doDelete(String url, Map<String, String> headerMap) {
        HttpDelete request = new HttpDelete(url);
        addRequestHeader(request, headerMap);
        return executeHttpRequest(request);
    }

    public static String doDeleteForString(String url, Map<String, String> headerMap) {
        HttpResponse response = doDelete(url, headerMap);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return ((StreamClosedHttpResponse)response).getContent();
    }

    private static void addRequestHeader(HttpUriRequest request, Map<String, String> headerMap) {
        if (headerMap != null) {
            Iterator var3 = headerMap.keySet().iterator();

            while(var3.hasNext()) {
                String headerName = (String)var3.next();
                if (!"Content-Length".equalsIgnoreCase(headerName)) {
                    String headerValue = (String)headerMap.get(headerName);
                    request.addHeader(headerName, headerValue);
                }
            }

        }
    }

    private static HttpResponse executeHttpRequest(HttpUriRequest request) {
        HttpResponse response = null;
        CloseableHttpClient httpclient = null;

        try {
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(request);
        } catch (Exception var12) {
            System.out.println("executeHttpRequest failed.");
        } finally {
            try {
                response = new StreamClosedHttpResponse((HttpResponse)response);
                httpclient.close();
            } catch (IOException var11) {
                System.out.println("IOException: " + var11.getMessage());
            }

        }

        return (HttpResponse)response;
    }

    public static String getHttpResponseBody(HttpResponse response) throws UnsupportedOperationException, IOException {
        if (response == null) {
            return null;
        } else {
            String body = null;
            if (response instanceof StreamClosedHttpResponse) {
                body = ((StreamClosedHttpResponse)response).getContent();
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null && entity.isStreaming()) {
                    String encoding = entity.getContentEncoding() != null ? entity.getContentEncoding().getValue() : null;
                    body = StreamUtil.inputStream2String(entity.getContent(), encoding);
                }
            }

            return body;
        }
    }
}
