package com.yinhe.ec.cpps.sys.service;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.ResourceGroup;

import java.util.List;

/**
 * 资源分组
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface ResourceGroupService {

    List<ResourceGroup> queryResourceGroupList(ResourceGroup resourceGroup);

    void addResourceGroup(ResourceGroup resourceGroup);

    void updateResourceGroup(ResourceGroup resourceGroup);

    ApiResult deleteResourceGroupList(Long[] ids);

    List<ResourceGroup> queryPermissionGroupList(ResourceGroup resourceGroup);
}

