package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.yinhe.ec.cpps.sys.mapper.PermissionResourceMapper;
import com.yinhe.ec.cpps.sys.model.PermissionResource;
import com.yinhe.ec.cpps.sys.service.PermissionResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限项与资源关系
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class PermissionResourceServiceImpl implements PermissionResourceService
{

	@Autowired
	private PermissionResourceMapper permissionResourceMapper;

	@Override
	public List<PermissionResource> queryPermissionResourceList(PermissionResource permissionResource)
	{
		return permissionResourceMapper.queryPermissionResourceList(permissionResource);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertPermissionResource(PermissionResource permissionResource)
	{
		if (null == permissionResource)
		{
			return;
		}
		PermissionResource permissionRes = new PermissionResource();
		permissionRes.setPermissionId(permissionResource.getPermissionId());
		deletePermissionResource(permissionRes);
		if (ArrayUtil.isNotEmpty(permissionResource.getResourceIds()))
		{
			permissionResourceMapper.insertPermissionResource(permissionResource);
		}
	}

	@Override
	public void deletePermissionResource(PermissionResource permissionResource)
	{
		permissionResourceMapper.deletePermissionResource(permissionResource);
	}

	@Override
	public void updatePermissionResource(PermissionResource permissionResource)
	{
		permissionResourceMapper.updatePermissionResource(permissionResource);
	}

}
