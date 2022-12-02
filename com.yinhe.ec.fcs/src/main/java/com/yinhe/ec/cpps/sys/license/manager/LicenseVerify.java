package com.yinhe.ec.cpps.sys.license.manager;

import com.yinhe.ec.cpps.sys.license.model.CustomKeyStoreParam;
import com.yinhe.ec.cpps.sys.license.model.LicenseVerifyParam;
import de.schlichtherle.license.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.util.NestedServletException;

import java.io.File;
import java.security.KeyStoreException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.prefs.Preferences;

/**
 * License校验类
 */
@Component
public class LicenseVerify {
    private static Logger logger = LogManager.getLogger(LicenseVerify.class);


    /**
     * 安装License证书
     */
    public synchronized LicenseContent install(LicenseVerifyParam param) {
        LicenseContent result = null;
        //1. 安装证书
        try {
            LicenseParam licenseParam = initLicenseParam(param);
            //管理基类
            LicenseManager licenseManager = LicenseManagerHolder.getInstance(licenseParam);
            //卸载当前的许可证密钥
            licenseManager.uninstall();
            //获取license文件路径
            String licensePath = param.getLicensePath();
          //  URL resource = LicenseVerify.class.getResource(licensePath);
            result = licenseManager.install(new File(licensePath));
            logger.info("证书安装成功");
        } catch (KeyStoreException e) {
            logger.error("密钥库异常！安装失败",e);
        }catch (AssertionError e) {
            logger.error("断言错误！安装失败",e);
        } catch (NestedServletException e) {
            logger.error("证书安装失败！",e);
        } catch (IllegalArgumentException e) {
            logger.error("解析公钥失败，请确认公钥文件！",e);
         } catch (Exception e) {
            logger.error("证书安装失败！",e);
        }

        return result;
    }

    /**
     * 校验License证书
     *
     * @return boolean
     * @since 1.0.0
     */
    public boolean verify() {
        LicenseManager licenseManager = LicenseManagerHolder.getInstance(null);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //2. 校验证书
        try {
            LicenseContent licenseContent = licenseManager.verify();
            if (licenseContent.getNotBefore() != null && licenseContent.getNotAfter() == null) {
                logger.info(MessageFormat.format("证书校验通过，证书有效期：{0} - {1}", format.format(licenseContent.getNotBefore()), "无限期"));
            }
            if (licenseContent.getNotAfter() != null && licenseContent.getNotBefore() != null) {
                logger.info(MessageFormat.format("证书校验通过，证书有效期：{0} - {1}", format.format(licenseContent.getNotBefore()), format.format(licenseContent.getNotAfter())));
            }
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常！",e);
            return false;
        }catch (Exception e) {
            logger.error("证书校验失败！",e);
            return false;
        }
    }

    /**
     * 初始化证书生成参数
     *
     * @param param License校验类需要的参数
     * @return
     */
    private LicenseParam initLicenseParam(LicenseVerifyParam param) {
        Preferences preferences = Preferences.userNodeForPackage(LicenseVerify.class);

        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());

        KeyStoreParam publicStoreParam = new CustomKeyStoreParam(LicenseVerify.class
                , param.getPublicKeysStorePath()
                , param.getPublicAlias()
                , param.getStorePass()
                , null);
        LicenseParam licenseParam = new DefaultLicenseParam(param.getSubject()
                , preferences
                , publicStoreParam
                , cipherParam);

        return licenseParam;
    }

}
