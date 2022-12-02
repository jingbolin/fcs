package com.yinhe.ec.ws.server;

import com.yinhe.ec.ws.server.manager.vo.MessageInfo;

/**
 * 通讯模块相关常量
 * @author chenyh
 * @since 2018年8月20日下午3:56:41
 */
public class Protocol {
    
    /**
     * 通用类型
     */
    /** 数据链接成功 */
    public final static String TYPE_OUTPUT_CONNECT = "1001"; 

    /** 心跳请求 */
    public final static String TYPE_INPUT_HEARTBEAT = "0003";

    /** 心跳响应 */
    public final static String TYPE_OUTPUT_HEARTBEAT = "1005";
    
    /** 消息类型为空 **/
    public final static String TYPE_OUTPUT_MESSAGE_TYPE_NULL = "1101";

    /** 消息类型不存在 **/
    public final static String TYPE_OUTPUT_MESSAGE_TYPE_ERROR = "1102";

    /** 消息内容错误，无法解析 */
    public final static String TYPE_OUTPUT_CONTENT_ERROR = "1103"; 
    

    
    /** 
     * 测点（Subscribe）订阅类型，测点订阅消息中使用的type
     */
    /** 订阅测点 */
    public final static String TYPE_INPUT_SUBSCRIBE = "0001";

    /** 订阅成功 */
    public final static String TYPE_OUTPUT_SUBSCRIBE = "1002"; 

    /** 订阅推送 */
    public final static String TYPE_OUTPUT_MESSAGE = "1004";

    /** 获得所有测点订阅主题 **/
    public final static String TYPE_INPUT_TOPIC = "0004";

    /** 返回所有测点订阅主题 **/
    public final static String TYPE_OUTPUT_TOPIC = "1006";

    /** 退订请求 */
    public final static String TYPE_INPUT_CLEAR = "0002";

    /** 退订成功 */
    public final static String TYPE_OUTPUT_CLEAR = "1003";

    /** 退订所有主题 **/
    public final static String TYPE_INPUT_CLEAR_ALL = "0005";

    /** 退订所有主题成功 **/
    public final static String TYPE_OUTPUT_CLEAR_ALL = "1007";
    
    /**
     * 告警（Alarm）消息类型，订阅告警时使用的type
     */  
    /** 告警消息类型，订阅请求时的type */
    public final static String TYPE_INPUT_ALARM = "0008";
    
    /** 告警消息类型，返回界面时的type */
    public final static String TYPE_OUTPUT_ALARM = "1008";
    
    /**
     * 计算数据消息（SeriesAbnormal）类型，订阅计算数据消息时使用的type
     */
    /** 计算数据消息类型，订阅请求时的type */
    public final static String TYPE_INPUT_CALC_DATA = "0009";
    
    /** 计算数据消息类型，返回界面时的type */
    public final static String TYPE_OUTPUT_CALC_DATA = "1009";
    
    /** 计算数据消息类型，清理时的type */
    public final static String TYPE_INPUT_CALC_DATA_CLEAR = "0010";
    
    /** 计算数据消息类型，清理返回时的type */
    public final static String TYPE_OUTPUT_CALC_DATA_CLEAR = "1010";
    
    /**
     * 控制（Control）消息类型，控制指令（遥控、遥调）下发时使用的type
     */
    /** 定值-读定值-请求 **/
    public final static String TYPE_INPUT_DZ_READ = "0014";

    /** 定值-读定值-响应 **/
    public final static String TYPE_OUTPUT_DZ_READ = "1014";
    
    /** 遥控-写值-请求 **/
    public final static String TYPE_INPUT_YK_WRITE = "0015";

    /** 遥控-写值-响应 **/
    public final static String TYPE_OUTPUT_YK_WRITE = "1015";

    /** 遥调-写值-请求 **/
    public final static String TYPE_INPUT_YT_WRITE = "0016";

    /** 遥调-写值-响应 **/
    public final static String TYPE_OUTPUT_YT_WRITE = "1016";
    
    
    /**
     * 接收和返回都使用的字段
     */
    /** 消息类型 **/
    public final static String MESSAGE_TYPE = "type";
    
    /** 主题名称 **/
    public final static String MESSAGE_TOPIC = "topic";
    
    
    /**
     * 消息字段，解析接收到的消息中的字段列表，对应消息实体{@link MessageInfo}
     */
    /** 订单列表 **/
    public final static String MESSAGE_OEDER = "order";

    /** 清理的主题数组 **/
    public final static String MESSAGE_CLEAR = "clear";

    /** 操作人 **/
    public final static String MESSAGE_USER_ID = "userId";

    /** 写入的数据 **/
    public final static String MESSAGE_WRITE_VALUE = "writeValue";

    /** 读定值 **/
    public final static String MESSAGE_READ_VALUE = "readValue";

    /** 读定值时的设备号 **/
    public final static String MESSAGE_DEVICE = "device";
    
    /** 遥调遥控写值时的code */
    public final static String MESSAGE_WRITE_VALUE_CODE = "code";
    
    /** 遥调遥控写值时的value */
    public final static String MESSAGE_WRITE_VALUE_VALUE = "value";
    
    
    /**
     * 给websocket客户端返回的消息中使用的字段
     */
    /** 数据类型 **/
    public final static String MESSAGE_DATA_TYPE = "dataType";

    /** 数据 **/
    public final static String MESSAGE_DATA = "data";

    /** 消息 **/
    public final static String MESSAGE_MSG = "msg";

    /** 消息反校状态 **/
    public final static String MESSAGE_REPLY_TYPE = "replyType";
    
    /** 数据类型枚举 */
    public static enum MessageDataType {
        /** 订阅类消息 */
        Subscribe,
        /** 告警消息类型 */
        Alarm,
        /** 计算数据消息类型 */
        CalcData,
        /** 控制类消息 */
        Control;
    }
    
    /** 反校类型枚举 */
    public static enum MessageReplyType {
        /** 成功 */
        Successful,
        /** 失败 */
        Fail,
        /** 超时 */
        TimeOut;
        
    }
    
}
