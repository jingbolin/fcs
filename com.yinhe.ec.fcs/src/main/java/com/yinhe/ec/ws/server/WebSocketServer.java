package com.yinhe.ec.ws.server;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.yinhe.ec.ws.config.EndpointConfigure;
import com.yinhe.ec.ws.server.manager.CacheManager;
import com.yinhe.ec.ws.server.manager.MessageService;
import com.yinhe.ec.ws.server.manager.OrderManager;
import com.yinhe.ec.ws.server.manager.WebSocketSessionManager;
import com.yinhe.ec.ws.server.manager.vo.MessageInfo;
import com.yinhe.ec.ws.service.AlarmMessageService;
import com.yinhe.ec.ws.service.CalcDataMessageService;
import com.yinhe.ec.ws.service.ControlMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author chenyh
 * @since 2018年8月9日上午11:20:57
 */
@Component
@ServerEndpoint(value = "/ws", configurator = EndpointConfigure.class)
public class WebSocketServer {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private WebSocketSessionManager sessionManager;

    @Autowired
    private OrderManager orderManager;
    
    @Autowired
    private CacheManager cacheManager;
    
    @Autowired
    private AlarmMessageService alarmMessageService;
    
    @Autowired
    private CalcDataMessageService calcDataMessageService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ControlMessageService controlMessageService;

    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;

        // 设置websocket最大长度
        session.setMaxTextMessageBufferSize(10 * 1024 * 1024);
        session.setMaxBinaryMessageBufferSize(10 * 1024 * 1024);

        // 将连接的session信息保存至session管理器
        sessionManager.setSession(session.getId(), session);
        
        // 将连接的session信息保存至cache管理器
        cacheManager.setSession(session);

        // 消息服务返回连接成功的消息
        messageService.sendOutputConnect(session);
    }

    /**
     * 连接关闭调用的方法 连接关闭后，不需要返回消息，页面会自动调用onclose方法
     */
    @OnClose
    public void onClose() {
        if (!this.session.isOpen()) {
            sessionManager.removeSession(this.session.getId());
            orderManager.clearOrder(this.session.getId());
        }
    }

    /**
     * web或手机端发送过来的消息 收到客户端消息后调用的方法
     * 
     * @param message
     * @throws InterruptedException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws InterruptedException {
        
        logger.info("收到信息:" + message + "sessionId = " + session.getId());

        MessageInfo msg = this.parseMessage(message);
        
        if (null == msg.getType()) {
            logger.error("消息类型为空！");
            messageService.sendOutputTypeNull(session);
        }
        switch (msg.getType()) {
            case Protocol.TYPE_INPUT_SUBSCRIBE:
                orderManager.addOrder(session, msg.getTopic(), msg.getOrder());
                messageService.sendOutputSubscribe(session);
                messageService.firestSendRedisOrder(session, msg.getTopic(), msg.getOrder());
                break;
            case Protocol.TYPE_INPUT_CLEAR:
                orderManager.clearOrder(session, msg.getClear());
                cacheManager.delCache(session, msg.getClear());
                messageService.sendOutputClear(session);
                break;
            case Protocol.TYPE_INPUT_CLEAR_ALL:
                orderManager.clearOrder(session.getId());
                cacheManager.delCache(session);
                messageService.sendOutputClearAll(session);
                break;
            case Protocol.TYPE_INPUT_HEARTBEAT:
                messageService.sendOutputHeartbeat(session);
                break;
            case Protocol.TYPE_INPUT_TOPIC:
                List<String> topic = orderManager.getOrder(session);
                messageService.sendOutputTopic(session, topic);
                break;
            case Protocol.TYPE_INPUT_ALARM:
                alarmMessageService.addOrder(session, msg.getTopic());
                break;
            case Protocol.TYPE_INPUT_CALC_DATA:
                calcDataMessageService.addOrder(session, msg.getTopic());
                break;
            case Protocol.TYPE_INPUT_CALC_DATA_CLEAR:
                calcDataMessageService.clearOrder(session, msg.getClear());
                messageService.sendOutputCalcDataClear(session);
                break;
            case Protocol.TYPE_INPUT_YK_WRITE:
                controlMessageService.writeYk(session, msg);
                break;
            case Protocol.TYPE_INPUT_YT_WRITE:
                controlMessageService.writeYt(session, msg);
                break;
            default:
                logger.error("无法识别消息类型,type = " + msg.getType());
                messageService.sendOutputTypeError(session);
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable e) {
        logger.error(e.getMessage(), e);
    }

    /**
     * 字符串消息解析
     * 
     * @param message
     * @return
     */
    private MessageInfo parseMessage(String message) {
        MessageInfo msg = new MessageInfo();
        JSONObject json = JSONUtil.parseObj(message);

        String type = (String)json.get(Protocol.MESSAGE_TYPE);
        msg.setType(type);

        String topic = json.getStr(Protocol.MESSAGE_TOPIC);
        msg.setTopic(topic);

        JSONArray clearJson = json.getJSONArray(Protocol.MESSAGE_CLEAR);
        if (Objects.nonNull(clearJson)) {
            String[] clear = clearJson.stream().map(StrUtil::toString).toArray(String[]::new);
            msg.setClear(clear);
        }

        JSONArray orderJson = json.getJSONArray(Protocol.MESSAGE_OEDER);
        if (Objects.nonNull(orderJson)) {
            String[] order = orderJson.stream().map(StrUtil::toString).toArray(String[]::new);
            msg.setOrder(order);
        }

        Long userId = json.getLong(Protocol.MESSAGE_USER_ID);
        msg.setUserId(userId);

        JSONArray writeValueJson = json.getJSONArray(Protocol.MESSAGE_WRITE_VALUE);
        if (Objects.nonNull(writeValueJson)) {
            List<Map<String, Object>> writeValue = writeValueJson.stream().map(JSONUtil::parseObj).collect(Collectors.toList());
            msg.setWriteValue(writeValue);
        }
        
        JSONObject readValue = json.getJSONObject(Protocol.MESSAGE_READ_VALUE);
        msg.setReadValue(readValue);

        return msg;
    }
}
