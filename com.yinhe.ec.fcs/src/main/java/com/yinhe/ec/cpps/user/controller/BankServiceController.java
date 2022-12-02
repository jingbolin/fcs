//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.controller;

import com.yinhe.ec.cpps.model.CebDuizhanghuizong;
import com.yinhe.ec.cpps.user.service.BankService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class BankServiceController {
    @Resource
    private BankService bankService;
    private static Logger logger = Logger.getLogger(BankServiceController.class);

    public BankServiceController() {
    }

    @RequestMapping(
            value = {"/api/userBill"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public void userBill(HttpServletRequest request, HttpServletResponse response) {
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        logger.info("接收到源字符串：" + contentStr.trim());
        String msgStr = this.bankService.userBill(contentStr.trim());
        logger.info("返回Base64编码字符串：" + msgStr);
        response.setContentType("application/json;charset=utf-8");

        try {
            response.getWriter().write(msgStr);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    @RequestMapping(
            value = {"/api/userPay"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public void userPay(HttpServletRequest request, HttpServletResponse response) {
        int len = request.getContentLength();
        logger.info("BBBBBB--->" + len);
        String contentStr = "";

        try {
            BufferedReader br = request.getReader();

            for(String str = ""; (str = br.readLine()) != null; contentStr = contentStr + str) {
            }
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        logger.info("接收到源字符串：" + contentStr.trim());
        String msgStr = this.bankService.userPay(contentStr.trim());
        response.setContentType("application/json;charset=utf-8");

        try {
            response.getWriter().write(msgStr);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    @RequestMapping({"/api/readCsv"})
    @ResponseBody
    public void readCsv(String date, int type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("--->>对账日期：" + date.trim());
        List<String> list = this.bankService.readCsv(date, type);
        String msgStr = "总行数：" + list.size() + "<br>";

        for(int i = 0; i < list.size(); ++i) {
            msgStr = msgStr + ((String)list.get(i)).toString() + "<br>";
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(msgStr);
    }

    @RequestMapping({"/api/getCebDuizhangInfo"})
    @ResponseBody
    public Map<Object, Object> getCebDuizhangInfo(int page, int rows, String orders) {
        return this.bankService.getCebDuizhangInfo(page, rows, orders);
    }

    @RequestMapping({"/api/getCebDuizhangHuizong"})
    @ResponseBody
    public List<CebDuizhanghuizong> getCebDuizhangHuizong(String startDate, String endDate, String orders) {
        return this.bankService.getCebDuizhangHuizong(startDate, endDate, orders);
    }
}
