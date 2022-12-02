//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.base.dao.AreaInfoDao;
import com.yinhe.ec.cpps.model.OperRole;
import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.system.dao.CustDao;
import com.yinhe.ec.cpps.system.dao.DepartDao;
import com.yinhe.ec.cpps.system.dao.OperatorDao;
import com.yinhe.ec.cpps.system.dao.OperatorRoleDao;
import com.yinhe.ec.cpps.util.CommonSQL;
import com.yinhe.ec.cpps.util.ConstParam;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class OperatorDaoImpl implements OperatorDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private OperatorRoleDao operatorRoleDao;
    @Resource
    private DepartDao departDao;
    @Resource
    private CustDao custDao;
    @Resource
    private AreaInfoDao areaInfoDao;

    public OperatorDaoImpl() {
    }

    public Operator getOperatorByAccount(String operatorAccount) {
        String sql = "select * from Operator where operatorAccount = ?";
        return (Operator)this.jdbcTemplate.queryForObject(sql, new Object[]{operatorAccount}, new OperatorDTOMapper());
    }

    public void editOperatorPwdById(int operatorId, String operatorPwd) throws DataAccessException {
        String sql = "update Operator set operatorPWD = ? where operatorId = ?";
        this.jdbcTemplate.update(sql, new Object[]{operatorPwd, operatorId});
    }

    public Operator getOperatorById(int operatorId) {
        String sql = "select * from Operator where operatorId=?";
        return (Operator)this.jdbcTemplate.queryForObject(sql, new Object[]{operatorId}, new OperatorDTOMapper());
    }

    public List<Operator> getOperator(int page, int rows, String condition) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT * FROM Operator WHERE " + condition + " ORDER BY operatorId asc", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" * FROM Operator WHERE " + condition + " ", page, rows, "operatorId", "asc");
        }

        List<Operator> list = this.jdbcTemplate.query(sql, new OperatorDTOMapper());
        return list;
    }

    public int getTotal(String condition) {
        return this.jdbcTemplate.queryForList("select count(*) from Operator where " + condition).size();
    }

    public void addOperator(final Operator operator, final String roleId) throws DataAccessException {
        String sql = "insert into Operator(OPERATORID,CUSTID,DEPARTID,OPERATORACCOUNT,OPERATORNAME,OPERATORPWD,ISLOGIN,WORKCARD,CARDID,EMAIL,LOGINTIMES,PHONE,ISUSED,CREATEDATE,OPERATORPID,REMARK,AREAID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, operator.getOperatorId());
                ps.setInt(2, operator.getCustId());
                ps.setString(3, operator.getDepartId());
                ps.setString(4, operator.getOperatorAccount());
                ps.setString(5, operator.getOperatorName());
                ps.setString(6, operator.getOperatorPwd());
                ps.setInt(7, operator.getIsLogin());
                ps.setString(8, operator.getWorkCard());
                ps.setString(9, operator.getCardId());
                ps.setString(10, operator.getEmail());
                ps.setInt(11, operator.getLoginTimes());
                ps.setString(12, operator.getPhone());
                ps.setInt(13, 1);
                ps.setString(14, operator.getCreateDate());
                ps.setInt(15, operator.getOperatorPid());
                ps.setString(16, operator.getRemark());
                ps.setString(17, operator.getAreaId());
            }
        });
        String insertOperatorRole = "insert into OPERROLELIST(ORID,CUSTID,OPERATORID) values(?,?,?)";
        this.jdbcTemplate.update(insertOperatorRole, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, roleId);
                ps.setInt(2, operator.getCustId());
                ps.setInt(3, operator.getOperatorId());
            }
        });
    }

    public void editOperator(final Operator operator, final String roleId) throws DataAccessException {
        String sql = "update Operator set WorkCard=?,OperatorName=?,CardID=?,Email=?,Phone=?,departId=?,areaId=? where operatorId = ?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, operator.getWorkCard());
                ps.setString(2, operator.getOperatorName());
                ps.setString(3, operator.getCardId());
                ps.setString(4, operator.getEmail());
                ps.setString(5, operator.getPhone());
                ps.setString(6, operator.getDepartId());
                ps.setString(7, operator.getAreaId());
                ps.setInt(8, operator.getOperatorId());
            }
        });
        String deleteOperatorRole = "delete from OPERROLELIST where operatorId = ?";
        this.jdbcTemplate.update(deleteOperatorRole, new Object[]{operator.getOperatorId()});
        String insertOperatorRole = "insert into OPERROLELIST(ORID,CUSTID,OPERATORID) values(?,?,?)";
        this.jdbcTemplate.update(insertOperatorRole, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, roleId);
                ps.setInt(2, operator.getCustId());
                ps.setInt(3, operator.getOperatorId());
            }
        });
    }

    public void deleteOperatorById(int operatorId) throws DataAccessException {
        String deleteOperatorRole = "delete from OPERROLELIST where operatorId = ?";
        this.jdbcTemplate.update(deleteOperatorRole, new Object[]{operatorId});
        String sql = "delete from operator where operatorId = ?";
        this.jdbcTemplate.update(sql, new Object[]{operatorId});
    }

    public void setInitPwd(int operatorId, String pwd) throws DataAccessException {
        String sql = "update Operator set operatorPwd = ? where operatorId = ?";
        this.jdbcTemplate.update(sql, new Object[]{pwd, operatorId});
    }

    public void setIsLogin(int operatorId, int status) throws DataAccessException {
        String sql = "update Operator set isLogin = ? where operatorID = ?";
        if (status == 0) {
            this.jdbcTemplate.update(sql, new Object[]{1, operatorId});
        } else {
            this.jdbcTemplate.update(sql, new Object[]{0, operatorId});
        }

    }

    public Integer getOperatorResultSetByAccount(String operatorAccount) {
        String sql = "select count(*) from Operator where operatorAccount = ?";
        return this.jdbcTemplate.queryForList(sql, new Object[]{operatorAccount}).size();
    }

    public void editOperatorAccount(int operatorId, String operatorAccount) throws DataAccessException {
        String sql = "update Operator set operatorAccount = ? where operatorId = ?";
        this.jdbcTemplate.update(sql, new Object[]{operatorAccount, operatorId});
    }

    public List<Operator> getOperatorListByCondition(String condition) {
        String sql = "select * from Operator where 1=1 " + condition + " order by operatorId";
        return this.jdbcTemplate.query(sql, new OperatorDTOMapper());
    }

    public String getOperatorIdsById(int operatorId) {
        String ids = "";
        String sql = "select * from Operator where operatorPid=" + operatorId;
        List<Operator> list = this.jdbcTemplate.query(sql, new OperatorDTOMapper());
        if (list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                ids = ids + ((Operator)list.get(i)).getOperatorId() + ",";
            }
        }

        if (ids.length() > 0) {
            ids = operatorId + "," + ids.substring(0, ids.length() - 1);
        } else {
            ids = String.valueOf(operatorId);
        }

        return ids;
    }

    public class OperatorDTOMapper implements RowMapper {
        public OperatorDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Operator dto = new Operator();
            dto.setOperatorId(rs.getInt("operatorId"));
            dto.setOperatorAccount(rs.getString("operatorAccount"));
            dto.setOperatorName(rs.getString("operatorName"));
            dto.setOperatorPwd(rs.getString("operatorPwd"));
            dto.setIsLogin(rs.getInt("isLogin"));
            dto.setWorkCard(rs.getString("workCard"));
            dto.setCardId(rs.getString("cardId"));
            dto.setEmail(rs.getString("email"));
            dto.setPhone(rs.getString("phone"));
            dto.setLoginTimes(rs.getInt("loginTimes"));
            dto.setIsUsed(rs.getInt("isUsed"));
            dto.setCustId(rs.getInt("CustId"));
            dto.setDepartId(rs.getString("departId"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setDept(OperatorDaoImpl.this.departDao.getDepartInfoById(rs.getString("departId")));
            dto.setCust(OperatorDaoImpl.this.custDao.getCustomerById(rs.getInt("CustId")));
            dto.setArea(OperatorDaoImpl.this.areaInfoDao.getAreaInfoById(rs.getString("areaId")));
            List<OperRole> roleList = OperatorDaoImpl.this.operatorRoleDao.getOperRoleByOperId(dto.getOperatorId());
            String rolesId = "";
            String roleName = "";

            OperRole operRole;
            for(Iterator var8 = roleList.iterator(); var8.hasNext(); roleName = roleName + operRole.getOrName() + "；") {
                operRole = (OperRole)var8.next();
                rolesId = rolesId + operRole.getOrId() + ",";
            }

            if (rolesId == "") {
                dto.setRoleId((String)null);
                dto.setRoleName((String)null);
            } else {
                dto.setRoleId(rolesId.substring(0, rolesId.length() - 1));
                dto.setRoleName(roleName.substring(0, roleName.length() - 1));
            }

            return dto;
        }
    }
}
