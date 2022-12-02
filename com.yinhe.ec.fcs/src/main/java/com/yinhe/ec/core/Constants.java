package com.yinhe.ec.core;

import com.yinhe.ec.core.support.cache.CacheKey;
import com.yinhe.ec.core.util.InstanceUtil;

import java.util.Map;

/**
 * 常量表
 */
public interface Constants {
    public static final Integer PASSWORD_INTENSITY_LIGHT=1;
    public static final Integer PASSWORD_INTENSITY_MIDDLE=2;
    public static final Integer PASSWORD_INTENSITY_HIGH=3;
    public static final String SYS_NAME = "新能源电站智能运维平台V2.0";
    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    static final String EXCEPTION_HEAD = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
    /**
     * 缓存键值
     */
    static final Map<Class<?>, CacheKey> CACHEKEYMAP = InstanceUtil.newHashMap();
    /**
     * 操作名称
     */
    static final String OPERATION_NAME = "OPERATION_NAME";
    /**
     * 客户端语言
     */
    static final String USERLANGUAGE = "userLanguage";
    /**
     * 客户端主题
     */
    static final String WEBTHEME = "webTheme";
    /**
     * 当前用户
     */
    static final String CURRENT_USER = "CURRENT_USER";
    /**
     * 客户端信息
     */
    static final String USER_AGENT = "USER-AGENT";
    /**
     * 客户端信息
     */
    static final String USER_IP = "USER_IP";
    /**
     * 登录地址
     */
    static final String LOGIN_URL = "/login.html";
    /**
     * 缓存命名空间
     */
    static final String CACHE_NAMESPACE = "eCloud:";
    /**
     * 缓存命名空间
     */
    static final String SYSTEM_CACHE_NAMESPACE = "S:eCloud:";
    /**
     * 缓存命名空间
     */
    static final String CACHE_NAMESPACE_LOCK = "L:eCloud:";
    /**
     * 上次请求地址
     */
    static final String PREREQUEST = CACHE_NAMESPACE + "PREREQUEST";
    /**
     * 上次请求时间
     */
    static final String PREREQUEST_TIME = CACHE_NAMESPACE + "PREREQUEST_TIME";
    /**
     * 非法请求次数
     */
    static final String MALICIOUS_REQUEST_TIMES = CACHE_NAMESPACE + "MALICIOUS_REQUEST_TIMES";
    /**
     * 在线用户数量
     */
    static final String ALLUSER_NUMBER = SYSTEM_CACHE_NAMESPACE + "ALLUSER_NUMBER";
    /**
     * TOKEN
     */
    static final String TOKEN_KEY = SYSTEM_CACHE_NAMESPACE + "TOKEN_KEY:";
    /**
     * shiro cache
     */
    static final String REDIS_SHIRO_CACHE = SYSTEM_CACHE_NAMESPACE + "SHIRO-CACHE:";
    /**
     * SESSION
     */
    static final String REDIS_SHIRO_SESSION = SYSTEM_CACHE_NAMESPACE + "SHIRO-SESSION:";

    /**
     * SESSION
     */
    static final String REDIS_SHIRO_SESSION_PROP = SYSTEM_CACHE_NAMESPACE + "SHIRO-SESSION-PROP";
    /**
     * CACHE
     */
    static final String MYBATIS_CACHE = "C:eCloud:MYBATIS:";
    /**
     * 默认数据库密码加密key
     */
    static final String DB_KEY = "00100111";
    /**
     * 临时目录
     */
    static final String TEMP_DIR = "/temp/";
    /**
     * 请求报文体
     */
    static final String REQUEST_BODY = "eCloud.requestBody";
    /**
     * token默认有效时间 1天
     */
    public static Long JWT_TIME_OUT = System.currentTimeMillis() + 30 * 60 * 1000L;
    public static String TOKEN_CACHE_PREFIX = "cache.token.";
    public static final String TOKEN = "token";

    public static final String RESOURCE_TYPE_CODE_MENU = "1";
    public static final String RESOURCE_TYPE_CODE_PERMISSION = "2";
    /**
     * 启用验证码
     */
    public static final String  ENABLE_AUTH_NUM_YES ="1" ;
    /**
     * 不启用验证码
     */
    public static final String  ENABLE_AUTH_NUM_NO ="0" ;
    /**
     * 默认出现验证码前的登录失败次数为3
     */
    public static final Integer  DETAULT_FAIL_COUNT_FOR_AUTH_NUM =3 ;
    /**
     * 锁定用户前登录的失败次数默认为6
     */
    public static final Integer  DETAULT_FAIL_COUNT_FOR_LOCK_USER =6 ;


    /**
     * 交易方式
     */
    public static enum Payment {
        /**
         * 零钱
         */
        POCKET("1"),
        /**
         * 微信-付款码
         */
        WECHAT_MICRO("21", "", ""),
        /**
         * 微信-JSAPI
         */
        WECHAT_JSAPI("22", "JSAPI", ""),
        /**
         * 微信-NATIVE
         */
        WECHAT_NATIVE("23", "NATIVE", ""),
        /**
         * 微信-APP
         */
        WECHAT_APP("24", "APP", ".app"),
        /**
         * 微信-H5
         */
        WECHAT_H5("25", "MWEB", ""),
        /**
         * 微信-小程序
         */
        WECHAT_WXA("26", "JSAPI", ".mip"),
        /**
         * 微信-人脸
         */
        WECHAT_FACE("27", "", ""),
        /**
         * 支付宝
         */
        ALIPAY("3"),
        /**
         * 银行卡
         */
        CARD("4");

        String value;
        String type;
        String appid;

        Payment(String value) {
            this.value = value;
        }

        Payment(String value, String type) {
            this.value = value;
            this.type = type;
        }

        Payment(String value, String type, String appid) {
            this.value = value;
            this.type = type;
            this.appid = appid;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public String type() {
            return this.type;
        }

        public String appid() {
            return this.appid;
        }
    }

    /**
     * 日志表状态
     */
    interface JobState {
        /**
         * 日志表状态，初始状态，插入
         */
        static final String INIT_STATS = "I";
        /**
         * 日志表状态，成功
         */
        static final String SUCCESS_STATS = "S";
        /**
         * 日志表状态，失败
         */
        static final String ERROR_STATS = "E";
        /**
         * 日志表状态，未执行
         */
        static final String UN_STATS = "N";
    }

    /**
     * 短信验证码类型
     */
    public interface MsgChkType {
        /**
         * 注册
         */
        static final String REGISTER = CACHE_NAMESPACE + "REGISTER:";
        /**
         * 登录
         */
        static final String LOGIN = CACHE_NAMESPACE + "LOGIN:";
        /**
         * 修改密码验证码
         */
        static final String CHGPWD = CACHE_NAMESPACE + "CHGPWD:";
        /**
         * 身份验证验证码
         */
        static final String VLDID = CACHE_NAMESPACE + "VLDID:";
        /**
         * 信息变更验证码
         */
        static final String CHGINFO = CACHE_NAMESPACE + "CHGINFO:";
        /**
         * 活动确认验证码
         */
        static final String AVTCMF = CACHE_NAMESPACE + "AVTCMF:";
    }

    public interface Times {
        /**
         * 一秒
         */
        static final long SECOND = 1000;
        /**
         * 一分钟
         */
        static final long MINUTE = SECOND * 60;
        /**
         * 一小时
         */
        static final long HOUR = MINUTE * 60;
        /**
         * 一天
         */
        static final long DAY = HOUR * 24;
        /**
         * 一周
         */
        static final long WEEK = DAY * 7;
        /**
         * 一年
         */
        static final long YEAR = DAY * 365;
    }


    /**
     * 密码长度限制
     */
    public interface PwLimit{
        /**
         * 3位 下限
         */
        static final int LOWER_LIMIT = 6;
        /**
         * 3位 上限
         */
        static final int UPPER_LIMIT = 20;

    }
    /**
     * 账户长度限制
     */
    public interface AccountLimit{
        /**
         * 2位 下限
         */
        static final int LOWER_LIMIT = 2;
        /**
         * 3位 上限
         */
        static final int UPPER_LIMIT = 20;

    }

    /**
     * License数据
     */
    public interface LicenseFile{
        static final long DEFAULT_MAX_SIZE = 5 * 1024;
    }
}
