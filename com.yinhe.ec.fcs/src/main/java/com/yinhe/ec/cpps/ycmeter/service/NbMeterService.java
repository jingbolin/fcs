//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.service;

import cmcc.iot.onenet.javasdk.api.datapoints.AddDatapointsApi;
import cmcc.iot.onenet.javasdk.api.datapoints.GetDatapointsListApi;
import cmcc.iot.onenet.javasdk.api.datastreams.GetDatastreamApi;
import cmcc.iot.onenet.javasdk.api.device.*;
import cmcc.iot.onenet.javasdk.model.Location;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.response.device.DeciceLatestDataPoint;
import cmcc.iot.onenet.javasdk.response.device.DeviceList;
import cmcc.iot.onenet.javasdk.response.device.DevicesStatusList;
import cmcc.iot.onenet.javasdk.response.device.NewDeviceResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.iot.dao.IotI;
import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.util.*;
import com.yinhe.ec.cpps.ycmeter.dao.NbMeterDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.yinhe.ec.cpps.util.TxtFileReadAndWrite;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NbMeterService {
    @Resource
    private NbMeterDao nbMeterDao;
    @Resource
    private IotI iotI;
    public String TOKEN = "XASTDY2018";
    public String API_KEY = "qSM67lAPjYBwOm8r3bVFoszO7nM=";
    public String PROTOCAL = "HTTP";
    public String DESC = "NBMETER";
    public List<String> tags = Arrays.asList("china", "stedayu");

    public NbMeterService() {
    }

    public List<NbMeterInfo> getNbMeterListByClause(String orders) {
        return this.nbMeterDao.getNbMeterListByClause(orders);
    }

//    @BussAnnotation(
//            moduleName = "NB-IoT管理",
//            option = "新增NB仪表信息"
//    )
    public String addNbMeterInfo(NbMeterInfo nbMeterInfo) {
        try {
            if (this.nbMeterDao.getNbMeterCount(" and meterNo='" + nbMeterInfo.getMeterNo() + "'") > 0) {
                return "新增失败，表号系统已存在";
            } else if (this.nbMeterDao.getNbMeterCount(" and nodeId='" + nbMeterInfo.getNodeId() + "'") > 0) {
                return "新增失败，模组编号系统已存在";
            } else {
                this.nbMeterDao.addNbMeterInfo(nbMeterInfo);
                return "新增成功";
            }
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

    public String modifyNbMeterInfo(NbMeterInfo nbMeterInfo) {
        try {
            this.nbMeterDao.modifyNbMeterInfo(nbMeterInfo);
            return "修改成功";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "NB-IoT管理",
//            option = "删除NB仪表信息"
//    )
    public String deleteNbMeterInfo(String meterNo, String nodeId, int custId, String deviceId) {
        try {
            this.nbMeterDao.deleteNbMeterInfo(meterNo, nodeId, custId, deviceId);
            return "删除成功";
        } catch (DataAccessException var6) {
            var6.printStackTrace();
            return var6.getMessage();
        }
    }

    public void uploadNbMeterInfo(String meterNo, String nodeId, int custId, int createUser, String manuCode, String batchNo, int pwdGroupNo, int tmodel, String imsi, String meterAddr, int typeId) {
        try {
            this.nbMeterDao.uploadNbMeterInfo(meterNo, nodeId, custId, createUser, manuCode, batchNo, pwdGroupNo, tmodel, imsi, meterAddr, typeId);
        } catch (DataAccessException var13) {
            var13.printStackTrace();
        }

    }

//    @BussAnnotation(
//            moduleName = "NB-IoT管理",
//            option = "NB设备注册"
//    )
    public Object registerDevice(String meterNo, String nodeId, int custId, String manuId) {
        String msgStr = "";
        NbResult nb = new NbResult();
        System.out.println("Invoking registerDevice...");

        try {
            msgStr = this.iotI.RegisterDevice(nodeId, manuId);
            System.out.println("registerDevice.result=" + msgStr);
            if (msgStr != null && !"".equals(msgStr)) {
                JSONObject jsonObject = JSONObject.parseObject(msgStr);
                if (jsonObject.containsKey("error_code")) {
                    nb.setResultCode(jsonObject.get("error_code").toString());
                    nb.setResultValue(jsonObject.get("error_desc").toString());
                    if ("100416".equals(jsonObject.get("error_code").toString())) {
                        List<NbMeterInfo> list = this.nbMeterDao.getNbMeterListByClause(" and MeterNo='" + meterNo + "' and nodeId='" + nodeId + "' and deviceid is not null ");
                        if (list.size() > 0) {
                            nb.setResultCode("OK");
                            nb.setResultValue(((NbMeterInfo)list.get(0)).getDeviceId());
                        }
                    }

                    return nb;
                } else {
                    String deviceId = jsonObject.getString("deviceId");
                    System.out.println("deviceId...=" + deviceId);
                    msgStr = this.iotI.ModifyDevice(deviceId, meterNo, manuId);
                    System.out.println("modifyDevice.result=" + msgStr);
                    this.nbMeterDao.registerDevice(meterNo, deviceId, custId, nodeId, manuId);
                    nb.setResultCode("OK");
                    nb.setResultValue(deviceId);
                    return nb;
                }
            } else {
                nb.setResultCode("ERR");
                nb.setResultValue("");
                return nb;
            }
        } catch (Exception var9) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(var9.toString());
            nb.setResultCode("Exception");
            nb.setResultValue("失败");
            return nb;
        }
    }

    public Object modifyDevice(String meterNo, String deviceId, String name, int custId, String manuId) {
        String msgStr = "";
        NbResult nb = new NbResult();
        System.out.println("Invoking modifyDevice...");

        try {
            msgStr = this.iotI.ModifyDevice(deviceId, name, manuId);
            System.out.println("modifyDevice.result=" + msgStr);
            nb.setResultCode("OK");
            nb.setResultValue("成功");
            return nb;
        } catch (Exception var9) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(var9.toString());
            nb.setResultCode("Exception");
            nb.setResultValue("失败");
            return nb;
        }
    }

    public void deleteDeviceForIot(String deviceId) {
        this.nbMeterDao.deleteDevice("", deviceId, 0);
    }

//    @BussAnnotation(
//            moduleName = "NB-IoT管理",
//            option = "NB设备删除"
//    )
    public Object deleteDevice(String meterNo, String deviceId, int custId, String manuId) {
        String msgStr = "";
        NbResult nb = new NbResult();
        System.out.println("Invoking deleteDevice...");

        try {
            msgStr = this.iotI.DeleteDevice(deviceId, manuId);
            System.out.println("deleteDevice.result=" + msgStr);
            this.nbMeterDao.deleteDevice(meterNo, deviceId, custId);
            nb.setResultCode("OK");
            nb.setResultValue("成功");
            return nb;
        } catch (Exception var8) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(var8.toString());
            nb.setResultCode("Exception");
            nb.setResultValue("失败");
            return nb;
        }
    }

    public Object queryDeviceData(String deviceId, String manuId) {
        String msgStr = "";
        NbMeterInfo nbm = new NbMeterInfo();
        System.out.println("Invoking queryDeviceData...");

        try {
            msgStr = this.iotI.QueryDeviceData(deviceId, manuId);
            System.out.println("queryDeviceData.result=" + msgStr);
            JSONObject jsonObject = JSONObject.parseObject(msgStr);
            if (jsonObject.containsKey("error_code")) {
                nbm.setRemark(jsonObject.getString("error_desc"));
                return nbm;
            } else {
                JSONObject jsonObject_device = jsonObject.getJSONObject("deviceInfo");
                String onOffState = jsonObject_device.getString("status");
                System.out.println("onOffState:" + onOffState);
                if ("OFFLINE".equals(onOffState)) {
                    nbm.setOnOffState(onOffState);
                    nbm.setRemark("成功");
                    return nbm;
                } else if ("ABNORMAL".equals(onOffState)) {
                    nbm.setOnOffState(onOffState);
                    nbm.setRemark("成功");
                    return nbm;
                } else {
                    JSONArray jsonArray_services = JSONArray.parseArray(jsonObject.getString("services"));
                    JSONObject servicesObject = null;
                    JSONObject services_data = null;
                    String serviceId = "";
                    String readTime = jsonArray_services.getJSONObject(0).getString("eventTime");
                    readTime = readTime.substring(0, 4) + "-" + readTime.substring(4, 6) + "-" + readTime.substring(6, 8) + " " + readTime.substring(9, 11) + ":" + readTime.substring(11, 13) + ":" + readTime.substring(13, 15);
                    Date d = null;
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    try {
                        d = sd.parse(readTime);
                        long rightTime = d.getTime() + 28800000L;
                        String newtime = sd.format(rightTime);
                        readTime = newtime;
                    } catch (ParseException var24) {
                        var24.printStackTrace();
                    }

                    Double dosageSum = 0.0;
                    Double sigValue = 0.0;
                    Double batVolte = 0.0;
                    Double temperature = 0.0;
                    String errStatus = "";
                    int switchState = 0;
                    String transpond = "";
                    int i;
                    if (!manuId.equals("APXL") && !manuId.equals("APXL_ZSHJ")) {
                        if (!manuId.equals("JX") && !manuId.equals("JX_ZSHJ") && !manuId.equals("JX_ZSHJ_2020") && !manuId.equals("JX_YQKJ")) {
                            if (manuId.equals("APXL_ELE")) {
                                for(i = 0; i < jsonArray_services.size(); ++i) {
                                    servicesObject = jsonArray_services.getJSONObject(i);
                                    serviceId = servicesObject.get("serviceId").toString();
                                    services_data = servicesObject.getJSONObject("data");
                                    if (services_data != null) {
                                        if ("UpLoadMeterParamData".equals(serviceId)) {
                                            sigValue = services_data.getDouble("CurrentCsq");
                                            batVolte = services_data.getDouble("Volt");
                                            temperature = services_data.getDouble("Temperature");
                                            switchState = 0;
                                        } else if ("UpLoadMeterActEnegy".equals(serviceId)) {
                                            dosageSum = services_data.getDouble("PositiveActPower");
                                        }
                                    }
                                }

                                this.nbMeterDao.updateNbMeterInfoData(deviceId, dosageSum, batVolte, temperature, switchState, sigValue, errStatus, readTime);
                                nbm.setDosageSum(dosageSum);
                                nbm.setErrDesc(errStatus);
                                nbm.setSigValue(sigValue);
                                nbm.setBatVolt(batVolte);
                                nbm.setTemperature(temperature);
                                nbm.setSwitchState(switchState);
                                nbm.setOnOffState(onOffState);
                                nbm.setReadTime(readTime);
                                nbm.setRemark("成功");
                            }
                        } else {
                            for(i = 0; i < jsonArray_services.size(); ++i) {
                                servicesObject = jsonArray_services.getJSONObject(i);
                                serviceId = servicesObject.get("serviceId").toString();
                                services_data = servicesObject.getJSONObject("data");
                                if (services_data != null) {
                                    if ("Connectivity".equals(serviceId)) {
                                        sigValue = services_data.getDouble("signalStrength");
                                        sigValue = -113.0 + sigValue * 2.0;
                                        this.updateNbUploadData(deviceId, "sigValue", "" + sigValue, readTime, manuId);
                                    } else if ("Battery".equals(serviceId)) {
                                        batVolte = services_data.getDouble("batteryLevel");
                                        this.updateNbUploadData(deviceId, "batVolte", "" + batVolte, readTime, manuId);
                                    } else if ("DeliverySchedule".equals(serviceId)) {
                                        transpond = services_data.get("transpond").toString();
                                        String str = this.updateNbUploadData(deviceId, "transpond", transpond, readTime, manuId);
                                        if (!"".equals(str)) {
                                            dosageSum = Double.valueOf(str.split("@")[0].toString());
                                            switchState = Integer.parseInt(str.split("@")[1].toString());
                                        }
                                    }
                                }
                            }

                            nbm.setDosageSum(dosageSum);
                            nbm.setErrDesc(errStatus);
                            nbm.setSigValue(sigValue);
                            nbm.setBatVolt(batVolte);
                            nbm.setTemperature(temperature);
                            nbm.setSwitchState(switchState);
                            nbm.setOnOffState(onOffState);
                            nbm.setReadTime(readTime);
                            nbm.setRemark("成功");
                        }
                    } else {
                        for(i = 0; i < jsonArray_services.size(); ++i) {
                            servicesObject = jsonArray_services.getJSONObject(i);
                            serviceId = servicesObject.get("serviceId").toString();
                            services_data = servicesObject.getJSONObject("data");
                            if (services_data != null && "UploadData".equals(serviceId)) {
                                dosageSum = services_data.getDouble("DosageSum");
                                sigValue = services_data.getDouble("SigValue");
                                batVolte = services_data.getDouble("BatVolte");
                                switchState = services_data.getInteger("Switch");
                                this.nbMeterDao.updateNbMeterInfoData(deviceId, dosageSum, batVolte, temperature, switchState, sigValue, errStatus, readTime);
                                nbm.setDosageSum(dosageSum);
                                nbm.setErrDesc(errStatus);
                                nbm.setSigValue(sigValue);
                                nbm.setBatVolt(batVolte);
                                nbm.setTemperature(temperature);
                                nbm.setSwitchState(switchState);
                                nbm.setOnOffState(onOffState);
                                nbm.setReadTime(readTime);
                                nbm.setRemark("成功");
                            }
                        }
                    }

                    return nbm;
                }
            }
        } catch (Exception var25) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(var25.toString());
            return null;
        }
    }

//    @BussAnnotation(
//            moduleName = "NB-IoT管理",
//            option = "NB校对时钟"
//    )
    public Object writeDateTime(String deviceId, String commandCode, String commandValue, String manuId) {
        String msgStr = "";
        NbResult nb = new NbResult();
        System.out.println("Invoking writeDateTime...");

        try {
            msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue, manuId);
            System.out.println("writeDateTime.result=" + msgStr);
            nb.setResultCode("OK");
            nb.setResultValue("成功");
            return nb;
        } catch (Exception var8) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(var8.toString());
            return nb;
        }
    }

    public synchronized Object postAsynCommand(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String manuId, String sysSendId, String createUser) {
        String msgStr = "";
        String commandId = "";
        NbResult nb = new NbResult();
        System.out.println("====manuId==" + manuId + "======sysSendId==" + sysSendId + "=====commandCode==" + commandCode);
        String dataStr;
        String control;
        String d1;
        String tmpMeterAddr;
        String meterAddr;
        String endStr;
        String ipPort;
        String header0;
        String control0;
        String dataStr0;
        int ip3;
        int ip4;
        String d0;
        String d2;
        String header_end;
        String commandValue_end;
        String commandValue0;
        int cs_end;
        byte[] dataData_end;
        String dataStr_end;
        if (!"JX".equals(manuId) && !"JX_ZSHJ".equals(manuId) && !"JX_YQKJ".equals(manuId)) {
            if ("JX_ZSHJ_2020".equals(manuId)) {
                dataStr = "";
                control = "4A";
                d1 = DataConvertTools.numToHex16(Integer.parseInt(meterNo.substring(4, 9)));
                tmpMeterAddr = meterNo.substring(2, 4) + meterNo.substring(0, 2) + d1.substring(4, 6) + d1.substring(2, 4) + d1.substring(0, 2);
                meterAddr = DataConvertTools.inc33h(tmpMeterAddr);
                dataStr = "";
                endStr = "";
                byte cs0 = 0;
                header0 = "683100310068";
                control0 = "0C";
                dataStr0 = "339333333633";
                commandValue0 = "";
                byte[] dataData0 = DataConvertTools.hexString2Bytes(control0 + meterAddr + dataStr0);

                for(ip4 = 0; ip4 < dataData0.length; ++ip4) {
                    cs0 += dataData0[ip4];
                }

                commandValue0 = String.format("%02x", cs0);
                commandValue0 = header0 + control0 + meterAddr + dataStr0 + commandValue0 + endStr;
                commandValue0 = commandValue0.toUpperCase();
                System.out.println("捷先新插件有任务组帧：commandValue0=" + commandValue0);

                try {
                    if (!"FF".equals(commandCode) && !"FE".equals(commandCode)) {
                        this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue0, manuId);
                    }
                } catch (Exception var42) {
                    System.out.println("回应确认帧（有任务） Expected exception: Exception has occurred.");
                    System.out.println(var42.toString());
                }

                byte cs = 0;
                int i;
                if (!"0".equals(commandCode) && !"55".equals(commandCode)) {
                    if (!"1".equals(commandCode) && !"99".equals(commandCode)) {
                        if (!"9".equals(commandCode)) {
                            byte cs_ok;
                            if ("FF".equals(commandCode)) {
                                cs_ok = 0;
                                header_end = "683100310068";
                                header_end = "0C";
                                dataStr_end = "339333333433";
                                commandValue_end = "";
                                dataData_end = DataConvertTools.hexString2Bytes(header_end + meterAddr + dataStr_end);

                                for(i = 0; i < dataData_end.length; ++i) {
                                    cs_ok += dataData_end[i];
                                }

                                commandValue_end = String.format("%02x", cs_ok);
                                commandValue_end = header_end + header_end + meterAddr + dataStr_end + commandValue_end + endStr;
                                commandValue_end = commandValue_end.toUpperCase();
                                System.out.println("捷先新版插件:无任务结束帧=" + commandValue_end);

                                try {
                                    this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue_end, manuId);
                                } catch (Exception var37) {
                                    System.out.println("下发无任务结束帧 Expected exception: Exception has occurred.");
                                    System.out.println(var37.toString());
                                }

                                nb.setResultCode("OK");
                                nb.setResultValue("成功");
                                return nb;
                            }

                            if (!"FE".equals(commandCode)) {
                                nb.setResultCode("Not Support");
                                nb.setResultValue("暂不支持");
                                return nb;
                            }

                            cs_ok = 0;
                            header_end = "684500450068";
                            header_end = "0C";
                            dataStr_end = "339333333733BF33337857";
                            commandValue_end = "";
                            dataData_end = DataConvertTools.hexString2Bytes(header_end + meterAddr + dataStr_end);

                            for(i = 0; i < dataData_end.length; ++i) {
                                cs_ok += dataData_end[i];
                            }

                            commandValue_end = String.format("%02x", cs_ok);
                            commandValue_end = header_end + header_end + meterAddr + dataStr_end + commandValue_end + endStr;
                            commandValue_end = commandValue_end.toUpperCase();
                            System.out.println("捷先新版插件:阀控更新结束=" + commandValue_end);

                            try {
                                this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue_end, manuId);
                            } catch (Exception var38) {
                                System.out.println("阀控更新结束 Expected exception: Exception has occurred.");
                                System.out.println(var38.toString());
                            }

                            nb.setResultCode("OK");
                            nb.setResultValue("成功");
                            return nb;
                        }

                        dataStr = "68A100A10068";
                        d1 = "";
                        d2 = "";
                        header_end = "";
                        header_end = "";
                        dataStr_end = "000000010120";
                        d1 = commandValue.split(",")[0];
                        d2 = commandValue.split(",")[1];
                        header_end = commandValue.split(",")[2];
                        if (Integer.parseInt(d2) > 8) {
                            d2 = "8";
                        }

                        dataStr_end = "001001220720";
                        header_end = "0" + header_end;
                        dataStr = "847001012003" + header_end + dataStr_end + "000000000000000000000000000000000000000000";
                    } else {
                        dataStr = "688500850068";
                        dataStr = "85702000010200000001010000000000AA00000000000000000000";
                    }
                } else {
                    dataStr = "688500850068";
                    dataStr = "857020000102000000010100000000005500000000000000000000";
                }

                System.out.println(dataStr);
                byte[] dataData = DataConvertTools.hexString2Bytes(control + tmpMeterAddr + dataStr);

                for(cs_end = 0; cs_end < dataData.length; ++cs_end) {
                    cs += dataData[cs_end];
                }

                endStr = String.format("%02x", cs);
                commandValue = dataStr + control + tmpMeterAddr + dataStr + endStr + endStr;
                commandValue = commandValue.toUpperCase();
                System.out.println("-->>commandValue:" + commandValue);
                System.out.println("-->>deviceId:" + deviceId);
                System.out.println("-->>commandCode:" + commandCode);
                System.out.println("-->>manuId:" + manuId);

                try {
                    System.out.println("---iotI.PostAsynCommandV4(deviceId, commandCode, commandValue, manuId)>>" + deviceId + "--" + commandCode + "--" + commandValue + "--" + manuId);
                    msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue, manuId);
                    System.out.println("PostAsynCommandV4.result=" + msgStr);
                    JSONObject jsonobj = JSONObject.parseObject(msgStr);
                    if (jsonobj.containsKey("error_code")) {
                        nb.setResultCode(jsonobj.get("error_code").toString());
                        nb.setResultValue(jsonobj.get("error_desc").toString());
                        return nb;
                    }

                    if (jsonobj != null) {
                        commandId = jsonobj.get("commandId").toString();
                    }

                    if ("".equals(commandId)) {
                        commandId = UUIDUtil.getUUID();
                    }

                    this.nbMeterDao.postAsynCommand(meterNo, deviceId, commandCode, commandValue, custId, commandId, sysSendId, createUser);
                    nb.setResultCode("OK");
                    nb.setResultValue("成功");

                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException var41) {
                    }

                    header_end = "687D007D0068";
                    dataStr_end = "85700000100300000000000000000000000000000000000000";
                    commandValue_end = "";
                    dataData_end = DataConvertTools.hexString2Bytes(control + tmpMeterAddr + dataStr_end);

                    for(i = 0; i < dataData_end.length; ++i) {
                        cs_end += dataData_end[i];
                    }

                    commandValue_end = String.format("%02x", cs_end);
                    commandValue_end = header_end + control + tmpMeterAddr + dataStr_end + commandValue_end + endStr;
                    commandValue_end = commandValue_end.toUpperCase();
                    System.out.println("下发有任务结束帧:commandValue_end=" + commandValue_end);

                    try {
                        this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue_end, manuId);
                    } catch (Exception var40) {
                        System.out.println("下发有任务结束帧 Expected exception: Exception has occurred.");
                        System.out.println(var40.toString());
                    }

                    return nb;
                } catch (Exception var48) {
                    System.out.println("Expected exception: Exception has occurred.");
                    System.out.println(var48.toString());
                }
            } else if (!"APXL".equals(manuId) && !"APXL_ZSHJ".equals(manuId)) {
                if (!"STDY".equals(manuId) && !"STDY_ZSHJ".equals(manuId)) {
                    if ("APXL_ELE".equals(manuId)) {
                        try {
                            dataStr = "";
                            if ("0".equals(commandCode)) {
                                dataStr = "55";
                            } else if ("1".equals(commandCode)) {
                                dataStr = "99";
                            } else if ("85".equals(commandCode)) {
                                dataStr = "55";
                            } else {
                                if (!"153".equals(commandCode)) {
                                    nb.setResultCode("Not Support");
                                    nb.setResultValue("暂不支持");
                                    return nb;
                                }

                                dataStr = "99";
                            }

                            msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, dataStr, manuId);
                        } catch (Exception var39) {
                            var39.printStackTrace();
                        }

                        System.out.println("PostAsynCommandV4.result=" + msgStr);
                        JSONObject jsonobj = JSONObject.parseObject(msgStr);
                        if (jsonobj.containsKey("error_code")) {
                            nb.setResultCode(jsonobj.get("error_code").toString());
                            nb.setResultValue(jsonobj.get("error_desc").toString());
                            return nb;
                        }

                        if (jsonobj != null) {
                            commandId = jsonobj.get("commandId").toString();
                        }

                        if ("".equals(commandId)) {
                            commandId = UUIDUtil.getUUID();
                        }

                        this.nbMeterDao.postAsynCommand(meterNo, deviceId, commandCode, commandValue, custId, commandId, sysSendId, createUser);
                        nb.setResultCode("OK");
                        nb.setResultValue("成功");
                        return nb;
                    }

                    if ("STDY_YWKJ".equals(manuId) || "STDY_YWKJ_ZSHJ".equals(manuId)) {
                        dataStr = "";
                        control = "0";
                        if ("0".equals(commandCode)) {
                            commandCode = meterNo;
                            dataStr = "1";
                            control = "0";
                        } else if ("1".equals(commandCode)) {
                            commandCode = meterNo;
                            dataStr = "0";
                            control = "1";
                        } else {
                            if (!"0A".equals(commandCode)) {
                                nb.setResultCode("Not Support");
                                nb.setResultValue("暂不支持");
                                return nb;
                            }

                            commandCode = "10";
                            dataStr = commandValue;
                            control = "10";
                        }

                        try {
                            msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, dataStr, manuId);
                        } catch (Exception var43) {
                            var43.printStackTrace();
                        }

                        System.out.println("PostAsynCommandV4.result=" + msgStr);
                        JSONObject jsonobj = JSONObject.parseObject(msgStr);
                        if (jsonobj.containsKey("error_code")) {
                            nb.setResultCode(jsonobj.get("error_code").toString());
                            nb.setResultValue(jsonobj.get("error_desc").toString());
                            return nb;
                        } else {
                            if (jsonobj != null) {
                                commandId = jsonobj.get("commandId").toString();
                            }

                            if ("".equals(commandId)) {
                                commandId = UUIDUtil.getUUID();
                            }

                            this.nbMeterDao.postAsynCommand(meterNo, deviceId, control, commandValue, custId, commandId, sysSendId, createUser);
                            nb.setResultCode("OK");
                            nb.setResultValue("成功");
                            return nb;
                        }
                    }
                } else {
                    dataStr = "";
                    if ("0".equals(commandCode)) {
                        dataStr = "55";
                    } else if ("1".equals(commandCode)) {
                        dataStr = "99";
                    } else if ("9".equals(commandCode)) {
                        commandCode = "3";
                        control = "";
                        d1 = "";
                        tmpMeterAddr = "";
                        control = commandValue.split(",")[0];
                        d1 = commandValue.split(",")[1];
                        tmpMeterAddr = commandValue.split(",")[2];
                        if (d1.length() < 2) {
                            d1 = "0" + d1;
                        }

                        if (tmpMeterAddr.length() < 2) {
                            tmpMeterAddr = "0" + tmpMeterAddr;
                        }

                        if ("0".equals(control)) {
                            dataStr = "1" + d1 + tmpMeterAddr;
                        } else {
                            dataStr = "0" + d1 + tmpMeterAddr;
                        }

                        commandValue = dataStr;
                    } else if ("10".equals(commandCode)) {
                        commandCode = "4";
                        dataStr = commandValue;
                    } else {
                        if (!"0A".equals(commandCode)) {
                            nb.setResultCode("Not Support");
                            nb.setResultValue("暂不支持");
                            return nb;
                        }

                        commandCode = "10";
                        dataStr = commandValue;
                    }

                    commandValue = commandValue.toUpperCase();

                    try {
                        msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, dataStr, manuId);
                        System.out.println("PostAsynCommandV4.result=" + msgStr);
                        JSONObject jsonobj = JSONObject.parseObject(msgStr);
                        if (jsonobj.containsKey("error_code")) {
                            nb.setResultCode(jsonobj.get("error_code").toString());
                            nb.setResultValue(jsonobj.get("error_desc").toString());
                            return nb;
                        }

                        if (jsonobj != null) {
                            commandId = jsonobj.get("commandId").toString();
                        }

                        if ("".equals(commandId)) {
                            commandId = UUIDUtil.getUUID();
                        }

                        this.nbMeterDao.postAsynCommand(meterNo, deviceId, commandCode, commandValue, custId, commandId, sysSendId, createUser);
                        nb.setResultCode("OK");
                        nb.setResultValue("成功");
                        return nb;
                    } catch (Exception var49) {
                        System.out.println("Expected exception: Exception has occurred.");
                        System.out.println(var49.toString());
                    }
                }
            } else {
                dataStr = "68";
                control = "10";
                d1 = meterNo.substring(12, 14) + meterNo.substring(10, 12) + meterNo.substring(8, 10) + meterNo.substring(6, 8) + meterNo.substring(4, 6) + meterNo.substring(2, 4) + meterNo.substring(0, 2);
                tmpMeterAddr = "";
                meterAddr = "";
                dataStr = "16";
                byte cs = 0;

                try {
                    if ("0".equals(commandCode)) {
                        tmpMeterAddr = "040417A00155";
                    } else if ("1".equals(commandCode)) {
                        tmpMeterAddr = "040417A00199";
                    } else {
                        if ("9".equals(commandCode)) {
                            nb.setResultCode("Not Support");
                            nb.setResultValue("暂不支持");
                            return nb;
                        }

                        if ("10".equals(commandCode)) {
                            String ipAddr = commandValue.split(":")[0];
                            ipPort = commandValue.split(":")[1];
                            int theport = Integer.parseInt(ipPort);
                            int ip1 = Integer.parseInt(ipAddr.split("\\.")[0]);
                            int ip2 = Integer.parseInt(ipAddr.split("\\.")[1]);
                            ip3 = Integer.parseInt(ipAddr.split("\\.")[2]);
                            ip4 = Integer.parseInt(ipAddr.split("\\.")[3]);
                            String thePorthex = String.format("%02x", theport);
                            d0 = "C1";
                            d1 = "01";
                            tmpMeterAddr = "040B01A201" + d0 + d1 + String.format("%02x", ip1) + String.format("%02x", ip2) + String.format("%02x", ip3) + String.format("%02x", ip4) + thePorthex.substring(2, 4) + thePorthex.substring(0, 2);
                        }
                    }

                    byte[] dataData = DataConvertTools.hexString2Bytes(dataStr + control + d1 + tmpMeterAddr);

                    for(int i = 0; i < dataData.length; ++i) {
                        cs += dataData[i];
                    }

                    meterAddr = String.format("%02x", cs);
                    commandValue = dataStr + control + d1 + tmpMeterAddr + meterAddr + dataStr;
                    commandValue = commandValue.toUpperCase();
                    System.out.println(commandValue);
                    msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue, manuId);
                    System.out.println("PostAsynCommandV4.result=" + msgStr);
                    JSONObject jsonobj = JSONObject.parseObject(msgStr);
                    if (jsonobj.containsKey("error_code")) {
                        nb.setResultCode(jsonobj.get("error_code").toString());
                        nb.setResultValue(jsonobj.get("error_desc").toString());
                        return nb;
                    }

                    if (jsonobj != null) {
                        commandId = jsonobj.get("commandId").toString();
                    }

                    if ("".equals(commandId)) {
                        commandId = UUIDUtil.getUUID();
                    }

                    this.nbMeterDao.postAsynCommand(meterNo, deviceId, commandCode, commandValue, custId, commandId, sysSendId, createUser);
                    nb.setResultCode("OK");
                    nb.setResultValue("成功");
                    return nb;
                } catch (Exception var50) {
                    System.out.println("Expected exception: Exception has occurred.");
                    System.out.println(var50.toString());
                }
            }
        } else {
            dataStr = "";
            control = "70";
            d1 = DataConvertTools.numToHex16(Integer.parseInt(meterNo.substring(4, 9)));
            tmpMeterAddr = meterNo.substring(2, 4) + meterNo.substring(0, 2) + d1.substring(4, 6) + d1.substring(2, 4) + d1.substring(0, 2);
            meterAddr = "";
            dataStr = "";
            endStr = "16";
            byte cs0 = 0;
            ipPort = "683100310068";
            header0 = "00";
            control0 = "006000000300";
            dataStr0 = "";
            byte[] dataData0 = DataConvertTools.hexString2Bytes(header0 + tmpMeterAddr + control0);

            for(ip3 = 0; ip3 < dataData0.length; ++ip3) {
                cs0 += dataData0[ip3];
            }

            dataStr0 = String.format("%02x", cs0);
            commandValue0 = ipPort + header0 + tmpMeterAddr + control0 + dataStr0 + endStr;
            commandValue0 = commandValue0.toUpperCase();

            try {
                if (!"FF".equals(commandCode)) {
                    this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue0, manuId);
                }
            } catch (Exception var47) {
                System.out.println("回应确认帧（有任务） Expected exception: Exception has occurred.");
                System.out.println(var47.toString());
            }

            ip4 = 0;
            int i;
            if (!"0".equals(commandCode) && !"55".equals(commandCode)) {
                if (!"1".equals(commandCode) && !"99".equals(commandCode)) {
                    if ("9".equals(commandCode)) {
                        dataStr = "68A100A10068";
                        d0 = "";
                        d1 = "";
                        d2 = "";
                        header_end = "";
                        header_end = "000000100818";
                        d0 = commandValue.split(",")[0];
                        d1 = commandValue.split(",")[1];
                        d2 = commandValue.split(",")[2];
                        if (Integer.parseInt(d1) > 8) {
                            d1 = "8";
                        }

                        header_end = "0000000" + d1 + "0818";
                        if ("0".equals(d0)) {
                            if (Integer.parseInt(d2) > 8) {
                                d2 = "8";
                            }

                            header_end = "4" + d2;
                        }

                        if ("1".equals(d0)) {
                            if (Integer.parseInt(d2) > 8) {
                                d2 = "8";
                            }

                            header_end = "8" + d2;
                        }

                        meterAddr = "847101012003" + header_end + header_end + "000100010036353433323139383736353433323130";
                    } else {
                        if (!"10".equals(commandCode)) {
                            if (!"FF".equals(commandCode)) {
                                nb.setResultCode("Not Support");
                                nb.setResultValue("暂不支持");
                                return nb;
                            }

                            byte cs_ok = 0;
                            d2 = "683100310068";
                            header_end = "00";
                            header_end = "006000000100";
                            dataStr_end = "";
                            byte[] dataData_ok = DataConvertTools.hexString2Bytes(header_end + tmpMeterAddr + header_end);

                            for(i = 0; i < dataData_ok.length; ++i) {
                                cs_ok += dataData_ok[i];
                            }

                            dataStr_end = String.format("%02x", cs_ok);
                            commandValue_end = d2 + header_end + tmpMeterAddr + header_end + dataStr_end + endStr;
                            commandValue_end = commandValue_end.toUpperCase();
                            System.out.println("commandValue_ok:" + commandValue_end);

                            try {
                                this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue_end, manuId);
                            } catch (Exception var44) {
                                System.out.println("下发结束无任务帧 Expected exception: Exception has occurred.");
                                System.out.println(var44.toString());
                            }

                            nb.setResultCode("OK");
                            nb.setResultValue("成功");
                            return nb;
                        }

                        dataStr = "685501550168";
                        d0 = commandValue.split(":")[0];
                        d1 = commandValue.split(":")[1];
                        int theport = Integer.parseInt(d1);
                        int ip1 = Integer.parseInt(d0.split("\\.")[0]);
                        int ip2 = Integer.parseInt(d0.split("\\.")[1]);
                        ip3 = Integer.parseInt(d0.split("\\.")[2]);
                        i = Integer.parseInt(d0.split("\\.")[3]);
                        commandValue_end = String.format("%02x", theport);
                        meterAddr = "847D0000400002" + String.format("%02x", ip1) + String.format("%02x", ip2) + String.format("%02x", ip3) + String.format("%02x", i) + commandValue_end.substring(2, 4) + commandValue_end.substring(0, 2) + "000102030001000102030001000102030001" + "43504E45540000000000000000000000" + "0001020300010001020300010203040500010203040506070809101112131415";
                    }
                } else {
                    dataStr = "688500850068";
                    meterAddr = "85772000010256341201010111111100AA39383736353433323130";
                }
            } else {
                dataStr = "688500850068";
                meterAddr = "857820000102563412010101111111005539383736353433323130";
            }

            System.out.println(meterAddr);
            byte[] dataData = DataConvertTools.hexString2Bytes(control + tmpMeterAddr + meterAddr);

            for(int j = 0; j < dataData.length; ++j) {
                ip4 += dataData[j];
            }

            dataStr = String.format("%02x", Byte.valueOf((byte)ip4));
            commandValue = dataStr + control + tmpMeterAddr + meterAddr + dataStr + endStr;
            commandValue = commandValue.toUpperCase();
            System.out.println("-->>commandValue:" + commandValue);
            System.out.println("-->>deviceId:" + deviceId);
            System.out.println("-->>commandCode:" + commandCode);
            System.out.println("-->>manuId:" + manuId);

            try {
                System.out.println("---port.PostAsynCommandV4(deviceId, commandCode, commandValue, manuId)>>" + deviceId + "--" + commandCode + "--" + commandValue + "--" + manuId);
                msgStr = this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue, manuId);
                System.out.println("PostAsynCommandV4.result=" + msgStr);
                JSONObject jsonobj = JSONObject.parseObject(msgStr);
                if (jsonobj.containsKey("error_code")) {
                    nb.setResultCode(jsonobj.get("error_code").toString());
                    nb.setResultValue(jsonobj.get("error_desc").toString());
                    return nb;
                }

                if (jsonobj != null) {
                    commandId = jsonobj.get("commandId").toString();
                }

                if ("".equals(commandId)) {
                    commandId = UUIDUtil.getUUID();
                }

                this.nbMeterDao.postAsynCommand(meterNo, deviceId, commandCode, commandValue, custId, commandId, sysSendId, createUser);
                nb.setResultCode("OK");
                nb.setResultValue("成功");

                try {
                    Thread.sleep(500L);
                } catch (InterruptedException var46) {
                }

                cs_end = 0;
                header_end = "687D007D0068";
                header_end = "85780000100356341200000000000000000000000000000000";
                dataStr_end = "";
                dataData_end = DataConvertTools.hexString2Bytes(control + tmpMeterAddr + header_end);

                for(i = 0; i < dataData_end.length; ++i) {
                    cs_end += dataData_end[i];
                }

                dataStr_end = String.format("%02x", Byte.valueOf((byte)cs_end));
                commandValue_end = header_end + control + tmpMeterAddr + header_end + dataStr_end + endStr;
                commandValue_end = commandValue_end.toUpperCase();
                System.out.println("commandValue_end:" + commandValue_end);

                try {
                    this.iotI.PostAsynCommandV4(deviceId, commandCode, commandValue_end, manuId);
                } catch (Exception var45) {
                    System.out.println("下发结束任务帧 Expected exception: Exception has occurred.");
                    System.out.println(var45.toString());
                }

                return nb;
            } catch (Exception var51) {
                System.out.println("Expected exception: Exception has occurred.");
                System.out.println(var51.toString());
            }
        }

        nb.setResultCode("Exception");
        nb.setResultValue("失败");
        return nb;
    }

    public Object cmdCancelTask(String deviceId, String manuId) {
        String msgStr = "";
        NbResult nb = new NbResult();
        System.out.println("Invoking CreateDeviceCmdCancelTask...");

        try {
            msgStr = this.iotI.CreateDeviceCmdCancelTask(deviceId, manuId);
            System.out.println("CreateDeviceCmdCancelTask-->>" + msgStr);
            JSONObject taskobj = JSONObject.parseObject(msgStr);
            if (taskobj.containsKey("error_code")) {
                System.out.println("CreateDeviceCmdCancelTask ERROR...");
            } else if (taskobj != null) {
                JSONArray commandIds = taskobj.getJSONArray("deviceCommands");
                JSONObject dataObject = null;

                for(int i = 0; i < commandIds.size(); ++i) {
                    dataObject = commandIds.getJSONObject(i);
                    this.nbMeterDao.updateNbsenddataFlag(deviceId, "10", dataObject.get("commandId").toString());
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        nb.setResultCode("OK");
        nb.setResultValue("撤销成功");
        return nb;
    }

    public List<NbDataDetail> getNbDataDetail(String orders) {
        return this.nbMeterDao.getNbDataDetail(orders);
    }

    public Object onenetRegisterDevice(String meterNo, String nodeId, int custId, String manuId) {
        AddDevicesApi api = new AddDevicesApi(meterNo, this.PROTOCAL, this.DESC, this.tags, (Location)null, true, nodeId, (Map)null, 0, this.API_KEY);
        BasicResponse<NewDeviceResponse> rsp = api.executeApi();
        if (rsp.errno == 0) {
            String deviceId = ((NewDeviceResponse)rsp.getData()).DeviceId;
            this.nbMeterDao.registerDevice(meterNo, deviceId, custId, nodeId, manuId);
            NbResult nb = new NbResult();
            nb.setResultCode("OK");
            nb.setResultValue(deviceId);
            return nb;
        } else {
            NbResult nb = new NbResult();
            nb.setResultCode("ERR");
            nb.setResultValue("失败");
            return nb;
        }
    }

    public Object onenetDeleteDevice(String meterNo, String deviceId, int custId, String manuId) {
        DeleteDeviceApi api = new DeleteDeviceApi(deviceId, this.API_KEY);
        BasicResponse<Void> rsp = api.executeApi();
        NbResult nb;
        if (rsp.errno == 0) {
            this.nbMeterDao.deleteDevice(meterNo, deviceId, custId);
            nb = new NbResult();
            nb.setResultCode("OK");
            nb.setResultValue("成功");
            return nb;
        } else {
            if (rsp.errno == 3) {
                this.nbMeterDao.deleteDevice(meterNo, deviceId, custId);
            }

            nb = new NbResult();
            nb.setResultCode("ERR");
            nb.setResultValue("失败");
            return nb;
        }
    }

    public Object onenetQueryDeviceData(String deviceId, String manuId) {
        GetLatesDeviceData api = new GetLatesDeviceData(deviceId, this.API_KEY);
        BasicResponse<DeciceLatestDataPoint> rsp = api.executeApi();
        if (rsp.errno != 0) {
            return null;
        } else {
            System.out.println(rsp.json);
            NbMeterInfo nbm = new NbMeterInfo();
            String manuCode = "";
            String meterAddr = "";
            String readTime = "";
            Double dosageSum = 0.0;
            Double sigValue = 0.0;
            Double batVolte = 0.0;
            Double temperature = 0.0;
            String errStatus = "";
            int switchState = 0;
            String onOffState = "ONLINE";
            JSONObject jsonobj = JSONObject.parseObject(rsp.json);
            JSONObject dataObject = jsonobj.getJSONObject("data");
            JSONArray devices = dataObject.getJSONArray("devices");
            JSONObject devicesObject = null;

            for(int i = 0; i < devices.size(); ++i) {
                devicesObject = devices.getJSONObject(i);
                JSONArray datastreams = devicesObject.getJSONArray("datastreams");
                if (datastreams != null) {
                    JSONObject datastreamsObject = null;

                    for(int j = 0; j < datastreams.size(); ++j) {
                        datastreamsObject = datastreams.getJSONObject(j);
                        if ("ManuCode".equals(datastreamsObject.get("id"))) {
                            manuCode = datastreamsObject.get("value").toString();
                        }

                        if ("MeterAddr".equals(datastreamsObject.get("id"))) {
                            meterAddr = datastreamsObject.get("value").toString();
                        }

                        if ("DosageSum".equals(datastreamsObject.get("id"))) {
                            dosageSum = Double.valueOf(datastreamsObject.get("value").toString());
                            readTime = datastreamsObject.get("at").toString();
                        }

                        if ("ErrStatus".equals(datastreamsObject.get("id"))) {
                            errStatus = datastreamsObject.get("value").toString();
                        }

                        if ("SigValue".equals(datastreamsObject.get("id"))) {
                            sigValue = Double.valueOf(datastreamsObject.get("value").toString());
                        }

                        if ("BatVolte".equals(datastreamsObject.get("id"))) {
                            batVolte = Double.valueOf(datastreamsObject.get("value").toString());
                        }

                        if ("Temperature".equals(datastreamsObject.get("id"))) {
                            temperature = Double.valueOf(datastreamsObject.get("value").toString());
                        }

                        if ("Switch".equals(datastreamsObject.get("id"))) {
                            switchState = Integer.parseInt(datastreamsObject.get("value").toString());
                        }
                    }
                }
            }

            this.nbMeterDao.updateNbMeterInfoData(deviceId, dosageSum, batVolte, temperature, switchState, sigValue, errStatus, readTime);
            nbm.setDosageSum(dosageSum);
            nbm.setErrDesc(errStatus);
            nbm.setSigValue(sigValue);
            nbm.setBatVolt(batVolte);
            nbm.setTemperature(temperature);
            nbm.setSwitchState(switchState);
            nbm.setOnOffState(onOffState);
            nbm.setReadTime(readTime);
            nbm.setRemark("成功");
            return nbm;
        }
    }

    public Object onenetQueryDeviceState(String deviceId, String manuId) {
        GetDevicesStatus api = new GetDevicesStatus(deviceId, this.API_KEY);
        BasicResponse<DevicesStatusList> rsp = api.executeApi();
        if (rsp.errno == 0) {
            System.out.println(rsp.json);
            JSONObject jsonobj = JSONObject.parseObject(rsp.json);
            JSONObject dataObject = jsonobj.getJSONObject("data");
            JSONArray devices = dataObject.getJSONArray("devices");
            JSONObject devicesObject = null;
            NbResult nb = null;
            String onOffState = "";

            for(int i = 0; i < devices.size(); ++i) {
                devicesObject = devices.getJSONObject(i);
                nb = new NbResult();
                nb.setResultCode("OK");
                if ((Boolean)devicesObject.get("online")) {
                    onOffState = "在线";
                } else {
                    onOffState = "离线";
                }

                nb.setResultValue(onOffState);
            }

            return nb;
        } else {
            return null;
        }
    }

    public Object searchOneNetDataStream(String deviceId, String datastreamId, String manuId) {
        GetDatastreamApi api = new GetDatastreamApi(deviceId, datastreamId, this.API_KEY);
        BasicResponse<DatastreamsResponse> rsp = api.executeApi();
        NbResult nb = null;
        if (rsp.errno == 0) {
            JSONObject jsonobj = JSONObject.parseObject(rsp.json);
            JSONObject dataObject = jsonobj.getJSONObject("data");
            nb = new NbResult();
            nb.setResultCode("OK");
            nb.setResultValue(dataObject.toString());
        }

        return nb;
    }

    public Object searchOneNetDataPoint(String deviceId, String datastreamId, String start, String end, String manuId) {
        GetDatapointsListApi api = new GetDatapointsListApi(datastreamId, start, end, deviceId, (Integer)null, (Integer)null, (String)null, (Integer)null, (String)null, 1, "DESC", this.API_KEY);
        BasicResponse<DatapointsList> rsp = api.executeApi();
        String respStr = "";
        String headStr = "";
        if (rsp.errno == 0) {
            JSONObject jsonobj = JSONObject.parseObject(rsp.json);
            JSONObject dataObject = jsonobj.getJSONObject("data");
            if (Integer.parseInt(dataObject.get("count").toString()) > 0) {
                JSONArray datastreams = dataObject.getJSONArray("datastreams");
                if (datastreams != null) {
                    JSONObject datastreamsObject = null;

                    for(int i = 0; i < datastreams.size(); ++i) {
                        datastreamsObject = datastreams.getJSONObject(i);
                        headStr = datastreamsObject.get("id").toString();
                        if ("DosageSum".equals(headStr)) {
                            headStr = "【累计量】";
                        }

                        if ("SigValue".equals(headStr)) {
                            headStr = "【信号值】";
                        }

                        if ("BatVolte".equals(headStr)) {
                            headStr = "【电池电压】";
                        }

                        if ("Switch".equals(headStr)) {
                            headStr = "【阀门状态】";
                        }

                        JSONArray datapoints = datastreamsObject.getJSONArray("datapoints");
                        if (datapoints != null) {
                            JSONObject datapointsObject = null;

                            for(int j = 0; j < datapoints.size(); ++j) {
                                datapointsObject = datapoints.getJSONObject(j);
                                respStr = respStr + headStr + "时间：" + datapointsObject.get("at") + "  &nbsp;&nbsp;数据：" + datapointsObject.get("value") + "<br>";
                            }
                        } else {
                            respStr = respStr + headStr + "暂无数据<br>";
                        }
                    }
                } else {
                    respStr = "暂无数据";
                }
            } else {
                respStr = "暂无数据";
            }
        }

        NbResult nb = new NbResult();
        nb.setResultCode("OK");
        nb.setResultValue(respStr);
        return nb;
    }

    public Object sendOneNetCmd(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String manuId) {
        String contents = "";
        String cmdArgs = "";
        if ("1".equals(commandCode)) {
            contents = FrameTools.encodeFrame(meterNo, "A017", "55");
        } else if ("2".equals(commandCode)) {
            contents = FrameTools.encodeFrame(meterNo, "A017", "99");
        } else if ("3".equals(commandCode)) {
            contents = "";
        } else if ("4".equals(commandCode)) {
            contents = "";
        } else if ("9".equals(commandCode)) {
            contents = FrameTools.encodeFrame(meterNo, "A01A", commandValue);
        }

        NbResult nb = new NbResult();
        if ("".equals(contents)) {
            nb.setResultCode("ERR");
            nb.setResultValue("ERROR");
            return nb;
        } else {
            cmdArgs = "{\"Command\":\"" + contents + "\"}";
            System.out.println(cmdArgs);
            AddDatapointsApi api = new AddDatapointsApi((Map)null, cmdArgs, 3, deviceId, this.API_KEY);
            BasicResponse<Void> rsp = api.executeApi();
            System.out.println(rsp.json);
            this.nbMeterDao.postAsynCommand(meterNo, deviceId, commandCode, commandValue, custId, UUIDUtil.getUUID(), "", "");
            nb.setResultCode("OK");
            nb.setResultValue("成功");
            return nb;
        }
    }

    public Object oneNetValidate(String msg, String nonce, String signature) {
        String enStr = MD5.getDigestedString(this.TOKEN + nonce + msg);
        String enStrBASE64 = Base64Object.stringToBase64(enStr);
        String enStrURLDecode = "";

        try {
            enStrURLDecode = URLDecoder.decode(enStrBASE64, "utf-8");
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        }

        System.out.println(enStrURLDecode + "==" + signature);
        return null;
    }

    public void oneNetPushData(String msg) {
        TxtFileReadAndWrite.writeToFile(msg);
    }

    public List<OneNetNbDataDetail> getOneNetNbDataDetail(String orders) {
        String path = "";
        path = Config.getString("test.txtPath") + "/onenet_logs.txt";
        File file = new File(path);
        List<String> list = new ArrayList();
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String text = "";

                while((text = bufferedReader.readLine()) != null) {
                    list.add(text);
                }

                try {
                    FileOutputStream out = new FileOutputStream(path, false);
                    out.write((new String("")).getBytes());
                    out.close();
                } catch (Exception var10) {
                }
            } catch (Exception var11) {
            }
        }

        this.nbMeterDao.insertNbDataToDatabase(list);
        return this.nbMeterDao.getOneNetNbDataDetail(orders);
    }

    public void insertNbData(String deviceId, String meterNo, String dosageSum, String sigValue, String batVolte, String switchFlag, String readDt, String errDesc) {
        this.nbMeterDao.insertNbData(deviceId, meterNo, dosageSum, sigValue, batVolte, switchFlag, readDt, errDesc);
    }

    public List<NbSendData> getOneNetNbSendDataInfo(String orders) {
        return this.nbMeterDao.getOneNetNbSendDataInfo(orders);
    }

    public Object checkOnenetRegisterDevice(int custId) {
        String dev_id = "";
        String meterNo = "";
        String nodeId = "";
        String imsi = "";
        String create_time = "";
        FindDevicesListApi api = new FindDevicesListApi((String)null, (Object)null, (String)null, (Date)null, (Date)null, (String)null, (Boolean)null, (Integer)null, (Integer)null, (Boolean)null, this.API_KEY);
        BasicResponse<DeviceList> rsp = api.executeApi();
        if (rsp.errno == 0) {
            System.out.println(rsp.json);
            JSONObject jsonobj = JSONObject.parseObject(rsp.json);
            JSONObject dataObject = jsonobj.getJSONObject("data");
            JSONArray devices = dataObject.getJSONArray("devices");
            JSONObject devicesObject = null;

            for(int i = 0; i < devices.size(); ++i) {
                devicesObject = devices.getJSONObject(i);
                dev_id = devicesObject.get("id").toString();
                meterNo = devicesObject.get("title").toString();
                if (devicesObject.get("auth_info").toString().indexOf(":") != -1) {
                    nodeId = devicesObject.get("auth_info").toString().split(":")[0];
                    imsi = devicesObject.get("auth_info").toString().split(":")[1];
                } else {
                    nodeId = devicesObject.get("auth_info").toString();
                }

                create_time = devicesObject.get("create_time").toString();
                this.nbMeterDao.checkOnenetRegisterDevice(dev_id, meterNo, nodeId, create_time, custId, imsi);
            }
        }
        return null;
    }

    public int getWillPostIotData(String orders) {
        List<NbSendData> list = this.nbMeterDao.getWillPostData(orders);
        System.out.println("AUTO Invoking PostAsynCommandV4...");
        System.out.println("list.size():" + list.size());
        if (list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                this.postAsynCommand(((NbSendData)list.get(i)).getMeterNo(), ((NbSendData)list.get(i)).getDeviceId(), ((NbSendData)list.get(i)).getSendDetail(), ((NbSendData)list.get(i)).getSendDetail(), ((NbSendData)list.get(i)).getCustId(), ((NbSendData)list.get(i)).getManuCode(), ((NbSendData)list.get(i)).getSendId(), ((NbSendData)list.get(i)).getRemark());
            }
        }

        return list.size();
    }

    public void updateNbsenddataFlag(String deviceId, String sucFlag, String commandId) {
        this.nbMeterDao.updateNbsenddataFlag(deviceId, sucFlag, commandId);
    }

    public void updateNbMeterSwitchState(String deviceId, String switchState) {
        this.nbMeterDao.updateNbMeterSwitchState(deviceId, switchState);
    }

    public void updateNbMeterInfoData(String deviceId, Double dosageSum, Double batVolte, Double temperature, int switchState, Double sigValue, String errStatus, String readTime) {
        this.nbMeterDao.updateNbMeterInfoData(deviceId, dosageSum, batVolte, temperature, switchState, sigValue, errStatus, readTime);
    }

    public String updateNbUploadData(String deviceId, String key, String value, String readTime, String manuId) {
        System.out.println("deviceId:" + deviceId + " key:" + key + " value:" + value + " manuId:" + manuId);

        try {
            String dosageSum;
            String switchState;
            if ("transpond".equals(key) && "JX_ZSHJ".equals(manuId)) {
                dosageSum = "";
                switchState = "";
                BigDecimal d;
                BigDecimal d2;
                if ("30000107".equals(value.substring(28, 36))) {
                    dosageSum = value.substring(44, 52);
                    dosageSum = dosageSum.substring(6, 8) + dosageSum.substring(4, 6) + dosageSum.substring(2, 4) + dosageSum.substring(0, 2);
                    switchState = value.substring(52, 54);
                    System.out.println("-->>手动唤醒上传transpond---dosageSum:" + dosageSum + "  switchState:" + switchState);
                    if (!"".equals(dosageSum)) {
                        d = new BigDecimal(dosageSum);
                        d2 = new BigDecimal("100");
                        dosageSum = d.divide(d2, 2, 4).toString();
                        this.nbMeterDao.updateNbUploadData(deviceId, "dosageSum", dosageSum, readTime);
                    }

                    if ("00".equals(switchState)) {
                        switchState = "0";
                    } else if ("01".equals(switchState)) {
                        switchState = "1";
                    } else if ("10".equals(switchState)) {
                        switchState = "2";
                    } else if ("11".equals(switchState)) {
                        switchState = "3";
                    } else {
                        switchState = "3";
                    }

                    this.nbMeterDao.updateNbUploadData(deviceId, "switchState", switchState, readTime);
                } else {
                    if (!"00110107".equals(value.substring(28, 36).toUpperCase())) {
                        return "";
                    }

                    dosageSum = value.substring(40, 48);
                    dosageSum = dosageSum.substring(6, 8) + dosageSum.substring(4, 6) + dosageSum.substring(2, 4) + dosageSum.substring(0, 2);
                    switchState = value.substring(value.length() - 22, value.length() - 20);
                    System.out.println("-->>自动触发上传transpond---dosageSum:" + dosageSum + "  switchState:" + switchState);
                    if (!"".equals(dosageSum)) {
                        d = new BigDecimal(dosageSum);
                        d2 = new BigDecimal("100");
                        dosageSum = d.divide(d2, 2, 4).toString();
                        this.nbMeterDao.updateNbUploadData(deviceId, "dosageSum", dosageSum, readTime);
                    }

                    if ("00".equals(switchState)) {
                        switchState = "0";
                    } else if ("01".equals(switchState)) {
                        switchState = "1";
                    } else if ("10".equals(switchState)) {
                        switchState = "2";
                    } else if ("11".equals(switchState)) {
                        switchState = "3";
                    } else {
                        switchState = "3";
                    }

                    this.nbMeterDao.updateNbUploadData(deviceId, "switchState", switchState, readTime);
                }

                System.out.println("------>>>" + dosageSum + "@" + switchState);
                return dosageSum + "@" + switchState;
            } else if ("transpond".equals(key) && "JX_ZSHJ_2020".equals(manuId)) {
                dosageSum = "";
                switchState = "";
                String c = value.substring(12, 14);
                String item = value.substring(28, 36).toLowerCase();
                BigDecimal d;
                BigDecimal d2;
                if ("8c".equals(c)) {
                    if ("6333343a".equals(item)) {
                        dosageSum = DataConvertTools.dec33h(value.substring(44, 52));
                        dosageSum = dosageSum.substring(6, 8) + dosageSum.substring(4, 6) + dosageSum.substring(2, 4) + dosageSum.substring(0, 2);
                        switchState = DataConvertTools.dec33h(value.substring(value.length() - 6, value.length() - 4));
                        System.out.println("-->>新版唤醒上报transpond---dosageSum:" + dosageSum + "  switchState:" + switchState);
                        if (!"".equals(dosageSum)) {
                            d = new BigDecimal(dosageSum);
                            d2 = new BigDecimal("100");
                            dosageSum = d.divide(d2, 2, 4).toString();
                            this.nbMeterDao.updateNbUploadData(deviceId, "dosageSum", dosageSum, readTime);
                        }

                        if ("00".equals(switchState)) {
                            switchState = "0";
                        } else if ("01".equals(switchState)) {
                            switchState = "1";
                        } else if ("10".equals(switchState)) {
                            switchState = "2";
                        } else if ("11".equals(switchState)) {
                            switchState = "3";
                        } else {
                            switchState = "3";
                        }

                        this.nbMeterDao.updateNbUploadData(deviceId, "switchState", switchState, readTime);
                    } else {
                        if (!"3344343b".equals(item) && !"3344343a".equals(item)) {
                            return "";
                        }

                        dosageSum = DataConvertTools.dec33h(value.substring(40, 48));
                        dosageSum = dosageSum.substring(6, 8) + dosageSum.substring(4, 6) + dosageSum.substring(2, 4) + dosageSum.substring(0, 2);
                        switchState = DataConvertTools.dec33h(value.substring(value.length() - 28, value.length() - 26));
                        System.out.println("-->>新版定时上报transpond---dosageSum:" + dosageSum + "  switchState:" + switchState);
                        if (!"".equals(dosageSum)) {
                            d = new BigDecimal(dosageSum);
                            d2 = new BigDecimal("100");
                            dosageSum = d.divide(d2, 2, 4).toString();
                            this.nbMeterDao.updateNbUploadData(deviceId, "dosageSum", dosageSum, readTime);
                        }

                        if ("00".equals(switchState)) {
                            switchState = "0";
                        } else if ("01".equals(switchState)) {
                            switchState = "1";
                        } else if ("10".equals(switchState)) {
                            switchState = "2";
                        } else if ("11".equals(switchState)) {
                            switchState = "3";
                        } else {
                            switchState = "3";
                        }

                        this.nbMeterDao.updateNbUploadData(deviceId, "switchState", switchState, readTime);
                    }
                } else if ("30000107".equals(item)) {
                    dosageSum = value.substring(44, 52);
                    dosageSum = dosageSum.substring(6, 8) + dosageSum.substring(4, 6) + dosageSum.substring(2, 4) + dosageSum.substring(0, 2);
                    switchState = value.substring(52, 54);
                    System.out.println("-->>新版不加密唤醒上报transpond---dosageSum:" + dosageSum + "  switchState:" + switchState);
                    if (!"".equals(dosageSum)) {
                        d = new BigDecimal(dosageSum);
                        d2 = new BigDecimal("100");
                        dosageSum = d.divide(d2, 2, 4).toString();
                        this.nbMeterDao.updateNbUploadData(deviceId, "dosageSum", dosageSum, readTime);
                    }

                    if ("00".equals(switchState)) {
                        switchState = "0";
                    } else if ("01".equals(switchState)) {
                        switchState = "1";
                    } else if ("10".equals(switchState)) {
                        switchState = "2";
                    } else if ("11".equals(switchState)) {
                        switchState = "3";
                    } else {
                        switchState = "3";
                    }

                    this.nbMeterDao.updateNbUploadData(deviceId, "switchState", switchState, readTime);
                } else {
                    if (!"00110108".equals(item)) {
                        return "";
                    }

                    dosageSum = value.substring(40, 48);
                    dosageSum = dosageSum.substring(6, 8) + dosageSum.substring(4, 6) + dosageSum.substring(2, 4) + dosageSum.substring(0, 2);
                    switchState = value.substring(value.length() - 22, value.length() - 20);
                    System.out.println("-->>新版不加密定时上报transpond---dosageSum:" + dosageSum + "  switchState:" + switchState);
                    if (!"".equals(dosageSum)) {
                        d = new BigDecimal(dosageSum);
                        d2 = new BigDecimal("100");
                        dosageSum = d.divide(d2, 2, 4).toString();
                        this.nbMeterDao.updateNbUploadData(deviceId, "dosageSum", dosageSum, readTime);
                    }

                    if ("00".equals(switchState)) {
                        switchState = "0";
                    } else if ("01".equals(switchState)) {
                        switchState = "1";
                    } else if ("10".equals(switchState)) {
                        switchState = "2";
                    } else if ("11".equals(switchState)) {
                        switchState = "3";
                    } else {
                        switchState = "3";
                    }

                    this.nbMeterDao.updateNbUploadData(deviceId, "switchState", switchState, readTime);
                }

                System.out.println("------>>>" + dosageSum + "@" + switchState);
                return dosageSum + "@" + switchState;
            } else {
                this.nbMeterDao.updateNbUploadData(deviceId, key, value, readTime);
                return "";
            }
        } catch (Exception var12) {
            return "异常@异常";
        }
    }

    public void subscribeNotification(String manuId) {
        try {
            this.iotI.SubscribeNotification(manuId);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void delSubscribeNotification(String manuId) {
        try {
            this.iotI.DelSubscribeNotification(manuId);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void insertEleNbData(String deviceId, String meterNo, String dosageSum, String sigValue, String batVolte, String switchFlag, String readDt, String errDesc, String actPower, String reactPower, String powerFac, String volt, String lcurrent, String zlCurrent, String appPower, String gridsFreq, String temperature, String positiveActPower, String unPositiveActPower, String dataType) {
        this.nbMeterDao.insertEleNbData(deviceId, meterNo, dosageSum, sigValue, batVolte, switchFlag, readDt, errDesc, actPower, reactPower, powerFac, volt, lcurrent, zlCurrent, appPower, gridsFreq, temperature, positiveActPower, unPositiveActPower, dataType);
    }

    public void updateNbMeterIMSI(String deviceId, String imsi) {
        this.nbMeterDao.updateNbMeterIMSI(deviceId, imsi);
    }

    public String delFactoryTestMeter() {
        try {
            this.nbMeterDao.delFactoryTestMeter();
            return "删除成功";
        } catch (DataAccessException var2) {
            var2.printStackTrace();
            return var2.getMessage();
        }
    }

    public NbResult postCommandIntoDb(String meterNo, String deviceId, String commandCode, String commandValue, int custId) {
        this.nbMeterDao.postCommandIntoDb(meterNo, deviceId, commandCode, commandValue, custId);
        NbResult nb = new NbResult();
        nb.setResultCode("OK");
        nb.setResultValue("成功");
        return nb;
    }
}
