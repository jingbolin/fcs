package com.yinhe.ec.core.interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinhe.ec.cpps.sys.model.SysEvent;
import org.springframework.web.method.HandlerMethod;
import com.alibaba.fastjson.JSON;
import com.yinhe.ec.core.support.http.SessionUser;
import com.yinhe.ec.core.util.ThreadUtil;
import com.yinhe.ec.core.util.WebUtil;
import io.swagger.annotations.ApiOperation;

public class PropInterceptor extends BaseInterceptor
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, HttpServletResponse response, Object handler, final Exception ex) throws Exception
	{
		Map<String, Object> params = WebUtil.getParameterMap(request);
		String path = request.getServletPath();
		// 保存日志
		if (handler instanceof HandlerMethod)
		{
			try
			{
				String userAgent = request.getHeader("USER-AGENT");
				String clientIp = WebUtil.getHost(request);
				SessionUser session = WebUtil.getCurrentUser(request);
				if (!path.contains("/error") && !path.contains("/read/") && !path.contains("/get") && !path.contains("/query") && !path.contains("/detail")
						&& !path.contains("/unauthorized") && !path.contains("/forbidden"))
				{
					final SysEvent record = new SysEvent();
					record.setMethod(request.getMethod());
					record.setRequestUri(path);
					record.setClientHost(clientIp);
					record.setUserAgent(userAgent);
					if (path.contains("/upload"))
					{
						record.setParameters("");
					}
					else
					{
						record.setParameters(JSON.toJSONString(WebUtil.getParameter(request)));
					}
					record.setStatus(response.getStatus());
					if (session != null)
					{
						record.setUserName(session.getUserName());
						record.setUserPhone(session.getUserPhone());
						record.setCreateBy(session.getId());
						record.setUpdateBy(session.getId());
					}
					// final String msg = (String) request.getAttribute("msg");
					try
					{
						HandlerMethod handlerMethod = (HandlerMethod) handler;
						ApiOperation apiOperation = handlerMethod.getMethod().getAnnotation(ApiOperation.class);
						if (apiOperation != null)
						{
							record.setTitle(apiOperation.value());
						}
					}
					catch (Exception e)
					{
						logger.error("", e);
					}
					ThreadUtil.execute("sysEventLog", 10, 60, new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								// saveEvent(record);
							}
							catch (Exception e)
							{
								logger.error("Save event log cause error :", e);
							}
						}
					});
				}
				else if (path.contains("/unauthorized"))
				{
					logger.warn("The user [{}] no login", clientIp + "@" + userAgent);
				}
				else if (path.contains("/forbidden"))
				{
					logger.warn("The user [{}] no promission", JSON.toJSONString(session) + "@" + clientIp + "@" + userAgent);
				}
				else
				{
					logger.info(JSON.toJSONString(session) + "@" + path + "@" + clientIp + userAgent);
				}
			}
			catch (Throwable e)
			{
				logger.error("", e);
			}
		}
		super.afterCompletion(request, response, handler, ex);
	}

	protected void getProp(SysEvent record)
	{
		/*
		 * if (sysEventService == null) { sysEventService = ApplicationContextHolder.getService(SysEventService.class);
		 * } sysEventService.update(record);
		 */
	}
}
