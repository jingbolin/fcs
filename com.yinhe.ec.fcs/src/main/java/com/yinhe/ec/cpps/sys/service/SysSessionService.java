/**
 *
 */
package com.yinhe.ec.cpps.sys.service;

import com.yinhe.ec.core.base.BaseService;
import com.yinhe.ec.cpps.sys.model.SysSession;

import java.util.List;

/**
 * @author ShenHuaJie
 * @version 2016年5月15日 上午11:21:21
 */
public interface SysSessionService extends BaseService<SysSession> {
    void deleteBySessionId(final SysSession sysSession);

    List<String> querySessionIdByAccount(SysSession sysSession);

    void cleanExpiredSessions();
}
