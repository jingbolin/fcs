package com.yinhe.ec.cpps.sys.web;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.RolePermission;
import com.yinhe.ec.cpps.sys.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 角色与权限关系
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("角色与权限关系API")
@RestController
@RequestMapping("/sys/rolePermission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 查询角色与权限关系列表
     */
    @ApiOperation(value = "查询角色与权限关系列表", notes = "查询角色与权限关系列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listRolePermission(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, RolePermission rolePermission) {
        PageInfo<RolePermission> rolePermissionPage =
            rolePermissionService.queryRolePermissionPage(pageNum, pageSize, rolePermission);
        return ApiResult.ok().put("page", rolePermissionPage);
    }

    /**
     * 保存角色与权限关系
     */
    @ApiOperation(value = "保存角色与权限关系", notes = "保存角色与权限关系", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveRolePermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.insertRolePermission(rolePermission);
        return ApiResult.ok();
    }

    /**
     * 删除角色与权限关系
     */
    @ApiOperation(value = "删除角色与权限关系", notes = "删除角色与权限关系", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteRolePermission(@RequestBody Long[] roleIds) {
        rolePermissionService.batchDelete(Arrays.asList(roleIds));
        return ApiResult.ok();
    }

}
