package com.yinhe.ec.cpps.sys.service;

public interface SysCacheService {
    /**
     * 清缓存
     */
    void flush();

    /**
     * 清缓存
     */
    void flush(String key);
}
