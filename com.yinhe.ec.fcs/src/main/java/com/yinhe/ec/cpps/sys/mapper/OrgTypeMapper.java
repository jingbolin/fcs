package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.OrgType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户、群组、公司、分公司等
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface OrgTypeMapper extends BaseMapper<OrgType> {
    /**
     * 查询客户、群组、公司、分公司等分页列表
     *
     * @return
     */
    List<OrgType> queryOrgTypeList(@Param("orgType") OrgType orgType);

    void addOrgType(OrgType orgType);

    Integer  batchDelete(OrgType orgType);

    Integer updateOrgType(OrgType orgType);
}
