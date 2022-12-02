//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.yinhe.ec.cpps.model.CebDuizhanghuizong;
import com.yinhe.ec.cpps.user.dao.BankServiceDao;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    @Resource
    private BankServiceDao bankServiceDao;

    public BankService() {
    }

    public String userBill(String param) {
        return this.bankServiceDao.userBill(param);
    }

    public String userPay(String param) {
        return this.bankServiceDao.userPay(param);
    }

    public List<String> readCsv(String date, int type) {
        String fileName = "";
        if (type == 0) {
            fileName = "WY_0012100002_" + date + ".csv";
        } else {
            fileName = "WY_0012100002_YJ_" + date + ".csv";
        }

        List<String> list = new ArrayList();
        File csv = new File("E:\\ceb_cvs_files\\" + fileName);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(csv));
            String line = "";

            try {
                while((line = br.readLine()) != null) {
                    list.add(line);
                }
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        }

        this.bankServiceDao.writeCvsToDb(list);
        return list;
    }

    public Map<Object, Object> getCebDuizhangInfo(int page, int rows, String orders) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.bankServiceDao.getCebDuizhangInfoTotal(orders));
        map.put("rows", this.bankServiceDao.getCebDuizhangInfo(page, rows, orders));
        return map;
    }

    public List<CebDuizhanghuizong> getCebDuizhangHuizong(String startDate, String endDate, String orders) {
        return this.bankServiceDao.getCebDuizhangHuizong(startDate, endDate, orders);
    }
}
