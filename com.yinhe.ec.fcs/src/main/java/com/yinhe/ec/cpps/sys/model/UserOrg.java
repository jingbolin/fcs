package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户组织间角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_user_org")
@ApiModel(value = "UserOrg对象", description = "用户组织间角色")

public class UserOrg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户标识
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value = "用户标识")
    private Long userId;
    /**
     * 组织标识
     */
    @TableField("org_id")
    @ApiModelProperty(value = "组织标识")
    private Long orgId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
