package com.yinhe.ec.cpps.sys.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.base.AbstractController;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Resource;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 资源
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("资源API")
@RestController
@RequestMapping("/sys/resource")
public class ResourceController extends AbstractController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 查询资源列表
     */
    @ApiOperation(value = "查询资源列表", notes = "查询资源列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listResource(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, Resource resource) {
        getCurrUser();
        PageInfo<Resource> resourcePage = resourceService.queryResourcePage(pageNum, pageSize, resource);
        return ApiResult.ok().put("page", resourcePage);
    }

    /**
     * 查询已分配和未分配资源列表
     */
    @ApiOperation(value = "查询已分配和未分配资源列表", notes = "查询已分配和未分配资源列表", response = ApiResult.class)
    @PostMapping("/listAassignedAndUnAassignedRes")
    public ApiResult listAassignedAndUnAassignedRes(Long resourceGroupId, Long permissionId) {
        JSONObject aassignedAndUnAassignedJsonObject =
            resourceService.listAassignedAndUnAassigned(resourceGroupId, permissionId);
        return ApiResult.ok().put("data", aassignedAndUnAassignedJsonObject);
    }

    /**
     * 查询资源
     */
    @ApiOperation(value = "查询资源", notes = "查询资源", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryResource(@RequestParam("id") Long id) {
        Resource resource = resourceService.queryResourceById(id);
        return ApiResult.ok().put("resource", resource);
    }

    /**
     * 保存资源
     */
    @ApiOperation(value = "保存资源", notes = "保存资源", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveResource(@RequestBody Resource resource) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        resource.setCreateBy(userTemp.getId());
        resourceService.insertResource(resource);
        return ApiResult.ok();
    }

    /**
     * 修改资源
     */
    @ApiOperation(value = "修改资源", notes = "修改资源", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateResource(@RequestBody Resource resource) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        resource.setUpdateBy(userTemp.getId());
        resource.setUpdateTime(new Date());
        resourceService.updateResource(resource);
        return ApiResult.ok();
    }

    /**
     * 删除资源
     */
    @ApiOperation(value = "删除资源", notes = "删除资源", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteResource(@RequestBody Long[] ids) {
        return resourceService.deleteResourceList(ids);
    }

}
