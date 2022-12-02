package com.yinhe.ec.core.interceptor;

import com.yinhe.ec.core.filter.CsrfFilter;
import com.yinhe.ec.core.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 签名验证
 * @author ShenHuaJie
 * @since 2018年5月12日 下午10:40:38
 */
public class TokenInterceptor extends BaseInterceptor
{
	private SignInterceptor signInterceptor;
	// 白名单
	private List<String> whiteUrls;
	private int size = 0;

	public TokenInterceptor()
	{
		signInterceptor = new SignInterceptor();
		// 读取文件
		/*String path = TokenInterceptor.class.getResource("/").getFile();
		whiteUrls = FileUtil.readFile(path + "white/tokenWhite.txt");
		size = null == whiteUrls ? 0 : whiteUrls.size();*/
		
		InputStream stream = CsrfFilter.class.getResourceAsStream("/white/signWhite.txt");
		whiteUrls = FileUtil.readFile(stream);
		size = null == whiteUrls ? 0 : whiteUrls.size();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		/*Cookie[] cookies = request.getCookies();
		Map<String,String> cookieMap=Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName,Cookie::getValue));
		String tokenStr=cookieMap.get("Admin-Token");
		String username=JWTUtil.getUsername(tokenStr);
		if(JWTUtil.isExpired(tokenStr)){
			return WebUtil.write(response, HttpCode.UNAUTHORIZED.value(), "token已过期,请重新登录!");
		}
		Console.log("username:{}",username);
		String url = request.getRequestURL().toString();
		String refer = request.getHeader("Referer");
		*//*if (refer != null && refer.contains("/swagger") || WebUtil.isWhiteRequest(url, size, whiteUrls))
		{
			logger.info("TokenInterceptor skip");
			if (signInterceptor.preHandle(request, response, handler))
			{
				return super.preHandle(request, response, handler);
			}
			return false;
		}*//*
		SessionUser session = null;
		// 获取token
		String token = request.getHeader("token");
		logger.debug("Token {}", token);
		if (DataUtil.isNotEmpty(token))
		{
			String cacheKey = Constants.TOKEN_KEY + SecurityUtil.encryptMd5(token);
			session = (SessionUser) CacheUtil.getCache().get(cacheKey);
			// 判断token是否过期
			if (DataUtil.isNotEmpty(session))
			{
				request.setAttribute(Constants.CURRENT_USER, session);
				boolean expire=CacheUtil.getCache().expire(cacheKey, PropertiesUtil.getInt("APP-TOKEN-EXPIRE", 60 * 60 * 24 * 7));
				if(expire){
					return WebUtil.write(response, HttpCode.UNAUTHORIZED.value(), "token已过期,请重新登录!");
				}
				logger.info("TokenInterceptor successful");
				if(handler instanceof HandlerMethod){
					HandlerMethod handlerMethod=(HandlerMethod)handler;
					RequiresPermissions reqPermissions= handlerMethod.getMethod().getAnnotation(RequiresPermissions.class);
					String[]  reqPermissionsArr =reqPermissions.value();
					StringJoiner reqPermissionSj=new StringJoiner(",");
					for (String reqPermission : reqPermissionsArr) {
						reqPermissionSj.add(reqPermission);
					}
					Console.log("reqPermission:{}",reqPermissionSj.toString());
				}
				if (signInterceptor.preHandle(request, response, handler))
				{
					return super.preHandle(request, response, handler);
				}
				return false;
			}else
			{
				return WebUtil.write(response, HttpCode.UNAUTHORIZED.value(), "会话已过期");
			}
		}
		else
		{
			return WebUtil.write(response, HttpCode.UNAUTHORIZED.value(), "请登录");
		}*/
		return super.preHandle(request,response,handler);
	}
}
