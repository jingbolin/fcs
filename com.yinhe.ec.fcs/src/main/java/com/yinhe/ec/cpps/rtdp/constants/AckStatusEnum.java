package com.yinhe.ec.cpps.rtdp.constants;

import java.util.Arrays;

/**
 * 告警确认状态
 * @author wangshilei
 * @date 2021/03/30
 */
public enum AckStatusEnum {
    
    /** 未确认 */
    UN_ACK(0, "未确认"),
    
    /** 已确认 */
    ACKED(1, "已确认"),
    
    /** 其他 */
    OTHER(Integer.MAX_VALUE, "其他");

    /** 状态 */
    private int state;

    /** 信息 */
    private String label;

    AckStatusEnum(int state, String label) {
        this.state = state;
        this.label = label;
    }
    
    public static String getLabel(int state) {
        return Arrays.stream(AckStatusEnum.values())
                .filter(e -> e.state == state)
                .findFirst()
                .orElse(OTHER)
                .label;
    }

}
