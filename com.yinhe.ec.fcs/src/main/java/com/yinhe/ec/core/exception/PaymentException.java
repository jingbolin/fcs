package com.yinhe.ec.core.exception;

import com.yinhe.ec.core.support.http.HttpCode;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class PaymentException extends BaseException
{
	public PaymentException()
	{
	}

	public PaymentException(Throwable ex)
	{
		super(ex);
	}

	public PaymentException(String message)
	{
		super(message);
	}

	public PaymentException(String message, Throwable ex)
	{
		super(message, ex);
	}

	@Override
	protected HttpCode getCode()
	{
		return HttpCode.PAYMENT_FAILED;
	}
}