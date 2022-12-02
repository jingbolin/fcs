package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.PermissionGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限分组
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface PermissionGroupMapper extends BaseMapper<PermissionGroup> {
    /**
     * 查询权限分组分页列表
     *
     * @return
     */
    List<PermissionGroup> queryPermissionGroupList(@Param("permissionGroup") PermissionGroup permissionGroup);


    Integer addPermissionGroup(@Param("permissionGroup") PermissionGroup permissionGroup);

    Integer updatePermissionGroup(@Param("permissionGroup") PermissionGroup permissionGroup);

    Integer batchDelete(PermissionGroup permissionGroup);
}
