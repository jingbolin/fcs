package com.yinhe.ec.cpps.sys.web;

import java.util.List;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.ConfList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.service.ConfListService;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 参数配置表
 * @author zhanglei
 * @date 2020-02-19 13:40:13
 */
@Api("参数配置表API")
@RestController
@RequestMapping("/sys/conflist")
public class ConfListController {
    @Autowired
    private ConfListService confListService;

    /**
     * 查询参数配置表列表
     */
    @ApiOperation(value = "查询参数配置表列表", notes = "查询参数配置表列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listConfList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, ConfList confList) {
        PageInfo<ConfList> confListPage = confListService.queryConfListPage(pageNum, pageSize, confList);
        return ApiResult.ok().put("page", confListPage);
    }

    /**
     * 查询参数配置表
     */
    @ApiOperation(value = "查询参数配置表", notes = "查询参数配置表", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryConfList(@RequestParam("confListCode") String confListCode) {
        ConfList confListParam = new ConfList();
        confListParam.setConfListCode(confListCode);
        List<ConfList> confListList = confListService.queryConfListList(confListParam);
        ConfList confList = new ConfList();
        if (CollUtil.isNotEmpty(confListList)) {
            confList = confListList.get(0);
        }
        return ApiResult.ok().put("confList", confList);
    }

    /**
     * 修改参数配置表
     */
    @ApiOperation(value = "修改参数配置表", notes = "修改参数配置表", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateConfList(@RequestBody ConfList confList) {
        try {
            confListService.updateConfList(confList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
        return ApiResult.ok();
    }

    /**
     * 批量修改参数配置表
     * @throws Exception 
     */
    @ApiOperation(value = "批量修改参数配置表", notes = "批量修改参数配置表", response = ApiResult.class)
    @PostMapping("/updateBatch")
    public ApiResult updateConfListBatch(@RequestBody List<ConfList> confLists) throws Exception {
        for (ConfList confList : confLists) {
            confListService.updateConfList(confList);
        }
        return ApiResult.ok();
    }

}
