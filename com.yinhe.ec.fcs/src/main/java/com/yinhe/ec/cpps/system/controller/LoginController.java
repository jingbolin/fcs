package com.yinhe.ec.cpps.system.controller;

import com.yinhe.ec.cpps.model.Operator;
import com.yinhe.ec.cpps.system.service.OperatorService;
import com.yinhe.ec.cpps.util.MD5;
import com.yinhe.ec.cpps.util.Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Controller
public class LoginController {
    @Resource
    private OperatorService operatorService;

    public LoginController() {
    }

//    @RequestMapping({"/login/operatorLogin"})
    public String operatorLogin(String operatorAccount, String password, HttpServletRequest request) {
        String regCode = "";
        String result = "login";
        if (request.getSession().getAttribute("Operator") != null) {
            Operator operator = (Operator)request.getSession().getAttribute("Operator");
            regCode = operator.getCust().getRegCode();
            if (operator.getOperatorAccount().equals(operatorAccount)) {
                result = "index";
            } else {
                request.setAttribute("loginError", "另一个用户正在登录，请关闭浏览器重新打开登录！");
                result = "login";
            }

            try {
                if (operator.getCust().getRegCode().length() != 23 || !regCode.substring(0, 12).equals(Tools.getMacByIp().toUpperCase()) || !regCode.substring(21, 23).equals("FE")) {
                    return "regist";
                }
            } catch (IOException var10) {
                request.setAttribute("loginError", var10.getMessage());
                result = "login";
            }

            try {
                if (Tools.daysBetween(operator.getCust().getRegEndDate(), Tools.getForNRDay(0)) > 1) {
                    return "regist";
                }
            } catch (Exception var9) {
                request.setAttribute("loginError", var9.getMessage());
                result = "login";
            }

            return result;
        } else if (operatorAccount != null && password != null) {
            if (!operatorAccount.trim().equals("") && !password.trim().equals("")) {
                if (request.getParameter("vCode") != null) {
                    String vCode = request.getParameter("vCode").toLowerCase();
                    if (vCode.equals("")) {
                        request.setAttribute("loginError", "您的[验证码不正确]！");
                        result = "login";
                        return result;
                    }

                    if (request.getSession().getAttribute("code") == null) {
                        request.setAttribute("loginError", "您的[验证码不正确]！");
                        result = "login";
                        return result;
                    }

                    String rand = request.getSession().getAttribute("code").toString().toLowerCase();
                    if (!vCode.equals(rand)) {
                        request.setAttribute("loginError", "您的[验证码不正确]！");
                        result = "login";
                        return result;
                    }
                }

                UsernamePasswordToken token = new UsernamePasswordToken(operatorAccount.trim(), MD5.getDigestedString(password));
                Subject currentUser = SecurityUtils.getSubject();

                try {
                    if (!currentUser.isAuthenticated()) {
                        token.setRememberMe(true);
                        token.setHost(request.getParameter("ipAddress"));
                        currentUser.login(token);
                        currentUser.hasRole("");
                        Operator operator = this.operatorService.getOperatorByAccount(operatorAccount);
                        currentUser.getSession().setAttribute("Operator", operator);
                        regCode = operator.getCust().getRegCode();
                        if (regCode.length() != 23 || !regCode.substring(0, 12).equals(Tools.getMacByIp().toUpperCase()) || !regCode.substring(21, 23).equals("FE")) {
                            return "regist";
                        }

                        if (Tools.daysBetween(operator.getCust().getRegEndDate(), Tools.getForNRDay(0)) > 1) {
                            return "regist";
                        }
                    }

                    result = "index";
                } catch (Exception var11) {
                    var11.getMessage();
                    request.setAttribute("loginError", var11.getMessage());
                    result = "login";
                }

                return result;
            } else {
                request.setAttribute("loginError", "您的[用户名]或者[密码]必须填写！");
                result = "login";
                return result;
            }
        } else {
            result = "login";
            return result;
        }
    }

//    @RequestMapping({"/login/cancelLogin"})
    public String cancelLogin() {
        Subject currentUser = SecurityUtils.getSubject();
        if (SecurityUtils.getSubject().getSession() != null) {
            currentUser.logout();
        }

        return "redirect:/";
    }

//    @RequestMapping({"/login/app_operatorLogin"})
    public String app_operatorLogin(String operatorAccount, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String msg = "";
        Operator operator = this.operatorService.getOperatorByAccount(operatorAccount);
        if (operator != null) {
            if (operator.getOperatorPwd().equals(MD5.getDigestedString(password))) {
                msg = "login_callback({msg:'true',operatorId:'" + operator.getOperatorId() + "',operatorAccount:'" + operator.getOperatorAccount() + "',companyId:'" + operator.getCustId() + "',departId:'" + operator.getDept().getDepartId() + "'})";
            } else {
                msg = "login_callback({msg:'密码错误'})";
            }
        } else {
            msg = "login_callback({msg:'账号不存在'})";
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        return null;
    }
}
