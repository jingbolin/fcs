//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.user.service;

import com.yinhe.ec.cpps.model.Schedule;
import com.yinhe.ec.cpps.user.dao.ScheduleDao;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleService {
    @Resource
    private ScheduleDao scheduleDao;

    public ScheduleService() {
    }

    public List<Schedule> getScheduleAll(String condition) {
        return this.scheduleDao.getScheduleAll(condition);
    }

    public Schedule getScheduleById(String scheduleId) {
        return this.scheduleDao.getScheduleById(scheduleId);
    }

//    @BussAnnotation(
//            moduleName = "调价管理",
//            option = "新增"
//    )
    public String addSchedule(Schedule schedule) {
        try {
            this.scheduleDao.addSchedule(schedule);
            return "新增成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "调价设置",
//            option = "修改"
//    )
    public String modSchedule(Schedule schedule) {
        try {
            this.scheduleDao.modSchedule(schedule);
            return "修改成功！";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }

//    @BussAnnotation(
//            moduleName = "调价设置",
//            option = "删除"
//    )
    public String delSchedule(int scheduleId) {
        try {
            Integer res = this.scheduleDao.delSchedule(scheduleId);
            return res == 0 ? "删除失败" : "删除成功";
        } catch (DataAccessException var3) {
            var3.printStackTrace();
            return var3.getMessage();
        }
    }
}
