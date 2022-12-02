package com.yinhe.ec.cpps.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Role;

import java.util.List;

/**
 * 角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface RoleService{

    PageInfo<Role> queryRolePage(Integer pageNum, Integer pageSize, Role role);

    void insertRole(Role role);

    JSONObject listAassignedAndUnAassignedRole(Long userId, Long roleId);

    ApiResult deleteRoleList(Long[] ids);

    List<Role> queryRoleList(Role role);

    Integer updateRole(Role role);
}

