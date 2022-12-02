package com.yinhe.ec.core.shiro;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yinhe.ec.core.Constants;
import cn.hutool.core.lang.Console;

public class JWTUtil
{
	static Logger logger = LogManager.getLogger();

	/**
	 * 校验 token是否正确
	 * @param token
	 * 密钥
	 * @param secret
	 * 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String username, String secret)
	{
		try
		{
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
			verifier.verify(token);
			logger.info("token is valid");
			return true;
		}
		catch (Exception e)
		{
			logger.error("token is invalid{}", e.getMessage());
			return false;
		}
	}

	/**
	 * 从 token中获取用户名
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token)
	{
		try
		{
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		}
		catch (JWTDecodeException e)
		{
			logger.error("error：{}", e.getMessage());
			return null;
		}
	}

	public static boolean isExpired(String token)
	{
		DecodedJWT jwt = JWT.decode(token);
		Date expireDate = jwt.getExpiresAt();
		String expireDateStr = DateFormatUtils.format(expireDate, "yyyy-MM-dd HH:mm:ss");
		String currentDateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		Console.log("currentDateStr:{}", currentDateStr);
		Console.log("expireDate:{}", expireDateStr);
		if (System.currentTimeMillis() <= expireDate.getTime())
		{
			return false;
		}
		return true;
	}

	/**
	 * 生成 token
	 * @param username
	 * 用户名
	 * @param secret
	 * 用户的密码
	 * @return token
	 */
	public static String sign(String username, String secret)
	{
		try
		{
			username = StringUtils.lowerCase(username);
			Date date = new Date(Constants.JWT_TIME_OUT);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
		}
		catch (Exception e)
		{
			logger.error("error：{}", e);
			return null;
		}
	}

	public static void main(String[] args)
	{
		// String token=sign("admin", "111111");
		// String
		// token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTE2ODU4MTYsInVzZXJuYW1lIjoiYWRtaW4ifQ._GWNTiLtAq0ho_B5pSjHl08Law17OaSRG1RDxmeT6O0";
		/*
		 * String token=
		 * "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTA0NzY2MzgsInVzZXJuYW1lIjoiYWRtaW4ifQ.sd52fqzhSpuW7TVG8i3KwQk_yayTbH9Xh0v-b8YfdPY";
		 * String userName=getUsername(token); Console.log(userName); boolean isExpired=isExpired(token);
		 * Console.log(isExpired);
		 */
		String result = getUsername("sdsdfsds");
		Console.log("结果:{}", result);
	}
}
