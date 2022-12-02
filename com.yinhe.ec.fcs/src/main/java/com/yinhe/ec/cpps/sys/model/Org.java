package com.yinhe.ec.cpps.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织机构
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@TableName("sa_org")
@ApiModel(value = "Org对象", description = "组织机构")

public class Org extends BaseModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * 组织机构类型、电站、群组等
	 */
	@TableField("org_type_id")
	@ApiModelProperty(value = "组织机构类型、电站、群组等")
	private Long orgTypeId;
	/**
	 * 父节点标识
	 */
	@TableField("parent_id")
	@ApiModelProperty(value = "父节点标识")
	private Long parentId;
	/**
	 * 用“|”分隔
	 */
	@TableField("org_tree")
	@ApiModelProperty(value = "用“|”分隔")
	private String orgTree;
	/**
	 * 组织机构名称
	 */
	@TableField("title")
	@ApiModelProperty(value = "组织机构名称")
	private String title;
	/**
	 * 序号
	 */
	@TableField("order_id")
	@ApiModelProperty(value = "序号")
	private Long orderId;

	@ApiModelProperty(value = "创建时间字符串")
	@TableField(exist = false)
	private String createTimeStr;
	@ApiModelProperty(value = "修改时间字符串")
	@TableField(exist = false)
	private String updateTimeStr;
	/**
	 * 子组织列表
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "子组织列表")
	private List<Org> children = new ArrayList<Org>();

	/**
	 * 查询根节点组织 1:查询根节点组织
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "查询根节点组织")
	private Integer queryParent;
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
	 * 1:查询已分配电站，2:查询未分配电站
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "1:查询已分配电站，2:查询未分配电站")
	private Integer assigned;
	/**
	 * sa_org_ast_psr表组织id
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "sa_org_ast_psr表组织id")
	private Long orgId;
	/**
	 * 父组织id集合
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "父组织id集合")
	private List<Long> parentIdList;
	/**
	 * 组织类型id集合
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "组织类型id集合")
	private List<Long> orgTypeIdList;
	/**
	 * 登录用户的user主键
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "登录用户的user主键")
	private Long userId;
	/**
	 * 组织id集合
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "组织id集合")
	private List<Long> orgIdList;

	public Long getOrgTypeId()
	{
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId)
	{
		this.orgTypeId = orgTypeId;
	}

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public String getOrgTree()
	{
		return orgTree;
	}

	public void setOrgTree(String orgTree)
	{
		this.orgTree = orgTree;
	}

	public Long getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Long orderId)
	{
		this.orderId = orderId;
	}

	public String getCreateTimeStr()
	{
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr)
	{
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr()
	{
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr)
	{
		this.updateTimeStr = updateTimeStr;
	}

	public List<Org> getChildren()
	{
		return children;
	}

	public void setChildren(List<Org> children)
	{
		this.children = children;
	}

	public Integer getQueryParent()
	{
		return queryParent;
	}

	public void setQueryParent(Integer queryParent)
	{
		this.queryParent = queryParent;
	}

	public String getCreateUserName()
	{
		return createUserName;
	}

	public void setCreateUserName(String createUserName)
	{
		this.createUserName = createUserName;
	}

	public String getUpdateUserName()
	{
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName)
	{
		this.updateUserName = updateUserName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Integer getAssigned()
	{
		return assigned;
	}

	public void setAssigned(Integer assigned)
	{
		this.assigned = assigned;
	}

	public Long getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Long orgId)
	{
		this.orgId = orgId;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public List<Long> getParentIdList()
	{
		return parentIdList;
	}

	public void setParentIdList(List<Long> parentIdList)
	{
		this.parentIdList = parentIdList;
	}

	public List<Long> getOrgTypeIdList()
	{
		return orgTypeIdList;
	}

	public void setOrgTypeIdList(List<Long> orgTypeIdList)
	{
		this.orgTypeIdList = orgTypeIdList;
	}

	public List<Long> getOrgIdList()
	{
		return orgIdList;
	}

	public void setOrgIdList(List<Long> orgIdList)
	{
		this.orgIdList = orgIdList;
	}
}
