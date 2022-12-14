package com.yinhe.ec.core.base;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinhe.ec.core.exception.BaseException;
import com.yinhe.ec.core.exception.IllegalParameterException;
import com.yinhe.ec.core.support.DateFormat;
import com.yinhe.ec.core.support.context.StringEscapeEditor;
import com.yinhe.ec.core.support.http.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import com.alibaba.fastjson.JSON;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.util.PropertiesUtil;

import cn.hutool.core.exceptions.ExceptionUtil;

/**
 * @author ShenHuaJie
 * @since 2018年5月24日 上午9:24:09
 */
@ControllerAdvice
public class AdviceController
{
	private Logger logger = LogManager.getLogger();

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new DateFormat("yyyy-MM-dd HH:mm:ss"), true));
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class,
				new StringEscapeEditor(PropertiesUtil.getBoolean("spring.mvc.htmlEscape", false),
						PropertiesUtil.getBoolean("spring.mvc.javaScriptEscape", false)));
	}

	/**
	 * 异常处理
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ModelMap> exceptionHandler(HttpServletRequest request, HttpServletResponse response,
			Throwable ex)
	{
		logger.error(Constants.EXCEPTION_HEAD, ex);
		ModelMap modelMap = new ModelMap();
		if (ex instanceof BaseException)
		{
			((BaseException) ex).handler(modelMap);
		}
		else if (ex instanceof IllegalArgumentException)
		{
			new IllegalParameterException(ex.getMessage()).handler(modelMap);
		}
		else if ("org.apache.shiro.authz.UnauthorizedException".equals(ex.getClass().getName()))
		{
			modelMap.put("code", HttpCode.FORBIDDEN.value().toString());
			modelMap.put("msg", HttpCode.FORBIDDEN.msg());
		}
		else
		{
			modelMap.put("code", HttpCode.INTERNAL_SERVER_ERROR.value().toString());
			String msg = StringUtils.defaultIfBlank(ExceptionUtil.getRootCause(ex).getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
			logger.debug(msg);
			modelMap.put("msg", msg.length() > 1000 ? "系统繁忙，请稍候再试." : msg);
		}
		modelMap.put("timestamp", System.currentTimeMillis());
		logger.info("response===>" + JSON.toJSON(modelMap));
		return ResponseEntity.ok(modelMap);
	}
}
