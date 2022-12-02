//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.service;

import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.system.dao.GenPkIdDao;
import com.yinhe.ec.cpps.util.AliyunSendSms;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.MeterDao;
import com.yinhe.ec.cpps.ycmeter.dao.NbMeterDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeterService {
    @Resource
    private MeterDao meterDao;
    @Resource
    private GenPkIdDao genPkIdDao;
    @Resource
    private NbMeterDao nbMeterDao;
    @Resource
    private YinheService yinheService;

    public MeterService() {
    }

    public Map<Object, Object> getMeterInfo(int page, int rows, String order) {
        order = " 1=1 " + order;
        Map<Object, Object> map = new HashMap();
        map.put("total", this.meterDao.getTotal(order));
        map.put("rows", this.meterDao.getMeterInfo(page, rows, order));
        return map;
    }

    public List<UserInfoView> getMeterListByClause(String order) {
        order = " 1=1 " + order;
        return this.meterDao.getMeterListByClause(order);
    }

    public Integer getMaxRecNo(String musterNo) {
        return this.meterDao.getMaxRecNo(musterNo);
    }

//    @BussAnnotation(
//            moduleName = "远程表管理",
//            option = "更换仪表"
//    )
    public String changeMeterInfo(MeterChange meterchange) {
        if (this.meterDao.getMeterCount(meterchange) > 0) {
            return "更换失败，新表号已经存在";
        } else {
            try {
                List<NbMeterInfo> list = this.nbMeterDao.getNbMeterListByClause(" and meterNo='" + meterchange.getPreMeterNo() + "'");
                if (list.size() > 0 && !"".equals(meterchange.getNodeId()) && !((NbMeterInfo)list.get(0)).getNodeId().equals(meterchange.getNodeId()) && ((NbMeterInfo)list.get(0)).getRegState() == 1) {
                    this.yinheService.deleteDevice(((NbMeterInfo)list.get(0)).getDeviceId());
                }

                this.meterDao.changeMeterInfo(meterchange);
                if (list.size() > 0 && !"".equals(meterchange.getNodeId()) && !((NbMeterInfo)list.get(0)).getNodeId().equals(meterchange.getNodeId())) {
                    this.yinheService.registerDevice(meterchange.getNodeId(), ((NbMeterInfo)list.get(0)).getTypeId(), ((NbMeterInfo)list.get(0)).getTmodel(), ((NbMeterInfo)list.get(0)).getImsi());
                }

                return "更换成功";
            } catch (DataAccessException var3) {
                var3.printStackTrace();
                return var3.getMessage();
            }
        }
    }

    public List<DosageDays> getDayDosageByMeterNo(String meterNo) {
        return this.meterDao.getDayDosageByMeterNo(meterNo);
    }

//    @BussAnnotation(
//            moduleName = "远程表管理",
//            option = "修改仪表日用量最大值"
//    )
    public String updateMaxdayDosage(String meterNo, Double maxDosage) {
        try {
            this.meterDao.updateMaxdayDosage(meterNo, maxDosage);
            return "修改成功";
        } catch (DataAccessException var4) {
            var4.printStackTrace();
            return var4.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "远程表管理",
//            option = "异常数据审核"
//    )
    public String modDayDosageSum(String meterNo, String markday, Double dayDosageSum, String checker) {
        try {
            this.meterDao.modDayDosageSum(meterNo, markday, dayDosageSum, checker);
            return "审核通过";
        } catch (DataAccessException var6) {
            var6.printStackTrace();
            return var6.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "远程表管理",
//            option = "异常数据逐一审核"
//    )
    public String getResultStrForUpdateDosageDays(String meterNo, String markday, Double dayDosageSum, String checker) {
        try {
            return this.meterDao.getResultStrForUpdateDosageDays(meterNo, markday, dayDosageSum, checker);
        } catch (DataAccessException var6) {
            var6.printStackTrace();
            return var6.getMessage();
        }
    }

    public String addMessageInfo(String messageId, String mobile, String tplId, String tplValue, String userId, String userName, String operatorName, int custId, String leftSum) {
        try {
            this.meterDao.addMessageInfo(messageId, mobile, tplId, tplValue, userId, userName, operatorName, custId, leftSum);
            return "添加短信成功";
        } catch (DataAccessException var11) {
            var11.printStackTrace();
            return var11.getMessage();
        }
    }

    public Map<Object, Object> getSendMsgInfo(int page, int rows, String orders) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.meterDao.getMsgTotal(orders));
        map.put("rows", this.meterDao.getSendMsgInfo(page, rows, orders));
        return map;
    }

    public void getListForSendmsg(String order) throws UnsupportedEncodingException {
        order = " 1=1 " + order;
        String userId = "";
        String userName = "";
        String mobilePhone = "";
        Double leftSum = 0.0;
        String lastMsgDt = "";
        String tpl_id = "";
        int custId = 1;
        String meterNo = "";
        int smsKind = 0;
        List<UserInfoView> list = this.meterDao.getMeterListByClause(order);
        System.out.println("--push message scanning......" + list.size());

        for(int j = 0; j < list.size(); ++j) {
            userId = ((UserInfoView)list.get(j)).getUserId();
            userName = ((UserInfoView)list.get(j)).getUserName();
            mobilePhone = ((UserInfoView)list.get(j)).getMobilePhone();
            leftSum = ((UserInfoView)list.get(j)).getLeftSum();
            custId = ((UserInfoView)list.get(j)).getCustId();
            smsKind = ((UserInfoView)list.get(j)).getSmsKind();
            meterNo = ((UserInfoView)list.get(j)).getMeterNo();
            lastMsgDt = this.meterDao.getLastMsgDt(userId);
            lastMsgDt = lastMsgDt.replace("-", "");
            String result = "";
            String resultCode = "";
            String messageContent = "";
            int ndays = -1;
            if (!"".equals(lastMsgDt)) {
                try {
                    ndays = Tools.daysBetween(lastMsgDt, Tools.getForNDay(0).replace("-", ""));
                } catch (ParseException var19) {
                    var19.printStackTrace();
                }
            }

            String messageId;
            if (leftSum <= 0.0) {
                tpl_id = "SMS_218549112";
                if (!"".equals(mobilePhone) && mobilePhone.length() == 11 && (ndays > 7 || ndays == -1) && smsKind == 1) {
                    messageId = "{\"userId\":\"" + userId + "\"}";
                    result = AliyunSendSms.SendSms(custId, mobilePhone, tpl_id, messageId);
                    JSONObject jsonobj = JSONObject.parseObject(result);
                    if (jsonobj != null) {
                        resultCode = jsonobj.get("Code").toString();
                        messageContent = "尊敬的" + userName + ", 电表" + meterNo + "余量不足,请及时缴费！";
                    }
                }
            }

            if ("OK".equals(resultCode)) {
                messageId = this.genPkIdDao.getPkNoByTable("MessageList", "MessageId", 9);
                this.meterDao.addAliMessageInfo(messageId, mobilePhone, tpl_id, messageContent, userId, userName, "Auto", custId);
            }
        }

    }

    public Meters getMetersByClause(String orders) {
        orders = " 1=1 " + orders;
        return this.meterDao.getMetersByClause(orders);
    }

    public void getListForSendmsgForWuqiao(String order) throws UnsupportedEncodingException {
        order = " 1=1 " + order;
        String userId = "";
        String userName = "";
        String mobilePhone = "";
        Double leftSum = 0.0;
        String lastMsgDt = "";
        int custId = 1;
        String tpl_id = "WQ001";
        String sn = "DXX-HRM-010-00007";
        String pwd = "DECB72850E455401FB63B397AAA188E3";
        String url = "";
        String jsonStr = "";
        String type = "1";
        String tmp_tpl_value = "";
        List<UserInfoView> list = this.meterDao.getMeterListByClause(order);
        System.out.println("--wuqiao push message scanning......" + list.size());

        for(int j = 0; j < list.size(); ++j) {
            userId = ((UserInfoView)list.get(j)).getUserId();
            userName = ((UserInfoView)list.get(j)).getUserName();
            mobilePhone = ((UserInfoView)list.get(j)).getMobilePhone();
            leftSum = ((UserInfoView)list.get(j)).getLeftSum();
            custId = ((UserInfoView)list.get(j)).getCustId();
            type = "" + ((UserInfoView)list.get(j)).getTypeId();
            if (leftSum < 0.0) {
                tpl_id = "WQ002";
            } else {
                tpl_id = "WQ001";
            }

            lastMsgDt = this.meterDao.getLastMsgDt(userId);
            lastMsgDt = lastMsgDt.replace("-", "");
            String company = ((UserInfoView)list.get(j)).getCustName();
            String desc = "";
            String tpl_value = "";
            if ("WQ001".equals(tpl_id)) {
                type = "水费";
                tpl_value = "(" + userId + ")" + userName + "您好！截至当前，水表余额" + leftSum + "元，为避免欠费停水，请及时充值。0317-7234011[吴桥自来水]";
            } else if ("WQ002".equals(tpl_id)) {
                type = "水费";
                tpl_value = "(" + userId + ")" + userName + "您好！截至当前，水表已欠费" + leftSum + "元，请及时充值。0317-7234011[吴桥自来水]";
            }

            String path = "";
            int ndays = -1;
            if (!"".equals(lastMsgDt)) {
                try {
                    ndays = Tools.daysBetween(lastMsgDt, Tools.getForNDay(0).replace("-", ""));
                } catch (ParseException var28) {
                    var28.printStackTrace();
                }
            }

            if (!"".equals(mobilePhone) && mobilePhone.length() == 11 && (ndays >= 10 || ndays == -1)) {
                try {
                    path = "http://sdk2.entinfo.cn:8061/mdsmssend.ashx?sn=" + sn + "&pwd=" + pwd + "&mobile=" + mobilePhone + "&content=" + URLEncoder.encode(tpl_value, "UTF-8") + "&ext=&stime=&rrid=&msgfmt=";
                    URL urls = new URL(path);
                    InputStream is = urls.openStream();
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);

                    for(String line = ""; (line = br.readLine()) != null; jsonStr = line) {
                    }

                    is.close();
                } catch (MalformedURLException var29) {
                    var29.printStackTrace();
                } catch (IOException var30) {
                    var30.printStackTrace();
                }

                String messageId = this.genPkIdDao.getPkNoByTable("MessageList", "MessageId", 9);
                this.meterDao.addMessageInfo(messageId, mobilePhone, tpl_id, tpl_value + "【" + jsonStr + "】", userId, userName, "Auto", custId, "" + leftSum);
            }
        }

    }

    public void addAliMessageInfo(String messageId, String phoneNo, String templateCode, String messageContent, String userId, String userName, String operatorName, int custId) {
        this.meterDao.addAliMessageInfo(messageId, phoneNo, templateCode, messageContent, userId, userName, operatorName, custId);
    }

    public String getLeftInfoByDosageSum(String meterNo, Double dosageSum) {
        return this.meterDao.getLeftInfoByDosageSum(meterNo, dosageSum);
    }

    public String getDosageSumByLeftDosage(String meterNo, Double leftDosage) {
        return this.meterDao.getDosageSumByLeftDosage(meterNo, leftDosage);
    }
}
