package com.yinhe.ec.cpps.sys.license.manager;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
import org.springframework.stereotype.Component;

/**
 * de.schlichtherle.license.LicenseManager的单例
 *
 * @date 2021/5/6
 * @since 1.0.0
 */
@Component
public class LicenseManagerHolder {

    private static volatile LicenseManager LICENSE_MANAGER;

    public static LicenseManager getInstance(LicenseParam param) {
        if (LICENSE_MANAGER == null) {
            synchronized (LicenseManagerHolder.class) {
                if (LICENSE_MANAGER == null) {
                    LICENSE_MANAGER = new CustomLicenseManager(param);
                }
            }
        }

        return LICENSE_MANAGER;
    }

}
