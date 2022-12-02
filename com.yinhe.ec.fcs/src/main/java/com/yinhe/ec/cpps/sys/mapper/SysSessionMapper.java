package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.SysSession;

import java.util.List;

/**
 * @author ShenHuaJie
 * @since 2018年3月3日 下午7:24:27
 */
public interface SysSessionMapper extends BaseMapper<SysSession> {

    void deleteBySessionId(String sessionId);

    Long queryBySessionId(String sessionId);

    List<String> querySessionIdByAccount(String account);
}