//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.DosageDetail;
import com.yinhe.ec.cpps.model.OnOffLogs;
import com.yinhe.ec.cpps.model.SendData;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.ycmeter.dao.WsLogDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class WsLogDaoImpl implements WsLogDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public WsLogDaoImpl() {
    }

    public Integer getTotal(String order) {
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount(" ONOFFLOGS,MeterInfo ", order),Integer.class);
    }

    public List<OnOffLogs> getWsInfo(int page, int rows, String order) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT ONOFFLOGS.* FROM ONOFFLOGS,MeterInfo WHERE " + order + "  ORDER BY ONOFFDATE DESC", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" ONOFFLOGS.* FROM ONOFFLOGS WHERE " + order + " ", page, rows, " ONOFFDATE", "DESC");
        }

        List<OnOffLogs> list = this.jdbcTemplate.query(sql, new WsDTOMapper());
        return list;
    }

    public Integer getTotalSendData(String order) {
        order = order + " and MeterInfo.MeterNo=SendData.MeterNo and (SendData.remark<>'-9' or SendData.remark is null) ";
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount("SendData,MeterInfo", order),Integer.class);
    }

    public List<SendData> getSendDataInfo(int page, int rows, String order) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT UserInfo.UserId,UserInfo.UserName,UserInfo.UserAddr,SendData.*,MeterInfo.keyFlag,MeterInfo.MusterNo,MeterInfo.recNo,MeterInfo.COMPORT,MeterInfo.baud,connInfo.connPara,Muster.MusterPtl,MeterInfo.MeterPtl FROM SendData,MeterInfo,Muster,connInfo,UserInfo,UserDetail WHERE " + order + " and UserDetail.userId=UserInfo.userId and UserDetail.MeterNo=MeterInfo.MeterNo and connInfo.connNo=muster.connNo and muster.MusterNo=meterInfo.musterNo and MeterInfo.MeterNo=SendData.MeterNo and (SendData.remark<>'-9' or SendData.remark is null) ORDER BY SendData.createDate DESC", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" UserInfo.UserId,UserInfo.UserName,UserInfo.UserAddr,SendData.*,MeterInfo.keyFlag,MeterInfo.MusterNo,MeterInfo.recNo,MeterInfo.COMPORT,MeterInfo.baud,connInfo.connPara,Muster.MusterPtl,MeterInfo.MeterPtl FROM SendData,MeterInfo,Muster,connInfo,UserInfo,UserDetail WHERE " + order + " and UserDetail.userId=UserInfo.userId and UserDetail.MeterNo=MeterInfo.MeterNo and connInfo.connNo=muster.connNo and muster.MusterNo=meterInfo.musterNo and MeterInfo.MeterNo=SendData.MeterNo and (SendData.remark<>'-9' or SendData.remark is null) ", page, rows, " SendData.createDate", "DESC");
        }

        List<SendData> list = this.jdbcTemplate.query(sql, new SendDataDTOMapper());
        return list;
    }

    public List<DosageDetail> getUploadListByClause(int page, int rows, String order) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT DOSAGEDETAIL.* FROM DOSAGEDETAIL WHERE " + order + " ORDER BY MARKTIME DESC", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" DOSAGEDETAIL.* FROM DOSAGEDETAIL WHERE " + order + "  ", page, rows, " MARKTIME", "DESC");
        }

        List<DosageDetail> list = this.jdbcTemplate.query(sql, new DosageDetailDTOMapper());
        return list;
    }

    public Integer getDosageDetailTotal(String order) {
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount(" DOSAGEDETAIL ", order),Integer.class);
    }

    public List<SendData> getNbSendDataInfo(String orders) {
        String sql = "select * from senddata where 1=1 " + orders + " order by CreateDate desc";
        return this.jdbcTemplate.query(sql, new NbSendDataDTOMapper());
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
            return dd;
        }
    }

    public class NbSendDataDTOMapper implements RowMapper {
        public NbSendDataDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            SendData ws = new SendData();
            ws.setMeterNo(rs.getString("meterNo"));
            ws.setCustId(rs.getInt("custId"));
            ws.setSendDetail(rs.getString("sendDetail"));
            ws.setSendType(rs.getInt("sendType"));
            ws.setCreateDate(rs.getString("CreateDate"));
            ws.setManageFlag(rs.getInt("manageFlag"));
            ws.setManageDate(rs.getString("ManageDate"));
            ws.setManageCount(rs.getInt("manageCount"));
            ws.setRemark(rs.getString("remark"));
            ws.setsId(rs.getInt("sId"));
            ws.setCreateUser(rs.getString("createUser"));
            return ws;
        }
    }

    public class SendDataDTOMapper implements RowMapper {
        public SendDataDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            SendData ws = new SendData();
            ws.setMeterNo(rs.getString("meterNo"));
            ws.setCustId(rs.getInt("custId"));
            ws.setSendDetail(rs.getString("sendDetail"));
            ws.setSendType(rs.getInt("sendType"));
            ws.setCreateDate(rs.getString("CreateDate"));
            ws.setManageFlag(rs.getInt("manageFlag"));
            ws.setManageDate(rs.getString("ManageDate"));
            ws.setManageCount(rs.getInt("manageCount"));
            ws.setsId(rs.getInt("sId"));
            ws.setMusterNo(rs.getString("MusterNO"));
            ws.setRecNo(rs.getInt("recNo"));
            ws.setComPort(rs.getInt("ComPort"));
            ws.setBaud(rs.getInt("baud"));
            ws.setConnPara(rs.getString("connPara"));
            ws.setMusterPtl(rs.getInt("musterPtl"));
            ws.setMeterPtl(rs.getInt("meterPtl"));
            ws.setKeyFlag(rs.getInt("keyFlag"));
            ws.setUserId(rs.getString("userId"));
            ws.setUserName(rs.getString("userName"));
            ws.setUserAddr(rs.getString("userAddr"));
            ws.setCreateUser(rs.getString("createUser"));
            ws.setRemark(rs.getString("remark"));
            return ws;
        }
    }

    public class WsDTOMapper implements RowMapper {
        public WsDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            OnOffLogs ws = new OnOffLogs();
            ws.setMeterNo(rs.getString("meterNo"));
            ws.setCustId(rs.getInt("custId"));
            ws.setSuccFlag(rs.getInt("succFlag"));
            ws.setOnOffFlag(rs.getInt("onOffFlag"));
            ws.setOnOffDate(rs.getString("onOffDate"));
            ws.setOnOffUser(rs.getString("onOffUser"));
            ws.setOnOffId(rs.getInt("onOffId"));
            return ws;
        }
    }
}
