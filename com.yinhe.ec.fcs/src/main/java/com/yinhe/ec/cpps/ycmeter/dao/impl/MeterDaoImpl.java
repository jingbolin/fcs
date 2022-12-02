//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.MeterDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@Repository
@Transactional
public class MeterDaoImpl implements MeterDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public MeterDaoImpl() {
    }

    public Integer getTotal(String order) {
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount("UserInfoView", order),Integer.class);
    }

    public List<UserInfoView> getMeterInfo(int page, int rows, String order) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("select * from UserInfoView WHERE " + order + " order by MusterNo,RecNo asc", page, rows);
        } else if ("PG".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPageForPG("select * from UserInfoView WHERE " + order + " order by MusterNo,RecNo asc", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" * from UserInfoView WHERE " + order + " ", page, rows, " MusterNo,RecNo", "asc");
        }

        List<UserInfoView> list = this.jdbcTemplate.query(sql, new UserViewDTOMapper());
        return list;
    }

    public List<UserInfoView> getMeterListByClause(String condition) {
        String sql = "select * from UserInfoView WHERE " + condition + "  order by MusterNo,RecNo asc";
        List<UserInfoView> list = this.jdbcTemplate.query(sql, new UserViewDTOMapper());
        return list;
    }

    public Integer getMaxRecNo(String musterNo) {
        String sql = "SELECT isnull(Max(RecNO),0)+1 RECNO FROM MeterInfo WHERE MusterNO ='" + musterNo + "'";
        return this.jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public List<DosageDays> getDayDosageByMeterNo(String meterNo) {
        String sql = "";
        String sql1 = "";
        String markDay = "2020-01-01";
        int n = this.jdbcTemplate.queryForObject("select count(meterNo) from dosagedays where meterno='" + meterNo + "' and dosagestate=2",Integer.class);
        if (n > 0) {
            if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                sql1 = "select markday from dosagedays where meterno='" + meterNo + "' and dosagestate=2 and rownum=1 order by markday desc";
            } else {
                sql1 = "select markday from dosagedays where meterno='" + meterNo + "' and dosagestate=2 order by markday desc limit 1";
            }

            markDay = (String)this.jdbcTemplate.queryForObject(sql1, String.class);

            try {
                markDay = Tools.daysEdit(markDay, -7);
            } catch (ParseException var7) {
                var7.printStackTrace();
            }
        }

        sql = "select meterNo,markday,daydosage,dosagesum,realdosagesum,readdate,dosagestate from dosagedays  where meterno='" + meterNo + "' and markday>='" + markDay + "'" + " order by markday desc ";
        List<DosageDays> list = this.jdbcTemplate.query(sql, new DosageDaysDTOMapper());
        return list;
    }

    public void updateMaxdayDosage(String meterNo, Double maxDosage) throws DataAccessException {
        this.jdbcTemplate.update("UPDATE METERINFO SET MAXDOSAGE=" + maxDosage + " WHERE METERNO='" + meterNo + "'");
    }

    public void modDayDosageSum(String meterNo, String markday, Double dayDosageSum, String checker) throws DataAccessException {
        if ("".equals(markday)) {
            this.jdbcTemplate.update("UPDATE DOSAGEDAYS SET dosagestate=2,CHECKDATE='" + Tools.getNow() + "',CHECKUSER='" + checker + "' WHERE METERNO='" + meterNo + "' AND DOSAGESTATE<>2");
            this.jdbcTemplate.update("UPDATE METERINFO SET ERRFLAG=2,VERIFYDATE='" + Tools.getNow() + "' WHERE METERNO='" + meterNo + "'");
        } else {
            this.jdbcTemplate.update("UPDATE DOSAGEDAYS SET DAYDOSAGE=NULL,DOSAGESUM=" + dayDosageSum + ",dosagestate=2,CHECKDATE='" + Tools.getNow() + "',CHECKUSER='" + checker + "' WHERE METERNO='" + meterNo + "' AND MARKDAY='" + markday + "'");
        }

    }

    public String getResultStrForUpdateDosageDays(final String meterNo, final String markday, final Double dayDosageSum, final String checker) {
        String Res = (String)this.jdbcTemplate.execute("{call CheckDayDosageSum(?,?,?,?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, meterNo);
                cs.setString(2, markday);
                cs.setDouble(3, dayDosageSum);
                cs.setString(4, checker);
                cs.registerOutParameter(5, 12);
                cs.execute();
                return cs.getString(5);
            }
        });
        return Res;
    }

    public List<MeterInfo> getMeterCountForFooter(String condition) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "SELECT (SELECT COUNT(METERNO) FROM METERINFO WHERE " + condition + ") METERCOUNT," + "(SELECT COUNT(METERNO) FROM METERINFO WHERE " + condition + " AND SUBSTR(LASTREADDT,1,10)=" + Tools.getForNDay(0) + ") HASREADCOUNT " + " FROM DUAL";
        } else {
            sql = "SELECT (SELECT COUNT(METERNO) FROM METERINFO WHERE " + condition + ") METERCOUNT," + "(SELECT COUNT(METERNO) FROM METERINFO WHERE " + condition + " AND SUBSTR(LASTREADDT,1,10)=" + Tools.getForNDay(0) + ") HASREADCOUNT ";
        }

        List<MeterInfo> list = this.jdbcTemplate.query(sql, new MeterFooterDTOMapper());
        return list;
    }

    public void batchUpdateMuster(final String[] meterIds, final String musterNo) {
        String sql = "update meterInfo set musterno = ? where meterno = ?";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                return meterIds.length;
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String meterId = meterIds[index];
                ps.setString(1, musterNo);
                ps.setString(2, meterId);
            }
        });
    }

    public void addMessageInfo(String messageId, String mobile, String tplId, String tplValue, String userId, String userName, String operatorName, int custId, String leftSum) throws DataAccessException {
        String messageContent = "";
        if ("42897".equals(tplId)) {
            messageContent = "【隆盛物业】尊敬的" + userName + "，您的剩余" + tplValue + "为" + leftSum + "元，请及时到隆盛物业充值，谢谢合作！ 【" + mobile + "】";
        } else if ("42898".equals(tplId)) {
            messageContent = "【隆盛物业】尊敬的" + userName + "，您已交" + tplValue + "元，如您发现仍未开通，请及时与物业中心联系处理！【" + mobile + "】";
        } else if ("42899".equals(tplId)) {
            messageContent = "【隆盛物业】尊敬的" + userName + "，您的" + tplValue + "已用完，会自动关停，请及时到隆盛物业充值，谢谢合作！【" + mobile + "】";
        } else if ("42900".equals(tplId)) {
            messageContent = "【隆盛物业】尊敬的" + userName + "，" + tplValue + "，给你带来不便，敬请谅解！【" + mobile + "】";
        }

        String sql = "insert into MessageList(MessageId,CustId,UserId,MessageMid,MessageContent,MessageTime,MessageUser) values ('" + messageId + "'," + custId + ",'" + userId + "','" + tplId + "','" + messageContent + "','" + Tools.getNow() + "','" + operatorName + "')";
        this.jdbcTemplate.update(sql);
    }

    public List<MessageList> getSendMsgInfo(int page, int rows, String orders) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("select MessageList.*,UserInfo.userName,UserInfo.Useraddr from MessageList, UserInfo where UserInfo.UserId=MessageList.Userid " + orders + " order by MESSAGETIME desc", page, rows);
        } else if ("PG".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPageForPG("select MessageList.*,UserInfo.userName,UserInfo.Useraddr from MessageList, UserInfo where UserInfo.UserId=MessageList.Userid " + orders + " order by MESSAGETIME desc", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" MessageList.*,UserInfo.userName,UserInfo.Useraddr from MessageList, UserInfo where UserInfo.UserId=MessageList.Userid " + orders + " ", page, rows, " MESSAGETIME", "desc");
        }

        List<MessageList> list = this.jdbcTemplate.query(sql, new MessageListDTOMapper());
        return list;
    }

    public String getLastMsgDt(String userId) {
        String sql = "";
        if (this.jdbcTemplate.queryForObject("select count(MessageTime) from messagelist where userid='" + userId + "' ",Integer.class) > 0) {
            sql = "select * from (select substring(MessageTime,1,10)MessageTime from messagelist where userid='" + userId + "' order by MessageTime desc) where rownum=1";
            return (String)this.jdbcTemplate.queryForObject(sql, String.class);
        } else {
            return "";
        }
    }

    public Meters getMetersByClause(String condition) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select meterCount,hasReadCount,meterCount-hasReadCount as notReadCount from (select (select count(MeterNo) from UserInfoView where " + condition + ")meterCount," + "(select count(MeterNo) from UserInfoView where " + condition + " and substr(ReadDate,1,10)='" + Tools.getForNDay(0) + "')hasReadCount " + "from dual)t";
        } else {
            sql = "select meterCount,hasReadCount,meterCount-hasReadCount as notReadCount from (select (select count(MeterNo) from UserInfoView where " + condition + ")meterCount," + "(select count(MeterNo) from UserInfoView where " + condition + " and substr(ReadDate,1,10)='" + Tools.getForNDay(0) + "')hasReadCount " + ")t";
        }

        List<Meters> list = this.jdbcTemplate.query(sql, new MetersDTOMapper());
        return list.size() > 0 ? (Meters)list.get(0) : new Meters();
    }

    public void changeMeterInfo(final MeterChange meterChange) throws DataAccessException {
        String sql = "insert into meterchange(meterno,custid,changedate,changeuser,changeflag,reason,premeterno,enddosagesum,startdosagesum,v1,v2,v3,v4,nv1,nv2,nv3,nv4,oldLeftsum) values (?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, meterChange.getMeterNo());
                ps.setInt(2, meterChange.getCustId());
                ps.setString(3, Tools.getNow());
                ps.setString(4, meterChange.getChangeUser());
                ps.setInt(5, meterChange.getChangeFlag());
                ps.setString(6, meterChange.getReason());
                ps.setString(7, meterChange.getPreMeterNo());
                ps.setDouble(8, meterChange.getEndDosageSum());
                ps.setDouble(9, meterChange.getStartDosageSum());
                ps.setDouble(10, meterChange.getV1());
                ps.setDouble(11, meterChange.getV2());
                ps.setDouble(12, meterChange.getV3());
                ps.setDouble(13, meterChange.getV4());
                ps.setDouble(14, meterChange.getNv1());
                ps.setDouble(15, meterChange.getNv2());
                ps.setDouble(16, meterChange.getNv3());
                ps.setDouble(17, meterChange.getNv4());
                ps.setDouble(18, meterChange.getOldLeftsum());
            }
        });
        if (!"".equals(meterChange.getNodeId())) {
            this.jdbcTemplate.update("update nbmeterInfo set meterNo='" + meterChange.getMeterNo() + "',nodeId='" + meterChange.getNodeId() + "' where meterNo='" + meterChange.getPreMeterNo() + "'");
        }

    }

    public int getMeterCount(MeterChange meterChange) {
        return meterChange.getMeterNo().equals(meterChange.getPreMeterNo()) ? 0 : (Integer)this.jdbcTemplate.queryForObject("select count(meterNo) from MeterInfo where meterNo='" + meterChange.getMeterNo() + "'", Integer.class);
    }

    public Integer getMsgTotal(String orders) {
        return this.jdbcTemplate.queryForObject("select count(*) from MessageList, UserInfo where UserInfo.UserId=MessageList.Userid " + orders + " ",Integer.class);
    }

    public void addAliMessageInfo(String messageId, String phoneNo, String templateCode, String messageContent, String userId, String userName, String operatorName, int custId) throws DataAccessException {
        String sql = "insert into MessageList(MessageId,CustId,UserId,MessageMid,MessageContent,MessageTime,MessageUser,PhoneNo) values ('" + messageId + "'," + custId + ",'" + userId + "','" + templateCode + "','" + messageContent + "','" + Tools.getNow() + "','" + operatorName + "','" + phoneNo + "')";
        this.jdbcTemplate.update(sql);
    }

    public String getLeftInfoByDosageSum(String meterNo, Double dosageSum) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select getLeftInfoByDosageSum('" + meterNo + "', " + dosageSum + ") FROM DUAL";
        } else {
            sql = "select getLeftInfoByDosageSum('" + meterNo + "', " + dosageSum + ")";
        }

        return (String)this.jdbcTemplate.queryForObject(sql, String.class);
    }

    public String getDosageSumByLeftDosage(String meterNo, Double leftDosage) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select getDosageSumByLeftDosage('" + meterNo + "', " + leftDosage + ") FROM DUAL";
        } else {
            sql = "select getDosageSumByLeftDosage('" + meterNo + "', " + leftDosage + ")";
        }

        return (String)this.jdbcTemplate.queryForObject(sql, String.class);
    }

    public class DosageDaysDTOMapper implements RowMapper {
        public DosageDaysDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            DosageDays dto = new DosageDays();
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setMarkDay(rs.getString("markDay"));
            dto.setDayDosage(rs.getDouble("daydosage"));
            dto.setDosageSum(rs.getDouble("dosagesum"));
            dto.setRealDosageSum(rs.getDouble("realdosagesum"));
            dto.setReadDate(rs.getString("readdate").substring(0, 19));
            dto.setDosageState(rs.getInt("DosageState"));
            return dto;
        }
    }

    public class MessageListDTOMapper implements RowMapper {
        public MessageListDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MessageList dto = new MessageList();
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setMessageContent(rs.getString("messageContent"));
            dto.setMessageTime(rs.getString("messageTime"));
            dto.setMessageUser(rs.getString("messageUser"));
            return dto;
        }
    }

    public class MeterFooterDTOMapper implements RowMapper {
        public MeterFooterDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterInfo dto = new MeterInfo();
            dto.setLblCount("仪表总数：");
            dto.setMeterCount(rs.getInt("METERCOUNT"));
            dto.setLblHasCout("当日抄回表数：");
            dto.setMeterHasCount(rs.getInt("HASREADCOUNT"));
            return dto;
        }
    }

    public class MetersDTOMapper implements RowMapper {
        public MetersDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Meters dto = new Meters();
            dto.setMeterCount(rs.getInt("meterCount"));
            dto.setHasReadCount(rs.getInt("hasReadCount"));
            dto.setNotReadCount(rs.getInt("notReadCount"));
            return dto;
        }
    }

    public class UserViewDTOMapper implements RowMapper {
        public UserViewDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfoView dto = new UserInfoView();
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setMobilePhone(rs.getString("mobilePhone"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setCustId(rs.getInt("custId"));
            dto.setCustName(rs.getString("custName"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setManuId(rs.getInt("manuId"));
            dto.setManuName(rs.getString("manuName"));
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setTypeId(rs.getInt("typeId"));
            dto.setTypeName(rs.getString("typeName"));
            dto.setMusterNo(rs.getString("musterNo"));
            dto.setComPort(rs.getInt("comPort"));
            dto.setBaud(rs.getInt("baud"));
            dto.setConnPara(rs.getString("connPara"));
            dto.setRecNo(rs.getInt("recNo"));
            dto.setDynameter(rs.getInt("dynameter"));
            dto.setReadDate(rs.getString("readDate"));
            dto.setAllSum(rs.getDouble("allSum"));
            dto.setBuySum(rs.getDouble("buySum"));
            dto.setDosageSum(rs.getDouble("dosageSum"));
            dto.setLeftSum(rs.getDouble("leftSum"));
            dto.setSyncFlag(rs.getInt("syncFlag"));
            dto.setTryFlag(rs.getInt("tryFlag"));
            dto.setPattern(rs.getInt("pattern"));
            dto.setOnState(rs.getInt("onState"));
            dto.setErrFlag(rs.getInt("errFlag"));
            dto.setAlarm1(rs.getInt("alarm1"));
            dto.setAlarm2(rs.getInt("alarm2"));
            dto.setMeterPtl(rs.getInt("meterPtl"));
            dto.setMusterPtl(rs.getInt("musterPtl"));
            dto.setBatch(rs.getInt("batch"));
            dto.setFeeState(rs.getInt("feeState"));
            dto.setMaxDosage(rs.getDouble("maxDosage"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setSmsKind(rs.getInt("smsKind"));
            dto.setFeeDate(rs.getString("feeDate"));
            dto.setYearDosageDt(rs.getString("yearDosageDt"));
            dto.setInitDosage(rs.getDouble("initDosage"));
            dto.setUseDosageSum(rs.getDouble("useDosageSum"));
            dto.setKeyFlag(rs.getInt("keyFlag"));
            dto.setRemark(rs.getString("remark"));
            dto.setBuyCount(rs.getInt("buyCount"));
            dto.setV1(rs.getDouble("v1"));
            dto.setV2(rs.getDouble("v2"));
            dto.setV3(rs.getDouble("v3"));
            dto.setV4(rs.getDouble("v4"));
            dto.setScheduleBuysum(rs.getDouble("scheduleBuysum"));
            dto.setScheduleLeftsum(rs.getDouble("scheduleLeftsum"));
            dto.setStepTotal(rs.getDouble("stepTotal"));
            dto.setChargemethod(rs.getInt("chargemethod"));
            dto.setPrice(rs.getString("price"));
            dto.setDeviceId(rs.getString("deviceId"));
            dto.setOnOff(rs.getInt("onOff"));
            return dto;
        }
    }
}
