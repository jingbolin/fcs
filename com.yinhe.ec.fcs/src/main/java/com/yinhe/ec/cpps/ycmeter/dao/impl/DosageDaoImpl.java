//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.DosageDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class DosageDaoImpl implements DosageDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public DosageDaoImpl() {
    }

    public List<DosageDays> getDosageByClause(String meterNo, String startDt, String endDt) {
        String sql = "SELECT meterno,markday,daydosage,dosagesum,readdt from dosagedays where meterno='" + meterNo + "' and markday>='" + startDt + "' and markday<='" + endDt + "' order by markday desc";
        List<DosageDays> list = this.jdbcTemplate.query(sql, new DosageDaysDTOMapper());
        return list;
    }

    public Integer getYDosageTotal(String order) {
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount("METERINFO", order),Integer.class);
    }

    public String getMReadRatio() {
        DecimalFormat df = new DecimalFormat("#.00");
        double meterCount = (double)this.jdbcTemplate.queryForObject("select count(meterno) MeterCount from meterinfo",Integer.class);
        double notTryCount = (double)this.jdbcTemplate.queryForObject("select count(meterno) NotTryCount from meterinfo where tryflag<>1",Integer.class);
        double hasReadCount = (double)this.jdbcTemplate.queryForObject("select count(meterno) ReadCount from dosagedays where markday='" + Tools.getForNDay(1) + "'",Integer.class);
        return meterCount == 0.0 ? "" : df.format(hasReadCount / (meterCount - notTryCount) * 100.0);
    }

    public List<RpInfo> getDateDosageByYear(String year) {
        String sql = "select (select sum(monthdosage) from dosagemonths where markmonth='" + year + "-01')monthdosage1," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-02')monthdosage2," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-03')monthdosage3," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-04')monthdosage4," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-05')monthdosage5," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-06')monthdosage6," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-07')monthdosage7," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-08')monthdosage8," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-09')monthdosage9," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-10')monthdosage10," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-11')monthdosage11," + "(select sum(monthdosage) from dosagemonths where markmonth='" + year + "-12')monthdosage12 " + " from dual";
        List<RpInfo> list = this.jdbcTemplate.query(sql, new DateDosageDTO());
        return list;
    }

    public List<DosageMonths> getMonthDosageByClause(String clauses, String markMonth) {
        String sql = "select UserCode,UserId,UserName,UserAddr,userviewinfo.MeterNo,dosagemonths.markmonth,trunc(dosagemonths.dosagesum)dosagesum,dosagemonths.readdt from userviewinfo,dosagemonths where userviewinfo.meterNo = dosagemonths.meterno and markMonth='" + markMonth + "' " + clauses;
        List<DosageMonths> list = this.jdbcTemplate.query(sql, new DosageMonthDTO());
        return list;
    }

    public List<DosageDetail> getDosageDeatilByClause(int page, int rows, String order) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT DOSAGEDETAIL.*,USERVIEWINFO.USERID,USERVIEWINFO.USERNAME,USERVIEWINFO.USERADDR,USERVIEWINFO.REGIONNAME,USERVIEWINFO.LINESNAME,USERVIEWINFO.MUSTERNO FROM DOSAGEDETAIL,USERVIEWINFO WHERE " + order + " ORDER BY DOSAGEDETAIL.METERNO,MARKTIME DESC", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" DOSAGEDETAIL.*,USERVIEWINFO.USERID,USERVIEWINFO.USERNAME,USERVIEWINFO.USERADDR,USERVIEWINFO.REGIONNAME,USERVIEWINFO.LINESNAME,USERVIEWINFO.MUSTERNO FROM DOSAGEDETAIL,USERVIEWINFO WHERE " + order + "  ", page, rows, " DOSAGEDETAIL.METERNO,MARKTIME", "DESC");
        }

        List<DosageDetail> list = this.jdbcTemplate.query(sql, new DosageDetailDTOMapper());
        return list;
    }

    public Integer getDosageDetailTotal(String order) {
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount(" DOSAGEDETAIL,USERVIEWINFO ", order),Integer.class);
    }

    public AlarmInfo getAlarmInfo(String createUser) {
        String sql = "SELECT (SELECT COUNT(METERNO) FROM DOSAGEDETAIL WHERE S2=1 AND SUBSTR(MARKTIME,1,10)='" + Tools.getForNDay(0) + "' AND METERNO IN (SELECT METERNO FROM USERDETAIL WHERE USERID IN (SELECT USERID FROM USERINFO WHERE CREATEUSER='" + createUser + "'))) Num_1, " + "(SELECT COUNT(METERNO) FROM DOSAGEDETAIL WHERE S2=1 AND SUBSTR(MARKTIME,1,10)>='" + Tools.getForNMonth(0) + "-01' AND SUBSTR(MARKTIME,1,10)<='" + Tools.getForNMonth(0) + "-31' AND METERNO IN (SELECT METERNO FROM USERDETAIL WHERE USERID IN (SELECT USERID FROM USERINFO WHERE CREATEUSER='" + createUser + "')))Num_2, " + "(SELECT SUM(D0) FROM DOSAGEDETAIL WHERE SUBSTR(MARKTIME,1,10)>='" + Tools.getForNMonth(0) + "-01' AND SUBSTR(MARKTIME,1,10)<='" + Tools.getForNMonth(0) + "-31' AND METERNO IN (SELECT METERNO FROM USERDETAIL WHERE USERID IN (SELECT USERID FROM USERINFO WHERE CREATEUSER='" + createUser + "')))Num_3, " + "(SELECT COUNT(USERID) FROM USERINFO WHERE CREATEUSER='" + createUser + "')Num_5, " + "(SELECT SUM(DOSAGESUM) FROM METERINFO WHERE METERNO IN (SELECT METERNO FROM USERDETAIL WHERE USERID IN (SELECT USERID FROM USERINFO WHERE CREATEUSER='" + createUser + "')))Num_6 " + " FROM DUAL";
        List<AlarmInfo> list = this.jdbcTemplate.query(sql, new AlarmInfoDTOMapper());
        return list.size() > 0 ? (AlarmInfo)list.get(0) : new AlarmInfo();
    }

    public AlarmInfo getMainReportInfo(String operatorId, String custId, String feeOrders) {
        String sql = "SELECT (SELECT SUM(DAYDOSAGE*DYNAMETER) FROM DOSAGEDAYS,METERINFO WHERE METERINFO.METERNO=DOSAGEDAYS.METERNO AND TYPEID=1 AND MARKDAY='" + Tools.getForNDay(1) + "' AND METERINFO.CUSTID=" + custId + ") as num_ele_Dosage, " + "(SELECT SUM(DAYDOSAGE*DYNAMETER) FROM DOSAGEDAYS,METERINFO WHERE METERINFO.METERNO=DOSAGEDAYS.METERNO AND TYPEID=2 AND MARKDAY='" + Tools.getForNDay(1) + "' AND METERINFO.CUSTID=" + custId + ") as num_water_Dosage, " + "(SELECT SUM(DAYDOSAGE*DYNAMETER) FROM DOSAGEDAYS,METERINFO WHERE METERINFO.METERNO=DOSAGEDAYS.METERNO AND TYPEID=3 AND MARKDAY='" + Tools.getForNDay(1) + "' AND METERINFO.CUSTID=" + custId + ") as num_hot_Dosage, " + "(SELECT COUNT(SENDDATA.SID) FROM SENDDATA,METERINFO WHERE METERINFO.METERNO=SENDDATA.METERNO AND TYPEID=1 AND MANAGEFLAG=0  AND METERINFO.CUSTID=" + custId + ")num_ele_Send_0, " + "(SELECT COUNT(NBSENDDATA.SENDID) FROM NBSENDDATA,METERINFO WHERE METERINFO.METERNO=NBSENDDATA.METERNO AND TYPEID=1 AND MANAGEFLAG=0  AND METERINFO.CUSTID=" + custId + ")num_ele_Send_1, " + "(SELECT COUNT(SENDDATA.SID) FROM SENDDATA,METERINFO WHERE METERINFO.METERNO=SENDDATA.METERNO AND TYPEID=2 AND MANAGEFLAG=0  AND METERINFO.CUSTID=" + custId + ")num_water_Send_0, " + "(SELECT COUNT(NBSENDDATA.SENDID) FROM NBSENDDATA,METERINFO WHERE METERINFO.METERNO=NBSENDDATA.METERNO AND TYPEID=2 AND MANAGEFLAG=0  AND METERINFO.CUSTID=" + custId + ")num_water_Send_1, " + "(SELECT COUNT(SENDDATA.SID) FROM SENDDATA,METERINFO WHERE METERINFO.METERNO=SENDDATA.METERNO AND TYPEID=3 AND MANAGEFLAG=0  AND METERINFO.CUSTID=" + custId + ")num_hot_Send_0, " + "(SELECT COUNT(NBSENDDATA.SENDID) FROM NBSENDDATA,METERINFO WHERE METERINFO.METERNO=NBSENDDATA.METERNO AND TYPEID=3 AND MANAGEFLAG=0  AND METERINFO.CUSTID=" + custId + ")num_hot_Send_1, " + "(SELECT COUNT(METERNO) FROM METERINFO WHERE CUSTID=" + custId + " AND TYPEID=1)num_ele_Count, " + "(SELECT COUNT(METERNO) FROM METERINFO WHERE CUSTID=" + custId + " AND TYPEID=2)num_water_Count, " + "(SELECT COUNT(METERNO) FROM METERINFO WHERE CUSTID=" + custId + " AND TYPEID=3)num_hot_Count, " + "(SELECT COUNT(USERID) FROM USERINFO WHERE CUSTID=" + custId + ")num_userCount, " + "(SELECT SUM(PAYFEE) FROM PAYFEE WHERE PAYSTATE<>8 AND SUBSTR(PAYDATE,1,10)='" + Tools.getForNDay(0) + "' AND CUSTID=" + custId + " )num_feeSum " + " FROM DUAL";
        List<AlarmInfo> list = this.jdbcTemplate.query(sql, new MainReportInfoDTOMapper());
        return list.size() > 0 ? (AlarmInfo)list.get(0) : new AlarmInfo();
    }

    public List<MainReportInfo> getUserByYear(String custId) {
        String sql = "select substr(createdate,1,4)markYear,count(distinct(userid)) UserCount,count(meterno) FeeUserCount from userdetail where custid=" + custId + " group by substr(createdate,1,4)";
        return this.jdbcTemplate.query(sql, new UserAnalysisDTOMapper());
    }

    public List<MainReportInfo> getHbDosageByCustId(String custId) {
        String sql = "select substr(markday,6,5)markday,sum(case when typeid=1 then daydosage*dynameter else 0 end) eleDosage,sum(case when typeid=2 then daydosage*dynameter else 0 end) waterDosage, sum(case when typeid=3 then daydosage*dynameter else 0 end) hotDosage  from dosagedays, meterinfo where meterinfo.custid=" + custId + " and meterinfo.meterno=dosagedays.meterno and markday>='" + Tools.getForNDay(30) + "' and markday<='" + Tools.getForNDay(1) + "' " + " group by markday order by markday";
        return this.jdbcTemplate.query(sql, new DosageAnalysisDTOMapper());
    }

    public List<MainReportInfo> getYearFeeKindByCustId(String custId) {
        String sql = "select paykindName,payfee,round(payfee_ratio,2)payfee_ratio from (  select t.paykindName,sum(payfee) payfee,sum(sum(payfee)) over() payfee_total,sum(payfee) / sum(sum(payfee)) over() payfee_ratio  from  (  select paykind,(select paykindName from Payways where payKind=payfee.payKind) as paykindName,payfee from payfee where paystate<>8 and custId=" + custId + " and substr(paydate,1,10)>'" + Tools.getCurDateY() + "-01-01' " + " )t group by t.paykindName " + " )p ";
        return this.jdbcTemplate.query(sql, new PayFeeKindAnalysisDTOMapper());
    }

    public List<MainReportInfo> getYearFeeByOperator(String custId, String feeOrders) {
        String sql = "select sum(payfee)payfee,operatorname from payfee,operator where payfee.paystate<>8 and payfee.custId=" + custId + " and operator.operatorid=payfee.operatorid " + feeOrders + " and substr(paydate,1,10)>='" + Tools.getCurDateY() + "-01-01' group by operatorname";
        return this.jdbcTemplate.query(sql, new YearfeeAnalysisDTOMapper());
    }

    public List<MainReportInfo> getReadRatioByCustId(String custId) {
        String sql = "SELECT A.ReadDay,A.expectNum, B.ActualNum FROM (select t.ReadDay,    (select count(MeterNo) from Meterinfo where custId=" + custId + "  and substr(createDate,1,10)<=t.ReadDay) expectNum " + "  from " + "  (" + "  select to_char(sysdate-31 + rownum, 'YYYY-MM-DD') ReadDay " + "\t\tfrom dual " + "\t\tconnect by rownum<=30 " + "\t\torder by ReadDay desc " + "\t\t)t" + ")  A " + " LEFT JOIN " + " (select MARKDAY,count(MeterNo) AS ActualNum from DosageDays where custId=" + custId + "  AND MARKDAY>=to_char(sysdate-30, 'YYYY-MM-DD') GROUP BY MARKDAY  ORDER BY MARKDAY)  B " + " ON A.ReadDay=B.MARKDAY " + " order by A.ReadDay desc";
        return this.jdbcTemplate.query(sql, new ReadRatioDTOMapper());
    }

    public Map<String, Integer> getMainErrorCount(String custId) {
        String sql1 = "select count(meterNo) from meterInfo where substr(readDate,1,10)< '" + Tools.getForNDay(7) + "' and custId=" + custId;
        String sql2 = "select count(meterNo) from meterInfo where substr(readDate,1,10)< '" + Tools.getForNDay(30) + "' and custId=" + custId;
        String sql3 = "select count(userId) from userInfo where userId not in (select userId from payfee where substr(paydate,1,10)>= '" + Tools.getForNDay(60) + "') and custId=" + custId;
        String sql4 = "select count(meterNo) from meterInfo where leftSum<0 and custId=" + custId;
        String sql5 = "select count(meterNo) from meterInfo where ErrFlag=1 and tryFlag=1 and custId=" + custId;
        String sql6 = "select count(meterNo) from nbMeterInfo where deviceId<>uploadDeviceId and regState=1 and custId=" + custId;
        int num1 = (Integer)this.jdbcTemplate.queryForObject(sql1, Integer.class);
        int num2 = (Integer)this.jdbcTemplate.queryForObject(sql2, Integer.class);
        int num3 = (Integer)this.jdbcTemplate.queryForObject(sql3, Integer.class);
        int num4 = (Integer)this.jdbcTemplate.queryForObject(sql4, Integer.class);
        int num5 = (Integer)this.jdbcTemplate.queryForObject(sql5, Integer.class);
        int num6 = (Integer)this.jdbcTemplate.queryForObject(sql6, Integer.class);
        Map<String, Integer> map = new HashMap();
        map.put("num1", num1);
        map.put("num2", num2);
        map.put("num3", num3);
        map.put("num4", num4);
        map.put("num5", num5);
        map.put("num6", num6);
        return map;
    }

    public class AlarmInfoDTOMapper implements RowMapper {
        public AlarmInfoDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            AlarmInfo dd = new AlarmInfo();
            dd.setNum_1(rs.getInt("Num_1"));
            dd.setNum_2(rs.getInt("Num_2"));
            dd.setNum_3(rs.getDouble("Num_3"));
            dd.setNum_4(0);
            dd.setNum_5(rs.getInt("Num_5"));
            dd.setNum_6(rs.getDouble("Num_6"));
            return dd;
        }
    }

    public class DateDosageDTO implements RowMapper {
        public DateDosageDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            RpInfo dto = new RpInfo();
            dto.setDosage1(rs.getDouble("monthdosage1"));
            dto.setDosage2(rs.getDouble("monthdosage2"));
            dto.setDosage3(rs.getDouble("monthdosage3"));
            dto.setDosage4(rs.getDouble("monthdosage4"));
            dto.setDosage5(rs.getDouble("monthdosage5"));
            dto.setDosage6(rs.getDouble("monthdosage6"));
            dto.setDosage7(rs.getDouble("monthdosage7"));
            dto.setDosage8(rs.getDouble("monthdosage8"));
            dto.setDosage9(rs.getDouble("monthdosage9"));
            dto.setDosage10(rs.getDouble("monthdosage10"));
            dto.setDosage11(rs.getDouble("monthdosage11"));
            dto.setDosage12(rs.getDouble("monthdosage12"));
            return dto;
        }
    }

    public class DosageAnalysisDTOMapper implements RowMapper {
        public DosageAnalysisDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainReportInfo dd = new MainReportInfo();
            dd.setEleDosage(rs.getDouble("eleDosage"));
            dd.setWaterDosage(rs.getDouble("waterDosage"));
            dd.setHotDosage(rs.getDouble("hotDosage"));
            dd.setMarkDay(rs.getString("markDay"));
            return dd;
        }
    }

    public class DosageDaysDTOMapper implements RowMapper {
        public DosageDaysDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            DosageDays dto = new DosageDays();
            dto.setMeterNo(rs.getString("meterno"));
            dto.setMarkDay(rs.getString("markday"));
            dto.setDayDosage(rs.getDouble("daydosage"));
            dto.setDosageSum(rs.getDouble("dosagesum"));
            dto.setReadDate(rs.getString("readDate"));
            return dto;
        }
    }

    public class DosageDetailDTOMapper implements RowMapper {
        public DosageDetailDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            DosageDetail dd = new DosageDetail();
            dd.setMeterNo(rs.getString("meterNo"));
            dd.setD0(rs.getDouble("d0"));
            dd.setV0(rs.getDouble("v0"));
            dd.setS1(rs.getInt("s1"));
            dd.setS2(rs.getInt("s2"));
            dd.setMarkTime(rs.getString("markTime"));
            dd.setReadUser(rs.getString("readUser"));
            dd.setUserId(rs.getString("userId"));
            dd.setUserName(rs.getString("userName"));
            dd.setUserAddr(rs.getString("userAddr"));
            dd.setLinesName(rs.getString("linesName"));
            dd.setRegionName(rs.getString("regionName"));
            dd.setMusterNo(rs.getString("musterNo"));
            return dd;
        }
    }

    public class DosageMonthDTO implements RowMapper {
        public DosageMonthDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            DosageMonths dto = new DosageMonths();
            return dto;
        }
    }

    public class MainReportInfoDTOMapper implements RowMapper {
        public MainReportInfoDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            AlarmInfo dd = new AlarmInfo();
            dd.setNum_feeSum(rs.getDouble("num_feeSum"));
            dd.setNum_userCount(rs.getInt("num_userCount"));
            dd.setNum_ele_Count(rs.getInt("num_ele_Count"));
            dd.setNum_water_Count(rs.getInt("num_water_Count"));
            dd.setNum_hot_Count(rs.getInt("num_hot_Count"));
            dd.setNum_ele_Dosage(rs.getDouble("num_ele_Dosage"));
            dd.setNum_water_Dosage(rs.getDouble("num_water_Dosage"));
            dd.setNum_hot_Dosage(rs.getDouble("num_hot_Dosage"));
            dd.setNum_ele_Send(rs.getInt("num_ele_Send_0") + rs.getInt("num_ele_Send_1"));
            dd.setNum_water_Send(rs.getInt("num_water_Send_0") + rs.getInt("num_water_Send_1"));
            dd.setNum_hot_Send(rs.getInt("num_hot_Send_0") + rs.getInt("num_hot_Send_1"));
            return dd;
        }
    }

    public class PayFeeKindAnalysisDTOMapper implements RowMapper {
        public PayFeeKindAnalysisDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainReportInfo dd = new MainReportInfo();
            dd.setPaykindName(rs.getString("paykindName"));
            dd.setPayFee(rs.getDouble("payFee"));
            dd.setPaykindRatio(rs.getDouble("payfee_ratio") * 100.0);
            return dd;
        }
    }

    public class ReadRatioDTOMapper implements RowMapper {
        public ReadRatioDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainReportInfo dd = new MainReportInfo();
            dd.setExpectNum(rs.getInt("expectNum"));
            dd.setActualNum(rs.getInt("actualNum"));
            dd.setReadDay(rs.getString("readDay").substring(5));
            return dd;
        }
    }

    public class UserAnalysisDTOMapper implements RowMapper {
        public UserAnalysisDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainReportInfo dd = new MainReportInfo();
            dd.setUserCount(rs.getInt("USERCOUNT"));
            dd.setFeeUserCount(rs.getInt("FeeUserCount"));
            dd.setMarkYear(rs.getString("markYear"));
            return dd;
        }
    }

    public class YearfeeAnalysisDTOMapper implements RowMapper {
        public YearfeeAnalysisDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainReportInfo dd = new MainReportInfo();
            dd.setFeeSum(rs.getDouble("payfee"));
            dd.setOperatorName(rs.getString("operatorName"));
            return dd;
        }
    }
}
