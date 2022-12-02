package com.yinhe.ec.core.support.http;

import java.io.Serializable;
import com.yinhe.ec.cpps.sys.model.User;

/**
 * 用户会话信息
 * @author ShenHuaJie
 * @since 2018年7月22日 上午9:34:50
 */
@SuppressWarnings("serial")
public class SessionUser implements Serializable
{
	private Long id;
	private User user;
	private String account;
	private String userName;
	private String userPhone;
	private Boolean rememberMe = false;

	public SessionUser(Long id, String account, String userName, String userPhone, boolean rememberMe)
	{
		this.id = id;
		this.account = account;
		this.userName = userName;
		this.userPhone = userPhone;
		this.rememberMe = rememberMe;
	}

	public SessionUser()
	{
	}

	public Long getId()
	{
		return id;
	}

	public SessionUser setId(Long id)
	{
		this.id = id;
		return this;
	}

	public String getAccount()
	{
		return account;
	}

	public SessionUser setAccount(String account)
	{
		this.account = account;
		return this;
	}

	public SessionUser setUserName(String userName)
	{
		this.userName = userName;
		return this;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getUserPhone()
	{
		return userPhone;
	}

	public SessionUser setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
		return this;
	}

	public Boolean getRememberMe()
	{
		return rememberMe;
	}

	public SessionUser setRememberMe(Boolean rememberMe)
	{
		this.rememberMe = rememberMe;
		return this;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
