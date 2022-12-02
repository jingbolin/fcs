package com.yinhe.ec.ws.server.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenyh
 * @since 2018年8月18日下午2:42:11
 */
@Component
public class OrderManager {
    
    Logger logger = LogManager.getLogger();

    @Autowired
    private WebSocketSessionManager webSocketSessionManager;

    /**
     * key1:sessionId key2:主题 key3:pointId
     */
    private static Map<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>> orderData = new ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>>();

    /**
     * @return the orderData
     */
    public Map<String, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>> getOrderData() {
        return orderData;
    }

    /**
     * 添加测点订阅信息
     * 
     * @param session
     * @param topic
     * @param pointIds
     */
    public synchronized void addOrder(Session session, String topic, String pointIds[]) {
        
        logger.info("添加测点订阅信息！");
        
        if (null == session || !session.isOpen() || null == topic || null == pointIds || pointIds.length <= 0) {
            logger.info("data null！");
            return;
        }
        
        String sessionId = session.getId();
        
        long startTime = System.currentTimeMillis();

        /** 判断sessionId订单是否存在，如果不存在则添加sessionId **/
        if (!orderData.containsKey(sessionId)) {
            orderData.put(sessionId, new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>(16));
        }

        /** 判断主题是否存在，如果不存在则添加主题 **/
        if (!(orderData.get(sessionId)).containsKey(topic)) {
            orderData.get(sessionId).put(topic, new ConcurrentHashMap<String, String>(8));
        }

        ConcurrentHashMap<String, String> pointData = new ConcurrentHashMap<String, String>(128);
        for (int i = 0; i < pointIds.length; i++) {
            String id = pointIds[i];
            pointData.put(id, "1");
        }

        orderData.get(sessionId).get(topic).putAll(pointData);

        logger.info((new StringBuilder("addOrder sessionId :")).append(sessionId).append("  space:").append(System.currentTimeMillis() - startTime).append(" orderSize:").append(orderData.size()).append(" pointSize:").append(pointData.size()).append("  topic:").append(topic).toString());
    }

    /**
     * 获得当前连接订阅的所有主题
     * 
     * @param session
     */
    public List<String> getOrder(Session session) {

        List<String> topicList = new ArrayList<String>();

        /** 判断sessionId是否存在,如果不存在返回[] **/
        String sessionId = session.getId();
        if (!webSocketSessionManager.existSession(sessionId)) {
            return topicList;
        }

        /** 判断sessionId是否存在,如果不存在返回[] **/
        if (!orderData.containsKey(sessionId)) {
            return topicList;
        }

        /** 判断topic是否存在,如果不存在返回[] **/
        ConcurrentHashMap<String, ConcurrentHashMap<String, String>> topic = orderData.get(sessionId);
        if (topic == null) {
            return topicList;
        }

        for (Map.Entry<String, ConcurrentHashMap<String, String>> order : topic.entrySet()) {
            topicList.add(order.getKey());
        }
        
        return topicList;
    }

    /**
     * 取消订阅信息
     * 
     * @param session
     * @param topic
     * @param pointIds
     */
    public synchronized void clearOrder(Session session, String[] topic) {
        String sessionId = session.getId();
        /** 判断sessionId是否存在,如果不存在无法退订,返回 **/
        if (!orderData.containsKey(sessionId)) {
            return;
        }
        
        if (Objects.nonNull(topic)) {
            /** 清除当前用户、订阅的主题 **/
            Arrays.asList(topic).forEach(orderData.get(sessionId)::remove);
        }
    }

    /**
     * 用户掉线,清理订单
     * @param session
     * @param topic
     * @param pointIds
     * @throws Exception
     */
    public synchronized void clearOrder(String sessionId) {
        /** 判断sessionId是否存在,如果不存在无法退订,返回 **/
        if (orderData.containsKey(sessionId)) {
            orderData.remove(sessionId);
            return;
        }
    }

    /**
     * 清理已关闭的session订单
     */
    public synchronized void clearOrder() {
        orderData.keySet().stream()
            .filter(e -> !webSocketSessionManager.existSession(e))
            .forEach(orderData::remove);
    }

}
