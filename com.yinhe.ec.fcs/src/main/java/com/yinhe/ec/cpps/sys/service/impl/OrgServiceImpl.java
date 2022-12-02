package com.yinhe.ec.cpps.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yinhe.ec.core.base.BaseServiceImpl;
import com.yinhe.ec.cpps.sys.mapper.OrgMapper;
import com.yinhe.ec.cpps.sys.mapper.UserMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.Org;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 组织机构
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */

@Service
public class OrgServiceImpl extends BaseServiceImpl<Org, OrgMapper> implements OrgService
{

	@Autowired
	private OrgMapper orgMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Org> queryOrgList()
	{
		List<Org> resOrgList = new ArrayList<>();
		Org org = new Org();
		List<Org> orgList = orgMapper.queryOrgList(org);
		List<Org> parentOrgList = new ArrayList<>();
		List<Org> sonOrgList = new ArrayList<>();
		for (Org orgIndex : orgList)
		{
			if (null == orgIndex.getParentId())
			{
				parentOrgList.add(orgIndex);
			}
			else
			{
				sonOrgList.add(orgIndex);
			}
		}
		if (CollUtil.isEmpty(parentOrgList))
		{
			for (Org orgItem : sonOrgList)
			{
				orgItem.setChildren(getChildren(sonOrgList, orgItem.getId()));
				resOrgList.add(orgItem);
			}
		}
		else
		{
			for (Org parentOrgItem : parentOrgList)
			{
				parentOrgItem.setChildren(getChildren(sonOrgList, parentOrgItem.getId()));
				resOrgList.add(parentOrgItem);
			}
		}
		return resOrgList;
	}

	@Override
	public void addOrg(Org org)
	{
		orgMapper.addOrg(org);
	}

	/**
	 * 根据组织id查询已分配的电站和根据当前登录用户和组织id查询未分配的电站
	 * @param orgId
	 * 组织id
	 * @param userId
	 * 当前登录用户id
	 * @return
	 */
	@Override
	public JSONObject listAassignedAndUnAassignedStation(Long orgId, Long userId)
	{
		// 根据组织id查询已分配的电站
		Org org = new Org();
		org.setOrgIdList(Arrays.asList(orgId));
		org.setAssigned(1);
		List<Map<String, Object>> assignedStationList = orgMapper.queryStationList(org);
		// 根据组织id和当前登录用户id查询未分配的电站
		org.setOrgIdList(Arrays.asList(orgId));
		org.setAssigned(2);
		List<Map<String, Object>> unAssignedStationList = orgMapper.queryStationList(org);
		JSONObject resultJsonObject = new JSONObject();
		resultJsonObject.put("assignedStationList", assignedStationList);
		resultJsonObject.put("unAssignedStationList", unAssignedStationList);
		return resultJsonObject;
	}

	@Transactional
	@Override
	public Integer saveAassignedStation(Long orgId, List<Long> stationIdList, Long userId)
	{
		Integer result = 0;
		// 根据组织id查询已分配的电站
		Org org = new Org();
		org.setOrgIdList(Arrays.asList(orgId));
		org.setAssigned(1);
		List<Map<String, Object>> assignedStationList = orgMapper.queryStationList(org);
		if (CollUtil.isNotEmpty(assignedStationList))
		{
			for (Map<String, Object> assignedStationMap : assignedStationList)
			{
				Long stationId = MapUtil.getLong(assignedStationMap, "id");
				Long temOrgId = MapUtil.getLong(assignedStationMap, "org_id");
				boolean removeRes = stationIdList.remove(stationId);
				if (!removeRes)
				{
					orgMapper.deleteOrgPsr(temOrgId, stationId);
				}
			}
		}
		// 批量保存组织和电站关联信息
		if (CollUtil.isNotEmpty(stationIdList) && null != orgId && null != userId)
		{
			result = orgMapper.batchSaveOrgPsr(stationIdList, orgId, userId);
		}
		return result;
	}

	/**
	 * 递归查询组织树
	 * @param id
	 * @return
	 */
	@Override
	public List<Org> getChildren(List<Org> orgList, Long id)
	{
		List<Org> list = new ArrayList<>();
		if (CollUtil.isNotEmpty(orgList))
		{
			for (Org orgIndex : orgList)
			{
				Long parentId = orgIndex.getParentId();
				if (null != parentId && parentId.longValue() == id.longValue())
				{
					orgIndex.setChildren(getChildren(orgList, orgIndex.getId()));
					list.add(orgIndex);
				}
			}
		}
		if (CollUtil.isNotEmpty(list))
		{
			list = list.stream().sorted(Comparator.comparing(Org::getOrderId, Comparator.nullsLast(Long::compareTo))).collect(Collectors.toList());
		}
		return list;
	}

	/**
	 * 递归查询组织以及父级组织
	 */
	public List<Org> queryParentOrgList(Map<Long, Org> orgMap, Long orgId)
	{
		List<Org> orgList = new ArrayList<>();
		Org org = orgMap.get(orgId);
		if (null == org)
		{
			return orgList;
		}
		orgList.add(org);
		Long parentId = org.getParentId();
		if (null == parentId)
		{
			return orgList;
		}
		Org orgParentQueryParam = new Org();
		orgParentQueryParam.setId(org.getParentId());
		List<Org> parentOrgList = orgMapper.queryOrgList(orgParentQueryParam);
		Org parentOrg = CollUtil.getFirst(parentOrgList);
		if (parentOrg != null)
		{
			if (null != parentOrg.getParentId())
			{
				List<Org> tempOrgList = queryParentOrgList(orgMap, parentOrg.getId());
				tempOrgList.forEach(orgTemp -> {
					orgList.add(orgTemp);
				});
			}
			else
			{
				orgList.add(parentOrg);
			}
		}

		return orgList;
	}

	@Override
	public ApiResult deleteOrg(Long[] ids)
	{
		Org org = new Org();
		org.setParentIdList(Arrays.asList(ids));
		List<Org> orgList = orgMapper.queryOrgList(org);
		if (CollUtil.isNotEmpty(orgList))
		{
			return ApiResult.error("存在下级组织,无法删除!");
		}
		User userParam = new User();
		userParam.setOrgIdList(Arrays.asList(ids));
		List<User> userList = userMapper.queryUserList(userParam);
		if (CollUtil.isNotEmpty(userList))
		{
			return ApiResult.error("组织下存在用户,无法删除!");
		}

		// 根据组织id查询已分配的电站
		Org orgParam = new Org();
		orgParam.setOrgIdList(Arrays.asList(ids));
		orgParam.setAssigned(1);
		List<Map<String, Object>> assignedStationList = orgMapper.queryStationList(orgParam);
		if (CollUtil.isNotEmpty(assignedStationList))
		{
			return ApiResult.error("组织下存在已分配的电站,无法删除!");
		}
		orgMapper.delete(new UpdateWrapper<Org>().in("id", ids));
		return ApiResult.ok();
	}

	@Override
	public List<Org> queryOrgList(Org orgParam)
	{
		return orgMapper.queryOrgList(orgParam);
	}

	@Override
	public Integer updateOrg(Org org)
	{
		return orgMapper.updateOrg(org);
	}
}
