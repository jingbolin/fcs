package com.yinhe.ec.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.yinhe.ec.core.Constants.Times;

/**
 * 日期操作辅助类
 * @author ShenHuaJie
 * @version $Id: DateUtil.java, v 0.1 2014年3月28日 上午8:58:11 ShenHuaJie Exp $
 */
public final class DateUtil
{
	private static final Map<String, DateTimeFormatter> FORMATS = new ConcurrentHashMap<String, DateTimeFormatter>();
	private final static int[] DAYS = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };
	private final static String[] STARSIGNS = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };

	private DateUtil()
	{
	}

	/** 日期格式 **/
	public interface DATE_PATTERN
	{
		String HHMMSS = "HHmmss";
		String HH_MM_SS = "HH:mm:ss";
		String YYYY = "YYYY";
		String YYYY_MM = "YYYY-MM";
		String YYYYMM = "yyyyMM";
		String YYYYMMDD = "yyyyMMdd";
		String YYYY_MM_DD = "yyyy-MM-dd";
		String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
		String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
		String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	}

	public static final DateTimeFormatter getFormat(String pattern)
	{
		if (!FORMATS.containsKey(pattern))
		{
			FORMATS.put(pattern, DateTimeFormatter.ofPattern(pattern));
		}
		return FORMATS.get(pattern);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static final String format(Object date)
	{
		return format(date, DatePattern.YYYY_MM_DD);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern)
	{
		if (date == null)
		{
			return null;
		}
		if (pattern == null)
		{
			return format(date);
		}
		return getFormat(pattern).format(TimeUtil.date2LocalDateTime(date));
	}

	/**
	 * 获取日期
	 * @return
	 */
	public static final String getDate()
	{
		return format(new Date());
	}

	/**
	 * 获取日期时间
	 * @return
	 */
	public static final String getDateTime()
	{
		return format(new Date(), DatePattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取日期
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern)
	{
		return format(new Date(), pattern);
	}

	/**
	 * 日期计算
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount)
	{
		if (date == null)
		{
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date)
	{
		if (date == null)
		{
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*"))
		{
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10)
			{
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
			pattern += " HH:mm:ss.SSS";
		}
		else if (date.length() < 8)
		{
			pattern = "yyyyMd";
		}
		else
		{
			pattern += "HHmmss.SSS";
		}
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try
		{
			return new SimpleDateFormat(pattern).parse(date);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	/**
	 * 间隔秒
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getBetween(Date startDate, Date endDate)
	{
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		long n = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (n / 1000L);
	}

	/**
	 * 间隔天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getDayBetween(Date startDate, Date endDate)
	{
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);

		long n = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (n / (60 * 60 * 24 * 1000L));
	}

	/**
	 * 间隔月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate)
	{
		if (startDate == null || endDate == null || !startDate.before(endDate))
		{
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate)
	{
		if (startDate == null || endDate == null || !startDate.before(endDate))
		{
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2)
		{
			n++;
		}
		return n;
	}

	/**
	 * 获取友好型与当前时间的差
	 * @param millis
	 * 毫秒时间戳
	 * @return 友好型与当前时间的差
	 * <ul>
	 * <li>如果小于1秒钟内，显示刚刚</li>
	 * <li>如果在1分钟内，显示XXX秒前</li>
	 * <li>如果在1小时内，显示XXX分钟前</li>
	 * <li>如果在1小时外的今天内，显示今天15:32</li>
	 * <li>如果是昨天的，显示昨天15:32</li>
	 * <li>如果是当年的，显示10-15</li>
	 * <li>其余显示，2016-10-15</li>
	 * <li>时间不合法的情况全部日期和时间信息，如2018-05-13 14:21:20</li>
	 * </ul>
	 */
	public static String getFriendly(long millis)
	{
		long now = System.currentTimeMillis();
		long span = now - millis;
		if (span < 0)
		{
			return String.format("%tF %tT", millis, millis);
		}
		if (span < 1000)
		{
			return "刚刚";
		}
		else if (span < Times.MINUTE)
		{
			return String.format("%d秒前", span / Times.SECOND);
		}
		else if (span < Times.HOUR)
		{
			return String.format("%d分钟前", span / Times.MINUTE);
		}
		// 获取当天00:00
		long wee = now / Times.DAY * Times.DAY;
		if (millis >= wee)
		{
			return String.format("今天%tR", millis);
		}
		else if (millis >= wee - Times.DAY)
		{
			return String.format("昨天%tR", millis);
		}
		else
		{
			wee = now / Times.YEAR * Times.YEAR;
			if (millis >= wee)
			{
				return String.format("%tm-%te", millis, millis);
			}
			return String.format("%tF", millis);
		}
	}

	public static Calendar getCalendar(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 通过生日计算星座
	 * @return
	 */
	public static String getStarSign(Date date)
	{
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day < DAYS[month] ? STARSIGNS[month] : STARSIGNS[month + 1];
	}

	/**
	 * 日期格式
	 **/
	public interface DatePattern
	{
		String HHMMSS = "HHmmss";
		String HH_MM_SS = "HH:mm:ss";
		String YYYY = "yyyy";
		String YYYYMM = "yyyyMM";
		String YYYYMMDD = "yyyyMMdd";
		String YYYY_MM = "yyyy-MM";
		String YYYY_MM_DD = "yyyy-MM-dd";
		String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
		String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
		String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	}

	/**
	 * Date转LocalDate
	 * @param date
	 */
	public static LocalDate date2LocalDate(Date date)
	{
		if (null == date)
		{
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * LocalDate转Date
	 * @param localDate
	 * @return
	 */
	public static Date localDate2Date(LocalDate localDate)
	{
		if (null == localDate)
		{
			return null;
		}
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * Date转换为LocalDateTime
	 * @param date
	 */
	public static LocalDateTime date2LocalDateTime(Date date)
	{
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

		System.out.println(localDateTime.toString());
		System.out.println(localDateTime.toLocalDate() + " " + localDateTime.toLocalTime());

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateTimeFormatter.format(localDateTime));
		return localDateTime;
	}

	/**
	 * LocalDateTime转换为Date
	 * @param localDateTime
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime)
	{
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		return date;
	}

	public static void main(String[] args)
	{

		LocalDateTime localDateTime = date2LocalDateTime(new Date());
		localDateTime2Date(localDateTime);
	}
}
