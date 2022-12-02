package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Permission;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 权限项
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("权限项API")
@RestController
@RequestMapping("/sys/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询权限项列表
     */
    @ApiOperation(value = "查询权限项列表", notes = "查询权限项列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listPermission(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, Permission permission) {
        PageInfo<Permission> permissionPage = permissionService.queryPermissionPage(pageNum, pageSize, permission);
        return ApiResult.ok().put("page", permissionPage);
    }

    /**
     * 根据角色id查询已分配和未分配权限列表
     */
    @ApiOperation(value = "根据角色id查询已分配和未分配权限列表", notes = "根据角色id查询已分配和未分配权限列表", response = ApiResult.class)
    @PostMapping("/listAassignedAndUnAassignedPermissions")
    public ApiResult listAassignedAndUnAassignedPermissions(Long roleId, Long permissionGroupId) {
        JSONObject aassignedAndUnAassignedJsonObject =
            permissionService.listAassignedAndUnAassignedPermissions(roleId, permissionGroupId);
        return ApiResult.ok().put("data", aassignedAndUnAassignedJsonObject);
    }

    /**
     * 查询权限项
     */
    @ApiOperation(value = "查询权限项", notes = "查询权限项", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryPermission(@RequestParam("id") Long id) {
        Permission permission = new Permission();
        permission.setId(id);
        List<Permission> permissionList = permissionService.queryPermissionList(permission);
        if (CollUtil.isNotEmpty(permissionList)) {
            return ApiResult.ok().put("permission", permissionList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存权限项
     */
    @ApiOperation(value = "保存权限项", notes = "保存权限项", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult savePermission(@RequestBody Permission permission) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        permission.setUpdateBy(userTemp.getId());
        permissionService.addPermission(permission);
        return ApiResult.ok();
    }

    /**
     * 修改权限项
     */
    @ApiOperation(value = "修改权限项", notes = "修改权限项", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updatePermission(@RequestBody Permission permission) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        permission.setUpdateBy(userTemp.getId());
        permission.setUpdateTime(new Date());
        permissionService.updatePermission(permission);
        return ApiResult.ok();
    }

    /**
     * 删除权限项
     */
    @ApiOperation(value = "删除权限项", notes = "删除权限项", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deletePermission(@RequestBody Long[] ids) {
        return permissionService.deletePermissions(ids);
    }
}
