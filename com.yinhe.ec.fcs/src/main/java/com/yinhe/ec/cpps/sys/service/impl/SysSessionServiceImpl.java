package com.yinhe.ec.cpps.sys.service.impl;

import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.util.CacheUtil;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.cpps.sys.mapper.SysSessionMapper;
import com.yinhe.ec.cpps.sys.model.SysSession;
import com.yinhe.ec.cpps.sys.service.SysSessionService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@CacheConfig(cacheNames = "sysSession")
@Service
public class SysSessionServiceImpl extends BaseServiceImpl<SysSession, SysSessionMapper> implements SysSessionService
{
	@Override
	@CachePut
	@Transactional
	public SysSession update(SysSession record)
	{
		if (record != null && record.getId() == null)
		{
			record.setUpdateTime(new Date());
			Long id = mapper.queryBySessionId(record.getSessionId());
			if (id != null)
			{
				mapper.updateById(record);
			}
			else
			{
				record.setCreateTime(new Date());
				mapper.insert(record);
			}
		}
		else
		{
			mapper.updateById(record);
		}
		return record;
	}

	// 系统触发,由系统自动管理缓存
	@Override
	public void deleteBySessionId(final SysSession sysSession)
	{
		if (sysSession != null)
		{
			mapper.deleteBySessionId(sysSession.getSessionId());
		}
	}

	@Override
	public List<String> querySessionIdByAccount(SysSession sysSession)
	{
		if (sysSession != null)
		{
			return mapper.querySessionIdByAccount(sysSession.getAccount());
		}
		return null;
	}

	//
	@Override
	public void cleanExpiredSessions()
	{
		Map<String, Object> columnMap = InstanceUtil.newHashMap();
		List<SysSession> sessions = queryList(columnMap);
		for (SysSession sysSession : sessions)
		{
			if (sysSession != null)
			{
				logger.info("检查SESSION : {}", sysSession.getSessionId());
				if (!CacheUtil.getCache().exists(Constants.REDIS_SHIRO_SESSION + sysSession.getSessionId()))
				{
					logger.info("移除SESSION : {}", sysSession.getSessionId());
					delete(sysSession.getId());
				}
			}
		}
	}
}
