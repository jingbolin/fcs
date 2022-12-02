package com.yinhe.ec.cpps.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.mapper.UserOrgMapper;
import com.yinhe.ec.cpps.sys.model.UserOrg;
import com.yinhe.ec.cpps.sys.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户组织间角色
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class UserOrgServiceImpl implements UserOrgService
{

	@Autowired
	private UserOrgMapper userOrgMapper;

	@Override
	public PageInfo<UserOrg> queryUserOrgPage(Integer pageNum, Integer pageSize, UserOrg userOrg)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<UserOrg> userOrgListPage = userOrgMapper.queryUserOrgList(userOrg);
		return new PageInfo<UserOrg>(userOrgListPage);
	}

	@Override
	public void save(UserOrg userOrg)
	{
		userOrgMapper.insert(userOrg);
	}

	@Override
	public void deleteUserOrg(List<Long> userIds)
	{
		userOrgMapper.batchDeleteUserOrg(userIds);
	}
}
