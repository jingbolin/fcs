package com.yinhe.ec.core.config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.yinhe.ec.core.listener.SessionListener;
import com.yinhe.ec.core.support.cache.shiro.RedisCacheManager;
import com.yinhe.ec.core.support.shiro.IRealm;
import com.yinhe.ec.core.support.shiro.RedisSessionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import com.yinhe.ec.core.filter.ShiroLoginFilter;
import com.yinhe.ec.core.util.FileUtil;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.core.util.PropertiesUtil;

/**
 * 权限拦截配置
 */
@Configuration
@ConditionalOnBean(IRealm.class)
@ConditionalOnClass(RememberMeManager.class)
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class ShiroConfig
{
	private final static Logger logger = LogManager.getLogger();
	static String filters = "/=anon;/login=anon;/upload=anon;/getServerInfos=anon;/*.ico=anon;/upload/*=anon;" + "/unauthorized=anon;/forbidden=anon;/sns*=anon;/*/api-docs=anon;/callback*=anon;/swagger*=anon;"
			+ "/configuration/*=anon;/*/configuration/*=anon;/webjars/**=anon;/doc.html=anon;/v2/api-docs=anon;";

	static
	{
		try
		{
			InputStream stream = ShiroConfig.class.getResourceAsStream("/config/shiro.config");
			List<String> urlList = FileUtil.readFile(stream);
			if (urlList != null && urlList.size() > 0)
			{
				StringBuilder sb = new StringBuilder();
				for (String url : urlList)
				{
					sb.append(url.trim());
					if (!url.trim().endsWith(";"))
					{
						sb.append(";");
					}
				}
				filters = filters + sb.toString() + "/**=authc,user;";
			}
		}
		catch (Exception e)
		{
			logger.error("读取shiro配置发生错误", e);
		}
	}

	@Bean
	public SessionListener sessionListener()
	{
		return new SessionListener();
	}

	@Bean
	public SessionDAO sessionDao(IRealm realm)
	{
		RedisSessionDAO dao = new RedisSessionDAO();
		realm.setSessionDAO(dao);
		return dao;
	}

	@Bean
	public DefaultWebSecurityManager securityManager(AuthorizingRealm realm, SessionManager sessionManager, RememberMeManager rememberMeManager)
	{
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(realm);
		manager.setCacheManager(new RedisCacheManager());
		manager.setSessionManager(sessionManager);
		manager.setRememberMeManager(rememberMeManager);
		return manager;
	}

	@Bean
	public SessionManager sessionManager(SessionDAO sessionDao, SessionListener sessionListener, Cookie cookie)
	{
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDao);
		sessionManager.getSessionListeners().add(sessionListener);
		sessionManager.setSessionIdCookie(cookie);
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setGlobalSessionTimeout(-1);
		return sessionManager;
	}

	@Bean
	public Cookie cookie()
	{
		SimpleCookie cookie = new SimpleCookie("ECLOUDSESSIONID");
		cookie.setSecure(PropertiesUtil.getBoolean("session.cookie.secure", false));
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		return cookie;
	}

	@Bean
	public RememberMeManager rememberMeManager()
	{
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.getCookie().setMaxAge(PropertiesUtil.getInt("rememberMe.cookie.maxAge", 60 * 60 * 24));
		return rememberMeManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
	{
		ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
		factory.getFilters().put("authc", new ShiroLoginFilter());
		factory.setSecurityManager(securityManager);
		Map<String, String> filterMap = InstanceUtil.newLinkedHashMap();
		for (String filter : filters.split("\\;"))
		{
			String[] keyValue = filter.split("\\=");
			if (keyValue.length >= 2)
				filterMap.put(keyValue[0], keyValue[1]);
		}
		factory.setFilterChainDefinitionMap(filterMap);
		return factory;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
	{
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator()
	{
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAdvisor(org.apache.shiro.mgt.SecurityManager securityManager)
	{
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
