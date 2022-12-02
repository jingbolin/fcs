//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.model.FuncMethod;
import com.yinhe.ec.cpps.system.dao.FuncMethodDao;
import com.yinhe.ec.cpps.util.ConstParam;
import org.apache.shiro.dao.DataAccessException;
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
public class FuncMethodDaoImp implements FuncMethodDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public FuncMethodDaoImp() {
    }




    public void addFuncMethod(FuncMethod funcMethod) throws DataAccessException {
        String sql = "insert into FuncMethod(FMID,FMName,FMURL,FMDesc,FMPID,FMCreateDate,FMPicUrl,IsMethod) values(?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new Object[]{funcMethod.getFmId(), funcMethod.getFmName(), funcMethod.getFmUrl(), funcMethod.getFmDesc(), funcMethod.getFmPid(), funcMethod.getFmCreateDate(), funcMethod.getFmPicUrl(), funcMethod.getIsMethod()});
    }

    public void deleteFuncMethod(String fmId, int custId) throws DataAccessException {
        String sql0 = "delete from rolefuncmethod where fmid='" + fmId + "' and custid=" + custId;
        String sql1 = "delete from operatorandfm where fmid='" + fmId + "' and custid=" + custId;
        String[] sql = new String[]{sql0, sql1};
        this.jdbcTemplate.batchUpdate(sql);
    }

    public void editFuncMethodByFmId(FuncMethod funcMethod) throws DataAccessException {
        String sql = "update FuncMethod set fmName=?,fmUrl=?,fmDesc=?,fMPicUrl=?,isMethod=? where fmId = ?";
        this.jdbcTemplate.update(sql, new Object[]{funcMethod.getFmName(), funcMethod.getFmUrl(), funcMethod.getFmDesc(), funcMethod.getFmPicUrl(), funcMethod.getIsMethod(), funcMethod.getFmId()});
    }

    public List<FuncMethod> getFuncMethod() {
        List<FuncMethod> list = this.jdbcTemplate.query("select * from FuncMethod order by fmId", new FuncMethodDTOMapper());
        return list;
    }

    public List<FuncMethod> getFuncMethodByOperatorAccount(String operatorAccount) {
        String sql = "select FuncMethod.* from Operator,OperRoleList,OperRole,RoleFuncMethod,FuncMethod  where Operator.OperatorId = OperRoleList.OPERATORID and OperRoleList.ORID=OperRole.orId and OperRole.orId = RoleFuncMethod.orId  and RoleFuncMethod.fmId = FuncMethod.fmId  and OperatorAccount = ?";
        return this.jdbcTemplate.query(sql, new Object[]{operatorAccount}, new FuncMethodDTOMapper());
    }

    @Override
    public void initFuncMethodToAdmin(List list) {

    }

    public List<FuncMethod> getFuncMethodByOrId(String orId) {
        return this.jdbcTemplate.query("select FuncMethod.* from FuncMethod,RoleFuncMethod where FuncMethod.FMID = RoleFuncMethod.FMID and RoleFuncMethod.ORID = '" + orId + "'", new FuncMethodDTOMapper());
    }

    public FuncMethod getFuncMethodRoot() {
        return (FuncMethod)this.jdbcTemplate.queryForObject("select * from FuncMethod where FMPID = '0'", new FuncMethodDTOMapper());
    }

    public List<FuncMethod> getKJFuncMethodByOperatorId(int operatorId) {
        String sql = "SELECT FUNCMETHOD.* FROM FUNCMETHOD, OPERATORANDFM WHERE FUNCMETHOD.FMID = OPERATORANDFM.FMID AND OPERATORANDFM.OPERATORID = ?";
        return this.jdbcTemplate.query(sql, new Object[]{operatorId}, new FuncMethodDTOMapper());
    }

//    public void initFuncMethodToAdmin(List<RoleFuncMethod> roleFuncMethodList) {
//        String deleteAdminFuncMethod = "delete from RoleFuncMethod where orId = ?";
//        this.jdbcTemplate.update(deleteAdminFuncMethod, new Object[]{"0000"});
//        String insertAdminFuncMethod = "insert into RoleFuncMethod(orId,fmId,RFMStatus,CUSTID) values(?,?,?,?)";
//        this.jdbcTemplate.batchUpdate(insertAdminFuncMethod, new BatchPreparedStatementSetter() {
//            public int getBatchSize() {
//                return roleFuncMethodList.size();
//            }
//
//            public void setValues(PreparedStatement ps, int index) throws SQLException {
//                RoleFuncMethod roleFuncMethod = (RoleFuncMethod)roleFuncMethodList.get(index);
//                ps.setString(1, roleFuncMethod.getOperRole().getOrId());
//                ps.setString(2, roleFuncMethod.getFuncMethod().getFmId());
//                ps.setString(3, "checked");
//                ps.setInt(4, roleFuncMethod.getOperRole().getCustId());
//            }
//        });
//    }

    public List<FuncMethod> getFuncListByOperatorAccount(String operatorAccount) {
        String sql = "select FuncMethod.* from Operator,OperRoleList,OperRole,RoleFuncMethod,FuncMethod  where Operator.OperatorId = OperRoleList.OPERATORID and OperRoleList.ORID=OperRole.orId and OperRole.orId = RoleFuncMethod.orId  and RoleFuncMethod.fmId = FuncMethod.fmId and IsMethod=0 and OperatorAccount = ? order by FuncMethod.fmId";
        return this.jdbcTemplate.query(sql, new Object[]{operatorAccount}, new FuncMethodDTOMapper());
    }

    public List<FuncMethod> getFuncMethodByOperatorId(int operatorId) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select FuncMethod.* from Operator,OperRoleList,OperRole,RoleFuncMethod,FuncMethod  where Operator.OperatorId = OperRoleList.OPERATORID and OperRoleList.ORID=OperRole.orId and OperRole.orId = RoleFuncMethod.orId  and RoleFuncMethod.fmId = FuncMethod.fmId and IsMethod=0 and FmDesc is not null and OPERATOR.OperatorId= ? order by FuncMethod.FMPID,FuncMethod.fmId";
        } else {
            sql = "select FuncMethod.* from Operator,OperRoleList,OperRole,RoleFuncMethod,FuncMethod  where Operator.OperatorId = OperRoleList.OPERATORID and OperRoleList.ORID=OperRole.orId and OperRole.orId = RoleFuncMethod.orId  and RoleFuncMethod.fmId = FuncMethod.fmId and IsMethod=0 and FmDesc<>'' and OPERATOR.OperatorId= ? order by FuncMethod.FMPID,FuncMethod.fmId";
        }

        return this.jdbcTemplate.query(sql, new Object[]{operatorId}, new FuncMethodDTOMapper());
    }

    public class FuncMethodDTOMapper implements RowMapper {
        public FuncMethodDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FuncMethod dto = new FuncMethod();
            dto.setFmId(rs.getString("fmId"));
            dto.setFmName(rs.getString("fmName"));
            dto.setFmUrl(rs.getString("fmUrl"));
            if (rs.getString("fmDesc") == null) {
                dto.setFmDesc("");
            } else {
                dto.setFmDesc(rs.getString("fmDesc"));
            }

            dto.setFmPid(rs.getString("FMPID"));
            dto.setFmCreateDate(rs.getString("FMCreateDate"));
            if (rs.getString("fmPicUrl") == null) {
                dto.setFmPicUrl("");
            } else {
                dto.setFmPicUrl(rs.getString("fmPicUrl"));
            }

            dto.setIsMethod(rs.getInt("isMethod"));
            return dto;
        }
    }
}
