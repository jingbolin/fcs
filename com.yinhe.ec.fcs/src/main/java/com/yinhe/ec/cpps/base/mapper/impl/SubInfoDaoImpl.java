//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.mapper.impl;

import com.yinhe.ec.cpps.base.dao.SubInfoDao;
import com.yinhe.ec.cpps.model.SubInfo;
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
public class SubInfoDaoImpl implements SubInfoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public SubInfoDaoImpl() {
    }

    public void addSubInfo(final SubInfo subInfo) throws DataAccessException {
        String sql = "insert into SUBINFO(subNo,custId,subName,subAddr,subDate,subUser,subPhone,remark) values (?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, subInfo.getSubNo());
                ps.setInt(2, subInfo.getCustId());
                ps.setString(3, subInfo.getSubName());
                ps.setString(4, subInfo.getSubAddr());
                ps.setString(5, subInfo.getSubDate());
                ps.setString(6, subInfo.getSubUser());
                ps.setString(7, subInfo.getSubPhone());
                ps.setString(8, subInfo.getRemark());
            }
        });
    }

    public void delSubInfoById(String subNo) throws DataAccessException {
        this.jdbcTemplate.update("delete from SUBINFO where subNo='" + subNo + "'");
    }

    public void editSubInfo(final SubInfo subInfo) throws DataAccessException {
        String sql = "update SUBINFO set subName=?,subAddr=?,subUser=?,subPhone=?,remark=? where subNo=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, subInfo.getSubName());
                ps.setString(2, subInfo.getSubAddr());
                ps.setString(3, subInfo.getSubUser());
                ps.setString(4, subInfo.getSubPhone());
                ps.setString(5, subInfo.getRemark());
                ps.setString(6, subInfo.getSubNo());
            }
        });
    }

    public SubInfo getSubInfo(String subNo) {
        String sql = "select * from SUBINFO where subNo='" + subNo + "'";
        return (SubInfo)this.jdbcTemplate.queryForObject(sql, new SubInfoDTO());
    }

    public List<SubInfo> getSubInfoByCustId(int custId) {
        String sql = "select * from SUBINFO where custId=" + custId + " order by subNo";
        return this.jdbcTemplate.query(sql, new SubInfoDTO());
    }

    public class SubInfoDTO implements RowMapper {
        public SubInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            SubInfo dto = new SubInfo();
            dto.setCustId(rs.getInt("custId"));
            dto.setSubNo(rs.getString("subNo"));
            dto.setSubName(rs.getString("subName"));
            dto.setSubAddr(rs.getString("subAddr"));
            dto.setSubPhone(rs.getString("subPhone"));
            dto.setSubDate(rs.getString("subDate"));
            dto.setSubUser(rs.getString("subUser"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
