//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.model.NbResult;
import com.yinhe.ec.cpps.model.NbSendData;
import com.yinhe.ec.cpps.model.YinheData;
import com.yinhe.ec.cpps.model.YinheMeterData;
import com.yinhe.ec.cpps.util.HttpClientTools;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.NbMeterDao;
import com.yinhe.ec.cpps.ycmeter.dao.YinheDao;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YinheService {
    private static final Logger logger = Logger.getLogger(YinheService.class);
    @Resource
    private YinheDao yinheDao;
    @Resource
    private NbMeterDao nbMeterDao;

    public YinheService() {
    }

    public Map<String, Object> deviceDatas(String deviceId, String reciveFrame, String commandId) {
        return this.yinheDao.deviceDatas(deviceId, reciveFrame, commandId);
    }

    public NbResult postCommand(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String createUser, String sysSendId) {
        Map<String, String> map = this.yinheDao.postCommand(meterNo, deviceId, commandCode, commandValue, custId, createUser, sysSendId);
        NbResult nb = new NbResult();
        if ("1".equals(((String)map.get("typeId")).toString())) {
            nb = this.realPostCommand(((String)map.get("deviceId")).toString(), ((String)map.get("sid")).toString(), ((String)map.get("encodeFrame")).toString(), commandCode);
        } else {
            nb.setResultCode("OK");
            nb.setResultValue("命令已缓存");
        }

        return nb;
    }

    public synchronized NbResult realPostCommand(String deviceId, String sid, String encodeFrame, String commandCode) {
        YinheData yinheData = new YinheData();
        yinheData.setAppId("FzScc0Ly3jmgXqG6v7dBuSL1");
        yinheData.setDeviceId(deviceId);
        yinheData.setDataFormat("");
        yinheData.setTime(System.currentTimeMillis());
        yinheData.setSid(sid);
        yinheData.setAction("send");
        yinheData.setRawData(encodeFrame);
        yinheData.setSecret("HkjmepcDmWq5N59BGDItkVkS");
        JSONObject jsonObj = null;
        logger.info("发送命令信息：" + JSON.toJSONString(yinheData));
        String sendDate = Tools.getNow();
        String returnValue = HttpClientTools.HttpPostWithJson("http://39.104.180.79/yhiot/api/service/v1021/", JSON.toJSONString(yinheData));
        logger.info("接收命令信息：" + returnValue);
        String reciveDeviceId = "";
        String reciveSid = "";
        String reciveCommandId = "";
        String reciveSecret = "";
        String reciveMsg = "";
        NbResult nb = new NbResult();
        if (!"".equals(returnValue)) {
            this.yinheDao.updateNbsenddata(sid, 2);

            try {
                JSONObject returnObj = JSONObject.parseObject(returnValue);
                reciveSid = returnObj.get("sid").toString();
                if (returnObj.containsKey("secret")) {
                    reciveSecret = returnObj.get("secret").toString();
                }

                if (returnObj.containsKey("deviceId")) {
                    reciveDeviceId = returnObj.get("deviceId").toString();
                }

                if (returnObj.containsKey("commandId")) {
                    reciveCommandId = returnObj.get("commandId").toString();
                }

                if (returnObj.containsKey("msg")) {
                    reciveMsg = returnObj.get("msg").toString();
                }

                String sql = "";
                if ("".equals(reciveCommandId)) {
                    this.updateCommandId(reciveDeviceId, reciveSid, reciveSid);
                } else {
                    this.updateCommandId(reciveDeviceId, reciveSid, reciveCommandId);
                }

                if (deviceId.equals(reciveDeviceId)) {
                    if ("".equals(reciveCommandId)) {
                        if ("1".equals(commandCode)) {
                            sql = " and deviceid='" + reciveDeviceId + "' and datatype is null and readTime>='" + sendDate + "' ";
                        } else if (Integer.parseInt(commandCode) >= 9 && Integer.parseInt(commandCode) <= 12) {
                            sql = " and deviceid='" + reciveDeviceId + "' and datatype='9C' and readTime>='" + sendDate + "' ";
                        } else {
                            sql = " and deviceid='" + reciveDeviceId + "' and readTime>='" + sendDate + "' and datatype in (select dataitem from cmd where cmdid in (select sendtype from NBSENDDATA where sendid='" + reciveSid + "')) ";
                        }
                    } else {
                        sql = " and commandId='" + reciveCommandId + "'";
                    }

                    List<YinheMeterData> list = this.getYinheMeterData(sql);
                    long theTime = System.currentTimeMillis();

                    while(System.currentTimeMillis() < theTime + 180000L && list.size() == 0) {
                        list = this.getYinheMeterData(sql);

                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException var21) {
                            var21.printStackTrace();
                        }
                    }

                    if (list.size() > 0) {
                        nb.setResultCode("OK");
                        nb.setResultValue(((YinheMeterData)list.get(0)).getMeterData());
                    } else {
                        nb.setResultCode("ERR");
                        nb.setResultValue("失败");
                    }
                } else {
                    nb.setResultCode("ERR");
                    nb.setResultValue("下发和回复的设备不一致");
                }
            } catch (JSONException var22) {
                nb.setResultCode("ERR");
                nb.setResultValue("通讯异常");
                var22.printStackTrace();
            }
        } else {
            nb.setResultCode("ERR");
            nb.setResultValue("通讯异常");
        }

        return nb;
    }

    public void updateCommandId(String deviceId, String sid, String commandId) {
        this.yinheDao.updateCommandId(deviceId, sid, commandId);
    }

//    @BussAnnotation(
//            moduleName = "物联网平台",
//            option = "注册设备"
//    )
    public Object registerDevice(String nodeId, int typeId, int tmodel, String imsi) {
        Map<String, Object> map = new HashMap();
        map.put("appId", "FzScc0Ly3jmgXqG6v7dBuSL1");
        map.put("secret", "HkjmepcDmWq5N59BGDItkVkS");
        map.put("sid", String.valueOf(System.currentTimeMillis()));
        map.put("iotNodeId", nodeId);
        if (tmodel == 1) {
            map.put("imsi", imsi);
        }

        map.put("action", "register");
        if (tmodel == 0 && typeId == 2) {
            map.put("iotProvider", "hlxP1k3a76Gs9hZXfvViO5Bk");
        }

        if (tmodel == 1 && typeId == 1) {
            map.put("iotProvider", "na_onenet_huangling_v1");
        }

        JSONObject jsonObj = new JSONObject(map);
        logger.info("发送注册信息：" + jsonObj);
        String returnValue = HttpClientTools.HttpPostWithJson("http://39.104.180.79/yhiot/api/service/v1021/", jsonObj.toString());
        NbResult nb = new NbResult();
        if (!"".equals(returnValue)) {
            String reciveDeviceId = "";

            try {
                JSONObject returnObj =JSONObject.parseObject(returnValue);
                logger.info("接收注册信息：" + returnObj);
                if (returnObj != null) {
                    reciveDeviceId = returnObj.get("deviceId").toString();
                    this.yinheDao.registerDevice(nodeId, reciveDeviceId);
                    nb.setResultCode("OK");
                    nb.setResultValue("注册成功");
                }
            } catch (JSONException var12) {
                nb.setResultCode("ERR");
                nb.setResultValue("注册失败");
                var12.printStackTrace();
            }
        } else {
            nb.setResultCode("ERR");
            nb.setResultValue("注册失败");
        }

        return nb;
    }

//    @BussAnnotation(
//            moduleName = "物联网平台",
//            option = "删除设备"
//    )
    public Object deleteDevice(String deviceId) {
        Map<String, Object> map = new HashMap();
        map.put("appId", "FzScc0Ly3jmgXqG6v7dBuSL1");
        map.put("secret", "HkjmepcDmWq5N59BGDItkVkS");
        map.put("sid", String.valueOf(System.currentTimeMillis()));
        map.put("deviceId", deviceId);
        map.put("action", "unregister");
        JSONObject jsonObj = new JSONObject(map);
        logger.info("发送注销信息：" + jsonObj);
        String returnValue = HttpClientTools.HttpPostWithJson("http://39.104.180.79/yhiot/api/service/v1021/", jsonObj.toString());
        NbResult nb = new NbResult();
        if (!"".equals(returnValue)) {
            String reciveDeviceId = "";
            String message = "";

            try {
                JSONObject returnObj = JSONObject.parseObject(returnValue);
                logger.info("接收注销信息：" + returnObj);
                if (returnObj != null) {
                    message = returnObj.get("msg").toString();
                    if ("ok".equals(message.toLowerCase())) {
                        reciveDeviceId = returnObj.get("deviceId").toString();
                        this.yinheDao.deleteDevice(reciveDeviceId);
                        nb.setResultCode("OK");
                        nb.setResultValue("删除成功");
                    } else {
                        nb.setResultCode("ERR");
                        nb.setResultValue("删除失败");
                    }
                }
            } catch (JSONException var10) {
                nb.setResultCode("ERR");
                nb.setResultValue("删除失败");
                var10.printStackTrace();
            }
        } else {
            nb.setResultCode("ERR");
            nb.setResultValue("删除失败");
        }

        return nb;
    }

    public List<YinheMeterData> getYinheMeterData(String orders) {
        return this.yinheDao.getYinheMeterData(orders);
    }

//    @BussAnnotation(
//            moduleName = "物联网平台",
//            option = "修正注册信息不一致"
//    )
    public String modifyDeviceId(String meterNo, String uploadDeviceId, String uploadNodeId) {
        return this.yinheDao.modifyDeviceId(meterNo, uploadDeviceId, uploadNodeId);
    }

    public List<NbSendData> getWillPostData(String orders) {
        return this.nbMeterDao.getWillPostData(orders);
    }

    public List<NbSendData> getWaterWillPostData(String orders) {
        return this.nbMeterDao.getWaterWillPostData(orders);
    }

    public void updateTmpnbsenddata(String sendId, int manageFlag) {
        this.yinheDao.updateTmpnbsenddata(sendId, manageFlag);
    }

    @Async
    public void asyncPostCmd(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String createUser, String sysSendId) {
        this.postCommand(meterNo, deviceId, commandCode, commandValue, custId, createUser, sysSendId);
    }

    public List<NbSendData> getPostCommand(String orders) {
        return this.yinheDao.getPostCommand(orders);
    }

    public void updateNbsenddata(String sendId, int manageFlag) {
        this.yinheDao.updateNbsenddata(sendId, manageFlag);
    }
}
