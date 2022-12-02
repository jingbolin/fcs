package com.yinhe.ec;

import com.yinhe.ec.core.config.WebMvcConfig;
import com.yinhe.ec.core.interceptor.EventInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.yinhe.ec")
public class WebConfig extends WebMvcConfig
{
	@Override
	@Bean
	public EventInterceptor eventInterceptor()
	{
		return new EventInterceptor();
	}
}
