package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.yinhe.ec.cpps.sys.dto.OrgAassignedStationDTO;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Org;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 组织机构
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("组织机构API")
@RestController
@RequestMapping("/sys/org")
public class OrgController {
    @Autowired
    private OrgService orgService;

    /**
     * 查询组织机构列表
     */
    @ApiOperation(value = "查询组织机构列表", notes = "查询组织机构列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listOrg() {
        List<Org> orgPage = orgService.queryOrgList();
        return ApiResult.ok().put("page", orgPage);
    }

    /**
     * 根据组织id查询已分配的电站 根据当前登录用户和组织id查询未分配的电站
     */
    @ApiOperation(value = "根据组织id查询已分配的电站和根据当前登录用户和组织id查询未分配的电站", notes = "根据组织id查询已分配的电站和根据当前登录用户和组织id查询未分配的电站",
        response = ApiResult.class)
    @PostMapping("/listAassignedAndUnAassignedStation")
    public ApiResult listAassignedAndUnAassignedStation(Long orgId) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        JSONObject aassignedAndUnAassignedJsonObject =
            orgService.listAassignedAndUnAassignedStation(orgId, user.getId());
        return ApiResult.ok().put("data", aassignedAndUnAassignedJsonObject);
    }

    /**
     * 查询组织机构
     */
    @ApiOperation(value = "查询组织机构", notes = "查询组织机构", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryOrg(@RequestParam("id") Long id) {
        Org orgParam = new Org();
        orgParam.setId(id);
        List<Org> orgList = orgService.queryOrgList(orgParam);
        if (CollUtil.isNotEmpty(orgList)) {
            return ApiResult.ok().put("org", orgList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存组织机构
     */
    @ApiOperation(value = "保存组织机构", notes = "保存组织机构", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveOrg(@RequestBody Org org) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        org.setCreateBy(userTemp.getId());
        orgService.addOrg(org);
        return ApiResult.ok();
    }

    /**
     * 保存组织已分配的电站
     */
    @ApiOperation(value = "保存组织已分配的电站", notes = "保存组织已分配的电站", response = ApiResult.class)
    @PostMapping("/saveOrgAassignedStations")
    public ApiResult saveOrgAassignedStations(@RequestBody OrgAassignedStationDTO orgAassignedStationDTO) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        try {
            orgService.saveAassignedStation(orgAassignedStationDTO.getOrgId(),
                orgAassignedStationDTO.getStationIdList(), userTemp.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.error("保存失败!");
        }
        return ApiResult.ok();
    }

    /**
     * 修改组织机构
     */
    @ApiOperation(value = "修改组织机构", notes = "修改组织机构", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateOrg(@RequestBody Org org) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        org.setUpdateBy(userTemp.getId());
        org.setUpdateTime(new Date());
        orgService.updateOrg(org);

        return ApiResult.ok();
    }

    /**
     * 删除组织机构
     */
    @ApiOperation(value = "删除组织机构", notes = "删除组织机构", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteOrg(@RequestBody Long[] ids) {

        return orgService.deleteOrg(ids);
    }

}
