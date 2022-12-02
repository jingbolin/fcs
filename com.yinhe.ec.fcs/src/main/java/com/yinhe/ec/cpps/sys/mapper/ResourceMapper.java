package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询资源分页列表
     *
     * @return
     */
    List<Resource> queryResourceList(@Param("resource") Resource resource);
    
    List<String> queryResourceModuleList(@Param("account") String account);

    String getSaResourcesStr(@Param("resource") Resource resource);

    /**
     * 添加资源
     */
    Integer insertResource(@Param("resource") Resource resource);

    Integer batchDelete(Resource resource);

    Integer updateResource(Resource resource);
}
