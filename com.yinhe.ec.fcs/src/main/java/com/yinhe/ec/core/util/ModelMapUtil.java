package com.yinhe.ec.core.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页辅助工具
 * @author ShenHuaJie
 * @since 2018年5月24日 下午6:32:32
 */
public final class ModelMapUtil
{
	private ModelMapUtil()
	{
	}

	private static String nvlString(Object obj)
	{
		return obj != null ? obj.toString().trim() : "";
	}

	public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields())
		{
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = nvlString(field.get(obj));
			map.put(fieldName, value);
		}
		return map;
	}

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception
	{
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		org.apache.commons.beanutils.BeanUtils.populate(obj, map);

		return obj;
	}

}
