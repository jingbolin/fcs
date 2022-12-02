package com.yinhe.ec.cpps.ycmeter.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import javax.annotation.Resource;

import com.yinhe.ec.cpps.user.dao.UserDao;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.ycmeter.dao.GenerateExcelDao;
import com.yinhe.ec.cpps.ycmeter.dao.NbMeterDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.stereotype.Service;

@Service
public class UploadExcelService {
    @Resource
    private UserDao userDao;
    @Resource
    private GenerateExcelDao generateExcelDao;
    @Resource
    private NbMeterDao nbMeterDao;

    public UploadExcelService() {
    }

    public Vector<HashMap> getExcels(String sql, String file_str, String header_str, String dataF_str) {
        Vector<HashMap> x = this.generateExcelDao.getExcelInfo(sql);
        if (x == null) {
            x = new Vector();
        }

        return x;
    }

    public void excelToDB(String path, int custId, int operatorId) throws BiffException, IOException {
        File file = new File(path);
        Workbook rwb = null;
        rwb = Workbook.getWorkbook(file);
        Sheet sheet = rwb.getSheets()[0];
        int rsColumns = sheet.getColumns();
        int rsRows = sheet.getRows();
        System.out.println("列数:" + rsColumns + "行数:" + rsRows);

        for(int i = 1; i < rsRows; ++i) {
            Cell cell0 = sheet.getCell(0, i);
            Cell cell1 = sheet.getCell(1, i);
            Cell cell2 = sheet.getCell(2, i);
            Cell cell3 = sheet.getCell(3, i);
            Cell cell4 = sheet.getCell(4, i);
            Cell cell5 = sheet.getCell(5, i);
            Cell cell6 = sheet.getCell(6, i);
            Cell cell7 = sheet.getCell(7, i);
            Cell cell8 = sheet.getCell(8, i);
            Cell cell9 = sheet.getCell(9, i);
            Cell cell10 = sheet.getCell(10, i);
            Cell cell11 = sheet.getCell(11, i);
            Cell cell12 = sheet.getCell(12, i);
            Cell cell13 = sheet.getCell(13, i);
            Cell cell14 = sheet.getCell(14, i);
            Cell cell15 = sheet.getCell(15, i);
            Cell cell16 = sheet.getCell(16, i);
            Cell cell17 = sheet.getCell(17, i);
            Cell cell18 = sheet.getCell(18, i);
            Cell cell19 = sheet.getCell(19, i);
            Cell cell20 = sheet.getCell(20, i);

            try {
                this.userDao.excelIntoDB(cell0.getContents(), cell1.getContents(), cell2.getContents(), cell3.getContents(), cell4.getContents(), cell5.getContents(), cell6.getContents(), cell7.getContents(), cell8.getContents(), cell9.getContents(), cell10.getContents(), cell11.getContents(), cell12.getContents(), cell13.getContents(), cell14.getContents(), cell15.getContents(), cell16.getContents(), cell17.getContents(), cell18.getContents(), cell19.getContents(), cell20.getContents(), custId, operatorId);
            } catch (Exception var32) {
                System.err.println("err:" + var32.getMessage());
            }
        }

    }

    public void excelToDBForWy(String path, int custId, int operatorId, String areaId, String wyfId, String nqfId, String feeItemId, int keyFlag, String musterNo, String manuId, int meterPtl, int pattern, int comPort, int baud, int onOff, int alarm1, int alarm2, String subNo, String mboxId, String feeItemId2, int keyFlag2, String musterNo2, String manuId2, int meterPtl2, int pattern2, int comPort2, int baud2, int onOff2, int alarm12, int alarm22, String feeItemId3, int keyFlag3, String musterNo3, String manuId3, int meterPtl3, int pattern3, int comPort3, int baud3, int onOff3, int alarm13, int alarm23) throws BiffException, IOException {
        File file = new File(path);
        Workbook rwb = null;
        rwb = Workbook.getWorkbook(file);
        Sheet sheet = rwb.getSheets()[0];
        int rsColumns = sheet.getColumns();
        int rsRows = sheet.getRows();
        WritableWorkbook wwb = Workbook.createWorkbook(file, rwb);
        WritableSheet sheet1 = wwb.getSheet(0);
        Label lb = null;
        System.out.println("列数:" + rsColumns + "行数:" + rsRows);

        for(int i = 1; i < rsRows; ++i) {
            String userName = sheet.getCell(0, i).getContents().trim();
            String userAddr = sheet.getCell(1, i).getContents();
            String floors = sheet.getCell(2, i).getContents();
            String areas = sheet.getCell(3, i).getContents();
            String mobilePhone = sheet.getCell(4, i).getContents();
            String idCrad = sheet.getCell(5, i).getContents();
            String meterNo = sheet.getCell(6, i).getContents().trim();
            String dynameter = sheet.getCell(7, i).getContents();
            String initDosage = sheet.getCell(8, i).getContents();
            String shareFlag = sheet.getCell(9, i).getContents();
            String shareRatio = sheet.getCell(10, i).getContents();
            String meterNo2 = sheet.getCell(11, i).getContents().trim();
            String dn = sheet.getCell(12, i).getContents();
            String initDosage2 = sheet.getCell(13, i).getContents();
            String shareFlag2 = sheet.getCell(14, i).getContents();
            String shareRatio2 = sheet.getCell(15, i).getContents();
            String meterNo3 = sheet.getCell(16, i).getContents().trim();
            String dn3 = sheet.getCell(17, i).getContents();
            String initDosage3 = sheet.getCell(18, i).getContents();
            String shareFlag3 = sheet.getCell(19, i).getContents();
            String shareRatio3 = sheet.getCell(20, i).getContents();
            String userId = sheet.getCell(21, i).getContents();
            if ("".equals(floors)) {
                floors = "1";
            }

            if ("".equals(areas)) {
                areas = "0";
            }

            if ("".equals(dynameter)) {
                dynameter = "1";
            }

            if ("".equals(initDosage)) {
                initDosage = "0";
            }

            if ("".equals(shareFlag)) {
                shareFlag = "0";
            }

            if ("".equals(shareRatio)) {
                shareRatio = "100";
            }

            if ("".equals(shareFlag2)) {
                shareFlag2 = "0";
            }

            if ("".equals(shareRatio2)) {
                shareRatio2 = "100";
            }

            if ("".equals(shareFlag3)) {
                shareFlag3 = "0";
            }

            if ("".equals(shareRatio3)) {
                shareRatio3 = "100";
            }

            try {
                this.userDao.excelIntoDBForWy(userName, userAddr, floors, areas, mobilePhone, idCrad, meterNo, dynameter, initDosage, meterNo2, dn, initDosage2, custId, operatorId, areaId, wyfId, nqfId, feeItemId, keyFlag, musterNo, manuId, meterPtl, pattern, comPort, baud, onOff, alarm1, alarm2, subNo, mboxId, feeItemId2, keyFlag2, musterNo2, manuId2, meterPtl2, pattern2, comPort2, baud2, onOff2, alarm12, alarm22, shareFlag, shareRatio, shareFlag2, shareRatio2, meterNo3, dn3, initDosage3, feeItemId3, keyFlag3, musterNo3, manuId3, meterPtl3, pattern3, comPort3, baud3, onOff3, alarm13, alarm23, shareFlag3, shareRatio3, userId);
                lb = new Label(22, i, "成功");

                try {
                    sheet1.addCell(lb);
                } catch (RowsExceededException var78) {
                    var78.printStackTrace();
                } catch (WriteException var79) {
                    var79.printStackTrace();
                }
            } catch (Exception var80) {
                System.err.println("err:" + var80.getMessage());
                lb = new Label(22, i, "失败");

                try {
                    sheet1.addCell(lb);
                } catch (RowsExceededException var76) {
                    var76.printStackTrace();
                } catch (WriteException var77) {
                    var77.printStackTrace();
                }
            }
        }

        wwb.write();

        try {
            wwb.close();
        } catch (WriteException var75) {
            var75.printStackTrace();
        }

    }

    public void uploadNbMeterInfo(String path, int custId, int createUser, String manuCode, String batchNo, int pwdGroupNo, int tmodel, int typeId) throws BiffException, IOException {
        File file = new File(path);
        Workbook rwb = null;
        rwb = Workbook.getWorkbook(file);
        Sheet sheet = rwb.getSheets()[0];
        int rsColumns = sheet.getColumns();
        int rsRows = sheet.getRows();
        System.out.println("列数:" + rsColumns + "行数:" + rsRows);

        for(int i = 1; i < rsRows; ++i) {
            Cell cell0 = sheet.getCell(0, i);
            Cell cell1 = sheet.getCell(1, i);
            Cell cell2 = sheet.getCell(2, i);
            Cell cell3 = sheet.getCell(3, i);

            try {
                this.nbMeterDao.uploadNbMeterInfo(cell0.getContents().trim(), cell1.getContents().trim(), custId, createUser, manuCode, batchNo, pwdGroupNo, tmodel, cell2.getContents().trim(), cell3.getContents().trim(), typeId);
            } catch (Exception var20) {
                System.out.println("---------->>>" + cell0.getContents().trim());
                System.err.println("err:" + var20.getMessage());
            }
        }

    }

    public void upLoadMeterToDosagedetail(String path, int custId, int operatorId) throws BiffException, IOException {
        File file = new File(path);
        Workbook rwb = null;
        rwb = Workbook.getWorkbook(file);
        Sheet sheet = rwb.getSheets()[0];
        int rsColumns = sheet.getColumns();
        int rsRows = sheet.getRows();
        System.out.println("列数:" + rsColumns + "行数:" + rsRows);

        for(int i = 1; i < rsRows; ++i) {
            Cell cell0 = sheet.getCell(0, i);

            try {
                this.userDao.upLoadMeterToDosagedetail(cell0.getContents().trim(), custId, operatorId);
            } catch (Exception var12) {
                System.out.println("---------->>>" + cell0.getContents().trim());
                System.err.println("err:" + var12.getMessage());
            }
        }

    }

    public void upLoadFactoryTestExcel(String path) throws BiffException, IOException {
        File file = new File(path);
        Workbook rwb = null;
        rwb = Workbook.getWorkbook(file);
        Sheet sheet = rwb.getSheets()[0];
        int rsColumns = sheet.getColumns();
        int rsRows = sheet.getRows();
        System.out.println("列数:" + rsColumns + "行数:" + rsRows);
        String batchNo = Tools.getNow_YYYYMMDDHHMMSS();

        for(int i = 1; i < rsRows; ++i) {
            Cell cell0 = sheet.getCell(0, i);
            Cell cell1 = sheet.getCell(1, i);

            try {
                this.nbMeterDao.upLoadFactoryTestExcel(cell0.getContents().trim(), cell1.getContents().trim(), batchNo);
            } catch (Exception var12) {
                System.out.println("---------->>>" + cell0.getContents().trim());
                System.err.println("err:" + var12.getMessage());
            }
        }

    }
}
