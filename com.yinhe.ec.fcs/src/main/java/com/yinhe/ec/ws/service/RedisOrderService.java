package com.yinhe.ec.ws.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.websocket.Session;

import com.yinhe.ec.cpps.Constants;
import com.yinhe.ec.ws.server.manager.OrderManager;
import com.yinhe.ec.ws.server.manager.WebSocketSessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yinhe.ec.ws.server.Protocol;
import com.yinhe.ec.ws.server.manager.CacheManager;
import com.yinhe.ec.ws.server.manager.MessageService;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * @author chenyh
 * @since 2018年9月3日上午10:23:15
 */
@Component
public class RedisOrderService {
    
    public final static String REDIS_ORDER_STARTS_WITH = "";

    private final Logger logger = LogManager.getLogger();

    @Autowired
    private WebSocketSessionManager sessionManager;
    
    @Autowired
    private MessageService messageService;

    @Autowired
    private OrderManager orderManager;
    
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * 订阅的测点数据, 每隔1秒从Redis读取数据, 发送给客户端
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void sendRedisOrder() {
        
        logger.debug("websocket开始了！");
        
        Map<String, Session> sessionMap = sessionManager.getSessionMap();
        if (null == sessionMap || sessionMap.isEmpty()) {
            return;
        }

        Map<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>> orderData = orderManager.getOrderData();
        if (null == orderData || orderData.isEmpty()) {
            return;
        }

        /**
         * 发送数据
         */
        for (Map.Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
            Session session = sessionEntry.getValue();
            if (null == session || !session.isOpen() || null == session.getId() || orderData.get(session.getId()) == null) {
                continue;
            }

            for (Map.Entry<String, ConcurrentHashMap<String, String>> topic : orderData.get(session.getId()).entrySet()) {
                String topicName = topic.getKey();
                if (null != topicName && topicName.startsWith(RedisOrderService.REDIS_ORDER_STARTS_WITH)) {
                    try {
                        logger.debug("sessionId = " + session.getId() + " and topic = " + topicName + " Receive!");
                        List<Object> hashKeys   = new ArrayList<>(topic.getValue().keySet());
                        List<Object> hashvalues = redisTemplate.opsForHash().multiGet(Constants.REAL_TIME_TABLE, hashKeys);
                        List<JSONArray> list = IntStream.range(0, hashKeys.size())
                            .filter(i -> hashvalues.get(i) != null)
                            .mapToObj(i -> {
                                // 如果和上一次的缓存的测点值值相等，跳过，不发送
                                if (Objects.equals(cacheManager.getCache(session, topicName, hashKeys.get(i).toString()), hashvalues.get(i).toString())) {
                                    return null;
                                }
                                // 缓存本次的测点值
                                cacheManager.putCache(session, topicName, hashKeys.get(i).toString(), hashvalues.get(i).toString());
                                return new JSONArray()
                                    .put(hashKeys.get(i).toString())
                                    .put(hashvalues.get(i).toString())
                                    .put(System.currentTimeMillis())
                                    .put(1);
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                        
                        // 如果没有新数据，不发送ws消息
                        if (list.isEmpty()) {
                            logger.debug("sessionId = " + session.getId() + " and topic = " + topicName + " not data change!");
                            continue;
                        }

                        JSONObject sendData = new JSONObject();
                        sendData.set(Protocol.MESSAGE_TOPIC, topicName);
                        sendData.set(Protocol.MESSAGE_DATA_TYPE, Protocol.MessageDataType.Subscribe);
                        sendData.set(Protocol.MESSAGE_DATA, list);

                        JSONArray sendDataArray = new JSONArray();
                        sendDataArray.add(sendData);

                        JSONObject sendmsg = new JSONObject();
                        sendmsg.set(Protocol.MESSAGE_DATA, sendDataArray);
                        sendmsg.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_MESSAGE);
                        messageService.sendMessage(session, sendmsg.toString());

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
                        logger.debug(sendmsg.toString() + " session = " + session.getId());
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
        
        logger.debug("websocket结束了！");
        
    }
    
}
