package com.yinhe.ec.cpps.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Org;

import java.util.List;

/**
 * 组织机构
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface OrgService {

    Integer saveAassignedStation(Long orgId, List<Long> stationIdList, Long userId);

    List<Org> getChildren(List<Org> orgList, Long id);


    List<Org> queryOrgList();

    void addOrg(Org org);


    JSONObject listAassignedAndUnAassignedStation(Long orgId, Long userId);

    ApiResult deleteOrg(Long[] ids);

    List<Org> queryOrgList(Org orgParam);

    Integer updateOrg(Org org);
}

