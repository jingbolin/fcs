//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.model.BaseParam;
import com.yinhe.ec.cpps.model.MeterType;
import com.yinhe.ec.cpps.system.dao.BaseParamDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BaseParamDaoimpl implements BaseParamDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public BaseParamDaoimpl() {
    }

    @Override
    public void EditBaseParam(BaseParam sps) throws DataAccessException {
        String sql = "update BASEPARAM set userIdCode=?, genMDosageDay=?, genMDosageTime=?, calculateDay=?, calculateTime=?, mfrpDay=?, chargeFlag=?,  dosageDot=?, feeDot=?,preUserId=?,Type1=?,Type2=?,Type3=?,Type4=?,Type5=?,Type6=?,Type7=?,TotalLen=? where CustId = ?";
        this.jdbcTemplate.update(sql, new Object[]{sps.getUserIdCode(), sps.getGenMDosageDay(), sps.getGenMDosageTime(), sps.getCalculateDay(), sps.getCalculateTime(), sps.getMfrpDay(), sps.getChargeFlag(), sps.getDosageDot(), sps.getFeeDot(), sps.getPreUserId(), sps.getType1(), sps.getType2(), sps.getType3(), sps.getType4(), sps.getType5(), sps.getType6(), sps.getType7(), sps.getTotalLen(), sps.getCustId()});
    }

    @Override
    public BaseParam getBaseParam(int custId) {
        String sql = "select a.*,b.CustNo,b.CustName from BASEPARAM a, CUSTOMER b where b.CustId=a.CustId and a.custId=" + custId;
        return (BaseParam)this.jdbcTemplate.queryForObject(sql, new BaseParamMapper());
    }

    @Override
    public List<BaseParam> getBaseParamList(int custId) {
        String sql = "";
        sql = "select a.*,b.CustNo,b.custName from BASEPARAM a, CUSTOMER b where b.CustId=a.CustId and a.CustId='" + custId + "'";
        return this.jdbcTemplate.query(sql, new BaseParamMapper());
    }

    @Override
    public void AddBaseParam(final BaseParam sps) throws DataAccessException {
        String isql = "insert into BASEPARAM(CustId,UserIdCode,GenMDosageDay,GenMDosageTime,CalculateDay,CalculateTime,MfrpDay,ChargeFlag,DosageDot,FeeDot,PreBakYear,preUserId,Type1,Type2,Type3,Type4,Type5,Type6,Type7,TotalLen) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, sps.getCustId());
                ps.setInt(2, sps.getUserIdCode());
                ps.setInt(3, sps.getGenMDosageDay());
                ps.setString(4, sps.getGenMDosageTime());
                ps.setInt(5, sps.getCalculateDay());
                ps.setString(6, sps.getCalculateTime());
                ps.setInt(7, sps.getMfrpDay());
                ps.setInt(8, sps.getChargeFlag());
                ps.setInt(9, sps.getDosageDot());
                ps.setInt(10, sps.getFeeDot());
                ps.setInt(11, sps.getPreBakYear());
                ps.setString(12, sps.getPreUserId());
                ps.setInt(13, sps.getType1());
                ps.setInt(14, sps.getType2());
                ps.setInt(15, sps.getType3());
                ps.setInt(16, sps.getType4());
                ps.setInt(17, sps.getType5());
                ps.setInt(18, sps.getType6());
                ps.setInt(19, sps.getType7());
                ps.setInt(20, sps.getTotalLen());
            }
        });
    }

    @Override
    public void DelBaseParam(int custId) throws DataAccessException {
        this.jdbcTemplate.update("delete from BASEPARAM where CustId=" + custId);
    }

    @Override
    public List<MeterType> getMeterType(int custId) {
        new BaseParam();
        BaseParam baseParam = this.getBaseParam(custId);
        List<MeterType> list = new ArrayList();
        if (baseParam != null) {
            new MeterType();
            MeterType meterType;
            if (baseParam.getType1() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(1);
                meterType.setTypeName("远传电表");
                list.add(meterType);
            }

            if (baseParam.getType2() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(2);
                meterType.setTypeName("远传水表");
                list.add(meterType);
            }

            if (baseParam.getType3() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(3);
                meterType.setTypeName("远传热表");
                list.add(meterType);
            }
        }

        return list;
    }

    @Override
    public List<MeterType> getUsedMeterTypes(int custId) {
        new BaseParam();
        BaseParam baseParam = this.getBaseParam(custId);
        List<MeterType> list = new ArrayList();
        if (baseParam != null) {
            new MeterType();
            MeterType meterType;
            if (baseParam.getType1() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(1);
                meterType.setTypeName("YcEleFlag");
                list.add(meterType);
            }

            if (baseParam.getType2() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(2);
                meterType.setTypeName("YcWaterFlag");
                list.add(meterType);
            }

            if (baseParam.getType3() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(3);
                meterType.setTypeName("YcHotFlag");
                list.add(meterType);
            }

            if (baseParam.getType6() == 1) {
                meterType = new MeterType();
                meterType.setTypeId(6);
                meterType.setTypeName("WyFeeFlag");
                list.add(meterType);
            }
        }

        return list;
    }

    public class BaseParamMapper implements RowMapper<BaseParam> {
        public BaseParamMapper() {
        }
        @Override
        public BaseParam mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseParam sps = new BaseParam();
            sps.setCustId(rs.getInt("custId"));
            sps.setUserIdCode(rs.getInt("userIdCode"));
            sps.setGenMDosageDay(rs.getInt("genMDosageDay"));
            sps.setGenMDosageTime(rs.getString("genMDosageTime"));
            sps.setCalculateDay(rs.getInt("calculateDay"));
            sps.setCalculateTime(rs.getString("calculateTime"));
            sps.setMfrpDay(rs.getInt("mfrpDay"));
            sps.setChargeFlag(rs.getInt("chargeFlag"));
            sps.setDosageDot(rs.getInt("dosageDot"));
            sps.setFeeDot(rs.getInt("feeDot"));
            sps.setPreBakYear(rs.getInt("preBakYear"));
            sps.setPreUserId(rs.getString("preUserId"));
            sps.setCustNo(rs.getString("custNo"));
            sps.setCustName(rs.getString("custName"));
            sps.setType1(rs.getInt("type1"));
            sps.setType2(rs.getInt("type2"));
            sps.setType3(rs.getInt("type3"));
            sps.setType4(rs.getInt("type4"));
            sps.setType5(rs.getInt("type5"));
            sps.setType6(rs.getInt("type6"));
            sps.setType7(rs.getInt("type7"));
            sps.setTotalLen(rs.getInt("totalLen"));
            return sps;
        }
    }
}
