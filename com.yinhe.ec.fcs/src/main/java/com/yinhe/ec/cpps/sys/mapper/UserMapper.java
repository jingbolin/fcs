package com.yinhe.ec.cpps.sys.mapper;

import com.yinhe.ec.cpps.sys.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface UserMapper {

    List<User> queryAllUser();

    List<Long> selectIdPage(@Param("cm") User params);

    /**
     * 查询用户表分页列表
     *
     * @return
     */
    List<User> queryUserList(@Param("user") User user);

    List<User> batchQueryUserList(@Param("user") User user);

    User getUserByAccount(@Param("account") String account);

    void insertUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    int batchDeleteUser(@Param("idList") List<Long> idList);
}
