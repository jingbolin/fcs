package com.yinhe.ec.cpps.rtdp.constants;

import java.util.Arrays;

/**
 * 告警等级
 * 0：事故、1：异常、2：变位、3：越限、4：告知
 * @author wangshilei
 * @date 2021/03/30
 */
public enum AlarmLevelEnum {
    
    /** 事故 */
    ACCIDENT(0, "事故"),
    
    /** 异常 */
    ABNORMAL(1, "异常"),
    
    /** 变位 */
    SIGNAL_CHANGE(2, "变位"),
    
    /** 越限 */
    OUT_LIMIT(3, "越限"),
    
    /** 告知 */
    NOTIFY(4, "告知"),
    
    /** 其他 */
    OTHER(Integer.MAX_VALUE, "其他");

    /** 状态 */
    private int state;

    /** 信息 */
    private String label;

    AlarmLevelEnum(int state, String label) {
        this.state = state;
        this.label = label;
    }
    
    public static String getLabel(int state) {
        return Arrays.stream(AlarmLevelEnum.values())
                .filter(e -> e.state == state)
                .findFirst()
                .orElse(OTHER)
                .label;
    }

}
