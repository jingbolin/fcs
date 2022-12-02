package com.yinhe.ec.ws.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.websocket.Session;

import com.yinhe.ec.cpps.Constants;
import com.yinhe.ec.ws.server.manager.vo.MessageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.yinhe.ec.ws.server.Protocol;
import com.yinhe.ec.ws.server.manager.MessageService;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 控制消息服务
 * 
 * @author wangshilei
 * @date 2021/04/09
 */
@Component
public class ControlMessageService {

    Logger logger = LogManager.getLogger();

    @Autowired
    private MessageService messageService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /** 指令超时时长 */
    private final static Long TIME_OUT_LENGTH  = 30 * 1000L;

    /**
     * 遥控-写值-请求
     * 
     * @param session
     * @param msg
     */
    public void writeYk(Session session, MessageInfo msg) {
        
        logger.info("遥控写值请求！");
        
        if (null == session || !session.isOpen()) {
            logger.info("session null or is close！");
            messageService.sendOutputError(session, "session null or is close！");
            return;
        }
        if (null == msg.getUserId() || null == msg.getTopic() || null == msg.getWriteValue() || msg.getWriteValue().size() <= 0) {
            logger.info("user or data null！");
            messageService.sendOutputError(session, "user or data null！");
            return;
        }

        // 计时
        long start = System.currentTimeMillis();

        // 预定义给客户端的响应数据
        JSONObject sendData = new JSONObject()
            .set(Protocol.MESSAGE_TOPIC, msg.getTopic())
            .set(Protocol.MESSAGE_DATA_TYPE, Protocol.MessageDataType.Control)
            .set(Protocol.MESSAGE_REPLY_TYPE, Protocol.MessageReplyType.TimeOut);
        JSONArray sendDataArray = new JSONArray()
            .set(sendData);
        JSONObject sendmsg = new JSONObject()
            .set(Protocol.MESSAGE_DATA, sendDataArray)
            .set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_YK_WRITE);
        
        // 目前一条指令只写一个值
        Map<String, Object> writeValue =  msg.getWriteValue().get(0);
        
        // 下发遥控指令        
        JSONObject controlData = new JSONObject()
            .set(Constants.CmdTableAttr.CODE, writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE).toString())
            .set(Constants.CmdTableAttr.CONTROL_TYPE, Constants.ControlType.YK)
            .set(Constants.CmdTableAttr.CMD_TYPE, msg.getTopic())
            .set(Constants.CmdTableAttr.VALUE, Double.parseDouble(writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE).toString()));
        logger.info("清空反校表数据！");
        stringRedisTemplate.delete(Constants.CMD_REPLY_TABLE);
        logger.info("下发指令：" + controlData.toString());
        stringRedisTemplate.opsForList().rightPush(Constants.CMD_TABLE, controlData.toString());
        
        // 生成遥控告警
        this.createAlarmInfo(
            writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE),
            System.currentTimeMillis(),
            Constants.AlarmControlCmdType.valueOf(msg.getTopic()).getState(),
            writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE),
            msg.getUserId(),
            null
        );
        
        // 循环读取反校命令
        while (System.currentTimeMillis() - start < TIME_OUT_LENGTH) {
            
            // 从redis反校表pop数据
            Object object = null;
            if ((object = stringRedisTemplate.opsForList().leftPop(Constants.CMD_REPLY_TABLE, 1, TimeUnit.SECONDS)) != null) {
                
                JSONObject json = JSONUtil.parseObj(object);
                logger.info("反校信息" + json.toString());
                
                // 匹配测点id
                if (
                    json.getStr(Constants.CmdTableAttr.CODE).equals(controlData.getStr(Constants.CmdTableAttr.CODE)) &&
                    json.getStr(Constants.CmdTableAttr.CONTROL_TYPE).equals(controlData.getStr(Constants.CmdTableAttr.CONTROL_TYPE)) &&
                    json.getStr(Constants.CmdTableAttr.CMD_TYPE).equals(controlData.getStr(Constants.CmdTableAttr.CMD_TYPE))
                ) {
                    // 生成遥控反校告警
                    this.createAlarmInfo(
                        writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE),
                        System.currentTimeMillis(),
                        Constants.AlarmControlCmdType.valueOf(msg.getTopic() + "Reply").getState(),
                        writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE),
                        null,
                        json.getStr(Constants.CmdTableAttr.REPLY_RESULT)
                    );
                    
                    logger.info("反校结果：" + json.getStr(Constants.CmdTableAttr.REPLY_RESULT));
                    sendData.set(Protocol.MESSAGE_REPLY_TYPE, json.getStr(Constants.CmdTableAttr.REPLY_RESULT));
                    break;
                }
            }
        }

        logger.info("遥控响应：" + sendmsg.toString() + " session = " + session.getId());
        messageService.sendMessage(session, sendmsg.toString());
        
        // 生成遥控超时告警
        if (sendData.get(Protocol.MESSAGE_REPLY_TYPE).equals(Protocol.MessageReplyType.TimeOut.toString())) {
            this.createAlarmInfo(
                writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE),
                System.currentTimeMillis(),
                Constants.AlarmControlCmdType.valueOf(msg.getTopic()).getState(),
                writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE),
                null,
                Constants.MessageReplyType.TimeOut.toString()
            );
        }

    }

    /**
     * 遥调-写定值-请求
     * 
     * @param session
     * @param msg
     */
    public void writeYt(Session session, MessageInfo msg) {
        
        logger.info("遥调写值请求！");
        
        if (null == session || !session.isOpen()) {
            logger.info("session null or is close！");
            messageService.sendOutputError(session, "session null or is close！");
            return;
        }
        if (null == msg.getUserId() || null == msg.getTopic() || null == msg.getWriteValue() || msg.getWriteValue().size() <= 0) {
            logger.info("user or data null！");
            messageService.sendOutputError(session, "user or data null！");
            return;
        }

        // 计时
        long start = System.currentTimeMillis();

        // 预定义给客户端的响应数据
        JSONObject sendData = new JSONObject()
            .set(Protocol.MESSAGE_TOPIC, msg.getTopic())
            .set(Protocol.MESSAGE_DATA_TYPE, Protocol.MessageDataType.Control)
            .set(Protocol.MESSAGE_REPLY_TYPE, Protocol.MessageReplyType.TimeOut);
        JSONArray sendDataArray = new JSONArray()
            .set(sendData);
        JSONObject sendmsg = new JSONObject()
            .set(Protocol.MESSAGE_DATA, sendDataArray)
            .set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_YT_WRITE);
        
        // 目前一条指令只写一个值
        Map<String, Object> writeValue =  msg.getWriteValue().get(0);
        
        // 下发遥调指令        
        JSONObject controlData = new JSONObject()
            .set(Constants.CmdTableAttr.CODE, writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE).toString())
            .set(Constants.CmdTableAttr.CONTROL_TYPE, Constants.ControlType.YT)
            .set(Constants.CmdTableAttr.CMD_TYPE, msg.getTopic())
            .set(Constants.CmdTableAttr.VALUE, Double.parseDouble(writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE).toString()));
        logger.info("清空反校表数据！");
        stringRedisTemplate.delete(Constants.CMD_REPLY_TABLE);
        logger.info("下发指令：" + controlData.toString());
        stringRedisTemplate.opsForList().rightPush(Constants.CMD_TABLE, controlData.toString());
        
        // 生成遥调告警
        this.createAlarmInfo(
            writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE),
            System.currentTimeMillis(),
            Constants.AlarmControlCmdType.valueOf(msg.getTopic()).getState(),
            writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE),
            msg.getUserId(),
            null
        );
        
        // 循环读取反校命令
        while (System.currentTimeMillis() - start < TIME_OUT_LENGTH) {
            
            // 从redis反校表pop数据
            Object object = null;
            if ((object = stringRedisTemplate.opsForList().leftPop(Constants.CMD_REPLY_TABLE, 1, TimeUnit.SECONDS)) != null) {
                
                JSONObject json = JSONUtil.parseObj(object);
                logger.info("反校信息：" + json.toString());
                
                // 匹配测点id
                if (
                    json.getStr(Constants.CmdTableAttr.CODE).equals(controlData.getStr(Constants.CmdTableAttr.CODE)) &&
                    json.getStr(Constants.CmdTableAttr.CONTROL_TYPE).equals(controlData.getStr(Constants.CmdTableAttr.CONTROL_TYPE)) &&
                    json.getStr(Constants.CmdTableAttr.CMD_TYPE).equals(controlData.getStr(Constants.CmdTableAttr.CMD_TYPE))
                ) {
                    // 生成遥调反校告警
                    this.createAlarmInfo(
                        writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE),
                        System.currentTimeMillis(),
                        Constants.AlarmControlCmdType.valueOf(msg.getTopic() + "Reply").getState(),
                        writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE),
                        null,
                        json.getStr(Constants.CmdTableAttr.REPLY_RESULT)
                    );
                    
                    logger.info("反校结果：" + json.getStr(Constants.CmdTableAttr.REPLY_RESULT));
                    sendData.set(Protocol.MESSAGE_REPLY_TYPE, json.getStr(Constants.CmdTableAttr.REPLY_RESULT));
                    break;
                }
            }
        }

        logger.info("遥调响应：" + sendmsg.toString() + " session = " + session.getId());
        messageService.sendMessage(session, sendmsg.toString());
        
        // 生成遥调超时告警
        if (sendData.get(Protocol.MESSAGE_REPLY_TYPE).equals(Protocol.MessageReplyType.TimeOut.toString())) {
            this.createAlarmInfo(
                writeValue.get(Protocol.MESSAGE_WRITE_VALUE_CODE),
                System.currentTimeMillis(),
                Constants.AlarmControlCmdType.valueOf(msg.getTopic()).getState(),
                writeValue.get(Protocol.MESSAGE_WRITE_VALUE_VALUE),
                null,
                Constants.MessageReplyType.TimeOut.toString()
            );
        }

    }

    /**
     * 生成告警信息,插入redis告警表
     * @param code id
     * @param time 时间戳
     * @param alarmType 告警类型
     * @param value 告警时的值
     * @param operator 操作人id
     * @param replyResult 反校结果
     */
    private void createAlarmInfo(Object code, long time, int alarmType, Object value, Long operator, String replyResult) {
        JSONObject json = new JSONObject()
            .set(Constants.AlarmTableAttr.CODE, code)
            .set(Constants.AlarmTableAttr.TIME, time)
            .set(Constants.AlarmTableAttr.ALARMTYPE, alarmType)
            .set(Constants.AlarmTableAttr.VALUE, value)
            .set(Constants.AlarmTableAttr.OPERATOR, operator)
            .set(Constants.AlarmTableAttr.REPLYRESULT, replyResult);
        stringRedisTemplate.opsForList().rightPush(Constants.ALARM_TABLE, json.toString());
        logger.info("告警信息:" + json);
    }

}
