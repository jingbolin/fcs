package com.yinhe.ec.cpps.sys.service;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Menu;

import java.util.List;

/**
 * 菜单
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface MenuService {

    List<Menu> queryMenuList();

    List<Menu> queryMenuByAccount(String account);

    void addMenu(Menu menu);

    ApiResult deleteMenu(Long[] ids);

    Integer updateMenu(Menu menu);
}
