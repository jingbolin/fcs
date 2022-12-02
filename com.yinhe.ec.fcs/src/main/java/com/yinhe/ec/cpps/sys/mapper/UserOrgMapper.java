package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.cpps.sys.model.UserOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组织间角色
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface UserOrgMapper {
    /**
     * 查询用户组织间角色分页列表
     *
     * @return
     */
    List<UserOrg> queryUserOrgList(@Param("userOrg") UserOrg userOrg);

    void insert(@Param("userOrg") UserOrg userOrg);

    void batchDeleteUserOrg(@Param("userIdList") List<Long> userIdList);
}
