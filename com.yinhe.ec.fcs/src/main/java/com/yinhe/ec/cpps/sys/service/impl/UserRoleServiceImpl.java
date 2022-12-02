package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.mapper.UserRoleMapper;
import com.yinhe.ec.cpps.sys.model.UserRole;
import com.yinhe.ec.cpps.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class UserRoleServiceImpl implements UserRoleService
{

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public PageInfo<UserRole> queryUserRolePage(Integer pageNum, Integer pageSize, UserRole userRole)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<UserRole> userRoleListPage = userRoleMapper.queryUserRoleList(userRole);
		return new PageInfo<UserRole>(userRoleListPage);
	}

    @Override
    public void saveUserRole(UserRole userRole) {
        if (null == userRole) {
            return;
        }
        UserRole userRoleParam = new UserRole();
        if (userRole.getRoleId() != null) {
            userRoleParam.setRoleId(userRole.getRoleId());
            userRoleMapper.deleteUserRole(userRoleParam);
            if (CollUtil.isNotEmpty(userRole.getUserIds())) {
                userRoleParam.setUserIds(userRole.getUserIds());
                userRoleMapper.insertUserRole(userRoleParam);
            }
        }
        if (userRole.getUserId() != null) {
            userRoleParam.setUserId(userRole.getUserId());
            userRoleMapper.deleteUserRole(userRoleParam);
            if (CollUtil.isNotEmpty(userRole.getRoleIds())) {
                userRoleParam.setRoleIds(userRole.getRoleIds());
                userRoleMapper.insertUserRole(userRoleParam);
            }
        }
    }

	@Override
	public Integer batchDeleteUserRole(List<Long> userIdList)
	{
		UserRole userRoleParam = new UserRole();
		userRoleParam.setUserIds(userIdList);
		return userRoleMapper.deleteUserRole(userRoleParam);
	}
}
