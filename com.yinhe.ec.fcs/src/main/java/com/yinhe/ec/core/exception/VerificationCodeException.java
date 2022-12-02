package com.yinhe.ec.core.exception;

import org.apache.shiro.authc.CredentialsException;

/**
 * 抛出此异常，表示需要出现验证码
 */
public class VerificationCodeException extends CredentialsException
{
	private static final long serialVersionUID = 1L;

	public VerificationCodeException()
	{
		super();
	}

	public VerificationCodeException(String message)
	{
		super(message);
	}

	public VerificationCodeException(Throwable cause)
	{
		super(cause);
	}

	public VerificationCodeException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
