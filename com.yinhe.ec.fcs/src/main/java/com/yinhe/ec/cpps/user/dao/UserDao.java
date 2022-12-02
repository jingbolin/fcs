// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserDao.java

package com.yinhe.ec.cpps.user.dao;

import com.yinhe.ec.cpps.dto.UserInfoViewDTO;
import com.yinhe.ec.cpps.model.UserInfo;
import com.yinhe.ec.cpps.model.UserInfoView;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao
{

    public abstract void excelIntoDB(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9, String s10, String s11, String s12, String s13, 
            String s14, String s15, String s16, String s17, String s18, String s19, String s20, 
            int i, int j)
        throws DataAccessException;

    public abstract UserInfo getUserInfoByUserId(String s);

    public abstract String getUserId(int i);

    public abstract Integer addUser(UserInfoView userinfoview)
        throws DataAccessException;

    public abstract Integer editUser(UserInfoView userinfoview)
        throws DataAccessException;

    public abstract int getTotal(String s);

    public abstract List getUser(int i, int j, String s, int k);

    public abstract Integer delUserById(String s)
        throws DataAccessException;

    public abstract List getPayFeeDetailByUserId(String s);

    public abstract int getPayFeeDetailByUserIdTotal(String s);

    public abstract List getgetPayFeeDetailByUserIdForFooter(String s);

    public abstract Integer userStartFee(String s, String s1)
        throws DataAccessException;

    public abstract Integer userSuspendFee(String s, String s1)
        throws DataAccessException;

    public abstract int getTotalForWy(String s);

    public abstract List getUserForWy(int i, int j, String s, int k);

    public abstract Integer addUserForWy(UserInfoViewDTO userinfoviewdto)
        throws DataAccessException;

    public abstract Integer editUserForWy(UserInfoViewDTO userinfoviewdto)
        throws DataAccessException;

    public abstract Integer delUserByIdForWy(String s)
        throws DataAccessException;

    public abstract List getUserDetailByUserId(String s);

    public abstract List getUserMeterNoByUserId(String s);

    public abstract void excelIntoDBForWy(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9, String s10, String s11, int i, int j, 
            String s12, String s13, String s14, String s15, int k, String s16, String s17, 
            int l, int i1, int j1, int k1, int l1, int i2, int j2, 
            String s18, String s19, String s20, int k2, String s21, String s22, int l2, 
            int i3, int j3, int k3, int l3, int i4, int j4, String s23, 
            String s24, String s25, String s26, String s27, String s28, String s29, String s30, 
            int k4, String s31, String s32, int l4, int i5, int j5, int k5, 
            int l5, int i6, int j6, String s33, String s34, String s35)
        throws DataAccessException;

    public abstract List getWyfListByUserId(String s);

    public abstract List getQnfListByUserId(String s);

    public abstract List getTcfListByUserId(String s);

    public abstract List getPayFeeHisByUserIdAndType(String s, int i);

    public abstract int getPayFeeHisByUserIdAndTypeTotal(String s, int i);

    public abstract List getgetPayFeeHisByUserIdAndTypeForFooter(String s, int i);

    public abstract Integer addLsUserForWy(UserInfo userinfo);

    public abstract Integer editLsUserForWy(UserInfo userinfo);

    public abstract UserInfo getUserInfoByOrders(String s);

    public abstract int getTotalForSchedule(String s, int i);

    public abstract List getUserForSchedule(int i, int j, String s, int k);

    public abstract List getUserHffInfoByUserId(String s, String s1);

    public abstract void errMarkMeter(String s, int i);

    public abstract void editUserKuozhanForWy(UserInfo userinfo);

    public abstract Integer addSigleMeterForWy(UserInfoViewDTO userinfoviewdto)
        throws DataAccessException;

    public abstract Integer delMeterInfoByMeterNo(String s)
        throws DataAccessException;

    public abstract void upLoadMeterToDosagedetail(String s, int i, int j);
}
