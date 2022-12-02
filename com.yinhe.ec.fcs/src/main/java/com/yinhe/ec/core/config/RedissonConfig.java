package com.yinhe.ec.core.config;

import com.yinhe.ec.core.support.cache.RedissonHelper;
import com.yinhe.ec.core.support.redisson.Client;
import com.yinhe.ec.core.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis连接配置
 */
@Configuration
@ConditionalOnClass(RedissonClient.class)
public class RedissonConfig
{
	@Bean
	public RedissonClient redissonClient()
	{
		Client client = new Client();
		String nodes = PropertiesUtil.getString("redis.cluster.nodes");
		if (StringUtils.isNotBlank(nodes))
		{
			client.setNodeAddresses(nodes);
		}
		else
		{
			String address = "redis://" + PropertiesUtil.getString("redis.host") + ":" + PropertiesUtil.getString("redis.port");
			client.setAddress(address);
		}
		client.setPassword(PropertiesUtil.getString("redis.password"));
		client.setTimeout(PropertiesUtil.getInt("redis.timeout"));
		return client.getRedissonClient();
	}

	@Bean
	public RedissonHelper redissonHelper(RedissonClient client)
	{
		RedissonHelper helper = new RedissonHelper();
		helper.setRedissonClient(client);
		return helper;
	}
}
