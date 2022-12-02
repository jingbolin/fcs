//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.MeterChange;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.ycmeter.dao.ChangeMInfoDao;
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
public class ChangeMInfoImpl implements ChangeMInfoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public ChangeMInfoImpl() {
    }

    public List<MeterChange> getChangeMInfo(int page, int rows, String order) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT USERINFOVIEW.CUSTID,USERINFOVIEW.USERID,USERINFOVIEW.USERNAME,USERINFOVIEW.USERADDR,USERINFOVIEW.MUSTERNO,USERINFOVIEW.AREANAME,USERINFOVIEW.MANUNAME, METERCHANGE.METERNO,METERCHANGE.CHANGEDATE,METERCHANGE.CHANGEUSER,METERCHANGE.REASON,METERCHANGE.PREMETERNO,dynameter*METERCHANGE.ENDDOSAGESUM as ENDDOSAGESUM,dynameter*METERCHANGE.STARTDOSAGESUM as STARTDOSAGESUM,dynameter*METERCHANGE.V1 as V1,dynameter*METERCHANGE.V2 as V2,dynameter*METERCHANGE.V3 as V3,dynameter*METERCHANGE.V4 as V4,dynameter*METERCHANGE.NV1 as NV1,dynameter*METERCHANGE.NV2 as NV2,dynameter*METERCHANGE.NV3 as NV3,dynameter*METERCHANGE.NV4 as NV4,METERCHANGE.oldLeftsum,METERCHANGE.allSum,METERCHANGE.remark  FROM USERINFOVIEW,METERCHANGE  WHERE METERCHANGE.MeterNo=USERINFOVIEW.MeterNo " + order + " ORDER BY METERCHANGE.CHANGEDATE ASC", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" USERINFOVIEW.CUSTID,USERINFOVIEW.USERID,USERINFOVIEW.USERNAME,USERINFOVIEW.USERADDR,USERINFOVIEW.MUSTERNO,USERINFOVIEW.AREANAME,USERINFOVIEW.MANUNAME, METERCHANGE.METERNO,METERCHANGE.CHANGEDATE,METERCHANGE.CHANGEUSER,METERCHANGE.REASON,METERCHANGE.PREMETERNO,dynameter*METERCHANGE.ENDDOSAGESUM as ENDDOSAGESUM,dynameter*METERCHANGE.STARTDOSAGESUM as STARTDOSAGESUM,dynameter*METERCHANGE.V1 as V1,dynameter*METERCHANGE.V2 as V2,dynameter*METERCHANGE.V3 as V3,dynameter*METERCHANGE.V4 as V4,dynameter*METERCHANGE.NV1 as NV1,dynameter*METERCHANGE.NV2 as NV2,dynameter*METERCHANGE.NV3 as NV3,dynameter*METERCHANGE.NV4 as NV4,METERCHANGE.oldLeftsum,METERCHANGE.allSum,METERCHANGE.remark  FROM USERINFOVIEW,METERCHANGE  WHERE METERCHANGE.MeterNo=USERINFOVIEW.MeterNo " + order + " ", page, rows, "CHANGEMETER.CHANGEDATE", "asc");
        }

        List<MeterChange> list = this.jdbcTemplate.query(sql, new CMDTOMapper());
        return list;
    }

    public Integer getTotal(String order) {
        order = " USERINFOVIEW.METERNO=METERCHANGE.METERNO " + order;
        return this.jdbcTemplate.queryForObject(CommonSQL.searchCount(" USERINFOVIEW,METERCHANGE ", order),Integer.class);
    }

    public class CMDTOMapper implements RowMapper {
        public CMDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterChange cm = new MeterChange();
            cm.setUserId(rs.getString("userId"));
            cm.setUserName(rs.getString("userName"));
            cm.setUserAddr(rs.getString("userAddr"));
            cm.setAreaName(rs.getString("areaName"));
            cm.setMeterNo(rs.getString("meterNo"));
            cm.setMusterNo(rs.getString("musterNo"));
            cm.setChangeDate(rs.getString("changeDate"));
            cm.setChangeUser(rs.getString("changeUser"));
            cm.setReason(rs.getString("reason"));
            cm.setPreMeterNo(rs.getString("preMeterNo"));
            cm.setEndDosageSum(rs.getDouble("endDosageSum"));
            cm.setStartDosageSum(rs.getDouble("startDosageSum"));
            cm.setManuName(rs.getString("manuName"));
            cm.setV1(rs.getDouble("v1"));
            cm.setV2(rs.getDouble("v2"));
            cm.setV3(rs.getDouble("v3"));
            cm.setV4(rs.getDouble("v4"));
            cm.setNv1(rs.getDouble("nv1"));
            cm.setNv2(rs.getDouble("nv2"));
            cm.setNv3(rs.getDouble("nv3"));
            cm.setNv4(rs.getDouble("nv4"));
            cm.setOldLeftsum(rs.getDouble("oldLeftsum"));
            cm.setAllSum(rs.getDouble("allSum"));
            cm.setRemark(rs.getString("remark"));
            return cm;
        }
    }
}
