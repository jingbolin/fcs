package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 资源
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_resource")
@ApiModel(value = "Resource对象", description = "资源")

public class Resource extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 资源类型
     */
    @TableField("type_code")
    @ApiModelProperty(value = "资源类型")
    private String typeCode;
    /**
     * 资源分组
     */
    @TableField("group_id")
    @ApiModelProperty(value = "资源分组")
    private Long groupId;
    /**
     * 资源名称
     */
    @TableField("name")
    @ApiModelProperty(value = "资源名称")
    private String name;
    /**
     * 资源名称
     */
    @TableField("name_en")
    @ApiModelProperty(value = "资源名称(英文)")
    private String nameEn;
    /**
     * 统一资源标识符
     */
    @TableField("uri")
    @ApiModelProperty(value = "统一资源标识符")
    private String uri;
    /**
     * 是否管理资源
     */
    @TableField("is_manager")
    @ApiModelProperty(value = "是否管理资源")
    private Boolean isManager;
    /**
     * 默认资源为系统功能，初始化完成后，不能修改。
     */
    @TableField("is_default")
    @ApiModelProperty(value = "默认资源为系统功能，初始化完成后，不能修改。")
    private Boolean isDefault;

    @ApiModelProperty(value = "修改时间字符串")
    @TableField(exist = false)
    private String updateTimeStr;
    /**
     * 权限id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "权限id")
    private Long permissionId;
    /**
     * 权限名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    /**
     * 权限id列表
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "权限id列表")
    private List<Long> permissionIdList;

    /**
     * true:查询已分配资源，false:查询未分配资源
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:查询已分配资源，2:查询未分配资源")
    private Integer assigned;
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
     * 用户id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 创建者用户名
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "创建者账号")
    private String account;
    /**
     * 关联权限
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:关联权限")
    private Integer relevancePerm;
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getRelevancePerm() {
        return relevancePerm;
    }

    public void setRelevancePerm(Integer relevancePerm) {
        this.relevancePerm = relevancePerm;
    }

    public List<Long> getPermissionIdList() {
        return permissionIdList;
    }

    public void setPermissionIdList(List<Long> permissionIdList) {
        this.permissionIdList = permissionIdList;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getIdList() {
        return idList;
    }
}
