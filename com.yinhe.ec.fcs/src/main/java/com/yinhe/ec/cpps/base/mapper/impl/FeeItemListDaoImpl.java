//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.dao.impl;

import com.yinhe.ec.cpps.base.dao.FeeItemListDao;
import com.yinhe.ec.cpps.base.dao.FeeTypeDao;
import com.yinhe.ec.cpps.model.FeeItemList;
import com.yinhe.ec.cpps.model.PayWays;
import com.yinhe.ec.cpps.model.StepPriceList;
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
import java.util.List;

@Repository
@Transactional
public class FeeItemListDaoImpl implements FeeItemListDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private FeeTypeDao feeTypeDao;

    public FeeItemListDaoImpl() {
    }

    @Override
    public void addFeeItemList(final FeeItemList feeItemList) throws DataAccessException {
        String sql = "insert into FEEITEMLIST(custId,feeItemId,feeItemName,chargeMethod,feeTypeId,price,areaPrice,baseFee,price1,price2,price3,stepPriceId,mixPriceId,createDate,remark,areaId,remark1,lateFeeId,allowpay,paymentMode,cycleType,cycleStartDate,cycleEndDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, feeItemList.getCustId());
                ps.setString(2, feeItemList.getFeeItemId());
                ps.setString(3, feeItemList.getFeeItemName());
                ps.setInt(4, feeItemList.getChargeMethod());
                ps.setString(5, feeItemList.getFeeTypeId());
                ps.setDouble(6, feeItemList.getPrice());
                ps.setDouble(7, feeItemList.getAreaPrice());
                ps.setDouble(8, feeItemList.getBaseFee());
                ps.setDouble(9, feeItemList.getPrice1());
                ps.setDouble(10, feeItemList.getPrice2());
                ps.setDouble(11, feeItemList.getPrice3());
                ps.setString(12, feeItemList.getStepPriceId());
                ps.setString(13, feeItemList.getMixPriceId());
                ps.setString(14, feeItemList.getCreateDate());
                ps.setString(15, feeItemList.getRemark());
                ps.setString(16, feeItemList.getAreaId());
                ps.setString(17, feeItemList.getRemark1());
                ps.setString(18, feeItemList.getLateFeeId());
                ps.setInt(19, feeItemList.getAllowpay());
                ps.setInt(20, feeItemList.getPaymentMode());
                ps.setInt(21, feeItemList.getCycleType());
                ps.setString(22, feeItemList.getCycleStartDate());
                ps.setString(23, feeItemList.getCycleEndDate());
            }
        });
    }

    @Override
    public void addStepPriceList(final StepPriceList stepPriceList) throws DataAccessException {
        String sql = "insert into STEPPRICELIST(custId,stepPriceId,stepStyle,step1Price,step1dosage,step2Price,step2dosage,step3Price,step3dosage,step4Price,remark) values (?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, stepPriceList.getCustId());
                ps.setString(2, stepPriceList.getStepPriceId());
                ps.setInt(3, stepPriceList.getStepStyle());
                ps.setDouble(4, stepPriceList.getStep1Price());
                ps.setDouble(5, (double)stepPriceList.getStep1Dosage());
                ps.setDouble(6, stepPriceList.getStep2Price());
                ps.setDouble(7, (double)stepPriceList.getStep2Dosage());
                ps.setDouble(8, stepPriceList.getStep3Price());
                ps.setDouble(9, (double)stepPriceList.getStep3Dosage());
                ps.setDouble(10, stepPriceList.getStep4Price());
                ps.setString(11, stepPriceList.getRemark());
            }
        });
    }

    @Override
    public boolean getFeeItemIsused(String feeItemId) {
        return this.jdbcTemplate.queryForList("SELECT * FROM (select FeeItemId from USERDETAIL where FeeItemId='" + feeItemId + "'" + " UNION " + "select WYFEEITEMID FROM USERFUNDDETAIL where WYFEEITEMID='" + feeItemId + "'" + " UNION " + "SELECT NQFEEITEMID FROM USERFUNDDETAIL where NQFEEITEMID='" + feeItemId + "'" + " UNION " + "SELECT FZFEEITEMID FROM USERFUNDDETAIL where FZFEEITEMID='" + feeItemId + "'" + ") ").size() > 0;
    }

    @Override
    public void delFeeItemListById(String feeItemId) throws DataAccessException {
        String sql = "delete from FEEITEMLIST where feeItemId='" + feeItemId + "'";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public void delStepPriceList(String stepId) throws DataAccessException {
        String sql = "delete from STEPPRICELIST where stepPriceId='" + stepId + "'";
        this.jdbcTemplate.update(sql);
    }

    @Override
    public void editFeeItemList(final FeeItemList feeItemList) throws DataAccessException {
        String sql = "";
        if (this.getFeeItemIsused(feeItemList.getFeeItemId())) {
            sql = "update FEEITEMLIST set feeItemName=?,remark=?,areaId=?,remark1=?,lateFeeId=?,allowpay=?,paymentMode=? where feeItemId=?";
            this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, feeItemList.getFeeItemName());
                    ps.setString(2, feeItemList.getRemark());
                    ps.setString(3, feeItemList.getAreaId());
                    ps.setString(4, feeItemList.getRemark1());
                    ps.setString(5, feeItemList.getLateFeeId());
                    ps.setInt(6, feeItemList.getAllowpay());
                    ps.setInt(7, feeItemList.getPaymentMode());
                    ps.setString(8, feeItemList.getFeeItemId());
                }
            });
        } else {
            sql = "update FEEITEMLIST set feeItemName=?,chargeMethod=?,feeTypeId=?,price=?,areaPrice=?,baseFee=?,price1=?,price2=?,price3=?,stepPriceId=?,mixPriceId=?,remark=?,areaId=?,remark1=?,lateFeeId=?,allowpay=?,paymentMode=?,cycleType=?,cycleStartDate=?,cycleEndDate=? where feeItemId=?";
            this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, feeItemList.getFeeItemName());
                    ps.setInt(2, feeItemList.getChargeMethod());
                    ps.setString(3, feeItemList.getFeeTypeId());
                    ps.setDouble(4, feeItemList.getPrice());
                    ps.setDouble(5, feeItemList.getAreaPrice());
                    ps.setDouble(6, feeItemList.getBaseFee());
                    ps.setDouble(7, feeItemList.getPrice1());
                    ps.setDouble(8, feeItemList.getPrice2());
                    ps.setDouble(9, feeItemList.getPrice3());
                    ps.setString(10, feeItemList.getStepPriceId());
                    ps.setString(11, feeItemList.getMixPriceId());
                    ps.setString(12, feeItemList.getRemark());
                    ps.setString(13, feeItemList.getAreaId());
                    ps.setString(14, feeItemList.getRemark1());
                    ps.setString(15, feeItemList.getLateFeeId());
                    ps.setInt(16, feeItemList.getAllowpay());
                    ps.setInt(17, feeItemList.getPaymentMode());
                    ps.setInt(18, feeItemList.getCycleType());
                    ps.setString(19, feeItemList.getCycleStartDate());
                    ps.setString(20, feeItemList.getCycleEndDate());
                    ps.setString(21, feeItemList.getFeeItemId());
                }
            });
        }

    }

    @Override
    public void editStepPriceList(final StepPriceList stepPriceList) throws DataAccessException {
        String sql = "update STEPPRICELIST set stepStyle=?,step1Price=?,step1dosage=?,step2Price=?,step2dosage=?,step3Price=?,step3dosage=?,step4Price=?,remark=? where stepPriceId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, stepPriceList.getStepStyle());
                ps.setDouble(2, stepPriceList.getStep1Price());
                ps.setDouble(3, (double)stepPriceList.getStep1Dosage());
                ps.setDouble(4, stepPriceList.getStep2Price());
                ps.setDouble(5, (double)stepPriceList.getStep2Dosage());
                ps.setDouble(6, stepPriceList.getStep3Price());
                ps.setDouble(7, (double)stepPriceList.getStep3Dosage());
                ps.setDouble(8, stepPriceList.getStep4Price());
                ps.setString(9, stepPriceList.getRemark());
                ps.setString(10, stepPriceList.getStepPriceId());
            }
        });
    }

    @Override
    public FeeItemList getFeeItemList(String feeItemId) {
        String sql = "select FEEITEMLIST.*,AREAINFO.areaName,LATEFEELIST.Latefeename  from FEEITEMLIST left join AREAINFO on AREAINFO.areaid=FEEITEMLIST.areaId left join LATEFEELIST on LATEFEELIST.lateFeeid=FEEITEMLIST.lateFeeId  where FeeItemId='" + feeItemId + "'";
        List<FeeItemList> list = this.jdbcTemplate.query(sql, new FeeItemDTO());
        return list.size() > 0 ? (FeeItemList)list.get(0) : new FeeItemList();
    }

    @Override
    public List<FeeItemList> getFeeItemListByCustId(int custId, String orders) {
        String sql = "select * from (select FEEITEMLIST.*,AREAINFO.areaName,LATEFEELIST.Latefeename  from FEEITEMLIST left join AREAINFO on AREAINFO.areaid=FEEITEMLIST.areaId left join LATEFEELIST on LATEFEELIST.lateFeeid=FEEITEMLIST.lateFeeId)t  where 1=1 " + orders + " and custId=" + custId + " order by AreaId,feeItemId";
        return this.jdbcTemplate.query(sql, new FeeItemDTO());
    }

    @Override
    public List<StepPriceList> getStepPriceList(int custId) {
        String sql = "select * from STEPPRICELIST where custId=" + custId + " order by stepPriceId";
        return this.jdbcTemplate.query(sql, new StepPriceDTO());
    }

    @Override
    public StepPriceList getStepPrice(String stepId) {
        String sql = "select * from STEPPRICELIST where stepPriceId='" + stepId + "'";
        List<StepPriceList> list = this.jdbcTemplate.query(sql, new StepPriceDTO());
        return list.size() > 0 ? (StepPriceList)list.get(0) : new StepPriceList();
    }

    @Override
    public boolean getStepPriceIsused(String stepId) {
        return this.jdbcTemplate.queryForList("select * from FEEITEMLIST where stepPriceId='" + stepId + "'").size() > 0;
    }

    @Override
    public List<PayWays> getPayWays() {
        String sql = "select payKind,payKindName,isUse,imgUrl from PAYWAYS where isUse=0 order by payKind";
        return this.jdbcTemplate.query(sql, new PayWaysDTO());
    }

    public class FeeItemDTO implements RowMapper {
        public FeeItemDTO() {
        }

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeeItemList dto = new FeeItemList();
            dto.setCustId(rs.getInt("custId"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setFeeItemName(rs.getString("feeItemName"));
            dto.setChargeMethod(rs.getInt("chargeMethod"));
            dto.setFeeTypeId(rs.getString("feeTypeId"));
            dto.setPrice(rs.getDouble("price"));
            dto.setAreaPrice(rs.getDouble("areaPrice"));
            dto.setBaseFee(rs.getDouble("baseFee"));
            dto.setPrice1(rs.getDouble("price1"));
            dto.setPrice2(rs.getDouble("price2"));
            dto.setPrice3(rs.getDouble("price3"));
            dto.setStepPriceId(rs.getString("stepPriceId"));
            dto.setMixPriceId(rs.getString("mixPriceId"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setRemark(rs.getString("remark"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setRemark1(rs.getString("remark1"));
            dto.setLateFeeId(rs.getString("lateFeeId"));
            dto.setLateFeeName(rs.getString("lateFeeName"));
            dto.setFeeType(FeeItemListDaoImpl.this.feeTypeDao.getFeeType(rs.getString("feeTypeId")));
            dto.setStepPrice(FeeItemListDaoImpl.this.getStepPrice(rs.getString("stepPriceId")));
            dto.setAllowpay(rs.getInt("allowpay"));
            dto.setPaymentMode(rs.getInt("paymentMode"));
            dto.setCycleType(rs.getInt("cycleType"));
            dto.setCycleStartDate(rs.getString("cycleStartDate"));
            dto.setCycleEndDate(rs.getString("cycleEndDate"));
            return dto;
        }
    }

    public class PayWaysDTO implements RowMapper {
        public PayWaysDTO() {
        }

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            PayWays dto = new PayWays();
            dto.setPayKind(rs.getInt("payKind"));
            dto.setPayKindName(rs.getString("payKindName"));
            dto.setImgUrl(rs.getString("imgUrl"));
            dto.setIsUse(rs.getInt("isUse"));
            return dto;
        }
    }

    public class StepPriceDTO implements RowMapper {
        public StepPriceDTO() {
        }

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            StepPriceList dto = new StepPriceList();
            dto.setCustId(rs.getInt("custId"));
            dto.setStepPriceId(rs.getString("stepPriceId"));
            dto.setStepStyle(rs.getInt("stepStyle"));
            dto.setStep1Price(rs.getDouble("step1Price"));
            dto.setStep1Dosage(rs.getInt("step1Dosage"));
            dto.setStep2Price(rs.getDouble("step2Price"));
            dto.setStep2Dosage(rs.getInt("step2Dosage"));
            dto.setStep3Price(rs.getDouble("step3Price"));
            dto.setStep3Dosage(rs.getInt("step3Dosage"));
            dto.setStep4Price(rs.getDouble("step4Price"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
