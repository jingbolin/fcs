package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 权限项
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@TableName("sa_permission")
@ApiModel(value = "Permission对象", description = "权限项")
public class Permission extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 权限分组标识
     */
    @TableField("group_id")
    @ApiModelProperty(value = "权限分组标识")
    private Long groupId;
    /**
     * 权限项名称
     */
    @TableField("permission_name")
    @ApiModelProperty(value = "权限项名称")
    private String permissionName;
    /**
     * 是否管理权限
     */
    @TableField("is_manager")
    @ApiModelProperty(value = "是否管理权限")
    private Boolean manager;

    @ApiModelProperty(value = "创建时间字符串")
    @TableField(exist = false)
    private String createTimeStr;
    @ApiModelProperty(value = "修改时间字符串")
    @TableField(exist = false)
    private String updateTimeStr;
    @TableField(exist = false)
    @ApiModelProperty(value = "资源id")
    private Long resoureId;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    /**
     * 1:查询已分配权限，2:查询未分配权限
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:查询已分配权限，2:查询未分配权限")
    private Integer assigned;

    /**
     * 关联查询资源
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:关联查询资源")
    private Integer relevanceRes;

    /**
     * 创建者用户名
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "创建者用户名")
    private String createUserName;
    /**
     * 更新者用户名
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "更新者用户名")
    private String updateUserName;
    /**
     * 分组id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "分组id集合")
    private List<Long> groupIdList;
    /**
     * id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "id集合")
    private List<Long> idList;
    /**
     * 权限分组标识
     */
    @TableField("is_default")
    @ApiModelProperty(value = "是否是默认权限，初始化数据，不能删除,不能修改")
    private boolean isDefault;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public Long getResoureId() {
        return resoureId;
    }

    public void setResoureId(Long resoureId) {
        this.resoureId = resoureId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getAssigned() {
        return assigned;
    }

    public void setAssigned(Integer assigned) {
        this.assigned = assigned;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getRelevanceRes() {
        return relevanceRes;
    }

    public void setRelevanceRes(Integer relevanceRes) {
        this.relevanceRes = relevanceRes;
    }

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public  void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
