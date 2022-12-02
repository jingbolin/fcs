package com.yinhe.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = { "com.yinhe.ec" }, exclude = DataSourceAutoConfiguration.class)
public class ServiceApplication extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		SpringApplication.run(ServiceApplication.class, args);
	}
}
