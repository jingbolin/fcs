package com.yinhe.ec.cpps.sys.service;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.UserOrg;

import java.util.List;

/**
 * 用户组织间角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface UserOrgService {

    PageInfo<UserOrg> queryUserOrgPage(Integer pageNum, Integer pageSize, UserOrg userOrg);

    void save(UserOrg userOrg);

    void deleteUserOrg(List<Long> userIdList);
}

