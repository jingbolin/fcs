// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tools.java

package com.yinhe.ec.cpps.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools
{

    public Tools()
    {
    }

    public static String getNow()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = df.format(new Date());
        return str;
    }

    public static String getNowSecond()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String str = df.format(new Date());
        return str;
    }

    public static String getNowDT()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        String str = df.format(new Date());
        return str;
    }

    public static String getNowWeek()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5  EEEE");
        String str = df.format(new Date());
        return str;
    }

    public static String getNow_YYYYMMDDHHMMSS()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = df.format(new Date());
        return str;
    }

    public static String getNow_YYMMDDHHMMSS()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String str = df.format(new Date());
        return str;
    }

    public static String formatFloat(double db)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(db);
    }

    public static String getForNDay(int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(5, -n);
        String dayDate = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
        return dayDate;
    }

    public static String getForNRDay(int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(5, n);
        String dayDate = (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
        return dayDate;
    }

    public static String getWeek()
    {
        Calendar cal = Calendar.getInstance();
        String ww = String.valueOf(cal.get(7) - 1);
        if(ww.length() < 2)
            ww = (new StringBuilder("0")).append(ww).toString();
        return ww;
    }

    public static String getNow_HHMMSS()
    {
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        String str = df.format(new Date());
        return str;
    }

    public static String getForNMonth(int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(2, -n);
        String monthDate = (new SimpleDateFormat("yyyy-MM")).format(cal.getTime());
        return monthDate;
    }

    public static String lpad(String id, int len, char c)
    {
        if(!id.equals(""))
            for(; id.length() < len; id = (new StringBuilder(String.valueOf(c))).append(id).toString());
        return id;
    }

    public static Integer getIntDate(String str)
    {
        str = str.replace("-", "").substring(0, 8);
        Integer intDate = Integer.valueOf(Integer.parseInt(str));
        return intDate;
    }

    public static int daysBetween(String smdate, String bdate)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 0x5265c00L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int daysBetween2(String smdate, String bdate)
        throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = null;
        Date eDate = null;
        try
        {
            sdate = df.parse(smdate);
            eDate = df.parse(bdate);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        long betweendays = (long)((double)((eDate.getTime() - sdate.getTime()) / 0x5265c00L) + 0.5D);
        return Integer.parseInt(String.valueOf(betweendays));
    }

    public static String getMacByIp()
        throws IOException
    {
        String MacStr = "";
        try
        {
            InetAddress ia = InetAddress.getLocalHost();
            byte mac[] = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for(int i = 0; i < mac.length; i++)
            {
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if(str.length() == 1)
                    sb.append((new StringBuilder("0")).append(str).toString());
                else
                    sb.append(str);
            }

            MacStr = sb.toString().toUpperCase();
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        return MacStr;
    }

    public static String getCurDateY()
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getRandomString(int length)
    {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++)
        {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static Date strToDateTime(String str)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = df.parse(str);
        }
        catch(ParseException e)
        {
            System.err.println(e.getMessage());
        }
        return date;
    }

    public static String NullToEmpty(Object obj)
    {
        if(obj == null)
            return "";
        else
            return obj.toString();
    }

    public static Map StrToMap(String str, String splitStr)
    {
        if("|".equals(splitStr))
            splitStr = "\\|";
        if("|".equals(splitStr))
            splitStr = "\\.";
        String paramStrArray[] = str.split(splitStr);
        Map map = new HashMap();
        for(int i = 0; i < paramStrArray.length; i++)
            if(paramStrArray[i].split("=").length == 2)
                map.put(paramStrArray[i].split("=")[0], paramStrArray[i].split("=")[1]);
            else
                map.put(paramStrArray[i].split("=")[0], "");

        return map;
    }

    public static String daysEdit(String srcdate, int n)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(srcdate));
        cal.add(5, n);
        String dayDate = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
        return dayDate;
    }

    public static String formatTimestamp(String times)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        long lt = (new Long(times)).longValue();
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }

    public static String formartTimeFromDatetime(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(d);
        return str;
    }

    public static String numToBinary(int num, int n)
    {
        String binaryString;
        for(binaryString = Integer.toBinaryString(num); binaryString.length() < n; binaryString = (new StringBuilder("0")).append(binaryString).toString());
        return binaryString;
    }

    public static String hexToBinary(String hex)
    {
        int decimal = Integer.parseInt(hex, 16);
        String binaryString;
        for(binaryString = Integer.toBinaryString(decimal); binaryString.length() < 8; binaryString = (new StringBuilder("0")).append(binaryString).toString());
        return binaryString;
    }

    public static int buildRandom(int length)
    {
        int num = 1;
        double random = Math.random();
        if(random < 0.10000000000000001D)
            random += 0.10000000000000001D;
        for(int i = 0; i < length; i++)
            num *= 10;

        return (int)(random * (double)num);
    }

    public static String getCurrTime()
    {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s = outFormat.format(now);
        return s;
    }

    public static String genNonceStr()
    {
        String currTime = getCurrTime();
        String strRandom = (new StringBuilder(String.valueOf(buildRandom(4)))).toString();
        return (new StringBuilder(String.valueOf(currTime))).append(strRandom).toString();
    }

    public static double round(double value, int scale)
    {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, 4);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    public static String getForNmonthDay(String theDay, int n)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(theDay));
        cal.add(2, n);
        cal.add(5, -1);
        String dayDate = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
        return dayDate;
    }

    public static long getTime(String oldTime, String newTime)
        throws ParseException
    {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0L;
        try
        {
            Date begin = dfs.parse(oldTime);
            Date end = dfs.parse(newTime);
            between = end.getTime() - begin.getTime();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return between;
    }

    public static void main(String args1[])
        throws ParseException
    {
    }

    public static final String GENTIME_START = "00:00:00";
    public static final String GENTIME_END = "23:59:59";
}
