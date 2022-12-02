package com.yinhe.ec.cpps.rtdp.constants;

import java.util.Arrays;

/**
 * 遥测告警状态
 * @author wangshilei
 * @date 2021/03/30
 */
public enum YcAlarmStateEnum {
    
    /** 正常状态 */
    STATE_YC_ALARM_NORMAL(0, "正常状态"),
    
    /** 遥测越上限 */
    STATE_YC_ALARM_UP(1, "遥测越上限"),
    
    /** 遥测越下限 */
    STATE_YC_ALARM_down(2, "遥测越下限"),
    
    /** 遥测越上限回归 */
    STATE_YC_UP_ALARM_RECOVER(3, "遥测越上限回归"),
    
    /** 遥测越下限回归 */
    STATE_YC_DOWN_ALARM_RECOVER(4, "遥测越下限回归"),
    
    /** 其他 */
    STATE_YC_OTHER(Integer.MAX_VALUE, "其他");

    /** 状态 */
    private int state;

    /** 信息 */
    private String label;

    YcAlarmStateEnum(int state, String label) {
        this.state = state;
        this.label = label;
    }
    
    public static String getLabel(int state) {
        return Arrays.stream(YcAlarmStateEnum.values())
                .filter(e -> e.state == state)
                .findFirst()
                .orElse(STATE_YC_OTHER)
                .label;
    }

}
