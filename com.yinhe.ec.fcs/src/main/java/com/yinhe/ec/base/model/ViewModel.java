package com.yinhe.ec.base.model;

import java.io.Serializable;

public class ViewModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String name;

	private Long id;

	private String unit;

	private Integer rowNumber;

	private Integer colNumber;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public Integer getRowNumber()
	{
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber)
	{
		this.rowNumber = rowNumber;
	}

	public Integer getColNumber()
	{
		return colNumber;
	}

	public void setColNumber(Integer colNumber)
	{
		this.colNumber = colNumber;
	}

	@Override
	public String toString()
	{
		return "ViewModel [name=" + name + ", id=" + id + ", unit=" + unit + ", rowNumber=" + rowNumber + ", colNumber="
				+ colNumber + "]";
	}
}
