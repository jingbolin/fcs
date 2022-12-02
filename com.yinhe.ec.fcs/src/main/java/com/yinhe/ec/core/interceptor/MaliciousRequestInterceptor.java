package com.yinhe.ec.core.interceptor;

import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.filter.CsrfFilter;
import com.yinhe.ec.core.support.http.HttpCode;
import com.yinhe.ec.core.util.CacheUtil;
import com.yinhe.ec.core.util.FileUtil;
import com.yinhe.ec.core.util.WebUtil;

/**
 * 恶意请求拦截器
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:57
 */
public class MaliciousRequestInterceptor extends BaseInterceptor
{
	/** 拦截所有请求,否则拦截相同请求 */
	private boolean allRequest = false;
	/** 包含参数 */
	private boolean containsParamter = true;
	/** 允许的最小请求间隔 */
	private int minRequestIntervalTime = 100;
	/** 允许的最大恶意请求次数 */
	private int maxMaliciousTimes = 0;

	/** 白名单 */
	private List<String> whiteUrls;
	/**  */
	private int size = 0;

	public MaliciousRequestInterceptor()
	{
		String path = MaliciousRequestInterceptor.class.getResource("/").getFile();
		whiteUrls = FileUtil.readFile(path + "white/mrqWhite.txt");
		size = null == whiteUrls ? 0 : whiteUrls.size();
		InputStream stream = CsrfFilter.class.getResourceAsStream("/white/mrqWhite.txt");
		whiteUrls = FileUtil.readFile(stream);
		size = null == whiteUrls ? 0 : whiteUrls.size();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String url = request.getServletPath();
		if (url.endsWith("/unauthorized") || url.endsWith("/forbidden") || WebUtil.isWhiteRequest(url, size, whiteUrls) || url.contains("/read") || url.contains("/get")
				|| url.contains("/query"))
		{
			return super.preHandle(request, response, handler);
		}
		if (containsParamter)
		{
			url += JSON.toJSONString(WebUtil.getParameterMap(request));
		}
		Object userId = WebUtil.getCurrentUser(request);
		String user = userId != null ? userId.toString() : WebUtil.getHost(request) + request.getHeader("USER-AGENT");
		String preRequest = (String) CacheUtil.getCache().getFire(Constants.PREREQUEST + user);
		Long preRequestTime = (Long) CacheUtil.getCache().getFire(Constants.PREREQUEST_TIME + user);
		int seconds = minRequestIntervalTime;
		// 过滤频繁操作
		if (preRequestTime != null && preRequest != null)
		{
			boolean timeout = System.currentTimeMillis() - preRequestTime < minRequestIntervalTime;
			if ((url.equals(preRequest) || allRequest) && timeout)
			{
				Integer maliciousRequestTimes = (Integer) CacheUtil.getCache().getFire(Constants.MALICIOUS_REQUEST_TIMES + user);
				if (maliciousRequestTimes == null)
				{
					maliciousRequestTimes = 1;
				}
				else
				{
					maliciousRequestTimes++;
				}
				CacheUtil.getCache().set(Constants.MALICIOUS_REQUEST_TIMES + user, maliciousRequestTimes, seconds);
				if (maliciousRequestTimes > maxMaliciousTimes)
				{
					CacheUtil.getCache().set(Constants.MALICIOUS_REQUEST_TIMES + user, 0, seconds);
					logger.warn("To intercept a malicious request : {}", url);
					return WebUtil.write(response, HttpCode.MULTI_STATUS.value(), HttpCode.MULTI_STATUS.msg());
				}
			}
			else
			{
				CacheUtil.getCache().set(Constants.MALICIOUS_REQUEST_TIMES + user, 0, seconds);
			}
		}
		CacheUtil.getCache().set(Constants.PREREQUEST + user, url, seconds);
		CacheUtil.getCache().set(Constants.PREREQUEST_TIME + user, System.currentTimeMillis(), seconds);
		return super.preHandle(request, response, handler);
	}

	public MaliciousRequestInterceptor setAllRequest(boolean allRequest)
	{
		this.allRequest = allRequest;
		return this;
	}

	public MaliciousRequestInterceptor setContainsParamter(boolean containsParamter)
	{
		this.containsParamter = containsParamter;
		return this;
	}

	public MaliciousRequestInterceptor setMinRequestIntervalTime(int minRequestIntervalTime)
	{
		this.minRequestIntervalTime = minRequestIntervalTime;
		return this;
	}

	public MaliciousRequestInterceptor setMaxMaliciousTimes(int maxMaliciousTimes)
	{
		this.maxMaliciousTimes = maxMaliciousTimes;
		return this;
	}
}
