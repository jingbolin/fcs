package com.yinhe.ec.core.interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinhe.ec.core.util.*;
import com.yinhe.ec.core.util.WebUtil;
import com.yinhe.ec.cpps.sys.model.SysEvent;
import org.springframework.core.NamedThreadLocal;
import com.alibaba.fastjson.JSON;
import com.yinhe.ec.core.support.context.ApplicationContextHolder;
import com.yinhe.ec.cpps.sys.service.SysEventService;


/**
 * 日志拦截器
 * @author chenyh
 * @since 2020年2月29日上午9:31:51
 */
public class EventInterceptor extends BaseInterceptor
{
	private final ThreadLocal<Long> STARTTIME_THREADLOCAL = new NamedThreadLocal<Long>("ThreadLocalStartTime");

	private SysEventService sysEventService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String path = request.getServletPath();
		// 开始时间（该数据只有当前请求的线程可见）
		STARTTIME_THREADLOCAL.set(System.currentTimeMillis());
		Map<String, Object> params = WebUtil.getParameterMap(request);
		logger.info("request {} parameters===>{}", path, JSON.toJSONString(params));
		WebUtil.REQUEST.set(request);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, HttpServletResponse response, Object handler, final Exception ex) throws Exception {}

	protected void saveEvent(SysEvent record)
	{
		if (sysEventService == null)
		{
			sysEventService = ApplicationContextHolder.getService(SysEventService.class);
		}
		sysEventService.update(record);
	}
}
