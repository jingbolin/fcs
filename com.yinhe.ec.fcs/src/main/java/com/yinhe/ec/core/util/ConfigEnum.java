package com.yinhe.ec.core.util;

/**
 *  配置参数
 */
public enum ConfigEnum {
    FAIL_COUNT_FOR_LOCK_USER("锁定用户前登录的失败次数"),
    PASSWORD_EXPIRED_DAYS("用户密码有效（天）"),
    USERS_PER_TERMINAL("每终端用户数限制（0~5）"),
    LOCK_USER_INTERVAL("锁定用户时间间隔（分钟）"),
    SESSIONS_PER_USER("每用户会话次数限制（0~5）"),
    PASSWORD_HISTORY_NUM("密码历史个数（0~6）"),
    SESSIONS_PER_TERMINAL("每终端会话次数限制（0~5）"),
    ENABLE_AUTH_NUM("启动验证码(0/1)"),
    SESSION_TIMEOUT("系统超时间隔（分钟）"),
    FAIL_COUNT_FOR_AUTH_NUM("出现验证码前的登录失败次数"),
    PASSWORD_INTENSITY_NUM("密码强度（1,2,3）"),
    PASSWORD_MIN_LEN("密码长度最小值(3,4,5,6,7,8,9,10)"),
    AUTH_NUMBER_LEN("随机验证码位数"),
    
    HIS_DATA_TIME_TO_LIVE("历史数据存储时间(年)"),
    HIS_DATA_INTERVAL("历史数据存储间隔(分)"),
    SA_EVENT_TIME_TO_LIVE("操作日志保存天数(天)"),
    IS_OPEN_MASTER_SLAVE("是否启用主备机功能"),
    IS_MASTER_OF_LOCALHOST("本机是否主机"),
    IP_OF_MASTER("主机IP"),
    IP_OF_SLAVE("备机IP"),
    SLAVE_CHECK_TIME_INTERVAL("双机热备检测时间间隔"),
    SAFE_RUN_START_TIME("安全运行起始日期"),
    SERIES_I_OVER_LIMIT("组串电流正常值上限"),
    IS_ENABLE_SYN_DATETIME("是否自动对时");
    
    ConfigEnum(final String configName) {}
}
