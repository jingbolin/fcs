package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 客户、群组、公司、分公司等
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_org_type")
@ApiModel(value = "OrgType对象", description = "客户、群组、公司、分公司等")

public class OrgType extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 组织机构编码
     */
    @TableField("code")
    @ApiModelProperty(value = "组织机构编码")
    private String code;
    /**
     * 名称
     */
    @TableField("name")
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "id集合")
    private List<Long> idList;
    /**
     * 序号
     */
    @TableField("order_id")
    @ApiModelProperty(value = "序号")
    private Long orderId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
