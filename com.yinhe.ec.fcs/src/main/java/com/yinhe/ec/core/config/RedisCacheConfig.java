package com.yinhe.ec.core.config;

import com.alibaba.fastjson.JSON;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.annotations.RedisKeyGenerator;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Redis缓存配置
 */
@Configuration
@ConditionalOnClass(CacheConfig.class)
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport
{
	String prefix = Constants.CACHE_NAMESPACE + "M:";

	@Override
	@Bean
	public KeyGenerator keyGenerator()
	{
		return new KeyGenerator()
		{

			/** 重写生成key方法 */
			@Override
			public Object generate(Object o, Method method, Object... paramsObjs)
			{
				StringBuilder sb = new StringBuilder(prefix);
				CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
				Cacheable cacheable = method.getAnnotation(Cacheable.class);
				CachePut cachePut = method.getAnnotation(CachePut.class);
				CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
				Annotation[][] parameterAnnotations = method.getParameterAnnotations();
				if (ArrayUtils.isNotEmpty(parameterAnnotations))
				{
					for (Annotation[] parameterAnnotation : parameterAnnotations)
					{
						int paramIndex = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
						for (Annotation annotation : parameterAnnotation)
						{
							if (annotation instanceof RedisKeyGenerator)
							{
								RedisKeyGenerator redisKeyGenerator = (RedisKeyGenerator) annotation;
								String fieldName = redisKeyGenerator.value();
								Object paramValueObj = paramsObjs[paramIndex];
								Class clazz = paramValueObj.getClass();
								PropertyDescriptor descriptor = null;
								try
								{
									descriptor = new PropertyDescriptor(fieldName, clazz);
									Method readMethod = descriptor.getReadMethod();
									Object readValue = readMethod.invoke(paramValueObj);
									sb.append(readValue).append(":");
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						}
					}
					return sb.toString().substring(0, sb.lastIndexOf(":"));
				}
				String className = o.getClass().getSimpleName();
				String methodName = method.getName();
				if (cacheable != null)
				{
					String[] cacheNames = cacheable.value();
					if (ArrayUtils.isNotEmpty(cacheNames))
					{
						sb.append(cacheNames[0]);
					}
					else
					{
						sb.append(className + "." + methodName);
					}
				}
				else if (cachePut != null)
				{
					String[] cacheNames = cachePut.value();
					if (ArrayUtils.isNotEmpty(cacheNames))
					{
						sb.append(cacheNames[0]);
					}
					else
					{
						sb.append(className + "." + methodName);
					}
				}
				else if (cacheEvict != null)
				{
					String[] cacheNames = cacheEvict.value();
					if (ArrayUtils.isNotEmpty(cacheNames))
					{
						sb.append(cacheNames[0]);
					}
					else
					{
						sb.append(className + "." + methodName);
					}
				}
				if (cacheConfig != null && sb.toString().equals(prefix))
				{
					String[] cacheNames = cacheConfig.cacheNames();
					if (ArrayUtils.isNotEmpty(cacheNames))
					{
						sb.append(cacheNames[0]);
					}
				}
				if (sb.toString().equals(prefix))
				{
					sb.append(o.getClass().getName()).append(".").append(method.getName());
				}
				sb.append(":");
				if (paramsObjs != null)
				{
					for (Object object : paramsObjs)
					{
						sb.append(JSON.toJSONString(object));
					}
				}
				return sb.toString();
			}
		};
	}
}
