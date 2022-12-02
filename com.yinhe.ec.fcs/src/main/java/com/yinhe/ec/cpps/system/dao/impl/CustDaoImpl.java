//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.model.Customer;
import com.yinhe.ec.cpps.system.dao.BaseParamDao;
import com.yinhe.ec.cpps.system.dao.CustDao;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import com.yinhe.ec.cpps.util.DESPlus;
import com.yinhe.ec.cpps.util.MD5;
import com.yinhe.ec.cpps.util.Tools;
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
public class CustDaoImpl implements CustDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private BaseParamDao baseParamDao;
    @Resource
    private GenPkIdService genPkIdservice;

    public CustDaoImpl() {
    }

    public void addCustomer(final Customer customer) throws DataAccessException {
        String isql = "insert into CUSTOMER(CustID,CustNo,CustName,CustAddr,CustIP,CustPerson,CustPhone,CustLogo,RegFlag,CreateUser,CreateDate, bankName,merchantId,merchantName,remark) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, customer.getCustId());
                ps.setString(2, customer.getCustNo());
                ps.setString(3, customer.getCustName());
                ps.setString(4, customer.getCustAddr());
                ps.setString(5, customer.getCustIp());
                ps.setString(6, customer.getCustPerson());
                ps.setString(7, customer.getCustPhone());
                ps.setString(8, customer.getCustLogo());
                ps.setInt(9, 0);
                ps.setString(10, customer.getCreateUser());
                ps.setString(11, customer.getCreateDate());
                ps.setString(12, customer.getBankName());
                ps.setString(13, customer.getMerchantId());
                ps.setString(14, customer.getMerchantName());
                ps.setString(15, customer.getRemark());
            }
        });
        String sql1 = "insert into BASEPARAM(CustId,UserIdCode,TotalLen,GenMDosageDay,GenMDosageTime,CalculateDay,CalculateTime,MfrpDay,ChargeFlag,DosageDot,FeeDot,PreBakYear,preUserId,Type1,Type2,Type3,Type4,Type5,Type6,Type7) values(" + customer.getCustId() + ",1,10,1,'12:00:00',2,'12:00:00',1,0,2,2,6,'',1,1,0,0,1,1,0)";
        String departId = this.genPkIdservice.getPkNoByTable("departInfo", "departId", 4);
        String sql2 = "insert into DEPARTINFO(departId,departName,departPid,CustId,CreateDate,remark) values ('" + departId + "','" + customer.getCustName() + "','0'," + customer.getCustId() + ",'" + customer.getCreateDate() + "','')";
        String areaId = this.genPkIdservice.getPkNoByTable("AreaInfo", "AreaId", 4);
        String sql3 = "insert into AREAINFO(areaId,custId,areaName,areaPid,createDate,remark,areaFullName,businessFlag) values ('" + areaId + "'," + customer.getCustId() + ",'" + customer.getCustName() + "','0','" + customer.getCreateDate() + "','','',1)";
        String orId = this.genPkIdservice.getPkNoByTable("OperRole", "ORID", 4);
        String sql4 = "insert into OPERROLE(ORID,ORName,ORCreateDate,CustId,ORPID) values('" + orId + "','" + customer.getCustName() + "','" + customer.getCreateDate() + "'," + customer.getCustId() + ",'0000')";
        String sql5 = "insert into ROLEFUNCMETHOD(FmId,OrId,CustId,RfmStatus) select fmid,'" + orId + "'," + customer.getCustId() + ",'checked' from ROLEFUNCMETHOD " + "where orid='0000'  and fmid not in ('0017','0018','0125','2601','2603','0159','0065','0066','0067','4044')";
        int operatorId = this.genPkIdservice.getPkNoForInt("operator", "operatorId");
        String pwd = MD5.getDigestedString("666666");
        String sql6 = "insert into OPERATOR(OPERATORID,CUSTID,DEPARTID,OPERATORACCOUNT,OPERATORNAME,OPERATORPWD,ISLOGIN,WORKCARD,CARDID,EMAIL,LOGINTIMES,PHONE,ISUSED,CREATEDATE,OPERATORPID,REMARK,AREAID) values(" + operatorId + "," + customer.getCustId() + ",'" + departId + "','" + customer.getCustNo() + "_admin','" + customer.getCustName() + "','" + pwd + "',1," + "'','','',0,'',1,'" + customer.getCreateDate() + "','2','','" + areaId + "')";
        String sql7 = "insert into OPERROLELIST(orid,operatorid,custid) values('" + orId + "'," + operatorId + "," + customer.getCustId() + ")";
        String subNo = this.genPkIdservice.getPkNoByTable("SubInfo", "SubNo", 6);
        String sql8 = "insert into SUBINFO(subNo,custId,subName,subAddr,subDate,subUser,subPhone,remark) values ('" + subNo + "'," + customer.getCustId() + ",'" + customer.getCustName() + "','" + customer.getCustName() + "','" + customer.getCreateDate() + "','" + customer.getCustNo() + "_admin','','')";
        int mboxId = this.genPkIdservice.getPkNoForInt("MboxInfo", "MboxId");
        String sql9 = "insert into MBOXINFO(mboxId,mboxNo,custId,Remark) values (" + mboxId + ",'" + customer.getCustName() + "'," + customer.getCustId() + ",'')";
        int manuId = this.genPkIdservice.getPkNoForInt("manufacture", "manuId");
        String sql10 = "insert into MANUFACTURE(manuId,manuName,custId) values (" + manuId + ",'" + customer.getCustName() + "'," + customer.getCustId() + ")";
        String connNo = this.genPkIdservice.getPkNoByTable("ConnInfo", "ConnNO", 4);
        String sql11 = "insert into CONNINFO(connNo,connStyle,connPara,custId) values ('" + connNo + "',2,'2:127.0.0.1:20000'," + customer.getCustId() + ")";
        String[] bsql = new String[]{sql1, sql2, sql3, sql4, sql5, sql6, sql7, sql8, sql9, sql10, sql11};
        this.jdbcTemplate.batchUpdate(bsql);
    }

    public void modCustomer(final Customer customer) throws DataAccessException {
        String isql = "update CUSTOMER set CustName=?,CustAddr=?,CustIP=?,CustPerson=?,CustPhone=?,CustLogo=?, bankName=?,merchantId=?,merchantName=?,remark=?  where CustID=?";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, customer.getCustName());
                ps.setString(2, customer.getCustAddr());
                ps.setString(3, customer.getCustIp());
                ps.setString(4, customer.getCustPerson());
                ps.setString(5, customer.getCustPhone());
                ps.setString(6, customer.getCustLogo());
                ps.setString(7, customer.getBankName());
                ps.setString(8, customer.getMerchantId());
                ps.setString(9, customer.getMerchantName());
                ps.setString(10, customer.getRemark());
                ps.setInt(11, customer.getCustId());
            }
        });
    }

    public String delCustomerById(int custId) throws DataAccessException {
        if ((Integer)this.jdbcTemplate.queryForObject("select count(userId) from USERINFO where custId=" + custId, Integer.class) != 0 && (Integer)this.jdbcTemplate.queryForObject("select count(*) from MUSTER where custId=" + custId, Integer.class) != 0) {
            return "客户已被使用，不允许删除！";
        } else {
            String sql1 = "delete from BASEPARAM where custId=" + custId;
            String sql2 = "delete from ROLEFUNCMETHOD where CustId=" + custId;
            String sql3 = "delete from OPERATORANDFM where CustId=" + custId;
            String sql4 = "delete from OPERROLELIST where CustId=" + custId;
            String sql5 = "delete from OPERROLE where CustId=" + custId;
            String sql6 = "delete from OPERATOR where CustId=" + custId;
            String sql7 = "delete from DEPARTINFO where custId=" + custId;
            String sql8 = "delete from AREAINFO where custId=" + custId;
            String sql9 = "delete from SUBINFO where CustId=" + custId;
            String sql10 = "delete from MBOXINFO where CustId=" + custId;
            String sql11 = "delete from MANUFACTURE where CustId=" + custId;
            String sql12 = " delete from  OPERATORLOG  where CustId=" + custId;
            String sql13 = "delete from CUSTOMER where CustId=" + custId;
            String[] bsql = new String[]{sql1, sql2, sql3, sql4, sql5, sql6, sql7, sql8, sql9, sql10, sql11, sql12, sql13};
            this.jdbcTemplate.batchUpdate(bsql);
            return "删除成功！";
        }
    }

    public Customer getCustomerById(int custId) {
        List<Customer> list = this.jdbcTemplate.query("select * from CUSTOMER where CustId=?", new Object[]{custId}, new CustomerDTO());
        return list.size() == 0 ? new Customer() : (Customer)list.get(0);
    }

    public List<Customer> getCustomerInfo(int custId) {
        String sql = "";
        if (custId == 0) {
            sql = "select * from CUSTOMER order by CustId";
        } else {
            sql = "select * from CUSTOMER where CustId=" + custId + " order by CustId";
        }

        return this.jdbcTemplate.query(sql, new CustomerDTO());
    }

    public String registerInfo(int custId, String regEnd, String regCode) {
        String regStart = "";
        String regEndDt = "";

        try {
            DESPlus dp = new DESPlus();
            regStart = Tools.getForNRDay(0) + "|" + Tools.getMacByIp();
            regEndDt = regEnd + "|" + Tools.getMacByIp();
            String sql = "update CUSTOMER set regflag=1,RegStartDate='" + dp.encrypt(regStart) + "',RegEndDate='" + dp.encrypt(regEndDt) + "',regcode='" + regCode + "' where CustId='" + custId + "' ";
            this.jdbcTemplate.update(sql);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return regEnd;
    }

    public int getCountCustomer(String custNo) {
        return this.jdbcTemplate.queryForObject("select count(CustId) from CUSTOMER where custNo='" + custNo + "'",Integer.class);
    }

    public Customer getCustomerByNo(String custNo) {
        List<Customer> list = this.jdbcTemplate.query("select * from CUSTOMER where CustNo=?", new Object[]{custNo}, new CustomerDTO());
        return list.size() == 0 ? new Customer() : (Customer)list.get(0);
    }

    public class CustomerDTO implements RowMapper {
        public CustomerDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer dto = new Customer();
            String endDt = "";
            String regCode = "";
            String regEndDate = "";
            dto.setCustId(rs.getInt("CustId"));
            dto.setCustNo(rs.getString("custNo"));
            dto.setCustName(rs.getString("CustName"));
            dto.setCustAddr(rs.getString("CustAddr"));
            dto.setCustIp(rs.getString("CustIP"));
            dto.setCustPerson(rs.getString("CustPerson"));
            dto.setCustPhone(rs.getString("CustPhone"));
            dto.setCustLogo(rs.getString("custLogo"));
            dto.setRemark(rs.getString("Remark"));
            dto.setBaseParam(CustDaoImpl.this.baseParamDao.getBaseParam(rs.getInt("custId")));
            dto.setRegFlag(rs.getInt("regFlag"));
            dto.setRegStartDate(rs.getString("regStartDate"));
            dto.setBankName(rs.getString("bankName"));
            dto.setMerchantId(rs.getString("merchantId"));
            dto.setMerchantName(rs.getString("merchantName"));
            regEndDate = rs.getString("regEndDate");

            try {
                DESPlus des = new DESPlus();
                if (!"".equals(regEndDate) && regEndDate != null) {
                    endDt = des.decrypt(regEndDate);
                    endDt = endDt.substring(0, endDt.indexOf("|"));
                } else {
                    endDt = "19000101";
                }

                if (!"".equals(rs.getString("regCode")) && rs.getString("regCode") != null) {
                    regCode = des.decrypt(rs.getString("regCode"));
                } else {
                    regCode = "";
                }
            } catch (Exception var8) {
                endDt = "19000101";
                var8.printStackTrace();
            }

            dto.setRegEndDate(endDt);
            dto.setRegCode(regCode);
            return dto;
        }
    }
}
