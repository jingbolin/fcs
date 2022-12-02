package com.yinhe.ec.core.interceptor;

import com.yinhe.ec.core.support.http.HttpCode;
import com.yinhe.ec.core.util.PropertiesUtil;
import com.yinhe.ec.core.util.WebUtil;
import com.yinhe.ec.cpps.sys.license.manager.LicenseVerify;
import com.yinhe.ec.cpps.sys.license.model.LicenseVerifyParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.system.ApplicationHome;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LicenseCheckInterceptor extends BaseInterceptor {
    private static Logger logger = LogManager.getLogger(LicenseCheckInterceptor.class);

    /**
     * 证书subject
     */
    private String subject = PropertiesUtil.getString("license.subject");

    /**
     * 公钥别称
     */
    private String publicAlias = PropertiesUtil.getString("license.publicAlias");

    /**
     * 访问公钥库的密码
     */
    private String storePass = PropertiesUtil.getString("license.storePass");

    /**
     * 证书生成路径
     */
    private String licensePath = PropertiesUtil.getString("license.licensePath");

    /**
     * 密钥库存储路径
     */
    private String publicKeysStorePath = PropertiesUtil.getString("license.publicKeysStorePath");

    /**
     * 密钥库存储路径
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!PropertiesUtil.getBoolean("license.enable")) {
            // 开发环境不校验
            return true;
        }
        logger.info("++++++++ 开始安装证书 ++++++++");
        ApplicationHome h = new ApplicationHome(Object.class.getClass());
        String path = h.toString();
       String licensepa= path+"/../"+licensePath;

        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject(subject);
        param.setPublicAlias(publicAlias);
        param.setStorePass(storePass);
        param.setLicensePath(licensepa);
        param.setPublicKeysStorePath(publicKeysStorePath);
        logger.info("公钥路径："+publicKeysStorePath);
        logger.info("license路径："+licensepa);
        LicenseVerify licenseVerify = new LicenseVerify();
        //安装证书
        licenseVerify.install(param);
        logger.info("++++++++ 证书安装结束 ++++++++");
        //校验证书是否有效
        boolean verifyResult = licenseVerify.verify();
        if (!verifyResult) {
            return WebUtil.write(response, HttpCode.LICENSE_INVALID.value(), "您的证书无效或者不存在，请核查运维人员是否取得授权或重新申请证书!");
        } else {
            return true;
        }
    }
}
