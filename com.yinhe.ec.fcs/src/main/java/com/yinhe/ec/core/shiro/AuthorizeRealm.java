package com.yinhe.ec.core.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.yinhe.ec.core.support.shiro.IRealm;
import com.yinhe.ec.core.support.shiro.RedisSessionDAO;
import com.yinhe.ec.cpps.sys.model.Resource;
import com.yinhe.ec.cpps.sys.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yinhe.ec.cpps.sys.service.ResourceService;
import com.yinhe.ec.cpps.sys.service.UserService;
import cn.hutool.core.lang.Console;

/**
 * 权限检查类
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:44:45
 */
@Component
public class AuthorizeRealm extends AuthorizingRealm implements IRealm
{
	@Autowired
	private UserService userService;

	@Autowired
	private ResourceService resourceService;

	private RedisSessionDAO sessionDAO;

	@Override
	public void setSessionDAO(RedisSessionDAO sessionDAO)
	{
		this.sessionDAO = sessionDAO;
	}

	public AuthorizeRealm()
	{
		// setCredentialsMatcher(new Md5CredentialsMatcher());
	}

	@Override
	public boolean supports(AuthenticationToken token)
	{
		return super.supports(token) || (token instanceof JwtToken);
	}

	/**
	 * 授权
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Long userId = user.getId();
		// 角色
		String saRolesStr = userService.getRoleStrByUserId(userId);
		Set<String> rolesSet = Arrays.stream(saRolesStr.split(",")).collect(Collectors.toSet());
		// 权限
		Resource resourceParam = new Resource();
		resourceParam.setUserId(userId);
		List<String> resourceStrList = resourceService.queryResourceStrList(resourceParam);
		Set<String> permissionsSet = new HashSet<>(resourceStrList);
		authorizationInfo.setRoles(rolesSet);
		authorizationInfo.setStringPermissions(permissionsSet);
		Console.log("用户:{}", user);
		Console.log("角色:{}", rolesSet);
		Console.log("权限:{}", permissionsSet);
		return authorizationInfo;
	}

	/**
	 * 登录验证
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		if (authenticationToken.getPrincipal() == null)
		{
			return null;
		}
		String account;
		if (authenticationToken instanceof JwtToken)
		{
			account = JWTUtil.getUsername(((JwtToken) authenticationToken).getToken());
		}
		else
		{
			account = (String) authenticationToken.getPrincipal();
		}
		User user = userService.getUserByAccount(account);
		SimpleAuthenticationInfo authenticationInfo;
		if (authenticationToken instanceof JwtToken)
		{
			authenticationInfo = new SimpleAuthenticationInfo(user, authenticationToken.getPrincipal(), getName());
		}
		else
		{
			authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		}
		return authenticationInfo;
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission)
	{
		User user = (User) principals.getPrimaryPrincipal();
		// 如果是管理员拥有所有的访问权限
		return "admin".equals(user.getAccount()) || super.isPermitted(principals, permission);
	}

	@Override
	public boolean hasRole(PrincipalCollection principals, String roleIdentifier)
	{
		User user = (User) principals.getPrimaryPrincipal();
		// 如果是管理员拥有所有的角色权限
		return "admin".equals(user.getAccount()) || super.hasRole(principals, roleIdentifier);
	}
}
