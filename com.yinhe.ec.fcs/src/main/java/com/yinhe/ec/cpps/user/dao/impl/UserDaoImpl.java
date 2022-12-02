//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.dao.impl;

import com.yinhe.ec.cpps.dto.UserInfoDTO;
import com.yinhe.ec.cpps.dto.UserInfoViewDTO;
import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.user.dao.UserDao;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {
    }

    public String getUserId(final int operatorId) {
        String no = (String)this.jdbcTemplate.execute("{call getUserId(?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, operatorId);
                cs.registerOutParameter(2, 12);
                cs.execute();
                return cs.getString(2);
            }
        });
        return no;
    }

    public Integer addUser(final UserInfoView userInfo) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call AddUserInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, userInfo.getCustId());
                cs.setString(2, userInfo.getUserName());
                cs.setString(3, userInfo.getUserAddr());
                cs.setString(4, userInfo.getMobilePhone());
                cs.setString(5, userInfo.getAreaId());
                cs.setInt(6, userInfo.getMemberCount());
                cs.setDouble(7, userInfo.getAreas());
                cs.setString(8, userInfo.getEmail());
                cs.setInt(9, userInfo.getSmsKind());
                cs.setInt(10, userInfo.getTypeId());
                cs.setString(11, userInfo.getMeterNo());
                cs.setInt(12, userInfo.getMeterPtl());
                cs.setString(13, userInfo.getMusterNo());
                cs.setString(14, userInfo.getSubNo());
                cs.setInt(15, userInfo.getMboxId());
                cs.setString(16, userInfo.getSealNo());
                cs.setInt(17, userInfo.getManuId());
                cs.setInt(18, userInfo.getComPort());
                cs.setInt(19, userInfo.getBaud());
                cs.setInt(20, userInfo.getDynameter());
                cs.setDouble(21, userInfo.getSpan());
                cs.setInt(22, userInfo.getAlarm1());
                cs.setInt(23, userInfo.getAlarm2());
                cs.setString(24, userInfo.getMeterPic());
                cs.setString(25, userInfo.getYearDosageDt());
                cs.setInt(26, userInfo.getPattern());
                cs.setDouble(27, userInfo.getInitDosage());
                cs.setString(28, userInfo.getFeeItemId());
                cs.setInt(29, userInfo.getKeyFlag());
                cs.setInt(30, userInfo.getOperatorId());
                cs.setString(31, userInfo.getIdCard());
                cs.setInt(32, userInfo.getOnOff());
                cs.registerOutParameter(33, 4);
                cs.execute();
                return cs.getInt(33);
            }
        });
        return no;
    }

    public Integer delUserById(final String userId) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call delUserInfo(?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, userId);
                cs.registerOutParameter(2, 4);
                cs.execute();
                return cs.getInt(2);
            }
        });
        return no;
    }

    public Integer editUser(final UserInfoView userInfo) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call editUserInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, userInfo.getUserId());
                cs.setString(2, userInfo.getMeterNo());
                cs.setInt(3, userInfo.getTypeId());
                cs.setString(4, userInfo.getUserName());
                cs.setString(5, userInfo.getUserAddr());
                cs.setInt(6, userInfo.getMeterPtl());
                cs.setString(7, userInfo.getAreaId());
                cs.setInt(8, userInfo.getMemberCount());
                cs.setDouble(9, userInfo.getAreas());
                cs.setString(10, userInfo.getEmail());
                cs.setString(11, userInfo.getMobilePhone());
                cs.setString(12, userInfo.getMusterNo());
                cs.setString(13, userInfo.getSubNo());
                cs.setInt(14, userInfo.getMboxId());
                cs.setString(15, userInfo.getSealNo());
                cs.setInt(16, userInfo.getComPort());
                cs.setInt(17, userInfo.getBaud());
                cs.setInt(18, userInfo.getDynameter());
                cs.setInt(19, userInfo.getAlarm1());
                cs.setInt(20, userInfo.getAlarm2());
                cs.setInt(21, userInfo.getManuId());
                cs.setInt(22, userInfo.getOperatorId());
                cs.setString(23, userInfo.getMeterPic());
                cs.setString(24, userInfo.getYearDosageDt());
                cs.setString(25, userInfo.getFeeItemId());
                cs.setInt(26, userInfo.getPattern());
                cs.setDouble(27, userInfo.getInitDosage());
                cs.setInt(28, userInfo.getSmsKind());
                cs.setInt(29, userInfo.getKeyFlag());
                cs.setString(30, userInfo.getIdCard());
                cs.setInt(31, userInfo.getOnOff());
                cs.registerOutParameter(32, 4);
                cs.execute();
                return cs.getInt(32);
            }
        });
        return no;
    }

    public void excelIntoDB(String typeName, String meterNo, String meterPtl, String userName, String userAddr, String mobilePhone, String areaName, String feeItemName, String musterNo, String manuName, String mboxNo, String sealNo, String subName, String comPort, String baud, String dynameter, String alarm1, String alarm2, String initDosage, String keyflag, String idcard, int custId, int operatorId) throws DataAccessException {
        this.jdbcTemplate.execute("{call addExcelUserInfo('" + typeName + "','" + meterNo + "','" + meterPtl + "','" + userName + "','" + userAddr + "','" + mobilePhone + "','" + areaName + "','" + feeItemName + "','" + musterNo + "','" + manuName + "','" + mboxNo + "','" + sealNo + "','" + subName + "'," + comPort + "," + baud + "," + dynameter + "," + alarm1 + "," + alarm2 + ",'" + initDosage + "'," + keyflag + ",'" + idcard + "'," + custId + "," + operatorId + ")}");
    }

    public int getTotal(String condition) {
        return this.jdbcTemplate.queryForObject("select count(*) from UserInfoView where " + condition,Integer.class);
    }

    public List<UserInfoView> getUser(int page, int rows, String condition, int operatorId) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT * FROM USERINFOVIEW WHERE " + condition + " ORDER BY UserID asc", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" USERINFOVIEW.* FROM USERINFOVIEW WHERE " + condition + " ", page, rows, "UserID", "desc");
        }

        List<UserInfoView> list = this.jdbcTemplate.query(sql, new UserDTOMapper());
        return list;
    }

    public UserInfo getUserInfoByUserId(String userId) {
        return (UserInfo)this.jdbcTemplate.queryForObject("SELECT * FROM UserInfo WHERE USERID = ?", new Object[]{userId}, new UserInfoDTOMapper());
    }

    public List<PayFee> getPayFeeDetailByUserId(String userId) {
        String sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEE.PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,PAYFEE.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,PAYFEEDETAIL.METERNO,PAYFEEDETAIL.QUANTITY,FEEITEMLIST.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,USERDETAIL,FEEITEMLIST  WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERINFO.USERID=PAYFEE.USERID AND USERDETAIL.USERID=PAYFEE.USERID  AND USERDETAIL.METERNO=PAYFEEDETAIL.METERNO AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID AND PAYFEE.USERID='" + userId + "' ORDER BY PAYDATE DESC";
        List<PayFee> list = this.jdbcTemplate.query(sql, new PayFeeDetailDTOMapper());
        return list;
    }

    public int getPayFeeDetailByUserIdTotal(String userId) {
        return this.jdbcTemplate.queryForObject("select count(*) from PAYFEE where userId='" + userId + "'",Integer.class);
    }

    public List<PayFee> getgetPayFeeDetailByUserIdForFooter(String userId) {
        String sql = "SELECT SUM(PAYFEE)PAYFEE FROM PAYFEE WHERE USERID='" + userId + "' ";
        List<PayFee> list = this.jdbcTemplate.query(sql, new PayFeeDetailForFooterDTOMapper());
        return list;
    }

    public Integer userStartFee(final String userId, final String meterNo) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call UserStartFee(?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, userId);
                cs.setString(2, meterNo);
                cs.registerOutParameter(3, 4);
                cs.execute();
                return cs.getInt(3);
            }
        });
        return no;
    }

    public Integer userSuspendFee(final String userId, final String meterNo) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call UserSuspendFee(?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, userId);
                cs.setString(2, meterNo);
                cs.registerOutParameter(3, 4);
                cs.execute();
                return cs.getInt(3);
            }
        });
        return no;
    }

    public int getTotalForWy(String condition) {
        return this.jdbcTemplate.queryForObject("select count(*) from UserInfo where " + condition,Integer.class);
    }

    public List<String> getMetersByUserId(String userId) {
        String sql = "select meterNo from userDetail where userId='" + userId + "'";
        return this.jdbcTemplate.query(sql, new MetersByUserIdMapper());
    }

    public List<UserInfoDTO> getUserForWy(int page, int rows, String condition, int operatorId) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT USERINFO.*,(SELECT AREANAME FROM AREAINFO WHERE AREAID=USERINFO.AREAID)AREANAME,(SELECT WYFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)WYFID,(SELECT NQFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)NQFID,(SELECT FZFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)FZFID,(SELECT PARKID FROM USERPARKINFO WHERE  USERID=USERINFO.USERID AND ROWNUM=1)PARKID,(SELECT FEEITEMID FROM USERPARKINFO WHERE  USERID=USERINFO.USERID AND ROWNUM=1)TCFID,(SELECT CARNO FROM USERPARKINFO WHERE  USERID=USERINFO.USERID  AND ROWNUM=1)CARNO  FROM USERINFO WHERE " + condition + " ORDER BY UserID asc", page, rows);
        } else if ("PG".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPageForPG("SELECT USERINFO.*,(SELECT AREANAME FROM AREAINFO WHERE AREAID=USERINFO.AREAID)AREANAME,(SELECT WYFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)WYFID,(SELECT NQFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)NQFID,(SELECT FZFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)FZFID,(SELECT PARKID FROM USERPARKINFO WHERE  USERID=USERINFO.USERID limit 1)PARKID,(SELECT FEEITEMID FROM USERPARKINFO WHERE  USERID=USERINFO.USERID limit 1)TCFID,(SELECT CARNO FROM USERPARKINFO WHERE  USERID=USERINFO.USERID limit 1)CARNO  FROM USERINFO WHERE " + condition + " ORDER BY UserID asc", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" USERINFO.*,(SELECT AREANAME FROM AREAINFO WHERE AREAID=USERINFO.AREAID)AREANAME,(SELECT WYFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)WYFID,(SELECT NQFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)NQFID,(SELECT FZFEEITEMID FROM USERFUNDDETAIL WHERE USERID=USERINFO.USERID)FZFID,(SELECT TOP 1 PARKID FROM USERPARKINFO WHERE  USERID=USERINFO.USERID)PARKID,(SELECT TOP 1 FEEITEMID FROM USERPARKINFO WHERE  USERID=USERINFO.USERID)TCFID,(SELECT TOP 1 CARNO FROM USERPARKINFO WHERE  USERID=USERINFO.USERID)CARNO  FROM USERINFO WHERE " + condition + " ", page, rows, "UserID", "desc");
        }

        List<UserInfoDTO> list = this.jdbcTemplate.query(sql, new UserForWyDTOMapper());
        return list;
    }

    public Integer addUserForWy(final UserInfoViewDTO userInfo) throws DataAccessException {
        if (userInfo.getShareFlag() == 0) {
            userInfo.setShareRatio(100.0);
        }

        if (userInfo.getShareFlag2() == 0) {
            userInfo.setShareRatio2(100.0);
        }

        if (userInfo.getShareFlag3() == 0) {
            userInfo.setShareRatio3(100.0);
        }

        Integer no = (Integer)this.jdbcTemplate.execute("{call AddUserInfoForWy(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?, ?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, userInfo.getCustId());
                cs.setString(2, userInfo.getUserName());
                cs.setString(3, userInfo.getUserAddr());
                cs.setString(4, userInfo.getMobilePhone());
                cs.setString(5, userInfo.getAreaId());
                cs.setInt(6, userInfo.getMemberCount());
                cs.setDouble(7, userInfo.getAreas());
                cs.setString(8, userInfo.getEmail());
                cs.setInt(9, userInfo.getSmsKind());
                cs.setInt(10, userInfo.getTypeId());
                cs.setString(11, userInfo.getMeterNo().trim());
                cs.setInt(12, userInfo.getMeterPtl());
                cs.setString(13, userInfo.getMusterNo());
                cs.setString(14, userInfo.getSubNo());
                cs.setInt(15, userInfo.getMboxId());
                cs.setString(16, userInfo.getSealNo());
                cs.setInt(17, userInfo.getManuId());
                cs.setInt(18, userInfo.getComPort());
                cs.setInt(19, userInfo.getBaud());
                cs.setInt(20, userInfo.getDynameter());
                cs.setDouble(21, userInfo.getSpan());
                cs.setInt(22, userInfo.getAlarm1());
                cs.setInt(23, userInfo.getAlarm2());
                cs.setString(24, userInfo.getYearDosageDt());
                cs.setInt(25, userInfo.getPattern());
                cs.setDouble(26, userInfo.getInitDosage());
                cs.setString(27, userInfo.getFeeItemId());
                cs.setInt(28, userInfo.getKeyFlag());
                cs.setInt(29, userInfo.getOperatorId());
                cs.setString(30, userInfo.getIdCard());
                cs.setInt(31, userInfo.getFloors());
                cs.setInt(32, userInfo.getTypeId2());
                cs.setString(33, userInfo.getMeterNo2().trim());
                cs.setInt(34, userInfo.getMeterPtl2());
                cs.setString(35, userInfo.getMusterNo2());
                cs.setString(36, userInfo.getSealNo2());
                cs.setInt(37, userInfo.getManuId2());
                cs.setInt(38, userInfo.getComPort2());
                cs.setInt(39, userInfo.getBaud2());
                cs.setInt(40, userInfo.getDynameter2());
                cs.setDouble(41, userInfo.getSpan2());
                cs.setInt(42, userInfo.getAlarm12());
                cs.setInt(43, userInfo.getAlarm22());
                cs.setString(44, userInfo.getYearDosageDt2());
                cs.setInt(45, userInfo.getPattern2());
                cs.setDouble(46, userInfo.getInitDosage2());
                cs.setString(47, userInfo.getFeeItemId2());
                cs.setInt(48, userInfo.getKeyFlag2());
                cs.setString(49, userInfo.getWyfId());
                cs.setString(50, userInfo.getNqfId());
                cs.setString(51, userInfo.getParkId());
                cs.setString(52, userInfo.getCarNo());
                cs.setString(53, userInfo.getTcfId());
                cs.setString(54, userInfo.getFzfId());
                cs.setInt(55, userInfo.getOnOff());
                cs.setInt(56, userInfo.getOnOff2());
                cs.setInt(57, userInfo.getDn());
                cs.setString(58, userInfo.getXsfeeItemId());
                cs.setInt(59, userInfo.getShareFlag());
                cs.setDouble(60, userInfo.getShareRatio());
                cs.setInt(61, userInfo.getShareFlag2());
                cs.setDouble(62, userInfo.getShareRatio2());
                cs.setInt(63, userInfo.getTypeId3());
                cs.setString(64, userInfo.getMeterNo3().trim());
                cs.setInt(65, userInfo.getMeterPtl3());
                cs.setString(66, userInfo.getMusterNo3());
                cs.setString(67, userInfo.getSealNo3());
                cs.setInt(68, userInfo.getManuId3());
                cs.setInt(69, userInfo.getComPort3());
                cs.setInt(70, userInfo.getBaud3());
                cs.setInt(71, userInfo.getDynameter3());
                cs.setDouble(72, userInfo.getSpan3());
                cs.setInt(73, userInfo.getAlarm13());
                cs.setInt(74, userInfo.getAlarm23());
                cs.setString(75, userInfo.getYearDosageDt3());
                cs.setInt(76, userInfo.getPattern3());
                cs.setDouble(77, userInfo.getInitDosage3());
                cs.setString(78, userInfo.getFeeItemId3());
                cs.setInt(79, userInfo.getKeyFlag3());
                cs.setInt(80, userInfo.getDn3());
                cs.setInt(81, userInfo.getOnOff3());
                cs.setInt(82, userInfo.getShareFlag3());
                cs.setDouble(83, userInfo.getShareRatio3());
                cs.registerOutParameter(84, 4);
                cs.execute();
                return cs.getInt(84);
            }
        });
        return no;
    }

    public Integer editUserForWy(final UserInfoViewDTO userInfo) throws DataAccessException {
        if (userInfo.getShareFlag() == 0) {
            userInfo.setShareRatio(100.0);
        }

        if (userInfo.getShareFlag2() == 0) {
            userInfo.setShareRatio2(100.0);
        }

        if (userInfo.getShareFlag3() == 0) {
            userInfo.setShareRatio3(100.0);
        }

        Integer no = (Integer)this.jdbcTemplate.execute("{call EditUserInfoForWy(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?, ?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,  ?, ?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, userInfo.getCustId());
                cs.setString(2, userInfo.getUserName());
                cs.setString(3, userInfo.getUserAddr());
                cs.setString(4, userInfo.getMobilePhone());
                cs.setString(5, userInfo.getAreaId());
                cs.setInt(6, userInfo.getMemberCount());
                cs.setDouble(7, userInfo.getAreas());
                cs.setString(8, userInfo.getEmail());
                cs.setInt(9, userInfo.getSmsKind());
                cs.setInt(10, userInfo.getTypeId());
                cs.setString(11, userInfo.getMeterNo());
                cs.setInt(12, userInfo.getMeterPtl());
                cs.setString(13, userInfo.getMusterNo());
                cs.setString(14, userInfo.getSubNo());
                cs.setInt(15, userInfo.getMboxId());
                cs.setString(16, userInfo.getSealNo());
                cs.setInt(17, userInfo.getManuId());
                cs.setInt(18, userInfo.getComPort());
                cs.setInt(19, userInfo.getBaud());
                cs.setInt(20, userInfo.getDynameter());
                cs.setDouble(21, userInfo.getSpan());
                cs.setInt(22, userInfo.getAlarm1());
                cs.setInt(23, userInfo.getAlarm2());
                cs.setString(24, userInfo.getYearDosageDt());
                cs.setInt(25, userInfo.getPattern());
                cs.setDouble(26, userInfo.getInitDosage());
                cs.setString(27, userInfo.getFeeItemId());
                cs.setInt(28, userInfo.getKeyFlag());
                cs.setInt(29, userInfo.getOperatorId());
                cs.setString(30, userInfo.getIdCard());
                cs.setInt(31, userInfo.getFloors());
                cs.setInt(32, userInfo.getTypeId2());
                cs.setString(33, userInfo.getMeterNo2());
                cs.setInt(34, userInfo.getMeterPtl2());
                cs.setString(35, userInfo.getMusterNo2());
                cs.setString(36, userInfo.getSealNo2());
                cs.setInt(37, userInfo.getManuId2());
                cs.setInt(38, userInfo.getComPort2());
                cs.setInt(39, userInfo.getBaud2());
                cs.setInt(40, userInfo.getDynameter2());
                cs.setDouble(41, userInfo.getSpan2());
                cs.setInt(42, userInfo.getAlarm12());
                cs.setInt(43, userInfo.getAlarm22());
                cs.setString(44, userInfo.getYearDosageDt2());
                cs.setInt(45, userInfo.getPattern2());
                cs.setDouble(46, userInfo.getInitDosage2());
                cs.setString(47, userInfo.getFeeItemId2());
                cs.setInt(48, userInfo.getKeyFlag2());
                cs.setString(49, userInfo.getWyfId());
                cs.setString(50, userInfo.getNqfId());
                cs.setString(51, userInfo.getParkId());
                cs.setString(52, userInfo.getCarNo());
                cs.setString(53, userInfo.getTcfId());
                cs.setString(54, userInfo.getFzfId());
                cs.setInt(55, userInfo.getOnOff());
                cs.setInt(56, userInfo.getOnOff2());
                cs.setInt(57, userInfo.getDn());
                cs.setString(58, userInfo.getXsfeeItemId());
                cs.setInt(59, userInfo.getShareFlag());
                cs.setDouble(60, userInfo.getShareRatio());
                cs.setInt(61, userInfo.getShareFlag2());
                cs.setDouble(62, userInfo.getShareRatio2());
                cs.setInt(63, userInfo.getTypeId3());
                cs.setString(64, userInfo.getMeterNo3().trim());
                cs.setInt(65, userInfo.getMeterPtl3());
                cs.setString(66, userInfo.getMusterNo3());
                cs.setString(67, userInfo.getSealNo3());
                cs.setInt(68, userInfo.getManuId3());
                cs.setInt(69, userInfo.getComPort3());
                cs.setInt(70, userInfo.getBaud3());
                cs.setInt(71, userInfo.getDynameter3());
                cs.setDouble(72, userInfo.getSpan3());
                cs.setInt(73, userInfo.getAlarm13());
                cs.setInt(74, userInfo.getAlarm23());
                cs.setString(75, userInfo.getYearDosageDt3());
                cs.setInt(76, userInfo.getPattern3());
                cs.setDouble(77, userInfo.getInitDosage3());
                cs.setString(78, userInfo.getFeeItemId3());
                cs.setInt(79, userInfo.getKeyFlag3());
                cs.setInt(80, userInfo.getDn3());
                cs.setInt(81, userInfo.getOnOff3());
                cs.setInt(82, userInfo.getShareFlag3());
                cs.setDouble(83, userInfo.getShareRatio3());
                cs.setString(84, userInfo.getUserId());
                cs.registerOutParameter(85, 4);
                cs.execute();
                return cs.getInt(85);
            }
        });
        return no;
    }

    public Integer delUserByIdForWy(final String userId) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call DelUserInfoForWy(?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, userId);
                cs.registerOutParameter(2, 4);
                cs.execute();
                return cs.getInt(2);
            }
        });
        return no;
    }

    public List<UserInfoView> getUserDetailByUserId(String userId) {
        String sql = "select musterno,meterno,typeid,typeName,manuid,mboxid,subno, meterptl,comport,baud,keyflag,alarm1,alarm2,dynameter,dn,span,pattern,initdosage,feeitemid,feeItemName,price,thesdfprice,thewsfprice,xsfeeitemid,yeardosagedt,shareflag,shareratio, case when pattern=2 then (select sum(isnull(theFee,0))+sum(isnull(getlatefee(userid,feeitemid,feemonth,thefee,0),0)) from feeDetail where userId=userinfoview.userId and meterNo=userinfoview.meterNo and payflag=0) else leftSum end leftSum, case when pattern=2 then (select sum(isnull(getlatefee(userid,feeitemid,feemonth,thefee,0),0)) from feeDetail where userId=userinfoview.userId and meterNo=userinfoview.meterNo and payflag=0) else 0.0 end fineTotal, onOff,billKind,billTypeUrl,readDate,(select sum(yquantity) from ThisYearTotalView where meterno=userinfoview.meterno)yquantity  from userinfoview where userid='" + userId + "' " + " union " + " select musterno,meterno,typeid,typeName,manuid,mboxid,subno, " + " meterptl,comport,baud,keyflag,alarm1,alarm2,dynameter,dn,span,2 as pattern,initdosage,xsfeeitemid as feeitemid," + "(select feeitemName from feeitemList where feeitemId=xsfeeitemid and rownum=1) as feeItemName," + "(select to_char(price,'0.0000') from feeitemList where feeitemId=xsfeeitemid and rownum=1) as price,thesdfprice,thewsfprice,xsfeeitemid,yeardosagedt,shareflag,shareratio, " + " (select sum(isnull(theFee,0))+sum(isnull(getlatefee(userid,feeitemid,feemonth,thefee,0),0)) from feeDetail where userId=userinfoview.userId and meterNo=userinfoview.meterNo and payflag=0) as leftSum, " + " (select sum(isnull(getlatefee(userid,feeitemid,feemonth,thefee,0),0)) from feeDetail where userId=userinfoview.userId and meterNo=userinfoview.meterNo and payflag=0) as fineTotal, " + " onOff,billKind,billTypeUrl,readDate,(select sum(yquantity) from ThisYearTotalView where meterno=userinfoview.meterno)yquantity  " + " from userinfoview where userid='" + userId + "' and xsfeeitemid is not null ";
        return this.jdbcTemplate.query(sql, new UserDetailByUserIdDTO());
    }

    public List<UserInfoView> getUserMeterNoByUserId(String userId) {
        String sql = "select musterno,meterno,typeid,typeName,manuid,mboxid,subno, meterptl,comport,baud,keyflag,alarm1,alarm2,dynameter,dn,span,pattern,initdosage,feeitemid,feeItemName,price,thesdfprice,thewsfprice,xsfeeitemid,yeardosagedt,shareflag,shareratio, case when pattern=2 then (select sum(isnull(theFee,0))+sum(isnull(getlatefee(userid,feeitemid,feemonth,thefee,0),0)) from feeDetail where userId=userinfoview.userId and meterNo=userinfoview.meterNo and payflag=0) else leftSum end leftSum, case when pattern=2 then (select sum(isnull(getlatefee(userid,feeitemid,feemonth,thefee,0),0)) from feeDetail where userId=userinfoview.userId and meterNo=userinfoview.meterNo and payflag=0) else 0.0 end fineTotal, onOff,billKind,billTypeUrl,readDate,(select sum(yquantity) from ThisYearTotalView where meterno=userinfoview.meterno)yquantity  from userinfoview where userid='" + userId + "' ";
        return this.jdbcTemplate.query(sql, new UserDetailByUserIdDTO());
    }

    public void excelIntoDBForWy(String userName, String userAddr, String floors, String areas, String mobilePhone, String idCrad, String meterNo, String dynameter, String initDosage, String meterNo2, String dn, String initDosage2, int custId, int operatorId, String areaId, String wyfId, String nqfId, String feeItemId, int keyFlag, String musterNo, String manuId, int meterPtl, int pattern, int comPort, int baud, int onOff, int alarm1, int alarm2, String subNo, String mboxId, String feeItemId2, int keyFlag2, String musterNo2, String manuId2, int meterPtl2, int pattern2, int comPort2, int baud2, int onOff2, int alarm12, int alarm22, String shareFlag, String shareRatio, String shareFlag2, String shareRatio2, String meterNo3, String dn3, String initDosage3, String feeItemId3, int keyFlag3, String musterNo3, String manuId3, int meterPtl3, int pattern3, int comPort3, int baud3, int onOff3, int alarm13, int alarm23, String shareFlag3, String shareRatio3, String userId) throws DataAccessException {
        if ("".equals(floors)) {
            areas = "0";
        }

        if ("".equals(areas)) {
            areas = "0.0";
        }

        if ("".equals(dynameter)) {
            dynameter = "1";
        }

        if ("".equals(initDosage)) {
            initDosage = "0";
        }

        if ("".equals(dn)) {
            dn = "20";
        }

        if ("".equals(initDosage2)) {
            initDosage2 = "0";
        }

        if ("".equals(dn3)) {
            dn3 = "20";
        }

        if ("".equals(initDosage3)) {
            initDosage3 = "0";
        }

        this.jdbcTemplate.execute("{call addExcelUserInfoForWy('" + userName.trim() + "','" + userAddr.trim() + "'," + floors + "," + areas + ",'" + mobilePhone + "','" + idCrad + "','" + meterNo.trim() + "'," + dynameter + "," + initDosage + ",'" + meterNo2.trim() + "'," + dn + "," + initDosage2 + "," + custId + "," + operatorId + ",'" + areaId + "','" + wyfId + "','" + nqfId + "','" + feeItemId + "'," + keyFlag + ",'" + musterNo + "','" + manuId + "'," + meterPtl + "," + pattern + "," + comPort + "," + baud + "," + onOff + "," + alarm1 + "," + alarm2 + ",'" + subNo + "','" + mboxId + "','" + feeItemId2 + "'," + keyFlag2 + ",'" + musterNo2 + "','" + manuId2 + "'," + meterPtl2 + "," + pattern2 + "," + comPort2 + "," + baud2 + "," + onOff2 + "," + alarm12 + "," + alarm22 + "," + shareFlag + "," + shareRatio + "," + shareFlag2 + "," + shareRatio2 + ",'" + meterNo3.trim() + "'," + dn3 + "," + initDosage3 + ",'" + feeItemId3 + "'," + keyFlag3 + ",'" + musterNo3 + "','" + manuId3 + "'," + meterPtl3 + "," + pattern3 + "," + comPort3 + "," + baud3 + "," + onOff3 + "," + alarm13 + "," + alarm23 + "," + shareFlag3 + "," + shareRatio3 + ",'" + userId.trim() + "')}");
    }

    public List<FeeDetail> getQnfListByUserId(String userId) {
        String sql = "select feedetail.userid,feedetail.feeitemid,feeitemname,feemonth,areaprice,areas,thefee,thedosage,feedetail.remark,getlatefee(feedetail.userid,feedetail.feeitemid,feemonth,thefee,0)FineFee from feedetail,feeitemlist,userinfo  where feedetail.payflag=0 and feetypeId='0005' and feeitemlist.feeitemid=feedetail.feeitemid and userinfo.userid=feedetail.userid and userinfo.userid='" + userId + "'";
        return this.jdbcTemplate.query(sql, new FeeDetailByUserIdDTO());
    }

    public List<UserParkInfo> getTcfListByUserId(String userId) {
        String sql = "SELECT USERPARKINFO.USERID,USERPARKINFO.PARKID,PARKINFO.PARKNO,USERPARKINFO.CARNO,USERPARKINFO.CARSTARTDATE,USERPARKINFO.CARENDDATE,USERPARKINFO.FEEITEMID,FEEITEMLIST.FEEITEMNAME,FEEITEMLIST.BASEFEE AS THEFEE  FROM USERPARKINFO,PARKINFO,FEEITEMLIST WHERE USERPARKINFO.PARKID=PARKINFO.PARKID AND FEEITEMLIST.FEEITEMID=USERPARKINFO.FEEITEMID AND USERPARKINFO.USERID='" + userId + "'";
        return this.jdbcTemplate.query(sql, new UserParkInfoDTO());
    }

    public List<FeeDetail> getWyfListByUserId(String userId) {
        String sql = "select feedetail.userid,feedetail.feeitemid,feeitemname,feemonth,areaprice,areas,thefee,thedosage,feedetail.remark,getlatefee(feedetail.userid,feedetail.feeitemid,feemonth,thefee,0)FineFee from feedetail,feeitemlist,userinfo  where feedetail.payflag=0 and feetypeId='0004' and feeitemlist.feeitemid=feedetail.feeitemid and userinfo.userid=feedetail.userid and userinfo.userid='" + userId + "'";
        return this.jdbcTemplate.query(sql, new FeeDetailByUserIdDTO());
    }

    public List<PayFee> getPayFeeHisByUserIdAndType(String userId, int type) {
        String sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEE.PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,PAYFEE.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,PAYFEEDETAIL.METERNO,PAYFEEDETAIL.QUANTITY,FEEITEMLIST.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,USERDETAIL,FEEITEMLIST  WHERE 1<>1";
        if (type != 1 && type != 2) {
            if (type != 3 && type != 4) {
                if (type == 5) {
                    sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEEDETAIL.TOTAL AS PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,PAYFEEDETAIL.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,'' METERNO,1 AS QUANTITY,FEEITEMLIST.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,FEEITEMLIST,FEETYPE  WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERINFO.USERID=PAYFEE.USERID  AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID   AND FEETYPE.FEETYPEID=FEEITEMLIST.FEETYPEID AND FEETYPE.FEETYPEMARK=" + type + " " + " AND PAYFEE.USERID='" + userId + "' " + " ORDER BY PAYDATE DESC";
                } else if (type == 6) {
                    sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEEDETAIL.TOTAL AS PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,FEEDETAIL.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,'' METERNO,1 AS QUANTITY,PAYFEEDETAIL.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,FEEITEMLIST,FEETYPE,FEEDETAIL  WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERINFO.USERID=PAYFEE.USERID  AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID AND FEEDETAIL.PAYNO=PAYFEE.PAYNO AND FEEDETAIL.FEEMONTH=PAYFEEDETAIL.FEEMONTH  AND FEETYPE.FEETYPEID=FEEITEMLIST.FEETYPEID AND FEETYPE.FEETYPEMARK=" + type + " " + " AND PAYFEE.USERID='" + userId + "' " + " ORDER BY PAYDATE DESC";
                } else if (type == 7) {
                    sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEEDETAIL.TOTAL AS PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,FEEDETAIL.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,'' METERNO,1 AS QUANTITY,PAYFEEDETAIL.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,FEEITEMLIST,FEETYPE,FEEDETAIL  WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERINFO.USERID=PAYFEE.USERID  AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID AND FEEDETAIL.PAYNO=PAYFEE.PAYNO AND FEEDETAIL.FEEMONTH=PAYFEEDETAIL.FEEMONTH  AND FEETYPE.FEETYPEID=FEEITEMLIST.FEETYPEID AND FEETYPE.FEETYPEMARK=" + type + " " + " AND PAYFEE.USERID='" + userId + "' " + " ORDER BY PAYDATE DESC";
                } else if (type == 8) {
                    sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEE.PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,PAYFEE.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,'' METERNO,1 AS QUANTITY,FEEITEMLIST.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,FEEITEMLIST,FEETYPE  WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERINFO.USERID=PAYFEE.USERID  AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID  AND FEETYPE.FEETYPEID=FEEITEMLIST.FEETYPEID AND FEETYPE.FEETYPEMARK=" + type + " " + " AND PAYFEE.USERID='" + userId + "'" + " ORDER BY PAYDATE DESC";
                }
            }
        } else {
            sql = "SELECT PAYFEE.PAYNO,PAYFEE.USERID,PAYFEE.PAYDATE,PAYFEE.PAYFEE,PAYFEE.PAYKIND,(select PAYKINDNAME from PayWays where PAYKIND=PAYFEE.PAYKIND)payKindDesc,PAYFEE.OPERATORID,PAYFEE.PAYSTATE,PAYFEE.REMARK,PAYFEE.BILLNO,USERINFO.USERNAME,USERINFO.USERADDR,PAYFEEDETAIL.METERNO,PAYFEEDETAIL.QUANTITY,FEEITEMLIST.FEEITEMNAME,(SELECT OPERATORNAME FROM OPERATOR WHERE OPERATORID=PAYFEE.OPERATORID)OPERATORNAME  FROM PAYFEE,PAYFEEDETAIL,USERINFO,USERDETAIL,FEEITEMLIST  WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERINFO.USERID=PAYFEE.USERID AND USERDETAIL.USERID=PAYFEE.USERID  AND USERDETAIL.METERNO=PAYFEEDETAIL.METERNO AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID AND PAYFEE.USERID='" + userId + "' ORDER BY PAYDATE DESC";
        }

        List<PayFee> list = this.jdbcTemplate.query(sql, new PayFeeDetailDTOMapper());
        return list;
    }

    public int getPayFeeHisByUserIdAndTypeTotal(String userId, int type) {
        String sql = "";
        if (type != 1 && type != 2) {
            if (type == 3) {
                sql = "SELECT COUNT(*)PAYFEE FROM PAYFEE WHERE 1<>1";
            } else if (type == 4) {
                sql = "SELECT COUNT(*)PAYFEE FROM PAYFEE WHERE 1<>1";
            } else {
                sql = "SELECT COUNT(*) FROM PAYFEE,PAYFEEDETAIL,FEEITEMLIST,FEETYPE WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID AND FEETYPE.FEETYPEID=FEEITEMLIST.FEETYPEID AND FEETYPE.FEETYPEMARK=" + type + " " + "AND PAYFEE.USERID='" + userId + "' ";
            }
        } else {
            sql = "SELECT COUNT(*)PAYFEE FROM PAYFEE,PAYFEEDETAIL,USERDETAIL WHERE USERDETAIL.USERID=PAYFEE.USERID AND USERDETAIL.METERNO=PAYFEEDETAIL.METERNO AND PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND PAYFEE.USERID='" + userId + "' ";
        }

        return this.jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public List<PayFee> getgetPayFeeHisByUserIdAndTypeForFooter(String userId, int type) {
        String sql = "SELECT SUM(PAYFEE)PAYFEE FROM PAYFEE WHERE 1<>1";
        if (type != 1 && type != 2) {
            if (type != 3 && type != 4) {
                sql = "SELECT SUM(TOTAL)PAYFEE FROM PAYFEEDETAIL,PAYFEE,FEEITEMLIST,FEETYPE WHERE PAYFEE.PAYNO=PAYFEEDETAIL.PAYNO AND FEEITEMLIST.FEEITEMID=PAYFEEDETAIL.FEEITEMID AND FEETYPE.FEETYPEID=FEEITEMLIST.FEETYPEID AND FEETYPE.FEETYPEMARK=" + type + " " + "AND PAYFEE.USERID='" + userId + "' ";
            }
        } else {
            sql = "SELECT SUM(PAYFEE)PAYFEE FROM PAYFEE,PAYFEEDETAIL,USERDETAIL WHERE PAYFEEDETAIL.PAYNO=PAYFEE.PAYNO AND USERDETAIL.USERID=PAYFEE.USERID AND USERDETAIL.METERNO=PAYFEEDETAIL.METERNO AND PAYFEE.USERID='" + userId + "' ";
        }

        List<PayFee> list = this.jdbcTemplate.query(sql, new PayFeeDetailForFooterDTOMapper());
        return list;
    }

    public Integer addLsUserForWy(final UserInfo userInfo) {
        Integer no = (Integer)this.jdbcTemplate.execute("{call AddLsUserInfoForWy(?,?,?,?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, userInfo.getCustId());
                cs.setString(2, userInfo.getUserName());
                cs.setString(3, userInfo.getUserAddr());
                cs.setString(4, userInfo.getMobilePhone());
                cs.setString(5, userInfo.getAreaId());
                cs.setInt(6, userInfo.getMemberCount());
                cs.setDouble(7, userInfo.getAreas());
                cs.setString(8, userInfo.getEmail());
                cs.setInt(9, userInfo.getSmsKind());
                cs.setInt(10, Integer.parseInt(userInfo.getCreateUser()));
                cs.setString(11, userInfo.getIdCard());
                cs.setInt(12, userInfo.getFloors());
                cs.registerOutParameter(13, 4);
                cs.execute();
                return cs.getInt(13);
            }
        });
        return no;
    }

    public Integer editLsUserForWy(final UserInfo userInfo) {
        Integer no = (Integer)this.jdbcTemplate.execute("{call EditLsUserInfoForWy(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, userInfo.getCustId());
                cs.setString(2, userInfo.getUserName());
                cs.setString(3, userInfo.getUserAddr());
                cs.setString(4, userInfo.getMobilePhone());
                cs.setString(5, userInfo.getAreaId());
                cs.setInt(6, userInfo.getMemberCount());
                cs.setDouble(7, userInfo.getAreas());
                cs.setString(8, userInfo.getEmail());
                cs.setInt(9, userInfo.getSmsKind());
                cs.setInt(10, Integer.parseInt(userInfo.getCreateUser()));
                cs.setString(11, userInfo.getIdCard());
                cs.setInt(12, userInfo.getFloors());
                cs.setString(13, userInfo.getUserId());
                cs.registerOutParameter(14, 4);
                cs.execute();
                return cs.getInt(14);
            }
        });
        return no;
    }

    public UserInfo getUserInfoByOrders(String orders) {
        String sql = "SELECT * FROM USERINFO WHERE 1=1 " + orders;
        List<UserInfo> list = this.jdbcTemplate.query(sql, new UserInfoDTOMapper());
        return list.size() > 0 ? (UserInfo)list.get(0) : null;
    }

    public int getTotalForSchedule(String condition, int typeId) {
        String sql = "";
        if (typeId != 1 && typeId != 2) {
            if (typeId == 5) {
                sql = "select count(*) from (select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,userparkinfo.carno as meterno,userparkinfo.feeitemid,feeitemlist.feeitemname,userState from userinfo,userparkinfo,feeitemlist,feetype where userparkinfo.userid=userinfo.userid and feeitemlist.feeitemid=userparkinfo.feeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=5  and " + condition + ")t where 1=1)";
            } else if (typeId == 6) {
                sql = "select count(*) from (select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,'' as meterno,userfunddetail.wyfeeitemid feeitemid,feeitemlist.feeitemname,userState from userinfo,userfunddetail,feeitemlist,feetype where userfunddetail.userid=userinfo.userid and feeitemlist.feeitemid=userfunddetail.wyfeeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=6  and " + condition + ")t where 1=1)";
            } else if (typeId == 7) {
                sql = "select count(*) from (select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,'' as meterno,userfunddetail.nqfeeitemid feeitemid,feeitemlist.feeitemname,userState from userinfo,userfunddetail,feeitemlist,feetype where userfunddetail.userid=userinfo.userid and feeitemlist.feeitemid=userfunddetail.nqfeeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=7  and " + condition + ")t where 1=1)";
            } else {
                if (typeId != 99) {
                    return 0;
                }

                sql = "select count(*) from (select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,userdetail.meterno,userdetail.xsfeeitemid as feeitemid,feeitemlist.feeitemname,userState from userinfo,userdetail,feeitemlist,feetype where userdetail.userid=userinfo.userid and feeitemlist.feeitemid=userdetail.xsfeeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=1  and " + condition + ")t where 1=1)";
            }
        } else {
            sql = "select count(*) from (select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,userdetail.meterno,userdetail.feeitemid,feeitemlist.feeitemname,userState from userinfo,userdetail,feeitemlist,feetype where userdetail.userid=userinfo.userid and feeitemlist.feeitemid=userdetail.feeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=" + typeId + " " + " and " + condition + ")t where 1=1)";
        }

        return this.jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public List<UserInfoView> getUserForSchedule(int page, int rows, String condition, int typeId) {
        String sql = "";
        if (typeId != 1 && typeId != 2) {
            if (typeId == 5) {
                sql = "select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,userparkinfo.carno as meterno,userparkinfo.feeitemid,feeitemlist.feeitemname,userState from userinfo,userparkinfo,feeitemlist,feetype where userparkinfo.userid=userinfo.userid and feeitemlist.feeitemid=userparkinfo.feeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=5  and " + condition + ")t where 1=1";
            } else if (typeId == 6) {
                sql = "select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,'' as meterno,userfunddetail.wyfeeitemid feeitemid,feeitemlist.feeitemname,userState from userinfo,userfunddetail,feeitemlist,feetype where userfunddetail.userid=userinfo.userid and feeitemlist.feeitemid=userfunddetail.wyfeeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=6  and " + condition + ")t where 1=1";
            } else if (typeId == 7) {
                sql = "select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,'' as meterno,userfunddetail.nqfeeitemid feeitemid,feeitemlist.feeitemname,userState from userinfo,userfunddetail,feeitemlist,feetype where userfunddetail.userid=userinfo.userid and feeitemlist.feeitemid=userfunddetail.nqfeeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=7  and " + condition + ")t where 1=1";
            } else if (typeId == 99) {
                sql = "select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,userdetail.meterno,userdetail.xsfeeitemid as feeitemid,feeitemlist.feeitemname,userState from userinfo,userdetail,feeitemlist,feetype where userdetail.userid=userinfo.userid and feeitemlist.feeitemid=userdetail.xsfeeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=1  and " + condition + ")t where 1=1";
            }
        } else {
            sql = "select * from (select userinfo.custId,userinfo.userid,userinfo.areaid,userinfo.username,userinfo.useraddr,userdetail.meterno,userdetail.feeitemid,feeitemlist.feeitemname,userState from userinfo,userdetail,feeitemlist,feetype where userdetail.userid=userinfo.userid and feeitemlist.feeitemid=userdetail.feeitemid and feeitemlist.feetypeid=feetype.feetypeid and feetype.feetypemark=" + typeId + " " + " and " + condition + ")t where 1=1";
        }

        sql = CommonSQL.searchByPage(sql + " ORDER BY UserID asc", page, rows);
        return this.jdbcTemplate.query(sql, new UserForScheduleDTO());
    }

    public List<FeeDetail> getUserHffInfoByUserId(String userId, String meterNo) {
        String sql = "select UserId,MeterNo,Feedetail.FeeItemId,FeeMonth,TheDosage,AddDosage,TheFee,PayFlag,FeeItemName,Price,Feedetail.remark,getlatefee(feedetail.userid,feedetail.feeitemid,feemonth,thefee,0)FineFee from Feedetail left join Feeitemlist on Feeitemlist.Feeitemid=Feedetail.FeeItemId where Feedetail.payFlag=0 and Feedetail.MeterNo='" + meterNo + "' and Feedetail.userId='" + userId + "'";
        List<FeeDetail> list = this.jdbcTemplate.query(sql, new FeeDetailForHffDTO());
        return list;
    }

    public void errMarkMeter(String meterNo, int errMark) {
        String sql = "update MeterInfo set errMark=" + errMark + " where meterNO='" + meterNo + "'";
        this.jdbcTemplate.update(sql);
    }

    public void editUserKuozhanForWy(UserInfo userInfo) {
        String sql = "update userInfo set areas1=" + userInfo.getAreas1() + ",wyfeeItemid1='" + userInfo.getWyfeeItemid1() + "',nqfeeItemid1='" + userInfo.getNqfeeItemid1() + "',areas2=" + userInfo.getAreas2() + ",wyfeeItemid2='" + userInfo.getWyfeeItemid2() + "',nqfeeItemid2='" + userInfo.getNqfeeItemid2() + "',areas3=" + userInfo.getAreas3() + ",wyfeeItemid3='" + userInfo.getWyfeeItemid3() + "',nqfeeItemid3='" + userInfo.getNqfeeItemid3() + "' where userId='" + userInfo.getUserId() + "'";
        this.jdbcTemplate.update(sql);
    }

    public Integer addSigleMeterForWy(final UserInfoViewDTO userInfo) throws DataAccessException {
        if (userInfo.getShareFlag() == 0) {
            userInfo.setShareRatio(100.0);
        }

        if (userInfo.getShareFlag2() == 0) {
            userInfo.setShareRatio2(100.0);
        }

        Integer no = (Integer)this.jdbcTemplate.execute("{call AddSingleMeterForWy(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ,?,?, ?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, userInfo.getCustId());
                cs.setString(2, userInfo.getUserId());
                cs.setInt(3, userInfo.getTypeId());
                cs.setString(4, userInfo.getMeterNo());
                cs.setInt(5, userInfo.getMeterPtl());
                cs.setString(6, userInfo.getMusterNo());
                cs.setString(7, userInfo.getSubNo());
                cs.setInt(8, userInfo.getMboxId());
                cs.setString(9, userInfo.getSealNo());
                cs.setInt(10, userInfo.getManuId());
                cs.setInt(11, userInfo.getComPort());
                cs.setInt(12, userInfo.getBaud());
                cs.setInt(13, userInfo.getDynameter());
                cs.setDouble(14, userInfo.getSpan());
                cs.setInt(15, userInfo.getAlarm1());
                cs.setInt(16, userInfo.getAlarm2());
                cs.setString(17, userInfo.getYearDosageDt());
                cs.setInt(18, userInfo.getPattern());
                cs.setDouble(19, userInfo.getInitDosage());
                cs.setString(20, userInfo.getFeeItemId());
                cs.setInt(21, userInfo.getKeyFlag());
                cs.setInt(22, userInfo.getOperatorId());
                cs.setInt(23, userInfo.getOnOff());
                cs.setInt(24, userInfo.getShareFlag());
                cs.setDouble(25, userInfo.getShareRatio());
                cs.registerOutParameter(26, 4);
                cs.execute();
                return cs.getInt(26);
            }
        });
        return no;
    }

    public Integer delMeterInfoByMeterNo(final String meterNo) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call DelMeterInfoByMeterNo(?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, meterNo);
                cs.registerOutParameter(2, 4);
                cs.execute();
                return cs.getInt(2);
            }
        });
        return no;
    }

    public void upLoadMeterToDosagedetail(String meterNo, int custId, int operatorId) {
        String sql = "insert into dosagedetail(meterNo,markTime,custId,ReadStyle) values('" + meterNo + "','2000-01-01 00:00:00'," + custId + ",1)";
        this.jdbcTemplate.update(sql);
        String sql1 = "update dosagedetail set custId=" + custId + " where meterNo='" + meterNo + "'";
        this.jdbcTemplate.update(sql1);
    }

    public class FeeDetailByUserIdDTO implements RowMapper {
        public FeeDetailByUserIdDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeeDetail dto = new FeeDetail();
            dto.setUserId(rs.getString("userId"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setFeeMonth(rs.getString("feeMonth"));
            dto.setPrice(rs.getDouble("areaprice"));
            dto.setAreas(rs.getDouble("areas"));
            dto.setTheFee(rs.getDouble("thefee"));
            dto.setTheDosage(rs.getDouble("thedosage"));
            dto.setRemark(rs.getString("remark"));
            dto.setFineFee(rs.getDouble("fineFee"));
            return dto;
        }
    }

    public class FeeDetailForHffDTO implements RowMapper {
        public FeeDetailForHffDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeeDetail dto = new FeeDetail();
            dto.setUserId(rs.getString("userId"));
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setFeeMonth(rs.getString("feeMonth"));
            dto.setTheDosage(rs.getDouble("theDosage"));
            dto.setAddDosage(rs.getDouble("addDosage"));
            dto.setTheFee(rs.getDouble("theFee"));
            dto.setPayFlag(rs.getInt("payFlag"));
            dto.setPrice(rs.getDouble("price"));
            dto.setRemark(rs.getString("remark"));
            dto.setFineFee(rs.getDouble("fineFee"));
            return dto;
        }
    }

    public class MetersByUserIdMapper implements RowMapper {
        public MetersByUserIdMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("meterNo");
        }
    }

    public class PayFeeDetailDTOMapper implements RowMapper {
        public PayFeeDetailDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            PayFee dto = new PayFee();
            dto.setPayNo(rs.getString("payNo"));
            dto.setUserId(rs.getString("userId"));
            dto.setPayDate(rs.getString("payDate"));
            dto.setPayFee(rs.getDouble("payFee"));
            dto.setPayKind(rs.getInt("payKind"));
            dto.setOperatorId(rs.getInt("operatorId"));
            dto.setOperatorName(rs.getString("operatorName"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setPayState(rs.getInt("payState"));
            if (rs.getInt("PayState") == 8) {
                dto.setFeeItemName(rs.getString("feeItemName") + "【已退费】");
            } else {
                dto.setFeeItemName(rs.getString("feeItemName"));
            }

            dto.setMeterNo(rs.getString("meterNo"));
            dto.setRemark(rs.getString("remark"));
            dto.setQuantity(rs.getDouble("quantity"));
            dto.setBillNo(rs.getString("billNo"));
            dto.setPayKindDesc(rs.getString("payKindDesc"));
            return dto;
        }
    }

    public class PayFeeDetailForFooterDTOMapper implements RowMapper {
        public PayFeeDetailForFooterDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            PayFee dto = new PayFee();
            dto.setUserId("");
            dto.setPayDate("缴费金额合计：");
            dto.setPayFee(rs.getDouble("payFee"));
            dto.setPayKind(99);
            return dto;
        }
    }

    public class UserDTOMapper implements RowMapper {
        public UserDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfoView dto = new UserInfoView();
            dto.setCustId(rs.getInt("custId"));
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setUserTypeId(rs.getInt("userTypeId"));
            dto.setUserTypeName(rs.getString("userTypeName"));
            dto.setBillKind(rs.getInt("billKind"));
            dto.setBillTypeUrl(rs.getString("billTypeUrl"));
            dto.setMobilePhone(rs.getString("mobilePhone"));
            dto.setMemberCount(rs.getInt("memberCount"));
            dto.setAreas(rs.getDouble("areas"));
            dto.setFloors(rs.getInt("floors"));
            dto.setUserState(rs.getInt("userState"));
            dto.setIdCard(rs.getString("idCard"));
            dto.setMusterNo(rs.getString("musterNo"));
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setTypeId(rs.getInt("typeId"));
            dto.setTypeName(rs.getString("typeName"));
            dto.setManuId(rs.getInt("manuId"));
            dto.setManuName(rs.getString("manuName"));
            dto.setDynameter(rs.getInt("dynameter"));
            dto.setPattern(rs.getInt("pattern"));
            dto.setAllSum(rs.getDouble("allSum"));
            dto.setBuySum(rs.getDouble("buySum"));
            dto.setDosageSum(rs.getDouble("dosageSum"));
            dto.setLeftSum(rs.getDouble("leftSum"));
            dto.setInitDosage(rs.getDouble("initDosage"));
            dto.setUseDosageSum(rs.getDouble("useDosageSum"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setFeeState(rs.getInt("feeState"));
            dto.setFeeStyle(rs.getInt("feeStyle"));
            dto.setFeeDate(rs.getString("feeDate"));
            dto.setYearDosageDt(rs.getString("yearDosageDt"));
            dto.setMboxId(rs.getInt("mboxId"));
            dto.setMboxNo(rs.getString("mboxNo"));
            dto.setSubNo(rs.getString("subNo"));
            dto.setSubName(rs.getString("subName"));
            dto.setSmsKind(rs.getInt("smsKind"));
            dto.setComPort(rs.getInt("comPort"));
            dto.setBaud(rs.getInt("baud"));
            dto.setMeterPtl(rs.getInt("meterPtl"));
            dto.setMeterPic(rs.getString("meterPic"));
            dto.setAlarm1(rs.getInt("alarm1"));
            dto.setAlarm2(rs.getInt("alarm2"));
            dto.setKeyFlag(rs.getInt("keyFlag"));
            dto.setSpan(rs.getDouble("span"));
            dto.setSealNo(rs.getString("sealNo"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setDn(rs.getInt("dn"));
            dto.setOnOff(rs.getInt("onOff"));
            return dto;
        }
    }

    public class UserDetailByUserIdDTO implements RowMapper {
        public UserDetailByUserIdDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfoView dto = new UserInfoView();
            dto.setMusterNo(rs.getString("musterNo"));
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setTypeId(rs.getInt("typeId"));
            dto.setTypeName(rs.getString("typeName"));
            dto.setManuId(rs.getInt("manuId"));
            dto.setMboxId(rs.getInt("mboxId"));
            dto.setSubNo(rs.getString("subNo"));
            dto.setMeterPtl(rs.getInt("meterPtl"));
            dto.setComPort(rs.getInt("comPort"));
            dto.setBaud(rs.getInt("baud"));
            dto.setKeyFlag(rs.getInt("keyFlag"));
            dto.setAlarm1(rs.getInt("alarm1"));
            dto.setAlarm2(rs.getInt("alarm2"));
            dto.setDynameter(rs.getInt("dynameter"));
            dto.setDn(rs.getInt("dn"));
            dto.setSpan(rs.getDouble("span"));
            dto.setPattern(rs.getInt("pattern"));
            dto.setInitDosage(rs.getDouble("initDosage"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setYearDosageDt(rs.getString("yearDosageDt"));
            dto.setLeftSum(rs.getDouble("leftSum"));
            dto.setPattern(rs.getInt("pattern"));
            dto.setBillKind(rs.getInt("billKind"));
            dto.setBillTypeUrl(rs.getString("billTypeUrl"));
            dto.setYquantity(rs.getDouble("yquantity"));
            dto.setPrice(rs.getString("price"));
            dto.setReadDate(rs.getString("readDate"));
            dto.setThesdfprice(rs.getDouble("thesdfprice"));
            dto.setThewsfprice(rs.getDouble("thewsfprice"));
            dto.setOnOff(rs.getInt("onOff"));
            dto.setTransDosage(rs.getDouble("fineTotal"));
            dto.setXsfeeItemId(rs.getString("xsfeeItemId"));
            dto.setShareFlag(rs.getInt("shareFlag"));
            dto.setShareRatio(rs.getDouble("shareRatio"));
            return dto;
        }
    }

    public class UserForScheduleDTO implements RowMapper {
        public UserForScheduleDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfoView dto = new UserInfoView();
            dto.setCustId(rs.getInt("custId"));
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            return dto;
        }
    }

    public class UserForWyDTOMapper implements RowMapper {
        public UserForWyDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfoDTO dto = new UserInfoDTO();
            dto.setCustId(rs.getInt("custId"));
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setMobilePhone(rs.getString("mobilePhone"));
            dto.setMemberCount(rs.getInt("memberCount"));
            dto.setAreas(rs.getDouble("areas"));
            dto.setFloors(rs.getInt("floors"));
            dto.setUserState(rs.getInt("userState"));
            dto.setIdCard(rs.getString("idCard"));
            dto.setSmsKind(rs.getInt("smsKind"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setWyfId(rs.getString("wyfId"));
            dto.setNqfId(rs.getString("nqfId"));
            dto.setFzfId(rs.getString("fzfId"));
            dto.setParkId(rs.getString("parkId"));
            dto.setCarNo(rs.getString("carNo"));
            dto.setTcfId(rs.getString("tcfId"));
            dto.setAreas1(rs.getDouble("areas1"));
            dto.setWyfeeItemid1(rs.getString("wyfeeItemid1"));
            dto.setNqfeeItemid1(rs.getString("nqfeeItemid1"));
            dto.setAreas2(rs.getDouble("areas2"));
            dto.setWyfeeItemid2(rs.getString("wyfeeItemid2"));
            dto.setNqfeeItemid2(rs.getString("nqfeeItemid2"));
            dto.setAreas3(rs.getDouble("areas3"));
            dto.setWyfeeItemid3(rs.getString("wyfeeItemid3"));
            dto.setNqfeeItemid3(rs.getString("nqfeeItemid3"));
            dto.setMeters(UserDaoImpl.this.getMetersByUserId(rs.getString("userId")));
            return dto;
        }
    }

    public class UserInfoDTOMapper implements RowMapper {
        public UserInfoDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfo dto = new UserInfo();
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setUserTypeId(rs.getInt("userTypeId"));
            dto.setBillKind(rs.getInt("billKind"));
            dto.setMemberCount(rs.getInt("memberCount"));
            dto.setAreas(rs.getDouble("areas"));
            dto.setFloors(rs.getInt("floors"));
            dto.setEmail(rs.getString("email"));
            dto.setMobilePhone(rs.getString("mobilePhone"));
            dto.setUserState(rs.getInt("userState"));
            dto.setSmsKind(rs.getInt("smsKind"));
            dto.setContractNo(rs.getString("contractNo"));
            dto.setUserCode(rs.getString("userCode"));
            dto.setCreateDate(rs.getString("createDate"));
            return dto;
        }
    }

    public class UserParkInfoDTO implements RowMapper {
        public UserParkInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserParkInfo dto = new UserParkInfo();
            dto.setUserId(rs.getString("userId"));
            dto.setParkId(rs.getInt("parkId"));
            dto.setParkNo(rs.getString("parkNo"));
            dto.setCarNo(rs.getString("carNo"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setTheFee(rs.getDouble("theFee"));
            dto.setCarStartDate(rs.getString("carStartDate"));
            dto.setCarEndDate(rs.getString("carEndDate"));
            return dto;
        }
    }
}
