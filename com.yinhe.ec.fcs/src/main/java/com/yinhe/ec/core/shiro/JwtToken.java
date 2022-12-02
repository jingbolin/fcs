package com.yinhe.ec.core.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JSON Web Token
 */
public class JwtToken implements AuthenticationToken
{

	private static final long serialVersionUID = 1282057025599826155L;
	private String token;

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public JwtToken(String token)
	{
		this.token = token;
	}

	@Override
	public Object getPrincipal()
	{
		return token;
	}

	@Override
	public Object getCredentials()
	{
		return token;
	}
}
