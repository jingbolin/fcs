package com.yinhe.ec.base.model;

import java.io.Serializable;

/**
 * @author chenyh
 * @since  2019年3月6日上午11:08:13
 */
public class Select implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String value;
	
	private String label;

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}
}
