/**
 *
 */
package com.yinhe.ec.core.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @author ShenHuaJie
 * @version 2018年1月5日 上午9:46:04
 */
@SuppressWarnings("rawtypes")
public class ApplicationReadyListener implements ApplicationListener
{
	protected final Logger logger = LogManager.getLogger();

	@Override
	public void onApplicationEvent(ApplicationEvent event)
	{
		if (event instanceof ApplicationEnvironmentPreparedEvent)
		{
			logger.info("==========初始化环境变量==============");
		}
		else if (event instanceof ApplicationPreparedEvent)
		{
			logger.info("==========初始化完成==============");
		}
		else if (event instanceof ContextRefreshedEvent)
		{
			logger.info("==========应用刷新==============");
		}
		else if (event instanceof ApplicationReadyEvent)
		{
			logger.info("=================================");
			String server = ((ApplicationReadyEvent) event).getSpringApplication().getAllSources().iterator().next().toString();
			logger.info("系统[{}]启动完成!!!", server.substring(server.lastIndexOf(".") + 1));
			logger.info("=================================");
		}
		else if (event instanceof ContextStartedEvent)
		{
			logger.info("==========应用启动==============");
		}
		else if (event instanceof ContextStoppedEvent)
		{
			logger.info("==========应用停止==============");
		}
		else if (event instanceof ContextClosedEvent)
		{
			logger.info("==========应用关闭==============");
		}
		else
		{
		}
	}
}
