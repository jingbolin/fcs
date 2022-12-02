package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.PermissionGroup;
import com.yinhe.ec.cpps.sys.service.PermissionGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限分组
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("权限分组API")
@RestController
@RequestMapping("/sys/permissiongroup")
public class PermissionGroupController {
    @Autowired
    private PermissionGroupService permissionGroupService;

    /**
     * 查询权限分组列表
     */
    @ApiOperation(value = "查询权限分组列表", notes = "查询权限分组列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listPermissionGroup(PermissionGroup permissionGroup) {
        return ApiResult.ok().put("page", permissionGroupService.queryPermissionGroupList(permissionGroup));
    }

    /**
     * 查询权限分组
     */
    @ApiOperation(value = "查询权限分组", notes = "查询权限分组", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryPermissionGroup(@RequestParam("id") Long id) {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setId(id);
        List<PermissionGroup> permissionGroupList = permissionGroupService.queryPermissionGroupList(permissionGroup);
        if (CollUtil.isNotEmpty(permissionGroupList)) {
            return ApiResult.ok().put("permissionGroup", permissionGroupList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存权限分组
     */
    @ApiOperation(value = "保存权限分组", notes = "保存权限分组", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult savePermissionGroup(@RequestBody PermissionGroup permissionGroup) {
        permissionGroupService.addPermissionGroup(permissionGroup);

        return ApiResult.ok();
    }

    /**
     * 修改权限分组
     */
    @ApiOperation(value = "修改权限分组", notes = "修改权限分组", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updatePermissionGroup(@RequestBody PermissionGroup permissionGroup) {
        permissionGroupService.updatePermissionGroup(permissionGroup);
        return ApiResult.ok();
    }

    /**
     * 删除权限分组
     */
    @ApiOperation(value = "删除权限分组", notes = "删除权限分组", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deletePermissionGroup(@RequestBody Long[] ids) {
        return permissionGroupService.deletePermissionGroup(ids);
    }

}
