package com.yinhe.ec.cpps.sys.service;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ConfList;

import java.util.List;

/**
 * 参数配置表
 * @author zhanglei
 * @date 2020-02-19 13:40:13
 */
public interface ConfListService
{
	PageInfo<ConfList> queryConfListPage(Integer pageNum, Integer pageSize, ConfList confList);

	List<ConfList> queryConfListList(ConfList confList);

	void updateConfList(ConfList confList) throws Exception;
}
