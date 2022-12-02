//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MAPIHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body;

    public MAPIHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = this.readBytes(request.getReader(), "utf-8");
    }

    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(this.body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    private byte[] readBytes(BufferedReader br, String encoding) throws IOException {
        String str = null;

        String retStr;
        for(retStr = ""; (str = br.readLine()) != null; retStr = retStr + str) {
        }

        return StringUtils.isNotBlank(retStr) ? retStr.getBytes(Charset.forName(encoding)) : null;
    }
}
