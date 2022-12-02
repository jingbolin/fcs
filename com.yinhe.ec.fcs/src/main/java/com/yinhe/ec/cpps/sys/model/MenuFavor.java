package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 个人收藏菜单
 *
 * @author
 * @since 2020-07-09
 */
@ApiModel("个人收藏菜单")
@TableName("sa_menu_favor")
@SuppressWarnings("serial")
public class MenuFavor extends BaseModel {

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
	private Long userId;
    @ApiModelProperty(value = "菜单id")
    @TableField("memu_id")
	private Long memuId;
    @ApiModelProperty(value = "菜单名称")
    @TableField(exist=false)
	private String memuName;
    @ApiModelProperty(value = "点击次数")
    @TableField("click_num")
	private Long clickNum;
    @ApiModelProperty(value = "true：已被收藏")
    @TableField("is_favorite")
	private Boolean isFavorite;
    @ApiModelProperty(value = "菜单URL")
    @TableField(exist = false)
	private String url;




	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getMemuId()
	{
		return memuId;
	}

	public void setMemuId(Long memuId)
	{
		this.memuId = memuId;
	}

	public String getMemuName()
	{
		return memuName;
	}

	public void setMemuName(String memuName)
	{
		this.memuName = memuName;
	}

	public void setIsFavorite(Boolean isFavorite)
	{
		this.isFavorite = isFavorite;
	}

	public Long getClickNum()
	{
		return clickNum;
	}

	public void setClickNum(Long clickNum)
	{
		this.clickNum = clickNum;
	}

	public Boolean getIsFavorite()
	{
		return isFavorite;
	}

	public void setFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

}
