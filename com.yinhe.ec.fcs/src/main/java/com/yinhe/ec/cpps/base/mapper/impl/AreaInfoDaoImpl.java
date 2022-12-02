//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.mapper.impl;

import com.yinhe.ec.cpps.base.dao.AreaInfoDao;
import com.yinhe.ec.cpps.model.AreaInfo;
import com.yinhe.ec.cpps.model.ReadUser;
import com.yinhe.ec.cpps.util.Tools;
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
public class AreaInfoDaoImpl implements AreaInfoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public AreaInfoDaoImpl() {
    }

    public void addAreaInfo(final AreaInfo areaInfo) throws DataAccessException {
        String isql = "insert into AREAINFO(areaId,custId,areaName,areaPid,createDate,remark,areaFullName,businessFlag) values (?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, areaInfo.getAreaId());
                ps.setInt(2, areaInfo.getCustId());
                ps.setString(3, areaInfo.getAreaName());
                ps.setString(4, areaInfo.getAreaPid());
                ps.setString(5, areaInfo.getCreateDate());
                ps.setString(6, areaInfo.getRemark());
                ps.setString(7, areaInfo.getAreaFullName());
                ps.setInt(8, areaInfo.getBusinessFlag());
            }
        });
    }

    public void addReadUser(final ReadUser readUser) {
        String isql = "insert into ReadUser(CustId,ReadUserId,ReadUserName,CreateDate,Remark) values(?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, readUser.getCustId());
                ps.setString(2, readUser.getReadUserId());
                ps.setString(3, readUser.getReadUserName());
                ps.setString(4, readUser.getCreateDate());
                ps.setString(5, readUser.getRemark());
            }
        });
    }

    public void changeAreaInfoRoot(String targetId, String sourceId) throws DataAccessException {
        String sql = "update AREAINFO set areaPid = ? where areaId = ?";
        this.jdbcTemplate.update(sql, new Object[]{targetId, sourceId});
    }

    public void deleteAreaInfo(String areaId) throws DataAccessException {
        String sql = "delete from AREAINFO where areaId='" + areaId + "'";
        this.jdbcTemplate.update(sql);
    }

    public void deleteReadUser(String readUserId) {
        String sql = "delete from ReadUser where ReadUserId=?";
        this.jdbcTemplate.update(sql, new Object[]{readUserId});
    }

    public void editAreaInfo(final AreaInfo areaInfo) throws DataAccessException {
        String areaTmpFullName = areaInfo.getAreaFullName();
        if (areaTmpFullName.indexOf("->") != -1) {
            areaTmpFullName = areaTmpFullName.substring(0, areaTmpFullName.indexOf("->"));
        }

        areaTmpFullName = areaTmpFullName + "->" + areaInfo.getAreaName();
        String isql = "update AREAINFO set areaName=?,remark=?,areaFullName='" + areaTmpFullName + "',businessFlag=? where areaId=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, areaInfo.getAreaName());
                ps.setString(2, areaInfo.getRemark());
                ps.setInt(3, areaInfo.getBusinessFlag());
                ps.setString(4, areaInfo.getAreaId());
            }
        });
    }

    public void editReadUser(final ReadUser readUser) {
        String isql = "update ReadUser set readUserName=?,remark=? where readUserId=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, readUser.getReadUserName());
                ps.setString(2, readUser.getRemark());
                ps.setString(3, readUser.getReadUserId());
            }
        });
    }

    public List<AreaInfo> getAreaInfoByClause(int custId) {
        String sql = "select * from AREAINFO where custId =" + custId + " ";
        return this.jdbcTemplate.query(sql, new Object[0], new AreaInfoDTO());
    }

    public AreaInfo getAreaInfoById(String areaId) {
        String sql = "select * from AREAINFO where areaId = ? ";
        List<AreaInfo> list = this.jdbcTemplate.query(sql, new Object[]{areaId}, new AreaInfoDTO());
        return list.size() > 0 ? (AreaInfo)list.get(0) : new AreaInfo();
    }

    public List<ReadUser> getReadUserByCustId(int custId) {
        String sql = "select * from ReadUser where custId=" + custId;
        return this.jdbcTemplate.query(sql, new Object[0], new ReadUserDTO());
    }

    public List<AreaInfo> getAreaInfoByOrders(int custId, String orders) {
        String sql = "select * from AREAINFO where custId =" + custId + orders + " order by areaId,areaPid";
        return this.jdbcTemplate.query(sql, new Object[0], new AreaInfoDTO());
    }

    public void editAreaMeterInfo(final AreaInfo areaInfo) {
        String isql = "update AreaInfo set meterNo=? where areaId=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, areaInfo.getMeterNo());
                ps.setString(2, areaInfo.getAreaId());
            }
        });
    }

    public class AreaInfoDTO implements RowMapper {
        public AreaInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            AreaInfo dto = new AreaInfo();
            dto.setCustId(rs.getInt("custId"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setAreaPid(rs.getString("areaPid"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setAreaFullName(Tools.NullToEmpty(rs.getString("areaFullName")));
            dto.setRemark(Tools.NullToEmpty(rs.getString("remark")));
            dto.setBusinessFlag(rs.getInt("businessFlag"));
            dto.setMeterNo(Tools.NullToEmpty(rs.getString("meterNo")));
            return dto;
        }
    }

    public class ReadUserDTO implements RowMapper {
        public ReadUserDTO() {
        }

        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            ReadUser dto = new ReadUser();
            dto.setCustId(rs.getInt("custId"));
            dto.setReadUserId(rs.getString("readUserId"));
            dto.setReadUserName(rs.getString("readUserName"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
