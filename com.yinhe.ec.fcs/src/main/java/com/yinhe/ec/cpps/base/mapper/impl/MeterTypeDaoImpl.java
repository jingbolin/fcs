//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.dao.impl;

import com.yinhe.ec.cpps.base.dao.MeterTypeDao;
import com.yinhe.ec.cpps.model.MeterType;
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
public class MeterTypeDaoImpl implements MeterTypeDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public MeterTypeDaoImpl() {
    }

    public void addMeterType(final MeterType meterType) throws DataAccessException {
        String isql = "INSERT INTO METERTYPE(TYPEID,TYPENAME,USEFLAG,CUSTID,REMARK) VALUES (?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, meterType.getTypeId());
                ps.setString(2, meterType.getTypeName());
                ps.setInt(3, meterType.getUseFlag());
                ps.setInt(4, meterType.getCustId());
                ps.setString(5, meterType.getRemark());
            }
        });
    }

    public void delMeterTypeById(int typeId) throws DataAccessException {
        String sql = "delete from METERTYPE where typeId=" + typeId;
        this.jdbcTemplate.update(sql);
    }

    public void editMeterType(final MeterType meterType) throws DataAccessException {
        String isql = "UPDATE METERTYPE SET TYPENAME=?,USEFLAG=?,REMARK=? WHERE TYPEID=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, meterType.getTypeName());
                ps.setInt(2, meterType.getUseFlag());
                ps.setString(3, meterType.getRemark());
                ps.setInt(4, meterType.getTypeId());
            }
        });
    }

    public MeterType getMeterType(int typeId) {
        String sql = "select * from meterType where typeId=" + typeId;
        return (MeterType)this.jdbcTemplate.queryForObject(sql, new MeterTypeDTO());
    }

    public List<MeterType> getMeterTypeByCustId(int custId) {
        String sql = "select * from meterType where custId=" + custId + " order by typeId";
        return this.jdbcTemplate.query(sql, new MeterTypeDTO());
    }

    public class MeterTypeDTO implements RowMapper {
        public MeterTypeDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterType dto = new MeterType();
            dto.setTypeId(rs.getInt("typeId"));
            dto.setCustId(rs.getInt("custId"));
            dto.setTypeName(rs.getString("typeName"));
            dto.setUseFlag(rs.getInt("useFlag"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
