package com.yinhe.ec.core.interceptor;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.yinhe.ec.core.filter.CsrfFilter;
import com.yinhe.ec.core.support.http.HttpCode;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.FileUtil;
import com.yinhe.ec.core.util.WebUtil;

/**
 * 签名验证
 * @author ShenHuaJie
 * @since 2018年5月12日 下午10:40:38
 */
public class SignInterceptor extends HandlerInterceptorAdapter
{
	private static final Logger logger = LogManager.getLogger();
	// 白名单
	private List<String> whiteUrls;
	private int size = 0;

	public SignInterceptor()
	{
		// 读取文件
		String path = SignInterceptor.class.getResource("/").getFile();
		whiteUrls = FileUtil.readFile(path + "white/signWhite.txt");
		size = null == whiteUrls ? 0 : whiteUrls.size();

		InputStream stream = CsrfFilter.class.getResourceAsStream("/white/signWhite.txt");
		whiteUrls = FileUtil.readFile(stream);
		size = null == whiteUrls ? 0 : whiteUrls.size();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		// 请求秘钥的接口不需要签名
		String url = request.getRequestURL().toString();
		String refer = request.getHeader("Referer");
		if (refer != null && refer.contains("/swagger"))
		{
			logger.info("SignInterceptor skip");
			return true;
		}
		if (WebUtil.isWhiteRequest(url, size, whiteUrls))
		{
			logger.info("SignInterceptor skip");
			return true;
		}
		String sign = request.getHeader("sign");
		if (DataUtil.isEmpty(sign))
		{
			return WebUtil.write(response, HttpCode.NOT_ACCEPTABLE.value(), "请求参数未签名");
		}
		String timestamp = request.getHeader("timestamp");
		if (DataUtil.isEmpty(timestamp))
		{
			return WebUtil.write(response, HttpCode.NOT_ACCEPTABLE.value(), "请求无效");
		}
		logger.info("timestamp={}", timestamp);
		if (Math.abs(System.currentTimeMillis() - Long.valueOf(timestamp)) > 1000 * 60 * 5)
		{
			return WebUtil.write(response, HttpCode.FORBIDDEN.value(), "请求已过期");
		}
		// 获取参数
		Map<String, Object> params = WebUtil.getParameterMap(request);
		String[] keys = params.keySet().toArray(new String[] {});
		Arrays.sort(keys);
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp);
		for (String key : keys)
		{
			if (!"dataFile".equals(key))
			{
				sb.append("&").append(key).append("=").append(params.get(key));
			}
		}
		// 验证信息摘要MD5加密字节转十六进制字符串
		String encrypt = DigestUtils.md5Hex(URLEncoder.encode(sb.toString(), "UTF-8"));
		if (!encrypt.toLowerCase().equals(sign.toLowerCase()))
		{
			logger.warn("sign={} 错误， 正确sign= {}", sign, encrypt);
			return WebUtil.write(response, HttpCode.FORBIDDEN.value(), "签名错误");
		}
		logger.info("SignInterceptor successful");
		return true;
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
		String encrypt = DigestUtils.md5Hex(URLEncoder.encode("timestamp=1551017726066", "UTF-8"));
		System.out.println(encrypt);
	}
}
