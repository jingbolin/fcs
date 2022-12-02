//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.base.dao.impl;

import com.yinhe.ec.cpps.base.dao.CmdDao;
import com.yinhe.ec.cpps.model.Cmd;
import com.yinhe.ec.cpps.model.MeterProtocol;
import com.yinhe.ec.cpps.model.NbMeterPwd;
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
public class CmdDaoImpl implements CmdDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public CmdDaoImpl() {
    }

    public List<Cmd> getCmd(int meterPtl) {
        String sql = "select * from Cmd where isuse=0 and meterPtl=" + meterPtl + " order by CmdId";
        return this.jdbcTemplate.query(sql, new Object[0], new CmdDTO());
    }

    public MeterProtocol getMeterProtocolByPtl(int meterPtl) {
        String sql = "select meterPtl,ptlName,remark from MeterProtocol where meterPtl=" + meterPtl + " and rownum=1";
        List<MeterProtocol> list = this.jdbcTemplate.query(sql, new MeterProtocolDTO());
        return list.size() > 0 ? (MeterProtocol)list.get(0) : new MeterProtocol();
    }

    public Cmd getCmdByCmdId(int cmdId) {
        String sql = "select * from Cmd where cmdId=" + cmdId;
        List<Cmd> list = this.jdbcTemplate.query(sql, new CmdDTO());
        return list.size() > 0 ? (Cmd)list.get(0) : new Cmd();
    }

    public List<NbMeterPwd> getNbMeterPwd() {
        String sql = "select * from nbmeterpwd order by pwdGroupNo";
        return this.jdbcTemplate.query(sql, new Object[0], new NbMeterPwdDTO());
    }

    public NbMeterPwd getNbMeterPwdByGroupNo(int pwdGroupNo) {
        String sql = "select * from nbmeterpwd where pwdGroupNo=" + pwdGroupNo;
        List<NbMeterPwd> list = this.jdbcTemplate.query(sql, new Object[0], new NbMeterPwdDTO());
        return list.size() > 0 ? (NbMeterPwd)list.get(0) : new NbMeterPwd();
    }

    public void addNbMeterPwd(final NbMeterPwd nbMeterPwd) throws DataAccessException {
        String sql = "insert into nbmeterpwd(pwdGroupNo,pwdGroupName,operatorPwd,operatorCode,remark) values (?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, nbMeterPwd.getPwdGroupNo());
                ps.setString(2, nbMeterPwd.getPwdGroupName());
                ps.setString(3, nbMeterPwd.getOperatorPwd());
                ps.setString(4, nbMeterPwd.getOperatorCode());
                ps.setString(5, nbMeterPwd.getRemark());
            }
        });
    }

    public void editMeterPwd(final NbMeterPwd nbMeterPwd) throws DataAccessException {
        String sql = "update nbmeterpwd set pwdGroupName=?,operatorPwd=?,operatorCode=?,remark=? where pwdGroupNo=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterPwd.getPwdGroupName());
                ps.setString(2, nbMeterPwd.getOperatorPwd());
                ps.setString(3, nbMeterPwd.getOperatorCode());
                ps.setString(4, nbMeterPwd.getRemark());
                ps.setInt(5, nbMeterPwd.getPwdGroupNo());
            }
        });
    }

    public void deleteMeterPwdByGroupNo(int pwdGroupNo) throws DataAccessException {
        String sql = "delete from nbmeterpwd where pwdGroupNo=" + pwdGroupNo;
        this.jdbcTemplate.update(sql);
    }

    public boolean getMeterPwdIsUsed(int pwdGroupNo) {
        return (Integer)this.jdbcTemplate.queryForObject("select count(*) from nbMeterInfo where pwdGroupNo=" + pwdGroupNo, Integer.class) > 0;
    }

    public List<Cmd> getMeterCmd(String orders) {
        String sql = "select * from Cmd where 1=1" + orders + " order by CmdId";
        return this.jdbcTemplate.query(sql, new Object[0], new CmdDTO());
    }

    public void addMeterCmd(final Cmd cmd) {
        String sql = "insert into Cmd(cmdId,cmdName,meterPtl,isuse,cmdCode,dataLength,dataItem,returnCmdCode,returnDataLength,returnDataFormat,typeId) values (?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, cmd.getCmdId());
                ps.setString(2, cmd.getCmdName());
                ps.setInt(3, cmd.getMeterPtl());
                ps.setInt(4, 0);
                ps.setString(5, cmd.getCmdCode());
                ps.setString(6, cmd.getDataLength());
                ps.setString(7, cmd.getDataItem());
                ps.setString(8, cmd.getReturnCmdCode());
                ps.setString(9, cmd.getReturnDataLength());
                ps.setString(10, cmd.getReturnDataFormat());
                ps.setInt(11, cmd.getTypeId());
            }
        });
    }

    public void editMeterCmd(final Cmd cmd) {
        String sql = "update Cmd set cmdName=?,meterPtl=?,cmdCode=?,dataLength=?,dataItem=?,returnCmdCode=?,returnDataLength=?,returnDataFormat=?,typeId=? where cmdId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, cmd.getCmdName());
                ps.setInt(2, cmd.getMeterPtl());
                ps.setString(3, cmd.getCmdCode());
                ps.setString(4, cmd.getDataLength());
                ps.setString(5, cmd.getDataItem());
                ps.setString(6, cmd.getReturnCmdCode());
                ps.setString(7, cmd.getReturnDataLength());
                ps.setString(8, cmd.getReturnDataFormat());
                ps.setInt(9, cmd.getTypeId());
                ps.setInt(10, cmd.getCmdId());
            }
        });
    }

    public void stopMeterCmdById(int cmdId, int isuse) {
        String sql = "update Cmd set Isuse=" + isuse + " where cmdId=" + cmdId;
        this.jdbcTemplate.update(sql);
    }

    public class CmdDTO implements RowMapper {
        public CmdDTO() {
        }

        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            Cmd cmd = new Cmd();
            cmd.setCmdId(rs.getInt("cmdId"));
            cmd.setMeterPtl(rs.getInt("meterPtl"));
            cmd.setCmdName(rs.getString("cmdName"));
            cmd.setIsuse(rs.getInt("isuse"));
            cmd.setCmdCode(rs.getString("cmdCode"));
            cmd.setDataLength(rs.getString("dataLength"));
            cmd.setDataItem(rs.getString("dataItem") == null ? "" : rs.getString("dataItem"));
            cmd.setReturnCmdCode(rs.getString("returnCmdCode"));
            cmd.setReturnDataLength(rs.getString("returnDataLength"));
            cmd.setReturnDataFormat(rs.getString("returnDataFormat") == null ? "" : rs.getString("returnDataFormat"));
            cmd.setMeterProtocol(CmdDaoImpl.this.getMeterProtocolByPtl(rs.getInt("meterPtl")));
            cmd.setTypeId(rs.getInt("typeId"));
            return cmd;
        }
    }

    public class MeterProtocolDTO implements RowMapper {
        public MeterProtocolDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterProtocol dto = new MeterProtocol();
            dto.setMeterPtl(rs.getInt("meterPtl"));
            dto.setPtlName(rs.getString("ptlName"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }

    public class NbMeterPwdDTO implements RowMapper {
        public NbMeterPwdDTO() {
        }

        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            NbMeterPwd dto = new NbMeterPwd();
            dto.setPwdGroupNo(rs.getInt("pwdGroupNo"));
            dto.setPwdGroupName(rs.getString("pwdGroupName"));
            dto.setOperatorPwd(rs.getString("operatorPwd"));
            dto.setOperatorCode(rs.getString("operatorCode"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
