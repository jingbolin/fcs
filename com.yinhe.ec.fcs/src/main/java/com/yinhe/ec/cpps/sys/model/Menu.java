package com.yinhe.ec.cpps.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@TableName("sa_menu")
@ApiModel(value = "Menu对象", description = "菜单")
public class Menu extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资源标识
     */
    @TableField("resource_id")
    @ApiModelProperty(value = "资源标识")
    private Long resourceId;
    /**
     * 资源名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "资源名称")
    private String resName;
    /**
     * 菜单标题
     */
    @TableField("menu_title")
    @ApiModelProperty(value = "菜单标题")
    private String menuTitle;
    /**
     * 菜单标题（英文）
     */
    @TableField("menu_title_en")
    @ApiModelProperty(value = "菜单标题(英文)")
    private String menuTitleEn;
    /**
     * 父菜单标识
     */
    @TableField("p_menu_id")
    @ApiModelProperty(value = "父菜单标识")
    private Long pMenuId;
    /**
     * 菜单类型 1 菜单 2 菜单夹
     */
    @TableField("menu_type")
    @ApiModelProperty(value = "菜单类型 1 菜单 2 菜单夹")
    private Long menuType;
    /**
     * 菜单类型 1 菜单 2 菜单夹
     */
    @TableField("menu_classify")
    @ApiModelProperty(value = "菜单分类,1、监视2、分析3、运维4、配置")
    private Integer menuClassify;
    /**
     * 菜单打开方式
     */
    @TableField("link_type")
    @ApiModelProperty(value = "菜单打开方式")
    private String linkType;
    /**
     * 选中时图标
     */
    @TableField("select_img")
    @ApiModelProperty(value = "选中时图标")
    private String selectImg;
    /**
     * 未选中时图标
     */
    @TableField("unselect_img")
    @ApiModelProperty(value = "未选中时图标")
    private String unselectImg;
    /**
     * 序号
     */
    @TableField("order_id")
    @ApiModelProperty(value = "序号")
    private Long orderId;

    /**
     * uri
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "uri")
    private String uri;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "子菜单")
    private List<Menu> children = new ArrayList<Menu>();
    /**
     * 资源id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "资源id集合")
    private List<Long> resourceIdList;
    /**
     * 查询最顶层父菜单
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "查询最顶层父菜单")
    private Integer queryParentMenu;
    /**
     * 资源id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "父菜单id集合")
    private List<Long> pMenuIdList;

    /**
     * 菜单id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "菜单id集合")
    private List<Long> idList;


    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Long getPMenuId() {
        return pMenuId;
    }

    public void setPMenuId(Long pMenuId) {
        this.pMenuId = pMenuId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getSelectImg() {
        return selectImg;
    }

    public void setSelectImg(String selectImg) {
        this.selectImg = selectImg;
    }

    public String getUnselectImg() {
        return unselectImg;
    }

    public void setUnselectImg(String unselectImg) {
        this.unselectImg = unselectImg;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Long getMenuType() {
        return menuType;
    }

    public void setMenuType(Long menuType) {
        this.menuType = menuType;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public List<Long> getpMenuIdList() {
        return pMenuIdList;
    }

    public void setpMenuIdList(List<Long> pMenuIdList) {
        this.pMenuIdList = pMenuIdList;
    }

    public Integer getQueryParentMenu() {
        return queryParentMenu;
    }

    public void setQueryParentMenu(Integer queryParentMenu) {
        this.queryParentMenu = queryParentMenu;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Integer getMenuClassify() {
        return menuClassify;
    }

    public String getMenuTitleEn() {
        return menuTitleEn;
    }

    public void setMenuTitleEn(String menuTitleEn) {
        this.menuTitleEn = menuTitleEn;
    }

    public Long getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(Long pMenuId) {
        this.pMenuId = pMenuId;
    }

    public void setMenuClassify(Integer menuClassify) {
        this.menuClassify = menuClassify;
    }
}
