package com.yinhe.ec.cpps.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Resource;

import java.util.List;

/**
 * 资源
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface ResourceService {

    PageInfo<Resource> queryResourcePage(Integer pageNum, Integer pageSize, Resource resource);

    void insertResource(Resource resource);

    JSONObject listAassignedAndUnAassigned(Long resourceGroupId, Long permissionId);

    List<String> queryResourceModuleList(String account);

    List<String> queryResourceStrList(Resource resource);

    ApiResult deleteResourceList(Long[] ids);

    Integer updateResource(Resource resource);

    Resource queryResourceById(Long id);
}

