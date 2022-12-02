//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.iot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.*;

public class HttpsUtil extends DefaultHttpClient {
    public static final String HTTPGET = "GET";
    public static final String HTTPPUT = "PUT";
    public static final String HTTPPOST = "POST";
    public static final String HTTPDELETE = "DELETE";
    public static final String HTTPACCEPT = "Accept";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CHARSET_UTF8 = "UTF-8";
    private static HttpClient httpClient;

    public HttpsUtil() {
    }

    public void initSSLConfigForTwoWay() throws Exception {
        String selfcertpath = Constant.SELFCERTPATH;
        String trustcapath = Constant.TRUSTCAPATH;
        KeyStore selfCert = KeyStore.getInstance("pkcs12");
        selfCert.load(new FileInputStream(selfcertpath), Constant.SELFCERTPWD.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(selfCert, Constant.SELFCERTPWD.toCharArray());
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(trustcapath), Constant.TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), (SecureRandom)null);
        SSLSocketFactory ssf = new SSLSocketFactory(sc, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 8743, ssf));
        httpClient = new DefaultHttpClient(ccm);
    }

    public HttpResponse doPostJson(String url, Map<String, String> headerMap, String content) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        return this.executeHttpRequest(request);
    }

    public StreamClosedHttpResponse doPostMultipartFile(String url, Map<String, String> headerMap, File file) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        FileBody fileBody = new FileBody(file);
        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", fileBody).build();
        request.setEntity(reqEntity);
        return (StreamClosedHttpResponse)this.executeHttpRequest(request);
    }

    public StreamClosedHttpResponse doPostJsonGetStatusLine(String url, Map<String, String> headerMap, String content) {
        HttpPost request = new HttpPost(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        HttpResponse response = this.executeHttpRequest(request);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return (StreamClosedHttpResponse)response;
    }

    public StreamClosedHttpResponse doPostJsonGetStatusLine(String url, String content) {
        HttpPost request = new HttpPost(url);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        HttpResponse response = this.executeHttpRequest(request);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return (StreamClosedHttpResponse)response;
    }

    private List<NameValuePair> paramsConverter(Map<String, String> params) {
        List<NameValuePair> nvps = new LinkedList();
        Set<Map.Entry<String, String>> paramsSet = params.entrySet();
        Iterator var5 = paramsSet.iterator();

        while(var5.hasNext()) {
            Map.Entry<String, String> paramEntry = (Map.Entry)var5.next();
            nvps.add(new BasicNameValuePair((String)paramEntry.getKey(), (String)paramEntry.getValue()));
        }

        return nvps;
    }

    public StreamClosedHttpResponse doPostFormUrlEncodedGetStatusLine(String url, Map<String, String> formParams) throws Exception {
        HttpPost request = new HttpPost(url);
        request.setEntity(new UrlEncodedFormEntity(this.paramsConverter(formParams)));
        HttpResponse response = this.executeHttpRequest(request);
        if (response == null) {
            System.out.println("The response body is null.");
            throw new Exception();
        } else {
            return (StreamClosedHttpResponse)response;
        }
    }

    public HttpResponse doPutJson(String url, Map<String, String> headerMap, String content) {
        HttpPut request = new HttpPut(url);
        addRequestHeader(request, headerMap);
        request.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
        return this.executeHttpRequest(request);
    }

    public HttpResponse doPut(String url, Map<String, String> headerMap) {
        HttpPut request = new HttpPut(url);
        addRequestHeader(request, headerMap);
        return this.executeHttpRequest(request);
    }

    public StreamClosedHttpResponse doPutJsonGetStatusLine(String url, Map<String, String> headerMap, String content) {
        HttpResponse response = this.doPutJson(url, headerMap, content);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return (StreamClosedHttpResponse)response;
    }

    public StreamClosedHttpResponse doPutGetStatusLine(String url, Map<String, String> headerMap) {
        HttpResponse response = this.doPut(url, headerMap);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return (StreamClosedHttpResponse)response;
    }

    public HttpResponse doGetWithParas(String url, Map<String, String> queryParams, Map<String, String> headerMap) throws Exception {
        HttpGet request = new HttpGet();
        addRequestHeader(request, headerMap);

        URIBuilder builder;
        try {
            builder = new URIBuilder(url);
        } catch (URISyntaxException var7) {
            System.out.printf("URISyntaxException: {}", var7);
            throw new Exception(var7);
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            builder.setParameters(this.paramsConverter(queryParams));
        }

        request.setURI(builder.build());
        return this.executeHttpRequest(request);
    }

    public StreamClosedHttpResponse doGetWithParasGetStatusLine(String url, Map<String, String> queryParams, Map<String, String> headerMap) throws Exception {
        HttpResponse response = this.doGetWithParas(url, queryParams, headerMap);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return (StreamClosedHttpResponse)response;
    }

    public HttpResponse doDelete(String url, Map<String, String> headerMap) {
        HttpDelete request = new HttpDelete(url);
        addRequestHeader(request, headerMap);
        return this.executeHttpRequest(request);
    }

    public StreamClosedHttpResponse doDeleteGetStatusLine(String url, Map<String, String> headerMap) {
        HttpResponse response = this.doDelete(url, headerMap);
        if (response == null) {
            System.out.println("The response body is null.");
        }

        return (StreamClosedHttpResponse)response;
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

    private HttpResponse executeHttpRequest(HttpUriRequest request) {
        HttpResponse response = null;

        try {
            response = httpClient.execute(request);
        } catch (Exception var12) {
            System.out.println("executeHttpRequest failed.");
        } finally {
            try {
                response = new StreamClosedHttpResponse((HttpResponse)response);
            } catch (IOException var11) {
                System.out.println("IOException: " + var11.getMessage());
            }

        }

        return (HttpResponse)response;
    }

    public String getHttpResponseBody(HttpResponse response) throws UnsupportedOperationException, IOException {
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
