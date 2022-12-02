//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.dao.impl;

import com.yinhe.ec.cpps.base.dao.AlarmDao;
import com.yinhe.ec.cpps.model.AlarmParam;
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
public class AlarmDaoImpl implements AlarmDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public AlarmDaoImpl() {
    }

    public void addAlarmParam(final AlarmParam alarmParam) throws DataAccessException {
        String sql = "insert into AlarmParam(alarmId,alarmName,alarm1,alarm2,remark) values (?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, alarmParam.getAlarmId());
                ps.setString(2, alarmParam.getAlarmName());
                ps.setInt(3, alarmParam.getAlarm1());
                ps.setInt(4, alarmParam.getAlarm2());
                ps.setString(5, alarmParam.getRemark());
            }
        });
    }

    public void delAlarmParamById(int alarmId) throws DataAccessException {
        this.jdbcTemplate.update("delete from alarmParam where alarmId=" + alarmId);
    }

    public void editAlarmParam(final AlarmParam alarmParam) throws DataAccessException {
        String sql = "update alarmParam set alarmName=?,alarm1=?,alarm2=?,remark=? where alarmId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, alarmParam.getAlarmName());
                ps.setInt(2, alarmParam.getAlarm1());
                ps.setInt(3, alarmParam.getAlarm2());
                ps.setString(4, alarmParam.getRemark());
                ps.setInt(5, alarmParam.getAlarmId());
            }
        });
    }

    public AlarmParam getAlarmParam(int alarmId) {
        String sql = "select * from AlarmParam where alarmId=" + alarmId;
        return (AlarmParam)this.jdbcTemplate.queryForObject(sql, new AlarmParamDTO());
    }

    public List<AlarmParam> getAllAlarmParam() {
        String sql = "select * from AlarmParam order by alarmId";
        return this.jdbcTemplate.query(sql, new AlarmParamDTO());
    }

    public class AlarmParamDTO implements RowMapper {
        public AlarmParamDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            AlarmParam dto = new AlarmParam();
            dto.setAlarmId(rs.getInt("alarmId"));
            dto.setAlarmName(rs.getString("alarmName"));
            dto.setAlarm1(rs.getInt("alarm1"));
            dto.setAlarm2(rs.getInt("alarm2"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
