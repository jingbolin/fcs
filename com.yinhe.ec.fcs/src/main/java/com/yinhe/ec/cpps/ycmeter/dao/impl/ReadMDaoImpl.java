//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.MeterInfo;
import com.yinhe.ec.cpps.util.ARMCilentSocket;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.ReadMDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.DataInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class ReadMDaoImpl implements ReadMDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    private ARMCilentSocket cs = null;
    private byte[] buf = new byte[1024];
    public String Data = "";

    public ReadMDaoImpl() {
    }

    private boolean createConnection(Integer port) {
        try {
            this.cs = new ARMCilentSocket(ConstParam.SOCKETIP, port);
            this.cs.CreateConnection();
            return true;
        } catch (Exception var3) {
            this.Data = "连接服务器失败";
            return false;
        }
    }

    private boolean sendMessage() {
        boolean sucflag = false;
        if (this.cs == null) {
            return false;
        } else {
            try {
                this.cs.sendMessage(this.buf);
                new String(this.buf);
                sucflag = true;
            } catch (Exception var3) {
                this.Data = "发送消息失败";
                sucflag = false;
            }

            return sucflag;
        }
    }

    private void getMessage() {
        if (this.cs != null) {
            DataInputStream inputStream = null;

            try {
                inputStream = this.cs.getMessageStream();

                try {
                    int firstChar = inputStream.read();
                    int length = inputStream.available();
                    byte[] recData = new byte[length + 1];
                    recData[0] = (byte)firstChar;
                    inputStream.read(recData, 1, length);
                    this.Data = new String(recData);
                    if (this.Data.indexOf("Idle") != -1) {
                        Thread.sleep(1000L);
                        this.getMessage();
                    }

                    return;
                } catch (Exception var9) {
                    this.Data = "接收消息错误";
                    return;
                }
            } catch (Exception var10) {
                this.Data = "接收消息缓存错误";
            } finally {
                this.cs.shutDownConnection();
            }

        }
    }

    public int getReadServerPort(String musterNo) {
//        return this.jdbcTemplate.queryForInt("select readServerPort from muster where MusterNo='" + musterNo + "'");
        return 0;
    }

    private boolean upMeterSwitchState(String meterNo, int onstate) {
        return this.jdbcTemplate.update("update MeterInfo set ONSTATE=" + onstate + " where meterNo='" + meterNo + "'") >= 0;
    }

    private int getMaxOnOffId() {
        int tmpId = 0;
//        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
//            if (this.jdbcTemplate.queryForInt("select count(*) from ONOFFLOGS") > 0) {
//                tmpId = this.jdbcTemplate.queryForInt("select * from (select ONOFFID from ONOFFLOGS order by ONOFFID desc) where rownum=1 ");
//            }
//        } else if ("PG".equals(ConstParam.SQLTYPE)) {
//            if (this.jdbcTemplate.queryForInt("select count(*) from ONOFFLOGS") > 0) {
//                tmpId = this.jdbcTemplate.queryForInt("select ONOFFID from ONOFFLOGS order by ONOFFID desc LIMIT 1");
//            }
//        } else if (this.jdbcTemplate.queryForInt("select count(*) from ONOFFLOGS") > 0) {
//            tmpId = this.jdbcTemplate.queryForInt("select top 1 ONOFFID from ONOFFLOGS order by ONOFFID desc");
//        }

        return tmpId + 1;
    }

    private boolean insertOnOffLogs(String meterNo, Integer onstate, Integer sucflag, String opName, int custId) {
        return this.jdbcTemplate.update("INSERT INTO ONOFFLOGS(ONOFFID,CUSTID,METERNO,ONOFFFLAG,SUCCFLAG,ONOFFDATE,ONOFFUSER) VALUES ('" + this.getMaxOnOffId() + "'," + custId + ",'" + meterNo + "'," + onstate + "," + sucflag + ",'" + Tools.getNow() + "','" + opName + "')") >= 0;
    }

    public boolean addAndUpdateData(final String meterNo, final double dosage, final int n, final String readUser, final int custId) {
        String isql = "INSERT INTO dosagedays(MeterNo,CustId,MarkDay,Dosagesum,REALDOSAGESUM,ReadDate,ReadUser,DOSAGESTATE)VALUES(?,?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, meterNo);
                ps.setInt(2, custId);
                ps.setString(3, Tools.getForNDay(n));
                ps.setDouble(4, dosage);
                ps.setDouble(5, dosage);
                ps.setString(6, Tools.getNow());
                ps.setString(7, readUser);
                ps.setInt(8, 1);
            }
        }) >= 0;
    }

    public boolean addAndUpdateData(String meterNo, double dosage, String readUser) {
        return this.jdbcTemplate.update("UPDATE MeterInfo SET DosageSum=" + dosage + ",READDATE='" + Tools.getNow() + "' WHERE meterNo='" + meterNo + "'") >= 0;
    }

    @Override
    public synchronized String getData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, int custId) {
        this.Data = "";
        String[] errInfo = new String[]{"Err Data", "Err Frame", "Err Place", "Err Check", "Busy", "Time Out", "NO Online", "Not Exist", "Stop", "NO Data", "DisConnect", "DisHomeSite", "NO Func", "NO MusterPTL", "NO Support", "Err", "Idle", "Err@抄表异常", "状态异常", "连接服务器失败"};
        if (commItem.equals("2007")) {
            mPara = Tools.getNow();
        }

        int tRecNo = 1;
        this.buf = new byte[1024];
        byte[] temp = new byte[1023];
        temp = musterNo.getBytes();
        System.arraycopy(temp, 0, this.buf, 0, temp.length);
        temp = meterNo.getBytes();
        System.arraycopy(temp, 0, this.buf, 20, temp.length);
        temp = musterPtl.getBytes();
        System.arraycopy(temp, 0, this.buf, 40, temp.length);
        temp = commItem.getBytes();
        System.arraycopy(temp, 0, this.buf, 42, temp.length);
        temp = connPara.getBytes();
        System.arraycopy(temp, 0, this.buf, 46, temp.length);
        temp = mPara.getBytes();
        System.arraycopy(temp, 0, this.buf, 96, temp.length);
        String Prioprity = "0";
        temp = Prioprity.getBytes();
        System.arraycopy(temp, 0, this.buf, 1023, temp.length);

        try {
            Thread.sleep(500L);
            System.out.println(Tools.getNow() + "--->" + meterNo);
            if (this.createConnection(this.getReadServerPort(musterNo))) {
                if (this.sendMessage()) {
                    this.getMessage();
                } else {
                    System.out.println("发送参数失败");
                }
            }
        } catch (Exception var44) {
            var44.printStackTrace();
        }

        String theNo = "";
        String realData = "";
        System.out.println(Tools.getNow() + "<---" + this.Data);
        if (this.Data.indexOf("Err@") == -1 && this.Data.indexOf("@") != -1) {
            theNo = this.Data.substring(0, this.Data.indexOf("@"));
            realData = this.Data.substring(this.Data.indexOf("@") + 1, this.Data.length());
            if (!theNo.equals(meterNo)) {
                if (commItem.equals("2000")) {
                    try {
                        this.insertOnOffLogs(meterNo, 0, 2, opName, custId);
                    } catch (Exception var25) {
                        var25.printStackTrace();
                    }
                }

                if (commItem.equals("2002")) {
                    try {
                        this.insertOnOffLogs(meterNo, 1, 2, opName, custId);
                    } catch (Exception var24) {
                        var24.printStackTrace();
                    }
                }

                if (commItem.equals("2501")) {
                    try {
                        this.insertOnOffLogs(meterNo, 2, 2, opName, custId);
                    } catch (Exception var23) {
                        var23.printStackTrace();
                    }
                }

                if (commItem.equals("2502")) {
                    try {
                        this.insertOnOffLogs(meterNo, 3, 2, opName, custId);
                    } catch (Exception var22) {
                        var22.printStackTrace();
                    }
                }

                if (commItem.equals("1500")) {
                    try {
                        this.insertOnOffLogs(meterNo, 4, 2, opName, custId);
                    } catch (Exception var21) {
                        var21.printStackTrace();
                    }
                }

                return "Err Place";
            }

            this.Data = realData;
        }

        String[] var18 = errInfo;
        int var17 = errInfo.length;

        for(int var16 = 0; var16 < var17; ++var16) {
            String err = var18[var16];
            if (this.Data.equals(err)) {
                if (commItem.equals("2000")) {
                    try {
                        this.insertOnOffLogs(meterNo, 0, 2, opName, custId);
                    } catch (Exception var43) {
                        var43.printStackTrace();
                    }
                }

                if (commItem.equals("2002")) {
                    try {
                        this.insertOnOffLogs(meterNo, 1, 2, opName, custId);
                    } catch (Exception var42) {
                        var42.printStackTrace();
                    }
                }

                if (commItem.equals("2501")) {
                    try {
                        this.insertOnOffLogs(meterNo, 2, 2, opName, custId);
                    } catch (Exception var41) {
                        var41.printStackTrace();
                    }
                }

                if (commItem.equals("2502")) {
                    try {
                        this.insertOnOffLogs(meterNo, 3, 2, opName, custId);
                    } catch (Exception var40) {
                        var40.printStackTrace();
                    }
                }

                if (commItem.equals("1500")) {
                    try {
                        this.insertOnOffLogs(meterNo, 4, 2, opName, custId);
                    } catch (Exception var39) {
                        var39.printStackTrace();
                    }
                }

                return this.Data;
            }
        }

        if (commItem.equals("1000")) {
            try {
                this.Data = String.valueOf(Double.parseDouble(this.Data));
                this.addAndUpdateData(meterNo, Double.parseDouble(this.Data), "0000");
            } catch (Exception var38) {
                var38.printStackTrace();
            }
        }

        String[] tmpcmd = new String[]{"1001", "1002", "1003", "1004", "1005", "1006", "1007"};
        String[] var19 = tmpcmd;
        int j = tmpcmd.length;

        String sql;
        for(var17 = 0; var17 < j; ++var17) {
            sql = var19[var17];
            if (commItem.equals(sql)) {
                try {
                    this.Data = String.valueOf(Double.parseDouble(this.Data));
                } catch (Exception var37) {
                    var37.printStackTrace();
                }
                break;
            }
        }

        if (commItem.equals("1008")) {
            try {
                this.Data = String.valueOf(Double.parseDouble(this.Data));
            } catch (Exception var36) {
                var36.printStackTrace();
            }
        }

        if (commItem.equals("1009")) {
            try {
                this.Data = String.valueOf(Double.parseDouble(this.Data));
            } catch (Exception var35) {
                var35.printStackTrace();
            }
        }

        if (commItem.equals("1902")) {
            try {
                this.upMeterSwitchState(meterNo, Integer.parseInt(this.Data));
                if (this.Data.equals("0")) {
                    this.Data = "开通";
                } else if (this.Data.equals("1")) {
                    this.Data = "关断";
                } else if (this.Data.equals("2")) {
                    this.Data = "开通*";
                } else if (this.Data.equals("3")) {
                    this.Data = "关断*";
                }
            } catch (Exception var34) {
                var34.printStackTrace();
            }
        }

        if (commItem.equals("2000")) {
            try {
                this.insertOnOffLogs(meterNo, 0, 1, opName, custId);
                this.upMeterSwitchState(meterNo, 0);
            } catch (Exception var33) {
                var33.printStackTrace();
            }
        }

        if (commItem.equals("2002")) {
            try {
                this.insertOnOffLogs(meterNo, 1, 1, opName, custId);
                this.upMeterSwitchState(meterNo, 1);
            } catch (Exception var32) {
                var32.printStackTrace();
            }
        }

        if (commItem.equals("3001")) {
            try {
                this.upMeterSwitchState(meterNo, 0);
                this.insertOnOffLogs(meterNo, 0, 1, opName, custId);
            } catch (Exception var31) {
                var31.printStackTrace();
            }
        }

        if (commItem.equals("3002")) {
            try {
                this.upMeterSwitchState(meterNo, 1);
                this.insertOnOffLogs(meterNo, 1, 1, opName, custId);
            } catch (Exception var30) {
                var30.printStackTrace();
            }
        }

        if (commItem.equals("2501")) {
            try {
                this.insertOnOffLogs(meterNo, 2, 1, opName, custId);
            } catch (Exception var29) {
                var29.printStackTrace();
            }
        }

        if (commItem.equals("2502")) {
            try {
                this.insertOnOffLogs(meterNo, 3, 1, opName, custId);
            } catch (Exception var28) {
                var28.printStackTrace();
            }
        }

        if (commItem.equals("1500")) {
            if (this.Data.equals("0")) {
                this.Data = "取消保电";
            } else if (this.Data.equals("1")) {
                this.Data = "保电";
            }

            try {
                this.insertOnOffLogs(meterNo, 4, 1, opName, custId);
            } catch (Exception var27) {
                var27.printStackTrace();
            }
        }

        if (commItem.equals("9005")) {
            try {
                this.jdbcTemplate.update("update Muster set initflag=1 where musterno='" + musterNo + "'");
                sql = "select meterNo from MeterInfo where musterNo='" + musterNo + "' order by recNo";
                List<MeterInfo> meters = this.jdbcTemplate.query(sql, new MeterDTOMapper());

                for(j = 0; j < meters.size(); ++j) {
                    this.jdbcTemplate.update("update MeterInfo set syncFlag=0,RecNo=" + tRecNo + " where meterNo='" + ((MeterInfo)meters.get(j)).getMeterNo() + "'");
                    ++tRecNo;
                }

                if (this.Data.indexOf("NO Online") != -1) {
                    return this.Data;
                }

                Thread.sleep(180000L);
            } catch (InterruptedException var45) {
                var45.printStackTrace();
            }
        }

        if (commItem.equals("9110")) {
            try {
                Thread.sleep(40000L);
            } catch (InterruptedException var26) {
                var26.printStackTrace();
            }
        }

        return this.Data;
    }

    @Override
    public synchronized String getData(String musterNo, String meterNo, String musterPtl, String[] commItem, String connPara, String mPara, String controlInfo, String module) {
        int tRecNo = 1;
        char[] conmad = controlInfo.toCharArray();

        for(int i = 0; i < conmad.length; ++i) {
            if (module.equals("4")) {
                if (conmad[i] == '1') {
                    if (i == 0) {
                        this.Data = this.getData(musterNo, meterNo, musterPtl, commItem[i], connPara, mPara, "9999", 0);
                    } else if (i == 1) {
                        List<MeterInfo> meters = this.jdbcTemplate.query("select meterNo from MeterInfo where musterNo='" + musterNo + "' order by recNo", new MetersDTOMapper());

                        for(int j = 0; j < meters.size(); ++j) {
                            this.jdbcTemplate.update("update MeterInfo set RecNo=" + tRecNo + " where meterNo='" + meters.get(j) + "'");
                            ++tRecNo;
                        }
                    }
                }
            } else if (module.equals("1") && conmad[i] == '1') {
                if (i == 0) {
                    this.Data = this.getData(musterNo, meterNo, musterPtl, commItem[i], connPara, mPara, "9999", 0);
                } else if (i == 1) {
                    this.Data = this.getData(musterNo, meterNo, musterPtl, commItem[i], connPara, Tools.getNow(), "9999", 0);
                }
            }
        }

        return this.Data;
    }

    @Override
    public synchronized String getTryData(String musterNo, String meterNo, String musterPtl, String[] commItem, String connPara, String mPara) {
        int trycount = 0;
        String tryFlag = "0";
        String initFlag = "0";
        String[] mParaArr = mPara.split("@");
        String[] errInfo = new String[]{"Err Data", "Err Frame", "Err Place", "Err Check", "Busy", "Time Out", "NO Online", "Not Exist", "Stop", "NO Data", "DisConnect", "DisHomeSite", "NO Func", "NO MusterPTL", "NO Support", "Err", "Idle", "Err@抄表异常", "状态异常", "连接服务器失败"};

        try {
            for(int i = 0; i < commItem.length; ++i) {
                Thread.sleep(500L);
                if ("".equals(commItem[i])) {
                    this.Data = "OK";
                } else {
                    this.Data = this.getData(musterNo, meterNo, musterPtl, commItem[i], connPara, mParaArr[i], "9999", 0);
                }

                if ("9007".equals(commItem[i])) {
                    if ("OK".equals(this.Data)) {
                        initFlag = "1";
                    } else {
                        initFlag = "0";
                    }
                }

                String[] var16 = errInfo;
                int var15 = errInfo.length;

                for(int var14 = 0; var14 < var15; ++var14) {
                    String err = var16[var14];
                    if (this.Data.equals(err)) {
                        ++trycount;
                        break;
                    }
                }

                if (trycount != 0) {
                    tryFlag = "2";
                    break;
                }
            }
        } catch (InterruptedException var18) {
            var18.printStackTrace();
        }

        if (trycount == 0) {
            this.Data = "成功";
            tryFlag = "1";
        }

        try {
            this.upTryFlag(meterNo, tryFlag, initFlag);
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        return this.Data;
    }

    public boolean upTryFlag(String meterNo, String tryFlag, String initFlag) {
        String sql = "";
        if (!"1".equals(tryFlag)) {
            sql = "update MeterInfo set SYNCFLAG=" + initFlag + ",tryFlag=" + tryFlag + " where meterNo='" + meterNo + "'";
        } else {
            sql = "update MeterInfo set SYNCFLAG=" + initFlag + ",tryFlag=" + tryFlag + ",ReadDate='" + Tools.getNow() + "' where meterNo='" + meterNo + "'";
        }

        return this.jdbcTemplate.update(sql) >= 0;
    }

    @Override
    public String getSendData(String musterNo, String meterNo, String musterPtl, String commItem, String connPara, String mPara, String opName, int sId) {
        String manageFlag = "0";
        this.Data = this.getData(musterNo, meterNo, musterPtl, commItem, connPara, mPara, opName, 0);
        if ("数据请求已下发".equals(this.Data)) {
            return this.Data;
        } else {
            if ("OK".equals(this.Data)) {
                manageFlag = "1";
                this.jdbcTemplate.update("update SendData set manageFlag=" + manageFlag + ",MANAGEDATE='" + Tools.getNow() + "' where SID=" + sId);
            } else {
                manageFlag = "2";
                this.jdbcTemplate.update("update SendData set manageFlag=" + manageFlag + ",manageCount=manageCount+1,MANAGEDATE='" + Tools.getNow() + "' where SID=" + sId);
            }

            return this.Data;
        }
    }

    private int getMaxSendDataId() {
//        int tmpId = false;
        int tmpId=0;
//        if ("ORACLE".equals(ConstParam.SQLTYPE)) {
//            tmpId = this.jdbcTemplate.queryForInt("select * from (select SID from SendData order by SID desc) where rownum=1 ");
//        } else if ("PG".equals(ConstParam.SQLTYPE)) {
//            tmpId = this.jdbcTemplate.queryForInt("select SID from SendData order by SID desc limit 1 ");
//        } else {
//            tmpId = this.jdbcTemplate.queryForInt("select top 1 SID from SendData order by SID desc");
//        }

        return tmpId + 1;
    }

    @Override
    public void insertSendData(String musterNo, final String meterNo, final String sendDetail, final int sendType, final String opName, final int custId, final int readStyle) {
        String isql = "insert into SendData(sid,custId,meterno,sendtype,SENDDETAIL,CREATEDATE,manageflag,managecount,createUser,ReadStyle) values (?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(isql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, ReadMDaoImpl.this.getMaxSendDataId());
                ps.setInt(2, custId);
                ps.setString(3, meterNo);
                ps.setInt(4, sendType);
                ps.setString(5, sendDetail);
                ps.setString(6, Tools.getNow());
                ps.setInt(7, 0);
                ps.setInt(8, 0);
                ps.setString(9, "手动下发【" + opName + "】");
                ps.setInt(10, readStyle);
            }
        });
    }

    @Override
    public void updateSendDataManageFlag(String sid, int manageFlag) {
        String sql = "update sendData set manageFlag=" + manageFlag + " where sid=" + sid;
        if (manageFlag == 0) {
            sql = "update sendData set ManageFlag=" + manageFlag + ",ManageCount=0,Managedate='',Remark='' where sid=" + sid;
        }

        this.jdbcTemplate.update(sql);
    }
@Override
    public void updateMeterOnOff(String meterNo, String commItem, String opName, int custId) {
//        int onoff = false;
//        byte onoff;
//        if ("2501".equals(commItem)) {
//            onoff = 0;
//        } else {
//            onoff = 1;
//        }
//
//        String sql1 = "update meterinfo set onOff=" + onoff + " where meterNo='" + meterNo + "'";
//        this.jdbcTemplate.update(sql1);
//        this.insertOnOffLogs(meterNo, Integer.parseInt(commItem), 1, opName, custId);
    }

    public class MeterDTOMapper implements RowMapper {
        public MeterDTOMapper() {
        }
@Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterInfo meter = new MeterInfo();
            meter.setMeterNo(rs.getString("meterNo"));
            return meter;
        }
    }

    public class MetersDTOMapper implements RowMapper {
        public MetersDTOMapper() {
        }
@Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            MeterInfo dto = new MeterInfo();
            dto.setMeterNo(rs.getString("MeterNo"));
            return dto;
        }
    }
}
