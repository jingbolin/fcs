package com.yinhe.ec.cpps.sys.service;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.UserRole;

import java.util.List;

/**
 * 用户角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface UserRoleService {

    PageInfo<UserRole> queryUserRolePage(Integer pageNum, Integer pageSize, UserRole userRole);

    void saveUserRole(UserRole userRole);

    Integer batchDeleteUserRole(List<Long> asList);
}

