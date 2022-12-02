package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface MenuMapper extends BaseMapper<Menu> {
    
    List<Menu> queryRootMenu();

    /**
     * 查询菜单分页列表
     * @return
     */
    List<Menu> queryMenuList(@Param("menu") Menu menu);

    List<Menu> queryMenuByAccount(@Param("account") String account);

    Integer addMenu(@Param("menu") Menu menu);

    Integer updateMenu(Menu menu);
}
