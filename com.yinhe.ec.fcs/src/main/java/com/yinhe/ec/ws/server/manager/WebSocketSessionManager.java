package com.yinhe.ec.ws.server.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 单例模式,管理连接session信息
 * 
 * @author chenyh
 * @since 2018年8月15日下午3:26:47
 */
@Component
public class WebSocketSessionManager {
    Logger logger = LogManager.getLogger();

    /**
     * session缓存信息 key:sessionId,value:session
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    public Session getSession(String id) {
        return sessionMap.get(id);
    }

    public Map<String, Session> getSessionMap() {
        return sessionMap;
    }

    /** 添加新session */
    public void setSession(String id, Session session) {
        sessionMap.put(id, session);
        logger.info("sessionMap size = " + sessionMap.size());
    }

    /** 清理session连接 */
    public void removeSession(String id) {
        if (!sessionMap.isEmpty() && sessionMap.containsKey(id)) {
            sessionMap.remove(id);
        }
        logger.info("session remove, id = " + id);
    }

    /** 判断session连接是否存在 */
    public boolean existSession(String sessionId) {
        if (null == sessionId) {
            return false;
        }
        if (sessionMap.containsKey(sessionId)) {
            Session session = sessionMap.get(sessionId);
            if (null != session) {
                return true;
            }
        }
        return false;
    }

    /** 清理已关闭的session */
    public synchronized void clearSessionMap() {
        sessionMap.forEach((k,v) -> {
            if (!v.isOpen()) {
                this.removeSession(k);
            }
        });
        logger.info("sessionMap size = " + sessionMap.size());
    }
}
