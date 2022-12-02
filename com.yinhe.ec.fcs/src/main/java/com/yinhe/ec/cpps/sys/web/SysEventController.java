package com.yinhe.ec.cpps.sys.web;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.SysEvent;
import com.yinhe.ec.cpps.sys.service.SysEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理
 * @author
 * @date 2021-02-19 13:40:13
 */
@Api("日志管理API")
@RestController
@RequestMapping("/sys/sysEvent")
public class SysEventController {
    @Autowired
    private SysEventService sysEventService;

    /**
     * 查看日志列表
     */
    @ApiOperation(value = "查看日志列表", notes = "查看日志列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listSysEvent(@RequestBody SysEvent sysEvent) {
        PageInfo<SysEvent> sysEventList = sysEventService.selectEventlist(sysEvent);
        return ApiResult.ok().put("sysEventList", sysEventList);
    }
}
