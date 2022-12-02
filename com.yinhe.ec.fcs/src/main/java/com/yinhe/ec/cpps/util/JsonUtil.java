// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonUtil.java

package com.yinhe.ec.cpps.util;

//import net.sf.json.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil
{

    public JsonUtil()
    {
    }

    public static Object jsonString2Object(String jsonString, Class pojoCalss)
    {
//        JSONObject jsonObject = JSONObject.fromObject(jsonString);
//        Object pojo = JSONObject.toBean(jsonObject, pojoCalss);
//        return pojo;
        return null;
    }

//    public static Object jsonString2Object(String jsonString, Class pojoCalss, Map classMap)
//    {
//        JSONObject jobj = JSONObject.fromObject(jsonString);
//        return JSONObject.toBean(jobj, pojoCalss, classMap);
//    }
//
//    public static String object2JsonString(Object javaObj)
//    {
//        JSONObject json = JSONObject.fromObject(javaObj);
//        return json.toString();
//    }
//
//    public static List jsonList2JavaList(String jsonString, Class pojoClass)
//    {
//        JSONArray jsonArray = JSONArray.fromObject(jsonString);
//        List list = new ArrayList();
//        for(int i = 0; i < jsonArray.size(); i++)
//        {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            Object pojoValue = JSONObject.toBean(jsonObject, pojoClass);
//            list.add(pojoValue);
//        }
//
//        return list;
//    }
//
//    public static String javaList2JsonList(List list)
//    {
//        JSONArray jsonArray = JSONArray.fromObject(list);
//        return jsonArray.toString();
//    }
//
//    public static String javaArray2Json(Object array[])
//    {
//        JSONArray jsonarray = JSONArray.fromObject(((Object) (array)));
//        return jsonarray.toString();
//    }
//
//    public static Object jsonArray2JavaArrray(String jsonArray, Class clas)
//    {
//        JsonConfig jconfig = new JsonConfig();
//        jconfig.setArrayMode(2);
//        jconfig.setRootClass(clas);
//        JSONArray jarr = JSONArray.fromObject(jsonArray);
//        return JSONSerializer.toJava(jarr, jconfig);
//    }
//
//    public static String javaMap2Json(Map map)
//    {
//        return JSONObject.fromObject(map).toString();
//    }
//
//    public static Object javaMap2Json(String jsonString, Class pojoCalss)
//    {
//        return jsonString2Object(jsonString, pojoCalss);
//    }
}
