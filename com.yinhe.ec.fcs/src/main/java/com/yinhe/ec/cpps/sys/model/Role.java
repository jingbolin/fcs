package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@TableName("sa_role")
@ApiModel(value = "Role对象", description = "角色")
public class Role extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 角色名称
     */
    @TableField("role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色类型(01:业务角色;02:管理角色 ;03:系统内置角色)
     */
    @TableField("role_type")
    @ApiModelProperty(value = "角色类型(01:业务角色;02:管理角色 ;03:系统内置角色)")
    private String roleType;

    @ApiModelProperty(value = "创建时间字符串")
    @TableField(exist = false)
    private String createTimeStr;
    @ApiModelProperty(value = "修改时间字符串")
    @TableField(exist = false)
    private String updateTimeStr;
    /**
     * 用户id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 1:查询已分配角色，2:查询未分配角色
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:查询已分配角色，2:查询未分配角色")
    private Integer assigned;
    /**
     * 1:关联用户
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:关联用户")
    private Integer relevanceUser;
    /**
     * 1:关联权限
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:关联权限")
    private Integer relevancePermission;
    /**
     * id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "id集合")
    private List<Long> idList;
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
     * 用户名集合字符串
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户名集合字符串")
    private String usernameList;
    /**
     * 权限名集合字符串
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "权限名集合字符串")
    private String permissionNameList;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getRelevanceUser() {
        return relevanceUser;
    }

    public void setRelevanceUser(Integer relevanceUser) {
        this.relevanceUser = relevanceUser;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(String usernameList) {
        this.usernameList = usernameList;
    }

    public Integer getRelevancePermission() {
        return relevancePermission;
    }

    public void setRelevancePermission(Integer relevancePermission) {
        this.relevancePermission = relevancePermission;
    }

    public String getPermissionNameList() {
        return permissionNameList;
    }

    public void setPermissionNameList(String permissionNameList) {
        this.permissionNameList = permissionNameList;
    }
}
