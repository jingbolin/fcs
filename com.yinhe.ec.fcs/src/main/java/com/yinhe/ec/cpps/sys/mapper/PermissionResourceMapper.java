package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.cpps.sys.model.PermissionResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限项与资源关系
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface PermissionResourceMapper {
    /**
     * 查询权限项与资源关系分页列表
     *
     * @return
     */
    List<PermissionResource> queryPermissionResourceList(@Param("permissionResource") PermissionResource permissionResource);

    /**
     * 添加权限项与资源关系
     */
    Integer insertPermissionResource(@Param("permissionResource") PermissionResource permissionResource);

    /**
     * 删除权限项与资源关系
     *
     * @param permissionResource
     * @return
     */
    Integer deletePermissionResource(@Param("permissionResource") PermissionResource permissionResource);

    /**
     * 更新权限和资源关系
     *
     * @param permissionResource
     */
    void updatePermissionResource(@Param("permissionResource") PermissionResource permissionResource);
}
