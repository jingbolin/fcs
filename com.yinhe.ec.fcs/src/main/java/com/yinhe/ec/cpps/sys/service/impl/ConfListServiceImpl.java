package com.yinhe.ec.cpps.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.support.cache.RedisHelper;
import com.yinhe.ec.core.util.ConfigEnum;
import com.yinhe.ec.cpps.cache.ConfListCache;
import com.yinhe.ec.cpps.sys.mapper.ConfListMapper;
import com.yinhe.ec.cpps.sys.model.ConfList;
import com.yinhe.ec.cpps.sys.service.ConfListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 参数配置表
 * @author zhanglei
 * @date 2020-02-19 13:40:13
 */

@Service
public class ConfListServiceImpl extends BaseServiceImpl<ConfList, ConfListMapper> implements ConfListService {
    @Autowired
    private ConfListMapper confListMapper;
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private ConfListCache confListCache;

    @Override
    public PageInfo<ConfList> queryConfListPage(Integer pageNum, Integer pageSize, ConfList confList) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConfList> confListListPage = confListMapper.queryConfListList(confList);
        return new PageInfo<ConfList>(confListListPage);
    }

    @Override
    public List<ConfList> queryConfListList(ConfList confList) {
        return confListMapper.queryConfListList(confList);
    }

    @Override
    public void updateConfList(ConfList confList) throws Exception {
        if (null != confList && StringUtils.isNoneEmpty(confList.getConfListCode(), confList.getConfListValue())) {
            if (confList.getConfListCode().equals(ConfigEnum.SESSION_TIMEOUT.name())) {
                int timeOut = 30;
                try {
                    timeOut = Long.valueOf(TimeUnit.MINUTES.toSeconds(Integer.parseInt(confList.getConfListValue()))).intValue();
                } catch (NumberFormatException e) {
                    throw new Exception("参数格式有误,请重新输入!");
                }
                redisHelper.hset(Constants.REDIS_SHIRO_SESSION_PROP, "SessionTimeOut", timeOut);
            }
            confListMapper.updateConfList(confList);
            confListCache.update(confList);
        }
    }
}
