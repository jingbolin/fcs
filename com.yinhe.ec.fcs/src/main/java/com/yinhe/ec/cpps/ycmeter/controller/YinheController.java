//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.model.NbMeterInfo;
import com.yinhe.ec.cpps.model.NbSendData;
import com.yinhe.ec.cpps.model.YinheMeterData;
import com.yinhe.ec.cpps.system.service.BaseParamService;
//import com.yinhe.ec.cpps.ycmeter.service.NbMeterService;
import com.yinhe.ec.cpps.ycmeter.service.YinheService;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class YinheController {
    private static final Logger logger = Logger.getLogger(YinheController.class);
    @Resource
    private YinheService yinheService;
    @Resource
    private BaseParamService baseParamService;
//    @Resource
//    private NbMeterService nbMeterService;

    public YinheController() {
    }

    @RequestMapping(
            value = {"/api/yhiot/deviceDatas"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Object deviceDatas(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";
        Map<String, String> map = new HashMap();

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            logger.info("设备上报数据-接收到源字符串：" + contentStr.trim());
            String deviceId = "";
            String reciveFrame = "";
            String commandId = "";
            String sid = "";

            try {
                JSONObject jsonobj = new JSONObject(contentStr);
                deviceId = jsonobj.get("deviceId").toString();
                if (jsonobj.has("rawData")) {
                    reciveFrame = jsonobj.get("rawData").toString();
                }

                if (jsonobj.has("commandId")) {
                    commandId = jsonobj.get("commandId").toString();
                }

                if (jsonobj.has("sid")) {
                    sid = jsonobj.get("sid").toString();
                    map.put("sid", sid);
                }

                logger.info("设备上报数据：deviceId=" + deviceId + " reciveFrame=" + reciveFrame);
                Map<String, Object> resultMap = null;
                if (!"".equals(reciveFrame)) {
                    resultMap = this.yinheService.deviceDatas(deviceId, reciveFrame, commandId);
                }

                if ("".equals(commandId) && resultMap != null) {
                    int custId = Integer.parseInt(resultMap.get("custId").toString());
                    String typeId = resultMap.get("typeId").toString();
                    String meterNo = resultMap.get("meterNo").toString();
                    List list;
                    int i;
                    if ("1".equals(typeId)) {
                        if (this.baseParamService.getBaseParam(custId).getChargeFlag() == 1) {
                            list = this.yinheService.getWillPostData(" and sendType=9 and manageFlag=0 and deviceId='" + deviceId + "' and meterNo='" + meterNo + "'");
                            if (list.size() > 0) {
                                for(i = 0; i < list.size(); ++i) {
                                    this.yinheService.postCommand(meterNo, deviceId, "9", "", custId, "系统自动【欠费】", ((NbSendData)list.get(i)).getSendId());
                                }
                            }
                        }
                    } else if ("2".equals(typeId)) {
                        list = this.yinheService.getWaterWillPostData(" and manageFlag=0 and deviceId='" + deviceId + "' and meterNo='" + meterNo + "'");
                        if (list.size() > 0) {
                            for(i = 0; i < list.size(); ++i) {
                                this.yinheService.realPostCommand(deviceId, ((NbSendData)list.get(i)).getSendId(), ((NbSendData)list.get(i)).getSendFrame(), String.valueOf(((NbSendData)list.get(i)).getSendType()));
                            }
                        }
                    }
                }
            } catch (JSONException var18) {
                logger.error(var18.getMessage());
                var18.printStackTrace();
            }
        } catch (IOException var19) {
            var19.printStackTrace();
        }

        JSONObject jsonObj = new JSONObject(map);
        logger.info("设备上报数据-平台响应：" + jsonObj);
        return jsonObj;
    }

    @RequestMapping({"/yhiot/postCommand"})
    @ResponseBody
    public Object postCommand(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String createUser) {
        return this.yinheService.postCommand(meterNo, deviceId, commandCode, commandValue, custId, createUser, "");
    }

    @RequestMapping({"/yhiot/registerDevice"})
    @ResponseBody
    public Object registerDevice(String nodeId, int typeId, int tmodel, String imsi) {
        return this.yinheService.registerDevice(nodeId, typeId, tmodel, imsi);
    }

    @RequestMapping({"/yhiot/deleteDevice"})
    @ResponseBody
    public Object deleteDevice(String deviceId) {
        return this.yinheService.deleteDevice(deviceId);
    }

    @RequestMapping({"/yhiot/getYinheMeterData"})
    @ResponseBody
    public List<YinheMeterData> getYinheMeterData(String orders) {
        return this.yinheService.getYinheMeterData(orders);
    }

    @RequestMapping({"/yhiot/modifyDeviceId"})
    @ResponseBody
    public String modifyDeviceId(String meterNo, String uploadDeviceId, String uploadNodeId) {
        return this.yinheService.modifyDeviceId(meterNo, uploadDeviceId, uploadNodeId);
    }

    @RequestMapping({"/yhiot/getPostCommand"})
    @ResponseBody
    public List<NbSendData> getPostCommand(String meterNo, String startDate, String endDate, HttpServletRequest request) {
        String orders = "";
        if (request.getParameter("orders") != null) {
            orders = request.getParameter("orders");
        }

        if (!"".equals(meterNo)) {
            orders = orders + " and meterno='" + meterNo + "'";
        }

        if (!"".equals(startDate)) {
            orders = orders + " and substr(createdate,1,10)>='" + startDate + "'";
        }

        if (!"".equals(endDate)) {
            orders = orders + " and substr(createdate,1,10)<='" + endDate + "'";
        }

        return this.yinheService.getPostCommand(orders);
    }

    @RequestMapping({"/api/yhiot/asyncPostCmd"})
    @ResponseBody
    public String asyncPostCmd(String typeId, String userId, String meterNo, String commandCode, String commandValue, String createUser) {
//        List<NbMeterInfo> list = this.nbMeterService.getNbMeterListByClause(" and meterNo='" + meterNo + "' ");
//        if (list.size() > 0) {
//            this.yinheService.asyncPostCmd(meterNo, ((NbMeterInfo)list.get(0)).getDeviceId(), commandCode, commandValue, ((NbMeterInfo)list.get(0)).getCustId(), createUser, "");
//        }

        return "OK";
    }
}
