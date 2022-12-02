package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.cpps.sys.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与权限关系
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface RolePermissionMapper {
    /**
     * 查询角色与权限关系分页列表
     *
     * @return
     */
    List<RolePermission> queryRolePermissionList(@Param("rolePermission") RolePermission rolePermission);

    void insertRolePermission(@Param("rolePermission") RolePermission rolePermission);

    void delete(@Param("roleId") Long roleId);
}
