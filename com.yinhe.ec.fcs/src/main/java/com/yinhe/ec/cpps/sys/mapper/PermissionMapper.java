package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限项
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询权限项分页列表
     *
     * @return
     */
    List<Permission> queryPermissionList(@Param("permission") Permission permission);

    String getPermissionStrByUserId(@Param("userId") Long userId);

    Integer addPermission(@Param("permission") Permission permission);

    Integer batchDelete(Permission permission);

    Integer updatePermission(Permission permission);
}
