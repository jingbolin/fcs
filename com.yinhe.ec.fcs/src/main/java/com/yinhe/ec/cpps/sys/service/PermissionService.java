package com.yinhe.ec.cpps.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Permission;

import java.util.List;

/**
 * 权限项
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface PermissionService{

    PageInfo<Permission> queryPermissionPage(Integer pageNum, Integer pageSize, Permission permission);

    void addPermission(Permission permission);

    JSONObject listAassignedAndUnAassignedPermissions(Long roleId, Long permissionGroupId);

    ApiResult deletePermissions(Long[] ids);

    List<Permission> queryPermissionList(Permission permission);

    Integer updatePermission(Permission permission);
}

