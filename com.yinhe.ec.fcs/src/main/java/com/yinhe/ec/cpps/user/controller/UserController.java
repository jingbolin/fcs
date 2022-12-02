// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserController.java

package com.yinhe.ec.cpps.user.controller;

import com.yinhe.ec.cpps.dto.UserInfoViewDTO;
import com.yinhe.ec.cpps.model.UserInfo;
import com.yinhe.ec.cpps.model.UserInfoView;
import com.yinhe.ec.cpps.user.service.UserService;

import java.util.List;
import java.util.Map;

public class UserController
{

    public UserController()
    {
    }

    public UserInfo getUserInfoByUserId(String userId)
    {
        return userService.getUserInfoByUserId(userId);
    }

    public Map getUser(int page, int rows, String condition, int operatorId, int custId)
    {
        condition = (new StringBuilder(" 1=1 ")).append(condition).append(" and custId=").append(custId).toString();
        return userService.getUser(page, rows, condition, operatorId);
    }

    public String addUser(UserInfoView userInfo)
    {
        return userService.addUser(userInfo);
    }

    public String editUser(UserInfoView userInfo)
    {
        return userService.editUser(userInfo);
    }

    public String delUserById(String userId)
    {
        return userService.delUserById(userId);
    }

    public Map getPayFeeDetailByUserId(String userId)
    {
        return userService.getPayFeeDetailByUserId(userId);
    }

    public String userStartFee(String userId, String meterNo)
    {
        return userService.userStartFee(userId, meterNo);
    }

    public String userSuspendFee(String userId, String meterNo)
    {
        return userService.userSuspendFee(userId, meterNo);
    }

    public Map getUserForWy(int page, int rows, String condition, int operatorId, int custId)
    {
        condition = (new StringBuilder(" 1=1 ")).append(condition).append(" and custId=").append(custId).toString();
        return userService.getUserForWy(page, rows, condition, operatorId);
    }

    public String addUserForWy(UserInfoViewDTO userInfo)
    {
        return userService.addUserForWy(userInfo);
    }

    public String editUserForWy(UserInfoViewDTO userInfo)
    {
        return userService.editUserForWy(userInfo);
    }

    public String delUserByIdForWy(String userId)
    {
        return userService.delUserByIdForWy(userId);
    }

    public List getUserDetailByUserId(String userId)
    {
        return userService.getUserDetailByUserId(userId);
    }

    public List getUserMeterNoByUserId(String userId)
    {
        return userService.getUserMeterNoByUserId(userId);
    }

    public Map getWyfAndQnfByUserId(String userId)
    {
        return userService.getWyfAndQnfByUserId(userId);
    }

    public Map getPayFeeHisByUserIdAndType(String userId, int type)
    {
        return userService.getPayFeeHisByUserIdAndType(userId, type);
    }

    public String addLsUserForWy(UserInfo userInfo)
    {
        return userService.addLsUserForWy(userInfo);
    }

    public String editLsUserForWy(UserInfo userInfo)
    {
        return userService.editLsUserForWy(userInfo);
    }

    public UserInfo getUserInfoByOrders(String orders)
    {
        return userService.getUserInfoByOrders(orders);
    }

    public Map getUserForSchedule(int page, int rows, String condition, int typeId, int custId)
    {
        condition = (new StringBuilder(" 1=1 ")).append(condition).append(" and userinfo.custId=").append(custId).toString();
        return userService.getUserForSchedule(page, rows, condition, typeId);
    }

    public List getUserHffInfoByUserId(String userId, String meterNo)
    {
        return userService.getUserHffInfoByUserId(userId, meterNo);
    }

    public String errMarkMeter(String meterNo, int errMark)
    {
        return userService.errMarkMeter(meterNo, errMark);
    }

    public String editUserKuozhanForWy(UserInfo userInfo)
    {
        return userService.editUserKuozhanForWy(userInfo);
    }

    public String addSigleMeterForWy(UserInfoViewDTO userInfo)
    {
        return userService.addSigleMeterForWy(userInfo);
    }

    public String delMeterInfoByMeterNo(String meterNo)
    {
        return userService.delMeterInfoByMeterNo(meterNo);
    }

    private UserService userService;
}
