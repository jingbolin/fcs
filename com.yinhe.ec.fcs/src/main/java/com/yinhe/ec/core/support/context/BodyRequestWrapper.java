package com.yinhe.ec.core.support.context;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.WebUtils;

public class BodyRequestWrapper extends HttpServletRequestWrapper
{

	private final Charset charset;
	private final String body;

	public BodyRequestWrapper(HttpServletRequest request) throws IOException
	{
		super(request);
		charset = Charset.forName(getCharacterEncoding());
		body = StreamUtils.copyToString(request.getInputStream(), charset);
	}

	@Override
	public BufferedReader getReader() throws IOException
	{
		return new BufferedReader(new InputStreamReader(getInputStream(), charset));
	}

	public String getBody()
	{
		return body;
	}

	@Override
	public String getCharacterEncoding()
	{
		String enc = super.getCharacterEncoding();
		return enc != null ? enc : WebUtils.DEFAULT_CHARACTER_ENCODING;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException
	{
		final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(charset));
		return new ServletInputStream()
		{
			@Override
			public int read() throws IOException
			{
				return bais.read();
			}

			@Override
			public boolean isFinished()
			{
				return false;
			}

			@Override
			public boolean isReady()
			{
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener)
			{

			}
		};
	}
}
