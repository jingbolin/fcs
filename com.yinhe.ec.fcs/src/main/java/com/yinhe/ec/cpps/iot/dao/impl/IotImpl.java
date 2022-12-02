//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.iot.dao.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yinhe.ec.cpps.iot.dao.IotI;
import com.yinhe.ec.cpps.iot.utils.HttpsUtil;
import com.yinhe.ec.cpps.iot.utils.JsonUtil;
import com.yinhe.ec.cpps.iot.utils.StreamClosedHttpResponse;
import com.yinhe.ec.cpps.model.Nb_MeterParameter;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.NbApplicationDAO;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional
public class IotImpl implements IotI {
    private static Logger logger = Logger.getLogger(IotImpl.class);
    private static boolean isprint = true;
    @Resource
    private NbApplicationDAO nbApplicationDAO;
    @Resource
    private Nb_MeterParameter nb_MeterParameter;
    private final int time = 300;

    public IotImpl() {
    }

    public Map<String, Object> login(HttpsUtil httpsUtil, String manuId) throws Exception {
        String appId = "";
        String secret = "";
        String urlLogin = "";
        int appUseFlag = 0;
        Map<String, Object> accessTokenMap = new HashMap();
        this.nb_MeterParameter = this.nbApplicationDAO.getNbMeterParameterByManuCode(manuId);
        appId = this.nb_MeterParameter.getAppId();
        secret = this.nb_MeterParameter.getApplication().getSecret();
        appUseFlag = this.nb_MeterParameter.getApplication().getAppuseFlag();
        if (!"".equals(this.nb_MeterParameter.getApplication().getAccessToken()) && Tools.getTime(this.nb_MeterParameter.getApplication().getAccessTime(), Tools.getNow()) <= 3000000L) {
            accessTokenMap.put("accessToken", this.nb_MeterParameter.getApplication().getAccessToken());
            accessTokenMap.put("appId", appId);
            accessTokenMap.put("appUseFlag", "" + appUseFlag);
            accessTokenMap.put("nbMeterParameter", this.nb_MeterParameter);
        } else {
            if (appUseFlag == 0) {
                urlLogin = "https://180.101.147.89:8743/iocm/app/sec/v1.1.0/login";
            } else {
                urlLogin = "https://device.api.ct10649.com:8743/iocm/app/sec/v1.1.0/login";
            }

            Map<String, String> paramLogin = new HashMap();
            paramLogin.put("appId", appId);
            paramLogin.put("secret", secret);
            StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);
            Map<String, String> data = new HashMap();
            data = (Map)JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
            if (isprint) {
                logger.info("app auth success,return accessToken:");
                logger.info(data);
            }

            accessTokenMap.put("accessToken", data.get("accessToken"));
            accessTokenMap.put("appId", appId);
            accessTokenMap.put("appUseFlag", "" + appUseFlag);
            accessTokenMap.put("nbMeterParameter", this.nb_MeterParameter);
            this.nbApplicationDAO.updateAccessToken(appId, (String)data.get("accessToken"));
        }

        return accessTokenMap;
    }

    @Override
    public String RegisterDevice(String nodeId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlReg = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlReg = "https://180.101.147.89:8743/iocm/app/reg/v1.1.0/devices";
        } else {
            urlReg = "https://device.api.ct10649.com:8743/iocm/app/reg/v1.1.0/devices";
        }

        Integer timeout = 0;
        Map<String, Object> paramReg = new HashMap();
        paramReg.put("verifyCode", nodeId.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("timeout", timeout);
        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, header, jsonRequest);
        if (isprint) {
            logger.info("RegisterDirectlyConnectedDevice, response content:");
            logger.info(responseReg.getStatusLine() + "--" + responseReg.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var15) {
        }

        return responseReg.getContent();
    }

    @Override
    public String ModifyDevice(String deviceId, String name, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        new Nb_MeterParameter();
        Nb_MeterParameter nbm = (Nb_MeterParameter)accessTokenMap.get("nbMeterParameter");
        String manufacturerId = nbm.getManufacturerId();
        String manufacturerName = nbm.getManufacturerName();
        String deviceType = nbm.getDeviceType();
        String model = nbm.getDeviceModel();
        String protocolType = nbm.getProtocolType();
        String urlModifyDeviceInfo = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlModifyDeviceInfo = "https://180.101.147.89:8743/iocm/app/dm/v1.1.0/devices/" + deviceId;
        } else {
            urlModifyDeviceInfo = "https://device.api.ct10649.com:8743/iocm/app/dm/v1.1.0/devices/" + deviceId;
        }

        Map<String, Object> paramModifyDeviceInfo = new HashMap();
        paramModifyDeviceInfo.put("manufacturerId", manufacturerId);
        paramModifyDeviceInfo.put("manufacturerName", manufacturerName);
        paramModifyDeviceInfo.put("deviceType", deviceType);
        paramModifyDeviceInfo.put("model", model);
        paramModifyDeviceInfo.put("protocolType", protocolType);
        paramModifyDeviceInfo.put("name", name);
        paramModifyDeviceInfo.put("location", "XIAN");
        String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo, header, jsonRequest);
        if (isprint) {
            logger.info("ModifyDeviceInfo, response content:");
            logger.info(responseModifyDeviceInfo.getStatusLine() + "--" + responseModifyDeviceInfo.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var20) {
        }

        return responseModifyDeviceInfo.getStatusLine().toString();
    }

    @Override
    public String DeleteDevice(String deviceId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlDelete = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlDelete = "https://180.101.147.89:8743/iocm/app/dm/v1.1.0/devices/" + deviceId;
        } else {
            urlDelete = "https://device.api.ct10649.com:8743/iocm/app/dm/v1.1.0/devices/" + deviceId;
        }

        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse responseDelete = httpsUtil.doDeleteGetStatusLine(urlDelete, header);
        if (isprint) {
            logger.info("DeleteDirectlyConnectedDevice, response content:");
            logger.info(responseDelete.getStatusLine() + "--" + responseDelete.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var11) {
        }

        return responseDelete.getStatusLine().toString();
    }

    @Override
    public String QueryDeviceStatus(String deviceId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlDeviceActivationStatus = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlDeviceActivationStatus = "https://180.101.147.89:8743/iocm/app/reg/v1.1.0/devices/" + deviceId;
        } else {
            urlDeviceActivationStatus = "https://device.api.ct10649.com:8743/iocm/app/reg/v1.1.0/devices/" + deviceId;
        }

        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse bodyDeviceActivationStatus = httpsUtil.doGetWithParasGetStatusLine(urlDeviceActivationStatus, (Map)null, header);
        if (isprint) {
            logger.info("QueryDeviceActivationStatus, response content:");
            logger.info(bodyDeviceActivationStatus.getStatusLine() + "--" + bodyDeviceActivationStatus.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var11) {
        }

        return bodyDeviceActivationStatus.getContent();
    }

    @Override
    public String QueryDeviceCapabilities(String deviceId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlQueryDeviceCapabilities = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlQueryDeviceCapabilities = "https://180.101.147.89:8743/iocm/app/data/v1.1.0/deviceCapabilities";
        } else {
            urlQueryDeviceCapabilities = "https://device.api.ct10649.com:8743/iocm/app/data/v1.1.0/deviceCapabilities";
        }

        Map<String, String> paramQueryDeviceCapabilities = new HashMap();
        paramQueryDeviceCapabilities.put("deviceId", deviceId);
        paramQueryDeviceCapabilities.put("gatewayId", deviceId);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse bodyQueryDeviceCapabilities = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceCapabilities, paramQueryDeviceCapabilities, header);
        if (isprint) {
            logger.info("QueryDeviceCapabilities, response content:");
            logger.info(bodyQueryDeviceCapabilities.getStatusLine() + "--" + bodyQueryDeviceCapabilities.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var13) {
        }

        return bodyQueryDeviceCapabilities.getContent();
    }

    @Override
    public String QueryDeviceData(String deviceId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlQueryDeviceData = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlQueryDeviceData = "https://180.101.147.89:8743/iocm/app/dm/v1.3.0/devices/" + deviceId;
        } else {
            urlQueryDeviceData = "https://device.api.ct10649.com:8743/iocm/app/dm/v1.3.0/devices/" + deviceId;
        }

        Map<String, String> paramQueryDeviceData = new HashMap();
        paramQueryDeviceData.put("appId", appId);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse bodyQueryDeviceData = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceData, paramQueryDeviceData, header);
        if (isprint) {
            logger.info("QueryDeviceData, response content:");
            logger.info(bodyQueryDeviceData.getStatusLine() + "--" + bodyQueryDeviceData.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var12) {
        }

        return bodyQueryDeviceData.getContent();
    }

    @Override
    public String QueryDeviceHistoryData(String deviceId, String startTime, String endTime, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlQueryDeviceHistoryData = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlQueryDeviceHistoryData = "https://180.101.147.89:8743/iocm/app/data/v1.1.0/deviceDataHistory";
        } else {
            urlQueryDeviceHistoryData = "https://device.api.ct10649.com:8743/iocm/app/data/v1.1.0/deviceDataHistory";
        }

        Map<String, String> paramQueryDeviceHistoryData = new HashMap();
        paramQueryDeviceHistoryData.put("deviceId", deviceId);
        paramQueryDeviceHistoryData.put("gatewayId", deviceId);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse bodyQueryDeviceHistoryData = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceHistoryData, paramQueryDeviceHistoryData, header);
        if (isprint) {
            logger.info("QueryDeviceHistoryData, response content:");
            logger.info(bodyQueryDeviceHistoryData.getStatusLine() + "--" + bodyQueryDeviceHistoryData.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var15) {
        }

        return bodyQueryDeviceHistoryData.getContent();
    }

    @Override
    public String QueryDevices(String deviceId, String startTime, String endTime, String status, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlQueryDevices = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlQueryDevices = "https://180.101.147.89:8743/iocm/app/dm/v1.3.0/devices";
        } else {
            urlQueryDevices = "https://device.api.ct10649.com:8743/iocm/app/dm/v1.3.0/devices";
        }

        Integer pageNo = 0;
        Integer pageSize = 10;
        Map<String, String> paramQueryDevices = new HashMap();
        paramQueryDevices.put("appId", appId);
        paramQueryDevices.put("pageNo", pageNo.toString());
        paramQueryDevices.put("pageSize", pageSize.toString());
        if (!"".equals(deviceId) && deviceId != null) {
            paramQueryDevices.put("gatewayId", deviceId);
        }

        if (!"".equals(startTime) && startTime != null) {
            paramQueryDevices.put("startTime", startTime);
        }

        if (!"".equals(endTime) && endTime != null) {
            paramQueryDevices.put("endTime", endTime);
        }

        if (!"".equals(status) && status != null) {
            paramQueryDevices.put("status", status);
        }

        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        StreamClosedHttpResponse bodyQueryDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevices, paramQueryDevices, header);
        if (isprint) {
            logger.info("QueryDevices, response content:");
            logger.info(bodyQueryDevices.getStatusLine() + "--" + bodyQueryDevices.getContent());
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var17) {
        }

        return bodyQueryDevices.getContent();
    }

    @Override
    public String PostAsynCommandV4(String deviceId, String commandCode, String commandValue, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlPostAsynCmd = "https://device.api.ct10649.com:8743/iocm/app/cmd/v1.4.0/deviceCommands";
        String callbackUrl = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/reportCmdExecResult";
        int expireTime = 0;
        String serviceId = "Downlink";
        String method = "Command";
        ObjectNode paras = null;
        if (manuId.equals("APXL_ZSHJ")) {
            serviceId = "DataTransport";
            method = "DataTransport";
            paras = JsonUtil.convertObject2ObjectNode("{\"Data\":\"" + commandValue + "\"}");
        } else if (manuId.equals("JX_ZSHJ")) {
            serviceId = "DeliverySchedule";
            method = "BATTERY_WARNING";
            paras = JsonUtil.convertObject2ObjectNode("{\"array\":\"" + commandValue + "\"}");
        } else if (manuId.equals("JX_ZSHJ_2020")) {
            serviceId = "DeliverySchedule";
            method = "BATTERY_WARNING";
            paras = JsonUtil.convertObject2ObjectNode("{\"array\":\"" + commandValue + "\"}");
        }

        Map<String, Object> paramCommand = new HashMap();
        paramCommand.put("serviceId", serviceId);
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);
        Map<String, Object> paramPostAsynCmd = new HashMap();
        paramPostAsynCmd.put("deviceId", deviceId);
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", callbackUrl);
        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);
        String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
        if (isprint) {
            logger.info("下发命令:" + jsonRequest);
            logger.info("下发命令返回:" + responseBody);
        }

        try {
            Thread.sleep(300L);
        } catch (InterruptedException var22) {
        }

        return responseBody;
    }

    @Override
    public void SubscribeNotification(String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlSubscribe = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlSubscribe = "https://180.101.147.89:8743/iocm/app/sub/v1.1.0/subscribe";
        } else {
            urlSubscribe = "https://device.api.ct10649.com:8743/iocm/app/sub/v1.1.0/subscribe";
        }

        String callbackurl_deviceAdded = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/addDevice";
        String notifyType_deviceAdded = "deviceAdded";
        Map<String, Object> paramSubscribe = new HashMap();
        paramSubscribe.put("notifyType", notifyType_deviceAdded);
        paramSubscribe.put("callbackurl", callbackurl_deviceAdded);
        String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);
        String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceAdded + "  " + httpResponse.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe);
        }

        String callbackurl_deviceInfoChanged = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceInfo";
        String notifyType_deviceInfoChanged = "deviceInfoChanged";
        Map<String, Object> paramSubscribe_deviceInfoChanged = new HashMap();
        paramSubscribe_deviceInfoChanged.put("notifyType", notifyType_deviceInfoChanged);
        paramSubscribe_deviceInfoChanged.put("callbackurl", callbackurl_deviceInfoChanged);
        String jsonRequest_deviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceInfoChanged);
        Map<String, String> header_deviceInfoChanged = new HashMap();
        header_deviceInfoChanged.put("app_key", appId);
        header_deviceInfoChanged.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_deviceInfoChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceInfoChanged, jsonRequest_deviceInfoChanged);
        String bodySubscribe_deviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceInfoChanged);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceInfoChanged + "  " + httpResponse_deviceInfoChanged.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceInfoChanged);
        }

        String callbackurl_deviceDataChanged = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceData";
        String notifyType_deviceDataChanged = "deviceDataChanged";
        Map<String, Object> paramSubscribe_deviceDataChanged = new HashMap();
        paramSubscribe_deviceDataChanged.put("notifyType", notifyType_deviceDataChanged);
        paramSubscribe_deviceDataChanged.put("callbackurl", callbackurl_deviceDataChanged);
        String jsonRequest_deviceDataChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDataChanged);
        Map<String, String> header_deviceDataChanged = new HashMap();
        header_deviceDataChanged.put("app_key", appId);
        header_deviceDataChanged.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_deviceDataChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceDataChanged, jsonRequest_deviceDataChanged);
        String bodySubscribe_deviceDataChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDataChanged);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceDataChanged + "  " + httpResponse_deviceDataChanged.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceDataChanged);
        }

        String callbackurl_deviceDeleted = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/deletedDevice";
        String notifyType_deviceDeleted = "deviceDeleted";
        Map<String, Object> paramSubscribe_deviceDeleted = new HashMap();
        paramSubscribe_deviceDeleted.put("notifyType", notifyType_deviceDeleted);
        paramSubscribe_deviceDeleted.put("callbackurl", callbackurl_deviceDeleted);
        String jsonRequest_deviceDeleted = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDeleted);
        Map<String, String> header_deviceDeleted = new HashMap();
        header_deviceDeleted.put("app_key", appId);
        header_deviceDeleted.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_deviceDeleted = httpsUtil.doPostJson(urlSubscribe, header_deviceDeleted, jsonRequest_deviceDeleted);
        String bodySubscribe_deviceDeleted = httpsUtil.getHttpResponseBody(httpResponse_deviceDeleted);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceDeleted + "  " + httpResponse_deviceDeleted.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceDeleted);
        }

        String callbackurl_messageConfirm = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/commandConfirmData";
        String notifyType_messageConfirm = "messageConfirm";
        Map<String, Object> paramSubscribe_messageConfirm = new HashMap();
        paramSubscribe_messageConfirm.put("notifyType", notifyType_messageConfirm);
        paramSubscribe_messageConfirm.put("callbackurl", callbackurl_messageConfirm);
        String jsonRequest_messageConfirm = JsonUtil.jsonObj2Sting(paramSubscribe_messageConfirm);
        Map<String, String> header_messageConfirm = new HashMap();
        header_messageConfirm.put("app_key", appId);
        header_messageConfirm.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_messageConfirm = httpsUtil.doPostJson(urlSubscribe, header_messageConfirm, jsonRequest_messageConfirm);
        String bodySubscribe_messageConfirm = httpsUtil.getHttpResponseBody(httpResponse_messageConfirm);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_messageConfirm + "  " + httpResponse_messageConfirm.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_messageConfirm);
        }

        String callbackurl_commandRsp = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/commandRspData";
        String notifyType_commandRsp = "commandRsp";
        Map<String, Object> paramSubscribecommandRsp = new HashMap();
        paramSubscribecommandRsp.put("notifyType", notifyType_commandRsp);
        paramSubscribecommandRsp.put("callbackurl", callbackurl_commandRsp);
        String jsonRequestcommandRsp = JsonUtil.jsonObj2Sting(paramSubscribecommandRsp);
        Map<String, String> headercommandRsp = new HashMap();
        headercommandRsp.put("app_key", appId);
        headercommandRsp.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponsecommandRsp = httpsUtil.doPostJson(urlSubscribe, headercommandRsp, jsonRequestcommandRsp);
        String bodySubscribecommandRsp = httpsUtil.getHttpResponseBody(httpResponsecommandRsp);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_commandRsp + "  " + httpResponsecommandRsp.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribecommandRsp);
        }

        String callbackurl_deviceDatasChanged = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceDatas";
        String notifyType_deviceDatasChanged = "deviceDatasChanged";
        Map<String, Object> paramSubscribe_deviceDatasChanged = new HashMap();
        paramSubscribe_deviceDatasChanged.put("notifyType", notifyType_deviceDatasChanged);
        paramSubscribe_deviceDatasChanged.put("callbackurl", callbackurl_deviceDatasChanged);
        String jsonRequest_deviceDatasChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDatasChanged);
        Map<String, String> header_deviceDatasChanged = new HashMap();
        header_deviceDatasChanged.put("app_key", appId);
        header_deviceDatasChanged.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_deviceDatasChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceDatasChanged, jsonRequest_deviceDatasChanged);
        String bodySubscribe_deviceDatasChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDatasChanged);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceDatasChanged + "  " + httpResponse_deviceDatasChanged.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceDatasChanged);
        }

    }

    @Override
    public void DelSubscribeNotification(String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlSubscribe = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlSubscribe = "https://180.101.147.89:8743/iocm/app/sub/v1.2.0/subscriptions?appId={appId}&notifyType={notifyType}&callbackUrl={callbackUrl}";
        } else {
            urlSubscribe = "https://device.api.ct10649.com:8743/iocm/app/sub/v1.2.0/subscriptions?appId={appId}&notifyType={notifyType}&callbackUrl={callbackUrl}";
        }

        String callbackurl_deviceAdded = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/addDevice";
        String notifyType_deviceAdded = "deviceAdded";
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        System.out.println(urlSubscribe);
        System.out.println("appId=" + appId);
        String urlSubscribe_deviceAdded = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_deviceAdded).replace("{callbackUrl}", callbackurl_deviceAdded);
        HttpResponse httpResponse = httpsUtil.doDelete(urlSubscribe_deviceAdded, header);
        String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceAdded + "  " + httpResponse.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe);
        }

        String callbackurl_deviceInfoChanged = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceInfo";
        String notifyType_deviceInfoChanged = "deviceInfoChanged";
        Map<String, String> header_deviceInfoChanged = new HashMap();
        header_deviceInfoChanged.put("app_key", appId);
        header_deviceInfoChanged.put("Authorization", "Bearer " + accessToken);
        String urlSubscribe_deviceInfoChanged = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_deviceInfoChanged).replace("{callbackUrl}", callbackurl_deviceInfoChanged);
        System.out.println(urlSubscribe_deviceInfoChanged);
        HttpResponse httpResponse_deviceInfoChanged = httpsUtil.doDelete(urlSubscribe_deviceInfoChanged, header_deviceInfoChanged);
        String bodySubscribe_deviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceInfoChanged);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceInfoChanged + "  " + httpResponse_deviceInfoChanged.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceInfoChanged);
        }

        Map<String, String> header_deviceDataChanged = new HashMap();
        header_deviceDataChanged.put("app_key", appId);
        header_deviceDataChanged.put("Authorization", "Bearer " + accessToken);
        String callbackurl_deviceDataChanged = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceData";
        String notifyType_deviceDataChanged = "deviceDataChanged";
        String urlSubscribe_deviceDataChanged = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_deviceDataChanged).replace("{callbackUrl}", callbackurl_deviceDataChanged);
        HttpResponse httpResponse_deviceDataChanged = httpsUtil.doDelete(urlSubscribe_deviceDataChanged, header_deviceDataChanged);
        String bodySubscribe_deviceDataChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDataChanged);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceDataChanged + "  " + httpResponse_deviceDataChanged.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceDataChanged);
        }

        String callbackurl_deviceDeleted = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/deletedDevice";
        String notifyType_deviceDeleted = "deviceDeleted";
        String urlSubscribe_deviceDeleted = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_deviceDeleted).replace("{callbackUrl}", callbackurl_deviceDeleted);
        Map<String, String> header_deviceDeleted = new HashMap();
        header_deviceDeleted.put("app_key", appId);
        header_deviceDeleted.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_deviceDeleted = httpsUtil.doDelete(urlSubscribe_deviceDeleted, header_deviceDeleted);
        String bodySubscribe_deviceDeleted = httpsUtil.getHttpResponseBody(httpResponse_deviceDeleted);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceDeleted + "  " + httpResponse_deviceDeleted.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceDeleted);
        }

        String callbackurl_messageConfirm = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/commandConfirmData";
        String notifyType_messageConfirm = "messageConfirm";
        String urlSubscribe_messageConfirm = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_messageConfirm).replace("{callbackUrl}", callbackurl_messageConfirm);
        Map<String, String> header_messageConfirm = new HashMap();
        header_messageConfirm.put("app_key", appId);
        header_messageConfirm.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_messageConfirm = httpsUtil.doDelete(urlSubscribe_messageConfirm, header_messageConfirm);
        String bodySubscribe_messageConfirm = httpsUtil.getHttpResponseBody(httpResponse_messageConfirm);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_messageConfirm + "  " + httpResponse_messageConfirm.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_messageConfirm);
        }

        String callbackurl_commandRsp = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/commandRspData";
        String notifyType_commandRsp = "commandRsp";
        String urlSubscribe_commandRsp = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_commandRsp).replace("{callbackUrl}", callbackurl_commandRsp);
        Map<String, String> headercommandRsp = new HashMap();
        headercommandRsp.put("app_key", appId);
        headercommandRsp.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponsecommandRsp = httpsUtil.doDelete(urlSubscribe_commandRsp, headercommandRsp);
        String bodySubscribecommandRsp = httpsUtil.getHttpResponseBody(httpResponsecommandRsp);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_commandRsp + "  " + httpResponsecommandRsp.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribecommandRsp);
        }

        String callbackurl_deviceDatasChanged = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceDatas";
        String notifyType_deviceDatasChanged = "deviceDatasChanged";
        String urlSubscribe_deviceDatasChanged = urlSubscribe.replace("{appId}", appId).replace("{notifyType}", notifyType_deviceDatasChanged).replace("{callbackUrl}", callbackurl_deviceDatasChanged);
        Map<String, String> header_deviceDatasChanged = new HashMap();
        header_deviceDatasChanged.put("app_key", appId);
        header_deviceDatasChanged.put("Authorization", "Bearer " + accessToken);
        HttpResponse httpResponse_deviceDatasChanged = httpsUtil.doDelete(urlSubscribe_deviceDatasChanged, header_deviceDatasChanged);
        String bodySubscribe_deviceDatasChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDatasChanged);
        if (isprint) {
            logger.info("SubscribeNotification:" + notifyType_deviceDatasChanged + "  " + httpResponse_deviceDatasChanged.getStatusLine());
            logger.info("SubscribeNotification bodySubscribe:" + bodySubscribe_deviceDatasChanged);
        }

    }

    @Override
    public String CreateDeviceCmdCancelTask(String deviceId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlCreateDeviceCmdCancelTask = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlCreateDeviceCmdCancelTask = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
        } else {
            urlCreateDeviceCmdCancelTask = "https://device.api.ct10649.com:8743/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
        }

        Map<String, Object> cmdCancelTask = new HashMap();
        cmdCancelTask.put("deviceId", deviceId);
        String jsonRequest = JsonUtil.jsonObj2Sting(cmdCancelTask);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        HttpResponse responseCmdCancelTask = httpsUtil.doPostJson(urlCreateDeviceCmdCancelTask, header, jsonRequest);
        String responseBody = httpsUtil.getHttpResponseBody(responseCmdCancelTask);
        if (isprint) {
            logger.info("CreateDeviceCmdCancelTask:" + responseCmdCancelTask.getStatusLine());
            logger.info("CreateDeviceCmdCancelTask body:" + responseBody);
        }

        return responseBody;
    }

    @Override
    public String UpdateAsynCommand(String deviceCommandId, String manuId) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        manuId = manuId.toUpperCase();
        Map<String, Object> accessTokenMap = this.login(httpsUtil, manuId);
        String appId = accessTokenMap.get("appId").toString();
        String accessToken = accessTokenMap.get("accessToken").toString();
        String urlUpdateAsynCommand = "";
        if (accessTokenMap.get("appUseFlag").equals("0")) {
            urlUpdateAsynCommand = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommands/{deviceCommandId}?appId={appId}";
        } else {
            urlUpdateAsynCommand = "https://device.api.ct10649.com:8743/iocm/app/cmd/v1.4.0/deviceCommands/{deviceCommandId}?appId={appId}";
        }

        urlUpdateAsynCommand = urlUpdateAsynCommand.replace("{deviceCommandId}", deviceCommandId).replace("{appId}", appId);
        Map<String, Object> updateTask = new HashMap();
        updateTask.put("status", "CANCELED");
        String jsonRequest = JsonUtil.jsonObj2Sting(updateTask);
        Map<String, String> header = new HashMap();
        header.put("app_key", appId);
        header.put("Authorization", "Bearer " + accessToken);
        HttpResponse responseUpdateTask = httpsUtil.doPutJson(urlUpdateAsynCommand, header, jsonRequest);
        String responseBody = httpsUtil.getHttpResponseBody(responseUpdateTask);
        if (isprint) {
            logger.info("responseUpdateTask:" + responseUpdateTask.getStatusLine());
            logger.info("responseUpdateTask body:" + responseBody);
        }

        return responseBody;
    }
}
