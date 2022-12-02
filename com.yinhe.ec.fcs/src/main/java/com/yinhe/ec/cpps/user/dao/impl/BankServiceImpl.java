//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.dao.impl;

import com.yinhe.ec.cpps.model.CebBankDetail;
import com.yinhe.ec.cpps.model.CebDuizhang;
import com.yinhe.ec.cpps.model.CebDuizhanghuizong;
import com.yinhe.ec.cpps.user.dao.BankServiceDao;
import com.yinhe.ec.cpps.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class BankServiceImpl implements BankServiceDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(BankServiceImpl.class);

    public BankServiceImpl() {
    }

    public String userBill(String param) {
        String decodeStr = "";
        byte[] encodeStrArry = null;
        String resultStr = "";
        String encodeStr = "";

        try {
            byte[] data = Base64.decodeBase64(param);
            byte[] paramStr = TripleDES.des3DecodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, data);
            decodeStr = new String(paramStr, "UTF-8");
            logger.info("userBill decodestr--->>" + decodeStr);
            Map paramMap = Tools.StrToMap(decodeStr, "|");
            final String phoneid = paramMap.get("Phoneid").toString();
            final String category = paramMap.get("category").toString();
            String appid = paramMap.get("appid").toString();
            String orders = "";
            if ("3".equals(category)) {
                orders = " and feetypemark=6 ";
            } else if ("4".equals(category)) {
                orders = " and feetypemark=7 ";
            } else if ("1".equals(category)) {
                orders = " and feetypemark=2 ";
            } else if ("2".equals(category)) {
                orders = " and feetypemark=1 ";
            } else {
                orders = " and 1<>1 ";
            }

//            byte[] encodeStrArry;
            if (!appid.equals("0012100002")) {
                resultStr = "code=4|message=参数验证错误|total=0|billno=|yearmonth=|itemname=|state=|fee=|latefeerate=|latefee=|actualfee=|category=|province=|city=|region=|cname=|unitname=|room_no=|accountname=|uniqueid=|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                encodeStr = Base64.encodeBase64String(encodeStrArry);
            } else {
                String orerNo = (String)this.jdbcTemplate.execute("{call GEN_USERORDER(?,?,?,?)}", new CallableStatementCallback<Object>() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                        cs.setString(1, "");
                        cs.setString(2, phoneid);
                        cs.setInt(3, Integer.parseInt(category));
                        cs.registerOutParameter(4, 12);
                        cs.execute();
                        return cs.getString(4);
                    }
                });
                if ("10".equals(category)) {
                    resultStr = "code=4|message=参数验证错误|total=0|billno=|yearmonth=|itemname=|state=|fee=|latefeerate=|latefee=|actualfee=|category=|province=|city=|region=|cname=|unitname=|room_no=|accountname=|uniqueid=|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                    encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                    encodeStr = Base64.encodeBase64String(encodeStrArry);
                } else if ("-1".equals(orerNo)) {
                    resultStr = "code=1|message=用户不存在|total=0|billno=|yearmonth=|itemname=|state=|fee=|latefeerate=|latefee=|actualfee=|category=|province=|city=|region=|cname=|unitname=|room_no=|accountname=|uniqueid=|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                    encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                    encodeStr = Base64.encodeBase64String(encodeStrArry);
                } else {
                    String sql = "";
                    String sql1 = "";
                    if (!"1".equals(category) && !"2".equals(category)) {
                        sql = "select feedetail.orderno as billno,case when length(feedetail.remark)>10 then substr(feedetail.remark,12,10) else feedetail.remark end yearmonth,feedetail.feeitemid, feeitemname as itemname,getlatefee(feedetail.userId,feedetail.feeItemId,feedetail.feeMonth,feedetail.theFee,1) as finefee,thefee,nvl(getlatefee(feedetail.userId,feedetail.feeItemId,feedetail.feeMonth,feedetail.theFee,1),0)+thefee as actualfee,onlinelateFeeratio as Latefeerate, feetypemark as category,userinfo.mobilephone, '河北省' as province,'三河市' as city,'燕郊开发区' as region, (select areaname from areainfo where areaid=t.areapid and rownum=1)cname, t.areaname as unitname,userinfo.useraddr as roomno,userinfo.username as accountname,feedetail.UserId as uniqueid,'' as Reserve1 from feedetail,userinfo,areainfo t,feeitemlist,feetype,latefeelist where userinfo.userid=feedetail.userid and t.areaid=userinfo.areaid and latefeelist.latefeeId=feeitemlist.latefeeId and feeitemlist.feeitemid=feedetail.feeitemid and feetype.feetypeid=feeitemlist.feetypeid and payflag=0 and mobilephone='" + phoneid + "' " + orders;
                        sql1 = "select count(*) from feedetail,userinfo,areainfo t,feeitemlist,feetype where userinfo.userid=feedetail.userid and t.areaid=userinfo.areaid and feeitemlist.feeitemid=feedetail.feeitemid and feetype.feetypeid=feeitemlist.feetypeid and payflag=0 and mobilephone='" + phoneid + "' " + orders;
                    } else {
                        sql = "select userdetail.orderno as billno,'' yearmonth,userdetail.feeitemid, feeitemname as itemname,0 as finefee,meterInfo.leftSum as thefee,0 as actualfee,0 as Latefeerate, feetypemark as category,userinfo.mobilephone, '河北省' as province,'三河市' as city,'燕郊开发区' as region, (select areaname from areainfo where areaid=t.areapid and rownum=1)cname, t.areaname as unitname,userinfo.useraddr as roomno,userinfo.username as accountname,userinfo.UserId as uniqueid,userdetail.meterNo as Reserve1 from userdetail,userinfo,areainfo t,feeitemlist,feetype,meterInfo where userinfo.userid=userdetail.userid and meterInfo.meterNo=userdetail.meterNo and t.areaid=userinfo.areaid and feeitemlist.feeitemid=userdetail.feeitemid and feetype.feetypeid=feeitemlist.feetypeid  and mobilephone='" + phoneid + "' " + orders;
                        sql1 = "select count(*) from userdetail,userinfo,areainfo t,feeitemlist,feetype where userinfo.userid=userdetail.userid and t.areaid=userinfo.areaid and feeitemlist.feeitemid=userdetail.feeitemid and feetype.feetypeid=feeitemlist.feetypeid and mobilephone='" + phoneid + "' " + orders;
                    }

                    logger.info("userBill sql--->>" + sql);
                    List<CebBankDetail> list = this.jdbcTemplate.query(sql, new CebBankDetailDTO());
                    int total = (Integer)this.jdbcTemplate.queryForObject(sql1, Integer.class);
                    logger.info("userBill FeeList--->>" + list.size() + ":" + total);
                    if (total == 0) {
                        resultStr = "code=0|message=用户不欠费|total=0|billno=|yearmonth=|itemname=|state=|fee=|latefeerate=|latefee=|actualfee=|category=|province=|city=|region=|cname=|unitname=|room_no=|accountname=|uniqueid=|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                        encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                        encodeStr = Base64.encodeBase64String(encodeStrArry);
                    } else {
                        String listStr = "";
                        resultStr = "code=0|message=交易成功|total=" + total + "|";

                        for(int i = 0; i < list.size(); ++i) {
                            listStr = listStr + "billno=" + ((CebBankDetail)list.get(i)).getBillno() + "|yearmonth=" + ((CebBankDetail)list.get(i)).getYearmonth() + "|itemname=" + ((CebBankDetail)list.get(i)).getItemname() + "|state=" + ((CebBankDetail)list.get(i)).getState() + "|fee=" + ((CebBankDetail)list.get(i)).getFee() + "|latefeerate=" + ((CebBankDetail)list.get(i)).getLatefeerate() + "|latefee=" + ((CebBankDetail)list.get(i)).getLatefee() + "|actualfee=" + ((CebBankDetail)list.get(i)).getActualfee() + "|category=" + category + "|province=" + ((CebBankDetail)list.get(i)).getProvince() + "|city=" + ((CebBankDetail)list.get(i)).getCity() + "|region=" + ((CebBankDetail)list.get(i)).getRegion() + "|cname=" + ((CebBankDetail)list.get(i)).getCname() + "|unitname=" + ((CebBankDetail)list.get(i)).getUnitname() + "|room_no=" + ((CebBankDetail)list.get(i)).getRoomno() + "|accountname=" + ((CebBankDetail)list.get(i)).getAccountname() + "|uniqueid=" + ((CebBankDetail)list.get(i)).getUniqueid() + "|Reserve1=" + ((CebBankDetail)list.get(i)).getReserve1() + "|Reserve2=|Reserve3=|Reserve4=,";
                        }

                        listStr = listStr.substring(0, listStr.length() - 1);
                        resultStr = resultStr + listStr;
                        encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                        encodeStr = Base64.encodeBase64String(encodeStrArry);
                    }
                }
            }

            logger.info("BankServiceImpl userBill resultStr--->>" + resultStr);
        } catch (Exception var20) {
            var20.printStackTrace();
        }

        return encodeStr;
    }

    public String userPay(String param) {
        String decodeStr = "";
        byte[] encodeStrArry = null;
        String resultStr = "";
        String encodeStr = "";

        try {
            byte[] paramStr = TripleDES.des3DecodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, Base64.decodeBase64(param));
            decodeStr = new String(paramStr, "UTF-8");
            logger.info("userPay decodeStr--->>" + decodeStr);
            Map paramMap = Tools.StrToMap(decodeStr, "|");
            String appid = paramMap.get("appid").toString();
            final String uniqueid = paramMap.get("uniqueid").toString();
            final String payfee = paramMap.get("payfee").toString();
            final String journalno = paramMap.get("journalno").toString();
            final String billno = paramMap.get("billno").toString();
            final String category = paramMap.get("category1").toString();
            final String reserve1 = paramMap.get("Reserve1").toString();
            int operatorId = 0;
            int payKind = 0;
            String sql = "";
            String no = "";
//            byte[] encodeStrArry;
            if (appid.equals("0012100002")) {
                if (!"3".equals(category) && !"4".equals(category)) {
                    if (!"1".equals(category) && !"2".equals(category)) {
                        resultStr = "code=4|message=参数验证错误|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                        encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                        encodeStr = Base64.encodeBase64String(encodeStrArry);
                    } else {
                        no = (String)this.jdbcTemplate.execute("{call CebBankPayFeeForWyfAndQnfProc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback<Object>() {
                            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                                cs.setString(1, UUIDUtil.getUUID());
                                cs.setString(2, uniqueid);
                                cs.setInt(3, 3);
                                cs.setInt(4, 3);
                                cs.setDouble(5, 0.0);
                                cs.setDouble(6, Double.parseDouble(payfee));
                                cs.setDouble(7, 0.0);
                                cs.setDouble(8, 0.0);
                                cs.setDouble(9, Double.parseDouble(payfee));
                                cs.setDouble(10, Double.parseDouble(payfee));
                                cs.setDouble(11, 0.0);
                                cs.setString(12, billno);
                                cs.setInt(13, 0);
                                cs.setString(14, reserve1);
                                cs.setString(15, journalno);
                                cs.setInt(16, Integer.parseInt(category));
                                cs.registerOutParameter(17, 12);
                                cs.execute();
                                return cs.getString(17);
                            }
                        });
                        if (journalno.equals(no)) {
                            resultStr = "code=0|message=缴费成功|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                            encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                            encodeStr = Base64.encodeBase64String(encodeStrArry);
                        } else {
                            resultStr = "code=2|message=缴费失败|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                            encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                            encodeStr = Base64.encodeBase64String(encodeStrArry);
                            sql = "update UserOrder set Status=3 where OrderNo='" + billno + "'";
                            this.jdbcTemplate.update(sql);
                        }
                    }
                } else {
                    sql = "select thefee, nvl(getlatefee(feedetail.userId,feedetail.feeItemId,feedetail.feeMonth,feedetail.theFee,1),0) as fineFee,nvl(getlatefee(feedetail.userId,feedetail.feeItemId,feedetail.feeMonth,feedetail.theFee,1),0)+thefee as actualfee  from feedetail,userinfo,areainfo t,feeitemlist,feetype where userinfo.userid=feedetail.userid and t.areaid=userinfo.areaid and feeitemlist.feeitemid=feedetail.feeitemid and feetype.feetypeid=feeitemlist.feetypeid and payflag=0 and userinfo.userid='" + uniqueid + "' and feedetail.orderNo='" + billno + "'";
                    List<CebBankDetail> list = this.jdbcTemplate.query(sql, new CebFeeDTO());
                    if (list.size() > 0) {
                        final Double fineFee = Double.parseDouble(((CebBankDetail)list.get(0)).getLatefee());
                        final Double theFee = Double.parseDouble(((CebBankDetail)list.get(0)).getFee());
                        logger.info("userPay--->>payfee:" + payfee + "  actualfee:" + ((CebBankDetail)list.get(0)).getActualfee() + "  fineFee:" + fineFee);
                        BigDecimal b1 = new BigDecimal(payfee);
                        BigDecimal b2 = new BigDecimal(((CebBankDetail)list.get(0)).getActualfee());
                        if (b1.subtract(b2).doubleValue() == 0.0 && !"".equals(payfee)) {
                            no = (String)this.jdbcTemplate.execute("{call CebBankPayFeeForWyfAndQnfProc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback<Object>() {
                                public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                                    cs.setString(1, UUIDUtil.getUUID());
                                    cs.setString(2, uniqueid);
                                    cs.setInt(3, 3);
                                    cs.setInt(4, 3);
                                    cs.setDouble(5, 0.0);
                                    cs.setDouble(6, theFee);
                                    cs.setDouble(7, fineFee);
                                    cs.setDouble(8, 0.0);
                                    cs.setDouble(9, Double.parseDouble(payfee));
                                    cs.setDouble(10, Double.parseDouble(payfee));
                                    cs.setDouble(11, 0.0);
                                    cs.setString(12, billno);
                                    cs.setInt(13, 0);
                                    cs.setString(14, "");
                                    cs.setString(15, journalno);
                                    cs.setInt(16, Integer.parseInt(category));
                                    cs.registerOutParameter(17, 12);
                                    cs.execute();
                                    return cs.getString(17);
                                }
                            });
                            if (journalno.equals(no)) {
                                resultStr = "code=0|message=缴费成功|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                                encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                                encodeStr = Base64.encodeBase64String(encodeStrArry);
                            } else {
                                resultStr = "code=2|message=缴费失败|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                                encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                                encodeStr = Base64.encodeBase64String(encodeStrArry);
                                sql = "update UserOrder set Status=3 where OrderNo='" + billno + "'";
                                this.jdbcTemplate.update(sql);
                            }
                        } else {
                            resultStr = "code=3|message=缴费金额不正确|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                            encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                            encodeStr = Base64.encodeBase64String(encodeStrArry);
                            sql = "update UserOrder set Status=3 where OrderNo='" + billno + "'";
                            this.jdbcTemplate.update(sql);
                        }
                    } else {
                        resultStr = "code=3|message=缴费金额不正确|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                        encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                        encodeStr = Base64.encodeBase64String(encodeStrArry);
                        sql = "update UserOrder set Status=3 where OrderNo='" + billno + "'";
                        this.jdbcTemplate.update(sql);
                    }
                }
            } else {
                resultStr = "code=4|message=参数验证错误|Reserve1=|Reserve2=|Reserve3=|Reserve4=";
                encodeStrArry = TripleDES.des3EncodeCBC("3B5032E9-67C6-48EB-BB9A-".getBytes("UTF-8"), CebbankParam.KEYIV, resultStr.getBytes("UTF-8"));
                encodeStr = Base64.encodeBase64String(encodeStrArry);
                sql = "update UserOrder set Status=3 where OrderNo='" + billno + "'";
                this.jdbcTemplate.update(sql);
            }
        } catch (Exception var24) {
            var24.printStackTrace();
        }

        logger.info("userPay result--->>" + resultStr + "|" + encodeStr);
        return encodeStr;
    }


    @Override
    public void writeCvsToDb(List list) {
        String msgStr = "";
        String isql = "";

        for(int i = 0; i < list.size(); ++i) {
            msgStr = ((String)list.get(i)).toString();
            final String[] bill_array = StringUtils.split(msgStr, ",");
            if (bill_array.length >= 7) {
                try {
                    isql = "insert into Ceb_duizhang(UserId,OrderNo,TrxDate,Amount,BankRspNo,Feetypemark,UserName,BillState) values (?,?,?,?,?,?,?,?)";
                    this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
                        public void setValues(PreparedStatement ps) throws SQLException {
                            ps.setString(1, bill_array[0]);
                            ps.setString(2, bill_array[1]);
                            ps.setString(3, bill_array[2]);
                            ps.setDouble(4, Double.parseDouble(bill_array[3]) / 100.0);
                            ps.setString(5, bill_array[4]);
                            ps.setInt(6, Integer.parseInt(bill_array[5]));
                            ps.setString(7, bill_array[6]);
                            ps.setInt(8, 1);
                        }
                    });
                } catch (Exception var7) {
                    System.err.println(var7.getMessage());
                }
            }
        }

    }

    public List<CebDuizhang> getCebDuizhangInfo(int page, int rows, String orders) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = CommonSQL.searchByPage("select ceb_duizhang.UserId,OrderNo,TrxDate,Amount,BankRspNo,Feetypemark,ceb_duizhang.UserName,BillState,userInfo.userAddr,userInfo.mobilePhone from ceb_duizhang,userInfo where userInfo.userId=ceb_duizhang.userId " + orders + " order by TrxDate", page, rows);
        } else {
            sql = CommonSQL.searchByPageForSql2005(" ceb_duizhang.UserId,OrderNo,TrxDate,Amount,BankRspNo,Feetypemark,ceb_duizhang.UserName,BillState,userInfo.userAddr,userInfo.mobilePhone from ceb_duizhang,userInfo where userInfo.userId=ceb_duizhang.userId " + orders + " ", page, rows, "TrxDate", "asc");
        }

        List<CebDuizhang> list = this.jdbcTemplate.query(sql, new CebDuizhangDTO());
        return list;
    }

    public int getCebDuizhangInfoTotal(String orders) {
        String sql = "select count(OrderNo) from ceb_duizhang,userInfo where userInfo.userId=ceb_duizhang.userId " + orders;
        return (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<CebDuizhanghuizong> getCebDuizhangHuizong(String startDate, String endDate, String orders) {
        String sql = "select MarkDay,sysPaytotal,sysCount,(select sum(amount) from ceb_duizhang where (substr(trxDate,1,4)||'-'||substr(trxDate,5,2)||'-'||substr(trxDate,7,2)) = MarkDay) as cebPayTotal,(select count(orderNo) from ceb_duizhang where (substr(trxDate,1,4)||'-'||substr(trxDate,5,2)||'-'||substr(trxDate,7,2)) = MarkDay) as cebCount  from (  select MarkDay, sum(PayFee) as sysPaytotal ,count(MarkDay) as sysCount from (  select substr(payDate,1,10) as MarkDay,PayFee from payfee where operatorId=3 and payKind=3 and substr(payDate,1,10)>='" + startDate + "' and substr(payDate,1,10)<='" + endDate + "' " + ")t group by MarkDay order by MarkDay " + ")p";
        return this.jdbcTemplate.query(sql, new CebDuizhanghuizongDTO());
    }

    public class CebBankDetailDTO implements RowMapper {
        public CebBankDetailDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CebBankDetail dto = new CebBankDetail();
            dto.setBillno(rs.getString("billno"));
            if (!"".equals(rs.getString("yearmonth")) && rs.getString("yearmonth") != null) {
                if (rs.getString("yearmonth").replaceAll("-", "").length() == 4) {
                    dto.setYearmonth(rs.getString("yearmonth").replaceAll("-", "") + "1231");
                } else {
                    dto.setYearmonth(rs.getString("yearmonth").replaceAll("-", ""));
                }
            } else {
                dto.setYearmonth("");
            }

            dto.setItemname(rs.getString("itemname"));
            if (rs.getDouble("finefee") > 0.0) {
                dto.setState("费用超期");
            } else {
                dto.setState("正常欠费");
            }

            dto.setFee(Tools.formatFloat(rs.getDouble("thefee")));
            dto.setLatefeerate(rs.getInt("latefeerate"));
            dto.setLatefee(Tools.formatFloat(rs.getDouble("finefee")));
            dto.setActualfee(Tools.formatFloat(rs.getDouble("actualfee")));
            dto.setCategory(rs.getInt("category"));
            dto.setProvince(rs.getString("province"));
            dto.setCity(rs.getString("city"));
            dto.setRegion(rs.getString("region"));
            dto.setCname(rs.getString("cname"));
            dto.setUnitname(rs.getString("unitname"));
            dto.setRoomno(rs.getString("roomno"));
            dto.setAccountname(rs.getString("accountname"));
            dto.setUniqueid(rs.getString("uniqueid"));
            dto.setReserve1(rs.getString("reserve1"));
            dto.setReserve2("");
            dto.setReserve3("");
            dto.setReserve4("");
            return dto;
        }
    }

    public class CebDuizhangDTO implements RowMapper {
        public CebDuizhangDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CebDuizhang dto = new CebDuizhang();
            dto.setUserId(rs.getString("userId"));
            dto.setUserName(rs.getString("userName"));
            dto.setUserAddr(rs.getString("userAddr"));
            dto.setMobilePhone(rs.getString("mobilePhone"));
            dto.setOrderNo(rs.getString("orderNo"));
            dto.setTrxDate(rs.getString("trxDate"));
            dto.setAmount(rs.getString("amount"));
            dto.setBankrspNo(rs.getString("bankrspNo"));
            dto.setFeetypemark(rs.getString("feetypemark"));
            dto.setBillState(rs.getString("billState"));
            return dto;
        }
    }

    public class CebDuizhanghuizongDTO implements RowMapper {
        public CebDuizhanghuizongDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CebDuizhanghuizong dto = new CebDuizhanghuizong();
            dto.setMarkDay(rs.getString("markDay"));
            dto.setSysPaytotal(rs.getDouble("sysPaytotal"));
            dto.setSysCount(rs.getInt("sysCount"));
            dto.setCebPaytotal(rs.getDouble("cebPaytotal"));
            dto.setCebCount(rs.getInt("cebCount"));
            return dto;
        }
    }

    public class CebFeeDTO implements RowMapper {
        public CebFeeDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CebBankDetail dto = new CebBankDetail();
            dto.setFee(Tools.formatFloat(rs.getDouble("thefee")));
            dto.setLatefee(Tools.formatFloat(rs.getDouble("finefee")));
            dto.setActualfee(Tools.formatFloat(rs.getDouble("actualfee")));
            return dto;
        }
    }
}
