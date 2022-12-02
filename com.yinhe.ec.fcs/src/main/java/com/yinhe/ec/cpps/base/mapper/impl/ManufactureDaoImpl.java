//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.mapper.impl;

import com.yinhe.ec.cpps.base.dao.ManufactureDao;
import com.yinhe.ec.cpps.model.Manufacture;
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
public class ManufactureDaoImpl implements ManufactureDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public ManufactureDaoImpl() {
    }

    public void addManufacture(final Manufacture manufacture) throws DataAccessException {
        String isql = "insert into MANUFACTURE(manuId,manuName,remark,manuUser,manuPhone,custId) values (?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, manufacture.getManuId());
                ps.setString(2, manufacture.getManuName());
                ps.setString(3, manufacture.getRemark());
                ps.setString(4, manufacture.getManuUser());
                ps.setString(5, manufacture.getManuPhone());
                ps.setInt(6, manufacture.getCustId());
            }
        });
    }

    public void delManufacture(int manuId) throws DataAccessException {
        String sql = "delete from MANUFACTURE where manuId=" + manuId;
        this.jdbcTemplate.update(sql);
    }

    public List<Manufacture> getManufacture(int custId) {
        String sql = "select * from MANUFACTURE where custId=" + custId + " order by manuId";
        return this.jdbcTemplate.query(sql, new ManufactureDTO());
    }

    public Manufacture getManufactureById(int manuId) {
        String sql = "select * from MANUFACTURE where manuId=" + manuId + " order by manuId";
        return (Manufacture)this.jdbcTemplate.queryForObject(sql, new ManufactureDTO());
    }

    public void modManufacture(final Manufacture manufacture) throws DataAccessException {
        String isql = "update MANUFACTURE set manuName=?,remark=?,manuUser=?,manuPhone=? where manuId=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, manufacture.getManuName());
                ps.setString(2, manufacture.getRemark());
                ps.setString(3, manufacture.getManuUser());
                ps.setString(4, manufacture.getManuPhone());
                ps.setInt(5, manufacture.getManuId());
            }
        });
    }

    public class ManufactureDTO implements RowMapper {
        public ManufactureDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Manufacture dto = new Manufacture();
            dto.setManuId(rs.getInt("manuId"));
            dto.setManuName(rs.getString("manuName"));
            dto.setManuUser(rs.getString("manuUser"));
            dto.setManuPhone(rs.getString("manuPhone"));
            dto.setRemark(rs.getString("remark"));
            dto.setCustId(rs.getInt("custId"));
            return dto;
        }
    }
}
