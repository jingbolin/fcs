package com.yinhe.ec.cpps.sys.license.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 显示设备信息
 *
 * @date 2021/5/6
 * @since 1.0.0
 */
public class LicenseCheckModelVo implements Serializable {

    private static final long serialVersionUID = 1L;
 /**
     * 可被允许的IP地址
     */
    private List<String> ipAddress;

   /**
     * 可被允许的MAC地址
     */
    private List<String> macAddress;

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

    /**
     * 扩展显示信息
     */
    //版本
    private String version;

    public List<String> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<String> getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(List<String> macAddress) {
        this.macAddress = macAddress;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LicenseCheckModelVo{" +
                "ipAddress=" + ipAddress +
                ", macAddress=" + macAddress +
                ", cpuSerial='" + cpuSerial + '\'' +
                ", mainBoardSerial='" + mainBoardSerial + '\'' +
                ", identifier='" + identifier + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
