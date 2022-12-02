//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

//import cmcc.iot.onenet.javasdk.utils.Config;
//import com.yinhe.ec.cpps.ycmeter.dao.NbMeterDao;

import java.io.*;
import java.util.*;

public class UploadNbDataTask {
    public UploadNbDataTask() {
    }

    public static void start() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                UploadNbDataTask.readFile((String)null);
            }
        }, time, 600000L);
    }

    public static List<String> readFile(String charset) {
//        if (charset == null) {
//            charset = "UTF-8";
//        }
//
//        String path = "";
//        path = Config.getString("test.txtPath") + "/onenet_logs.txt";
//        File file = new File(path);
//        List<String> list = new ArrayList();
//        if (file.isFile() && file.exists()) {
//            try {
//                System.out.println(Tools.getNow() + "--->>OneNet upload data start...");
//                FileInputStream fileInputStream = new FileInputStream(file);
//                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String text = "";
//
//                while((text = bufferedReader.readLine()) != null) {
//                    list.add(text);
//                }
//
//                try {
//                    FileOutputStream out = new FileOutputStream(path, false);
//                    out.write((new String("")).getBytes());
//                    out.close();
//                } catch (Exception var9) {
//                }
//
//                System.out.println(Tools.getNow() + "--->>OneNet upload data end...");
//            } catch (Exception var10) {
//            }
//        }
//
//        NbMeterDao nbMeterDao = (NbMeterDao)SpringTool.getBean("nbMeterDao");
//        nbMeterDao.insertNbDataToDatabase(list);
        return null;
    }
}
