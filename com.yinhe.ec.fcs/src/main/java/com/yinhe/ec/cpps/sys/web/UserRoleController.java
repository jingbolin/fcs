package com.yinhe.ec.cpps.sys.web;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.UserRole;
import com.yinhe.ec.cpps.sys.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 用户角色
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("用户角色API")
@RestController
@RequestMapping("/sys/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查询用户角色列表
     */
    @ApiOperation(value = "查询用户角色列表", notes = "查询用户角色列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listUserRole(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, UserRole userRole) {
        PageInfo<UserRole> userRolePage = userRoleService.queryUserRolePage(pageNum, pageSize, userRole);
        return ApiResult.ok().put("page", userRolePage);
    }

    /**
     * 保存用户角色
     */
    @ApiOperation(value = "保存用户角色", notes = "保存用户角色", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveUserRole(@RequestBody UserRole userRole) {
        userRoleService.saveUserRole(userRole);
        return ApiResult.ok();
    }

    /**
     * 删除用户角色
     */
    @ApiOperation(value = "删除用户角色", notes = "删除用户角色", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteUserRole(@RequestBody Long[] userIds) {
        userRoleService.batchDeleteUserRole(Arrays.asList(userIds));
        return ApiResult.ok();
    }
}
