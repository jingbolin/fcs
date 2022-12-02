//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.model.FuncMethod;
import com.yinhe.ec.cpps.model.OperRole;
import com.yinhe.ec.cpps.model.RoleFuncMethod;
import com.yinhe.ec.cpps.system.dao.OperatorRoleDao;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class OperatorRoleDaoImp implements OperatorRoleDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public OperatorRoleDaoImp() {
    }

    public void addOperatorRole(OperRole operRole) throws DataAccessException {
        String sql = "insert into OperRole(ORID,ORName,ORCreateDate,CustId,ORPID) values(?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new Object[]{operRole.getOrId(), operRole.getOrName(), operRole.getOrCreateDate(), operRole.getCustId(), operRole.getOrPid()});
    }

    public void delRoleMethodByOrId(String orId) throws DataAccessException {
        String sql = "delete from RoleFuncMethod where orId = ?";
        this.jdbcTemplate.update(sql, new Object[]{orId});
    }

    public void deleteOperRoleById(String orId) throws DataAccessException {
        String sql = "delete from OperRole where orId = ?";
        this.jdbcTemplate.update(sql, new Object[]{orId});
    }

    public void editOperRole(OperRole operRole) throws DataAccessException {
        String sql = "update OperRole set orName = ? where orId = ?";
        this.jdbcTemplate.update(sql, new Object[]{operRole.getOrName(), operRole.getOrId()});
    }

    public List<OperRole> getOperRole(int page, int rows, String condition) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT OperRole.custId,OperRole.orId,OperRole.OrName FROM OperRole where " + condition + " ORDER BY ORCREATEDATE asc ", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" OperRole.custId,OperRole.orId,OperRole.OrName FROM OperRole", page, rows, "ORCREATEDATE", "asc");
        }

        List<OperRole> list = this.jdbcTemplate.query(sql, new OperRoleDTOMapper());
        return list;
    }

    public List<OperRole> getOperRoleByOperId(int OperId) {
        String sql = "SELECT OperRole.custId,OperRole.orId,OperRole.OrName FROM OPERROLELIST,OPERROLE WHERE OPERROLELIST.ORID = OPERROLE.ORID AND OPERATORID = ?";
        return this.jdbcTemplate.query(sql, new Object[]{OperId}, new OperRoleDTOMapper());
    }

    public List<OperRole> getOperRoleByOperatorAccount(String operatorAccount) {
        String sql = "select OperRole.custId,OperRole.orId,OperRole.OrName from Operator,OperRoleList,OperRole where Operator.OperatorId = OperRoleList.OPERATORID and OperRoleList.ORID=OperRole.orId and OperatorAccount = ?";
        return this.jdbcTemplate.query(sql, new Object[]{operatorAccount}, new OperRoleDTOMapper());
    }

    public List<RoleFuncMethod> getRoleMethodByOrId(String orId) {
        String sql = "select * from RoleFuncMethod where orId = ?";
        return this.jdbcTemplate.query(sql, new Object[]{orId}, new RoleFuncMethodDTOMapper());
    }

    public int getTotal(String condition) {
        return this.jdbcTemplate.queryForList("select count(*) from OperRole where " + condition).size();
    }

    public void setRoleMethod(String orId, String[] indeterminateId, String[] checkedId, int custId) throws DataAccessException {
        this.jdbcTemplate.update("delete from RoleFuncMethod where orId = '" + orId + "'");

        int i;
        for(i = 0; i < indeterminateId.length; ++i) {
            this.jdbcTemplate.update("insert into RoleFuncMethod(orId,fmId,RFMStatus,custId) values('" + orId + "','" + indeterminateId[i] + "','indeterminate'," + custId + ")");
        }

        for(i = 0; i < checkedId.length; ++i) {
            this.jdbcTemplate.update("insert into RoleFuncMethod(orId,fmId,RFMStatus,custId) values('" + orId + "','" + checkedId[i] + "','checked'," + custId + ")");
        }

    }

    public List<OperRole> getAllOperRole(int custId) {
        String sql = "";
        sql = "select OperRole.custId,OperRole.orId,OperRole.OrName from OperRole where custId=" + custId;
        return this.jdbcTemplate.query(sql, new OperRoleDTOMapper());
    }

    public class OperRoleDTOMapper implements RowMapper {
        public OperRoleDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            OperRole dto = new OperRole();
            dto.setCustId(rs.getInt("custId"));
            dto.setOrId(rs.getString("orId"));
            dto.setOrName(rs.getString("orName"));
            return dto;
        }
    }

    public class RoleFuncMethodDTOMapper implements RowMapper {
        public RoleFuncMethodDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoleFuncMethod dto = new RoleFuncMethod();
            OperRole operRole = new OperRole();
            operRole.setOrId(rs.getString("orId"));
            FuncMethod funcMethod = new FuncMethod();
            funcMethod.setFmId(rs.getString("fmId"));
            dto.setOperRole(operRole);
            dto.setFuncMethod(funcMethod);
            dto.setRfmStatus(rs.getString("RfmStatus"));
            dto.setCustId(rs.getInt("custId"));
            return dto;
        }
    }
}
