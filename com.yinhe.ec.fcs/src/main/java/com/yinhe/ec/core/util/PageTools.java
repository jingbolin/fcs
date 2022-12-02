package com.yinhe.ec.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinhe.ec.core.support.Pagination;

public class PageTools<T>
{
	/**
	 * 根据数据进行分页处理
	 */
	public Pagination<T> getPage(List<T> list, Map<String, Object> params)
	{
		Page<Long> page = PageUtil.getPage(params);
		List<T> pageRecords = new ArrayList<T>();
		long total = list.size();
		for (int i = 0; i < list.size(); i++)
		{
			if (i >= page.getSize() * (page.getCurrent() - 1) && i < page.getSize() * page.getCurrent())
			{
				pageRecords.add(list.get(i));
			}
		}
		Pagination<T> result = new Pagination<T>(page.getCurrent(), page.getSize());
		result.setRecords(pageRecords);
		result.setTotal(total);
		return result;
	}
}
