//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.service;

import com.yinhe.ec.cpps.model.*;
import com.yinhe.ec.cpps.ycmeter.dao.MusterDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MusterService {
    @Resource
    private MusterDao musterDao;

    public MusterService() {
    }

    public List<ConnInfo> getConnInfo(int custId) {
        return this.musterDao.getConnInfo(custId);
    }

//    @BussAnnotation(
//            moduleName = "通讯参数管理",
//            option = "新增通讯参数"
//    )
    public String addConnInfo(ConnInfo connInfo) {
        try {
            this.musterDao.addConnInfo(connInfo);
            return "新增成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "通讯参数管理",
//            option = "修改通讯参数"
//    )
    public String modConnInfo(ConnInfo connInfo) {
        try {
            this.musterDao.modConnInfo(connInfo);
            return "修改成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "通讯参数管理",
//            option = "删除通讯参数"
//    )
    public String delConnInfoByNo(String connNo) {
        try {
            this.musterDao.delConnInfoByNo(connNo);
            return "删除成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

    public List<Muster> getMusterList(String orders) {
        orders = " 1=1 " + orders;
        return this.musterDao.getMusterList(orders);
    }

//    @BussAnnotation(
//            moduleName = "集中器管理",
//            option = "新增集中器"
//    )
    public String addMuster(Muster muster) {
        try {
            this.musterDao.addMuster(muster);
            return "新增成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "集中器管理",
//            option = "修改集中器"
//    )
    public String modMuster(Muster muster) {
        try {
            this.musterDao.modMuster(muster);
            return "修改成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "集中器管理",
//            option = "删除集中器"
//    )
    public String delMusterByNo(String musterNo) {
        try {
            this.musterDao.delMusterByNo(musterNo);
            return "删除成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "集中器管理",
//            option = "更换集中器"
//    )
    public String changeMuster(String oldMusterNo, String newMusterNo, int operatorId, int changeFlag) {
        try {
            int[] resutCount = this.musterDao.changeMuster(oldMusterNo, newMusterNo, operatorId, changeFlag);
            return resutCount[0] > 0 && resutCount[1] >= 0 && resutCount[2] > 0 ? "ok" : "Err";
        } catch (DataAccessException var6) {
            var6.printStackTrace();
            return "Err";
        }
    }

    public List<MeterInfo> getMeterListByMusterNo(String musterNo) {
        return this.musterDao.getMeterListByMusterNo(musterNo);
    }

    public Muster getMusterByMusterNo(String musterNo) {
        return this.musterDao.getMusterByMusterNo(musterNo);
    }

    public List<MusterOnline> getMusterOnlineInfo() {
        return this.musterDao.getMusterOnlineInfo();
    }

    public List<MusterProtocol> getMusterProtocol() {
        return this.musterDao.getMusterProtocol();
    }

    public List<MeterProtocol> getMeterProtocol() {
        return this.musterDao.getMeterProtocol();
    }

    public String getXuniMusterNo() {
        return this.musterDao.getXuniMusterNo();
    }
}
