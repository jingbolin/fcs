package com.yinhe.ec.core.support.http;

import java.util.List;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.yinhe.ec.core.support.Pagination;
import com.yinhe.ec.core.util.DataUtil;

/**
 * @author ShenHuaJie
 * @since 2018年8月29日 下午2:05:34
 */
public class ReturnValueHandler implements HandlerMethodReturnValueHandler
{
	private HandlerMethodReturnValueHandler handler;

	public ReturnValueHandler(HandlerMethodReturnValueHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType)
	{
		return handler.supportsReturnType(returnType);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception
	{
		if (returnValue != null)
		{
			if (returnValue instanceof ResponseEntity || returnValue instanceof ApiResult)
			{
				handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
			}
			else
			{
				ModelMap modelMap = new ModelMap();
				if (returnValue instanceof Pagination<?>)
				{
					Pagination<?> page = (Pagination<?>) returnValue;
					modelMap.put("rows", page.getRecords());
					modelMap.put("current", page.getCurrent());
					modelMap.put("size", page.getSize());
					modelMap.put("pages", page.getPages());
					modelMap.put("total", page.getTotal());
				}
				else if (returnValue instanceof List<?>)
				{
					modelMap.put("rows", returnValue);
					modelMap.put("total", ((List<?>) returnValue).size());
				}
				else if (DataUtil.isNotEmpty(returnValue))
				{
					modelMap.put("data", returnValue);
				}
				modelMap.put("code", 200);
				modelMap.put("msg", "");
				modelMap.put("timestamp", System.currentTimeMillis());
				handler.handleReturnValue(modelMap, returnType, mavContainer, webRequest);
			}
		}
		else
		{
			handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
		}
	}
}
