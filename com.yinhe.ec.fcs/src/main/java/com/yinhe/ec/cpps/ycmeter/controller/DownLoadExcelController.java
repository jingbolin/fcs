package com.yinhe.ec.cpps.ycmeter.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinhe.ec.cpps.sys.model.ApiResult;
import com.yinhe.ec.cpps.ycmeter.service.UploadExcelService;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/yinhe")
public class DownLoadExcelController {
    @Resource
    private UploadExcelService genxls;

    public DownLoadExcelController() {
    }

    @ApiOperation(value = "模板下载", notes = "模板下载", response = ApiResult.class)
    @RequestMapping({"/ycmeter/downLoadExcel"})
    @ResponseBody
    public void downLoadExcel(HttpServletRequest request, HttpServletResponse response, String sql, String file_str, String header_str, String dataF_str) throws IOException {
        String fileName = null;
        String sbHeadTitle = null;
        String sbHeadColName = null;
        new Vector();

        try {
            file_str = URLDecoder.decode(file_str, "UTF-8");
            header_str = URLDecoder.decode(header_str, "UTF-8");
            sql = URLDecoder.decode(sql, "UTF-8");
        } catch (Exception var28) {
            System.err.println(var28.getMessage());
        }

        fileName = file_str + "-" + (new SimpleDateFormat("yyyyMMdd")).format(new Date()) + ".xls";
        sbHeadTitle = header_str;
        sql = sql.replace("$", "%");
        Vector<HashMap> vec = this.genxls.getExcels(sql, file_str, header_str, dataF_str);
        ServletOutputStream os = response.getOutputStream();
        response.reset();
        String contentType = "application/msexcel";
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(), "iso-8859-1") + "\"");
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet = workbook.createSheet(file_str, 0);
        WritableFont wfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
        WritableCellFormat wcfFC = new WritableCellFormat(wfc);
        NumberFormat nf = new NumberFormat("#.##");
        new WritableCellFormat(nf);

        for(int i = 0; i < sbHeadTitle.toString().split(",").length; ++i) {
            try {
                sheet.addCell(new Label(i, 0, sbHeadTitle.toString().split(",")[i], wcfFC));
                sheet.setColumnView(i, 15);
            } catch (RowsExceededException var26) {
                var26.printStackTrace();
            } catch (WriteException var27) {
                var27.printStackTrace();
            }
        }

        String[] a = dataF_str.toString().split(",");

        for(int i = 1; i <= vec.size(); ++i) {
            for(int j = 0; j < a.length; ++j) {
                try {
                    String t = "";
                    if (((HashMap)vec.get(i - 1)).get(a[j]) != null) {
                        t = ((HashMap)vec.get(i - 1)).get(a[j]).toString();
                    }

                    sheet.addCell(new Label(j, i, t));
                } catch (RowsExceededException var24) {
                    var24.printStackTrace();
                } catch (WriteException var25) {
                    var25.printStackTrace();
                }
            }
        }

        workbook.write();

        try {
            workbook.close();
        } catch (WriteException var23) {
            var23.printStackTrace();
        }

        os.close();
    }
}
