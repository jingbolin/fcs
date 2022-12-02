package com.yinhe.ec.core.util;

import java.util.Date;

/**
 * @author chenyh
 * @since 2018年7月12日上午10:57:56
 */
public class TimeParaUtil
{
	public static Long getTime(String time)
	{
		if (MathUtil.isNumber(time))
		{
			return Long.valueOf(time);
		}
		else
		{
			Date ltime = DateUtil.stringToDate(time);
			if (null == ltime)
			{
				return null;
			}
			else
			{
				return ltime.getTime();
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.println(TimeParaUtil.getTime("2018-07-12 09:54:12"));
		System.out.println(1531360452000L);
	}
}
