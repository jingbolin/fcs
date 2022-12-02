//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import cmcc.iot.onenet.javasdk.utils.Config;

import java.io.*;

public class TxtFileReadAndWrite {
    public TxtFileReadAndWrite() {
    }

    public static void writeToFile(String msg) {
        String path = "";
        String path_bak = "";

        try {
            path = Config.getString("test.txtPath") + "/onenet_logs.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(msg + "\r\n");
            bw.close();
            fw.close();
            path_bak = Config.getString("test.txtPath") + "/onenet_logs_bak_" + Tools.getNowDT() + ".txt";
            File file_bak = new File(path_bak);
            if (!file_bak.exists()) {
                file_bak.createNewFile();
            }

            FileWriter fw_bak = new FileWriter(file_bak, true);
            BufferedWriter bw_bak = new BufferedWriter(fw_bak);
            bw_bak.write(msg + "\r\n");
            bw_bak.close();
            fw_bak.close();
        } catch (Exception var9) {
        }

    }

    public static String readFile(File file, String charset) {
        if (charset == null) {
            charset = "UTF-8";
        }

        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String text = null;

                while((text = bufferedReader.readLine()) != null) {
                    System.out.println(text);
                }

                clearFileContent();
            } catch (Exception var6) {
            }
        }

        return null;
    }

    public static void clearFileContent() {
//        String path = "";
//        path = Config.getString("test.txtPath") + "/onenet_logs.txt";
//
//        try {
//            FileOutputStream out = new FileOutputStream(path, false);
//            out.write((new String("")).getBytes());
//            out.close();
//        } catch (Exception var2) {
//        }

    }
}
