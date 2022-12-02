// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonHelper.java

package com.yinhe.ec.cpps.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonHelper
{

    public JsonHelper()
    {
    }

    public static Map toMap(Object javaBean)
    {
        Map result = new HashMap();
        Method methods[] = javaBean.getClass().getDeclaredMethods();
        Method amethod[];
        int j = (amethod = methods).length;
        for(int i = 0; i < j; i++)
        {
            Method method = amethod[i];
            try
            {
                if(method.getName().startsWith("get"))
                {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = (new StringBuilder(String.valueOf(field.toLowerCase().charAt(0)))).append(field.substring(1)).toString();
                    Object value = method.invoke(javaBean, null);
                    result.put(field, value != null ? ((Object) (value.toString())) : "");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static Map toMap(String jsonString)
        throws JSONException
    {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Map result = new HashMap();
        Set<String> strings = jsonObject.keySet();
        String key = null;
        String value = null;

        for (String s:strings) {
            key = s;
            value = jsonObject.getString(key);
            result.put(key, value);
        }

        return result;
    }

    public static JSONObject toJSON(Object bean)
    {
        return new JSONObject(toMap(bean));
    }

    public static Object toJavaBean(Object javabean, Map data)
    {
        Method methods[] = javabean.getClass().getDeclaredMethods();
        Method amethod[];
        int j = (amethod = methods).length;
        for(int i = 0; i < j; i++)
        {
            Method method = amethod[i];
            try
            {
                if(method.getName().startsWith("set"))
                {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = (new StringBuilder(String.valueOf(field.toLowerCase().charAt(0)))).append(field.substring(1)).toString();
                    method.invoke(javabean, new Object[] {
                        data.get(field)
                    });
                }
            }
            catch(Exception exception) { }
        }

        return javabean;
    }

    public static void toJavaBean(Object javabean, String jsonString)
        throws ParseException, JSONException
    {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Map map = toMap(jsonObject.toString());
        toJavaBean(javabean, map);
    }
}
