package com.yinhe.ec.base.service;

/**
 * @author chenyh
 * @since 2019年5月16日上午9:21:14
 */
public abstract class CacheService
{
	protected long spaceTime = 300000L;

	protected long lastTime = 0L;

	protected abstract void doClear();

	protected void doInit()
	{
		if (System.currentTimeMillis() - lastTime > spaceTime)
		{
			doClear();
			lastTime = System.currentTimeMillis();
		}
	}
}
