//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Config {
    private static final Properties properties;
    private static Logger logger = LoggerFactory.getLogger(Config.class);

    static {
        InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties");
        properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException var2) {
            logger.error("init config error", var2);
        }

    }

    public Config() {
    }

    public static List<String> getStringList(String conf) {
        return Arrays.asList(StringUtils.split(properties.getProperty(conf), ","));
    }

    public static String getString(String conf) {
        return properties.getProperty(conf);
    }

    public static int getInt(String conf) {
        int ret = 0;

        try {
            ret = Integer.parseInt(getString(conf));
        } catch (NumberFormatException var3) {
            logger.error("format error", var3);
        }

        return ret;
    }

    public static boolean getBoolean(String conf) {
        return Boolean.parseBoolean(getString(conf));
    }
}
