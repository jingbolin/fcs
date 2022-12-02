package com.yinhe.ec.core.support.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.core.util.SerializeUtil;

/**
 * RedisSession
 * @author chenyh
 * @since 2020年3月9日下午5:58:13
 */
public class RedisSessionDAO extends AbstractSessionDAO
{
	private static final int EXPIRE_TIME = 31536000;//一年

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public void delete(Serializable sessionId)
	{
		if (sessionId != null)
		{
			byte[] sessionKey = buildRedisSessionKey(sessionId);
			RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
			RedisConnection conn = null;
			try
			{
				conn = RedisConnectionUtils.getConnection(factory);
				conn.del(sessionKey);
			}
			finally
			{
				RedisConnectionUtils.releaseConnection(conn, factory);
			}
		}
	}

	@Override
	protected Serializable doCreate(Session session)
	{
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId)
	{
		byte[] sessionKey = buildRedisSessionKey(sessionId);
		RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
		RedisConnection conn = null;
		try
		{
			conn = RedisConnectionUtils.getConnection(factory);
			byte[] value = conn.get(sessionKey);
			if (value == null)
			{
				return null;
			}
			Session session = SerializeUtil.deserialize(value, SimpleSession.class);
			return session;
		}
		finally
		{
			RedisConnectionUtils.releaseConnection(conn, factory);
		}
	}

	@Override
	public void update(Session session) throws UnknownSessionException
	{
		saveSession(session);
	}

	@Override
	public void delete(Session session)
	{
		if (session != null)
		{
			Serializable id = session.getId();
			if (id != null)
			{
				RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
				RedisConnection conn = null;
				try
				{
					conn = RedisConnectionUtils.getConnection(factory);
					conn.del(buildRedisSessionKey(id));
				}
				finally
				{
					RedisConnectionUtils.releaseConnection(conn, factory);
				}
			}
		}
	}

	@Override
	public Collection<Session> getActiveSessions()
	{
		List<Session> list = InstanceUtil.newArrayList();
		RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
		RedisConnection conn = null;
		try
		{
			conn = RedisConnectionUtils.getConnection(factory);
			Set<byte[]> set = conn.keys((Constants.REDIS_SHIRO_SESSION + "*").getBytes());
			for (byte[] key : set)
			{
				list.add(SerializeUtil.deserialize(conn.get(key), SimpleSession.class));
			}
		}
		finally
		{
			RedisConnectionUtils.releaseConnection(conn, factory);
		}
		return list;
	}

	public void saveSession(Session session)
	{

		if (session == null || session.getId() == null)
		{
			throw new UnknownSessionException("session is empty");
		}
		byte[] sessionKey = buildRedisSessionKey(session.getId());
		int sessionTimeOut = this.getSessionTimeOut();
		byte[] value = SerializeUtil.serialize(session);
		RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
		RedisConnection conn = null;
		try
		{
			conn = RedisConnectionUtils.getConnection(factory);
			conn.set(sessionKey, value, Expiration.seconds(sessionTimeOut), SetOption.UPSERT);
		}
		finally
		{
			RedisConnectionUtils.releaseConnection(conn, factory);
		}
	}

	/**
	 * 从Redis中获取系统超时时间
	 * @param sessionId
	 * @return
	 */
	private int getSessionTimeOut()
	{
		Object time = redisTemplate.opsForHash().get(Constants.REDIS_SHIRO_SESSION_PROP, "SessionTimeOut");
		if (time == null)
		{
			redisTemplate.opsForHash().put(Constants.REDIS_SHIRO_SESSION_PROP, "SessionTimeOut", EXPIRE_TIME);
			return EXPIRE_TIME;
		}
		return (int) time;
	}

	private byte[] buildRedisSessionKey(Serializable sessionId)
	{
		return (Constants.REDIS_SHIRO_SESSION + sessionId).getBytes();
	}
}
