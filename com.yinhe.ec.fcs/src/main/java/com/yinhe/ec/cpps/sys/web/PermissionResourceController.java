package com.yinhe.ec.cpps.sys.web;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.PermissionResource;
import com.yinhe.ec.cpps.sys.service.PermissionResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限项与资源关系
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("权限项与资源关系API")
@RestController
@RequestMapping("/sys/apermissionresource")
public class PermissionResourceController {
    @Autowired
    private PermissionResourceService permissionResourceService;

    /**
     * 查询权限项与资源关系列表
     */
    @ApiOperation(value = "查询权限项与资源关系列表", notes = "查询权限项与资源关系列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listPermissionResource(PermissionResource permissionResource) {
        return ApiResult.ok().put("page", permissionResourceService.queryPermissionResourceList(permissionResource));
    }

    /**
     * 保存权限项与资源关系
     */
    @ApiOperation(value = "保存权限项与资源关系", notes = "保存权限项与资源关系", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult savePermissionResource(@RequestBody PermissionResource permissionResource) {
        permissionResourceService.insertPermissionResource(permissionResource);
        return ApiResult.ok();
    }

    /**
     * 修改权限项与资源关系
     */
    @ApiOperation(value = "修改权限项与资源关系", notes = "修改权限项与资源关系", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updatePermissionResource(@RequestBody PermissionResource permissionResource) {
        permissionResourceService.updatePermissionResource(permissionResource);
        return ApiResult.ok();
    }

    /**
     * 删除权限项与资源关系
     */
    @ApiOperation(value = "删除权限项与资源关系", notes = "删除权限项与资源关系", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deletePermissionResource(@RequestBody PermissionResource permissionResource) {
        permissionResourceService.deletePermissionResource(permissionResource);
        return ApiResult.ok();
    }

}
