package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.base.BaseModel;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.cpps.sys.mapper.SysEventMapper;
import com.yinhe.ec.cpps.sys.model.SysEvent;
import com.yinhe.ec.cpps.sys.service.SysEventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志 服务层
 */
@Service
public class SysEventServiceImpl extends BaseServiceImpl<SysEvent, SysEventMapper> implements SysEventService {
    @Override
    public PageInfo<SysEvent>  selectEventlist(SysEvent sysEvent) {

        QueryWrapper<SysEvent> queryWrapper = Wrappers.query();
        //开始时间，结束时间
        DateTime startDate = null, endDate = null;
        //时间区间字符串
        String date = sysEvent.getDateInterval();
        //转换数组
        if (StringUtils.isNotEmpty(date)) {
            String[] split = date.split(",");
            startDate = DateUtil.parse(split[0], "yyyy-MM-dd");
            endDate = DateUtil.parse(split[1], "yyyy-MM-dd");
        }
        queryWrapper.lambda().like(StringUtils.isNotBlank(sysEvent.getTitle()),SysEvent::getTitle,sysEvent.getTitle()).
                like(StringUtils.isNotEmpty(sysEvent.getUserName()),SysEvent::getUserName,sysEvent.getUserName())
                .ge(startDate != null, SysEvent::getCreateTime, startDate)  //大于等于
                .lt(endDate != null, SysEvent::getCreateTime, endDate).orderByDesc(BaseModel::getCreateTime);     //小于;
        PageHelper.startPage(sysEvent.getPageNum(),sysEvent.getPageSize());
        List<SysEvent> sysEvents = mapper.selectList(queryWrapper);
        PageInfo<SysEvent> sysEventList = new PageInfo<>(sysEvents);
        return sysEventList;
    }
}
