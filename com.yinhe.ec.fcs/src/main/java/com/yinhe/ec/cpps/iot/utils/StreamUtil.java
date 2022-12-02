//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.iot.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
    private static final String DEFAULT_ENCODING = "utf-8";

    public StreamUtil() {
    }

    public static String inputStream2String(InputStream in, String charsetName) {
        if (in == null) {
            return null;
        } else {
            InputStreamReader inReader = null;

            try {
                if (StringUtil.strIsNullOrEmpty(charsetName)) {
                    inReader = new InputStreamReader(in, "utf-8");
                } else {
                    inReader = new InputStreamReader(in, charsetName);
                }

                int readLen = 0;
                char[] buffer = new char[1024];
                StringBuffer strBuf = new StringBuffer();

                while((readLen = inReader.read(buffer)) != -1) {
                    strBuf.append(buffer, 0, readLen);
                }

                String var7 = strBuf.toString();
                return var7;
            } catch (IOException var10) {
                System.out.println(var10);
            } finally {
                closeStream(inReader);
            }

            return null;
        }
    }

    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException var2) {
                System.out.println(var2);
            }
        }

    }
}
