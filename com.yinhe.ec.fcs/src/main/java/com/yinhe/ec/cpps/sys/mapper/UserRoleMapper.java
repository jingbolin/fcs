package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.cpps.sys.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface UserRoleMapper {
    /**
     * 查询用户角色分页列表
     *
     * @return
     */
    List<UserRole> queryUserRoleList(@Param("userRole") UserRole userRole);

    void insertUserRole(@Param("userRole") UserRole userRole);

    Integer deleteUserRole(@Param("userRole") UserRole userRole);
}
