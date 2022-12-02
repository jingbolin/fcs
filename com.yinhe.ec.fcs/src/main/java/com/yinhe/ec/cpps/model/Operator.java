// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Operator.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            Customer, DepartInfo, AreaInfo

public class Operator
{

    public Operator()
    {
    }

    public int getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(int operatorId)
    {
        this.operatorId = operatorId;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getDepartId()
    {
        return departId;
    }

    public void setDepartId(String departId)
    {
        this.departId = departId;
    }

    public String getOperatorAccount()
    {
        return operatorAccount;
    }

    public void setOperatorAccount(String operatorAccount)
    {
        this.operatorAccount = operatorAccount;
    }

    public String getOperatorPwd()
    {
        return operatorPwd;
    }

    public void setOperatorPwd(String operatorPwd)
    {
        this.operatorPwd = operatorPwd;
    }

    public int getIsLogin()
    {
        return isLogin;
    }

    public void setIsLogin(int isLogin)
    {
        this.isLogin = isLogin;
    }

    public String getWorkCard()
    {
        return workCard;
    }

    public void setWorkCard(String workCard)
    {
        this.workCard = workCard;
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    public String getCardId()
    {
        return cardId;
    }

    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getLoginTimes()
    {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes)
    {
        this.loginTimes = loginTimes;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public int getIsUsed()
    {
        return isUsed;
    }

    public void setIsUsed(int isUsed)
    {
        this.isUsed = isUsed;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public int getOperatorPid()
    {
        return operatorPid;
    }

    public void setOperatorPid(int operatorPid)
    {
        this.operatorPid = operatorPid;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Customer getCust()
    {
        return cust;
    }

    public void setCust(Customer cust)
    {
        this.cust = cust;
    }

    public DepartInfo getDept()
    {
        return dept;
    }

    public void setDept(DepartInfo dept)
    {
        this.dept = dept;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public AreaInfo getArea()
    {
        return area;
    }

    public void setArea(AreaInfo area)
    {
        this.area = area;
    }

    private int operatorId;
    private int custId;
    private String departId;
    private String operatorAccount;
    private String operatorPwd;
    private int isLogin;
    private String workCard;
    private String operatorName;
    private String cardId;
    private String email;
    private int loginTimes;
    private String phone;
    private int isUsed;
    private String createDate;
    private int operatorPid;
    private String remark;
    private Customer cust;
    private DepartInfo dept;
    private AreaInfo area;
    private String roleId;
    private String roleName;
    private String areaId;
}
