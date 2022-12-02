package com.yinhe.ec.cpps.sys.web;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.UserOrg;
import com.yinhe.ec.cpps.sys.service.UserOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 用户组织间角色
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("用户组织间角色API")
@RestController
@RequestMapping("/sys/userorg")
public class UserOrgController {
    @Autowired
    private UserOrgService userOrgService;

    /**
     * 查询用户组织间角色列表
     */
    @ApiOperation(value = "查询用户组织间角色列表", notes = "查询用户组织间角色列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listUserOrg(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, UserOrg userOrg) {
        PageInfo<UserOrg> userOrgPage = userOrgService.queryUserOrgPage(pageNum, pageSize, userOrg);
        return ApiResult.ok().put("page", userOrgPage);
    }

    /**
     * 保存用户组织间角色
     */
    @ApiOperation(value = "保存用户组织间角色", notes = "保存用户组织间角色", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveUserOrg(@RequestBody UserOrg userOrg) {
        userOrgService.save(userOrg);
        return ApiResult.ok();
    }

    /**
     * 删除用户组织间角色
     */
    @ApiOperation(value = "删除用户组织间角色", notes = "删除用户组织间角色", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteUserOrg(@RequestBody Long[] userIds) {
        userOrgService.deleteUserOrg(Arrays.asList(userIds));
        return ApiResult.ok();
    }

}
