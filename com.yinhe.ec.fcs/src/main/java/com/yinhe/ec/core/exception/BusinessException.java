package com.yinhe.ec.core.exception;

import com.yinhe.ec.core.support.http.HttpCode;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException
{
	public BusinessException()
	{
	}

	public BusinessException(Throwable ex)
	{
		super(ex);
	}

	public BusinessException(String message)
	{
		super(message);
	}

	public BusinessException(String message, Throwable ex)
	{
		super(message, ex);
	}

	@Override
	protected HttpCode getCode()
	{
		return HttpCode.CONFLICT;
	}
}