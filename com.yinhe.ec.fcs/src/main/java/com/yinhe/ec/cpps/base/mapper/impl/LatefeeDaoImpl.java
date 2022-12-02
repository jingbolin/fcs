//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.dao.impl;

import com.yinhe.ec.cpps.base.dao.LatefeeDao;
import com.yinhe.ec.cpps.model.LateFeeList;
import org.apache.shiro.dao.DataAccessException;
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
public class LatefeeDaoImpl implements LatefeeDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public LatefeeDaoImpl() {
    }

    public List<LateFeeList> getLateFeeListByCustId(int custId, String orders) {
//        String sql = "select * from (select LATEFEELIST.*,AREAINFO.areaName from LATEFEELIST left join AREAINFO on AREAINFO.areaId=LATEFEELIST.areaId ) where custId=" + custId + " " + orders + " order by areaId,lateFeeId";
        String sql = "select * from (select LATEFEELIST.*,AREAINFO.areaName from LATEFEELIST left join AREAINFO on AREAINFO.areaId=LATEFEELIST.areaId ) a where custId=" + custId + " order by areaId,lateFeeId";
        return this.jdbcTemplate.query(sql, new LateFeeListDTO());
    }

    public LateFeeList getLateFeeListById(String lateFeeId) {
        String sql = "select * from (select LATEFEELIST.*,AREAINFO.areaName from LATEFEELIST left join AREAINFO on AREAINFO.areaId=LATEFEELIST.areaId) a where lateFeeId='" + lateFeeId + "'";
        List<LateFeeList> list = this.jdbcTemplate.query(sql, new LateFeeListDTO());
        return list.size() > 0 ? (LateFeeList)list.get(0) : new LateFeeList();
    }

    public void addLateFeeList(final LateFeeList lateFeeList) throws DataAccessException {
        String sql = "insert into LATEFEELIST(custId,latefeeId,latefeeName,latefeeType,latefeeRatio,onlineLatefeeRatio,latefeeDesc,easCode,areaId,createDate,lateDays) values (?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, lateFeeList.getCustId());
                ps.setString(2, lateFeeList.getLateFeeId());
                ps.setString(3, lateFeeList.getLateFeeName());
                ps.setInt(4, lateFeeList.getLateFeeType());
                ps.setDouble(5, lateFeeList.getLateFeeRatio());
                ps.setDouble(6, lateFeeList.getOnlineLateFeeRatio());
                ps.setString(7, lateFeeList.getLateFeeDesc());
                ps.setString(8, lateFeeList.getEasCode());
                ps.setString(9, lateFeeList.getAreaId());
                ps.setString(10, lateFeeList.getCreateDate());
                ps.setInt(11, lateFeeList.getLateDays());
            }
        });
    }

    public void modLateFeeList(final LateFeeList lateFeeList) throws DataAccessException {
        String sql = "update LATEFEELIST set latefeeName=?,latefeeType=?,latefeeRatio=?,onlineLatefeeRatio=?,latefeeDesc=?,easCode=?,areaId=?,lateDays=? where latefeeId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, lateFeeList.getLateFeeName());
                ps.setInt(2, lateFeeList.getLateFeeType());
                ps.setDouble(3, lateFeeList.getLateFeeRatio());
                ps.setDouble(4, lateFeeList.getOnlineLateFeeRatio());
                ps.setString(5, lateFeeList.getLateFeeDesc());
                ps.setString(6, lateFeeList.getEasCode());
                ps.setString(7, lateFeeList.getAreaId());
                ps.setInt(8, lateFeeList.getLateDays());
                ps.setString(9, lateFeeList.getLateFeeId());
            }
        });
    }

    public void delLateFeeListById(String lateFeeId) throws DataAccessException {
        String sql = "delete from LATEFEELIST where lateFeeId='" + lateFeeId + "'";
        this.jdbcTemplate.update(sql);
    }

    public boolean getLateFeeIsused(String lateFeeId) {
        return (Integer)this.jdbcTemplate.queryForObject("select count(*) from FEEITEMLIST where lateFeeId='" + lateFeeId + "'", Integer.class) > 0;
    }

    public class LateFeeListDTO implements RowMapper {
        public LateFeeListDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            LateFeeList dto = new LateFeeList();
            dto.setAreaId(rs.getString("areaId"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setLateFeeId(rs.getString("lateFeeId"));
            dto.setLateFeeName(rs.getString("lateFeeName"));
            dto.setLateFeeType(rs.getInt("lateFeeType"));
            dto.setLateFeeRatio(rs.getDouble("lateFeeRatio"));
            dto.setOnlineLateFeeRatio(rs.getDouble("onlineLateFeeRatio"));
            dto.setLateFeeDesc(rs.getString("lateFeeDesc"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setCustId(rs.getInt("custId"));
            dto.setEasCode(rs.getString("easCode"));
            dto.setLateDays(rs.getInt("lateDays"));
            return dto;
        }
    }
}
