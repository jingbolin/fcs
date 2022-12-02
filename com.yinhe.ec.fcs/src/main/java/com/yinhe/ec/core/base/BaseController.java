/**
 *
 */
package com.yinhe.ec.core.base;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.yinhe.ec.core.support.context.ApplicationContextHolder;
import com.yinhe.ec.core.util.ThreadUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 控制器基类
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class BaseController<T extends BaseModel, S extends BaseService<T>> extends AbstractController implements InitializingBean
{
	@Autowired
	protected S service;

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception
	{
		if (service == null)
		{
			ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			Class<?> cls = (Class<?>) type.getActualTypeArguments()[1];
			try
			{
				service = (S) ApplicationContextHolder.getService(cls);
			}
			catch (Exception e)
			{
				logger.error("", e);
				ThreadUtil.sleep(1, 5);
				afterPropertiesSet();
			}
		}
		super.afterPropertiesSet();
	}

	/** 分页查询 */
	public Object query(Map<String, Object> param)
	{
		return query(new ModelMap(), param);
	}

	/** 分页查询 */
	public Object query(ModelMap modelMap, Map<String, Object> param)
	{
		if (param.get("keyword") == null && param.get("search") != null)
		{
			param.put("keyword", param.get("search"));
			param.remove("search");
		}
		Object page = service.query(param);
		return setSuccessModelMap(modelMap, page);
	}

	/** 查询 */
	public Object queryList(Map<String, Object> param)
	{
		return queryList(new ModelMap(), param);
	}

	/** 查询 */
	public Object queryList(ModelMap modelMap, Map<String, Object> param)
	{
		List<?> list = service.queryList(param);
		return setSuccessModelMap(modelMap, list);
	}

	public Object get(BaseModel param)
	{
		return get(new ModelMap(), param);
	}

	public Object get(ModelMap modelMap, BaseModel param)
	{
		Object result = service.queryById(param.getId());
		return setSuccessModelMap(modelMap, result);
	}

	public Object update(T param)
	{
		return update(new ModelMap(), param);
	}

	public Object update(ModelMap modelMap, T param)
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (null != request)
		{
			Long userId = getCurrUserId(request);
			if (param.getId() == null && userId != null)
			{
				param.setCreateBy(userId);
				param.setCreateTime(new Date());
			}
			param.setUpdateBy(userId);
			param.setUpdateTime(new Date());
		}
		T result = service.update(param);
		param.setId(result.getId());
		return setSuccessModelMap(modelMap);
	}

	/** 物理删除 */
	public Object delete(T param)
	{
		return delete(new ModelMap(), param);
	}

	/** 物理删除 */
	public Object delete(ModelMap modelMap, T param)
	{
		Assert.notNull(param.getId(), "ID不能为空");
		service.delete(param.getId());
		return setSuccessModelMap(modelMap);
	}

	/** 逻辑删除 */
	public Object del(HttpServletRequest request, T param)
	{
		return del(new ModelMap(), request, param);
	}

	/** 逻辑删除 */
	public Object del(ModelMap modelMap, HttpServletRequest request, T param)
	{
		Long userId = getCurrUserId(request);
		if (param.getId() != null)
		{
			Assert.notNull(param.getId(), "ID不能为空");
			service.del(param.getId(), userId);
		}
		if (param.getIds() != null)
		{
			Assert.notNull(param.getIds(), "ID不能为空");
			service.del(param.getIds(), userId);
		}
		return setSuccessModelMap(modelMap);
	}
}
