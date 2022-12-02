package com.yinhe.ec.cpps.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.cpps.sys.mapper.MenuMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yinhe.ec.cpps.sys.service.MenuService;

import cn.hutool.core.collection.CollUtil;

/**
 * 菜单
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuMapper> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenuList() {
        List<Menu> menuList = menuMapper.queryMenuList(null);
        return this.formatMenuTree(menuList);
    }

    @Override
    public List<Menu> queryMenuByAccount(String account) {
        List<Menu> menuList = new ArrayList<>();
        if (StringUtils.isNotEmpty(account)) {
            menuList = menuMapper.queryMenuByAccount(account);
            menuList = this.addParentMenu(menuList);
        }
        return this.formatMenuTree(menuList);
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
    }

    /**
     * 根据id数组批量删除菜单
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult deleteMenu(Long[] ids) {

        Menu menu = new Menu();
        menu.setpMenuIdList(Arrays.asList(ids));
        List<Menu> menuList = menuMapper.queryMenuList(menu);
        if (CollUtil.isNotEmpty(menuList)) {
            return ApiResult.error("存在下级菜单,无法删除!");
        }
        menuMapper.delete(Wrappers.<Menu>update().in("id", Arrays.asList(ids)));
        return ApiResult.ok();
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 递归整理菜单树
     * @param menuList 菜单集合
     * @return
     */
    private List<Menu> formatMenuTree(List<Menu> menuList) {
        List<Menu> rootMenuList = menuMapper.queryRootMenu();
        Menu rootMenu = CollUtil.getFirst(rootMenuList);
        if (CollUtil.isNotEmpty(menuList) && rootMenu != null) {
            try {
                this.setChildren(rootMenu, menuList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return CollUtil.toList(rootMenu);
    }

    /**
     * 递归设置菜单树子节点
     * @param parent 父节点
     * @param menuList 菜单集合
     * @return 整理好的菜单树【父节点】
     */
    private Menu setChildren(Menu parent, List<Menu> menuList) {
        if (parent == null) {
            return parent;
        }
        List<Menu> list = new ArrayList<>();
        parent.setChildren(list);
        CollUtil.forEach(menuList, (menu, i) -> {
            Long pMenuId = menu.getPMenuId();
            if (pMenuId != null && pMenuId.equals(parent.getId())) {
                list.add(menu);
                this.setChildren(menu, menuList);
            }
        });
        list.sort(Comparator.comparingLong(Menu::getOrderId));
        return parent;
    }

    /**
     * 附加父节点
     * @param menuList
     * @return 
     */
    private List<Menu> addParentMenu(List<Menu> menuList) {
        if (menuList == null) {
            return Collections.emptyList();
        }
        Set<Long> menuIdSet = menuList.stream().map(Menu::getId).collect(Collectors.toSet());
        if (CollUtil.isNotEmpty(menuList)) {
            List<Long> idList = menuList.stream()
                .map(menu -> menu.getMenuType() != 3 ? menu.getPMenuId() : null)
                .filter(Objects::nonNull)
                .filter(pMenuId -> !menuIdSet.contains(pMenuId))
                .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(idList)) {
                Menu menu = new Menu();
                menu.setIdList(idList);
                List<Menu> pMenuList = menuMapper.queryMenuList(menu);
                pMenuList = this.addParentMenu(pMenuList);
                if (CollUtil.isNotEmpty(pMenuList)) {
                    menuList.addAll(pMenuList);
                }
            }
        }
        return menuList;
    }
}
