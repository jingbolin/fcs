// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataConvertTools.java

package com.yinhe.ec.cpps.util;

import java.util.Stack;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            Tools

public class DataConvertTools
{

    public DataConvertTools()
    {
    }

    public static String Integer2HexStr(Integer n)
    {
        String hex = Integer.toHexString(n.intValue());
        if(hex.length() == 1)
            hex = (new StringBuilder(String.valueOf('0'))).append(hex).toString();
        return hex.toUpperCase();
    }

    public static String numToHex16(int meterNo)
    {
        return String.format("%06x", new Object[] {
            Integer.valueOf(meterNo)
        });
    }

    public static String str2HexStr(String str)
    {
        char chars[] = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte bs[] = str.getBytes();
        for(int i = 0; i < bs.length; i++)
        {
            int bit = (bs[i] & 0xf0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0xf;
            sb.append(chars[bit]);
        }

        return sb.toString();
    }

    public static final String bytesToHexString(byte bArray[])
    {
        if(bArray != null)
        {
            StringBuffer sb = new StringBuffer(bArray.length);
            for(int i = 0; i < bArray.length; i++)
            {
                String sTemp = Integer.toHexString(0xff & bArray[i]);
                if(sTemp.length() < 2)
                    sb.append(0);
                sb.append(sTemp.toUpperCase());
            }

            return sb.toString();
        } else
        {
            return "";
        }
    }

    public static String _10_to_N(long number, int N)
    {
        Long rest = Long.valueOf(number);
        Stack stack = new Stack();
        StringBuilder result = new StringBuilder(0);
        for(; rest.longValue() != 0L; rest = Long.valueOf(rest.longValue() / (long)N))
            stack.add(Character.valueOf(array[(new Long(rest.longValue() % (long)N)).intValue()]));

        for(; !stack.isEmpty(); result.append(stack.pop()));
        return result.length() != 0 ? result.toString() : "0";
    }

    public static long N_to_10(String number, int N)
    {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0L;
        if(N == 10)
            return Long.parseLong(number);
        long base = 1L;
        for(int i = len - 1; i >= 0; i--)
        {
            int index = numStr.indexOf(ch[i]);
            result += (long)index * base;
            base *= N;
        }

        return result;
    }

    public static int byte2Int(byte b)
    {
        int r = b;
        return r;
    }

    public static byte int2Byte(int i)
    {
        byte r = (byte)i;
        return r;
    }

    public static String bytes2HexString(byte b[])
    {
        String r = "";
        for(int i = 0; i < b.length; i++)
        {
            String hex = Integer.toHexString(b[i] & 0xff);
            if(hex.length() == 1)
                hex = (new StringBuilder(String.valueOf('0'))).append(hex).toString();
            r = (new StringBuilder(String.valueOf(r))).append(hex.toUpperCase()).toString();
        }

        return r;
    }

    private static byte charToByte(char c)
    {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    public static byte[] hexString2Bytes(String hex)
    {
        if(hex == null || hex.equals(""))
            return null;
        if(hex.length() % 2 != 0)
            return null;
        hex = hex.toUpperCase();
        int len = hex.length() / 2;
        byte b[] = new byte[len];
        char hc[] = hex.toCharArray();
        for(int i = 0; i < len; i++)
        {
            int p = 2 * i;
            b[i] = (byte)(charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
        }

        return b;
    }

    public static String bytes2String(byte b[])
        throws Exception
    {
        String r = new String(b, "UTF-8");
        return r;
    }

    public static byte[] string2Bytes(String s)
    {
        byte r[] = s.getBytes();
        return r;
    }

    public static String hex2String(String hex)
        throws Exception
    {
        String r = bytes2String(hexString2Bytes(hex));
        return r;
    }

    public static String string2HexString(String s)
        throws Exception
    {
        String r = bytes2HexString(string2Bytes(s));
        return r;
    }

    public static String hexToBinary(String hex)
    {
        int decimal = Integer.parseInt(hex, 16);
        return toBinary(decimal, 8);
    }

    public static String toBinary(int num, int digits)
    {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value);
        return bs.substring(1);
    }

    public static String turnOver(String hexStr, boolean inc33Flag, boolean dec33Flag)
    {
        String returnValue = "";
        if(!"".equals(hexStr))
        {
            byte hexStrBuffer[] = hexString2Bytes(hexStr);
            byte buffer[] = new byte[hexStrBuffer.length];
            for(int i = 0; i < hexStrBuffer.length; i++)
                buffer[i] = hexStrBuffer[hexStrBuffer.length - 1 - i];

            if(inc33Flag)
            {
                for(int i = 0; i < buffer.length; i++)
                    buffer[i] += 51;

            }
            if(dec33Flag)
            {
                for(int i = 0; i < buffer.length; i++)
                    buffer[i] -= 51;

            }
            returnValue = bytesToHexString(buffer);
        }
        return returnValue;
    }

    public static String inc33h(byte buffer[])
    {
        for(int i = 0; i < buffer.length; i++)
            buffer[i] += 51;

        return bytesToHexString(buffer);
    }

    public static String dec33h(byte buffer[])
    {
        for(int i = 0; i < buffer.length; i++)
            buffer[i] -= 51;

        return bytesToHexString(buffer);
    }

    public static String cs(String hexStr)
    {
        byte cs = 0;
        byte csBuffer[] = new byte[1];
        byte hexStrBuffer[] = hexString2Bytes(hexStr);
        for(int i = 0; i < hexStrBuffer.length; i++)
            cs += hexStrBuffer[i];

        csBuffer[0] = cs;
        return bytesToHexString(csBuffer);
    }

    public static String inc33h(String hexStr)
    {
        String returnValue = "";
        if(!"".equals(hexStr))
        {
            byte hexStrBuffer[] = hexString2Bytes(hexStr);
            for(int i = 0; i < hexStrBuffer.length; i++)
                hexStrBuffer[i] += 51;

            returnValue = bytesToHexString(hexStrBuffer);
        }
        return returnValue;
    }

    public static String dec33h(String hexStr)
    {
        String returnValue = "";
        if(!"".equals(hexStr))
        {
            byte hexStrBuffer[] = hexString2Bytes(hexStr);
            for(int i = 0; i < hexStrBuffer.length; i++)
                hexStrBuffer[i] -= 51;

            returnValue = bytesToHexString(hexStrBuffer);
        }
        return returnValue;
    }

    public static String formatHexStr(String hexStr, int n)
    {
        return Tools.lpad(hexStr, n * 2, '0');
    }

    public static String formatPrint(String str)
    {
        String regex = "(.{2})";
        str = str.replaceAll(regex, "$1 ");
        return str;
    }

    public static void main(String args[])
        throws Exception
    {
        System.out.println(formatPrint("6810060504030201000413A0100000030050000000040000020000050015C716"));
    }

    private static char array[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

}
