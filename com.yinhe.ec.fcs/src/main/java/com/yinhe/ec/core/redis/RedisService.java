package com.yinhe.ec.core.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService
{
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value)
	{
		boolean result = false;
		ValueOperations<Serializable, Object> operations = null;
		ValueOperations stringOperations = null;
		try
		{
			operations = redisTemplate.opsForValue();
			operations.set(key, value);
		}
		catch (Exception e)
		{
			try
			{
				stringOperations = stringRedisTemplate.opsForValue();
				stringOperations.set(key, value);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		result = true;
		return result;
	}

	/**
	 * 写入缓存设置时效时间
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime)
	{
		boolean result = false;
		ValueOperations<Serializable, Object> operations = null;
		ValueOperations stringOperations = null;
		try
		{
			operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
			try
			{
				stringOperations = stringRedisTemplate.opsForValue();
				stringOperations.set(key, value);
				stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		result = true;
		return result;
	}

	/**
	 * 批量删除对应的value
	 * @param keys
	 */
	public void remove(final String... keys)
	{
		for (String key : keys)
		{
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * @param pattern
	 */
	public void removePattern(final String pattern)
	{
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * @param key
	 */
	public void remove(final String key)
	{
		if (exists(key))
		{
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * @param key
	 * @return
	 */
	public boolean exists(final String key)
	{
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	public Object get(final String key)
	{
		Object result = null;
		try
		{
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			result = operations.get(key);
		}
		catch (Exception e)
		{
			try
			{
				ValueOperations stringOperations = stringRedisTemplate.opsForValue();
				result = stringOperations.get(key);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 哈希 添加
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hSet(String key, Object hashKey, Object value)
	{
		try
		{
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			if (null != hashKey)
			{
				hash.put(key, String.valueOf(hashKey), value);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 哈希 添加
	 * @param key
	 * @param valueMap
	 */
	public void hmSet(String key, Map valueMap)
	{
		try
		{
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			if (StringUtils.isNotEmpty(key))
			{
				hash.putAll(key, valueMap);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 哈希获取数据
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hGet(String key, Object hashKey)
	{
		Object result = null;
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		try
		{
			result = hash.get(key, hashKey);
		}
		catch (Exception e)
		{
			try
			{
				result = stringRedisTemplate.opsForHash().get(key, hashKey);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 哈希获取数据
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hmGet(String key, Collection<Object> hashKey)
	{
		Object result = null;
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		try
		{
			hash.multiGet(key, hashKey);
		}
		catch (Exception e)
		{
			try
			{
				result = stringRedisTemplate.opsForHash().multiGet(key, hashKey);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 哈希获取全部数据
	 * @param key
	 * @return
	 */
	public Map hmGetAll(String key)
	{
		Map resultMap = null;
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		try
		{
			resultMap = hash.entries(key);
		}
		catch (Exception e)
		{
			try
			{
				resultMap = stringRedisTemplate.opsForHash().entries(key);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * 列表添加
	 * @param k
	 * @param v
	 */
	public void lPush(String k, Object v)
	{
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush(k, v);
	}

	/**
	 * 列表获取
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public List<Object> lRange(String k, long l, long l1)
	{
		ListOperations<String, Object> list = redisTemplate.opsForList();
		return list.range(k, l, l1);
	}

	/**
	 * 集合添加
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value)
	{
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}

	/**
	 * 集合获取
	 * @param key
	 * @return
	 */
	public Set<Object> setMembers(String key)
	{
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		return set.members(key);
	}

	/**
	 * 有序集合添加
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public void zAdd(String key, Object value, double scoure)
	{
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add(key, value, scoure);
	}

	/**
	 * 有序集合获取
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double scoure, double scoure1)
	{
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return zset.rangeByScore(key, scoure, scoure1);
	}
}
