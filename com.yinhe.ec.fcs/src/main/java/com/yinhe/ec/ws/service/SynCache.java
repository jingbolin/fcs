package com.yinhe.ec.ws.service;

import com.yinhe.ec.ws.server.manager.OrderManager;
import com.yinhe.ec.ws.server.manager.WebSocketSessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yinhe.ec.ws.server.manager.CacheManager;

/**
 * 定时清除websocket
 * @author wangshilei
 * @date 2021/04/30
 */
@Component
public class SynCache {
    
    private final Logger logger = LogManager.getLogger();
    
    @Autowired
    private WebSocketSessionManager sessionManager;
    
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private OrderManager orderManager;
    
    @Autowired
    private AlarmMessageService alarmMessageService;
    
    @Autowired
    private CalcDataMessageService calcDataMessageService;

    /** 定时清除websocket会话信息 */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void cleanExpiredSessions() {
        logger.info("clearCache start");
        Long startTime = System.currentTimeMillis();
        sessionManager.clearSessionMap();
        cacheManager.clearCache();
        orderManager.clearOrder();
        alarmMessageService.clraeOrder();
        calcDataMessageService.clearOrder();
        Long endTime = System.currentTimeMillis();
        logger.info("clearCache end: time = " + (endTime - startTime) + "毫秒");
    }
    
}
