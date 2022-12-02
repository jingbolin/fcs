package com.yinhe.ec.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyh
 * @since 2018年11月12日上午10:44:05
 */
public class TimeLableUtil
{
	public static List<String> getMinutesLable(int minutes, long startTime, long endTime)
	{
		List<String> lable = new ArrayList<>();
		long lableTime = startTime - (startTime % (minutes * 60 * 1000L));
		do
		{
			lable.add(DateUtil.format(lableTime, DateUtil.DatePattern.YYYY_MM_DD_HH_MM_SS));
			lableTime = lableTime + (15 * 60 * 1000L);
		} while (lableTime <= endTime);
		return lable;
	}

	public static List<String> getHourLable(int hour, long startTime, long endTime)
	{
		List<String> lable = new ArrayList<>();
		long lableTime = startTime - (startTime % (hour * 60 * 60 * 1000L));
		do
		{
			lable.add(DateUtil.format(lableTime, DateUtil.DatePattern.YYYY_MM_DD_HH_MM_SS));
			lableTime = lableTime + (15 * 60 * 1000L);
		} while (lableTime <= endTime);
		return lable;
	}

	public static List<String> get15MinutesLable(long startTime, long endTime)
	{
		List<String> lable = new ArrayList<>();
		long lableTime = startTime - (startTime % (15 * 60 * 1000L));
		do
		{
			lable.add(DateUtil.format(lableTime, DateUtil.DatePattern.YYYY_MM_DD_HH_MM_SS));
			lableTime = lableTime + (15 * 60 * 1000L);
		} while (lableTime <= endTime);
		return lable;
	}
}
