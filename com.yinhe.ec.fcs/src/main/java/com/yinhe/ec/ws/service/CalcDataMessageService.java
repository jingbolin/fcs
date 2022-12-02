package com.yinhe.ec.ws.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import com.yinhe.ec.cpps.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yinhe.ec.ws.server.Protocol;
import com.yinhe.ec.ws.server.manager.CacheManager;
import com.yinhe.ec.ws.server.manager.MessageService;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * 计算数据消息服务
 * @author wangshilei
 * @date 2021/04/27
 */
@Component
public class CalcDataMessageService {
    
    Logger logger = LogManager.getLogger();
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;
    
    /** topic -> RedisHashKey */
    private static Map<String, String> topicRedisHashKeyMap = new ConcurrentHashMap<String, String>();

    /** 计算数据订阅订单 */
    private static Map<Session, List<String>> seriesTopicMap = new ConcurrentHashMap<Session, List<String>>();
    
    public CalcDataMessageService() {
        topicRedisHashKeyMap.put("seriesAbnormalMonitor", Constants.SERIES_ABNORMAL_MONITOR);
        topicRedisHashKeyMap.put("equivalentTimeTop", Constants.EQUIVALENT_TIME_TOP);
        topicRedisHashKeyMap.put("equivalentTimeLast", Constants.EQUIVALENT_TIME_LAST);
    }

    /**
     * 推送计算数据信息, 每隔1秒从Redis读取数据, 发送给客户端
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void sendSeriesAbnormalOrder() {
        // 推送计算数据信息
        seriesTopicMap.keySet()
            .stream()
            .filter(Session::isOpen)
            .forEach(session -> {
                // 遍历topic
                List<String> topicList = seriesTopicMap.get(session);
                topicList.forEach(topic -> {
                    // 查询计算数据数据
                    Object result = redisTemplate.opsForHash().get(Constants.CALC_DATA, topicRedisHashKeyMap.get(topic));
                    String md5 = DigestUtil.md5Hex(StrUtil.toString(result));
                    // 如果和上一次的缓存的数据相同，跳过，不发送
                    if (Objects.equals(cacheManager.getCache(session, topic, topic), md5)) {
                        return;
                    }
                    // 缓存本次的数据
                    cacheManager.putCache(session, topic, topic, md5);
                    
                    // 拼装返回的数据
                    JSONObject sendData = new JSONObject();
                    sendData.set(Protocol.MESSAGE_TOPIC, topic);
                    sendData.set(Protocol.MESSAGE_DATA_TYPE, Protocol.MessageDataType.CalcData);
                    sendData.set(Protocol.MESSAGE_DATA, result);
                    
                    JSONArray sendDataArray = new JSONArray();
                    sendDataArray.add(sendData);
                    
                    JSONObject sendmsg = new JSONObject();
                    sendmsg.set(Protocol.MESSAGE_DATA, sendDataArray);
                    sendmsg.set(Protocol.MESSAGE_TYPE, Protocol.TYPE_OUTPUT_CALC_DATA);
                    messageService.sendMessage(session, sendmsg.toString());
                    
                    logger.debug("推送计算数据信息给前端：" + sendmsg.toString());
                });
            });
    }
    
    /**
     * 添加计算数据订阅信息
     * @param session
     * @param topic
     */
    public void addOrder(Session session, String topic) {
        
        logger.info("添加计算数据订阅信息！");
        
        if (null == session || !session.isOpen() || null == topic ) {
            messageService.sendOutputError(session, "data null！");
            logger.info("data null！");
            return;
        }
        
        cacheManager.delCache(session, topic, topic);
        
        if (!seriesTopicMap.containsKey(session)) {
            seriesTopicMap.put(session, new ArrayList<String>());
        }
        
        seriesTopicMap.get(session).add(topic);
        
        logger.info(session.getId() + ":" + topic);
        
    }

    /**
     * 清理已关闭的session订单
     */
    public void clearOrder() {
        
        seriesTopicMap.keySet().stream().filter(e -> !e.isOpen()).forEach(seriesTopicMap::remove);
        
    }   
    
    /**
     * 取消计算数据订阅信息
     * 
     * @param session
     * @param topic
     * @param pointIds
     */
    public synchronized void clearOrder(Session session, String[] topics) {
        if (Objects.nonNull(topics)) {
            /** 清除当前用户、订阅的主题 **/
            Arrays.asList(topics).forEach(topic -> {
                seriesTopicMap.getOrDefault(session, Collections.emptyList()).remove(topic);
                cacheManager.delCache(session, topic, topic);
            });
        }
    }

}
