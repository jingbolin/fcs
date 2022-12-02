//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.service;

import com.yinhe.ec.cpps.model.Schedule;
import com.yinhe.ec.cpps.model.ScheduleUserList;
import com.yinhe.ec.cpps.user.dao.UserScheduleDao;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserScheduleService {
    @Resource
    private UserScheduleDao userScheduleDao;

    public UserScheduleService() {
    }

    public List<Schedule> getUserScheduleHistory(String condition) {
        return this.userScheduleDao.getUserScheduleHistory(condition);
    }

    public List<ScheduleUserList> getUserScheduleById(String scheduleId) {
        return this.userScheduleDao.getUserScheduleById(scheduleId);
    }

//    @BussAnnotation(
//            moduleName = "单户调价",
//            option = "调价"
//    )
    public String addUserSchedule(Schedule schedule, String userIds, String meterNos) {
        try {
            this.userScheduleDao.addUserSchedule(schedule, userIds, meterNos);
            return "新增成功！";
        } catch (DataAccessException var5) {
            var5.printStackTrace();
            return var5.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "单户调价",
//            option = "删除"
//    )
    public String delUserScheduleById(String scheduleId) {
        try {
            Integer res = this.userScheduleDao.delUserScheduleById(scheduleId);
            return res == 0 ? "删除调价方案失败" : "删除调价方案成功";
        } catch (DataAccessException var3) {
            return var3.getMessage();
        }
    }
}
