package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.mapper.RolePermissionMapper;
import com.yinhe.ec.cpps.sys.model.RolePermission;
import com.yinhe.ec.cpps.sys.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色与权限关系
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class RolePermissionServiceImpl implements RolePermissionService
{

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public PageInfo<RolePermission> queryRolePermissionPage(Integer pageNum, Integer pageSize, RolePermission rolePermission)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<RolePermission> rolePermissionListPage = rolePermissionMapper.queryRolePermissionList(rolePermission);
		return new PageInfo<RolePermission>(rolePermissionListPage);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertRolePermission(RolePermission rolePermission)
	{
		if (null == rolePermission)
		{
			return;
		}
		rolePermissionMapper.delete(rolePermission.getRoleId());
		if (CollUtil.isNotEmpty(rolePermission.getPermissionIds()))
		{
			List<Long> permissionIds = rolePermission.getPermissionIds().stream().distinct().collect(Collectors.toList());
			rolePermission.setPermissionIds(permissionIds);
			rolePermissionMapper.insertRolePermission(rolePermission);
		}
	}

	@Transactional
	@Override
	public void batchDelete(List<Long> roleIdList)
	{
		for (Long roleId : roleIdList)
		{
			rolePermissionMapper.delete(roleId);
		}
	}
}
