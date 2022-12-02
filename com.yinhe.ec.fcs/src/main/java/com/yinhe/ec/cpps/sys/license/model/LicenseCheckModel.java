package com.yinhe.ec.cpps.sys.license.model;

import java.io.Serializable;

/**
 * 自定义需要校验的License参数
 *
 * @date 2021/5/6
 * @since 1.0.0
 */
public class LicenseCheckModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可被允许的CPU序列号
     */
    private String cpuSerial;

    /**
     * 可被允许的主板序列号
     */
    private String mainBoardSerial;

    /**
     * 机器识别码
     */
    private String identifier;


    public String getCpuSerial() {
        return cpuSerial;
    }

    public void setCpuSerial(String cpuSerial) {
        this.cpuSerial = cpuSerial;
    }

    public String getMainBoardSerial() {
        return mainBoardSerial;
    }

    public void setMainBoardSerial(String mainBoardSerial) {
        this.mainBoardSerial = mainBoardSerial;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "LicenseCheckModel{" +
                "cpuSerial='" + cpuSerial + '\'' +
                ", mainBoardSerial='" + mainBoardSerial + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
