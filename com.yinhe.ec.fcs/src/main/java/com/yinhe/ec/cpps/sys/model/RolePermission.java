package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 角色与权限关系
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_role_permission")
@ApiModel(value = "RolePermission对象", description = "角色与权限关系")

public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色标识
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    @ApiModelProperty(value = "角色标识")
    private Long roleId;
    /**
     * 权限项标识
     */
    @TableField("permission_id")
    @ApiModelProperty(value = "权限项标识")
    private Long permissionId;
    /**
     * 权限项标识集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "权限项标识集合")
    private List<Long> permissionIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
