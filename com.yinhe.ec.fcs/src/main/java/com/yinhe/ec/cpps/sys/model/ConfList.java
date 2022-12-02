package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 参数配置表
 *
 * @author zhanglei
 * @date 2020-02-19 13:40:13
 */

@TableName("sa_conf_list")
@ApiModel(value = "ConfList对象", description = "参数配置表")

public class ConfList extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 配置code
     */
    @ApiModelProperty(value = "配置code")
    @TableField(value = "conf_list_code")
    private String confListCode;
    /**
     * 配置类型code
     */
    @ApiModelProperty(value = "配置类型code")
    @TableField("conf_type_code")
    private String confTypeCode;
    /**
     * 配置名
     */
    @ApiModelProperty(value = "配置名")
    @TableField("conf_list_name")
    private String confListName;
    /**
     * 配置值
     */
    @ApiModelProperty(value = "配置值")
    @TableField("conf_list_value")
    private String confListValue;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private String isDelete;
    /**
     * value_list
     */
    @ApiModelProperty(value = "可选值列表")
    @TableField("value_list")
    private String valueList;
    /**
     * pattern
     */
    @ApiModelProperty(value = "正则表达式")
    @TableField("pattern")
    private String pattern;
    /**
     * msg
     */
    @ApiModelProperty(value = "提示文字")
    @TableField("msg")
    private String msg;

    public String getConfListCode() {
        return confListCode;
    }

    public void setConfListCode(String confListCode) {
        this.confListCode = confListCode;
    }

    public String getConfTypeCode() {
        return confTypeCode;
    }

    public void setConfTypeCode(String confTypeCode) {
        this.confTypeCode = confTypeCode;
    }

    public String getConfListName() {
        return confListName;
    }

    public void setConfListName(String confListName) {
        this.confListName = confListName;
    }

    public String getConfListValue() {
        return confListValue;
    }

    public void setConfListValue(String confListValue) {
        this.confListValue = confListValue;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
