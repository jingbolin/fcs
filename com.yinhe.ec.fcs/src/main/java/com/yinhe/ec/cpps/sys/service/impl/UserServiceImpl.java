package com.yinhe.ec.cpps.sys.service.impl;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.yinhe.ec.core.exception.BusinessException;
import com.yinhe.ec.core.support.cache.RedisHelper;
import com.yinhe.ec.core.util.CheckWord;
import com.yinhe.ec.core.util.ConfigEnum;
import com.yinhe.ec.core.util.DateUtil;
import com.yinhe.ec.cpps.cache.UserCache;
import com.yinhe.ec.cpps.sys.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.cpps.sys.mapper.ConfListMapper;
import com.yinhe.ec.cpps.sys.mapper.MenuMapper;
import com.yinhe.ec.cpps.sys.mapper.PermissionMapper;
import com.yinhe.ec.cpps.sys.mapper.RoleMapper;
import com.yinhe.ec.cpps.sys.mapper.UserMapper;
import com.yinhe.ec.cpps.sys.mapper.UserOrgMapper;
import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.sys.model.ConfList;
import com.yinhe.ec.cpps.sys.model.Menu;
import com.yinhe.ec.cpps.sys.model.Org;
import com.yinhe.ec.cpps.sys.model.UploadResult;
import com.yinhe.ec.cpps.sys.model.User;
import com.yinhe.ec.cpps.sys.model.UserOrg;
import com.yinhe.ec.cpps.sys.service.ConfListService;
import com.yinhe.ec.cpps.sys.service.MenuService;
import com.yinhe.ec.cpps.sys.service.ResourceService;
import com.yinhe.ec.cpps.sys.service.UserRoleService;
import com.yinhe.ec.cpps.sys.service.UserService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * ?????????
 *
 * @author zhanglei
 * @date 2020-01-13 16:14:04
 */
@CacheConfig(cacheNames = "sysUser")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserOrgMapper userOrgMapper;
    @Autowired
    private ConfListMapper confListMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ConfListService confListService;
    @Autowired
    private UserCache userCache;

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public PageInfo<User> queryUserPage(Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = queryUserList(user);
        return new PageInfo<User>(userList);
    }

    @Override
    public List<User> queryUserList(User user) {
        List<User> userList = new ArrayList<>();
        if (null == user) {
            return userList;
        }
        return userMapper.queryUserList(user);
    }

    public Set<Long> getChildrenIdList(List<Org> orgList, Long orgId) {
        Set<Long> orgIdList = new HashSet<>();

        if (null == orgId) {
            if (CollUtil.isNotEmpty(orgIdList)) {
                for (Org org : orgList) {
                    orgIdList.add(org.getId());
                }
            }
            return orgIdList;
        }
        orgIdList.add(orgId);
        if (CollUtil.isNotEmpty(orgList)) {
            for (Org orgIndex : orgList) {
                Long parentId = orgIndex.getParentId();
                if (null != parentId && parentId.longValue() == orgId.longValue()) {
                    orgIdList.add(orgIndex.getId());
                    Set<Long> orgIdTempList = getChildrenIdList(orgList, orgIndex.getId());
                    orgIdList.addAll(orgIdTempList);
                }
            }
        }
        return orgIdList;
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public String getRoleStrByUserId(Long userId) {
        return roleMapper.getRoleStrByUserId(userId);
    }

    @Override
    public String getPermissionStrByUserId(Long userId) {
        return permissionMapper.getPermissionStrByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult insertUser(User user) {
        ApiResult result = checkUser(user, 1);
        if (200 != MapUtil.getInt(result, "code")) {
            return result;
        }
        user.setEnable(true);
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userMapper.insertUser(user);
        userCache.update(user);
        Long userId = user.getId();
        Long orgId = user.getOrgId();
        if (null != userId && null != orgId) {
            UserOrg userOrg = new UserOrg();
            userOrg.setOrgId(orgId);
            userOrg.setUserId(userId);
            userOrgMapper.insert(userOrg);
        }
        return ApiResult.ok("????????????!");
    }

    /**
     * @param user
     * @param handleFlag 1:?????????2:??????
     * @return
     */
    ApiResult checkUser(User user, int handleFlag) {
        if (null == user) {
            return ApiResult.error("??????????????????");
        }
        String userName = user.getUserName();
        User uParam = new User();
        if (StringUtils.isNotEmpty(userName)) {
            uParam.setUserName(userName);
            uParam.setHandleFlag(handleFlag);
            if (handleFlag == 2) {
                uParam.setId(user.getId());
            }
            List<Long> userIdList = userMapper.selectIdPage(uParam);
            if (CollUtil.isNotEmpty(userIdList)) {
                return ApiResult.error("??????????????????!");
            }
        }
        String account = user.getAccount();
        if (StringUtils.isNotEmpty(account)) {
            uParam = new User();
            uParam.setAccount(account);
            uParam.setHandleFlag(handleFlag);
            if (handleFlag == 2) {
                uParam.setId(user.getId());
            }
            List<Long> userAccountIdList = userMapper.selectIdPage(uParam);
            if (CollUtil.isNotEmpty(userAccountIdList)) {
                return ApiResult.error("???????????????!");
            }
        }
        String jobNum = user.getJobNum();
        if (StringUtils.isNotEmpty(jobNum)) {
            uParam = new User();
            uParam.setJobNum(jobNum);
            uParam.setHandleFlag(handleFlag);
            if (handleFlag == 2) {
                uParam.setId(user.getId());
            }
            List<Long> userobNumIdList = userMapper.selectIdPage(uParam);
            if (CollUtil.isNotEmpty(userobNumIdList)) {
                return ApiResult.error("???????????????!");
            }
        }

        String password = user.getPassword();
        ConfList confListParam = new ConfList();
        confListParam.setConfListName(ConfigEnum.PASSWORD_MIN_LEN.name());
        List<ConfList> confListList = confListMapper.queryConfListList(confListParam);
        if (CollUtil.isNotEmpty(confListList)) {
            confListParam = confListList.get(0);
            String confListValue = confListParam.getConfListValue();
            if (StringUtils.isNotEmpty(confListValue)) {
                if (password.length() < Integer.parseInt(confListValue)) {
                    return ApiResult.error(StrUtil.format("????????????????????????{}???", confListValue));
                }
            }
        }
        return ApiResult.ok();
    }

    /**
     * ????????????id??????????????????????????????????????????
     *
     * @param roleId
     * @return
     */
    @Override
    public JSONObject listAassignedAndUnAassignedUsers(Long roleId) {
        // ????????????id????????????????????????
        User assignedUser = new User();
        assignedUser.setRoleId(roleId);
        assignedUser.setAssignedUser(1);
        List<User> assignedUserList = userMapper.queryUserList(assignedUser);
        // ????????????id???????????????id????????????????????????
        User unAssignedUser = new User();
        unAssignedUser.setRoleId(roleId);
        unAssignedUser.setAssignedUser(2);
        List<User> unAssignedUserList = userMapper.queryUserList(unAssignedUser);
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put("assignedUserList", assignedUserList);
        resultJsonObject.put("unAssignedUserList", unAssignedUserList);
        return resultJsonObject;
    }

    @Override
    public ApiResult checkPasswordIntensity(String password) {
        Integer intensity = CheckWord.checkPassword(password);
        ConfList confList = new ConfList();
        confList.setConfListCode(ConfigEnum.PASSWORD_INTENSITY_NUM.name());
        List<ConfList> confListList = confListMapper.queryConfListList(confList);
        confList = CollUtil.getFirst(confListList);
        String confListValueStr = confList.getConfListValue();
        Integer confListValue = Integer.parseInt(confListValueStr);
        if (intensity < confListValue) {
            return ApiResult.error("??????????????????!");
        }
        return ApiResult.ok();
    }

    @Override
    public ApiResult doLogin(String sessionId, String account, String password, String ip, String tokenStr) {
        JSONObject shiroSessionUserJsonObject = new JSONObject();
        if (StringUtils.isEmpty(password)) {
        }
        List<Menu> menuList = menuService.queryMenuByAccount(account);
        if (CollUtil.isNotEmpty(menuList)) {
            shiroSessionUserJsonObject.put("menuList", menuList);
        } else {
            menuList = menuMapper.queryRootMenu();
            // return ApiResult.error("???????????????????????????!");
        }
        Resource resource = new Resource();
        resource.setAccount(account);
        resource.setTypeCode(Constants.RESOURCE_TYPE_CODE_PERMISSION);
        List<String> resStrList = resourceService.queryResourceModuleList(account);
        resStrList = CollUtil.removeEmpty(resStrList);
        
        JSONObject loginInfoJson = new JSONObject();
        User user = userMapper.getUserByAccount(account);
        if (null != user) {
            shiroSessionUserJsonObject.put("user", user);
            loginInfoJson.put("userId", user.getId());
        }
        loginInfoJson.put("account", account);
        loginInfoJson.put("custId", user.getCustomerId());
        loginInfoJson.put("username", user.getUserName());
        loginInfoJson.put("ip", ip);
        loginInfoJson.put("loginTime", DateUtil.format(new Date(), DateUtil.DatePattern.YYYY_MM_DD_HH_MM_SS));
        shiroSessionUserJsonObject.put("loginInfo", loginInfoJson);
        if (StringUtils.isNotEmpty(sessionId)) {
            redisHelper.hset("shiro-session-user", sessionId, shiroSessionUserJsonObject);
            Integer confListSessionTimeoutMinutes = 30;
            ConfList confList = new ConfList();
            confList.setConfListCode(ConfigEnum.SESSION_TIMEOUT.name());
            List<ConfList> confListList = confListMapper.queryConfListList(confList);
            if (CollUtil.isNotEmpty(confListList)) {
                ConfList confListSessionTimeout = confListList.get(0);
                String confListSessionTimeoutStr = confListSessionTimeout.getConfListValue();
                if (StringUtils.isNotEmpty(confListSessionTimeoutStr)) {
                    try {
                        confListSessionTimeoutMinutes = Integer.parseInt(confListSessionTimeoutStr);
                    } catch (NumberFormatException e) {
                        Console.error("??????SESSION_TIMEOUT:{}????????????", confListSessionTimeoutStr);
                    }
                }
                Long confListSessionTimeoutSecond = TimeUnit.MINUTES.toSeconds(confListSessionTimeoutMinutes);
                redisHelper.expire("shiro:session:" + sessionId, confListSessionTimeoutSecond.intValue());
                redisHelper.expire("shiro-session-user", confListSessionTimeoutSecond.intValue());
            }
        }
        if (CollUtil.isNotEmpty(menuList) && menuList.size() == 1) {
            Menu menu = menuList.get(0);
            List<Menu> menuChildrenList = menu.getChildren();
            if (CollUtil.isEmpty(menuChildrenList)) {
                return ApiResult.error("?????????????????????!");
            }
        }
        return ApiResult.ok().put("menuList", CollUtil.isNotEmpty(menuList) ? menuList : new ArrayList<>()).put("resourceList", resStrList).put("token", tokenStr)
                .put("loginInfo", loginInfoJson).put("ECLOUDSESSIONID", sessionId);
    }

    @Override
    public ApiResult updateUser(User user) {
        ApiResult result = checkUser(user, 2);
        if (200 != MapUtil.getInt(result, "code")) {
            return result;
        }
        int affectNum = userMapper.updateUser(user);
        if (affectNum > 0) {
            userCache.update(user);
            return ApiResult.ok();
        }
        return ApiResult.error("??????????????????!");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteUser(List<Long> idList) {
        List<User> userList = this.queryUserByIdList(idList);
        boolean flag = userList.stream().anyMatch(e -> "admin".equals(e.getAccount()));
        if (flag) {
            throw new BusinessException("Admin?????????????????????");
        }
        int result = 0;
        result += userMapper.batchDeleteUser(idList);
        userCache.delete(idList);
        result += userRoleService.batchDeleteUserRole(idList);
        return result;
    }

    @Override
    public User queryUserById(Long userId) {
        User user = new User();
        user.setId(userId);
        List<User> userList = userMapper.queryUserList(user);
        if (CollUtil.isNotEmpty(userList)) {
            user = userList.get(0);
        }
        return user;
    }

    @Override
    public List<User> queryUserByIdList(List<Long> userIdList) {
        List<User> userList = new ArrayList<User>();
        User user = new User();
        user.setIdList(userIdList);
        userList = userMapper.batchQueryUserList(user);
        return userList;
    }

    @Override
    public ApiResult verifyUser(String account, String password, String token) {
        User user = null;
        try {
            user = getUserByAccount(account);
        } catch (Exception e) {
            return ApiResult.error("???????????????");
        }
        if (user == null) {
            // ???????????????
            return ApiResult.error("???????????????");
        }
        if (!user.getEnable()) {
            return ApiResult.error("???????????????!");
        } else {
            // ??????????????????????????????
            ApiResult result = passwordExpiredHandler(user);
            int code = MapUtil.getInt(result, "code");
            if (200 != code) {
                return result;
            }
        }
        Long failCounts = user.getFailCount() == null ? 0L : user.getFailCount();
        if (user.getIsLock() && failCounts == 0) {
            return ApiResult.error("??????????????????????????????????????????!");
        }

        if (user.getIsLock()) {
            // ????????????????????????
            ApiResult result = afterLockUserHandler(user);
            int code = MapUtil.getInt(result, "code");
            if (200 != code) {
                return result;
            }
        }
        if (StringUtils.isNotEmpty(password)) {
            // ??????????????????
            String pwdInDb = user.getPassword();
            if (!SecureUtil.md5(password).equals(pwdInDb)) {
                Long failCount = (null == user.getFailCount()) ? 1 : (user.getFailCount() + 1);
                // ????????????????????????
                ApiResult result = incorrectCredentialsHandler(failCount, user, password);
                Integer code = MapUtil.getInt(result, "code");
                if (200 != code) {
                    return result;
                }
                return ApiResult.error("????????????!");
            }
            Console.log(password);
        }
        return ApiResult.ok();
    }

    /**
     * ????????????????????????
     *
     * @param user
     */
    public ApiResult passwordExpiredHandler(User user) {
        // ?????????????????????????????????
        ConfList passwordExpiredDaysConfList = new ConfList();
        passwordExpiredDaysConfList.setConfListCode(ConfigEnum.PASSWORD_EXPIRED_DAYS.name());
        List<ConfList> passwordExpiredDaysConfListList = confListService.queryConfListList(passwordExpiredDaysConfList);
        passwordExpiredDaysConfList = CollUtil.getFirst(passwordExpiredDaysConfListList);
        // ?????????????????????????????????100??????
        Integer passwordExpiredDays = 100;
        if (null != passwordExpiredDaysConfList && StringUtils.isNotEmpty(passwordExpiredDaysConfList.getConfListValue())) {
            try {
                passwordExpiredDays = Integer.parseInt(passwordExpiredDaysConfList.getConfListValue());
            } catch (NumberFormatException e) {
                return ApiResult.error(String.format("???????????????????????????:{}???????????????", passwordExpiredDaysConfList.getConfListValue()));
            }
        }
        Date lastPasswordUpdateDate = null;
        lastPasswordUpdateDate = user.getLastPasswordUpdateDate();
        if (null == lastPasswordUpdateDate) {
            Date createTime = user.getCreateTime();
            if (null != createTime) {
                lastPasswordUpdateDate = createTime;
            } else {
                lastPasswordUpdateDate = new Date();
            }
            updateUser(user);
        }
        if (LocalDate.now().minusDays(passwordExpiredDays).isAfter(DateUtil.date2LocalDate(lastPasswordUpdateDate))) {
            updateUser(user);
            return ApiResult.error(StrUtil.format("????????????????????????????????????????????????{}???????????????????????????????????????????????????????????????????????????????????????!", passwordExpiredDays));
        }
        return ApiResult.ok();
    }

    /**
     * ??????????????????????????????
     */
    public ApiResult afterLockUserHandler(User user) {
        ConfList lockUserIntervalConfList = new ConfList();
        lockUserIntervalConfList.setConfListCode(ConfigEnum.LOCK_USER_INTERVAL.name());
        List<ConfList> lockUserIntervalConfListList = confListService.queryConfListList(lockUserIntervalConfList);
        lockUserIntervalConfList = CollUtil.getFirst(lockUserIntervalConfListList);
        // ???????????????????????????30??????????????????
        Integer lockUserInterval = 30;
        if (null != lockUserIntervalConfList && StringUtils.isNotEmpty(lockUserIntervalConfList.getConfListValue())) {
            lockUserInterval = Integer.parseInt(lockUserIntervalConfList.getConfListValue());
        }
        Date lockTime = (null == user.getLockTime()) ? new Date() : user.getLockTime();
        Long lockTimeMilliseconds = lockTime.getTime();
        Date now = new Date();
        Long nowTimeMilliseconds = now.getTime();
        // ????????????-????????????>??????????????????->????????????,????????????????????????????????????????????????
        if (TimeUnit.MILLISECONDS.toSeconds(nowTimeMilliseconds - lockTimeMilliseconds) > lockUserInterval) {
            user.setIsLock(false);
            user.setFailCount(0L);
            user.sethPassword1(null);
            user.sethPassword2(null);
            user.sethPassword3(null);
            user.sethPassword4(null);
            user.sethPassword5(null);
            user.sethPassword6(null);
            user.setUpdateTime(new Date());
            updateUser(user);
        } else {
            Long unLockTime = lockUserInterval - TimeUnit.MILLISECONDS.toSeconds(nowTimeMilliseconds - lockTimeMilliseconds);
            // ???????????????
            return ApiResult.error(StrUtil.format("???????????????????????????????????????????????????????????????????????????????????????:{}???!", unLockTime.intValue()));
        }
        return ApiResult.ok();
    }

    /**
     * ??????????????????
     *
     * @return
     */
    public ApiResult incorrectCredentialsHandler(Long failCount, User user, String password) {
        // ???????????????????????????0~6???
        ConfList passwordHistoryNumConfList = new ConfList();
        passwordHistoryNumConfList.setConfListCode(ConfigEnum.PASSWORD_HISTORY_NUM.name());
        List<ConfList> passwordHistoryNumConfListList = confListService.queryConfListList(passwordHistoryNumConfList);
        passwordHistoryNumConfList = CollUtil.getFirst(passwordHistoryNumConfListList);
        // ???????????????????????????6
        Integer passwordHistoryNum = 6;
        if (null != passwordHistoryNumConfList && StringUtils.isNotEmpty(passwordHistoryNumConfList.getConfListValue())) {
            try {
                passwordHistoryNum = Integer.parseInt(passwordHistoryNumConfList.getConfListValue());
                // ??????????????????????????????6????????????????????????????????????6
                passwordHistoryNum = passwordHistoryNum > 6 ? 6 : passwordHistoryNum;
            } catch (NumberFormatException e) {
                Console.error("?????????????????????0~6?????????:{}???????????????", passwordHistoryNumConfList.getConfListValue());
                ApiResult.error(String.format("?????????????????????0~6?????????:{}???????????????", passwordHistoryNumConfList.getConfListValue()));
            }
        }
        // ?????????????????????????????????????????????(sa_conf_list)?????????????????????(PASSWORD_HISTORY_NUM),?????????????????????
        if (failCount <= passwordHistoryNum) {
            try {
                Method method = user.getClass().getMethod("sethPassword" + failCount, String.class);
                method.invoke(user, password);
                user.setFailCount(failCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ????????????????????????
        ApiResult result = lockUserHandler(failCount, user);
        int code = MapUtil.getInt(result, "code");
        if (200 != code) {
            return result;
        }
        // ?????????????????????
        // verifactionCodeHandler(user, failCount);
        user.setFailCount(failCount);
        user.setUpdateTime(new Date());
        updateUser(user);
        return ApiResult.ok();
    }

    /**
     * ??????????????????
     */
    public ApiResult lockUserHandler(Long failCount, User user) {
        // ??????????????????????????????????????????
        ConfList confList = new ConfList();
        confList.setConfListCode(ConfigEnum.FAIL_COUNT_FOR_LOCK_USER.name());
        List<ConfList> confListList = confListService.queryConfListList(confList);
        confList = CollUtil.getFirst(confListList);
        String failCountForLockUserValueStr = confList.getConfListValue();
        // ?????????????????????????????????????????????6
        Integer failCountForLockUserValue = Constants.DETAULT_FAIL_COUNT_FOR_LOCK_USER;
        if (StringUtils.isNotEmpty(failCountForLockUserValueStr)) {
            failCountForLockUserValue = Integer.parseInt(failCountForLockUserValueStr);
        }
        // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        if (failCount > failCountForLockUserValue && !user.getIsLock()) {
            user.setIsLock(true);
            user.setLockTime(new Date());
            user.setFailCount(failCount);
            user.setUpdateTime(new Date());
            updateUser(user);
            return ApiResult.error("??????????????????????????????????????????????????????????????????");
        }
        return ApiResult.ok();
    }

    /**
     * ??????license??????
     *
     * @param file
     * @param uploadFilePath
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult upload(MultipartFile file, String uploadFilePath) throws Exception {
        if (file.isEmpty()){
            return null;
        }
        if(file.getSize() > Constants.LicenseFile.DEFAULT_MAX_SIZE){
            return ApiResult.error("??????????????????????????????????????????");
        }
        if(! "license.lic".equals(file.getOriginalFilename())){
            return ApiResult.error("?????????license.lic??????");
        }
        // ??????????????????
        ApplicationHome h = new ApplicationHome(Object.class.getClass());
        String path = h.toString();

        UploadResult uploadInfo = new UploadResult();
        String originalFilename;
        originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        long fileSize = file.getSize();
        File packageFile = new File(path+"/../"+uploadFilePath);

        if (!packageFile.getParentFile().exists()) { packageFile.getParentFile().mkdirs(); }
        if (!packageFile.exists()) { packageFile.createNewFile(); }

        file.transferTo(packageFile);
        uploadInfo.setBeginFileName(originalFilename);
        uploadInfo.setLastFileName(originalFilename);
        uploadInfo.setFileType(fileType);
        uploadInfo.setFileSize(Long.toString(fileSize));
        uploadInfo.setUploadUrl(packageFile.toString());
        uploadInfo.setResult("????????????");
        return ApiResult.ok(uploadInfo);
    }
}
