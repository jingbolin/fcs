package com.yinhe.ec.cpps.sys.service;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.base.BaseService;
import com.yinhe.ec.cpps.sys.model.SysEvent;

/**
 * @author ShenHuaJie
 * @since 2018年4月24日 上午10:59:04
 */
public interface SysEventService extends BaseService<SysEvent> {

    PageInfo<SysEvent>  selectEventlist(SysEvent sysEvent);

}
