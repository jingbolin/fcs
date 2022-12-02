//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.base.dao.AreaInfoDao;
import com.yinhe.ec.cpps.ipparse.IPSeeker;
import com.yinhe.ec.cpps.model.OperRole;
import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.model.OperatorLog;
import com.yinhe.ec.cpps.system.dao.*;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.Tools;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private OperatorLogDao opLogdao;
    private OperatorLog operatorLog;
    @Resource
    private GenPkIdService genPkIdservice;
    @Resource
    private CustDao custDao;
    @Resource
    private OperatorRoleDao operatorRoleDao;
    @Resource
    private DepartDao departDao;
    @Resource
    private AreaInfoDao areaInfoDao;

    public LoginDaoImpl() {
    }

    public Operator userLogin(String userName, String loginIp, String pwd) throws DataAccessException {
        int resultSet = this.jdbcTemplate.queryForList("select count(*) from Operator where OperatorAccount = '" + userName + "' and IsLogin = 1").size();
        if (resultSet > 0) {
            Operator operator = (Operator)this.jdbcTemplate.queryForObject("select * from Operator where OperatorAccount = '" + userName + "'", new OperatorDTOMapper());
            this.operatorLog.setOlId(this.genPkIdservice.getPkIdByTable("OperatorLog", "OLID"));
            this.operatorLog.setOperator(operator);
            this.operatorLog.setOlDate(Tools.getNow());
            this.operatorLog.setOlFmName("系统登录");
            if (operator.getOperatorPwd().equals(pwd)) {
                this.operatorLog.setOlDesc("登录-成功");
            } else {
                this.operatorLog.setOlDesc("登录-密码错误");
            }

            this.operatorLog.setCustId(operator.getCustId());
            String area = "";

            try {
                String path = "";
                path = LoginDaoImpl.class.getResource("").toString();
                path = path.substring(5, path.indexOf("webapps")) + "/webapps/MusterList";
                path = URLDecoder.decode(path, "utf-8");
                IPSeeker ips = new IPSeeker("qqwry.dat", path);
                area = ips.getIPLocation(loginIp).getCountry() + ":" + ips.getIPLocation(loginIp).getArea();
            } catch (Exception var9) {

            }

            this.operatorLog.setOlIp(loginIp);
            this.operatorLog.setOlArea(area);
            this.opLogdao.addOperatorLog(this.operatorLog);
            return operator;
        } else {
            return null;
        }
    }

    public class OperatorDTOMapper implements RowMapper {
        public OperatorDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Operator dto = new Operator();
            dto.setOperatorId(rs.getInt("operatorID"));
            dto.setOperatorAccount(rs.getString("operatorAccount"));
            dto.setOperatorPwd(rs.getString("operatorPWD"));
            dto.setIsLogin(rs.getInt("isLogin"));
            dto.setWorkCard(rs.getString("workCard"));
            dto.setOperatorName(rs.getString("operatorName"));
            dto.setCardId(rs.getString("cardID"));
            dto.setEmail(rs.getString("email"));
            dto.setLoginTimes(rs.getInt("loginTimes"));
            dto.setPhone(rs.getString("phone"));
            dto.setDept(LoginDaoImpl.this.departDao.getDepartInfoById(rs.getString("departId")));
            dto.setCust(LoginDaoImpl.this.custDao.getCustomerById(rs.getInt("CustId")));
            dto.setArea(LoginDaoImpl.this.areaInfoDao.getAreaInfoById(rs.getString("areaId")));
            List<OperRole> roleList = LoginDaoImpl.this.operatorRoleDao.getOperRoleByOperId(dto.getOperatorId());
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
