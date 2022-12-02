//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.model.OperatorLog;
import com.yinhe.ec.cpps.system.dao.OperatorDao;
import com.yinhe.ec.cpps.system.dao.OperatorLogDao;
import com.yinhe.ec.cpps.util.CommonSQL;
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
public class OperatorLogDaoImpl implements OperatorLogDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private OperatorDao operatorDao;

    public OperatorLogDaoImpl() {
    }

    public Integer addOperatorLog(OperatorLog operatorLog) throws DataAccessException {
        String sql = "insert into OperatorLog(OLID,CUSTID,OperatorID,OLDATE,OLFmName,OLDesc,OLIP,OLAREA) values('" + operatorLog.getOlId() + "'," + operatorLog.getCustId() + ",'" + operatorLog.getOperator().getOperatorId() + "','" + operatorLog.getOlDate() + "','" + operatorLog.getOlFmName() + "','" + operatorLog.getOlDesc() + "','" + operatorLog.getOlIp() + "','" + operatorLog.getOlArea() + "')";
        return this.jdbcTemplate.update(sql);
    }

    public List<OperatorLog> getOperatorLog(int page, int rows, String condition) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("SELECT OLID,Operator.OperatorID,OLDATE,OLFmName,OLDesc,OLIP,OLAREA from OperatorLog,Operator where " + condition + " ORDER BY OLDATE desc ", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" OLID,Operator.OperatorID,OLDATE,OLFmName,OLDesc,OLIP,OLAREA from OperatorLog,Operator WHERE " + condition + " ", page, rows, "OLDATE", "desc");
        }

        List<OperatorLog> list = this.jdbcTemplate.query(sql, new OperatorLogDTOMapper());
        return list;
    }

    public Integer getTotal(String order) {
        return this.jdbcTemplate.queryForList(CommonSQL.searchCount("operatorLog,Operator", order)).size();
    }

    public class OperatorLogDTOMapper implements RowMapper {
        public OperatorLogDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            OperatorLog dto = new OperatorLog();
            dto.setOlId(rs.getString("olId"));
            Operator operator = OperatorLogDaoImpl.this.operatorDao.getOperatorById(rs.getInt("OperatorID"));
            dto.setOperator(operator);
            dto.setOlDate(rs.getString("OLDATE"));
            dto.setOlFmName(rs.getString("OLFmName"));
            if (rs.getString("OLDesc").indexOf(":") >= 0) {
                dto.setOlDesc(rs.getString("OLDesc").substring(0, rs.getString("OLDesc").indexOf(":")));
            } else {
                dto.setOlDesc(rs.getString("OLDesc"));
            }

            dto.setOlIp(rs.getString("olIp"));
            dto.setOlArea(rs.getString("olArea"));
            return dto;
        }
    }
}
