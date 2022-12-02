// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESPlus.java

package com.yinhe.ec.cpps.util;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

public class DESPlus
{

    public static String byteArr2HexStr(byte arrB[])
        throws Exception
    {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for(int i = 0; i < iLen; i++)
        {
            int intTmp;
            for(intTmp = arrB[i]; intTmp < 0; intTmp += 256);
            if(intTmp < 16)
                sb.append("0");
            sb.append(Integer.toString(intTmp, 16));
        }

        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn)
        throws Exception
    {
        byte arrB[] = strIn.getBytes();
        int iLen = arrB.length;
        byte arrOut[] = new byte[iLen / 2];
        for(int i = 0; i < iLen; i += 2)
        {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    public DESPlus()
        throws Exception
    {
        this(strDefaultKey);
    }

    public DESPlus(String strKey)
        throws Exception
    {
        encryptCipher = null;
        decryptCipher = null;
        Security.addProvider(new SunJCE());
        Key key = getKey(strKey.getBytes());
        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(1, key);
        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(2, key);
    }

    public byte[] encrypt(byte arrB[])
        throws Exception
    {
        return encryptCipher.doFinal(arrB);
    }

    public String encrypt(String strIn)
        throws Exception
    {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public byte[] decrypt(byte arrB[])
        throws Exception
    {
        return decryptCipher.doFinal(arrB);
    }

    public String decrypt(String strIn)
        throws Exception
    {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    private Key getKey(byte arrBTmp[])
        throws Exception
    {
        byte arrB[] = new byte[8];
        for(int i = 0; i < arrBTmp.length && i < arrB.length; i++)
            arrB[i] = arrBTmp[i];

        Key key = new SecretKeySpec(arrB, "DES");
        return key;
    }

    public static void main(String args[])
    {
        DESPlus desplus;
        try
        {
            desplus = new DESPlus();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String strDefaultKey = "RD#flagele#com#cn";
    private Cipher encryptCipher;
    private Cipher decryptCipher;

}
