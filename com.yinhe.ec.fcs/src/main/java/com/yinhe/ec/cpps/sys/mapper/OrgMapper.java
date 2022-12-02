package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.core.base.BaseMapper;
import com.yinhe.ec.cpps.sys.model.Org;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 组织机构
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface OrgMapper extends BaseMapper<Org> {
    /**
     * 查询组织机构分页列表
     *
     * @return
     */
    List<Org> queryOrgList(@Param("org") Org org);

    /**
     * 根据组织id查询已分配电站
     * 或者根据组织id和当前登录用户id查询未分配电站
     * @param org
     * @return
     */
    List<Map<String,Object>> queryStationList(@Param("org") Org org);

    Integer addOrg(@Param("org") Org org);

    Integer deleteOrgPsr(@Param("orgId") Long orgId, @Param("psrId") Long psrId);

    Integer batchSaveOrgPsr(@Param("stationIdList") List<Long> stationIdList, @Param("orgId") Long orgId, @Param("createBy") Long createBy);

    Integer updateOrg(Org org);

}
