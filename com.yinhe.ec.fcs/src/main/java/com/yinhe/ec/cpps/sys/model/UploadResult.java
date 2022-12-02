
package com.yinhe.ec.cpps.sys.model;


import io.swagger.annotations.ApiModelProperty;

public class UploadResult {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上传结果")
    private String result;

    @ApiModelProperty(value = "初始文件名")
    private String beginFileName;

    @ApiModelProperty(value = "最终上传文件名")
    private String lastFileName;

    @ApiModelProperty(value = "文件类型")
    private String FileType;

    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    @ApiModelProperty(value = "文件上传的地址")
    private String uploadUrl;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBeginFileName() {
        return beginFileName;
    }

    public void setBeginFileName(String beginFileName) {
        this.beginFileName = beginFileName;
    }

    public String getLastFileName() {
        return lastFileName;
    }

    public void setLastFileName(String lastFileName) {
        this.lastFileName = lastFileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "result='" + result + '\'' +
                ", beginFileName='" + beginFileName + '\'' +
                ", lastFileName='" + lastFileName + '\'' +
                ", FileType='" + FileType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", uploadUrl='" + uploadUrl + '\'' +
                '}';
    }
}
