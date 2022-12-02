//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.model.NbDataDetail;
import com.yinhe.ec.cpps.model.NbMeterInfo;
import com.yinhe.ec.cpps.model.NbSendData;
import com.yinhe.ec.cpps.model.OneNetNbDataDetail;
import com.yinhe.ec.cpps.util.DataConvertTools;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.util.UUIDUtil;
import com.yinhe.ec.cpps.ycmeter.dao.NbMeterDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class NbMeterDaoImpl implements NbMeterDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public NbMeterDaoImpl() {
    }

    public void addNbMeterInfo(final NbMeterInfo nbMeterInfo) throws DataAccessException {
        String sql = "insert into NBMETERINFO(MeterNo,NodeId,CustId,RegState,SwitchState,CreateTime,CreateUser,ManuCode,Tmodel,imsi,meterAddr,batchNo,pwdGroupNo,typeId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterInfo.getMeterNo());
                ps.setString(2, nbMeterInfo.getNodeId());
                ps.setInt(3, nbMeterInfo.getCustId());
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setString(6, Tools.getNow());
                ps.setInt(7, nbMeterInfo.getCreateUser());
                ps.setString(8, nbMeterInfo.getManuCode());
                ps.setInt(9, nbMeterInfo.getTmodel());
                ps.setString(10, nbMeterInfo.getImsi());
                ps.setString(11, nbMeterInfo.getMeterAddr());
                ps.setString(12, nbMeterInfo.getBatchNo());
                ps.setInt(13, nbMeterInfo.getPwdGroupNo());
                ps.setInt(14, nbMeterInfo.getTypeId());
            }
        });
    }

    public void deleteNbMeterInfo(String meterNo, String nodeId, int custId, String deviceId) throws DataAccessException {
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";
        if (!"".equals(deviceId) && deviceId != null) {
            sql1 = "delete from ONENETNBDATADETAIL where deviceId='" + deviceId + "' and custId=" + custId;
            sql2 = "delete from NBSENDDATA where deviceId='" + deviceId + "' and custId=" + custId;
            sql3 = "delete from NBMETERINFO where deviceId='" + deviceId + "' and custId=" + custId;
        } else {
            sql1 = "delete from ONENETNBDATADETAIL where meterno='" + meterNo + "' and deviceId in (select deviceid from NBMETERINFO where nodeId='" + nodeId + "')  and custId=" + custId;
            sql2 = "delete from NBSENDDATA where meterno='" + meterNo + "' and deviceId in (select deviceid from NBMETERINFO where nodeId='" + nodeId + "') and custId=" + custId;
            sql3 = "delete from NBMETERINFO where meterno='" + meterNo + "' and nodeId='" + nodeId + "' and custId=" + custId;
        }

        this.jdbcTemplate.batchUpdate(new String[]{sql1, sql2, sql3});
    }

    public List<NbMeterInfo> getNbMeterListByClause(String orders) {
        if (StringUtils.isEmpty(orders)){
            orders="and tmodel=0 and typeId=1 and custId=0 and regState = 0";
        }
        String sql = "select NbMeterInfo.meterNo,nodeId,NbMeterInfo.custId,regstate,regtime,deviceid,dosagesum,batvolt,temperature,switchstate,sigvalue,errdesc, readtime,NbMeterInfo.createtime,NbMeterInfo.createuser,NbMeterInfo.remark,manucode,batchNo,pwdGroupNo,tmodel,imsi,NbMeterInfo.useflag,NbMeterInfo.MeterAddr, NbMeterInfo.uploadDeviceId,NbMeterInfo.uploadNodeId,NbMeterInfo.typeId,(select custName from CUSTOMER where custId=NbMeterInfo.custId) custName, (select buyCount from METERINFO where meterNo=NbMeterInfo.meterNo ) buyCount  from NBMETERINFO NbMeterInfo where 1=1 " + orders + " order by meterNo";
        List<NbMeterInfo> list = this.jdbcTemplate.query(sql, new NbMeterInfoDTO());
        return list;
    }

    public void modifyNbMeterInfo(final NbMeterInfo nbMeterInfo) throws DataAccessException {
        String sql = "update NBMETERINFO set NodeId=?,Imsi=?,manuCode=?,meterAddr=?,batchNo=?,pwdGroupNo=?,typeId=? where MeterNo=? and CustId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterInfo.getNodeId());
                ps.setString(2, nbMeterInfo.getImsi());
                ps.setString(3, nbMeterInfo.getManuCode());
                ps.setString(4, nbMeterInfo.getMeterAddr());
                ps.setString(5, nbMeterInfo.getBatchNo());
                ps.setInt(6, nbMeterInfo.getPwdGroupNo());
                ps.setInt(7, nbMeterInfo.getTypeId());
                ps.setString(8, nbMeterInfo.getMeterNo());
                ps.setInt(9, nbMeterInfo.getCustId());
            }
        });
    }

    public void uploadNbMeterInfo(final String meterNo, final String nodeId, final int custId, final int createUser, final String manuCode, final String batchNo, final int pwdGroupNo, final int tmodel, final String imsi, final String meterAddr, final int typeId) throws DataAccessException {
        if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from NbMeterInfo where meterNo='" + meterNo + "'", Integer.class) > 0) {
            this.jdbcTemplate.update("update NbMeterInfo set CustId=" + custId + " where meterNo='" + meterNo + "'");
        } else {
            String sql = "insert into NbMeterInfo(MeterNo,NodeId,CustId,Regstate,SwitchState,createUser,CreateTime,ManuCode,Tmodel,imsi,meterAddr,batchNo,pwdGroupNo,typeId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, meterNo);
                    ps.setString(2, nodeId);
                    ps.setInt(3, custId);
                    ps.setInt(4, 0);
                    ps.setInt(5, 0);
                    ps.setInt(6, createUser);
                    ps.setString(7, Tools.getNow());
                    ps.setString(8, manuCode);
                    ps.setInt(9, tmodel);
                    ps.setString(10, imsi);
                    ps.setString(11, meterAddr);
                    ps.setString(12, batchNo);
                    ps.setInt(13, pwdGroupNo);
                    ps.setInt(14, typeId);
                }
            });
        }

    }

    public void registerDevice(final String meterNo, final String deviceId, final int custId, final String nodeId, final String manuId) {
        String sql = "";
        System.out.println("registerDevice impl meterNo:" + meterNo);
        if ((Integer)this.jdbcTemplate.queryForObject("select count(MeterNo) from NbMeterInfo where meterNo='" + meterNo + "'", Integer.class) > 0) {
            sql = "update NbMeterInfo set deviceId='" + deviceId + "',regTime='" + Tools.getNow() + "',regState=1 where meterNo='" + meterNo + "' and custId=" + custId;
            this.jdbcTemplate.update(sql);
        } else {
            sql = "insert into NbMeterInfo(MeterNo,NodeId,CustId,Regstate,SwitchState,createUser,CreateTime,ManuCode,Tmodel,imsi,DeviceId,regTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, meterNo);
                    ps.setString(2, nodeId);
                    ps.setInt(3, custId);
                    ps.setInt(4, 1);
                    ps.setInt(5, 0);
                    ps.setInt(6, 0);
                    ps.setString(7, Tools.getNow());
                    ps.setString(8, manuId);
                    ps.setInt(9, 0);
                    ps.setString(10, "1234567890");
                    ps.setString(11, deviceId);
                    ps.setString(12, Tools.getNow());
                }
            });
        }

    }

    public void deleteDevice(String meterNo, String deviceId, int custId) {
        String sql = "update NbMeterInfo set regState=0 where deviceId='" + deviceId + "' ";
        this.jdbcTemplate.update(sql);
    }

    public void updateNbMeterInfoData(final String deviceId, final Double dosageSum, final Double batVolte, final Double temperature, final int switchState, final Double sigValue, final String errStatus, final String readTime) {
        String usql = "update NbMeterInfo set dosageSum=?, batVolt=?, temperature=?,switchState=?,sigValue=?, errDesc=?, readTime=? where deviceId=?";
        this.jdbcTemplate.update(usql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setDouble(1, dosageSum);
                ps.setDouble(2, batVolte);
                ps.setDouble(3, temperature);
                ps.setInt(4, switchState);
                ps.setDouble(5, sigValue);
                ps.setString(6, errStatus);
                ps.setString(7, readTime);
                ps.setString(8, deviceId);
            }
        });
        this.jdbcTemplate.update("update meterinfo set dosageSum=" + dosageSum + ",onState=" + switchState + ",ReadDate='" + readTime + "' where meterno in (select meterno from NbMeterInfo where deviceId='" + deviceId + "')");
    }

    public void postAsynCommand(final String meterNo, final String deviceId, final String commandCode, final String commandValue, final int custId, final String commandId, final String sysSendId, final String createUser) {
        String isql = "INSERT INTO NBSENDDATA(SENDID,CUSTID,METERNO,DEVICEID,SENDTYPE,SENDDETAIL,CREATEDATE,MANAGEFLAG,MANAGEDATE,MANAGECOUNT,REMARK,CREATEUSER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, commandId);
                ps.setInt(2, custId);
                ps.setString(3, meterNo);
                ps.setString(4, deviceId);
                ps.setString(5, commandCode);
                ps.setString(6, commandValue);
                ps.setString(7, Tools.getNow());
                ps.setInt(8, 0);
                ps.setString(9, "");
                ps.setInt(10, 0);
                ps.setString(11, sysSendId);
                ps.setString(12, createUser);
            }
        });
        String usql = "update tmpnbsenddata set MANAGEFLAG=10 where sendId='" + sysSendId + "'";
        this.jdbcTemplate.update(usql);
    }

    public void insertNbData(String deviceId, String meterNo, String dosageSum, String sigValue, String batVolte, String switchFlag, String readDt, String errDesc) {
        int custId = 0;
        custId = this.getCustIdByDeviceId(deviceId);
        String sql = "insert into nbdatadetail(meterno,deviceid,custid,dosagesum,batvolt,temperature,switchstate,sigvalue,readtime,errDesc) values ('" + meterNo + "','" + deviceId + "'," + custId + "," + dosageSum + "," + batVolte + ",0.0," + switchFlag + "," + sigValue + ",'" + readDt + "','" + errDesc + "')";
        this.jdbcTemplate.update(sql);
        this.updateNbMeterInfoData(deviceId, Double.valueOf(dosageSum), Double.valueOf(batVolte), 0.0, Integer.parseInt(switchFlag), Double.valueOf(sigValue), "", readDt);
    }


    public int getCustIdByDeviceId(String deviceId) {
        return (Integer)this.jdbcTemplate.queryForObject("select custId from nbmeterinfo where deviceId='" + deviceId + "' and rownum=1", Integer.class);
    }

    public List<NbDataDetail> getNbDataDetail(String orders) {
        String sql = "SELECT * FROM NbDataDetail WHERE 1=1 " + orders + " ORDER BY READTIME DESC";
        return this.jdbcTemplate.query(sql, new NbDataDetailDTO());
    }

    public void insertNbDataToDatabase(List list) {
        String dataMsg = "";

        for(int i = 0; i < list.size(); ++i) {
            dataMsg = ((String)list.get(i)).toString();
            if (!"".equals(dataMsg.trim())) {
                JSONObject jsonobj = JSONObject.parseObject(dataMsg.trim());
                JSONObject msgObject = jsonobj.getJSONObject("msg");
                String dev_id = "";
                String ds_id = "";
                String at = "";
                String value = "";
                String meterNo = "";
                int custId = 0;
                Double dosageSum = 0.0;
                Double batvolt = 0.0;
                Double temperature = 0.0;
                int switchState = 0;
                Double sigvalue = 0.0;
                String command = "";
                String readTime = "";
                at = msgObject.get("at").toString();
                ds_id = msgObject.get("ds_id").toString();
                value = msgObject.get("value").toString();
                dev_id = msgObject.get("dev_id").toString();
                readTime = Tools.formatTimestamp(at);
                String isql = "";
                String usql = "";
                String usql_meterInfo = "";
                String ssql = "";
                if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from nbmeterinfo where deviceid='" + dev_id + "' ", Integer.class) > 0) {
                    meterNo = (String)this.jdbcTemplate.queryForObject("select meterno from nbmeterinfo where deviceid='" + dev_id + "'", String.class);
                    custId = (Integer)this.jdbcTemplate.queryForObject("select custid from nbmeterinfo where deviceid='" + dev_id + "'", Integer.class);
                    if ("DosageSum".equals(ds_id)) {
                        dosageSum = Double.valueOf(value);
                        usql_meterInfo = "UPDATE NBMETERINFO SET DOSAGESUM=" + dosageSum + ",READTIME='" + readTime + "' WHERE DEVICEID='" + dev_id + "'";
                        this.jdbcTemplate.update("update meterinfo set dosageSum=" + dosageSum + ",ReadDate='" + readTime + "' where meterno='" + meterNo + "'");
                    } else if ("BatVolte".equals(ds_id)) {
                        batvolt = Double.valueOf(value);
                        usql_meterInfo = "UPDATE NBMETERINFO SET BATVOLT=" + batvolt + ",READTIME='" + readTime + "' WHERE DEVICEID='" + dev_id + "'";
                        this.jdbcTemplate.update("update meterinfo set volt=" + batvolt + " where meterno='" + meterNo + "'");
                    } else if ("Temperature".equals(ds_id)) {
                        temperature = Double.valueOf(value);
                        usql_meterInfo = "UPDATE NBMETERINFO SET TEMPERATURE=" + temperature + ",READTIME='" + readTime + "' WHERE DEVICEID='" + dev_id + "'";
                    } else if ("Switch".equals(ds_id)) {
                        switchState = Integer.parseInt(value);
                        usql_meterInfo = "UPDATE NBMETERINFO SET SWITCHSTATE=" + switchState + ",READTIME='" + readTime + "' WHERE DEVICEID='" + dev_id + "'";
                        this.jdbcTemplate.update("update meterinfo set onstate=" + switchState + " where meterno='" + meterNo + "'");
                    } else if ("SigValue".equals(ds_id)) {
                        sigvalue = Double.valueOf(value);
                        usql_meterInfo = "UPDATE NBMETERINFO SET SIGVALUE=" + sigvalue + ",READTIME='" + readTime + "' WHERE DEVICEID='" + dev_id + "'";
                    } else if ("Command".equals(ds_id)) {
                        System.out.println("------>>" + value);
                        if (value.indexOf("FE") >= 0) {
                            if (value.indexOf("84031AA0") >= 0) {
                                usql_meterInfo = "UPDATE NBSENDDATA SET ManageFlag=1,ManageDate='" + readTime + "',ManageCount=1 WHERE DEVICEID='" + dev_id + "' and ManageFlag=0 and SendType=9";
                            } else if (value.indexOf("840517A0") >= 0) {
                                usql_meterInfo = "UPDATE NBSENDDATA SET ManageFlag=1,ManageDate='" + readTime + "',ManageCount=1 WHERE DEVICEID='" + dev_id + "' and ManageFlag=0 and SendType in (1,2)";
                            } else {
                                usql_meterInfo = "UPDATE NBSENDDATA SET ManageFlag=1,ManageDate='" + readTime + "',ManageCount=1 WHERE DEVICEID='" + dev_id + "' and ManageFlag=0 ";
                            }
                        } else {
                            usql_meterInfo = "UPDATE NBMETERINFO SET REMARK='' WHERE DEVICEID='" + dev_id + "' ";
                        }
                    } else {
                        usql_meterInfo = "UPDATE NBMETERINFO SET REMARK='' WHERE DEVICEID='" + dev_id + "' ";
                    }

                    ssql = "select count(meterno) from OneNetNbDataDetail where deviceid='" + dev_id + "' and readtime='" + readTime + "'";
                    if ((Integer)this.jdbcTemplate.queryForObject(ssql, Integer.class) == 0) {
                        isql = "INSERT INTO OneNetNbDataDetail(METERNO,DEVICEID,CUSTID,DS_ID,DS_VALUE,DS_AT,READTIME,REMARK) VALUES('" + meterNo + "','" + dev_id + "'," + custId + ",'" + ds_id + "','" + value + "','" + at + "','" + readTime + "','')";
                        this.jdbcTemplate.update(isql);
                    }

                    this.jdbcTemplate.update(usql_meterInfo);
                }
            }
        }

    }

    public List<OneNetNbDataDetail> getOneNetNbDataDetail(String orders) {
        String sql = "SELECT * FROM OneNetNbDataDetail WHERE 1=1 " + orders + " ORDER BY READTIME DESC";
        return this.jdbcTemplate.query(sql, new OneNetNbDataDetailDTO());
    }

    public List<NbSendData> getOneNetNbSendDataInfo(String orders) {
        String sql = "select t.sendId,t.meterNo,t.deviceId,t.createDate,t.custId,t.manageDate,t.sendType,t.sendDetail,t.manageFlag,t.manageCount,t.remark,t.createUser,t.commandId,t.sendFrame,t.reciveFrame,t.reciveDesc,(select manuCode from nbmeterInfo where meterno=t.meterno and rownum=1)manuCode,(select cmdName from cmd where cmdId=t.sendtype)sendName from nbsenddata t where 1=1 " + orders + " " + "union " + "select t.sendId,t.meterNo,t.deviceId,t.createDate,t.custId,t.manageDate,t.sendType,t.sendDetail,t.manageFlag,t.manageCount,'' as remark,t.remark as createUser,'' commandId,'' sendFrame,'' reciveFrame,'' reciveDesc," + "(select manuCode from nbmeterInfo where meterno=t.meterno and rownum=1)manuCode,(select cmdName from cmd where cmdId=t.sendtype)sendName " + "from tmpnbsenddata t where manageFlag=0 " + orders + " " + "order by CreateDate desc";
        return this.jdbcTemplate.query(sql, new OneNetNbSendDataDTOMapper());
    }

    public void checkOnenetRegisterDevice(final String dev_id, final String meterNo, final String nodeId, final String create_time, final int custId, final String imsi) {
        String sql = "select count(meterno) from nbmeterinfo where meterno='" + meterNo + "' and nodeId='" + nodeId + "' and imsi = '" + imsi + "'";
        if ((Integer)this.jdbcTemplate.queryForObject(sql, Integer.class) == 0) {
            String isql = "insert into NbMeterInfo(MeterNo,NodeId,CustId,DeviceId,Regstate,SwitchState,createUser,CreateTime,ManuCode,Tmodel,RegTime,imsi) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, meterNo);
                    ps.setString(2, nodeId);
                    ps.setInt(3, custId);
                    ps.setString(4, dev_id);
                    ps.setInt(5, 1);
                    ps.setInt(6, 0);
                    ps.setInt(7, 0);
                    ps.setString(8, Tools.getNow());
                    ps.setString(9, meterNo.substring(0, 6));
                    ps.setInt(10, 1);
                    ps.setString(11, create_time);
                    ps.setString(12, imsi);
                }
            });
            String usql = "update nbmeterinfo set useflag=0 where meterno='" + meterNo + "' and DeviceId<>'" + dev_id + "'";
            this.jdbcTemplate.update(usql);
        }

    }

    public List<NbSendData> getWillPostData(String orders) {
        String sql = "select t.*,(select manucode from nbmeterinfo where meterno=t.meterno and rownum=1)manuCode,'' sendFrame from (select rank() over(partition by meterno order by createdate desc) r,a.*  from tmpnbsenddata a where a.manageflag=0 and meterno in (select meterno from nbmeterinfo where 1=1 " + orders + "))t where r=1 order by meterno";
        return this.jdbcTemplate.query(sql, new WillNbSendDataDTOMapper());
    }

    public void updateNbsenddataFlag(String deviceId, String sucFlag, String commandId) {
        String sql = "update nbsenddata set manageFlag=" + sucFlag + ", managedate='" + Tools.getNow() + "',managecount=1 where deviceId='" + deviceId + "' and sendId='" + commandId + "'";
        this.jdbcTemplate.update(sql);
        String sql2 = "update tmpnbsenddata set manageFlag=" + sucFlag + ", managedate='" + Tools.getNow() + "',managecount=1 where deviceId='" + deviceId + "' and sendid in (select remark from nbsenddata where deviceId='" + deviceId + "' and sendId='" + commandId + "')";
        this.jdbcTemplate.update(sql2);
    }

    public void updateNbMeterSwitchState(String deviceId, String switchState) {
        String sql = "update nbMeterInfo set switchstate=" + switchState + " where deviceId='" + deviceId + "'";
        this.jdbcTemplate.update(sql);
        String sql2 = "update meterinfo set onstate=" + switchState + " where meterno in (select meterno from nbMeterInfo where deviceId='" + deviceId + "')";
        this.jdbcTemplate.update(sql2);
    }

    public void updateNbUploadData(String deviceId, String key, String value, String readTime) {
        String meterNo = "";
        Double lastDosage = 0.0;
        if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from nbmeterinfo where deviceid='" + deviceId + "' ", Integer.class) > 0) {
            meterNo = (String)this.jdbcTemplate.queryForObject("select meterno from nbmeterinfo where deviceid='" + deviceId + "'", String.class);
            lastDosage = (Double)this.jdbcTemplate.queryForObject("select dosageSum from nbmeterinfo where deviceid='" + deviceId + "'", Double.class);
            String sql = "";
            String sql2 = "";
            int custId = 0;
            custId = this.getCustIdByDeviceId(deviceId);
            if ("sigValue".equals(key)) {
                sql = "UPDATE NBMETERINFO SET SIGVALUE=" + value + ",READTIME='" + readTime + "' WHERE DEVICEID='" + deviceId + "'";
                if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from nbdatadetail where deviceid='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ", Integer.class) == 0) {
                    sql2 = "insert into nbdatadetail(meterno,deviceid,custid,dosagesum,batvolt,temperature,switchstate,sigvalue,readtime) values ('" + meterNo + "','" + deviceId + "'," + custId + "," + lastDosage + ",90,0.0,0," + value + ",'" + readTime + "')";
                    this.jdbcTemplate.update(sql2);
                } else {
                    sql2 = "update nbdatadetail set sigvalue=" + value + " where deviceId='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ";
                    this.jdbcTemplate.update(sql2);
                }

                this.jdbcTemplate.update(sql);
            } else if ("batVolte".equals(key)) {
                sql = "UPDATE NBMETERINFO SET BATVOLT=" + value + ",READTIME='" + readTime + "' WHERE DEVICEID='" + deviceId + "'";
                this.jdbcTemplate.update("update meterinfo set volt=" + value + " where meterno='" + meterNo + "'");
                if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from nbdatadetail where deviceid='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ", Integer.class) == 0) {
                    sql2 = "insert into nbdatadetail(meterno,deviceid,custid,dosagesum,batvolt,temperature,switchstate,sigvalue,readtime) values ('" + meterNo + "','" + deviceId + "'," + custId + "," + lastDosage + "," + value + ",0.0,0,-78,'" + readTime + "')";
                    this.jdbcTemplate.update(sql2);
                } else {
                    sql2 = "update nbdatadetail set batvolt=" + value + " where deviceId='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ";
                    this.jdbcTemplate.update(sql2);
                }
            } else if ("dosageSum".equals(key)) {
                sql = "UPDATE NBMETERINFO SET DOSAGESUM=" + value + ",READTIME='" + readTime + "' WHERE DEVICEID='" + deviceId + "'";
                this.jdbcTemplate.update("update meterinfo set dosageSum=" + value + ",ReadDate='" + readTime + "' where meterno='" + meterNo + "'");
                lastDosage = Double.valueOf(value);
                if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from nbdatadetail where deviceid='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ", Integer.class) == 0) {
                    sql2 = "insert into nbdatadetail(meterno,deviceid,custid,dosagesum,batvolt,temperature,switchstate,sigvalue,readtime) values ('" + meterNo + "','" + deviceId + "'," + custId + "," + value + ",90,0.0,0,-78,'" + readTime + "')";
                    this.jdbcTemplate.update(sql2);
                } else {
                    sql2 = "update nbdatadetail set dosageSum=" + value + " where deviceId='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ";
                    this.jdbcTemplate.update(sql2);
                }
            } else if ("switchState".equals(key)) {
                sql = "UPDATE NBMETERINFO SET SWITCHSTATE=" + value + ",READTIME='" + readTime + "' WHERE DEVICEID='" + deviceId + "'";
                this.jdbcTemplate.update("update meterinfo set onstate=" + value + " where meterno='" + meterNo + "'");
                if ((Integer)this.jdbcTemplate.queryForObject("select count(*) from nbdatadetail where deviceid='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ", Integer.class) == 0) {
                    sql2 = "insert into nbdatadetail(meterno,deviceid,custid,dosagesum,batvolt,temperature,switchstate,sigvalue,readtime) values ('" + meterNo + "','" + deviceId + "'," + custId + "," + lastDosage + ",90,0.0," + value + ",-78,'" + readTime + "')";
                    this.jdbcTemplate.update(sql2);
                } else {
                    sql2 = "update nbdatadetail set switchstate=" + value + " where deviceId='" + deviceId + "' and substr(READTIME,0,16)='" + readTime.substring(0, 16) + "' ";
                    this.jdbcTemplate.update(sql2);
                }
            }

            this.jdbcTemplate.update(sql);
        }

    }

    public void insertEleNbData(String deviceId, String meterNo, String dosageSum, String sigValue, String batVolte, String switchFlag, String readDt, String errDesc, String actPower, String reactPower, String powerFac, String volt, String lcurrent, String zlCurrent, String appPower, String gridsFreq, String temperature, String positiveActPower, String unPositiveActPower, String dataType) {
        int custId = 0;
        custId = this.getCustIdByDeviceId(deviceId);
        String sql = "insert into nbdatadetail(meterno,deviceid,custid,dosagesum,batvolt,temperature,switchstate,sigvalue,readtime,errDesc,actPower,reactPower,powerFac,volt,lcurrent,zlCurrent,appPower,gridsFreq,positiveActPower,unPositiveActPower,dataType) values ('" + meterNo + "','" + deviceId + "'," + custId + "," + dosageSum + "," + batVolte + "," + temperature + "," + switchFlag + "," + sigValue + ",'" + readDt + "','" + errDesc + "','" + actPower + "','" + reactPower + "','" + powerFac + "','" + volt + "','" + lcurrent + "','" + zlCurrent + "','" + appPower + "','" + gridsFreq + "','" + positiveActPower + "','" + unPositiveActPower + "','" + dataType + "')";
        this.jdbcTemplate.update(sql);
        this.updateEleNbMeterInfoData(deviceId, Double.valueOf(dosageSum), Double.valueOf(batVolte), Double.valueOf(temperature), Integer.parseInt(switchFlag), Double.valueOf(sigValue), "", readDt, dataType);
    }

    public void updateEleNbMeterInfoData(final String deviceId, final Double dosageSum, final Double batVolte, final Double temperature, final int switchState, final Double sigValue, final String errStatus, final String readTime, String dataType) {
        String usql = "";
        if ("UpLoadMeterParamData".equals(dataType)) {
            usql = "update NbMeterInfo set batVolt=?, temperature=?,switchState=?,sigValue=?, errDesc=?, readTime=? where deviceId=?";
            this.jdbcTemplate.update(usql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setDouble(1, batVolte);
                    ps.setDouble(2, temperature);
                    ps.setInt(3, switchState);
                    ps.setDouble(4, sigValue);
                    ps.setString(5, errStatus);
                    ps.setString(6, readTime);
                    ps.setString(7, deviceId);
                }
            });
            this.jdbcTemplate.update("update meterinfo set onState=" + switchState + ",ReadDate='" + readTime + "' where meterno in (select meterno from NbMeterInfo where deviceId='" + deviceId + "')");
        } else if ("UpLoadMeterActEnegy".equals(dataType)) {
            usql = "update NbMeterInfo set dosageSum=?, readTime=? where deviceId=?";
            this.jdbcTemplate.update(usql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setDouble(1, dosageSum);
                    ps.setString(2, readTime);
                    ps.setString(3, deviceId);
                }
            });
            this.jdbcTemplate.update("update meterinfo set dosageSum=" + dosageSum + ",ReadDate='" + readTime + "' where meterno in (select meterno from NbMeterInfo where deviceId='" + deviceId + "')");
        }

    }

    public void updateNbMeterIMSI(String deviceId, String imsi) {
        String sql = "update NbMeterInfo set imsi='" + imsi + "' where deviceId='" + deviceId + "'";
        this.jdbcTemplate.update(sql);
    }

    public void upLoadFactoryTestExcel(String meterNo, String imsi, String batchNo) {
        String sql = "insert into NBMETERINFOFACTORY(meterNo,nodeId,batchNo,createtime) values('" + meterNo + "','" + imsi + "','" + batchNo + "','" + Tools.getNow() + "')";
        this.jdbcTemplate.update(sql);
    }

    public void delFactoryTestMeter() {
        String sql = "delete from NBMETERINFOFACTORY";
        this.jdbcTemplate.update(sql);
    }

    public void postCommandIntoDb(final String meterNo, final String deviceId, final String commandCode, final String commandValue, final int custId) {
        final String date = Tools.getNowSecond();
        String sql = "insert into TMPNBSENDDATA(sendid,custId,meterno,deviceid,sendtype,SendDetail,createdate,manageflag,managedate,managecount,remark)  values (?,?,?,?,?,?,?,0,null,0,'Auto')";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, UUIDUtil.getUUID());
                ps.setInt(2, custId);
                ps.setString(3, meterNo);
                ps.setString(4, deviceId);
                ps.setString(5, commandCode);
                ps.setString(6, commandValue);
                ps.setString(7, date);
            }
        });
        String usql = "update tmpnbsenddata set MANAGEFLAG=10 where meterNo='" + meterNo + "' and sendtype='" + commandCode + "' and SendDetail='" + commandValue + "' and createdate<'" + date + "' and manageflag=0";
        this.jdbcTemplate.update(usql);
    }

    public List<NbSendData> getWaterWillPostData(String orders) {
        String sql = "select t.*,(select manucode from nbmeterinfo where meterno=t.meterno and rownum=1)manuCode from (select rank() over(partition by meterno order by createdate desc) r,a.*  from nbsenddata a where a.manageflag=0 and meterno in (select meterno from nbmeterinfo where 1=1 " + orders + "))t where r=1 order by meterno";
        return this.jdbcTemplate.query(sql, new WillNbSendDataDTOMapper());
    }

    public int getNbMeterCount(String orders) {
        String sql = "select count(*) from nbmeterinfo where 1=1 " + orders;
        return (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public class NbDataDetailDTO implements RowMapper {
        public NbDataDetailDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            NbDataDetail dto = new NbDataDetail();
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setDeviceId(rs.getString("deviceId"));
            dto.setCustId(rs.getInt("custId"));
            dto.setDosageSum(rs.getDouble("dosageSum"));
            dto.setBatVolt(rs.getDouble("batVolt"));
            dto.setTemperature(rs.getDouble("temperature"));
            dto.setSigValue(rs.getDouble("sigValue"));
            dto.setSwitchState(rs.getInt("switchState"));
            dto.setErrDesc(rs.getString("errDesc"));
            dto.setReadTime(rs.getString("readTime"));
            dto.setRemark(rs.getString("remark"));
            dto.setActPower(rs.getString("actPower"));
            dto.setReactPower(rs.getString("reactPower"));
            dto.setPowerFac(rs.getString("powerFac"));
            dto.setVolt(rs.getString("volt"));
            dto.setCurrent(rs.getString("lcurrent"));
            dto.setZlCurrent(rs.getString("zlCurrent"));
            dto.setAppPower(rs.getString("appPower"));
            dto.setGridsFreq(rs.getString("gridsFreq"));
            dto.setCurrentCsq(rs.getString("currentCsq"));
            dto.setPositiveActPower(rs.getString("positiveActPower"));
            dto.setUnPositiveActPower(rs.getString("unPositiveActPower"));
            dto.setDataType(rs.getString("dataType"));
            return dto;
        }
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
            dto.setDosageSum(rs.getDouble("dosageSum"));
            dto.setBatVolt(rs.getDouble("batVolt"));
            dto.setTemperature(rs.getDouble("temperature"));
            dto.setSwitchState(rs.getInt("switchState"));
            dto.setSigValue(rs.getDouble("sigValue"));
            dto.setErrDesc(rs.getString("errDesc"));
            dto.setReadTime(rs.getString("readTime"));
            dto.setCreateTime(rs.getString("createTime"));
            dto.setCreateUser(rs.getInt("createUser"));
            dto.setRemark(rs.getString("remark"));
            dto.setManuCode(rs.getString("manuCode"));
            dto.setTmodel(rs.getInt("tmodel"));
            dto.setImsi(rs.getString("imsi"));
            dto.setUseFlag(rs.getInt("useFlag"));
            dto.setMeterAddr(rs.getString("meterAddr"));
            dto.setCustName(rs.getString("custName"));
            dto.setBatchNo(rs.getString("batchNo"));
            dto.setPwdGroupNo(rs.getInt("pwdGroupNo"));
            dto.setUploadDeviceId(rs.getString("uploadDeviceId"));
            dto.setUploadNodeId(rs.getString("uploadNodeId"));
            dto.setTypeId(rs.getInt("typeId"));
            dto.setBuyCount(rs.getInt("buyCount"));
            return dto;
        }
    }

    public class OneNetNbDataDetailDTO implements RowMapper {
        public OneNetNbDataDetailDTO() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            OneNetNbDataDetail dto = new OneNetNbDataDetail();
            dto.setMeterNo(rs.getString("meterNo"));
            dto.setDeviceId(rs.getString("deviceId"));
            dto.setCustId(rs.getInt("custId"));
            dto.setDs_id(rs.getString("ds_id"));
            if ("ErrStatus".equals(rs.getString("ds_id"))) {
                char[] st = DataConvertTools._10_to_N((long)rs.getInt("ds_value"), 2).toCharArray();
                String st_str = Tools.lpad(String.valueOf(st), 8, '0');
                String st_errstatus_str = st_str.substring(0, 5);
                if ("00000".equals(st_errstatus_str)) {
                    dto.setDs_value("正常");
                } else if ("00001".equals(st_errstatus_str)) {
                    dto.setDs_value("超温");
                } else if ("00010".equals(st_errstatus_str)) {
                    dto.setDs_value("空管");
                } else if ("00100".equals(st_errstatus_str)) {
                    dto.setDs_value("流量过载");
                } else if ("01000".equals(st_errstatus_str)) {
                    dto.setDs_value("换能器故障");
                } else if ("10000".equals(st_errstatus_str)) {
                    dto.setDs_value("水表安装方向错误");
                } else {
                    dto.setDs_value(rs.getString("ds_value"));
                }
            } else if ("Switch".equals(rs.getString("ds_id"))) {
                if ("0".equals(rs.getString("ds_value"))) {
                    dto.setDs_value("开");
                } else if ("1".equals(rs.getString("ds_value"))) {
                    dto.setDs_value("关");
                } else {
                    dto.setDs_value("异常");
                }
            } else {
                dto.setDs_value(rs.getString("ds_value"));
            }

            dto.setDs_at(rs.getString("ds_at"));
            dto.setReadTime(rs.getString("readTime"));
            dto.setRemark(rs.getString("remark"));
            return dto;
        }
    }

    public class OneNetNbSendDataDTOMapper implements RowMapper {
        public OneNetNbSendDataDTOMapper() {
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

    public class WillNbSendDataDTOMapper implements RowMapper {
        public WillNbSendDataDTOMapper() {
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
            dto.setSendFrame(rs.getString("sendFrame"));
            return dto;
        }
    }
}
