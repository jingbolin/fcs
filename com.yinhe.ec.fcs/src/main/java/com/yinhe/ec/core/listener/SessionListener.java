package com.yinhe.ec.core.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.yinhe.ec.core.Constants;

/**
 * 会话监听器
 * @author ShenHuaJie
 * @version $Id: SessionListener.java, v 0.1 2014年3月28日 上午9:06:12 ShenHuaJie Exp
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SessionListener implements org.apache.shiro.session.SessionListener
{
	@Autowired
	RedisTemplate redisTemplate;
	private Logger logger = LogManager.getLogger();

	@Override
	public void onStart(Session session)
	{
		session.setAttribute(Constants.WEBTHEME, "default");
		logger.info("创建了一个Session连接:[" + session.getId() + "]");
		redisTemplate.opsForSet().add(Constants.ALLUSER_NUMBER, session.getId());
	}

	@Override
	public void onStop(Session session)
	{
		if (getAllUserNumber() > 0)
		{
			redisTemplate.opsForSet().remove(Constants.ALLUSER_NUMBER, session.getId());
			logger.info("销毁了一个Session连接:[" + session.getId() + "]");
		}
		session.removeAttribute(Constants.CURRENT_USER);
	}

	@Override
	public void onExpiration(Session session)
	{
		onStop(session);
	}

	/**
	 * 获取在线用户数量
	 */
	public Integer getAllUserNumber()
	{
		return redisTemplate.opsForSet().size(Constants.ALLUSER_NUMBER).intValue();
	}
}
