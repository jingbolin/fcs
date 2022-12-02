//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.controller;

import com.yinhe.ec.cpps.model.NbDataDetail;
import com.yinhe.ec.cpps.model.NbMeterInfo;
import com.yinhe.ec.cpps.model.NbSendData;
import com.yinhe.ec.cpps.model.OneNetNbDataDetail;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.service.NbMeterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api("设备API")
@RestController
@RequestMapping("/yinhe")
public class NbMeterController {
    @Resource
    private NbMeterService nbMeterService;

    public NbMeterController() {
    }

    @ApiOperation(value = "查询设备", notes = "查询设备", response = ApiResult.class)
    @PostMapping({"/ycmeter/getNbMeterListByClause"})
    @ResponseBody
    public List<NbMeterInfo> getNbMeterListByClause(String orders) {
        return this.nbMeterService.getNbMeterListByClause(orders);

    }

    @ApiOperation(value = "新增设备", notes = "新增设备", response = ApiResult.class)
    @PostMapping({"/ycmeter/addNbMeterInfo"})
    @ResponseBody
    public String addNbMeterInfo(@RequestBody NbMeterInfo nbMeterInfo) {
        return this.nbMeterService.addNbMeterInfo(nbMeterInfo);
    }

    @ApiOperation(value = "更新设备", notes = "更新设备", response = ApiResult.class)
    @PostMapping({"/ycmeter/modifyNbMeterInfo"})
    @ResponseBody
    public String modifyNbMeterInfo(@RequestBody NbMeterInfo nbMeterInfo) {
        return this.nbMeterService.modifyNbMeterInfo(nbMeterInfo);
    }

    @ApiOperation(value = "删除设备", notes = "删除设备", response = ApiResult.class)
    @PostMapping({"/ycmeter/deleteNbMeterInfo"})
    @ResponseBody
    public String deleteNbMeterInfo(String meterNo, String nodeId, int custId, String deviceId) {
        return this.nbMeterService.deleteNbMeterInfo(meterNo, nodeId, custId, deviceId);
    }

    @PostMapping({"/ycmeter/uploadNbMeterInfo"})
    @ResponseBody
    public void uploadNbMeterInfo(String meterNo, String nodeId, int custId, int createUser, String manuCode, String batchNo, int pwdGroupNo, int tmodel, String imsi, String meterAddr, int typeId) {
        this.nbMeterService.uploadNbMeterInfo(meterNo, nodeId, custId, createUser, manuCode, batchNo, pwdGroupNo, tmodel, imsi, meterAddr, typeId);
    }

    @PostMapping({"/ycmeter/registerDevice"})
    @ResponseBody
    public Object registerDevice(String meterNo, String nodeId, int custId, String manuId) {
        return this.nbMeterService.registerDevice(meterNo, nodeId, custId, manuId);
    }

    @PostMapping({"/ycmeter/modifyDevice"})
    @ResponseBody
    public Object modifyDevice(String meterNo, String deviceId, String name, int custId, String manuId) {
        return this.nbMeterService.modifyDevice(meterNo, deviceId, name, custId, manuId);
    }

    @PostMapping({"/ycmeter/deleteDevice"})
    @ResponseBody
    public Object deleteDevice(String meterNo, String deviceId, int custId, String manuId) {
        return this.nbMeterService.deleteDevice(meterNo, deviceId, custId, manuId);
    }

    @PostMapping({"/ycmeter/queryDeviceData"})
    @ResponseBody
    public Object queryDeviceData(String deviceId, String manuId) {
        return this.nbMeterService.queryDeviceData(deviceId, manuId);
    }

    @PostMapping({"/ycmeter/postAsynCommand"})
    @ResponseBody
    public Object postAsynCommand(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String manuId, String createUser) {
        return this.nbMeterService.postCommandIntoDb(meterNo, deviceId, commandCode, commandValue, custId);
    }

    @PostMapping({"/ycmeter/cmdCancelTask"})
    @ResponseBody
    public Object cmdCancelTask(String deviceId, String manuId) {
        return this.nbMeterService.cmdCancelTask(deviceId, manuId);
    }

    @PostMapping({"/ycmeter/getWillPostIotData"})
    @ResponseBody
    public void getWillPostIotData(String orders, HttpServletRequest request, HttpServletResponse response) throws IOException {
        orders = " and tmodel=0 ";
        this.nbMeterService.getWillPostIotData(orders);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("OK");
    }

    @PostMapping({"/ycmeter/getNbDataDetail"})
    @ResponseBody
    public List<NbDataDetail> getNbDataDetail(String orders) {
        return this.nbMeterService.getNbDataDetail(orders);
    }

    @PostMapping({"/ycmeter/subscribeNotification"})
    @ResponseBody
    public void subscribeNotification(String manuId) {
        this.nbMeterService.subscribeNotification(manuId);
    }

    @PostMapping({"/ycmeter/delSubscribeNotification"})
    @ResponseBody
    public void delSubscribeNotification(String manuId) {
        this.nbMeterService.delSubscribeNotification(manuId);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/reportCmdExecResult"},
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> reportCmdExecResult(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        String commandId;
        try {
            BufferedReader br = request.getReader();

            for(commandId = ""; (commandId = br.readLine()) != null; contentStr = contentStr + commandId) {
            }

            System.out.println(Tools.getNow() + "--命令回复帧（cmdExecResult）-接收到源字符串：" + contentStr.trim());
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String deviceId = "";
        commandId = "";
        String sucFlag = "";
        String switchState = "0";
        JSONObject jsonobj = JSONObject.fromObject(contentStr);
        if (jsonobj != null) {
            deviceId = jsonobj.get("deviceId").toString();
            commandId = jsonobj.get("commandId").toString();
            JSONObject dataObject = jsonobj.getJSONObject("result");
            System.out.println("commandId:" + commandId);
            if (dataObject != null) {
                sucFlag = dataObject.get("resultCode").toString();
                if ("SENT".equals(sucFlag.toUpperCase())) {
                    sucFlag = "2";
                } else if ("DELIVERED".equals(sucFlag.toUpperCase())) {
                    sucFlag = "3";
                } else if ("SUCCESSFUL".equals(sucFlag.toUpperCase())) {
                    sucFlag = "1";
                    JSONObject resultDetail = dataObject.getJSONObject("resultDetail");
                    switchState = resultDetail.get("Data").toString();
                    System.out.println("SUCCESSFUL111:" + switchState);
                    if (switchState != null && switchState.length() == 36 && "17A0".equals(switchState.substring(22, 26))) {
                        switchState = switchState.substring(28, 30);
                        String binaryStr = Tools.hexToBinary(switchState);
                        System.out.println("binaryStr:" + binaryStr);
                        switchState = binaryStr.substring(6);
                        System.out.println("SUCCESSFUL222:" + switchState);
                        if ("00".equals(switchState)) {
                            switchState = "0";
                        } else if ("01".equals(switchState)) {
                            switchState = "1";
                        } else if ("10".equals(switchState)) {
                            switchState = "2";
                        } else if ("11".equals(switchState)) {
                            switchState = "3";
                        }

                        System.out.println("SUCCESSFUL333（暂不更新状态）:" + switchState);
                    }
                } else {
                    sucFlag = "9";
                }

                this.nbMeterService.updateNbsenddataFlag(deviceId, sucFlag, commandId);
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/addDevice"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> subscribeAddDevice(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        System.out.println(Tools.getNow() + "--平台新增设备（deviceAdded）-接收到源字符串：" + contentStr.trim());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/deletedDevice"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> subscribeDeletedDevice(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        String deviceId;
        try {
            BufferedReader br = request.getReader();

            for(deviceId = ""; (deviceId = br.readLine()) != null; contentStr = contentStr + deviceId) {
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        System.out.println(Tools.getNow() + "--平台删除设备（deviceDeleted）-接收到源字符串：" + contentStr.trim());
        JSONObject jsonobj = JSONObject.fromObject(contentStr);
        if (jsonobj != null) {
            deviceId = jsonobj.get("deviceId").toString();
            String notifyType = jsonobj.get("notifyType").toString();
            if ("deviceDeleted".equals(notifyType)) {
                this.nbMeterService.deleteDeviceForIot(deviceId);
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/updateDeviceInfo"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> subscribeUpdateDeviceInfo(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        System.out.println(Tools.getNow() + "--设备变化（deviceInfoChanged）-接收到源字符串：" + contentStr.trim());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/commandRspData"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> subscribeCommandRspData(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        System.out.println(Tools.getNow() + "--响应命令（commandRsp）-接收到源字符串：" + contentStr.trim());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/updateDeviceDatas"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> updateDeviceDatas(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("-------updateDeviceDatas:" + Tools.getNow());
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            System.out.println("设备数据变化（deviceDatasChanged）-接收到源字符串：" + contentStr.trim());
            String notifyType = "";
            String deviceId = "";
            String meterNo = "";
            String dosageSum = "0.0";
            String sigValue = "0.0";
            String batVolte = "0.0";
            String switchFlag = "0";
            String readDt = "";
            String manuCode = "";
            String errStatus = "";
            String errStatusStr = "";
            String errDesc = "";
            String serviceId = "";
            String transpond = "";
            String imsi = "";
            String actPower = "";
            String reactPower = "";
            String powerFac = "";
            String volt = "";
            String current = "";
            String zlCurrent = "";
            String appPower = "";
            String gridsFreq = "";
            String temperature = "";
            String currentCsq = "";
            String positiveActPower = "";
            String unPositiveActPower = "";
            String theCommManuCode = "";
            int theCustId = 0;
            String theMeterNo = "";
            String theImsi = "";
            String meterDate = "";
            JSONObject jsonobj = JSONObject.fromObject(contentStr);
            if (jsonobj != null) {
                notifyType = jsonobj.get("notifyType").toString();
                deviceId = jsonobj.get("deviceId").toString();
                List<NbMeterInfo> list = this.nbMeterService.getNbMeterListByClause(" and deviceId='" + deviceId + "'");
                if (list.size() <= 0) {
                    return new ResponseEntity(HttpStatus.OK);
                }

                theCommManuCode = ((NbMeterInfo)list.get(0)).getManuCode().toUpperCase();
                theCustId = ((NbMeterInfo)list.get(0)).getCustId();
                theMeterNo = ((NbMeterInfo)list.get(0)).getMeterNo();
                theImsi = ((NbMeterInfo)list.get(0)).getImsi();
                if ("deviceDatasChanged".equals(notifyType)) {
                    JSONArray services = jsonobj.getJSONArray("services");
                    JSONObject servicesObject = null;
                    JSONObject dataObject = null;

                    for(int i = 0; i < services.size(); ++i) {
                        servicesObject = services.getJSONObject(i);
                        serviceId = servicesObject.get("serviceId").toString();
                        dataObject = servicesObject.getJSONObject("data");
                        readDt = servicesObject.get("eventTime").toString();
                        readDt = readDt.substring(0, 4) + "-" + readDt.substring(4, 6) + "-" + readDt.substring(6, 8) + " " + readDt.substring(9, 11) + ":" + readDt.substring(11, 13) + ":" + readDt.substring(13, 15);
                        Date d = null;
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String meterId;
                        try {
                            d = sd.parse(readDt);
                            long rightTime = d.getTime() + 28800000L;
                            meterId = sd.format(rightTime);
                            readDt = meterId;
                        } catch (ParseException var50) {
                            var50.printStackTrace();
                        }

                        if (dataObject != null) {
                            String item;
                            if (!"UploadData".equals(serviceId)) {
                                if ("Connectivity".equals(serviceId)) {
                                    sigValue = dataObject.get("signalStrength").toString();
                                    sigValue = String.valueOf(-113.0 + Double.valueOf(sigValue) * 2.0);
                                    this.nbMeterService.updateNbUploadData(deviceId, "sigValue", sigValue, readDt, theCommManuCode);
                                } else if ("Battery".equals(serviceId)) {
                                    batVolte = dataObject.get("batteryLevel").toString();
                                    this.nbMeterService.updateNbUploadData(deviceId, "batVolte", batVolte, readDt, theCommManuCode);
                                } else if ("Imsi".equals(serviceId)) {
                                    imsi = dataObject.get("imsi").toString();
                                    this.nbMeterService.updateNbMeterIMSI(deviceId, imsi);
                                } else if ("Iccid".equals(serviceId)) {
                                    imsi = dataObject.get("Iccid").toString();
                                    this.nbMeterService.updateNbMeterIMSI(deviceId, imsi);
                                } else if ("DeliverySchedule".equals(serviceId)) {
                                    transpond = dataObject.get("transpond").toString();
                                    transpond = transpond.toLowerCase();
                                    item = "";
                                    if (transpond.length() > 36) {
                                        item = transpond.substring(28, 36);
                                    }

                                    if ("33337857".equals(item)) {
                                        this.nbMeterService.postAsynCommand(theMeterNo, deviceId, "FE", "", theCustId, theCommManuCode, "", "Upload");
                                    } else {
                                        int n = this.nbMeterService.getWillPostIotData(" and deviceId='" + deviceId + "' ");
                                        if (n == 0) {
                                            this.nbMeterService.postAsynCommand(theMeterNo, deviceId, "FF", "", theCustId, theCommManuCode, "", "Upload");
                                        }
                                    }

                                    this.nbMeterService.updateNbUploadData(deviceId, "transpond", transpond, readDt, theCommManuCode);
                                } else if (!"DayLock".equals(serviceId) && !"MonthLock".equals(serviceId)) {
                                    if ("UploadInfo".equals(serviceId)) {
                                        int cmdCode = Integer.parseInt(dataObject.get("CmdCode").toString());
                                        String cardInfo = dataObject.get("DataInfo").toString();
                                        meterId = "";
                                        String simId = "";
                                        System.out.println(cardInfo);
                                        if (cmdCode == 0 && cardInfo.split("\\|").length == 3) {
                                            meterId = cardInfo.split("\\|")[0];
                                            simId = cardInfo.split("\\|")[2];
                                            if (meterId.equals(theMeterNo)) {
                                                this.nbMeterService.updateNbMeterIMSI(deviceId, simId);
                                            }
                                        }
                                    } else if ("UpLoadMeterParamData".equals(serviceId)) {
                                        meterNo = dataObject.get("MeterAddr").toString().trim();
                                        actPower = dataObject.get("ActPower").toString().trim();
                                        reactPower = dataObject.get("ReactPower").toString().trim();
                                        powerFac = dataObject.get("PowerFac").toString().trim();
                                        volt = dataObject.get("Volt").toString().trim();
                                        current = dataObject.get("Current").toString().trim();
                                        zlCurrent = dataObject.get("ZLCurrent").toString().trim();
                                        appPower = dataObject.get("AppPower").toString().trim();
                                        gridsFreq = dataObject.get("GridsFreq").toString().trim();
                                        temperature = dataObject.get("Temperature").toString().trim();
                                        currentCsq = dataObject.get("CurrentCsq").toString().trim();
                                        this.nbMeterService.insertEleNbData(deviceId, meterNo, "0.0", currentCsq, volt, "0", readDt, errDesc, actPower, reactPower, powerFac, volt, current, zlCurrent, appPower, gridsFreq, temperature, positiveActPower, unPositiveActPower, serviceId);
                                    } else if ("UpLoadMeterActEnegy".equals(serviceId)) {
                                        positiveActPower = "0.0";
                                        currentCsq = "-85";
                                        volt = "0.0";
                                        temperature = "0.0";
                                        meterNo = dataObject.get("MeterAddr").toString().trim();
                                        positiveActPower = dataObject.get("PositiveActPower").toString().trim();
                                        unPositiveActPower = dataObject.get("UnPositiveActPower").toString().trim();
                                        this.nbMeterService.insertEleNbData(deviceId, meterNo, positiveActPower, currentCsq, volt, "0", readDt, errDesc, actPower, reactPower, powerFac, volt, current, zlCurrent, appPower, gridsFreq, temperature, positiveActPower, unPositiveActPower, serviceId);
                                    } else if ("ReadMeterInfo".equals(serviceId)) {
                                        meterNo = dataObject.get("meterAdd").toString();
                                        dosageSum = dataObject.get("reading").toString();
                                        sigValue = dataObject.get("signalStrength").toString();
                                        batVolte = dataObject.get("batteryVoltage").toString();
                                        switchFlag = dataObject.get("valveStatus").toString();
                                        System.out.println("----meterNo:" + meterNo + " dosageSum:" + dosageSum + "  sigValue:" + sigValue + "  batVolte:" + batVolte + "  switchFlag:" + switchFlag + "  readDt:" + readDt);
                                        if ("12345678".equals(meterNo.substring(6))) {
                                            this.nbMeterService.postAsynCommand(theMeterNo, deviceId, "0A", theMeterNo, theCustId, theCommManuCode, "", "Upload");
                                        }

                                        this.nbMeterService.insertNbData(deviceId, theMeterNo, dosageSum, sigValue, batVolte, switchFlag, readDt, errDesc);
                                    }
                                }
                            } else {
                                meterNo = dataObject.get("MeterAddr").toString();
                                dosageSum = dataObject.get("DosageSum").toString();
                                sigValue = dataObject.get("SigValue").toString();
                                batVolte = dataObject.get("BatVolte").toString();
                                switchFlag = dataObject.get("Switch").toString();
                                manuCode = dataObject.get("ManuCode").toString();
                                if ("8833".equals(manuCode.substring(0, 4))) {
                                    errStatus = dataObject.get("ErrStatus").toString();
                                    errStatusStr = Tools.numToBinary(Integer.parseInt(errStatus), 8).substring(0, 5);
                                    if ("00000".equals(errStatusStr)) {
                                        errDesc = "正常";
                                    } else if ("00001".equals(errStatusStr)) {
                                        errDesc = "超温";
                                    } else if ("00010".equals(errStatusStr)) {
                                        errDesc = "空管";
                                    } else if ("00100".equals(errStatusStr)) {
                                        errDesc = "流量过载";
                                    } else if ("01000".equals(errStatusStr)) {
                                        errDesc = "换能器故障";
                                    } else if ("10000".equals(errStatusStr)) {
                                        errDesc = "水表安装方向错误";
                                    }

                                    dosageSum = String.valueOf(Double.valueOf(dosageSum) / 100.0);
                                    batVolte = String.valueOf(Double.valueOf(batVolte) / 10.0);
                                    meterDate = dataObject.get("MeterDate").toString();
                                    if (meterDate != null || "".equals(meterDate) || "null".equals(meterDate)) {
                                        item = meterDate.substring(12, 14);
                                        readDt = meterDate.substring(0, 4) + "-" + meterDate.substring(4, 6) + "-" + meterDate.substring(6, 8) + " " + meterDate.substring(8, 10) + ":" + meterDate.substring(10, 12) + ":" + meterDate.substring(12, 14);
                                    }

                                    if ("12345678".equals(meterNo)) {
                                        this.nbMeterService.postAsynCommand(theMeterNo, deviceId, "0A", theMeterNo, theCustId, theCommManuCode, "", "Upload");
                                    }
                                }

                                if (("APXL".equals(theCommManuCode) || "APXL_ZSHJ".equals(theCommManuCode)) && ("".equals(theImsi) || "1234567890".equals(theImsi))) {
                                    imsi = dataObject.get("IMSI").toString();
                                    this.nbMeterService.updateNbMeterIMSI(deviceId, imsi);
                                }

                                this.nbMeterService.insertNbData(deviceId, theMeterNo, dosageSum, sigValue, batVolte, switchFlag, readDt, errDesc);
                            }
                        }
                    }
                }
            }
        } catch (IOException var51) {
            var51.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/updateDeviceData"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> updateDeviceData(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            System.out.println(Tools.getNow() + "--设备数据变化（deviceDataChanged）-接收到源字符串：" + contentStr.trim());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/DeviceEvent"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> deviceEvent(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            System.out.println(Tools.getNow() + "--设备数据变化（deviceEvent）-接收到源字符串：" + contentStr.trim());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/commandConfirmData"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> commandConfirmData(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            System.out.println(Tools.getNow() + "--设备数据变化（messageConfirm）-接收到源字符串：" + contentStr.trim());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/RulEevent"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> ruleEvent(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            System.out.println(Tools.getNow() + "--设备数据变化（ruleEvent）-接收到源字符串：" + contentStr.trim());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(
            value = {"/api/na/iocm/devNotify/v1.1.0/updateServiceInfo"},
            
            produces = {"application/json"}
    )
    public ResponseEntity<HttpStatus> updateServiceInfo(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }

            System.out.println(Tools.getNow() + "--设备数据变化（serviceInfoChanged）-接收到源字符串：" + contentStr.trim());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping({"/ycmeter/onenetRegisterDevice"})
    @ResponseBody
    public Object onenetRegisterDevice(String meterNo, String nodeId, int custId, String manuId) {
        return this.nbMeterService.onenetRegisterDevice(meterNo, nodeId, custId, manuId);
    }

    @PostMapping({"/ycmeter/onenetDeleteDevice"})
    @ResponseBody
    public Object onenetDeleteDevice(String meterNo, String deviceId, int custId, String manuId) {
        return this.nbMeterService.onenetDeleteDevice(meterNo, deviceId, custId, manuId);
    }

    @PostMapping({"/ycmeter/onenetQueryDeviceData"})
    @ResponseBody
    public Object onenetQueryDeviceData(String deviceId, String manuId) {
        return this.nbMeterService.onenetQueryDeviceData(deviceId, manuId);
    }

    @PostMapping({"/ycmeter/onenetQueryDeviceState"})
    @ResponseBody
    public Object onenetQueryDeviceState(String deviceId, String manuId) {
        return this.nbMeterService.onenetQueryDeviceState(deviceId, manuId);
    }

    @PostMapping({"/ycmeter/searchOneNetDataStream"})
    @ResponseBody
    public Object searchOneNetDataStream(String deviceId, String datastreamId, String manuId) {
        return this.nbMeterService.searchOneNetDataStream(deviceId, datastreamId, manuId);
    }

    @PostMapping({"/ycmeter/searchOneNetDataPoint"})
    @ResponseBody
    public Object searchOneNetDataPoint(String deviceId, String datastreamId, String start, String end, String manuId) {
        return this.nbMeterService.searchOneNetDataPoint(deviceId, datastreamId, start, end, manuId);
    }

    @PostMapping({"/ycmeter/sendOneNetCmd"})
    @ResponseBody
    public Object sendOneNetCmd(String meterNo, String deviceId, String commandCode, String commandValue, int custId, String manuId) {
        return "0".equals(commandCode) ? this.nbMeterService.onenetQueryDeviceState(deviceId, manuId) : this.nbMeterService.sendOneNetCmd(meterNo, deviceId, commandCode, commandValue, custId, manuId);
    }

//    @PostMapping({"/api/oneNetValidate"})
//    @ResponseBody
//    public void oneNetValidate(String msg, String nonce, String signature, HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/json;charset=utf-8");
//
//        try {
//            response.getWriter().write(msg);
//        } catch (IOException var7) {
//            var7.printStackTrace();
//        }
//
//    }

    @PostMapping(
            value = {"/api/oneNetValidate"}
    )
    @ResponseBody
    public void oneNetPushData(HttpServletRequest request, HttpServletResponse response) {
        String msg = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; msg = msg + str) {
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        this.nbMeterService.oneNetPushData(msg);
    }

    @PostMapping({"/ycmeter/getOneNetNbDataDetail"})
    @ResponseBody
    public List<OneNetNbDataDetail> getOneNetNbDataDetail(String orders) {
        return this.nbMeterService.getOneNetNbDataDetail(orders);
    }

    @PostMapping({"/ycmeter/getOneNetNbSendDataInfo"})
    @ResponseBody
    public List<NbSendData> getOneNetNbSendDataInfo(String meterNo, String startDate, String endDate, HttpServletRequest request) {
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

        return this.nbMeterService.getOneNetNbSendDataInfo(orders);
    }

    @PostMapping({"/ycmeter/checkOnenetRegisterDevice"})
    @ResponseBody
    public Object checkOnenetRegisterDevice(int custId) {
        return this.nbMeterService.checkOnenetRegisterDevice(custId);
    }

    @PostMapping({"/ycmeter/delFactoryTestMeter"})
    @ResponseBody
    public String delFactoryTestMeter() {
        return this.nbMeterService.delFactoryTestMeter();
    }
}
