//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.controller;

//import com.yinhe.ec.cpps.ycmeter.service.UploadExcelService;
import com.yinhe.ec.cpps.ycmeter.service.UploadExcelService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Controller
public class UploadExcelController {
    private static final Logger logger = Logger.getLogger(UploadExcelController.class);
    @Resource
    private UploadExcelService uploadExcelService;

    public UploadExcelController() {
    }

    @RequestMapping({"/ycmeter/upLoadExcel"})
    @ResponseBody
    public String upLoadExcel(HttpServletRequest request, HttpServletResponse response, int custId, int operatorId) throws IOException {
        String str = "";
        String filepath = "";
        str = UploadExcelController.class.getResource("").toString();
        str = str.substring(5, str.indexOf("webapps")) + "/webapps/upLoadFile/";
        filepath = URLDecoder.decode(str, "utf-8");
        filepath = URLDecoder.decode(filepath, "utf-8");
        response.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        try {
            List fileItems = upload.parseRequest(request);
            Iterator iter = fileItems.iterator();

            while(iter.hasNext()) {
                FileItem item = (FileItem)iter.next();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);

                    try {
                        item.write(new File(filepath + filename));
                        this.uploadExcelService.excelToDB(filepath + filename, custId, operatorId);
                    } catch (Exception var14) {
                        var14.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException var15) {
            var15.printStackTrace();
        }

        return "导入数据成功";
    }

    @RequestMapping({"/ycmeter/upLoadExcelForWy"})
    @ResponseBody
    public String upLoadExcelForWy(HttpServletRequest request, HttpServletResponse response, int custId, int operatorId, String areaId, String wyfId, String nqfId, String feeItemId, int keyFlag, String musterNo, String manuId, int meterPtl, int pattern, int comPort, int baud, int onOff, int alarm1, int alarm2, String subNo, String mboxId, String feeItemId2, int keyFlag2, String musterNo2, String manuId2, int meterPtl2, int pattern2, int comPort2, int baud2, int onOff2, int alarm12, int alarm22, String feeItemId3, int keyFlag3, String musterNo3, String manuId3, int meterPtl3, int pattern3, int comPort3, int baud3, int onOff3, int alarm13, int alarm23) throws IOException {
        String tmpStr = "";

        String paraName;
        for(Enumeration enu = request.getParameterNames(); enu.hasMoreElements(); tmpStr = tmpStr + paraName + ": " + request.getParameter(paraName) + "\n") {
            paraName = (String)enu.nextElement();
        }

        logger.info("批量上传用户档案：" + tmpStr);
        paraName = "";
        String filepath = "";
        paraName = UploadExcelController.class.getResource("").toString();
        paraName = paraName.substring(5, paraName.indexOf("webapps")) + "/webapps/upLoadFile/";
        filepath = URLDecoder.decode(paraName, "utf-8");
        filepath = URLDecoder.decode(filepath, "utf-8");
        response.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        try {
            List fileItems = upload.parseRequest(request);
            Iterator iter = fileItems.iterator();

            while(iter.hasNext()) {
                FileItem item = (FileItem)iter.next();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);

                    try {
                        item.write(new File(filepath + filename));
                        this.uploadExcelService.excelToDBForWy(filepath + filename, custId, operatorId, areaId, wyfId, nqfId, feeItemId, keyFlag, musterNo, manuId, meterPtl, pattern, comPort, baud, onOff, alarm1, alarm2, subNo, mboxId, feeItemId2, keyFlag2, musterNo2, manuId2, meterPtl2, pattern2, comPort2, baud2, onOff2, alarm12, alarm22, feeItemId3, keyFlag3, musterNo3, manuId3, meterPtl3, pattern3, comPort3, baud3, onOff3, alarm13, alarm23);
                    } catch (Exception var54) {
                        var54.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException var55) {
            var55.printStackTrace();
        }

        return "导入数据成功";
    }

    @RequestMapping({"/ycmeter/upLoadNbMeterExcel"})
    @ResponseBody
    public String upLoadNbMeterExcel(HttpServletRequest request, HttpServletResponse response, int custId, int createUser, String manuCode, String batchNo, int pwdGroupNo, int tmodel, int typeId) throws IOException {
        String str = "";
        String filepath = "";
        str = UploadExcelController.class.getResource("").toString();
        str = str.substring(5, str.indexOf("webapps")) + "/webapps/upLoadFile/";
        filepath = URLDecoder.decode(str, "utf-8");
        filepath = URLDecoder.decode(filepath, "utf-8");
        response.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        try {
            List fileItems = upload.parseRequest(request);
            Iterator iter = fileItems.iterator();

            while(iter.hasNext()) {
                FileItem item = (FileItem)iter.next();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);

                    try {
                        item.write(new File(filepath + filename));
                        this.uploadExcelService.uploadNbMeterInfo(filepath + filename, custId, createUser, manuCode, batchNo, pwdGroupNo, tmodel, typeId);
                    } catch (Exception var19) {
                        var19.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException var20) {
            var20.printStackTrace();
        }

        return "导入数据成功";
    }

    @RequestMapping({"/ycmeter/upLoadMeterToDosagedetail"})
    @ResponseBody
    public String upLoadMeterToDosagedetail(HttpServletRequest request, HttpServletResponse response, int custId, int operatorId) throws IOException {
        String str = "";
        String filepath = "";
        str = UploadExcelController.class.getResource("").toString();
        str = str.substring(5, str.indexOf("webapps")) + "/webapps/upLoadFile/";
        filepath = URLDecoder.decode(str, "utf-8");
        filepath = URLDecoder.decode(filepath, "utf-8");
        response.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        try {
            List fileItems = upload.parseRequest(request);
            Iterator iter = fileItems.iterator();

            while(iter.hasNext()) {
                FileItem item = (FileItem)iter.next();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);

                    try {
                        item.write(new File(filepath + filename));
                        this.uploadExcelService.upLoadMeterToDosagedetail(filepath + filename, custId, operatorId);
                    } catch (Exception var14) {
                        var14.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException var15) {
            var15.printStackTrace();
        }

        return "导入数据成功";
    }

    @RequestMapping({"/ycmeter/upLoadFactoryTestExcel"})
    @ResponseBody
    public String upLoadFactoryTestExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = "";
        String filepath = "";
        str = UploadExcelController.class.getResource("").toString();
        str = str.substring(5, str.indexOf("webapps")) + "/webapps/upLoadFile/";
        filepath = URLDecoder.decode(str, "utf-8");
        filepath = URLDecoder.decode(filepath, "utf-8");
        response.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        try {
            List fileItems = upload.parseRequest(request);
            Iterator iter = fileItems.iterator();

            while(iter.hasNext()) {
                FileItem item = (FileItem)iter.next();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);

                    try {
                        item.write(new File(filepath + filename));
                        this.uploadExcelService.upLoadFactoryTestExcel(filepath + filename);
                    } catch (Exception var12) {
                        var12.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException var13) {
            var13.printStackTrace();
        }

        return "导入数据成功";
    }
}
