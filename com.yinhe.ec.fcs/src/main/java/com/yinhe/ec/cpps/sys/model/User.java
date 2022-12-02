package com.yinhe.ec.cpps.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinhe.ec.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@TableName("sa_user")
@ApiModel(value = "User对象", description = "用户表")
public class User extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @Length(max = 16, message = "账号长度不能大于16位")
    @NotEmpty(message = "账号用户名不能为空")
    @ApiModelProperty(value = "账号")
    @Pattern(regexp = "[^\\u4e00-\\u9fa5]+",message = "账号不能包含中文")
    @TableField("account")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;
    /**
     * 用户类型-01：业务用户、02：管理用户、03：配置用户。
     */
    @ApiModelProperty(value = "用户类型-01：业务用户、02：管理用户、03：配置用户。")
    @TableField("user_type")
    private String userType;
    /**
     * 用户名称
     */
    @NotEmpty(message = "用户名不能为空")
    @Length(max = 16, message = "用户名长度不能大于16位")
    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    private String userName;
    /**
     * 电话
     */
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$", message = "请输入正确的手机号码!")
    @ApiModelProperty(value = "电话")
    @TableField("tel")
    private String tel;
    /**
     * 邮件
     */
    @Pattern(regexp = "^\\s*$|^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "请输入正确的email地址")
    @ApiModelProperty(value = "邮件")
    @TableField("mail")
    @Pattern(regexp = "^\\s*$|[^\\u4e00-\\u9fa5]+",message = "邮箱不能包含中文")
    private String mail;
    /**
     * 是否锁定
     */
    @ApiModelProperty(value = "是否锁定")
    @TableField("is_lock")
    private Boolean isLock;
    /**
     * 锁定时间
     */
    @ApiModelProperty(value = "锁定时间")
    @TableField("lock_time")
    private Date lockTime;
    @TableField(exist = false)
    @ApiModelProperty(value = "锁定时间开始时间")
    private Date lockTimeStart;
    @TableField(exist = false)
    @ApiModelProperty(value = "锁定时间结束时间")
    private Date lockTimeEnd;
    /**
     * 登录失败次数
     */
    @ApiModelProperty(value = "登录失败次数")
    @TableField("fail_count")
    private Long failCount;
    /**
     * 历史密码1
     */
    @ApiModelProperty(value = "历史密码1")
    @TableField("h_password1")
    private String hPassword1;
    /**
     * 历史密码2
     */
    @ApiModelProperty(value = "历史密码2")
    @TableField("h_password2")
    private String hPassword2;
    /**
     * 历史密码3
     */
    @ApiModelProperty(value = "历史密码3")
    @TableField("h_password3")
    private String hPassword3;
    /**
     * 历史密码4
     */
    @ApiModelProperty(value = "历史密码4")
    @TableField("h_password4")
    private String hPassword4;
    /**
     * 历史密码5
     */
    @ApiModelProperty(value = "历史密码5")
    @TableField("h_password5")
    private String hPassword5;
    /**
     * 历史密码6
     */
    @ApiModelProperty(value = "历史密码5")
    @TableField("h_password6")
    private String hPassword6;
    /**
     * 最后一次密码修改日期
     */
    @TableField("last_password_update_date")
    @ApiModelProperty(value = "最后一次密码修改日期")
    private Date lastPasswordUpdateDate;

    /**
     * 关联顾客id
     */
    @ApiModelProperty(value = "关联顾客id")
    @TableField("customer_id")
    private Integer customerId;


    @TableField(exist = false)
    @ApiModelProperty(value = "创建时间字符串")
    private String createTimeStr;

    @TableField(exist = false)
    @ApiModelProperty(value = "更新时间字符串")
    private String updateTimeStr;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建时间开始时间")
    private Date createTimeStart;
    @TableField(exist = false)
    @ApiModelProperty(value = "创建时间结束时间")
    private Date createTimeEnd;

    @TableField(exist = false)
    @ApiModelProperty(value = "修改时间开始时间")
    private Date updateTimeStart;
    @TableField(exist = false)
    @ApiModelProperty(value = "修改时间结束时间")
    private Date updateTimeEnd;
    @TableField(exist = false)
    @ApiModelProperty(value = "修改时间结束时间")
    private String jobNum;
    /**
     * 菜单列表
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "菜单列表")
    private List menuList;


    /**
     * 组织id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    /**
     * 组织id列表
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "组织id列表")
    private List<Long> orgIdList;
    /**
     * 组织名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "组织名称")
    private String title;
    /**
     * 每页显示数据条数，默认每页显示10条数据
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "第几页,默认第一页")
    private Integer pageSize = 10;
    /**
     * 第几页,默认第一页
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "第几页,默认第一页")
    private Integer pageNum = 1;

    /**
     * 1:查询角色已分配的用户，2:查询角色未分配的用户
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:查询角色已分配的用户，2:查询角色未分配的用户")
    private Integer assignedUser;
    /**
     * 角色id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    /**
     * 角色名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色id集合")
    private List<Long> roleIdList;
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
     * 1:新增,2：编辑
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "1:新增,2：编辑")
    private int handleFlag;
    /**
     *用户id集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户id集合")
    private List<Long> idList;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getLockTimeStart() {
        return lockTimeStart;
    }

    public void setLockTimeStart(Date lockTimeStart) {
        this.lockTimeStart = lockTimeStart;
    }

    public Date getLockTimeEnd() {
        return lockTimeEnd;
    }

    public void setLockTimeEnd(Date lockTimeEnd) {
        this.lockTimeEnd = lockTimeEnd;
    }

    public Long getFailCount() {
        return failCount;
    }

    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }

    public String gethPassword1() {
        return hPassword1;
    }

    public void sethPassword1(String hPassword1) {
        this.hPassword1 = hPassword1;
    }

    public String gethPassword2() {
        return hPassword2;
    }

    public void sethPassword2(String hPassword2) {
        this.hPassword2 = hPassword2;
    }

    public String gethPassword3() {
        return hPassword3;
    }

    public void sethPassword3(String hPassword3) {
        this.hPassword3 = hPassword3;
    }

    public String gethPassword4() {
        return hPassword4;
    }

    public void sethPassword4(String hPassword4) {
        this.hPassword4 = hPassword4;
    }

    public String gethPassword5() {
        return hPassword5;
    }

    public void sethPassword5(String hPassword5) {
        this.hPassword5 = hPassword5;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeStart(Date updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public Date getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(Date updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public List getMenuList() {
        return menuList;
    }

    public void setMenuList(List menuList) {
        this.menuList = menuList;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<Long> getOrgIdList() {
        return orgIdList;
    }

    public void setOrgIdList(List<Long> orgIdList) {
        this.orgIdList = orgIdList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(Integer assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String gethPassword6() {
        return hPassword6;
    }

    public void sethPassword6(String hPassword6) {
        this.hPassword6 = hPassword6;
    }

    public Date getLastPasswordUpdateDate() {
        return lastPasswordUpdateDate;
    }

    public void setLastPasswordUpdateDate(Date lastPasswordUpdateDate) {
        this.lastPasswordUpdateDate = lastPasswordUpdateDate;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getHandleFlag() {
        return handleFlag;
    }

    public void setHandleFlag(int handleFlag) {
        this.handleFlag = handleFlag;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}

