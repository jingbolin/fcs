// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TripleDES.java

package com.yinhe.ec.cpps.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            CebbankParam

public class TripleDES
{

    public TripleDES()
    {
    }

    public static byte[] des3EncodeECB(byte key[], byte data[])
        throws Exception
    {
        java.security.Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(1, deskey);
        byte bOut[] = cipher.doFinal(data);
        return bOut;
    }

    public static byte[] des3DecodeECB(byte key[], byte data[])
        throws Exception
    {
        java.security.Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(2, deskey);
        byte bOut[] = cipher.doFinal(data);
        return bOut;
    }

    public static byte[] des3EncodeCBC(byte key[], byte keyiv[], byte data[])
        throws Exception
    {
        java.security.Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(1, deskey, ips);
        byte bOut[] = cipher.doFinal(data);
        return bOut;
    }

    public static byte[] des3DecodeCBC(byte key[], byte keyiv[], byte data[])
        throws Exception
    {
        java.security.Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(2, deskey, ips);
        byte bOut[] = cipher.doFinal(data);
        return bOut;
    }

    public static void main(String args[])
        throws Exception
    {
        String keyStr = "3B5032E9-67C6-48EB-BB9A-";
        byte keyiv[] = new byte[8];
        String base64Str = "LO4ncML8ZLvRVbFg5A5hYTn1SxVhZ8NwduIo2GRCGjXCl1pkkJSqWxbRr1u8M2edjrXpNDpmI7azqtGQ1EycziKbj6ZJ7asfyCkqiT3Fvy8mSzVsahQPVVZ/m1AFhD6/j1+FmeJ1a/G3CXxQHzqoYWbY15oAUzbUTpKiHVODZj63HQQoFvo7HiAl68osKksUFVf+x3EBYEi9B2rWcY/1K6YC+NA3CKRNwops5tmq7M1WIvv8G8DKevmorRAI7Y0aNuFBCWJlD0bdwyolEQKINuWEICOqh1xkGOOfvgBKoL0r5auibXGfhIW5uznaBZf1";
        byte data[] = Base64.decodeBase64(base64Str);
        byte paramStr[] = des3DecodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), keyiv, data);
        System.out.println(new String(paramStr, "UTF-8"));
        String ss = "helloword\u798F\u6210\u96C6\u56E2aaaaaaaaaa\u725B\u725Bbbbbbbbbb\u6D4B\u6D4Bccccc--ccccc20171231";
        System.out.println(Base64.encodeBase64String(des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, ss.getBytes("UTF-8"))));
        String ss1 = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbiIsImlhdCI6MTU1NzY0MTA4Niwic3ViIjoiYzkzY2NkNzhiMjA3NjUyODM0NjIxNmIzYjJmNzAxZTYiLCJpc3MiOiJodHRwOi8vd3d3LnBuLWlvdC5jb20iLCJleHAiOjE1NTc2NDI4ODZ9.k5oJ08P3uKaqnbdANHb_jN_SNuDsr3HdUX6m12aa1C4";
        System.out.println(Base64.encodeBase64String(des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, ss1.getBytes("UTF-8"))));
    }
}
