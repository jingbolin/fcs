package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 资源分组
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_resource_group")
@ApiModel(value = "ResourceGroup对象", description = "资源分组")

public class ResourceGroup extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 名称
     */
    @TableField("name")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 名称
     */
    @TableField("name_en")
    @ApiModelProperty(value = "名称(英文)")
    private String nameEn;

    /**
     * id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "id集合")
    private List<Long> idList;


    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getIdList() {
        return idList;
    }
}
