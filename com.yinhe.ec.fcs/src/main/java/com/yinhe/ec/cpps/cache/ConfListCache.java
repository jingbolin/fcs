package com.yinhe.ec.cpps.cache;

import java.util.List;

import com.yinhe.ec.base.model.Cache;
import com.yinhe.ec.cpps.sys.model.ConfList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinhe.ec.cpps.sys.service.ConfListService;

import cn.hutool.core.collection.CollUtil;

/**
 * @author wangshilei
 * @date 2022/02/19
 */
@Component
public class ConfListCache extends Cache<ConfList> {

    @Autowired
    private ConfListService confListService;

    @Override
    public void init() {
        List<ConfList> confLists = confListService.queryConfListList(null);
        this.map = CollUtil.toMap(confLists, this.map, ConfList::getId);
    }

}
