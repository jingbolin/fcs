package com.yinhe.ec.cpps.rtdp.scheduled;

import com.yinhe.ec.cpps.Constants;
import com.yinhe.ec.cpps.sys.model.ConfList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yinhe.ec.core.util.ConfigEnum;
import com.yinhe.ec.cpps.cache.ConfListCache;

/**
 * @author wangshilei
 * @date 2021/07/03
 */
@Component
@ConditionalOnProperty(name = "solar.scheduled", havingValue = "true")
public class SynchronizationDateTimeScheduled {
    
    private final Logger logger = LogManager.getLogger();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ConfListCache confListCache;

    /**
     * 定时同步时间
     */
    @Scheduled(cron = "0 0 0/1 * * *")
    public void synDateTime() {
        ConfList confList = confListCache.filterOne(e -> {
            return ConfigEnum.IS_ENABLE_SYN_DATETIME.name().equals(e.getConfListCode());
        });
        if (confList != null && "是".equals(confList.getConfListValue())) {
            long currentTimeMillis = System.currentTimeMillis();
            logger.info("同步时间!" + currentTimeMillis);
            stringRedisTemplate.delete(Constants.CHECK_TIME_TABLE);
            stringRedisTemplate.opsForList().rightPush(Constants.CHECK_TIME_TABLE, String.valueOf(currentTimeMillis));
        }
    }
}
