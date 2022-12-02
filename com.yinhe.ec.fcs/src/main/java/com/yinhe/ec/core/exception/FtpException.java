package com.yinhe.ec.core.exception;

import com.yinhe.ec.core.support.http.HttpCode;

/**
 * FTP异常
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class FtpException extends BaseException
{
	public FtpException()
	{
	}

	public FtpException(String message)
	{
		super(message);
	}

	public FtpException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	@Override
	protected HttpCode getCode()
	{
		return HttpCode.INTERNAL_SERVER_ERROR;
	}
}
