/**
 *
 */
package com.yinhe.ec.core.support.context;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.yinhe.ec.core.util.InstanceUtil;

/**
 * @author ShenHuaJie
 * @version 2017年12月6日 上午11:53:31
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware
{
	private static final Logger logger = LogManager.getLogger();
	private static final Map<String, Object> SERVICE_FACTORY = InstanceUtil.newHashMap();
	static ApplicationContext applicationContext;

	public static <T> T getBean(Class<T> t)
	{
		return applicationContext.getBean(t);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> t)
	{
		return applicationContext.getBeansOfType(t);
	}

	public static Object getBean(String name)
	{
		return applicationContext.getBean(name);
	}

	public static void printBeanNames()
	{
		for (String name : applicationContext.getBeanDefinitionNames())
		{
			logger.info(name);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> T getService(Class<T> cls)
	{
		String clsName = cls.getName();
		T v = (T) SERVICE_FACTORY.get(clsName);
		if (v == null)
		{
			synchronized (clsName)
			{
				v = (T) SERVICE_FACTORY.get(clsName);
				if (v == null)
				{
					logger.info("*****Autowire {}*****", cls);
					v = ApplicationContextHolder.getBean(cls);
					logger.info("*****{} Autowired*****", cls);
					SERVICE_FACTORY.put(clsName, v);
				}
			}
		}
		return v;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		ApplicationContextHolder.applicationContext = applicationContext;
	}
}
