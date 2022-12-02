//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.service;

import com.yinhe.ec.cpps.dto.UserParkDTO;
import com.yinhe.ec.cpps.model.ParkInfo;
import com.yinhe.ec.cpps.model.UserParkInfo;
import com.yinhe.ec.cpps.user.dao.ParkInfoDao;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkInfoService {
    @Resource
    private ParkInfoDao parkInfoDao;

    public ParkInfoService() {
    }

//    @BussAnnotation(
//            moduleName = "车位管理",
//            option = "新增"
//    )
    public String AddParkInfo(ParkInfo parkInfo) {
        try {
            this.parkInfoDao.AddParkInfo(parkInfo);
            return "新增成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "车位管理",
//            option = "修改"
//    )
    public String EditParkInfo(ParkInfo parkInfo) {
        try {
            this.parkInfoDao.EditParkInfo(parkInfo);
            return "修改成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "车位管理",
//            option = "删除"
//    )
    public String DelParkInfo(int parkId) {
        try {
            return this.parkInfoDao.DelParkInfo(parkId) == 0 ? "该车未已被使用，不允许删除！" : "删除成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

    public List<ParkInfo> getParkInfo(int custId) {
        return this.parkInfoDao.getParkInfo(custId);
    }

    public Map<Object, Object> getParkInfoMap(int page, int row, String condition) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.parkInfoDao.getTotal(condition));
        map.put("rows", this.parkInfoDao.getParkInfo(page, row, condition));
        return map;
    }

    public ParkInfo getParkInfoById(int custId, int parkId) {
        return this.parkInfoDao.getParkInfoById(custId, parkId);
    }

//    @BussAnnotation(
//            moduleName = "车位管理",
//            option = "绑定"
//    )
    public String AddUserParkInfo(UserParkInfo userParkInfo) {
        try {
            this.parkInfoDao.AddUserParkInfo(userParkInfo);
            return "绑定成功！";
        } catch (DataAccessException var3) {
            System.out.println("------------");
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "车位管理",
//            option = "修改绑定"
//    )
    public String EditUserParkInfo(UserParkInfo userParkInfo) {
        try {
            this.parkInfoDao.EditUserParkInfo(userParkInfo);
            return "修改成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "车位管理",
//            option = "删除绑定"
//    )
    public String DelUserParkInfo(int parkId, String userId) {
        try {
            this.parkInfoDao.DelUserParkInfo(parkId, userId);
            return "删除成功！";
        } catch (DataAccessException var4) {
            var4.printStackTrace();
            return var4.getMessage();
        }
    }

    public UserParkDTO getUserInfoByParkId(int custId, int parkId) {
        return this.parkInfoDao.getUserInfoByParkId(custId, parkId);
    }

    public String EditCarCycle(String userId, String parkId, String startDate, String endDate) {
        try {
            this.parkInfoDao.EditCarCycle(userId, parkId, startDate, endDate);
            return "修改成功！";
        } catch (DataAccessException var6) {
            var6.printStackTrace();
            return var6.getMessage();
        }
    }
}
