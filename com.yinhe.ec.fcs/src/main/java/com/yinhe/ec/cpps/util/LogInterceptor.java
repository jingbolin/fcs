//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import com.alibaba.fastjson.JSONArray;
import com.yinhe.ec.cpps.ipparse.IPSeeker;
import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.model.OperatorLog;
import com.yinhe.ec.cpps.system.dao.OperatorLogDao;
import com.yinhe.ec.cpps.system.dao.impl.LoginDaoImpl;
import com.yinhe.ec.cpps.system.service.GenPkIdService;
import org.apache.shiro.SecurityUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;

//@Aspect
@Component
public class LogInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    @Resource
    private OperatorLogDao operatorLogDao;
    private OperatorLog operatorLog;
    @Resource
    private GenPkIdService genPkIdservice;

    public LogInterceptor() {
    }
//
//    @Pointcut("execution( * com.yinhe.ec.cpps.*.service.*.*(..))")
//    public void aApplogic() {
//    }
//
//    @Around(
//            value = "aApplogic() && @annotation(annotation) && args(object,..)",
//            argNames = "pj,annotation,object"
//    )
//    public Object interceptorApplogic(ProceedingJoinPoint pj, BussAnnotation annotation, Object object) throws Throwable {
//        String methodName = pj.getSignature().getName();
//        String params = JSONArray.toJSONString(pj.getArgs());
//        logger.info("请求方法名:{},请求参数:{}", methodName, params);
//        if (params.length() > 800) {
//            params = params.substring(0, 800);
//        }
//
//        Object result = pj.proceed();
//        Operator opertor = (Operator)SecurityUtils.getSubject().getSession().getAttribute("Operator");
//        this.operatorLog.setOlId(this.genPkIdservice.getPkIdByTable("OperatorLog", "OLID"));
//        this.operatorLog.setOperator(opertor);
//        this.operatorLog.setOlDate(Tools.getNow());
//        this.operatorLog.setOlFmName(annotation.moduleName());
//        this.operatorLog.setOlDesc(annotation.option() + ":" + params);
//        this.operatorLog.setCustId(opertor.getCustId());
//        String loginIp = SecurityUtils.getSubject().getSession().getHost();
//        String area = "";
//
//        try {
//            String path = "";
//            path = LoginDaoImpl.class.getResource("").toString();
//            path = path.substring(5, path.indexOf("webapps")) + "/webapps/MusterList";
//            path = URLDecoder.decode(path, "utf-8");
//            IPSeeker ips = new IPSeeker("qqwry.dat", path);
//            area = ips.getIPLocation(loginIp).getCountry() + ":" + ips.getIPLocation(loginIp).getArea();
//        } catch (Exception var12) {
//        }
//
//        this.operatorLog.setOlIp(loginIp);
//        this.operatorLog.setOlArea(area);
//        this.operatorLogDao.addOperatorLog(this.operatorLog);
//        return result;
//    }
//
//    @AfterThrowing(
//            pointcut = "aApplogic()",
//            throwing = "e"
//    )
//    public void errorApplogic(DataAccessException e) {
//    }
}
