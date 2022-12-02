package com.yinhe.ec.cpps.sys.service;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.PermissionGroup;

import java.util.List;

/**
 * 权限分组
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface PermissionGroupService{

    List<PermissionGroup> queryPermissionGroupList(PermissionGroup permissionGroup);

    void addPermissionGroup(PermissionGroup permissionGroup);

    void updatePermissionGroup(PermissionGroup permissionGroup);

    ApiResult deletePermissionGroup(Long[] ids);
}

