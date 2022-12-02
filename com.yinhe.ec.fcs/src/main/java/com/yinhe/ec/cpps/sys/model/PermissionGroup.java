package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 权限分组
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_permission_group")
@ApiModel(value = "PermissionGroup对象", description = "权限分组")

public class PermissionGroup extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限分组名称
     */
    @TableField("name")
    @ApiModelProperty(value = "权限分组名称")
    private String name;
    /**
     * id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "id集合")
    private List<Long> idList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
