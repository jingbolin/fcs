package com.yinhe.ec.core.base;

import com.yinhe.ec.core.util.InstanceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @param <T>
 * @param <S>
 * @author ShenHuaJie
 * @since 2019年4月4日 下午2:57:33
 */
public abstract class AppBaseController<T extends BaseModel, S extends BaseService<T>> extends BaseController<T, S> {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public Object update(HttpServletRequest request, T param) {
        return update(request, new ModelMap(), param);
    }

    public Object update(HttpServletRequest request, ModelMap modelMap, T param) {
        Long userId = getCurrUserId(request);
        if (param.getId() == null) {
            param.setCreateBy(userId);
            param.setCreateTime(new Date());
        }
        param.setUpdateBy(userId);
        param.setUpdateTime(new Date());
        Object record = service.update(param);
        Map<String, Object> result = InstanceUtil.newHashMap("bizeCode", 1);
        result.put("record", record);
        return setSuccessModelMap(modelMap, result);
    }
}
