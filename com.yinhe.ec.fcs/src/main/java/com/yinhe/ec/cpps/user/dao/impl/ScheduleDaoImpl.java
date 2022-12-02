//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.dao.impl;

import com.yinhe.ec.cpps.model.Schedule;
import com.yinhe.ec.cpps.user.dao.ScheduleDao;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class ScheduleDaoImpl implements ScheduleDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public ScheduleDaoImpl() {
    }

    public void addSchedule(final Schedule schedule) throws DataAccessException {
        String isql = "insert into Schedule(custId,ScheduleId,ScheduleType,OldFeeItemId,NewFeeItemId,StartDate,ScheduleFlag,CreateDate,CreateUser,areaId,YearDosageDT) values (?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, schedule.getCustId());
                ps.setInt(2, schedule.getScheduleId());
                ps.setInt(3, schedule.getScheduleType());
                ps.setString(4, schedule.getOldFeeItemId());
                ps.setString(5, schedule.getNewFeeItemId());
                ps.setString(6, schedule.getStartDate());
                ps.setInt(7, schedule.getScheduleFlag());
                ps.setString(8, schedule.getCreateDate());
                ps.setString(9, schedule.getCreateUser());
                ps.setString(10, schedule.getAreaId());
                ps.setString(11, schedule.getYearDosageDt());
            }
        });
    }

    public Integer delSchedule(final int scheduleId) throws DataAccessException {
        Integer no = (Integer)this.jdbcTemplate.execute("{call delSchedule(?,?)}", new CallableStatementCallback<Object>() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, scheduleId);
                cs.registerOutParameter(2, 4);
                cs.execute();
                return cs.getInt(2);
            }
        });
        return no;
    }

    public List<Schedule> getScheduleAll(String condition) {
        String sql = "select (select areaName from AreaInfo where AreaId=Schedule.AreaId)AreaName,AreaID,ScheduleID,ScheduleType,OldFeeItemId,NewFeeItemId,StartDate,ScheduleFlag,CreateDate,CreateUser,(select FeeItemName from FeeItemList where FeeItemId=OldFeeItemId)oldFeeItemName,(select FeeItemName from FeeItemList where FeeItemId=NewFeeItemId)newFeeItemName from Schedule where " + condition;
        List<Schedule> list = this.jdbcTemplate.query(sql, new ScheduleDTO());
        return list;
    }

    public Schedule getScheduleById(String scheduleId) {
        String sql = "select * from Schedule where ScheduleId='" + scheduleId + "'";
        List<Schedule> list = this.jdbcTemplate.query(sql, new ScheduleDTO());
        return list.size() == 0 ? new Schedule() : (Schedule)list.get(0);
    }

    public void modSchedule(final Schedule schedule) throws DataAccessException {
        String isql = "update Schedule set ScheduleType=?,OldFeeItemId=?,NewFeeItemId=?,StartDate=?,ScheduleFlag=?,CreateDate=?,CreateUser=? where ScheduleId=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, schedule.getScheduleType());
                ps.setString(2, schedule.getOldFeeItemId());
                ps.setString(3, schedule.getNewFeeItemId());
                ps.setString(4, schedule.getStartDate());
                ps.setInt(5, schedule.getScheduleFlag());
                ps.setString(6, schedule.getCreateDate());
                ps.setString(7, schedule.getCreateUser());
                ps.setInt(8, schedule.getScheduleId());
            }
        });
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
            return dto;
        }
    }
}
