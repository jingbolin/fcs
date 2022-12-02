//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.iot.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public JsonUtil() {
    }

    public static String jsonObj2Sting(Object jsonObj) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(jsonObj);
        } catch (IOException var3) {
            System.out.printf("pasre json Object[{}] to string failed.", jsonString);
        }

        return jsonString;
    }

    public static <T> T jsonString2SimpleObj(String jsonString, Class<T> cls) {
        T jsonObj = null;

        try {
            jsonObj = objectMapper.readValue(jsonString, cls);
        } catch (IOException var4) {
            System.out.printf("pasre json Object[{}] to string failed.", jsonString);
        }

        return jsonObj;
    }

    public static <T> ObjectNode convertObject2ObjectNode(T object) throws Exception {
        if (object == null) {
            return null;
        } else {
            ObjectNode objectNode = null;
            if (object instanceof String) {
                objectNode = (ObjectNode)convertJsonStringToObject((String)object, ObjectNode.class);
            } else {
                objectNode = (ObjectNode)convertValue(object, ObjectNode.class);
            }

            return objectNode;
        }
    }

    public static <T> T convertJsonStringToObject(String jsonString, Class<T> cls) throws Exception {
        if (StringUtil.strIsNullOrEmpty(jsonString)) {
            return null;
        } else {
            try {
                T object = objectMapper.readValue(jsonString, cls);
                return object;
            } catch (Exception var3) {
                throw new Exception(var3);
            }
        }
    }

    private static <T> T convertValue(Object fromValue, Class<T> toValueType) throws Exception {
        try {
            return objectMapper.convertValue(fromValue, toValueType);
        } catch (IllegalArgumentException var3) {
            throw new Exception(var3);
        }
    }

    public static void main(String[] args) {
        try {
            convertObject2ObjectNode("{\"array\":\"688500850068704080f00b008578200001025634120101011111110055393837363534333231307F16\"}");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
