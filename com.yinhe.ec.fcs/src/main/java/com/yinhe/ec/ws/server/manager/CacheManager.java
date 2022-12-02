package com.yinhe.ec.ws.server.manager;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.springframework.stereotype.Component;

import cn.hutool.core.map.MapUtil;

/**
 * 缓存管理，缓存上一个给页面的测点值
 * @author wangshilei
 * @date 2021/07/01
 */
@Component
public class CacheManager {
    
    private ConcurrentHashMap<Session, ConcurrentHashMap<String, ConcurrentHashMap<String, String>>> prevRealTimeValueCache = MapUtil.newConcurrentHashMap();
    
    public void setSession(Session session) {
        // 首次把Session加入集合中
        this.prevRealTimeValueCache.put(session, new ConcurrentHashMap<>());
    }

    public void putCache(Session session, String topic, String key, String value) {
        // 如果Session不存在集合中
        if (!this.prevRealTimeValueCache.containsKey(session)) {
            this.prevRealTimeValueCache.put(session, MapUtil.newConcurrentHashMap());
        }
        // 如果当前Session下topic不存在
        if (!this.prevRealTimeValueCache.get(session).containsKey(topic)) {
            this.prevRealTimeValueCache.get(session).put(topic, MapUtil.newConcurrentHashMap());
        }
        // 记录上一次的值
        this.prevRealTimeValueCache.get(session).get(topic).put(key, value);
    }
    
    public String getCache(Session session, String topic, String key) {
        return this.prevRealTimeValueCache.containsKey(session) && 
                this.prevRealTimeValueCache.get(session).containsKey(topic)
                ? this.prevRealTimeValueCache.get(session).get(topic).get(key) : null;
    }

    public void delCache(Session session) {
        this.prevRealTimeValueCache.remove(session);
    }

    public void delCache(Session session, String[] topic) {
        if (this.prevRealTimeValueCache.get(session) != null) {
            Arrays.asList(topic).forEach(topi -> {
                this.prevRealTimeValueCache.get(session).remove(topi);
            });
        }
    }
    
    public void delCache(Session session, String topic, String key) {
       if (this.prevRealTimeValueCache.get(session) != null) {
           this.prevRealTimeValueCache.get(session).remove(key);
       }
    }

    /**
     * 清理已关闭的session订单
     */
    public synchronized void clearCache() {
        prevRealTimeValueCache.keySet().stream().filter(e -> !e.isOpen()).forEach(prevRealTimeValueCache::remove);
    }

}
