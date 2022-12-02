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
 * 用户角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_user_role")
@ApiModel(value = "UserRole对象", description = "用户角色")

public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户标识
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value = "用户标识")
    private Long userId;
    /**
     * 角色标识
     */
    @TableField("role_id")
    @ApiModelProperty(value = "角色标识")
    private Long roleId;

    /**
     * 用户标识集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户标识集合")
    private List<Long> userIds;
    /**
     * 角色标识集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色标识集合")
    private List<Long> roleIds;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
