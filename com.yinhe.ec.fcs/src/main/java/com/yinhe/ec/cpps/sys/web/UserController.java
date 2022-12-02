package com.yinhe.ec.cpps.sys.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.yinhe.ec.core.util.ApiCode;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.ConfList;
import com.yinhe.ec.cpps.sys.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.util.ConfigEnum;
import com.yinhe.ec.core.util.EncryptUtil;
import com.yinhe.ec.cpps.sys.service.ConfListService;
import com.yinhe.ec.cpps.sys.service.MenuService;
import com.yinhe.ec.cpps.sys.service.ResourceService;
import com.yinhe.ec.cpps.sys.service.UserRoleService;
import com.yinhe.ec.cpps.sys.service.UserService;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户表
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("用户表API")
@Controller
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ConfListService confListService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询用户表列表
     */
    @ApiOperation(value = "查询用户表列表", notes = "查询用户表列表", response = ApiResult.class)
    @PostMapping("/list")

    @ResponseBody
    public ApiResult listUser(@RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "1000", required = false) Integer pageSize, User user) {
        PageInfo<User> userPage = userService.queryUserPage(pageNum, pageSize, user);
        return ApiResult.ok().put("page", userPage);
    }

    /**
     * 根据角色id查询已分配和未分配的用户列表
     */
    @ApiOperation(value = "根据角色id查询已分配和未分配的用户列表", notes = "根据角色id查询已分配和未分配的用户列表", response = ApiResult.class)
    @PostMapping("/listAassignedAndUnAassignedUsers")

    @ResponseBody
    public ApiResult listAassignedAndUnAassignedUsers(Long roleId) {
        JSONObject aassignedAndUnAassignedJsonObject = userService.listAassignedAndUnAassignedUsers(roleId);
        return ApiResult.ok().put("data", aassignedAndUnAassignedJsonObject);
    }

    /**
     * 查询用户表
     */
    @ApiOperation(value = "查询用户表", notes = "查询用户表", response = ApiResult.class)
    @PostMapping("/query")

    @ResponseBody
    public ApiResult queryUser(@RequestParam("id") Long id) {
        User user = new User();
        user.setId(id);
        List<User> userList = userService.queryUserList(user);
        if (CollUtil.isNotEmpty(userList)) {
            return ApiResult.ok().put("user", userList.get(0));
        }
        return ApiResult.error("未查出数据!");
    }

    /**
     * 保存用户表
     */
    @ApiOperation(value = "保存用户表", notes = "保存用户表", response = ApiResult.class)
    @PostMapping("/save")

    @ResponseBody
    public ApiResult saveUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return ApiResult.error(error.getDefaultMessage());
            }
        }
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        user.setCreateBy(userTemp.getId());
        return userService.insertUser(user);
    }

    /**
     * 修改用户表
     */
    @ApiOperation(value = "修改用户表", notes = "修改用户表", response = ApiResult.class)
    @PostMapping("/update")

    @ResponseBody
    public ApiResult updateUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return ApiResult.error(error.getDefaultMessage());
            }
        }
        User userTemp = (User)SecurityUtils.getSubject().getPrincipal();
        if ((null != userTemp && userTemp.getId().longValue() == user.getId().longValue())
            || (StringUtils.isNotEmpty(userTemp.getAccount()) && "admin".equals(userTemp.getAccount()))) {
            user.setUpdateBy(userTemp.getId());
            user.setUpdateTime(new Date());
            if (StringUtils.isNotEmpty(user.getPassword())) {
                String md5Password = EncryptUtil.md5(user.getPassword());
                user.setPassword(md5Password);
                user.setLastPasswordUpdateDate(new Date());
            }
            if (user.getIsLock() != null) {
                if (user.getIsLock()) {// 如果设置为禁用
                    user.setLockTime(null);
                } else { // 如果设置为开启 失败次数清零
                    user.setFailCount(0L);
                }
            }

            ApiResult apiResult = userService.updateUser(user);
            return apiResult;
        } else {
            return ApiResult.error(ApiCode.NOT_PERMISSION);
        }

    }

    /**
     * 删除用户表
     */
    @ApiOperation(value = "删除用户表", notes = "删除用户表", response = ApiResult.class)
    @PostMapping("/delete")

    @ResponseBody
    public ApiResult deleteUser(@RequestBody Long[] ids) {
        userService.deleteUser(Arrays.asList(ids));
        return ApiResult.ok();
    }

    @ApiOperation(value = "检测密码强度", notes = "检测密码强度", response = ApiResult.class)
    @PostMapping("/checkPasswordIntensity")
    @ResponseBody
    public ApiResult checkPasswordIntensity(String password) {
        return userService.checkPasswordIntensity(password);
    }

    @GetMapping("/verificationCode")
    public void generatorVerificationCode(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ConfList confList = new ConfList();
            confList.setConfListCode(ConfigEnum.AUTH_NUMBER_LEN.name());
            List<ConfList> confListList = confListService.queryConfListList(confList);
            if (CollUtil.isNotEmpty(confListList)) {
                confList = confListList.get(0);
            }
            RandomGenerator randomGenerator =
                new RandomGenerator("0123456789", Integer.parseInt(confList.getConfListValue()));
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
            lineCaptcha.setGenerator(randomGenerator);
            // 重新生成code
            lineCaptcha.createCode();
            lineCaptcha.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
