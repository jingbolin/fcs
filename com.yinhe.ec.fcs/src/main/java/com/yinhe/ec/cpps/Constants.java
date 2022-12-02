package com.yinhe.ec.cpps;

import cn.hutool.core.map.MapUtil;
import com.yinhe.ec.core.base.BaseModel;
//import com.yinhe.ec.cpps.mon.model.*;
//import com.yinhe.ec.cpps.mon.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * svg模块常量
 *
 * @author wangshilei
 * @date 2021/03/30
 */
public class Constants {

    /**↓↓↓↓↓↓ svg监控相关Redis表名 ↓↓↓↓↓↓↓↓**/

    /**
     * 实时数据表，表类型为Map，Map的key为测点ID，Value为测点值
     **/
    public final static String REAL_TIME_TABLE = "RealTimeTable";

    /**
     * 缓存键值名命空间，表类型为Map，key为存储的信息标识，Value为存储的String值
     **/
    public final static String CALC_DATA = "CalcData";

    /** 实时数据表，组串异常监视表 **/
    public final static String SERIES_ABNORMAL_MONITOR = "SeriesAbnormalMonitor";

    /** 实时数据表，逆变器的等效时top10 **/
    public final static String EQUIVALENT_TIME_TOP = "EquivalentTimeTop";

    /** 实时数据表，逆变器的等效时last10 **/
    public final static String EQUIVALENT_TIME_LAST = "EquivalentTimeLast";

    /**
     * 存储测点表，首页、组串监视等页面测点值
     **/
    public final static String CALC_TEMP_TABLE = "CalcTempTable";

    /**
     * 告警表，表类型为List
     **/
    public final static String ALARM_TABLE = "AlarmTable";

    /**
     * 命令表，表类型为List
     **/
    public final static String CMD_TABLE = "CmdTable";

    /**
     * 命令反校表，表类型为List
     **/
    public final static String CMD_REPLY_TABLE = "CmdReplyTable";

    /** 时间同步表 **/
    public final static String CHECK_TIME_TABLE = "CheckTimeTable";

    /**↓↓↓↓↓↓ 告警 事件类型 ↓↓↓↓↓↓↓↓**/
    /**
     * 告警类型-操作事件
     **/
    public final static String ALARM_TYPE_OPERATION = "操作事件";

    /**
     * 告警类型-遥信变位
     **/
    public final static String ALARM_TYPE_YX_DISPLACEMENT = "遥信变位";

    /**
     * 告警类型-遥测越限
     **/
    public final static String ALARM_TYPE_YC_OVER_LIMITATION = "遥测越限";

    /**
     * 告警类型-其他
     **/
    public final static String ALARM_TYPE_OTHER = "其他";

    /**↓↓↓↓↓↓ 告警 遥控遥调事件动作 ↓↓↓↓↓↓↓↓**/
    /**
     * 测点告警类型-遥控
     **/
    public final static String MEAS_ALARM_TYPE_YK = "遥控";

    /** 测点告警类型-遥调 **/
    public final static String MEAS_ALARM_TYPE_YT = "遥调";

    /**↓↓↓↓↓↓ 告警表 相关属性 ↓↓↓↓↓↓↓↓**/
    public static class AlarmTableAttr {

        /** 测点id：告警表存储测点id的键 **/
        public final static String CODE = "Code";

        /** 告警时间：告警表存储测点time的键 **/
        public final static String TIME = "Time";

        /** 告警值：告警表存储测点value的键 **/
        public final static String VALUE = "Value";

        /** 测点告警类型：告警表存储测点AlarmType的键 **/
        public final static String ALARMTYPE = "AlarmType";

        /** 操作人名称：告警表存储操作人Operator的键 **/
        public final static String OPERATOR = "Operator";

        /** 控制命令反校结果：告警表存储控制命令反校结果replyResult的键 **/
        public final static String REPLYRESULT = "replyResult";

    }

    /**↓↓↓↓↓↓ 测点类型 ↓↓↓↓↓↓↓↓**/
    public static class MeasType {

        // 1：遥信；2：遥测；3：电度；4：定值；5：遥控；6：遥调；7：定值区；8：计算测点
        /** 遥信 **/
        public final static int YX = 1;

        /** 遥测 **/
        public final static int YC = 2;

        /** 电度 **/
        public final static int DD = 3;

        /** 定值 **/
        public final static int DZ = 4;

        /** 遥控 **/
        public final static int YK = 5;

        /** 遥调 **/
        public final static int YT = 6;

        /** 定值区 **/
        public final static int DZQ = 7;

        /** 计算测点 **/
        public final static int JS = 8;

    }

    /**↓↓↓↓↓↓ 冻结类型 ↓↓↓↓↓↓↓↓**/
    public static class FrozenType {

        // 冻结类型:1:年;2:月;3:日
        /** 年 **/
        public final static int YEAR = 1;

        /** 月 **/
        public final static int MONTH = 2;

        /** 日 **/
        public final static int DAY = 3;

    }

    /**
     * 测点类型映射
     */
//    public final static Map<String, Class<? extends BaseModel>> MEAS_TYPE_MAP = MapUtil.builder(new HashMap<String, Class<? extends BaseModel>>())
//        .put("yx", MonMeasurementYx.class)
//        .put("yc", MonMeasurementYc.class)
//        .put("dd", MonMeasurementDd.class)
//        .put("yk", MonMeasurementYk.class)
//        .put("yt", MonMeasurementYt.class)
//        .put("calc", MonMeasurementCalc.class)
//        .put("xn", MonVirtualPoint.class)
//        .build();





    /**↓↓↓↓↓↓ 命令表 相关属性 ↓↓↓↓↓↓↓↓**/
    public static class CmdTableAttr {

        /** 测点ID */
        public final static String CODE = "Code";

        /** YK（YT）控制类型，代表是遥控点还是遥调点 */
        public final static String CONTROL_TYPE = "ControlType";

        /** Select（Execute）是选择命令还是执行命令。注意：ReadDZ是特殊的命令与遥控进行组合 */
        public final static String CMD_TYPE = "CmdType";

        /** 反校结果Successful代表反校成功，Fail表示反校失败，TimeOut表示超时 */
        public final static String REPLY_RESULT = "ReplyResult";

        /** 如果是遥控就是0和1 ，如果是遥调就是float类型的值。 */
        public final static String VALUE = "Value";

    }

    /** 控制类型枚举,下发命令 */
    public static enum ControlType {
        /** 遥控 */
        YK,
        /** 遥调 */
        YT;
    }

    /** 命令类型枚举,下发命令 */
    public static enum CmdType {
        /** 预置 */
        Select,
        /** 执行 */
        Execute;
    }

    /** 告警控制命令类型,生成告警 */
    public enum AlarmControlCmdType {

        /** 预置 */
        Select(1, "预置"),

        /** 预置反校 */
        SelectReply(2, "预置反校"),

        /** 执行 */
        Execute(3, "执行"),

        /** 执行反校 */
        ExecuteReply(4, "执行反校"),

        /** 其他 */
        Other(Integer.MAX_VALUE, "其他");

        /** 状态 */
        private int state;

        /** 信息 */
        private String label;

        AlarmControlCmdType(int state, String label) {
            this.state = state;
            this.label = label;
        }

        public static String getLabel(int state) {
            return Arrays.stream(AlarmControlCmdType.values())
                        .filter(e -> e.state == state)
                        .findFirst()
                        .orElse(Other)
                        .label;
        }

        public int getState() {
            return state;
        }
    }

    /** 反校类型枚举,生成告警 */
    public static enum MessageReplyType {
        /** 成功 */
        Successful("成功"),
        /** 失败 */
        Fail("失败"),
        /** 超时 */
        TimeOut("超时"),
        /** 其他 */
        Other("其他");

        private MessageReplyType(String label) {
            this.label = label;
        }

        private String label;

        public static String getLabel(String name) {
            return Arrays.stream(MessageReplyType.values())
                        .filter(e -> e.name().equals(name))
                        .findFirst()
                        .orElse(Other)
                        .label;
        }

    }

    /**
     * psr类型
     */
    public static class PsrType {
        /** 电站 **/
        public final static Long STATION = 12000000000000001L;
        /** 方阵 **/
        public final static Long MATRIX = 12000000000000002L;
        /** 间隔 **/
        public final static Long JG = 12000000000000003L;
        /** 箱变 **/
        public final static Long XB = 12000000000000004L;
        /** 集中式逆变器 **/
        public final static Long FOCUSINV = 12000000000000005L;
        /** 组串式逆变器 **/
        public final static Long STRINV = 12000000000000006L;
        /** 汇流箱 **/
        public final static Long COMBINEROX = 12000000000000007L;
        /** 组串 **/
        public final static Long SERIES = 12000000000000008L;
        /** 环境监测仪 **/
        public final static Long HJ = 12000000000000009L;
        /** 关口表 **/
        public final static Long GK = 12000000000000010L;
    }

    /**
     * psr类型Code
     */
    public static class PsrTypeCode {
        /** 电站 **/
        public final static String STATION = "1";
        /** 方阵 **/
        public final static String MATRIX = "2";
        /** 间隔 **/
        public final static String JG = "3";
        /** 箱变 **/
        public final static String XB = "4";
        /** 集中式逆变器 **/
        public final static String FOCUSINV = "5";
        /** 组串式逆变器 **/
        public final static String STRINV = "6";
        /** 汇流箱 **/
        public final static String COMBINEROX = "7";
        /** 组串 **/
        public final static String SERIES = "8";
        /** 环境监测仪 **/
        public final static String HJ = "9";
        /** 关口表 **/
        public final static String GK = "10";
    }

    /**
     * 测点属性id
     */
    public static class MeasurementAttr {

        /** 集中式逆变器累计发电量 **/
        public final static Long C_INV_G_A_YC = 14000502000000018L;
        /** 组串式逆变器累计发电量 **/
        public final static Long D_INV_G_A_YC = 14000602000000018L;

       /** 集中式逆变器年发电量 **/
        public final static Long C_INV_G_Y_YC = 14000502000000017L;
        /** 组串式逆变器年发电量 **/
        public final static Long D_INV_G_Y_YC = 14000602000000017L;

        /** 集中式逆变器日发电量 **/
        public final static Long C_INV_G_D_YC = 14000502000000015L;
        /** 组串式逆变器日发电量 **/
        public final static Long D_INV_G_D_YC = 14000602000000015L;

        /** 集中式逆变器交流侧无功功率 **/
        public final static Long C_INV_AC_REACTIVE_P = 14000502000000011L;
        /** 组串式逆变器交流侧无功功率 **/
        public final static Long D_INV_AC_REACTIVE_P = 14000602000000011L;

        /** 集中式逆变器交流侧有功功率 **/
        public final static Long C_INV_AC_ACTIVE_P = 14000502000000010L;
        /** 组串式逆变器交流侧有功功率 **/
        public final static Long D_INV_AC_ACTIVE_P = 14000602000000010L;

        /** 集中式逆变器通讯状态 **/
        public final static Long C_INV_COMMU = 14000501000000001L;
        /** 组串式逆变器通信状态 **/
        public final static Long D_INV_COMMU = 14000601000000001L;

        /** 集中式逆变器停机 **/
        public final static Long C_INV_STOP = 14000501000000002L;
        /** 组串式逆变器停机 **/
        public final static Long D_INV_STOP = 14000601000000002L;

       /** 集中式逆变器限功率 **/
        public final static Long C_INV_LIMIT_STOP = 14000501000000005L;
        /**组串式逆变器限功率 **/
        public final static Long D_INV_LIMIT_STOP = 14000601000000005L;

       /** 集中式逆变器故障 **/
        public final static Long C_INV_FAULT = 14000501000000006L;
        /** 组串式逆变器故障 **/
        public final static Long D_INV_FAULT = 14000601000000006L;

        /** 集中式逆变器运行状态 **/
        public final static Long C_INV_V_STATUS = 14000508000000001L;
        /** 组串式逆变器运行状态**/
        public final static Long D_INV_V_STATUS = 14000608000000001L;

        /** 方阵并网 **/
        public final static Long ARR_GRID_CON = 14000201000000002L;

        /** 方阵通讯状态 **/
        public final static Long ARR_COMMU = 14000201000000001L;

        /** 电站_计算测点_方阵运行状态（全部数量） **/
        public final static Long STA_V_ARR_ALL_NUM=14000108000000019L;

        /** 电站_计算测点_方阵运行状态（正常方阵数量） **/
        public final static Long STA_V_ARR_RUN_NUM=14000108000000020L;

        /** 电站_计算测点_方阵运行状态（告警方阵数量） **/
        public final static Long STA_V_ARR_ALARM_NUM=14000108000000021L;

        /** 方阵运行状态（通讯中断数量） **/
        public final static Long STA_V_ARR_INTERRUPT_NUM=14000108000000022L;

        /** 方阵运行状态**/
        public final static Long ARR_V_STATUS=14000208000000001L;

		/** 汇流箱通讯状态**/
        public final static Long DC_BOX_COMMU=14000701000000001L;

        /** 汇流箱母线电压**/
        public final static Long DC_BOX_V=14000702000000001L;

        /** 组串电流**/
        public final static Long STRING_I=14000802000000002L;
		/** 箱变通讯状态**/
		 public final static Long BOX_COMMUNICATION_STATE=14000401000000001L;
        /** 箱变运行状态**/
        public final static Long BOX_RUN_STATE=14000408000000001L;

        /** 箱变告警数量**/
        public final static Long BOX_ALARM_NUM=14000408000000002L;

        /** 集中式逆变器告警数量**/
        public final static Long CEN_INV_ALARM=14000508000000002L;

        /** 组串式逆变器告警数量**/
        public final static Long SER_INV_ALARM=14000608000000002L;

        /** 逆变器日等效时**/
        public final static Long INV_EQUIVALENT=14000508000000003L;
        /** 箱变所有数量**/
        public final static Long BOX_ALL=14000108000000023L;
        /** 正常箱变数量**/
        public final static Long BOX_NORMAL=14000108000000024L;
        /** 告警箱变数量**/
        public final static Long BOX_ALARM=14000108000000025L;
        /** 中断箱变数量**/
        public final static Long BOX_OFF=14000108000000026L;
    }
     /**
     * 计算测点属性id
     */
    public static class CalcMeasurementAttr {

        /** 电站_虚测点_当日发电量 **/
        public final static Long STA_V_PG_D = 14000108000000001L;

         /** 电站_虚测点_总无功 **/
        public final static Long STA_V_REACTIVE_P_ALL = 14000108000000005L;

        /** 电站_虚测点_总发电量**/
        public final static Long STA_V_PG_ALL = 14000108000000004L;

        /** 电站_虚测点_年累计发电量**/
        public final static Long STA_V_PG_Y = 14000108000000003L;

        /** 电站_虚测点_等效时 **/
        public final static Long STA_V_ET_D = 14000108000000007L;

        /** 电站_虚测点_总功率 **/
        public final static Long STA_V_ACTIVE_P_ALL = 14000108000000006L;

       /** 电站_虚测点_通讯中断 **/
        public final static Long STA_V_INV_INTERRUPT_NUM = 14000108000000013L;

       /** 电站_虚测点_故障停机 **/
        public final static Long STA_V_INV_FAULT_NUM = 14000108000000012L;

       /** 电站_虚测点_正常停机 **/
        public final static Long STA_V_INV_STOP_NUM = 14000108000000011L;

       /** 电站_虚测点_正常状态 **/
        public final static Long STA_V_INV_RUN_NUM = 14000108000000010L;

       /** 电站_虚测点_开机容量 **/
        public final static Long STA_V_POC_D = 14000108000000008L;

        /** 方阵_计算测点_组串异常数量 **/
        public final static Long STA_V_AB_SERIES_NUM = 14000208000000002L;


    }

    /**
     * 逆变器状态公式表测点类型
     */
    public static class MeasuringPointType {
        /** 正常 **/
        public final static Integer NORMAL = 0;
        /** 正常停机 **/
        public final static Integer NORMALSHUTDOWN = 1;
        /** 故障停机 **/
        public final static Integer FAULTSTOP = 2;
        /** 通讯中断 **/
        public final static Integer CONNBREAK = 3;

    }


}
