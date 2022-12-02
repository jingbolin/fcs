package com.yinhe.ec.cpps.sys.web;

import java.util.Date;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Menu;
import com.yinhe.ec.cpps.sys.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinhe.ec.cpps.sys.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("菜单API")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     */
    @ApiOperation(value = "查询菜单列表", notes = "查询菜单列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listMenu() {
        return ApiResult.ok().put("page", menuService.queryMenuList());
    }

    /**
     * 保存菜单
     */
    @ApiOperation(value = "保存菜单", notes = "保存菜单", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveMenu(@RequestBody Menu menu) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        menu.setCreateBy(userTemp.getId());
        if (StringUtils.isBlank(menu.getMenuTitleEn())) {
            menu.setMenuTitleEn(menu.getMenuTitle());
        }
        menuService.addMenu(menu);
        return ApiResult.ok();
    }

    /**
     * 修改菜单
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateMenu(@RequestBody Menu menu) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        menu.setUpdateBy(userTemp.getId());
        menu.setUpdateTime(new Date());
        if (StringUtils.isBlank(menu.getMenuTitleEn())) {
            menu.setMenuTitleEn(menu.getMenuTitle());
        }
        menuService.updateMenu(menu);

        return ApiResult.ok();
    }

    /**
     * 删除菜单
     */
    @ApiOperation(value = "删除菜单", notes = "删除菜单", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteMenu(@RequestBody Long[] ids) {
        return menuService.deleteMenu(ids);
    }
}
