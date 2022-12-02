package com.yinhe.ec.cpps.sys.service.impl;

import java.util.Arrays;
import java.util.List;

import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.cpps.sys.mapper.MenuMapper;
import com.yinhe.ec.cpps.sys.mapper.ResourceMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Menu;
import com.yinhe.ec.cpps.sys.model.Resource;
import com.yinhe.ec.cpps.sys.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.collection.CollUtil;

/**
 * 资源
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, ResourceMapper> implements ResourceService
{

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public PageInfo<Resource> queryResourcePage(Integer pageNum, Integer pageSize, Resource resource)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<Resource> resourceListPage = resourceMapper.queryResourceList(resource);
		return new PageInfo<Resource>(resourceListPage);
	}

	@Override
	public void insertResource(Resource resource)
	{
		if(StringUtils.isBlank(resource.getNameEn())){
			resource.setNameEn(resource.getName());
		}
		resourceMapper.insertResource(resource);
	};

	@Override
	public JSONObject listAassignedAndUnAassigned(Long resourceGroupId, Long permissionId)
	{
		// 根据权限id查询已分配的资源
		Resource assignedResource = new Resource();
		assignedResource.setPermissionId(permissionId);
		assignedResource.setGroupId(resourceGroupId);
		assignedResource.setAssigned(1);
		List<Resource> assignedResourceList = resourceMapper.queryResourceList(assignedResource);
		// 根据权限id和资源分组id查询已分配的资源
		Resource unAssignedResource = new Resource();
		unAssignedResource.setGroupId(resourceGroupId);
		unAssignedResource.setPermissionId(permissionId);
		unAssignedResource.setAssigned(2);
		List<Resource> unAssignedResourceList = resourceMapper.queryResourceList(unAssignedResource);
		JSONObject resultJsonObject = new JSONObject();
		resultJsonObject.put("assignedResource", assignedResourceList);
		resultJsonObject.put("unAssignedResource", unAssignedResourceList);
		return resultJsonObject;
	}

    @Override
    public List<String> queryResourceModuleList(String account) {
        return resourceMapper.queryResourceModuleList(account);
    }

	@Override
	public List<String> queryResourceStrList(Resource resource)
	{
		String resStr = resourceMapper.getSaResourcesStr(resource);
		return Arrays.asList(resStr.split(","));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ApiResult deleteResourceList(Long[] ids)
	{
		Menu menuParam = new Menu();
		menuParam.setResourceIdList(Arrays.asList(ids));
		List<Menu> menuList = menuMapper.queryMenuList(menuParam);
        if (CollUtil.isNotEmpty(menuList)) {
            // StringJoiner sj = new StringJoiner("\t");
            // menuList.stream().forEach(menu -> {
            //     sj.add("资源:【").add(menu.getResName()).add("】已被菜单:【").add(menu.getMenuTitle()).add("】使用,无法删除");
            // });
            // sj.add("无法删除!");
            return ApiResult.error("该资源已分配给菜单，无法删除!");
        }
		Resource resource = new Resource();
		resource.setIdList(Arrays.asList(ids));
		resourceMapper.batchDelete(resource);
		return ApiResult.ok(ApiCode.DELETE_SUCCESS);
	}

	@Override
	public Integer updateResource(Resource resource)
	{
		if(StringUtils.isBlank(resource.getNameEn())){
			resource.setNameEn(resource.getName());
		}
		return resourceMapper.updateResource(resource);
	}

	@Override
	public Resource queryResourceById(Long id)
	{
		Resource resource = new Resource();
		resource.setId(id);
		List<Resource> resList = resourceMapper.queryResourceList(resource);
		if (CollUtil.isNotEmpty(resList))
		{
			return resList.get(0);
		}
		return null;
	}

}
