package com.yinhe.ec.ws.server.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.websocket.Session;

import com.yinhe.ec.cpps.Constants;
import com.yinhe.ec.ws.server.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * @author chenyh
 * @since 2018年8月23日上午11:40:44
 */
@Component
public class MessageService {
    
    Logger logger = LogManager.getLogger();
    
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * 给客户端发送消息
     * @param session
     * @param message
     */
    public synchronized void sendMessage(Session session, String message) {
        if (null != session && session.isOpen() == true) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 返回订阅的消息 {"type":"1002"}
     */
    public void sendOutputSubscribe(Session session) {

        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_SUBSCRIBE);
        this.sendMessage(session, json.toString());
    }

    /**
     * 返回取消订阅的消息 {"type":"1003"}
     */
    public void sendOutputClear(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_CLEAR);
        this.sendMessage(session, json.toString());
    }

    /**
     * 返回取消当前连接的所有订阅 {"type":"1007"}
     */
    public void sendOutputClearAll(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_CLEAR_ALL);
        this.sendMessage(session, json.toString());
    }

    /**
     * 返回心跳的消息 {"type":"1005"}
     */
    public void sendOutputHeartbeat(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_HEARTBEAT);
        this.sendMessage(session, json.toString());
    }

    /**
     * 返回连接成功的消息 {"type":"1001"}
     */
    public void sendOutputConnect(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_CONNECT);
        this.sendMessage(session, json.toString());
    }

    /**
     * 返回当前连接订阅的主题 {"type":"1006"}
     */
    public void sendOutputTopic(Session session, List<String> topic) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_TOPIC);
        json.set(Protocol.MESSAGE_TOPIC, topic);
        this.sendMessage(session, json.toString());
    }

    /**
     * 返回取消计算数据异常消息的消息 {"type":"1010"}
     */
    public void sendOutputCalcDataClear(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_CALC_DATA_CLEAR);
        this.sendMessage(session, json.toString());
    }

    /**
     * 消息类型为空
     */
    public void sendOutputTypeNull(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_MESSAGE_TYPE_NULL);
        this.sendMessage(session, json.toString());
    }

    /**
     * 消息类型错误,无法解析
     */
    public void sendOutputTypeError(Session session) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_MESSAGE_TYPE_ERROR);
        this.sendMessage(session, json.toString());
    }

    /**
     * 消息上下文错误,无法解析
     */
    public void sendOutputError(Session session, String msg) {
        JSONObject json = new JSONObject();
        json.set(Protocol.MESSAGE_MSG, msg);
        json.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_CONTENT_ERROR);
        this.sendMessage(session, json.toString());
    }

    /**
     * 订阅时首次发送测点值
     * @param session
     * @param topic
     * @param pointIds
     */
    public void firestSendRedisOrder(Session session, String topicName, String pointIds[]) {
        
        logger.info("websocket首次发送！");
        
        if (null == session || !session.isOpen() || null == topicName || null == pointIds || pointIds.length <= 0) {
            logger.info("data null！");
            return;
        }

        List<Object> hashKeys   = new ArrayList<>(Arrays.asList(pointIds));
        List<Object> hashvalues = redisTemplate.opsForHash().multiGet(Constants.REAL_TIME_TABLE, hashKeys);
        List<JSONArray> list = IntStream.range(0, hashKeys.size())
            .filter(i -> hashvalues.get(i) != null)
            .mapToObj(i -> {
                // 缓存本次的测点值
                cacheManager.putCache(session, topicName, hashKeys.get(i).toString(), hashvalues.get(i).toString());
                return new JSONArray()
                    .put(hashKeys.get(i).toString())
                    .put(hashvalues.get(i).toString())
                    .put(System.currentTimeMillis())
                    .put(1);})
            .collect(Collectors.toList());
        
        JSONObject sendData = new JSONObject();
        sendData.set(Protocol.MESSAGE_TOPIC, topicName);
        sendData.set(Protocol.MESSAGE_DATA_TYPE, Protocol.MessageDataType.Subscribe);
        sendData.set(Protocol.MESSAGE_DATA, list);

        JSONArray sendDataArray = new JSONArray();
        sendDataArray.add(sendData);

        JSONObject sendmsg = new JSONObject();
        sendmsg.set(Protocol.MESSAGE_DATA, sendDataArray);
        sendmsg.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_MESSAGE);
        this.sendMessage(session, sendmsg.toString());

        /**
         * {"data": [
         *   {"data":[
         *     ["YC10310","55.000000",1617069960004,1],
         *     ["YC10674","55.000000",1617069960006,1],
         *     ["YC4864","55.000000",1617069960007,1]
         *   ],
         *   "dataType":"Redis",
         *   "topic":"REDIS-aaaa"}
         * ],
         * "type":"1004"}
         */
        logger.info(sendmsg.toString() + " session = " + session.getId());
        
        logger.info("websocket首次发送结束！");
    
    }
    
}
