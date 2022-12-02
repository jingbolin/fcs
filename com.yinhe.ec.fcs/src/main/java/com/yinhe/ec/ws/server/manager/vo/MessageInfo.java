package com.yinhe.ec.ws.server.manager.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yinhe.ec.ws.server.Protocol;

/**
 * websocket消息Bean
 * @author chenyh
 * @since 2018年8月24日下午3:44:52
 */
public class MessageInfo {
    
    /** 消息类型{@link Protocol} */
    private String type;
    /** 消息主题 */
    private String topic;
    
    // 遥信遥测订阅
    /** 清理订阅的主题 */
    private String[] clear;
    /** 当前主题订阅的测点列表 */
    private String[] order;
    
    // 遥控遥调
    /** 操作人id */
    private Long userId;
    /** 遥控遥调写入的值 */
    private List<Map<String, Object>> writeValue = new ArrayList<Map<String, Object>>();
    /** 遥控读定值 */
    private Map<String, Object> readValue = new HashMap<String, Object>();
    
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String[] getClear() {
        return clear;
    }
    public void setClear(String[] clear) {
        this.clear = clear;
    }
    public String[] getOrder() {
        return order;
    }
    public void setOrder(String[] order) {
        this.order = order;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<Map<String, Object>> getWriteValue() {
        return writeValue;
    }
    public void setWriteValue(List<Map<String, Object>> writeValue) {
        this.writeValue = writeValue;
    }
    public Map<String, Object> getReadValue() {
        return readValue;
    }
    public void setReadValue(Map<String, Object> readValue) {
        this.readValue = readValue;
    }
    
}
