package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.cpps.sys.mapper.ResourceGroupMapper;
import com.yinhe.ec.cpps.sys.mapper.ResourceMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.ResourceGroup;
import com.yinhe.ec.cpps.sys.service.ResourceGroupService;
import com.yinhe.ec.cpps.sys.model.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 资源分组
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class ResourceGroupServiceImpl extends BaseServiceImpl<ResourceGroup, ResourceGroupMapper> implements ResourceGroupService
{

	@Autowired
	private ResourceGroupMapper resourceGroupMapper;
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<ResourceGroup> queryResourceGroupList(ResourceGroup resourceGroup)
	{
		return resourceGroupMapper.queryResourceGroupList(resourceGroup);
	}

	@Override
	public void addResourceGroup(ResourceGroup resourceGroup)
	{
		if(StringUtils.isBlank(resourceGroup.getNameEn())){
			resourceGroup.setNameEn(resourceGroup.getName());
		}

		resourceGroupMapper.addResourceGroup(resourceGroup);
	}

	@Override
	public void updateResourceGroup(ResourceGroup resourceGroup)
	{
		if(StringUtils.isBlank(resourceGroup.getNameEn())){
			resourceGroup.setNameEn(resourceGroup.getName());
		}

		resourceGroupMapper.updateResourceGroup(resourceGroup);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ApiResult deleteResourceGroupList(Long[] ids)
	{
		Resource resource = new Resource();
		resource.setGroupIdList(Arrays.asList(ids));
		List<Resource> resourceList = resourceMapper.queryResourceList(resource);
		if (CollUtil.isNotEmpty(resourceList))
		{
			return ApiResult.error("资源分组下存在资源,无法删除!");
		}
		ResourceGroup resourceGroup = new ResourceGroup();
		resourceGroup.setIdList(Arrays.asList(ids));
		resourceGroupMapper.batchDelete(resourceGroup);
		return ApiResult.ok(ApiCode.DELETE_SUCCESS);
	}

	@Override
	public List<ResourceGroup> queryPermissionGroupList(ResourceGroup resourceGroup)
	{
		return resourceGroupMapper.queryResourceGroupList(resourceGroup);
	}
}
