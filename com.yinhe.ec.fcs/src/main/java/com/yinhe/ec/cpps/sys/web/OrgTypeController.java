package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.OrgType;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.OrgTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 客户、群组、公司、分公司等
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("客户、群组、公司、分公司等API")
@RestController
@RequestMapping("/sys/aorgtype")
public class OrgTypeController {
    @Autowired
    private OrgTypeService orgTypeService;

    /**
     * 查询客户、群组、公司、分公司等列表
     */
    @ApiOperation(value = "查询客户、群组、公司、分公司等列表", notes = "查询客户、群组、公司、分公司等列表", response = ApiResult.class)
    @PostMapping("/list")
    public ApiResult listOrgType(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, OrgType orgType) {
        PageInfo<OrgType> orgTypePage = orgTypeService.queryOrgTypePage(pageNum, pageSize, orgType);
        return ApiResult.ok().put("page", orgTypePage);
    }

    /**
     * 查询客户、群组、公司、分公司等
     */
    @ApiOperation(value = "查询客户、群组、公司、分公司等", notes = "查询客户、群组、公司、分公司等", response = ApiResult.class)
    @PostMapping("/query")
    public ApiResult queryOrgType(@RequestParam("id") Long id) {
        OrgType orgTypeParam = new OrgType();
        orgTypeParam.setId(id);
        List<OrgType> orgTypeList = orgTypeService.queryOrgTypeList(orgTypeParam);
        if (CollUtil.isNotEmpty(orgTypeList)) {
            return ApiResult.ok().put("orgType", orgTypeList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存客户、群组、公司、分公司等
     */
    @ApiOperation(value = "保存客户、群组、公司、分公司等", notes = "保存客户、群组、公司、分公司等", response = ApiResult.class)
    @PostMapping("/save")
    public ApiResult saveOrgType(@RequestBody OrgType orgType) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        orgType.setCreateBy(userTemp.getId());
        orgTypeService.addOrgType(orgType);
        return ApiResult.ok();
    }

    /**
     * 修改客户、群组、公司、分公司等
     */
    @ApiOperation(value = "修改客户、群组、公司、分公司等", notes = "修改客户、群组、公司、分公司等", response = ApiResult.class)
    @PostMapping("/update")
    public ApiResult updateOrgType(@RequestBody OrgType orgType) {
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        orgType.setUpdateBy(userTemp.getId());
        orgType.setUpdateTime(new Date());
        orgTypeService.updateOrgType(orgType);
        return ApiResult.ok();
    }

    /**
     * 删除客户、群组、公司、分公司等
     */
    @ApiOperation(value = "删除客户、群组、公司、分公司等", notes = "删除客户、群组、公司、分公司等", response = ApiResult.class)
    @PostMapping("/delete")
    public ApiResult deleteOrgType(@RequestBody Long[] ids) {
        return orgTypeService.deleteOrgType(ids);
    }
}
