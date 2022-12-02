package com.yinhe.ec.cpps.sys.service;

import com.yinhe.ec.cpps.sys.model.PermissionResource;

import java.util.List;

/**
 * 权限项与资源关系
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface PermissionResourceService {

    public List<PermissionResource> queryPermissionResourceList(PermissionResource permissionResource);

    void insertPermissionResource(PermissionResource permissionResource);

    void deletePermissionResource(PermissionResource permissionResource);

    void updatePermissionResource(PermissionResource permissionResource);
}

