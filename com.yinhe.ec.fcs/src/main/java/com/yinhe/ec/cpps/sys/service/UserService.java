package com.yinhe.ec.cpps.sys.service;

import java.util.List;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.User;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 用户表
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
public interface UserService {
    
    List<User> queryAllUser();

    PageInfo<User> queryUserPage(Integer pageNum, Integer pageSize, User user);

    User getUserByAccount(String username);

    String getRoleStrByUserId(Long userId);

    String getPermissionStrByUserId(Long userId);

    ApiResult insertUser(User user);

    JSONObject listAassignedAndUnAassignedUsers(Long roleId);

    ApiResult checkPasswordIntensity(String password);

    ApiResult doLogin(String sessionId, String username, String password, String ip, String tokenStr);

    ApiResult updateUser(User user);

    List<User> queryUserList(User user);

    User queryUserById(Long userId);

    Integer deleteUser(List<Long> asList);

    List<User> queryUserByIdList(List<Long> userIdList);


    ApiResult verifyUser(String account, String password, String token);

    ApiResult upload(MultipartFile file, String uploadFilePath) throws Exception;
}

