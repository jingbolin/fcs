package com.yinhe.ec.cpps.cache;

import java.util.List;

import com.yinhe.ec.base.model.Cache;
import com.yinhe.ec.cpps.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinhe.ec.cpps.sys.service.UserService;

import cn.hutool.core.collection.CollUtil;

/**
 * @author wangshilei
 * @date 2022/01/08
 */
@Component
public class UserCache extends Cache<User> {
    
    @Autowired
    private UserService service;
    
    @Override
    public void init() {
        List<User> list = service.queryUserList(null);
        this.map = CollUtil.toMap(list, this.map, User::getId);
    }
    
}
