//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.base.dao.CmdDao;
import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.util.YinheFrame;
import com.yinhe.ec.cpps.util.YinheWaterFrame188;
import com.yinhe.ec.cpps.ycmeter.dao.YinheDao;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class YinheDaoImpl implements YinheDao {
    private static final Logger logger = Logger.getLogger(YinheDaoImpl.class);
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CmdDao cmdDao;

    public YinheDaoImpl() {
    }

    public Map<String, Object> deviceDatas(String deviceId, String reciveFrame, String commandId) {
        Map<String, Object> resultMap = new HashMap();
        String meterNo = "";
        String readTime = Tools.getNowSecond();
        String dataType = "";
        String meterData = "";
        String reciveDesc = "";
        int custId = 0;
        String sysMeterNo = "";
        String imsi = "";
        String typeId = "";
        new NbMeterInfo();
        NbMeterInfo meter = this.getNbMeterInfoByClause(" and deviceId='" + deviceId + "' ");
        if (!"".equals(reciveFrame) && !"".equals(meter.getDeviceId())) {
            typeId = String.valueOf(meter.getTypeId());
            Map<String, String> map = null;
            if (meter.getTypeId() == 2) {
                map = YinheWaterFrame188.decodeFrame(reciveFrame);
            } else {
                map = YinheFrame.decodeFrame(reciveFrame);
            }

            if (map != null) {
                meterNo = ((String)map.get("meterNo")).toString();
                dataType = ((String)map.get("commandCode")).toString();
                meterData = ((String)map.get("value")).toString();
                reciveDesc = ((String)map.get("reciveDesc")).toString();
                custId = meter.getCustId();
                sysMeterNo = this.getMeterNoByDeviceId(deviceId, meterNo);
                imsi = this.getImsiByDeviceId(deviceId, meterNo);
                if (!meterNo.equals(sysMeterNo)) {
                    logger.info("上报数据表号和系统不一致===上报deviceId:" + deviceId + "  ===上报表号:" + meterNo + "  ===系统表号:" + sysMeterNo);
                }

                String sql = "";
                String state;
                if ("9C".equals(dataType)) {
                    if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                        if ((Integer)this.jdbcTemplate.queryForObject("select count(dataItem) from cmd where cmdId in (select sendtype from NBSENDDATA where commandId='" + commandId + "') and rownum=1", Integer.class) > 0) {
                            state = (String)this.jdbcTemplate.queryForObject("select dataItem from cmd where cmdId in (select sendtype from NBSENDDATA where commandId='" + commandId + "') and rownum=1", String.class);
                            dataType = state;
                        }
                    } else if ((Integer)this.jdbcTemplate.queryForObject("select count(dataItem) from cmd where cmdId in (select sendtype from NBSENDDATA where commandId='" + commandId + "') limit 1", Integer.class) > 0) {
                        state = (String)this.jdbcTemplate.queryForObject("select dataItem from cmd where cmdId in (select sendtype from NBSENDDATA where commandId='" + commandId + "') limit 1", String.class);
                        dataType = state;
                    }
                }

                if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from YinheMeterData where deviceId='" + deviceId + "' and readTime='" + readTime + "'", Integer.class) == 0) {
                    sql = "insert into YinheMeterData(meterNo,deviceId,readTime,custId,meterData,dataType,reciveFrame,commandId) values ('" + meterNo + "','" + deviceId + "','" + readTime + "'," + custId + ",'" + meterData + "','" + dataType + "','" + reciveFrame + "','" + commandId + "')";
                    this.jdbcTemplate.update(sql);
                }

                logger.info("上报数据或命令回复===deviceId:" + deviceId + "  ===reciveFrame:" + reciveFrame + "  ===commandId:" + commandId);
                logger.info("上报数据或命令回复===meterNo:" + meterNo + " ===dataType:" + dataType + "  ===meterData:" + meterData + "  ===readTime:" + readTime);
                this.jdbcTemplate.update("UPDATE NBMETERINFO SET uploadDeviceId='" + deviceId + "',UPLOADNODEID='" + imsi + "' WHERE meterNo='" + meterNo + "'");
                if ("33333333".equals(dataType)) {
                    this.jdbcTemplate.update("UPDATE NBMETERINFO SET DOSAGESUM=" + meterData + ",READTIME='" + readTime + "' WHERE meterNo='" + meterNo + "'");
                    this.jdbcTemplate.update("UPDATE METERINFO set DOSAGESUM=" + meterData + ",readDate='" + readTime + "' where meterNo='" + meterNo + "'");
                }

                if ("36383337".equals(dataType)) {
                    this.jdbcTemplate.update("UPDATE NBMETERINFO SET switchState=" + meterData + " WHERE meterNo='" + meterNo + "'");
                    this.jdbcTemplate.update("UPDATE METERINFO set onState=" + meterData + " where meterNo='" + meterNo + "'");
                }

                if ("901F".equals(dataType)) {
                    state = ((String)map.get("state")).toString();
                    this.jdbcTemplate.update("UPDATE NBMETERINFO SET DOSAGESUM=" + meterData + ",READTIME='" + readTime + "',switchState=" + state + " WHERE meterNo='" + meterNo + "'");
                    this.jdbcTemplate.update("UPDATE METERINFO set DOSAGESUM=" + meterData + ",readDate='" + readTime + "',onState=" + state + " where meterNo='" + meterNo + "'");
                }

                if ("A017".equals(dataType)) {
                    this.jdbcTemplate.update("UPDATE NBMETERINFO SET switchState=" + meterData + " WHERE meterNo='" + meterNo + "'");
                    this.jdbcTemplate.update("UPDATE METERINFO set onState=" + meterData + " where meterNo='" + meterNo + "'");
                }

                int sendType;
                if (meter.getTypeId() == 2) {
                    sendType = 0;
                    if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from cmd where dataItem='" + dataType + "'", Integer.class) > 0) {
                        String sendId;
                        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                            sendType = (Integer)this.jdbcTemplate.queryForObject("select cmdId from cmd where dataItem='" + dataType + "' and rownum=1", Integer.class);
                            if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from NBSENDDATA where deviceId='" + deviceId + "' and SENDTYPE='" + sendType + "' and manageFlag=2", Integer.class) > 0) {
                                sendId = (String)this.jdbcTemplate.queryForObject("select sendId from (select sendId from NBSENDDATA where deviceId='" + deviceId + "' and SENDTYPE='" + sendType + "' and manageFlag=2 order by CREATEDATE desc) where rownum=1", String.class);
                                this.jdbcTemplate.update("UPDATE NBSENDDATA set reciveFrame='" + reciveFrame + "',reciveDesc='" + reciveDesc + "', MANAGEFLAG=1,MANAGEDATE='" + Tools.getNow() + "',manageCount=manageCount+1 where sendId='" + sendId + "'");
                            }
                        } else {
                            sendType = (Integer)this.jdbcTemplate.queryForObject("select cmdId from cmd where dataItem='" + dataType + "' limit 1", Integer.class);
                            if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from NBSENDDATA where deviceId='" + deviceId + "' and SENDTYPE='" + sendType + "' and manageFlag=2", Integer.class) > 0) {
                                sendId = (String)this.jdbcTemplate.queryForObject("select sendId from NBSENDDATA where deviceId='" + deviceId + "' and SENDTYPE='" + sendType + "' and manageFlag=2 order by CREATEDATE desc limit 1", String.class);
                                this.jdbcTemplate.update("UPDATE NBSENDDATA set reciveFrame='" + reciveFrame + "',reciveDesc='" + reciveDesc + "', MANAGEFLAG=1,MANAGEDATE='" + Tools.getNow() + "',manageCount=manageCount+1 where sendId='" + sendId + "'");
                            }
                        }
                    }
                }

                if (!"".equals(commandId) && meter.getTypeId() == 1) {
                    this.jdbcTemplate.update("UPDATE NBSENDDATA set reciveFrame='" + reciveFrame + "',reciveDesc='" + meterData + "', MANAGEFLAG=1,MANAGEDATE='" + Tools.getNow() + "',manageCount=manageCount+1 where commandId='" + commandId + "'");
                    if ((Integer)this.jdbcTemplate.queryForObject("select count(sendType) from NBSENDDATA where commandId='" + commandId + "'", Integer.class) > 0) {
                        sendType = (Integer)this.jdbcTemplate.queryForObject("select sendType from NBSENDDATA where commandId='" + commandId + "'", Integer.class);
                        int switchState = 0;
                        if (sendType == 9) {
                            switchState = 1;
                        } else if (sendType == 10) {
                            switchState = 0;
                        } else if (sendType == 11) {
                            switchState = 2;
                        } else if (sendType == 12) {
                            switchState = 3;
                        }

                        if (sendType >= 9 && sendType <= 12) {
                            this.jdbcTemplate.update("UPDATE NBMETERINFO SET switchState=" + switchState + " WHERE meterNo='" + meterNo + "'");
                            this.jdbcTemplate.update("UPDATE METERINFO set onState=" + switchState + " where meterNo='" + meterNo + "'");
                        }
                    }
                }
            }
        }

        resultMap.put("meterNo", meterNo);
        resultMap.put("custId", custId);
        resultMap.put("typeId", typeId);
        return resultMap;
    }

    public Map<String, String> postCommand(final String meterNo, final String deviceId, final String commandCode, final String commandValue, final int custId, final String createUser, String sysSendId) {
        Cmd cmd = this.cmdDao.getCmdByCmdId(Integer.parseInt(commandCode));
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select pwdGroupNo from nbmeterInfo where meterNo='" + meterNo + "' and rownum=1";
        } else {
            sql = "select pwdGroupNo from nbmeterInfo where meterNo='" + meterNo + "' limit 1";
        }

        int pwdGroupNo = (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class);
        NbMeterPwd nbMeterPwd = this.cmdDao.getNbMeterPwdByGroupNo(pwdGroupNo);
        String encodeFrame = "";
        final String createDate = Tools.getNowSecond();
        if (cmd.getTypeId() == 2) {
            encodeFrame = YinheWaterFrame188.encodeFrame(meterNo, commandCode, commandValue, cmd, nbMeterPwd);
        } else {
            encodeFrame = YinheFrame.encodeFrame(meterNo, commandCode, commandValue, cmd, nbMeterPwd);
        }

        final String sid = String.valueOf(System.currentTimeMillis());
        String isql = "INSERT INTO NBSENDDATA(SENDID,CUSTID,METERNO,DEVICEID,SENDTYPE,SENDDETAIL,CREATEDATE,MANAGEFLAG,MANAGEDATE,MANAGECOUNT,REMARK,CREATEUSER,sendFrame) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,'" + encodeFrame + "')";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, sid);
                ps.setInt(2, custId);
                ps.setString(3, meterNo);
                ps.setString(4, deviceId);
                ps.setString(5, commandCode);
                ps.setString(6, commandValue);
                ps.setString(7, createDate);
                ps.setInt(8, 0);
                ps.setString(9, "");
                ps.setInt(10, 0);
                ps.setString(11, "");
                ps.setString(12, createUser);
            }
        });
        String usql = "update NBSENDDATA set manageflag=8 where METERNO='" + meterNo + "' and manageflag<>1 and SENDTYPE=" + commandCode + " and CREATEDATE<'" + createDate + "'";
        this.jdbcTemplate.update(usql);
        if (!"".equals(sysSendId)) {
            this.updateTmpnbsenddata(sysSendId, 3);
        }

        Map<String, String> map = new HashMap();
        map.put("sid", sid);
        map.put("encodeFrame", encodeFrame);
        map.put("typeId", String.valueOf(cmd.getTypeId()));
        map.put("deviceId", deviceId);
        map.put("sendType", commandCode);
        return map;
    }

    public int getCustIdByDeviceId(String deviceId) {
        if ((Integer)this.jdbcTemplate.queryForObject("select count(custId) from nbmeterinfo where deviceId='" + deviceId + "'", Integer.class) > 0) {
            return "ORACLE".equals(ConstParam.SQLTYPE) ? (Integer)this.jdbcTemplate.queryForObject("select custId from nbmeterinfo where deviceId='" + deviceId + "' and rownum=1", Integer.class) : (Integer)this.jdbcTemplate.queryForObject("select custId from nbmeterinfo where deviceId='" + deviceId + "' limit 1", Integer.class);
        } else {
            return 0;
        }
    }

    public String getMeterNoByDeviceId(String deviceId, String meterNo) {
        if ((Integer)this.jdbcTemplate.queryForObject("select count(meterNo) from nbmeterinfo where deviceId='" + deviceId + "' and meterNo='" + meterNo + "'", Integer.class) > 0) {
            return "ORACLE".equals(ConstParam.SQLTYPE) ? (String)this.jdbcTemplate.queryForObject("select meterNo from nbmeterinfo where deviceId='" + deviceId + "' and meterNo='" + meterNo + "' and rownum=1", String.class) : (String)this.jdbcTemplate.queryForObject("select meterNo from nbmeterinfo where deviceId='" + deviceId + "' and meterNo='" + meterNo + "' limit 1", String.class);
        } else if ((Integer)this.jdbcTemplate.queryForObject("select count(meterNo) from nbmeterinfo where deviceId='" + deviceId + "'", Integer.class) > 0) {
            return "ORACLE".equals(ConstParam.SQLTYPE) ? (String)this.jdbcTemplate.queryForObject("select meterNo from nbmeterinfo where deviceId='" + deviceId + "' and rownum=1", String.class) : (String)this.jdbcTemplate.queryForObject("select meterNo from nbmeterinfo where deviceId='" + deviceId + "' limit 1", String.class);
        } else {
            return "";
        }
    }

    public String getImsiByDeviceId(String deviceId, String meterNo) {
        if ((Integer)this.jdbcTemplate.queryForObject("select count(meterNo) from nbmeterinfo where deviceId='" + deviceId + "' and meterNo='" + meterNo + "'", Integer.class) > 0) {
            return "ORACLE".equals(ConstParam.SQLTYPE) ? (String)this.jdbcTemplate.queryForObject("select nodeId from nbmeterinfo where deviceId='" + deviceId + "' and meterNo='" + meterNo + "' and rownum=1", String.class) : (String)this.jdbcTemplate.queryForObject("select nodeId from nbmeterinfo where deviceId='" + deviceId + "' and meterNo='" + meterNo + "' limit 1", String.class);
        } else if ((Integer)this.jdbcTemplate.queryForObject("select count(meterNo) from nbmeterinfo where deviceId='" + deviceId + "'", Integer.class) > 0) {
            return "ORACLE".equals(ConstParam.SQLTYPE) ? (String)this.jdbcTemplate.queryForObject("select nodeId from nbmeterinfo where deviceId='" + deviceId + "' and rownum=1", String.class) : (String)this.jdbcTemplate.queryForObject("select nodeId from nbmeterinfo where deviceId='" + deviceId + "' limit 1", String.class);
        } else {
            return "";
        }
    }

    public void updateCommandId(String deviceId, String sid, String commandId) {
        String sql = "update NBSENDDATA set commandId='" + commandId + "' where deviceId='" + deviceId + "' and SENDID='" + sid + "'";
        this.jdbcTemplate.update(sql);
    }

    public void registerDevice(String nodeId, String deviceId) {
        String sql = "update NbMeterInfo set deviceId='" + deviceId + "',regTime='" + Tools.getNow() + "',regState=1 where nodeId='" + nodeId + "'";
        this.jdbcTemplate.update(sql);
    }

    public void deleteDevice(String deviceId) {
        String sql = "update NbMeterInfo set deviceId='',regState=0 where deviceId='" + deviceId + "' ";
        this.jdbcTemplate.update(sql);
    }

    public List<YinheMeterData> getYinheMeterData(String orders) {

        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select meterNo,deviceId,readTime,custId,meterData,dataType,(select cmdName from cmd where dataItem=yinhemeterdata.dataType and rownum=1)datatypeName,reciveFrame,remark,commandId from yinhemeterdata where 1=1 " + orders + " order by readTime desc";
        } else {
            sql = "select meterNo,deviceId,readTime,custId,meterData,dataType,(select cmdName from cmd where dataItem=yinhemeterdata.dataType limit 1)datatypeName,reciveFrame,remark,commandId from yinhemeterdata where 1=1 " + orders + " order by readTime desc";
        }

        return this.jdbcTemplate.query(sql, new YinheMeterDataDTO());
    }

    public String modifyDeviceId(String meterNo, String uploadDeviceId, String uploadNodeId) {
        String sql0 = "update nbMeterInfo set nodeId='---' where nodeId='" + uploadNodeId + "'";
        this.jdbcTemplate.update(sql0);
        String sql = "update nbMeterInfo set deviceId='" + uploadDeviceId + "',nodeId='" + uploadNodeId + "' where meterNo='" + meterNo + "'";
        this.jdbcTemplate.update(sql);
        return "修改成功";
    }

    public void updateTmpnbsenddata(String sendId, int manageFlag) {
        String sql = "update tmpnbsenddata set manageFlag=" + manageFlag + " where sendId='" + sendId + "'";
        this.jdbcTemplate.update(sql);
    }

    public List<NbSendData> getPostCommand(String orders) {
        String sql = "";
        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
            sql = "select t.*,(select manuCode from nbmeterInfo where meterno=t.meterno and rownum=1)manuCode,(select cmdName from cmd where cmdId=t.sendtype)sendName from nbsenddata t where 1=1 " + orders + " order by CreateDate desc";
        } else {
            sql = "select t.*,(select manuCode from nbmeterInfo where meterno=t.meterno limit 1)manuCode,(select cmdName from cmd where cmdId=t.sendtype)sendName from nbsenddata t where 1=1 " + orders + " order by CreateDate desc";
        }

        return this.jdbcTemplate.query(sql, new PostCommandDTO());
    }

    public NbMeterInfo getNbMeterInfoByClause(String orders) {
        String sql = "select meterNo,nodeId,custId,regstate,regtime,deviceid,manucode,batchNo,pwdGroupNo,tmodel,imsi,MeterAddr,uploadDeviceId,uploadNodeId,typeId from NbMeterInfo where 1=1 " + orders + " order by meterNo";
        List<NbMeterInfo> list = this.jdbcTemplate.query(sql, new NbMeterInfoDTO());
        return list.size() > 0 ? (NbMeterInfo)list.get(0) : new NbMeterInfo();
    }

    public void updateNbsenddata(String sendId, int manageFlag) {
        String usql = "update NBSENDDATA set manageflag=" + manageFlag + " where sendId='" + sendId + "'";
        this.jdbcTemplate.update(usql);
    }

    public class NbMeterInfoDTO implements RowMapper {
        public NbMeterInfoDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            NbMeterInfo dto = new NbMeterInfo();
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setNodeId(rs.getString("nodeId"));
            dto.setCustId(rs.getInt("custId"));
            dto.setRegState(rs.getInt("regState"));
            dto.setRegTime(rs.getString("regTime"));
            dto.setDeviceId(rs.getString("deviceId"));
            dto.setManuCode(rs.getString("manuCode"));
            dto.setBatchNo(rs.getString("batchNo"));
            dto.setPwdGroupNo(rs.getInt("pwdGroupNo"));
            dto.setTmodel(rs.getInt("tmodel"));
            dto.setImsi(rs.getString("imsi"));
            dto.setMeterAddr(rs.getString("meterAddr"));
            dto.setUploadDeviceId(rs.getString("uploadDeviceId"));
            dto.setUploadNodeId(rs.getString("uploadNodeId"));
            dto.setTypeId(rs.getInt("typeId"));
            return dto;
        }
    }

    public class PostCommandDTO implements RowMapper {
        public PostCommandDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            NbSendData dto = new NbSendData();
            dto.setSendId(rs.getString("sendId"));
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setDeviceId(rs.getString("deviceId"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setCustId(rs.getInt("custId"));
            dto.setManageDate(rs.getString("manageDate"));
            dto.setSendType(rs.getInt("sendType"));
            dto.setSendDetail(rs.getString("sendDetail"));
            dto.setManageFlag(rs.getInt("manageFlag"));
            dto.setManageCount(rs.getInt("manageCount"));
            dto.setManuCode(rs.getString("manuCode"));
            dto.setRemark(rs.getString("remark"));
            dto.setCreateUser(rs.getString("createUser"));
            dto.setCommandId(rs.getString("commandId"));
            dto.setSendFrame(rs.getString("sendFrame"));
            dto.setReciveFrame(rs.getString("reciveFrame"));
            dto.setReciveDesc(rs.getString("reciveDesc") == null ? "" : rs.getString("reciveDesc"));
            dto.setSendName(rs.getString("sendName"));
            return dto;
        }
    }

    public class YinheMeterDataDTO implements RowMapper {
        public YinheMeterDataDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            YinheMeterData dto = new YinheMeterData();
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setDeviceId(rs.getString("deviceId"));
            dto.setReadTime(rs.getString("readTime"));
            dto.setCustId(rs.getInt("custId"));
            dto.setMeterData(rs.getString("meterData"));
            dto.setDataType(rs.getString("dataType"));
            dto.setDatatypeName(rs.getString("datatypeName"));
            dto.setReciveFrame(rs.getString("reciveFrame"));
            dto.setRemark(rs.getString("remark"));
            dto.setCommandId(rs.getString("commandId"));
            return dto;
        }
    }
}
