package com.yinhe.ec.ws.service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinhe.ec.ws.server.Protocol;
import com.yinhe.ec.ws.server.manager.MessageService;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * 告警消息服务
 * @author wangshilei
 * @date 2021/04/27
 */
@Component
public class AlarmMessageService {
    
    Logger logger = LogManager.getLogger();
    
    @Autowired
    private MessageService messageService;
    
    /** 告警订阅订单 */
    private static Map<Session, String> alarmMap = new ConcurrentHashMap<Session, String>();

    /**
     * 推送告警信息
     * @param alarmInfo 告警json字符串
     */
//    public void sendMessage(HisAlarmInfo... hisAlarmInfos) {
//        // 拼接前台需要的数据格式
//        JSONArray alarmArr = new JSONArray();
//        Arrays.stream(hisAlarmInfos).forEach(alarmArr::add);
//        JSONObject data = new JSONObject()
//            .set(Protocol.MESSAGE_TOPIC, null)
//            .set(Protocol.MESSAGE_DATA_TYPE, Protocol.MessageDataType.Alarm)
//            .set(Protocol.MESSAGE_DATA, alarmArr);
//        JSONArray datas = new JSONArray()
//            .set(data);
//        JSONObject msg = new JSONObject()
//            .set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_ALARM)
//            .set(Protocol.MESSAGE_DATA, datas);
//
//        // 推送告警信息
//        alarmMap.keySet()
//            .stream()
//            .filter(Session::isOpen)
//            .forEach(e -> {
//                data.set(Protocol.MESSAGE_TOPIC, alarmMap.get(e));
//                messageService.sendMessage(e, msg.toString());
//            });
//        logger.info("推送告警信息给前端：" + msg.toString());
//    }
    
    /**
     * 添加告警订阅信息
     * @param session
     * @param topic
     */
    public void addOrder(Session session, String topic) {
        
        logger.info("添加告警订阅信息！");
        
        if (null == session || !session.isOpen() || null == topic ) {
            messageService.sendOutputError(session, "data null！");
            logger.info("data null！");
            return;
        }
        
        alarmMap.put(session, topic);
        
        logger.info(session.getId() + ":" + topic);
        
    }

    /**
     * 清理已关闭的session订单
     */
    public void clraeOrder() {
        
        alarmMap.keySet().stream().filter(e -> !e.isOpen()).forEach(alarmMap::remove);
        
    }

}
