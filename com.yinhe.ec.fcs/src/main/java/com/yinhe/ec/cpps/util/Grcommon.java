// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Grcommon.java

package com.yinhe.ec.cpps.util;


public class Grcommon
{

    public Grcommon()
    {
    }

    public static boolean HasSpecialChar(String text)
    {
        if(text == null)
            return false;
        boolean ret = false;
        int len = text.length();
        for(int i = 0; i < len; i++)
        {
            char c = text.charAt(i);
            if(c != '&' && c != '<' && c != '>' && c != '"')
                continue;
            ret = true;
            break;
        }

        return ret;
    }

    public static String HTMLEncode(String text)
    {
        int len = text.length();
        StringBuffer results = new StringBuffer(len + 20);
        char orig[] = text.toCharArray();
        int beg = 0;
        for(int i = 0; i < len; i++)
        {
            char c = text.charAt(i);
            switch(c)
            {
            default:
                break;

            case 38: // '&'
                if(i > beg)
                    results.append(orig, beg, i - beg);
                beg = i + 1;
                results.append("&amp;");
                break;

            case 60: // '<'
                if(i > beg)
                    results.append(orig, beg, i - beg);
                beg = i + 1;
                results.append("&lt;");
                break;

            case 62: // '>'
                if(i > beg)
                    results.append(orig, beg, i - beg);
                beg = i + 1;
                results.append("&gt;");
                break;

            case 34: // '"'
                if(i > beg)
                    results.append(orig, beg, i - beg);
                beg = i + 1;
                results.append("&quot;");
                break;
            }
        }

        results.append(orig, beg, len - beg);
        return results.toString();
    }

    public static String encodeBASE64(byte a[])
    {
        char codec_table[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
            'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
            '8', '9', '+', '/'
        };
        int totalBits = a.length * 8;
        int nn = totalBits % 6;
        int curPos = 0;
        StringBuffer toReturn = new StringBuffer();
        for(; curPos < totalBits; curPos += 6)
        {
            int bytePos = curPos / 8;
            switch(curPos % 8)
            {
            case 1: // '\001'
            case 3: // '\003'
            case 5: // '\005'
            default:
                break;

            case 0: // '\0'
                toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]);
                break;

            case 2: // '\002'
                toReturn.append(codec_table[a[bytePos] & 0x3f]);
                break;

            case 4: // '\004'
                if(bytePos == a.length - 1)
                {
                    toReturn.append(codec_table[(a[bytePos] & 0xf) << 2 & 0x3f]);
                } else
                {
                    int pos = ((a[bytePos] & 0xf) << 2 | (a[bytePos + 1] & 0xc0) >> 6) & 0x3f;
                    toReturn.append(codec_table[pos]);
                }
                break;

            case 6: // '\006'
                if(bytePos == a.length - 1)
                {
                    toReturn.append(codec_table[(a[bytePos] & 3) << 4 & 0x3f]);
                } else
                {
                    int pos = ((a[bytePos] & 3) << 4 | (a[bytePos + 1] & 0xf0) >> 4) & 0x3f;
                    toReturn.append(codec_table[pos]);
                }
                break;
            }
        }

        if(nn == 2)
            toReturn.append("==");
        else
        if(nn == 4)
            toReturn.append("=");
        return toReturn.toString();
    }
}
