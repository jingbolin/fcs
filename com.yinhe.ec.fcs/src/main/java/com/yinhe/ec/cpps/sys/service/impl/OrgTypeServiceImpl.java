package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.cpps.sys.mapper.OrgMapper;
import com.yinhe.ec.cpps.sys.mapper.OrgTypeMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Org;
import com.yinhe.ec.cpps.sys.model.OrgType;
import com.yinhe.ec.cpps.sys.service.OrgTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 客户、群组、公司、分公司等
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class OrgTypeServiceImpl extends BaseServiceImpl<OrgType, OrgTypeMapper> implements OrgTypeService
{

	@Autowired
	private OrgTypeMapper orgTypeMapper;
	@Autowired
	private OrgMapper orgMapper;

	@Override
	public PageInfo<OrgType> queryOrgTypePage(Integer pageNum, Integer pageSize, OrgType orgType)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<OrgType> orgTypeListPage = orgTypeMapper.queryOrgTypeList(orgType);
		return new PageInfo<OrgType>(orgTypeListPage);
	}

	@Override
	public void addOrgType(OrgType orgType)
	{
		orgTypeMapper.addOrgType(orgType);
	}

	/**
	 * 根据id数组批量删除组织类型
	 * @param ids
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ApiResult deleteOrgType(Long[] ids)
	{
		Org org = new Org();
		org.setOrgTypeIdList(Arrays.asList(ids));
		List<Org> orgList = orgMapper.queryOrgList(org);
		if (CollUtil.isNotEmpty(orgList))
		{
			return ApiResult.error("组织类型下存在组织,无法删除!");
		}
		OrgType orgType = new OrgType();
		orgType.setIdList(Arrays.asList(ids));
		orgTypeMapper.batchDelete(orgType);
		return ApiResult.ok(ApiCode.DELETE_SUCCESS);
	}

	@Override
	public List<OrgType> queryOrgTypeList(OrgType orgTypeParam)
	{
		return orgTypeMapper.queryOrgTypeList(orgTypeParam);
	}

	@Override
	public Integer updateOrgType(OrgType orgType)
	{
		return orgTypeMapper.updateOrgType(orgType);
	}
}
