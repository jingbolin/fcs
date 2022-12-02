package com.yinhe.ec.cpps.sys.service;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.RolePermission;

import java.util.List;

/**
 * 角色与权限关系
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface RolePermissionService {

    PageInfo<RolePermission> queryRolePermissionPage(Integer pageNum, Integer pageSize, RolePermission rolePermission);

    void insertRolePermission(RolePermission rolePermission);

    void batchDelete(List<Long> roleIdList);
}

