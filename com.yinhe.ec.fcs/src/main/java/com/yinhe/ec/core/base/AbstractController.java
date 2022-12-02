/**
 *
 */
package com.yinhe.ec.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinhe.ec.core.Constants;
import com.yinhe.ec.core.support.Pagination;
import com.yinhe.ec.core.support.context.ApplicationContextHolder;
import com.yinhe.ec.core.support.http.HttpCode;
import com.yinhe.ec.core.support.http.SessionUser;
import com.yinhe.ec.core.support.shiro.RedisSessionDAO;
import com.yinhe.ec.core.util.InstanceUtil;
import com.yinhe.ec.core.util.ThreadUtil;
import com.yinhe.ec.core.util.WebUtil;
import com.yinhe.ec.cpps.sys.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 控制器基类
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:47:58
 */
public abstract class AbstractController implements InitializingBean {
    protected Logger logger = LogManager.getLogger();
    private RedisSessionDAO redisSessionDAO;



    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] fields = getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object v = field.get(this);
                Class<?> cls = field.getType();
                if (v == null && cls.getSimpleName().toLowerCase().contains("service")) {
                    v = ApplicationContextHolder.getService(cls);
                    field.set(this, v);
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            logger.error("", e);
            ThreadUtil.sleep(1, 5);
            afterPropertiesSet();
        }
    }

    /** 获取当前用户Id(shiro) */
    protected SessionUser getCurrUser() {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SessionUser sessionUser=null;
        if(null!=request){
            sessionUser=getCurrUser(request);
        }
        return sessionUser;
    }

    /** 获取当前用户 */
    protected SessionUser getCurrUser(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) WebUtil.getCurrentUser(request);
        if (null != sessionUser) {
            return sessionUser;
        }
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            Map<String, String> cookieMap = Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
            String ecloudsessionId = cookieMap.get("ECLOUDSESSIONID");
            if (StringUtils.isNotEmpty(ecloudsessionId)) {
                if (null == redisSessionDAO) {
                    BeanFactory factory = WebApplicationContextUtils
                            .getRequiredWebApplicationContext(request.getServletContext());
                    redisSessionDAO = factory.getBean(RedisSessionDAO.class);
                }
                Session session = redisSessionDAO.readSession(ecloudsessionId);
                if (null != session) {
                    User user = (User) session.getAttribute(Constants.CURRENT_USER);
                    if (null != user && null != user.getId()) {
                        sessionUser = new SessionUser();
                        sessionUser.setId(user.getId());
                        //sessionUser.setUserName(user.getUserName());
                        sessionUser.setAccount(user.getAccount());
                        sessionUser.setUser(user);
                        WebUtil.saveCurrentUser(request, sessionUser);
                        return sessionUser;
                    }
                }
            }
        }
        return null;
    }
    /** 获取当前用户Id */
    protected Long getCurrUserId(HttpServletRequest request) {
        SessionUser sessionUser = getCurrUser(request);
        if(null!=sessionUser){
            return sessionUser.getId();
        }
        return null;
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap() {
        return setSuccessModelMap(new ModelMap(), null);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(Object data) {
        return setModelMap(new ModelMap(), HttpCode.OK, data);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(HttpCode code) {
        return setModelMap(new ModelMap(), code, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(String code, String msg) {
        return setModelMap(new ModelMap(), code, msg, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(HttpCode code, Object data) {
        return setModelMap(new ModelMap(), code, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(String code, String msg, Object data) {
        return setModelMap(new ModelMap(), code, msg, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        return setModelMap(modelMap, code.value().toString(), code.msg(), data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, String code, String msg, Object data) {
        if (!modelMap.isEmpty()) {
            Map<String, Object> map = InstanceUtil.newLinkedHashMap();
            map.putAll(modelMap);
            modelMap.clear();
            for (Entry<String, Object> entry : map.entrySet()) {
                if (!entry.getKey().startsWith("org.springframework.validation.BindingResult")
                        && !"void".equals(entry.getKey())) {
                    modelMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (data != null) {
            if (data instanceof Pagination<?>) {
                Pagination<?> page = (Pagination<?>) data;
                modelMap.put("rows", page.getRecords());
                modelMap.put("current", page.getCurrent());
                modelMap.put("size", page.getSize());
                modelMap.put("pages", page.getPages());
                modelMap.put("total", page.getTotal());
            } else if (data instanceof Page<?>) {
                Page<?> page = (Page<?>) data;
                modelMap.put("rows", page.getRecords());
                modelMap.put("current", page.getCurrent());
                modelMap.put("size", page.getSize());
                modelMap.put("pages", page.getPages());
                modelMap.put("total", page.getTotal());
            } else if (data instanceof List<?>) {
                modelMap.put("rows", data);
                modelMap.put("total", ((List<?>) data).size());
            } else {
                modelMap.put("data", data);
            }
        }
        modelMap.put("code", code);
        modelMap.put("msg", msg);
        modelMap.put("timestamp", System.currentTimeMillis());

        logger.debug(JSON.toJSONString(modelMap));
        String jsonString = JSON.toJSONString(modelMap);
        logger.info("response===>{}", jsonString.length() > 1000 ? jsonString.substring(0, 1000) + ".." : jsonString);
        return ResponseEntity.ok(modelMap);
    }

    /**
     * 设置创建人
     * @param request
     * @param t
     * @param <T>
     */
    public <T extends BaseModel> void  setCreate(HttpServletRequest request,T t,Long userId){
        if(null==userId){
            userId=getCurrUserId(request);
        }
        t.setCreateBy(userId);
        t.setCreateTime(new Date());
    }

    /**
     * 设置更新人
     * @param request
     * @param t
     * @param <T>
     */
    public <T extends BaseModel> void  setUpdate(HttpServletRequest request,T t,Long userId){
        if(null==userId){
            userId=getCurrUserId(request);
        }
        t.setUpdateBy(userId);
        t.setUpdateTime(new Date());
    }
    /**
     * 处理列表分页
     * @param paramJsonObject 前端页面传来的参数
     * @param parmaDto 对象
     * @param service service服务接口
     * @param methodName 方法名
     * @return
     */
    public Object handlerPage(JSONObject paramJsonObject, Object parmaDto, Object service, String methodName) {
        Integer pageNum = (null == paramJsonObject.getInteger("pageNum")) ? 1 : paramJsonObject.getInteger("pageNum");
        Integer pageSize = (null == paramJsonObject.getInteger("pageSize")) ? 10 : paramJsonObject.getInteger("pageSize");
        Object bean = JSONObject.parseObject(paramJsonObject.toJSONString(), parmaDto.getClass());
        try {
            Method method = service.getClass().getMethod(methodName, Integer.class, Integer.class, parmaDto.getClass());
            return method.invoke(service, pageNum, pageSize, bean);
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}
