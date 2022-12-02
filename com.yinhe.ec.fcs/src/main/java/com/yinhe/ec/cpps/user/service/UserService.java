//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.service;

import com.yinhe.ec.cpps.dto.UserInfoViewDTO;
import com.yinhe.ec.cpps.model.FeeDetail;
import com.yinhe.ec.cpps.model.UserInfo;
import com.yinhe.ec.cpps.model.UserInfoView;
import com.yinhe.ec.cpps.user.dao.UserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public UserService() {
    }

    public UserInfo getUserInfoByUserId(String userId) {
        return this.userDao.getUserInfoByUserId(userId);
    }

    public Map<Object, Object> getUser(int page, int row, String condition, int operatorId) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.userDao.getTotal(condition));
        map.put("rows", this.userDao.getUser(page, row, condition, operatorId));
        return map;
    }

    public String getUserId(int operatorId) {
        return this.userDao.getUserId(operatorId);
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "新增用户档案"
//    )
    public String addUser(UserInfoView userInfo) {
        try {
            Integer res = this.userDao.addUser(userInfo);
            return res == 0 ? "新增用户档案失败！" : "新增用户档案成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "修改用户档案"
//    )
    public String editUser(UserInfoView userInfo) {
        try {
            Integer res = this.userDao.editUser(userInfo);
            return res == 0 ? "修改用户档案失败！" : "修改用户档案成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "删除用户档案"
//    )
    public String delUserById(String userId) {
        try {
            Integer res = this.userDao.delUserById(userId);
            if (res == 0) {
                return "删除用户档案失败！";
            } else {
                return res == 2 ? "该用户已缴费，不能被删除！" : "删除用户档案成功！";
            }
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

    public Map<Object, Object> getPayFeeDetailByUserId(String userId) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.userDao.getPayFeeDetailByUserIdTotal(userId));
        map.put("rows", this.userDao.getPayFeeDetailByUserId(userId));
        map.put("footer", this.userDao.getgetPayFeeDetailByUserIdForFooter(userId));
        return map;
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "启用收费"
//    )
    public String userStartFee(String userId, String meterNo) {
        try {
            Integer res = this.userDao.userStartFee(userId, meterNo);
            return res == 0 ? "缴费启用失败！" : "缴费启用成功！";
        } catch (DataAccessException var4) {
            return var4.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "暂停缴费"
//    )
    public String userSuspendFee(String userId, String meterNo) {
        try {
            Integer res = this.userDao.userSuspendFee(userId, meterNo);
            return res == 0 ? "暂停缴费操作失败！" : "暂停缴费操作成功！";
        } catch (DataAccessException var4) {
            return var4.getMessage();
        }
    }

    public Map<Object, Object> getUserForWy(int page, int row, String condition, int operatorId) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.userDao.getTotalForWy(condition));
        map.put("rows", this.userDao.getUserForWy(page, row, condition, operatorId));
        return map;
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "新增用户档案"
//    )
    public String addUserForWy(UserInfoViewDTO userInfo) {
        try {
            Integer res = this.userDao.addUserForWy(userInfo);
            return res == 0 ? "新增用户档案失败！" : "新增用户档案成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "修改用户档案"
//    )
    public String editUserForWy(UserInfoViewDTO userInfo) {
        try {
            Integer res = this.userDao.editUserForWy(userInfo);
            return res == 0 ? "修改用户档案失败！" : "修改用户档案成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "删除用户档案"
//    )
    public String delUserByIdForWy(String userId) {
        try {
            Integer res = this.userDao.delUserByIdForWy(userId);
            if (res == 0) {
                return "删除用户档案失败！";
            } else {
                return res == 2 ? "该用户已缴费，不能被删除！" : "删除用户档案成功！";
            }
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

    public List<UserInfoView> getUserDetailByUserId(String userId) {
        return this.userDao.getUserDetailByUserId(userId);
    }

    public List<UserInfoView> getUserMeterNoByUserId(String userId) {
        return this.userDao.getUserMeterNoByUserId(userId);
    }

    public Map<Object, Object> getWyfAndQnfByUserId(String userId) {
        Map<Object, Object> map = new HashMap();
        map.put("wyflist", this.userDao.getWyfListByUserId(userId));
        map.put("qnflist", this.userDao.getQnfListByUserId(userId));
        map.put("tcflist", this.userDao.getTcfListByUserId(userId));
        return map;
    }

    public Map<Object, Object> getPayFeeHisByUserIdAndType(String userId, int type) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.userDao.getPayFeeHisByUserIdAndTypeTotal(userId, type));
        map.put("rows", this.userDao.getPayFeeHisByUserIdAndType(userId, type));
        map.put("footer", this.userDao.getgetPayFeeHisByUserIdAndTypeForFooter(userId, type));
        return map;
    }

    public String addLsUserForWy(UserInfo userInfo) {
        try {
            Integer res = this.userDao.addLsUserForWy(userInfo);
            return res == 0 ? "新增用户档案失败！" : "新增用户档案成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

    public String editLsUserForWy(UserInfo userInfo) {
        try {
            Integer res = this.userDao.editLsUserForWy(userInfo);
            return res == 0 ? "修改用户档案失败！" : "修改用户档案成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

    public UserInfo getUserInfoByOrders(String orders) {
        return this.userDao.getUserInfoByOrders(orders);
    }

    public Map<Object, Object> getUserForSchedule(int page, int row, String condition, int typeId) {
        Map<Object, Object> map = new HashMap();
        map.put("total", this.userDao.getTotalForSchedule(condition, typeId));
        map.put("rows", this.userDao.getUserForSchedule(page, row, condition, typeId));
        return map;
    }

    public List<FeeDetail> getUserHffInfoByUserId(String userId, String meterNo) {
        return this.userDao.getUserHffInfoByUserId(userId, meterNo);
    }

    public String errMarkMeter(String meterNo, int errMark) {
        try {
            this.userDao.errMarkMeter(meterNo, errMark);
            return "特殊标记成功！";
        } catch (DataAccessException var4) {
            return "特殊标记失败！" + var4.getMessage();
        }
    }

    public String editUserKuozhanForWy(UserInfo userInfo) {
        try {
            this.userDao.editUserKuozhanForWy(userInfo);
            return "修改用户扩展信息成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "单独增加表具"
//    )
    public String addSigleMeterForWy(UserInfoViewDTO userInfo) {
        try {
            Integer res = this.userDao.addSigleMeterForWy(userInfo);
            return res == 0 ? "新增表具失败！" : "新增表具成功！";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "用户档案管理",
//            option = "删除仪表信息"
//    )
    public String delMeterInfoByMeterNo(String meterNo) {
        try {
            Integer res = this.userDao.delMeterInfoByMeterNo(meterNo);
            if (res == 0) {
                return "删除表具信息失败！";
            } else {
                return res == 2 ? "该表具已缴费，不能被删除！" : "删除表具信息成功！";
            }
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }
}
