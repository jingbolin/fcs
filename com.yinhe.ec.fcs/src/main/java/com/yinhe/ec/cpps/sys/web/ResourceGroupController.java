package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.ResourceGroup;
import com.yinhe.ec.cpps.sys.service.ResourceGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源分组
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("资源分组API")
@RestController
@RequestMapping("/sys/resourcegroup")
public class ResourceGroupController {
    @Autowired
    private ResourceGroupService resourceGroupService;

    /**
     * 查询资源分组列表
     */
    @ApiOperation(value = "查询资源分组列表", notes = "查询资源分组列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listResourceGroup(ResourceGroup resourceGroup) {
        return ApiResult.ok().put("page", resourceGroupService.queryResourceGroupList(resourceGroup));
    }

    /**
     * 查询资源分组
     */
    @ApiOperation(value = "查询资源分组", notes = "查询资源分组", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryResourceGroup(@RequestParam("id") Long id) {
        ResourceGroup resourceGroup = new ResourceGroup();
        resourceGroup.setId(id);
        List<ResourceGroup> resourceGroupList = resourceGroupService.queryPermissionGroupList(resourceGroup);
        if (CollUtil.isNotEmpty(resourceGroupList)) {
            return ApiResult.ok().put("resourceGroup", resourceGroupList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存资源分组
     */
    @ApiOperation(value = "保存资源分组", notes = "保存资源分组", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveResourceGroup(@RequestBody ResourceGroup resourceGroup) {
        resourceGroupService.addResourceGroup(resourceGroup);
        return ApiResult.ok();
    }

    /**
     * 修改资源分组
     */
    @ApiOperation(value = "修改资源分组", notes = "修改资源分组", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateResourceGroup(@RequestBody ResourceGroup resourceGroup) {
        resourceGroupService.updateResourceGroup(resourceGroup);
        return ApiResult.ok();
    }

    /**
     * 删除资源分组
     */
    @ApiOperation(value = "删除资源分组", notes = "删除资源分组", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteResourceGroup(@RequestBody Long[] ids) {
        return resourceGroupService.deleteResourceGroupList(ids);

    }

}
