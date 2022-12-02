package com.yinhe.ec;

import com.yinhe.ec.core.listener.ApplicationReadyListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import com.yinhe.ec.cpps.sys.service.SysCacheService;

@Component
public class ServerListener extends ApplicationReadyListener
{
	protected final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(ApplicationEvent event)
	{

		if (event instanceof ApplicationReadyEvent)
		{
			ConfigurableApplicationContext context = ((ApplicationReadyEvent) event).getApplicationContext();
			context.getBean(SysCacheService.class).flush();
			logger.info("注入Bean数量为：" + context.getBeanDefinitionCount());
			String[] names = context.getBeanDefinitionNames();
			for (int i = 0; i < names.length; i++)
			{
				logger.info(names[i]);
			}
			logger.info("应用启动完成。。。");
		}
		super.onApplicationEvent(event);
	}
}
