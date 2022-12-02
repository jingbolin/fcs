//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.model;

import org.springframework.stereotype.Component;

@Component
public class Nb_MeterParameter {
    private String manuCode = "";
    private String manufacturerId = "";
    private String manufacturerName = "";
    private String deviceType = "";
    private String deviceModel = "";
    private String produceDesc = "";
    private String protocolType = "";
    private Integer custId = 0;
    private Integer useFlag = 0;
    private String projectId = "";
    private String createDate = "";
    private String appId = "";
    private Nb_Application application;

    public Nb_MeterParameter() {
    }

    public String getManuCode() {
        return this.manuCode;
    }

    public void setManuCode(String manuCode) {
        this.manuCode = manuCode;
    }

    public String getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getProduceDesc() {
        return this.produceDesc;
    }

    public void setProduceDesc(String produceDesc) {
        this.produceDesc = produceDesc;
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public Integer getCustId() {
        return this.custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getUseFlag() {
        return this.useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Nb_Application getApplication() {
        return this.application;
    }

    public void setApplication(Nb_Application application) {
        this.application = application;
    }
}
