package com.yinhe.ec.core.support.dbcp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.PropertiesUtil;

public class HandleDataSource
{
	private static final Logger logger = LogManager.getLogger();
	// 数据源名称线程池
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();
	/**
	 *
	 */
	static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

	public static void putDataSource(String datasource)
	{
		holder.set(datasource);
	}

	public static String getDataSource()
	{
		return holder.get();
	}

	public static void clear()
	{
		holder.remove();
	}

	public static void setDataSource(String service, String method)
	{
		logger.info(service + "." + method + "=>start...");
		if (DataUtil.isNotEmpty(PropertiesUtil.getString("druid.reader.url")))
		{
			try
			{
				L: for (String key : METHODTYPE.keySet())
				{
					for (String type : METHODTYPE.get(key))
					{
						if (method.startsWith(type))
						{
							logger.info(service + "." + method + "=>" + key);
							putDataSource(key);
							break L;
						}
					}
				}
			}
			catch (Exception e)
			{
				logger.error("", e);
				putDataSource("write");
			}
		}
	}
}
