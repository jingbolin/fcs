package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.ResourceGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源分组
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface ResourceGroupMapper extends BaseMapper<ResourceGroup> {
    /**
     * 查询资源分组分页列表
     *
     * @return
     */
    List<ResourceGroup> queryResourceGroupList(@Param("resourceGroup") ResourceGroup resourceGroup);

    void addResourceGroup(@Param("resourceGroup") ResourceGroup resourceGroup);
    /**
     * 修改资源分组
     * @return
     */
    Integer updateResourceGroup(@Param("resourceGroup") ResourceGroup resourceGroup);

    Integer batchDelete(ResourceGroup resourceGroup);
}
