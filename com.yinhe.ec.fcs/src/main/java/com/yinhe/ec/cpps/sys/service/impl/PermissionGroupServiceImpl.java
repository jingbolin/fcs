package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.cpps.sys.mapper.PermissionGroupMapper;
import com.yinhe.ec.cpps.sys.mapper.PermissionMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Permission;
import com.yinhe.ec.cpps.sys.model.PermissionGroup;
import com.yinhe.ec.cpps.sys.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 权限分组
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class PermissionGroupServiceImpl extends BaseServiceImpl<PermissionGroup, PermissionGroupMapper> implements PermissionGroupService
{

	@Autowired
	private PermissionGroupMapper permissionGroupMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<PermissionGroup> queryPermissionGroupList(PermissionGroup permissionGroup)
	{
		return permissionGroupMapper.queryPermissionGroupList(permissionGroup);
	}

	@Override
	public void addPermissionGroup(PermissionGroup permissionGroup)
	{
		permissionGroupMapper.addPermissionGroup(permissionGroup);
	}

	@Override
	public void updatePermissionGroup(PermissionGroup permissionGroup)
	{
		permissionGroupMapper.updatePermissionGroup(permissionGroup);
	}

	/**
	 * 根据id数组批量删除权限分组
	 * @param ids
	 * 主键数组
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ApiResult deletePermissionGroup(Long[] ids)
	{
		Permission permission = new Permission();
		permission.setGroupIdList(Arrays.asList(ids));
		List<Permission> groupPermissionList = permissionMapper.queryPermissionList(permission);
		if (CollUtil.isNotEmpty(groupPermissionList))
		{
			return ApiResult.error("权限分组下存在权限,无法删除!");
		}
		PermissionGroup permissionGroup = new PermissionGroup();
		permissionGroup.setIdList(Arrays.asList(ids));
		permissionGroupMapper.batchDelete(permissionGroup);
		return ApiResult.ok(ApiCode.DELETE_SUCCESS);
	}
}
