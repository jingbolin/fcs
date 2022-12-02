//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileViewer {
    private static Logger logger = Logger.getLogger(FileViewer.class);

    public FileViewer() {
    }

    public static List<String> getListFiles(String path, String suffix, boolean isdepth) {
        List<String> lstFileNames = new ArrayList();
        File file = new File(path);
        return listFile(lstFileNames, file, suffix, isdepth);
    }

    private static List<String> listFile(List<String> lstFileNames, File f, String suffix, boolean isdepth) {
        int i;
        if (f.isDirectory()) {
            File[] t = f.listFiles();

            for(i = 0; i < t.length; ++i) {
                if (isdepth || t[i].isFile()) {
                    listFile(lstFileNames, t[i], suffix, isdepth);
                }
            }
        } else {
            String filePath = f.getAbsolutePath();
            if (!suffix.equals("")) {
                i = filePath.lastIndexOf(".");
                String tempsuffix = "";
                if (i != -1) {
                    tempsuffix = filePath.substring(i + 1, filePath.length());
                    if (tempsuffix.equals(suffix)) {
                        lstFileNames.add(filePath);
                    }
                }
            } else {
                lstFileNames.add(filePath);
            }
        }

        return lstFileNames;
    }

    public static List<String> listFileName(List<String> ListFiles) {
        List<String> listFileName = new ArrayList();

        String fileName;
        for(Iterator var3 = ListFiles.iterator(); var3.hasNext(); listFileName.add(fileName)) {
            String file = (String)var3.next();
            File _file = new File(file);
            fileName = _file.getName();
            Image src = null;

            try {
                src = ImageIO.read(_file);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        return listFileName;
    }
}
