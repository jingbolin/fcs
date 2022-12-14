package com.yinhe.ec.core.util;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.yinhe.ec.core.Constants;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class EncryptUtil
{
	static Logger logger = LogManager.getLogger();
	// 设置默认密匙
	private static String strDefaultKey = "defaultKey";
	// 加密
	private Cipher encryptCipher = null;
	// 解密
	private Cipher decryptCipher = null;

	public EncryptUtil() throws Exception
	{
		this(strDefaultKey);
	}

	EncryptUtil(String strKey) throws Exception
	{
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	private static String byteArr2HexStr(byte[] arrB)
	{
		int iLen = arrB.length;
		StringBuilder sb = new StringBuilder(iLen * 2);
		for (byte anArrB : arrB)
		{
			int intTmp = anArrB;
			while (intTmp < 0)
			{
				intTmp = intTmp + 256;
			}
			if (intTmp < 16)
			{
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	private static byte[] hexStr2ByteArr(String strIn)
	{
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2)
		{
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * token 加密
	 * @param token
	 * token
	 * @return 加密后的 token
	 */
	public static String encryptToken(String token)
	{
		try
		{
			EncryptUtil encryptUtil = new EncryptUtil(Constants.TOKEN_CACHE_PREFIX);
			return encryptUtil.encrypt(token);
		}
		catch (Exception e)
		{
			logger.info("token加密失败：", e);
			return null;
		}
	}

	/**
	 * token 解密
	 * @param encryptToken
	 * 加密后的 token
	 * @return 解密后的 token
	 */
	public static String decryptToken(String encryptToken)
	{
		try
		{
			EncryptUtil encryptUtil = new EncryptUtil(Constants.TOKEN_CACHE_PREFIX);
			return encryptUtil.decrypt(encryptToken);
		}
		catch (Exception e)
		{
			logger.info("token解密失败：", e);
			return null;
		}
	}

	public static String md5(String str)
	{
		Digester md5 = new Digester(DigestAlgorithm.MD5);
		return md5.digestHex(str);
	}

	public static void main(String[] args)
	{
		Console.log(md5("111111"));
	}

	private byte[] encrypt(byte[] arrB) throws Exception
	{
		return encryptCipher.doFinal(arrB);
	}

	String encrypt(String strIn) throws Exception
	{
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	private byte[] decrypt(byte[] arrB) throws Exception
	{
		return decryptCipher.doFinal(arrB);
	}

	String decrypt(String strIn)
	{
		try
		{
			return new String(decrypt(hexStr2ByteArr(strIn)));
		}
		catch (Exception e)
		{
			return "";
		}
	}

	private Key getKey(byte[] arrBTmp)
	{
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
		{
			arrB[i] = arrBTmp[i];
		}
		return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
	}
}
