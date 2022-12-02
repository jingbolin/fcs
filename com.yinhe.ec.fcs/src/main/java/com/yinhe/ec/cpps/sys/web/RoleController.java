package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Role;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 角色
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("角色API")
@RestController
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listRole(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, Role role) {
        PageInfo<Role> rolePage = roleService.queryRolePage(pageNum, pageSize, role);
        return ApiResult.ok().put("page", rolePage);
    }

    /**
     * 查询已分配和未分配角色列表
     */
    @ApiOperation(value = "查询已分配和未分配角色列表", notes = "查询已分配和未分配角色列表", response = ApiResult.class)
    @PostMapping("/listAassignedAndUnAassignedRes")
    public ApiResult listAassignedAndUnAassignedRes(Long userId, Long roleId) {
        JSONObject aassignedAndUnAassignedJsonObject = roleService.listAassignedAndUnAassignedRole(userId, roleId);
        return ApiResult.ok().put("data", aassignedAndUnAassignedJsonObject);
    }

    /**
     * 查询角色
     */
    @ApiOperation(value = "查询角色", notes = "查询角色", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryRole(@RequestParam("id") Long id) {
        Role role = new Role();
        role.setId(id);
        List<Role> roleList = roleService.queryRoleList(role);
        if (CollUtil.isNotEmpty(roleList)) {
            return ApiResult.ok().put("role", roleList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存角色
     */
    @ApiOperation(value = "保存角色", notes = "保存角色", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveRole(@RequestBody Role role) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        role.setCreateBy(userTemp.getId());
        roleService.insertRole(role);
        return ApiResult.ok();
    }

    /**
     * 修改角色
     */
    @ApiOperation(value = "修改角色", notes = "修改角色", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateRole(@RequestBody Role role) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        role.setUpdateBy(userTemp.getId());
        role.setUpdateTime(new Date());
        roleService.updateRole(role);
        return ApiResult.ok();
    }

    /**
     * 删除角色
     */
    @ApiOperation(value = "删除角色", notes = "删除角色", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteRole(@RequestBody Long[] ids) {
        return roleService.deleteRoleList(ids);
    }

}
