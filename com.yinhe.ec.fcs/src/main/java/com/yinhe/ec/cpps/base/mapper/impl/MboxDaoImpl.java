//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.mapper.impl;

import com.yinhe.ec.cpps.base.dao.MboxDao;
import com.yinhe.ec.cpps.model.MboxInfo;
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
public class MboxDaoImpl implements MboxDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public MboxDaoImpl() {
    }

    public void addMboxInfo(final MboxInfo mboxInfo) throws DataAccessException {
        String sql = "insert into MBOXINFO(mboxId,mboxNo,custId,Remark) values (?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, mboxInfo.getMboxId());
                ps.setString(2, mboxInfo.getMboxNo());
                ps.setInt(3, mboxInfo.getCustId());
                ps.setString(4, mboxInfo.getRemark());
            }
        });
    }

    public void deleteMboxInfo(int mboxId) throws DataAccessException {
        this.jdbcTemplate.update("delete from MBOXINFO where mboxId=" + mboxId);
    }

    public void editMboxInfo(final MboxInfo mboxInfo) throws DataAccessException {
        String sql = "update MBOXINFO set mboxNo=?,Remark=? where mboxId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, mboxInfo.getMboxNo());
                ps.setString(2, mboxInfo.getRemark());
                ps.setInt(3, mboxInfo.getMboxId());
            }
        });
    }

    public List<MboxInfo> getMboxInfo(int custId) {
        String sql = "select * from MBOXINFO where custId=" + custId + " order by mboxId";
        return this.jdbcTemplate.query(sql, new MboxInfoDTO());
    }

    public MboxInfo getMboxInfoById(int mboxId) {
        String sql = "select * from MBOXINFO where mboxId=" + mboxId;
        return (MboxInfo)this.jdbcTemplate.queryForObject(sql, new MboxInfoDTO());
    }

    public class MboxInfoDTO implements RowMapper {
        public MboxInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MboxInfo dto = new MboxInfo();
            dto.setMboxId(rs.getInt("mboxId"));
            dto.setMboxNo(rs.getString("mboxNo"));
            dto.setCustId(rs.getInt("custId"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
