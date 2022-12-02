package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.cpps.sys.mapper.RoleMapper;
import com.yinhe.ec.cpps.sys.mapper.UserMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Role;
import com.yinhe.ec.cpps.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleMapper> implements RoleService
{

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageInfo<Role> queryRolePage(Integer pageNum, Integer pageSize, Role role)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roleListPage = roleMapper.queryRoleList(role);
		return new PageInfo<Role>(roleListPage);
	}

	@Override
	public void insertRole(Role role)
	{
		roleMapper.insertRole(role);
	}

	@Override
	public JSONObject listAassignedAndUnAassignedRole(Long userId, Long roleId)
	{
		// 根据用户id查询已分配的角色
		Role assignedRole = new Role();
		assignedRole.setUserId(userId);
		assignedRole.setAssigned(1);
		List<Role> assignedRoleList = roleMapper.queryRoleList(assignedRole);
		// 根据权限id和资源分组id查询已分配的资源
		Role unAssignedRole = new Role();
		unAssignedRole.setUserId(userId);
		unAssignedRole.setAssigned(2);
		List<Role> unAssignedRoleList = roleMapper.unAssignedRoleList(userId);
		JSONObject resultJsonObject = new JSONObject();
		resultJsonObject.put("assignedRole", assignedRoleList);
		resultJsonObject.put("unAssignedRole", unAssignedRoleList);
		return resultJsonObject;
	}

	/**
	 * 根据角色id数组批量删除角色
	 * @param ids
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ApiResult deleteRoleList(Long[] ids)
	{
		// 判断角色是否关联用户
		Role roleParam = new Role();
		roleParam.setIdList(Arrays.asList(ids));
		roleParam.setRelevanceUser(1);
		List<Role> roleList = roleMapper.queryRoleList(roleParam);
        if (CollUtil.isNotEmpty(roleList)) {
            // StringJoiner sj = new StringJoiner("\n\r");
            // roleList.stream().forEach(role -> {
            //     String userNameListStr = role.getUsernameList();
            //     if (StringUtils.isNotEmpty(userNameListStr)) {
            //         if (userNameListStr.length() > 20) {
            //             userNameListStr = userNameListStr.substring(0, 20) + "......";
            //         }
            //     }
            //     sj.add("角色:【" + role.getRoleName()).add("】").add("下面还有用户:【").add(userNameListStr).add("】");
            // });
            // sj.add("无法删除!");
            return ApiResult.error("该角色已分配用户，无法删除!");
        }
		// 判断角色是否关联权限
		Role rolePerParam = new Role();
		rolePerParam.setIdList(Arrays.asList(ids));
		rolePerParam.setRelevancePermission(1);
		List<Role> rolePerList = roleMapper.queryRoleList(rolePerParam);
        if (CollUtil.isNotEmpty(rolePerList)) {
            // StringJoiner rolePerSj = new StringJoiner("\n\r");
            // rolePerList.stream().forEach(role -> {
            //     String permissionNameListStr = role.getPermissionNameList();
            //     if (StringUtils.isNotEmpty(permissionNameListStr)) {
            //         if (permissionNameListStr.length() > 20) {
            //             permissionNameListStr = permissionNameListStr.substring(0, 20) + "......";
            //         }
            //     }
            //     rolePerSj.add("角色:【" + role.getRoleName()).add("】").add("关联权限:【").add(permissionNameListStr).add("】");
            // });
            // rolePerSj.add("无法删除!");
            return ApiResult.error("该角色已关联权限，无法删除!");
        }
		Role role = new Role();
		role.setIdList(Arrays.asList(ids));
		roleMapper.batchDelete(role);
		return ApiResult.ok(ApiCode.DELETE_SUCCESS);
	}

	@Override
	public List<Role> queryRoleList(Role role)
	{
		return roleMapper.queryRoleList(role);
	}

	@Override
	public Integer updateRole(Role role)
	{
		Console.log("updateById");
		return roleMapper.updateById(role);
	}
}
