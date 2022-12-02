//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.dao.impl;

import com.yinhe.ec.cpps.dto.UserParkDTO;
import com.yinhe.ec.cpps.model.ParkInfo;
import com.yinhe.ec.cpps.model.UserParkInfo;
import com.yinhe.ec.cpps.user.dao.ParkInfoDao;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
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
public class ParkInfoDaoImpl implements ParkInfoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public ParkInfoDaoImpl() {
    }

    public void AddParkInfo(final ParkInfo parkInfo) throws DataAccessException {
        String isql = "insert into parkInfo(PARKID,CUSTID,PARKNO,PARKPROPERTY,PARKSTATE,FREEPARKTIME,REMARK,AREAID) VALUES (?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, parkInfo.getParkId());
                ps.setInt(2, parkInfo.getCustId());
                ps.setString(3, parkInfo.getParkNo());
                ps.setInt(4, parkInfo.getParkProperty());
                ps.setInt(5, parkInfo.getParkState());
                ps.setInt(6, 60);
                ps.setString(7, parkInfo.getRemark());
                ps.setString(8, parkInfo.getAreaId());
            }
        });
    }

    public int DelParkInfo(int parkId) throws DataAccessException {
        if (this.jdbcTemplate.queryForObject("select count(*) from USERPARKINFO where PARKID=" + parkId,Integer.class) > 0) {
            return 0;
        } else {
            this.jdbcTemplate.update("delete from PARKINFO where PARKID=" + parkId);
            return 1;
        }
    }

    public void EditParkInfo(final ParkInfo parkInfo) throws DataAccessException {
        String sql = "update parkInfo set PARKNO=?,PARKPROPERTY=?,PARKSTATE=?,REMARK=?,AREAID=? where PARKID=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, parkInfo.getParkNo());
                ps.setInt(2, parkInfo.getParkProperty());
                ps.setInt(3, parkInfo.getParkState());
                ps.setString(4, parkInfo.getRemark());
                ps.setString(5, parkInfo.getAreaId());
                ps.setInt(6, parkInfo.getParkId());
            }
        });
    }

    public List<ParkInfo> getParkInfo(int custId) {
        String sql = "select parkInfo.*,userparkinfo.carNo from parkInfo left join userparkinfo on userparkinfo.parkId=parkInfo.parkId where parkInfo.custId=" + custId + " order by parkInfo.parkId desc";
        return this.jdbcTemplate.query(sql, new ParkInfoDTO());
    }

    public void AddUserParkInfo(final UserParkInfo userParkInfo) throws DataAccessException {
        String isql = "insert into userParkInfo(USERID,PARKID,CUSTID,FEEITEMID,FEEMODE,CARNO,CarStartDate,CarEndDate) values (?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, userParkInfo.getUserId());
                ps.setInt(2, userParkInfo.getParkId());
                ps.setInt(3, userParkInfo.getCustId());
                ps.setString(4, userParkInfo.getFeeItemId());
                ps.setInt(5, 3);
                ps.setString(6, userParkInfo.getCarNo());
                ps.setString(7, userParkInfo.getCarStartDate());
                ps.setString(8, userParkInfo.getCarEndDate());
            }
        });
        String usql = "update PARKINFO set PARKSTATE=1 where PARKID=?";
        this.jdbcTemplate.update(usql, new Object[]{userParkInfo.getParkId()});
    }

    public void DelUserParkInfo(int parkId, String userId) throws DataAccessException {
        String sql = "delete from userParkInfo where parkId=" + parkId + " and userid='" + userId + "'";
        this.jdbcTemplate.update(sql);
        String usql = "update PARKINFO set PARKSTATE=0 where PARKID=?";
        this.jdbcTemplate.update(usql, new Object[]{parkId});
    }

    public void EditUserParkInfo(final UserParkInfo userParkInfo) throws DataAccessException {
        String sql = "update userParkInfo set USERID=?,FEEITEMID=?,FEEMODE=?,CARNO=?,CarStartDate=?,CarEndDate=? where PARKID=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, userParkInfo.getUserId());
                ps.setString(2, userParkInfo.getFeeItemId());
                ps.setInt(3, 3);
                ps.setString(4, userParkInfo.getCarNo());
                ps.setString(5, userParkInfo.getCarStartDate());
                ps.setString(6, userParkInfo.getCarEndDate());
                ps.setInt(7, userParkInfo.getParkId());
            }
        });
    }

    public ParkInfo getParkInfoById(int custId, int parkId) {
        String sql = "select parkInfo.*,userparkinfo.carNo from parkInfo left join userparkinfo on userparkinfo.parkId=parkInfo.parkId where parkInfo.custId=" + custId + " and parkInfo.parkId=" + parkId + " order by parkInfo.parkId";
        List<ParkInfo> list = this.jdbcTemplate.query(sql, new ParkInfoDTO());
        return list.size() > 0 ? (ParkInfo)list.get(0) : new ParkInfo();
    }

    public UserParkDTO getUserInfoByParkId(int custId, int parkId) {
        String sql = "select UserInfo.*,userParkInfo.carNo,userParkInfo.feeitemId,userParkInfo.CarStartDate,userParkInfo.CarEndDate from UserInfo,userParkInfo where userParkInfo.userid=UserInfo.userid and userParkInfo.custid=" + custId + " and userParkInfo.parkId=" + parkId;
        List<UserParkDTO> list = this.jdbcTemplate.query(sql, new UserInfoDTO());
        return list.size() > 0 ? (UserParkDTO)list.get(0) : new UserParkDTO();
    }

    public List<ParkInfo> getParkInfo(int page, int rows, String condition) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("select parkInfo.*,userparkinfo.carNo from parkInfo left join userparkinfo on userparkinfo.parkId=parkInfo.parkId where " + condition + " order by parkInfo.parkId desc", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" * from (select parkInfo.*,userparkinfo.carNo from parkInfo left join userparkinfo on userparkinfo.parkId=parkInfo.parkId where " + condition + " ) ", page, rows, "parkId", "desc");
        }

        return this.jdbcTemplate.query(sql, new ParkInfoDTO());
    }

    public int getTotal(String condition) {
        String sql = "select count(*) from (select parkInfo.*,userparkinfo.carNo from parkInfo left join userparkinfo on userparkinfo.parkId=parkInfo.parkId where " + condition + ") ";
        return this.jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public void EditCarCycle(String userId, String parkId, String startDate, String endDate) throws DataAccessException {
        String sql = "update USERPARKINFO set carstartdate='" + startDate + "',carenddate='" + endDate + "' where userid='" + userId + "' and parkid=" + parkId;
        this.jdbcTemplate.update(sql);
    }

    public class ParkInfoDTO implements RowMapper {
        public ParkInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            ParkInfo dto = new ParkInfo();
            dto.setParkId(rs.getInt("parkId"));
            dto.setParkNo(rs.getString("parkNo"));
            dto.setParkProperty(rs.getInt("parkProperty"));
            dto.setParkState(rs.getInt("parkState"));
            dto.setFreeParkTime(rs.getInt("freeParkTime"));
            dto.setRemark(rs.getString("carNo"));
            dto.setCustId(rs.getInt("custId"));
            dto.setAreaId(rs.getString("areaId"));
            return dto;
        }
    }

    public class UserInfoDTO implements RowMapper {
        public UserInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserParkDTO dto = new UserParkDTO();
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setUserTypeId(rs.getInt("userTypeId"));
            dto.setBillKind(rs.getInt("billKind"));
            dto.setMemberCount(rs.getInt("memberCount"));
            dto.setAreas(rs.getDouble("areas"));
            dto.setFloors(rs.getInt("floors"));
            dto.setEmail(rs.getString("email"));
            dto.setMobilePhone(rs.getString("mobilePhone"));
            dto.setUserState(rs.getInt("userState"));
            dto.setSmsKind(rs.getInt("smsKind"));
            dto.setContractNo(rs.getString("contractNo"));
            dto.setUserCode(rs.getString("userCode"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setCarNo(rs.getString("carNo"));
            dto.setFeeItemId(rs.getString("feeItemId"));
            dto.setCarStartDate(rs.getString("carStartDate"));
            dto.setCarEndDate(rs.getString("carEndDate"));
            return dto;
        }
    }
}
