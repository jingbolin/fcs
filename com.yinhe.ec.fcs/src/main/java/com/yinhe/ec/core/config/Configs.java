package com.yinhe.ec.core.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TimeZone;

import com.yinhe.ec.core.util.SecurityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.util.DataUtil;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.core.util.PropertiesUtil;

@Configuration
public class Configs implements EnvironmentPostProcessor, Ordered {
    
    private Logger logger = LogManager.getLogger();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        String[] profiles = environment.getActiveProfiles();
        Properties props = getConfig(profiles);
        propertySources.addLast(new PropertiesPropertySource("thirdEnv", props));
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource.getSource() instanceof Map) {
                Map map = (Map)propertySource.getSource();
                for (Object key : map.keySet()) {
                    String keyStr = key.toString();
                    Object value = map.get(key);
                    // 解_密
                    if ("solar.password,data.password".contains(keyStr)) {
                        String dkey = (String)map.get("sql.key");
                        dkey = DataUtil.isEmpty(dkey) ? Constants.DB_KEY : dkey;
                        value = SecurityUtil.decryptDes(value.toString(), dkey.getBytes());
                        map.put(key, value);
                    }
                    // 动态时区设置
                    if ("solar.url,data.url".contains(keyStr)) {
                        if (!value.toString().contains("&serverTimezone")) {
                            String timeZone = TimeZone.getDefault().getID();
                            value = value + "&serverTimezone=" + timeZone;
                            map.put(key, value);
                        }
                    }
                    PropertiesUtil.getProperties().put(keyStr, value.toString());
                }
            }
            for (Entry<String, String> e : PropertiesUtil.getProperties().entrySet()) {
                if ("druid.writer.url".equals(e.getKey())) {
                    System.out.println("加载的属性 " + e.getKey() + " : " + e.getValue());
                }
            }
        }
        logger.info("Read configuration file finished.");
    }

    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }

    // 加载配置文件
    private Properties getConfig(String[] profiles) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Resource> resouceList = InstanceUtil.newArrayList();
        addResources(resolver, resouceList, "classpath*:config/*.properties");
        if (profiles != null) {
            for (String p : profiles) {
                if (DataUtil.isNotEmpty(p)) {
                    p = p + "/";
                }
                addResources(resolver, resouceList, "classpath*:config/" + p + "*.properties");
            }
        }
        try {
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            config.setLocations(resouceList.toArray(new Resource[] {}));
            config.afterPropertiesSet();
            return config.getObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 加载配置文件
    private void addResources(PathMatchingResourcePatternResolver resolver, List<Resource> resouceList, String path) {
        try {
            Resource[] resources = resolver.getResources(path);
            for (Resource resource : resources) {
                resouceList.add(resource);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public static void main(String[] args) {
        String encrypt = SecurityUtil.encryptDes("ecloud2019", Constants.DB_KEY.getBytes());
        System.out.println(encrypt);
        System.out.println(SecurityUtil.decryptDes(encrypt, Constants.DB_KEY.getBytes()));
    }
}
