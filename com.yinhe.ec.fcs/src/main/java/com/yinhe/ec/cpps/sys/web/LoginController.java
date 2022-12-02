package com.yinhe.ec.cpps.sys.web;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.shiro.JWTUtil;
import com.yinhe.ec.core.shiro.JwtToken;
import com.yinhe.ec.core.util.IpUtil;
import com.yinhe.ec.core.util.PropertiesUtil;
import com.yinhe.ec.core.util.SecurityUtil;
import com.yinhe.ec.core.util.ShiroUtil;
import com.yinhe.ec.cpps.sys.license.model.LicenseCheckModel;
import com.yinhe.ec.cpps.sys.license.service.AbstractServerInfos;
import com.yinhe.ec.cpps.sys.license.service.LinuxServerInfos;
import com.yinhe.ec.cpps.sys.license.service.WindowsServerInfos;
import com.yinhe.ec.cpps.sys.license.vo.LicenseCheckModelVo;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 用户表
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@Api("用户表API")
@Controller
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "登录系统", notes = "登录系统", response = ApiResult.class)
    @PostMapping("/doLogin")
    @ResponseBody
    public ApiResult doLogin(@RequestParam(required = false, name = "username") String account,
        @RequestParam(required = false, name = "password") String password,
        @RequestParam(required = false, name = "token") String token, HttpServletRequest request) {
        account = StringUtils.trimToEmpty(account);
        password = StringUtils.trimToEmpty(password);
        token = StringUtils.trimToEmpty(token);
        if (StringUtils.isEmpty(token) && StringUtils.isAnyEmpty(account, password)) {
            return ApiResult.error("账号或密码不能为空");
        }
        try {
            String ip = IpUtil.getRequestIp(request);
            AuthenticationToken authenticationToken;
            if (StringUtils.isNotEmpty(token) && StringUtils.isAnyEmpty(account, password)) {
                account = JWTUtil.getUsername(token);
                if (StringUtils.isEmpty(account)) {
                    return ApiResult.error("token不存在,登录失败!");
                }
                authenticationToken = new JwtToken(token);
            } else {
                String encryptPwd = SecureUtil.md5(password);
                token = JWTUtil.sign(account, encryptPwd);
                authenticationToken = new UsernamePasswordToken(account, encryptPwd);
            }
            ApiResult result = userService.verifyUser(account, password, token);
            int code = MapUtil.getInt(result, "code");
            if (200 != code) {
                return result;
            }
            SecurityUtils.getSubject().login(authenticationToken);
            String sessionId = (String)SecurityUtils.getSubject().getSession().getId();
            Console.log("sessionId:" + sessionId);
            //填充登录信息
            ApiResult apiResult = userService.doLogin(sessionId, account, password, ip, token);
            ShiroUtil.saveCurrentUser((User)SecurityUtils.getSubject().getPrincipal());
            return apiResult;
        } catch (ExpiredCredentialsException e) {
            return ApiResult.error(e.getMessage());
        } catch (IncorrectCredentialsException ice) {
            return ApiResult.error("密码错误");
        } catch (UnknownAccountException uae) {
            return ApiResult.error("账号不存在");
        } catch (LockedAccountException e) {
            return ApiResult.error(e.getMessage());
        } catch (DisabledAccountException e) {
            return ApiResult.error("账号当前状态不可用");
        } catch (ExcessiveAttemptsException eae) {
            return ApiResult.error("操作频繁，请稍后再试");
        } catch (Exception e) {
            logger.error("登录错误", e);
            return ApiResult.error();
        }
    }

    @PostMapping("/logout")
    @ResponseBody
    @ApiOperation(value = "退出系统", notes = "退出系统", response = ApiResult.class)
    public ApiResult doLogout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                String sessionId = subject.getSession().getId().toString();
                SecurityUtils.getSubject().logout();
                redisTemplate.delete(Constants.REDIS_SHIRO_SESSION + sessionId);
                Set sessionKeySet = redisTemplate.keys(Constants.REDIS_SHIRO_SESSION + "*");
                if (CollUtil.isEmpty(sessionKeySet)) {
                    redisTemplate.delete(Constants.ALLUSER_NUMBER);
                } else {
                    Set<String> allUserNumberSet = redisTemplate.opsForSet().members(Constants.ALLUSER_NUMBER);
                    String[] excludeArr = allUserNumberSet.stream().filter(
                        t -> !sessionKeySet.contains(Constants.REDIS_SHIRO_SESSION + t) && StringUtils.isNotEmpty(t))
                        .toArray(String[]::new);
                    if (ArrayUtil.isNotEmpty(excludeArr)) {
                        redisTemplate.opsForSet().remove(Constants.ALLUSER_NUMBER, excludeArr);
                    }
                }
                return ApiResult.ok("用户退出登录!");
            } else {
                return ApiResult.error("用户还未登录!");
            }
        } catch (Exception ice) {
            ice.printStackTrace();
            return ApiResult.error(ice.getMessage());
        }
    }

    /**
     * 获取服务器硬件信息
     *
     * @param osName 操作系统类型，如果为空则自动判断
     * @return
     */
    @PostMapping(value = "/getServerInfos", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "获取服务器硬件信息", notes = "获取服务器硬件信息")
    @ResponseBody
    public ApiResult getServerInfos(@RequestParam(value = "osName", required = false) String osName) {

        // 操作系统类型
        if (StringUtils.isBlank(osName)) {
            osName = System.getProperty("os.name");
        }
        osName = osName.toLowerCase();

        AbstractServerInfos abstractServerInfos;

        // 根据不同操作系统类型选择不同的数据获取方法
        if (osName.startsWith("windows") || osName.startsWith("Windows")) {
            abstractServerInfos = new WindowsServerInfos();
        } else if (osName.startsWith("linux")) {
            abstractServerInfos = new LinuxServerInfos();
        } else {// 其他服务器类型
            abstractServerInfos = new LinuxServerInfos();
        }
        // 获取信息
        LicenseCheckModel serverInfos = abstractServerInfos.getServerInfos();

        // 转json加密
        JSONObject jsonObject = JSONUtil.createObj();

        jsonObject.set("cpuSerial", serverInfos.getCpuSerial()).set("mainBoardSerial", serverInfos.getMainBoardSerial());
        String encrypt = SecurityUtil.encryptDes(JSONUtil.toJsonStr(jsonObject), Constants.DB_KEY.getBytes());

        //返回页面加密信息
        serverInfos.setIdentifier(encrypt);
        LicenseCheckModelVo checkModelVo = new LicenseCheckModelVo();

        checkModelVo.setIdentifier(encrypt);

        return ApiResult.ok(checkModelVo);
    }

    @PostMapping(value = "/upload", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "上传license文件", notes = "上传license文件")
    @ResponseBody
    public ApiResult uploadFile(MultipartFile file) {
        String licensePath = PropertiesUtil.getString("license.licensePath");
        ApiResult apiResult = null;
        try {
            apiResult = userService.upload(file, licensePath);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResult.error("上传文件出现异常");
        }
        return apiResult;

    }
}
