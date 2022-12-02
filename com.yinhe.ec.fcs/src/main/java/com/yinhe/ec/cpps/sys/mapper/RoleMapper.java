package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询角色分页列表
     *
     * @return
     */
    List<Role> queryRoleList(@Param("role") Role role);

    String getRoleStrByUserId(@Param("userId") Long userId);

    /**
     * 添加角色
     */
    Integer insertRole(@Param("role") Role role);

    Integer batchDelete(Role role);

    /**
     * 查询未分配的角色
     * @param userId
     * @return
     */
    List<Role> unAssignedRoleList(@Param("userId")Long userId);
}
