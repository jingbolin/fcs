//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.iot.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    public static final String LINE_SEPARATOR = "\r\n";

    public ExceptionUtil() {
    }

    public static String getExceptionStackTrace(Throwable e) {
        String stackTrace = "";
        StringWriter writer = null;
        PrintWriter bw = null;
        if (e == null) {
            return "";
        } else {
            try {
                writer = new StringWriter();
                bw = new PrintWriter(writer);
                e.printStackTrace(bw);
                stackTrace = writer.getBuffer().toString();
            } catch (Exception var17) {
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception var16) {
                    }
                }

                if (bw != null) {
                    try {
                        bw.close();
                    } catch (Exception var15) {
                    }
                }

            }

            return stackTrace;
        }
    }

    public static String getExceptionStackTrace(Throwable e, int lineNum) {
        if (e == null) {
            return "";
        } else {
            StringBuffer stackTrace = new StringBuffer(e.toString());
            StackTraceElement[] astacktraceelement = e.getStackTrace();
            int size = lineNum > astacktraceelement.length ? astacktraceelement.length : lineNum;

            for(int i = 0; i < size; ++i) {
                stackTrace.append("\r\n").append("\tat ").append(astacktraceelement[i]);
            }

            return stackTrace.toString();
        }
    }

    public static String getStackTraceLog(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements == null) {
            return "";
        } else {
            StringBuffer stackTrace = new StringBuffer();
            int size = stackTraceElements.length;

            for(int i = 0; i < size; ++i) {
                stackTrace.append("\r\n").append("\tat ").append(stackTraceElements[i]);
            }

            return stackTrace.toString();
        }
    }

    public static String getBriefExceptionStackTrace(Throwable e) {
        return getExceptionStackTrace(e, 5);
    }
}
