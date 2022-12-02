//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.dao.impl;

import com.yinhe.ec.cpps.model.Schedule;
import com.yinhe.ec.cpps.model.ScheduleUserList;
import com.yinhe.ec.cpps.user.dao.UserScheduleDao;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class UserScheduleDaoImpl implements UserScheduleDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public UserScheduleDaoImpl() {
    }

    public List<Schedule> getUserScheduleHistory(String condition) {
        String sql = "select (select AreaName from AreaInfo where AreaId=Schedule.AreaId)AreaName,AreaId,CustId,ScheduleID,ScheduleType,OldFeeItemId,NewFeeItemId,StartDate,ScheduleFlag,CreateDate,CreateUser,(select FeeItemName from FeeItemList where FeeItemId=OldFeeItemId)oldFeeItemName,(select FeeItemName from FeeItemList where FeeItemId=NewFeeItemId)newFeeItemName from Schedule where " + condition;
        List<Schedule> list = this.jdbcTemplate.query(sql, new ScheduleDTO());
        return list;
    }

    public List<ScheduleUserList> getUserScheduleById(String scheduleId) {
        String sql = "select * from ScheduleUserList sul,UserInfo ui where sul.userId=ui.userId and ScheduleId='" + scheduleId + "'";
        List<ScheduleUserList> list = this.jdbcTemplate.query(sql, new ScheduleUserListDTO());
        return list;
    }

    public void addUserSchedule(Schedule schedule, String userIds, String meterNos) throws DataAccessException {
        String sql = "INSERT INTO Schedule(ScheduleId,ScheduleType,OldFeeItemId,NewFeeItemId,StartDate,ScheduleFlag,CreateDate,CreateUser,AreaId,YearDosageDT,CustId)VALUES('" + schedule.getScheduleId() + "'," + schedule.getScheduleType() + ",'" + schedule.getOldFeeItemId() + "','" + schedule.getNewFeeItemId() + "','" + schedule.getStartDate() + "'," + schedule.getScheduleFlag() + ",'" + schedule.getCreateDate() + "','" + schedule.getCreateUser() + "','" + schedule.getAreaId() + "','" + schedule.getYearDosageDt() + "','" + schedule.getCustId() + "')";
        this.jdbcTemplate.update(sql, new Object[0]);
        if (!"".equals(userIds)) {
            sql = "insert into ScheduleUserList(ScheduleId,UserID,CustId,meterNo) values(?,?,?,?)";
            String[] userId = userIds.split(",");
            String[] meterNo = meterNos.split(",");

            for(int i = 0; i < userId.length; ++i) {
                this.jdbcTemplate.update(sql, new Object[]{schedule.getScheduleId(), userId[i], schedule.getCustId(), meterNo[i]});
            }
        }

    }

    public Integer delUserScheduleById(final String scheduleId) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call delUserSchedule(?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, scheduleId);
                cs.registerOutParameter(2, 4);
                cs.execute();
                return cs.getInt(2);
            }
        });
        return no;
    }

    public class ScheduleDTO implements RowMapper {
        public ScheduleDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule dto = new Schedule();
            dto.setScheduleId(rs.getInt("scheduleId"));
            dto.setScheduleType(rs.getInt("scheduleType"));
            dto.setOldFeeItemId(rs.getString("oldFeeItemId"));
            dto.setNewFeeItemId(rs.getString("newFeeItemId"));
            dto.setStartDate(rs.getString("startDate"));
            dto.setScheduleFlag(rs.getInt("scheduleFlag"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setCreateUser(rs.getString("createUser"));
            dto.setNewFeeItemName(rs.getString("newFeeItemName"));
            dto.setOldFeeItemName(rs.getString("oldFeeItemName"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setAreaName(rs.getString("areaName"));
            dto.setCustId(rs.getInt("custId"));
            return dto;
        }
    }

    public class ScheduleUserListDTO implements RowMapper {
        public ScheduleUserListDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            ScheduleUserList dto = new ScheduleUserList();
            dto.setScheduleId(rs.getString("scheduleId"));
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            return dto;
        }
    }
}
