//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.MusterDao;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLDecoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class MusterDaoImpl implements MusterDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public MusterDaoImpl() {
    }

    public List<ConnInfo> getConnInfo(int custId) {
        return this.jdbcTemplate.query("select * from CONNINFO where custId=" + custId, new ConnInfoDTOMapper());
    }

    public void addConnInfo(final ConnInfo connInfo) throws DataAccessException {
        String isql = "insert into CONNINFO(connNo,connStyle,connPara,custId) values (?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, connInfo.getConnNo());
                ps.setInt(2, connInfo.getConnStyle());
                ps.setString(3, connInfo.getConnPara());
                ps.setInt(4, connInfo.getCustId());
            }
        });
    }

    public void modConnInfo(final ConnInfo connInfo) throws DataAccessException {
        String usql = "update CONNINFO set connstyle=?,connpara=? where connNo=?";
        this.jdbcTemplate.update(usql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, connInfo.getConnStyle());
                ps.setString(2, connInfo.getConnPara());
                ps.setString(3, connInfo.getConnNo());
            }
        });
    }

    public void delConnInfoByNo(String connNo) throws DataAccessException {
        this.jdbcTemplate.update("delete from CONNINFO where connNo='" + connNo + "'");
    }

    public ConnInfo getConnInfoByConnNo(String connNo) {
        return (ConnInfo)this.jdbcTemplate.queryForObject("select * from CONNINFO where connNo = '" + connNo + "'", new ConnInfoDTOMapper());
    }

    public List<Muster> getMusterList(String orders) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select a.*,b.*,(select count(MeterNo) from METERINFO where MusterNO=a.musterNo)meterCount,(select count(MeterNo) from METERINFO where MusterNO=a.musterNo and TryFlag<>1)notTryCount,(select count(MeterNo) from METERINFO where MusterNO=a.musterNo and substr(READDATE,1,10)<'" + Tools.getForNDay(0) + "')notReadCount" + " from MUSTER a,CONNINFO b where " + orders + " and b.connNo=a.connNo order by a.musterNo";
        } else {
            sql = "select a.*,b.*,(select count(MeterNo) from METERINFO where MusterNO=a.musterNo)meterCount,(select count(MeterNo) from METERINFO where MusterNO=a.musterNo and TryFlag<>1)notTryCount,(select count(MeterNo) from METERINFO where MusterNO=a.musterNo and substring(READDATE,1,10)<'" + Tools.getForNDay(0) + "')notReadCount" + " from MUSTER a,CONNINFO b where " + orders + " and b.connNo=a.connNo order by a.musterNo";
        }

        return this.jdbcTemplate.query(sql, new MusterDTOMapper());
    }

    public void addMuster(final Muster muster) throws DataAccessException {
        String isql = "insert into MUSTER(musterNo,custId,connNo,musterPtl,manuId,musterAddr,accountNo,createDate,initFlag,readStyle,readServer,readServerPort,remark,areaId,batchCount,onlineState)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, muster.getMusterNo());
                ps.setInt(2, muster.getCustId());
                ps.setString(3, muster.getConnNo());
                ps.setInt(4, muster.getMusterPtl());
                ps.setInt(5, muster.getManuId());
                ps.setString(6, muster.getMusterAddr());
                ps.setString(7, muster.getAccountNo());
                ps.setString(8, muster.getCreateDate());
                ps.setInt(9, muster.getInitFlag());
                ps.setInt(10, muster.getReadStyle());
                ps.setInt(11, muster.getReadServer());
                ps.setInt(12, muster.getReadServerPort());
                ps.setString(13, muster.getRemark());
                ps.setString(14, muster.getAreaId());
                ps.setInt(15, muster.getBatchCount());
                ps.setInt(16, muster.getOnlineState());
            }
        });
    }

    public void modMuster(final Muster muster) throws DataAccessException {
        String usql = "update MUSTER set connNo=?,musterPtl=?,manuId=?,musterAddr=?,accountNo=?,readStyle=?,readServer=?,readServerPort=?,remark=?,areaId=?,batchCount=? where musterNo=?";
        this.jdbcTemplate.update(usql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, muster.getConnNo());
                ps.setInt(2, muster.getMusterPtl());
                ps.setInt(3, muster.getManuId());
                ps.setString(4, muster.getMusterAddr());
                ps.setString(5, muster.getAccountNo());
                ps.setInt(6, muster.getReadStyle());
                ps.setInt(7, muster.getReadServer());
                ps.setInt(8, muster.getReadServerPort());
                ps.setString(9, muster.getRemark());
                ps.setString(10, muster.getAreaId());
                ps.setInt(11, muster.getBatchCount());
                ps.setString(12, muster.getMusterNo());
            }
        });
    }

    public void delMusterByNo(String musterNo) throws DataAccessException {
        this.jdbcTemplate.update("delete from MUSTER where musterNo='" + musterNo + "'");
    }

    public Muster getMusterByMusterNo(String musterNo) {
        return (Muster)this.jdbcTemplate.queryForObject("select * from MUSTER where musterNo = '" + musterNo + "'", new MusterByNoDTOMapper());
    }

    public int[] changeMuster(String oldMusterNo, String newMusterNo, int operatorId, int changeFlag) throws DataAccessException {
        String sql1 = "insert into MUSTER (musterNo,custId,connNo,musterPtl,manuId,musterAddr,accountNo,createDate,initFlag,readStyle,readServer,readServerPort,remark,onlineState,lastDate,AreaId) select '" + newMusterNo + "',CustId,ConnNo,musterPtl,manuId,musterAddr,accountNo,createDate,0,readStyle,readServer,readServerPort,remark,onlineState,lastDate,AreaId from muster where musterno='" + oldMusterNo + "'";
        String sql2 = "update MeterInfo set musterNo='" + newMusterNo + "' where musterno='" + oldMusterNo + "'";
        String sql3 = "delete from Muster where musterNo='" + oldMusterNo + "'";
        String sql4 = "insert into MUSTERCHANGE(musterNo,custId,changeDate,changeUser,changeFlag,reason,remark)  select '" + newMusterNo + "',CustId,'" + Tools.getNow() + "','" + operatorId + "'," + changeFlag + ",'ERROR','' from muster where musterno='" + oldMusterNo + "'";
        String[] sql = new String[]{sql1, sql2, sql3, sql4};
        return this.jdbcTemplate.batchUpdate(sql);
    }

    public List<MeterInfo> getMeterListByMusterNo(String musterNo) {
        return this.jdbcTemplate.query("select METERNO,RECNO,COMPORT,BAUD,MeterPtl from METERINFO where musterNo='" + musterNo + "' order by RECNO", new MeterDTOMapper());
    }

    public List<MusterOnline> getMusterOnlineInfo() {
        String path = null;
        List<MusterOnline> list = new ArrayList();

        try {
            String str = MusterDaoImpl.class.getResource("").toString();
            str = str.substring(5, str.indexOf("webapps")) + "/webapps/MusterList/Muster.xml";
            path = URLDecoder.decode(str, "utf-8");
            File file = new File(path);
            if (file.exists()) {
                SAXBuilder sb = new SAXBuilder();
                Document doc = sb.build(file);
                Element root = doc.getRootElement();
                Element root2 = root.getChild("OnlineInfo");
                List<Element> listN = root2.getChildren("DataInfo");
                Iterator var11 = listN.iterator();

                while(var11.hasNext()) {
                    Element el = (Element)var11.next();
                    MusterOnline musDao = new MusterOnline();
                    musDao.setMusterNo(el.getChildText("MusterNO"));
                    musDao.setOnline(el.getChildText("Online"));
                    musDao.setIp(el.getChildText("IP"));
                    musDao.setPort(el.getChildText("Port"));
                    musDao.setLastDt(el.getChildText("LastDT"));
                    musDao.setAliveDt(el.getChildText("AliveDT"));
                    musDao.setAliveTimes(el.getChildText("AliveTimes"));
                    musDao.setOfflineDt(el.getChildText("OfflineDT"));
                    musDao.setOfflineTimes(el.getChildText("OfflineTimes"));
                    musDao.setRemark(el.getChildText("Remark"));
                    list.add(musDao);
                }
            } else {
                System.out.println("false");
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        return list;
    }

    public List<MeterProtocol> getMeterProtocol() {
        String sql = "select * from METERPROTOCOL order by MeterPTL";
        return this.jdbcTemplate.query(sql, new MeterProtocolDTOMapper());
    }

    public List<MusterProtocol> getMusterProtocol() {
        String sql = "select * from MUSTERPROTOCOL order by MusterPTL";
        return this.jdbcTemplate.query(sql, new MusterProtocolDTOMapper());
    }

    public MusterProtocol getMusterProtocolByPTL(int musterPtl) {
        String sql = "select * from MUSTERPROTOCOL where MusterPtl=" + musterPtl;
        return (MusterProtocol)this.jdbcTemplate.queryForObject(sql, new MusterProtocolDTOMapper());
    }

    public String getXuniMusterNo() {
        if (this.jdbcTemplate.queryForList("select * from MUSTER where musterno like '990%' ").size() > 0) {
            String sql = "select max(musterno)+1 from muster where musterno like '990%' ";
            return String.valueOf(this.jdbcTemplate.queryForObject(sql,Integer.class));
        } else {
            return "99000001";
        }
    }

    public class ConnInfoDTOMapper implements RowMapper {
        public ConnInfoDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConnInfo dto = new ConnInfo();
            dto.setConnNo(rs.getString("connNo"));
            dto.setConnPara(rs.getString("connPara"));
            dto.setConnStyle(rs.getInt("connStyle"));
            dto.setCustId(rs.getInt("custId"));
            return dto;
        }
    }

    public class MeterDTOMapper implements RowMapper {
        public MeterDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterInfo dto = new MeterInfo();
            dto.setMeterNo(rs.getString("METERNO"));
            dto.setRecNo(rs.getInt("RECNO"));
            dto.setComPort(rs.getInt("COMPORT"));
            dto.setBaud(rs.getInt("BAUD"));
            dto.setMeterPtl(rs.getInt("MeterPtl"));
            return dto;
        }
    }

    public class MeterProtocolDTOMapper implements RowMapper {
        public MeterProtocolDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterProtocol dto = new MeterProtocol();
            dto.setMeterPtl(rs.getInt("meterPtl"));
            dto.setPtlName(rs.getString("ptlName"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }

    public class MusterByNoDTOMapper implements RowMapper {
        public MusterByNoDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Muster dto = new Muster();
            new ConnInfo();
            new MusterProtocol();
            dto.setMusterNo(rs.getString("musterNo"));
            dto.setMusterAddr(rs.getString("musterAddr"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setInitFlag(rs.getInt("initFlag"));
            dto.setReadServer(rs.getInt("readServer"));
            dto.setReadServerPort(rs.getInt("readServerPort"));
            dto.setReadStyle(rs.getInt("readStyle"));
            dto.setAccountNo(rs.getString("accountNo"));
            dto.setRemark(rs.getString("remark"));
            dto.setConnNo(rs.getString("connNo"));
            dto.setMusterPtl(rs.getInt("musterPtl"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setOnlineState(rs.getInt("onlineState"));
            dto.setLastDate(rs.getString("lastDate"));
            ConnInfo cn = MusterDaoImpl.this.getConnInfoByConnNo(rs.getString("connNo"));
            dto.setCn(cn);
            MusterProtocol mptl = MusterDaoImpl.this.getMusterProtocolByPTL(rs.getInt("musterPtl"));
            dto.setMusterProtocol(mptl);
            return dto;
        }
    }

    public class MusterDTOMapper implements RowMapper {
        public MusterDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Muster dto = new Muster();
            ConnInfo cn = new ConnInfo();
            new MusterProtocol();
            dto.setMusterNo(rs.getString("musterNo"));
            dto.setMusterAddr(rs.getString("musterAddr"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setInitFlag(rs.getInt("initFlag"));
            dto.setReadStyle(rs.getInt("readStyle"));
            dto.setReadServer(rs.getInt("readServer"));
            dto.setReadServerPort(rs.getInt("readServerPort"));
            dto.setAccountNo(rs.getString("accountNo"));
            dto.setRemark(rs.getString("remark"));
            dto.setConnNo(rs.getString("connNo"));
            dto.setAreaId(rs.getString("areaId"));
            dto.setOnlineState(rs.getInt("onlineState"));
            dto.setLastDate(rs.getString("lastDate"));
            dto.setManuId(rs.getInt("manuId"));
            cn.setConnNo(rs.getString("connNo"));
            cn.setConnStyle(rs.getInt("connStyle"));
            cn.setConnPara(rs.getString("connPara"));
            dto.setCn(cn);
            dto.setMeterCount(rs.getInt("meterCount"));
            dto.setNotReadCount(rs.getInt("notReadCount"));
            dto.setNotTryCount(rs.getInt("notTryCount"));
            dto.setMusterPtl(rs.getInt("musterPtl"));
            MusterProtocol mptl = MusterDaoImpl.this.getMusterProtocolByPTL(rs.getInt("musterPtl"));
            dto.setMusterProtocol(mptl);
            dto.setBatchCount(rs.getInt("batchCount"));
            return dto;
        }
    }

    public class MusterProtocolDTOMapper implements RowMapper {
        public MusterProtocolDTOMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MusterProtocol dto = new MusterProtocol();
            dto.setMusterPtl(rs.getInt("MusterPtl"));
            dto.setPtlName(rs.getString("ptlName"));
            dto.setBatch(rs.getInt("batch"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }
}
