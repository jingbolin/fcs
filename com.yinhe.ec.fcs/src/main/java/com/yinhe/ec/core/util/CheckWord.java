package com.yinhe.ec.core.util;

import com.yinhe.ec.core.Constants;

public class CheckWord
{
	public static Integer checkPassword(String passwordStr)
	{
		String regexZ = "\\d*";
		String regexS = "[a-zA-Z]+";
		String regexT = "\\W+$";
		String regexZT = "\\D*";
		String regexST = "[\\d\\W]*";
		String regexZS = "\\w*";

		if (passwordStr.matches(regexZ) || passwordStr.matches(regexS) || passwordStr.matches(regexT))
		{
			return Constants.PASSWORD_INTENSITY_LIGHT;
		}
		if (passwordStr.matches(regexZT) || passwordStr.matches(regexST) || passwordStr.matches(regexZS))
		{
			return Constants.PASSWORD_INTENSITY_MIDDLE;
		}
		return Constants.PASSWORD_INTENSITY_HIGH;
	}
}
