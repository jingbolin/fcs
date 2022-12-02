package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 权限项与资源关系
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_permission_resource")
@ApiModel(value = "PermissionResource对象", description = "权限项与资源关系")

public class PermissionResource implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限标识
     */
    @TableId(value = "permission_id", type = IdType.INPUT)
    @ApiModelProperty(value = "权限标识")
    private BigDecimal permissionId;
    /**
     * 资源标识
     */
    @TableField("resource_id")
    @ApiModelProperty(value = "资源标识")
    private BigDecimal resourceId;

    /**
     * 资源标识id数组
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "资源标识资源标识id数组")
    private Long[] resourceIds;

    public BigDecimal getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BigDecimal permissionId) {
        this.permissionId = permissionId;
    }

    public BigDecimal getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigDecimal resourceId) {
        this.resourceId = resourceId;
    }

    public Long[] getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Long[] resourceIds) {
        this.resourceIds = resourceIds;
    }
}
