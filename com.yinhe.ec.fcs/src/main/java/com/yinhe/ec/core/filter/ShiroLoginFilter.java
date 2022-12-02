package com.yinhe.ec.core.filter;

import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.yinhe.ec.core.support.http.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.util.WebUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;

public class ShiroLoginFilter extends FormAuthenticationFilter
{
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
	{
		if (isLoginRequest(request, response))
		{
			if (isLoginSubmission(request, response))
			{
				return executeLogin(request, response);
			}
			else
			{
				return true;
			}
		}
		else
		{
			if (null == redisTemplate)
			{
				BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
				redisTemplate = factory.getBean("redisTemplate", RedisTemplate.class);
			}
			Set sessionKeySet = redisTemplate.keys(Constants.REDIS_SHIRO_SESSION + "*");
			if (CollUtil.isEmpty(sessionKeySet))
			{
				redisTemplate.delete(Constants.ALLUSER_NUMBER);
			}
			else
			{
				Set<String> allUserNumberSet = redisTemplate.opsForSet().members(Constants.ALLUSER_NUMBER);
				String[] excludeArr = allUserNumberSet.stream().filter(t -> !sessionKeySet.contains(Constants.REDIS_SHIRO_SESSION + t) && StringUtils.isNotEmpty(t))
						.toArray(String[]::new);
				if (ArrayUtil.isNotEmpty(excludeArr))
				{
					redisTemplate.opsForSet().remove(Constants.ALLUSER_NUMBER, excludeArr);
				}
			}
			return WebUtil.write(response, HttpCode.SESSION_TIMEOUT.value(), "登录超时，请重新登录!");
		}
	}
}
