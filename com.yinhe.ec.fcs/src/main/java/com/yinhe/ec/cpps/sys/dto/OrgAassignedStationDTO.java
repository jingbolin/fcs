package com.yinhe.ec.cpps.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel(value = "电站组织DTO", description = "电站组织DTO")
public class OrgAassignedStationDTO {
    @ApiModelProperty(value = "电站id集合")
    private List<Long> stationIdList;
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    public List<Long> getStationIdList() {
        return stationIdList;
    }

    public void setStationIdList(List<Long> stationIdList) {
        this.stationIdList = stationIdList;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
