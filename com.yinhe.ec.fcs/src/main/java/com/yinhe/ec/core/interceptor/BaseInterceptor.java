package com.yinhe.ec.core.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenyh
 * @since  2020年10月28日上午10:21:46
 */
public class BaseInterceptor extends HandlerInterceptorAdapter
{
	protected static final Logger logger = LogManager.getLogger();
	private BaseInterceptor[] nextInterceptor;

	public void setNextInterceptor(BaseInterceptor... nextInterceptor)
	{
		this.nextInterceptor = nextInterceptor;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (nextInterceptor == null)
		{
			return true;
		}
		for (int i = 0; i < nextInterceptor.length; i++)
		{
			if (!nextInterceptor[i].preHandle(request, response, handler))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		if (nextInterceptor != null)
		{
			for (BaseInterceptor element : nextInterceptor)
			{
				element.postHandle(request, response, handler, modelAndView);
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		if (nextInterceptor != null)
		{
			for (BaseInterceptor element : nextInterceptor)
			{
				element.afterCompletion(request, response, handler, ex);
			}
		}
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (nextInterceptor != null)
		{
			for (BaseInterceptor element : nextInterceptor)
			{
				element.afterConcurrentHandlingStarted(request, response, handler);
			}
		}
	}
}
