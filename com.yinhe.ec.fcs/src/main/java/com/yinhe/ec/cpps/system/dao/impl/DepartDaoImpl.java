//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.model.DepartInfo;
import com.yinhe.ec.cpps.system.dao.DepartDao;
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
public class DepartDaoImpl implements DepartDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public DepartDaoImpl() {
    }

    public List<DepartInfo> getDepartInfoByCompanyId(int custId) {
        String sql = "";
        sql = "select * from DepartInfo where CustId =" + custId + " ";
        return this.jdbcTemplate.query(sql, new Object[0], new DepartInfoDTO());
    }

    public void changeDepartInfoRoot(String targetId, String sourceId) throws DataAccessException {
        String sql = "update DepartInfo set departPid = ? where departId = ?";
        this.jdbcTemplate.update(sql, new Object[]{targetId, sourceId});
    }

    public void addDepartInfo(final DepartInfo departInfo) throws DataAccessException {
        String isql = "insert into DepartInfo(departId,departName,departPid,CustId,CreateDate,remark) values (?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, departInfo.getDepartId());
                ps.setString(2, departInfo.getDepartName());
                ps.setString(3, departInfo.getDepartPid());
                ps.setInt(4, departInfo.getCustId());
                ps.setString(5, departInfo.getCreateDate());
                ps.setString(6, departInfo.getRemark());
            }
        });
    }

    public void deleteDepartInfo(String departId) throws DataAccessException {
        String sql = "delete from DepartInfo where departId='" + departId + "'";
        this.jdbcTemplate.update(sql);
    }

    public void editDepartInfo(final DepartInfo departInfo) throws DataAccessException {
        String sql = "update DepartInfo set departName=?,remark=? where departId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, departInfo.getDepartName());
                ps.setString(2, departInfo.getRemark());
                ps.setString(3, departInfo.getDepartId());
            }
        });
    }

    public List<DepartInfo> getDepartInfoAll() {
        String sql = "select * from DepartInfo order by departId,departPid";
        return this.jdbcTemplate.query(sql, new DepartInfoDTO());
    }

    public DepartInfo getDepartInfoById(String departId) {
        String sql = "select * from DepartInfo where departId=?";
        List<DepartInfo> list = this.jdbcTemplate.query(sql, new Object[]{departId}, new DepartInfoDTO());
        return list.size() == 0 ? new DepartInfo() : (DepartInfo)list.get(0);
    }

    public DepartInfo getDepartInfoByPid(String departPid) {
        String sql = "select * from DepartInfo where departId=?";
        List<DepartInfo> list = this.jdbcTemplate.query(sql, new Object[]{departPid}, new DepartInfoDTO());
        return list.size() == 0 ? new DepartInfo() : (DepartInfo)list.get(0);
    }

    public List<DepartInfo> getDepartInfoListByPid(String departPid) {
        String sql = "select * from DepartInfo where departPid =? ";
        return this.jdbcTemplate.query(sql, new Object[]{departPid}, new DepartInfoDTO());
    }

    public class DepartInfoDTO implements RowMapper {
        public DepartInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            DepartInfo dto = new DepartInfo();
            dto.setCustId(rs.getInt("CustId"));
            dto.setDepartId(rs.getString("departId"));
            dto.setDepartName(rs.getString("departName"));
            dto.setDepartPid(rs.getString("departPid"));
            dto.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
            return dto;
        }
    }
}
