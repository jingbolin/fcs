package com.yinhe.ec.core.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.DateUtil;
import com.yinhe.ec.core.util.FileUtil;
import com.yinhe.ec.core.util.WebUtil;

/**
 * 跨站请求拦截
 * @author ShenHuaJie
 * @since 2018年7月27日 上午10:58:14
 */
public class CsrfFilter implements Filter
{
	private Logger logger = LogManager.getLogger();

	/**
	 * 白名单
	 */
	private List<String> whiteUrls;

	@Override
	public void init(FilterConfig filterConfig)
	{
		logger.info("init CsrfFilter..");
		String path = CsrfFilter.class.getResource("/").getFile();
		whiteUrls = FileUtil.readFile(path + "white/csrfWhite.txt");
		InputStream stream = CsrfFilter.class.getResourceAsStream("/white/csrfWhite.txt");
		whiteUrls = FileUtil.readFile(stream);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		try
		{
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURL().toString();
			String referurl = req.getHeader("Referer");
			if (isWhiteRequest(url, referurl))
			{
				chain.doFilter(request, response);
			}
			else
			{
				logger.warn("跨站请求---->>>{} || {} || {} || {}", url, referurl, WebUtil.getHost(req), DateUtil.getDateTime());
				WebUtil.write(response, 308, "错误的请求头信息");
				return;
			}
		}
		catch (Exception e)
		{
			logger.error("doFilter", e);
		}
		chain.doFilter(request, response);
	}

	/** 判断是否是白名单 */
	private boolean isWhiteRequest(String url, String referUrl)
	{
		for (String s : whiteUrls)
		{
			logger.info(s);
		}
		for (String urlTemp : whiteUrls)
		{
			if (url.contains(urlTemp))
			{
				return true;
			}
		}
		if (DataUtil.isNotEmpty(referUrl))
		{
			String refHost = referUrl.toLowerCase();
			for (String urlTemp : whiteUrls)
			{
				if (refHost.contains(urlTemp.toLowerCase()) || refHost.contains(urlTemp.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void destroy()
	{
		logger.info("destroy CsrfFilter.");
	}
}
