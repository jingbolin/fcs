package com.yinhe.ec.core.support.login;

import com.yinhe.ec.core.exception.LoginException;
import com.yinhe.ec.cpps.sys.model.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import com.yinhe.ec.core.support.context.Resources;

/**
 * @author chenyh
 * @since 2020年1月2日下午4:25:58
 */
public final class LoginHelper
{
	private LoginHelper()
	{
	}
	/**
	 * 用户登录
	 */
	public static final Boolean login(Login user, String host)
	{
		UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword(), host);
		token.setRememberMe(user.getRememberMe());
		Subject subject = SecurityUtils.getSubject();
		try
		{
			subject.login(token);
			return subject.isAuthenticated();
		}
		catch (LockedAccountException e)
		{
			throw new LoginException(Resources.getMessage("ACCOUNT_LOCKED", token.getPrincipal()));
		}
		catch (DisabledAccountException e)
		{
			throw new LoginException(Resources.getMessage("ACCOUNT_DISABLED", token.getPrincipal()));
		}
		catch (ExpiredCredentialsException e)
		{
			throw new LoginException(Resources.getMessage("ACCOUNT_EXPIRED", token.getPrincipal()));
		}
		catch (Exception e)
		{
			throw new LoginException(Resources.getMessage("LOGIN_FAIL"), e);
		}
	}
}
