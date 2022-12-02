package com.yinhe.ec.cpps.sys.service;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.OrgType;

import java.util.List;

/**
 * 客户、群组、公司、分公司等
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface OrgTypeService {

    PageInfo<OrgType> queryOrgTypePage(Integer pageNum, Integer pageSize, OrgType orgType);

    void addOrgType(OrgType orgType);

    ApiResult deleteOrgType(Long[] ids);

    List<OrgType> queryOrgTypeList(OrgType orgTypeParam);

    Integer updateOrgType(OrgType orgType);
}

