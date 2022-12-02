// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Constant.java

package com.yinhe.ec.cpps.iot.utils;


public class Constant
{

    public Constant()
    {
    }

    public static final String BASE_URL = "https://180.101.147.89:8743";
    public static final String CALLBACK_BASE_URL = "http://39.104.60.18:8999/nygl/api";
    public static final String DEVICE_ADDED_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/addDevice";
    public static final String DEVICE_INFO_CHANGED_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceInfo";
    public static final String DEVICE_DATA_CHANGED_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceData";
    public static final String DEVICE_DELETED_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/deletedDevice";
    public static final String MESSAGE_CONFIRM_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/commandConfirmData";
    public static final String SERVICE_INFO_CHANGED_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateServiceInfo";
    public static final String COMMAND_RSP_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/commandRspData";
    public static final String DEVICE_EVENT_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/DeviceEvent";
    public static final String RULE_EVENT_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/RulEevent";
    public static final String DEVICE_DATAS_CHANGED_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/updateDeviceDatas";
    public static final String REPORT_CMD_EXEC_RESULT_CALLBACK_URL = "http://39.104.60.18:8999/nygl/api/na/iocm/devNotify/v1.1.0/reportCmdExecResult";
    public static String SELFCERTPATH = "E:\\cert_iot_stdy\\outgoing.CertwithKey.pkcs12";
    public static String TRUSTCAPATH = "E:\\cert_iot_stdy\\ca.jks";
    public static String SELFCERTPWD = "IoM@1234";
    public static String TRUSTCAPWD = "Huawei@123";
    public static final String HEADER_APP_KEY = "app_key";
    public static final String HEADER_APP_AUTH = "Authorization";
    public static final String APP_AUTH = "https://180.101.147.89:8743/iocm/app/sec/v1.1.0/login";
    public static final String REFRESH_TOKEN = "https://180.101.147.89:8743/iocm/app/sec/v1.1.0/refreshToken";
    public static final String REGISTER_DEVICE = "https://180.101.147.89:8743/iocm/app/reg/v1.1.0/devices";
    public static final String MODIFY_DEVICE_INFO = "https://180.101.147.89:8743/iocm/app/dm/v1.1.0/devices";
    public static final String QUERY_DEVICE_ACTIVATION_STATUS = "https://180.101.147.89:8743/iocm/app/reg/v1.1.0/devices";
    public static final String DELETE_DEVICE = "https://180.101.147.89:8743/iocm/app/dm/v1.1.0/devices";
    public static final String DISCOVER_INDIRECT_DEVICE = "https://180.101.147.89:8743/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String REMOVE_INDIRECT_DEVICE = "https://180.101.147.89:8743/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String QUERY_DEVICES = "https://180.101.147.89:8743/iocm/app/dm/v1.3.0/devices";
    public static final String QUERY_DEVICE_DATA = "https://180.101.147.89:8743/iocm/app/dm/v1.3.0/devices";
    public static final String QUERY_DEVICE_HISTORY_DATA = "https://180.101.147.89:8743/iocm/app/data/v1.1.0/deviceDataHistory";
    public static final String QUERY_DEVICE_CAPABILITIES = "https://180.101.147.89:8743/iocm/app/data/v1.1.0/deviceCapabilities";
    public static final String SUBSCRIBE_NOTIFYCATION = "https://180.101.147.89:8743/iocm/app/sub/v1.1.0/subscribe";
    public static final String DEL_SUBSCRIBE_NOTIFYCATION = "https://180.101.147.89:8743/iocm/app/sub/v1.2.0/subscriptions?appId={appId}&notifyType={notifyType}&callbackUrl={callbackUrl}";
    public static final String POST_ASYN_CMD = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String QUERY_DEVICE_CMD = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String UPDATE_ASYN_COMMAND = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommands/{deviceCommandId}?appId={appId}";
    public static final String CREATE_DEVICECMD_CANCEL_TASK = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    public static final String QUERY_DEVICECMD_CANCEL_TASK = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    public static final String DEVICE_ADDED = "deviceAdded";
    public static final String DEVICE_INFO_CHANGED = "deviceInfoChanged";
    public static final String DEVICE_DATA_CHANGED = "deviceDataChanged";
    public static final String DEVICE_DELETED = "deviceDeleted";
    public static final String MESSAGE_CONFIRM = "messageConfirm";
    public static final String SERVICE_INFO_CHANGED = "serviceInfoChanged";
    public static final String COMMAND_RSP = "commandRsp";
    public static final String DEVICE_EVENT = "deviceEvent";
    public static final String RULE_EVENT = "ruleEvent";
    public static final String DEVICE_DATAS_CHANGED = "deviceDatasChanged";

}
