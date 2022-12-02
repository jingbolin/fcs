package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;

@TableName("sa_event")
@SuppressWarnings("serial")
public class SysEvent extends BaseModel {
	//标题
	@TableField("title")
	private String title;

	//请求路径
	@TableField("request_uri")
	private String requestUri;

	//参数
	@TableField("parameters")
	private String parameters;

	@TableField("method")
	private String method;

	//地址
	@TableField("client_host")
	private String clientHost;

	//用户代理
	@TableField("user_agent")
	private String userAgent;

	//请求状态
	@TableField("status")
	private Integer status;

	//用户名
	@TableField("user_name")
	private String userName;

	//账户
	@TableField("account")
	private String account;

	//联系方式
	@TableField("user_phone")
	private String userPhone;


    /*@TableField("type")
    private String type;*/


	//时间区间
	@TableField(exist = false)
	private String dateInterval;

	//
	@TableField(exist = false)
	private Integer pageNum;

	//时间区间
	@TableField(exist = false)
	private Integer pageSize;

	/* public String getType() {
         return type;
     }

     public void setType(String type) {
         this.type = type;
     }
 */
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDateInterval() {
		return dateInterval;
	}

	public void setDateInterval(String dateInterval) {
		this.dateInterval = dateInterval;
	}

	/**
	 * @return the value of sys_event.title_
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the value for sys_event.title_
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * @return the value of sys_event.request_uri
	 */
	public String getRequestUri() {
		return requestUri;
	}

	/**
	 * @param requestUri the value for sys_event.request_uri
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri == null ? null : requestUri.trim();
	}

	/**
	 * @return the value of sys_event.parameters_
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the value for sys_event.parameters_
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters == null ? null : parameters.trim();
	}

	/**
	 * @return the value of sys_event.method_
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the value for sys_event.method_
	 */
	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}

	/**
	 * @return the value of sys_event.client_host
	 */
	public String getClientHost() {
		return clientHost;
	}

	/**
	 * @param clientHost the value for sys_event.client_host
	 */
	public void setClientHost(String clientHost) {
		this.clientHost = clientHost == null ? null : clientHost.trim();
	}

	/**
	 * @return the value of sys_event.user_agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the value for sys_event.user_agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent == null ? null : userAgent.trim();
	}

	/**
	 * @return the value of sys_event.status_
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the value for sys_event.status_
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
}
