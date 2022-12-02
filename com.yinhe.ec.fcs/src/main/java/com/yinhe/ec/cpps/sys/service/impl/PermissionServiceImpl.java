package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.cpps.sys.mapper.PermissionMapper;
import com.yinhe.ec.cpps.sys.mapper.ResourceMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Permission;
import com.yinhe.ec.cpps.sys.service.PermissionService;
import com.yinhe.ec.cpps.sys.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限项
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, PermissionMapper> implements PermissionService
{

	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public PageInfo<Permission> queryPermissionPage(Integer pageNum, Integer pageSize, Permission permission)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissionListPage = permissionMapper.queryPermissionList(permission);
		return new PageInfo<Permission>(permissionListPage);
	}

	@Override
	public void addPermission(Permission permission)
	{
		permissionMapper.addPermission(permission);
	}

	@Override
	public JSONObject listAassignedAndUnAassignedPermissions(Long roleId, Long permissionGroupId)
	{
		// 根据角色id查询已分配的权限
		Permission assignedPermission = new Permission();
		assignedPermission.setRoleId(roleId);
		assignedPermission.setGroupId(permissionGroupId);
		assignedPermission.setAssigned(1);
		List<Permission> assignedPermissionList = permissionMapper.queryPermissionList(assignedPermission);
		assignedPermissionList = assignedPermissionList.stream().filter(permission -> !permission.isDefault()).collect(Collectors.toList());
		// 根据角色id和查询已分配的权限
		Permission unAssignedPermission = new Permission();
		unAssignedPermission.setRoleId(roleId);
		unAssignedPermission.setAssigned(2);
		unAssignedPermission.setGroupId(permissionGroupId);
		List<Permission> unAssignedPermissionList = permissionMapper.queryPermissionList(unAssignedPermission);
		unAssignedPermissionList = unAssignedPermissionList.stream().filter(permission -> !permission.isDefault()).collect(Collectors.toList());
		JSONObject resultJsonObject = new JSONObject();
		resultJsonObject.put("assignedPermission", assignedPermissionList);
		resultJsonObject.put("unAssignedPermission", unAssignedPermissionList);
		return resultJsonObject;
	}

	/**
	 * 根据id数组批量删除权限
	 * @param ids
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ApiResult deletePermissions(Long[] ids)
	{
		Resource resource = new Resource();
		resource.setRelevancePerm(1);
		resource.setPermissionIdList(Arrays.asList(ids));
		List<Resource> resourceList = resourceMapper.queryResourceList(resource);
        if (CollUtil.isNotEmpty(resourceList)) {
            // StringBuffer sb = new StringBuffer();
            // resourceList.stream().forEach(res -> {
            //     sb.append("权限:【").append(res.getPermissionName()).append("】存在关联的资源:【").append(res.getUri()).append("】").append("无法删除!");
            // });
            // return ApiResult.error(sb.toString());
            return ApiResult.error("删除失败，该权限存在关联的资源!");
        }
		Permission permission = new Permission();
		permission.setIdList(Arrays.asList(ids));
		permissionMapper.batchDelete(permission);
		return ApiResult.ok();
	}

	@Override
	public List<Permission> queryPermissionList(Permission permission)
	{
		return permissionMapper.queryPermissionList(permission);
	}

	@Override
	public Integer updatePermission(Permission permission)
	{
		return permissionMapper.updatePermission(permission);
	}
}
