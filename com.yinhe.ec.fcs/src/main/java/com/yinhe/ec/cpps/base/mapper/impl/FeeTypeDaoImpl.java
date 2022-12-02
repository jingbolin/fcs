//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.dao.impl;

import com.yinhe.ec.cpps.base.dao.FeeTypeDao;
import com.yinhe.ec.cpps.model.FeeType;
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
public class FeeTypeDaoImpl implements FeeTypeDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public FeeTypeDaoImpl() {
    }

    @Override
    public void addFeeType(final FeeType feeType) throws DataAccessException {
        String sql = "INSERT INTO FEETYPE(FEETYPEID,FEETYPECODE,FEETYPENAME,FEETYPEMARK,REMARK) VALUES (?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, feeType.getFeeTypeId());
                ps.setString(2, feeType.getFeeTypeCode());
                ps.setString(3, feeType.getFeeTypeName());
                ps.setInt(4, feeType.getFeeTypeMark());
                ps.setString(5, feeType.getRemark());
            }
        });
    }

    @Override
    public void delFeeTypeById(String feeTypeId) throws DataAccessException {
        this.jdbcTemplate.update("DELETE FROM FEETYPE WHERE FEETYPEID='" + feeTypeId + "'");
    }

    @Override
    public void editFeeType(final FeeType feeType) throws DataAccessException {
        String sql = "UPDATE FEETYPE SET FEETYPECODE=?,FEETYPENAME=?,FEETYPEMARK=?,REMARK=? WHERE FEETYPEID=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, feeType.getFeeTypeCode());
                ps.setString(2, feeType.getFeeTypeName());
                ps.setInt(3, feeType.getFeeTypeMark());
                ps.setString(4, feeType.getRemark());
                ps.setString(5, feeType.getFeeTypeId());
            }
        });
    }

    @Override
    public List<FeeType> getAllFeeType() {
        String sql = "select * from FEETYPE order by FEETYPEID";
        return this.jdbcTemplate.query(sql, new FeeTypeDTO());
    }

    @Override
    public FeeType getFeeType(String feeTypeId) {
        String sql = "select * from FEETYPE where feeTypeId='" + feeTypeId + "'";
        List<FeeType> list = this.jdbcTemplate.query(sql, new FeeTypeDTO());
        return list.size() > 0 ? (FeeType)list.get(0) : new FeeType();
    }

    @Override
    public boolean getFeeTypeIsused(String feeTypeId) {
        return this.jdbcTemplate.queryForList("select * from FEEITEMLIST where feeTypeId='" + feeTypeId + "'").size() > 0;
    }

    public class FeeTypeDTO implements RowMapper {
        public FeeTypeDTO() {
        }

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeeType dto = new FeeType();
            dto.setFeeTypeId(rs.getString("feeTypeId"));
            dto.setFeeTypeName(rs.getString("feeTypeCode") + ":" + rs.getString("feeTypeName"));
            dto.setFeeTypeCode(rs.getString("feeTypeCode"));
            dto.setFeeTypeMark(rs.getInt("feeTypeMark"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
