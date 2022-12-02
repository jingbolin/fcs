package com.yinhe.ec.cpps.sys.service.impl;

import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.util.CacheUtil;
import com.yinhe.ec.cpps.sys.service.SysCacheService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class SysCacheServiceImpl implements SysCacheService
{
	Logger logger = LogManager.getLogger();

	/**
	 * 清缓存
	 */
	@Override
	public void flush()
	{
		logger.info("清缓存开始......");
		CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*");
		logger.info("清缓存结束!");
	}

	/**
	 * 清缓存
	 */
	@Override
	public void flush(String key)
	{
		logger.info("清缓存[{}]开始......", key);
		CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*" + key + "*");
		if (key.contains("Permission"))
		{
			CacheUtil.getCache().delAll(Constants.CACHE_NAMESPACE + "*shiro_redis_cache*");
		}
		logger.info("清缓存[{}]结束!", key);
	}
}
