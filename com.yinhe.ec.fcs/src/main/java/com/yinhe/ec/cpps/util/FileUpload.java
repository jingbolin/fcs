//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FileUpload() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String realDir = request.getSession().getServletContext().getRealPath("");
        realDir = realDir.substring(0, realDir.length() - 5);
        String contextpath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath + "/";

        try {
            String filePath = "meterpics";
            String realPath = realDir + "\\" + filePath;
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }

            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory dff = new DiskFileItemFactory();
                dff.setRepository(dir);
                dff.setSizeThreshold(1024000);
                ServletFileUpload sfu = new ServletFileUpload(dff);
                FileItemIterator fii = null;
                fii = sfu.getItemIterator(request);
                String title = "";
                String url = "";
                String fileName = "";
                String state = "SUCCESS";
                String realFileName = "";

                while(fii.hasNext()) {
                    FileItemStream fis = fii.next();

                    try {
                        if (!fis.isFormField() && fis.getName().length() > 0) {
                            fileName = fis.getName();
                            Pattern reg = Pattern.compile("[.]jpg|png|jpeg|gif$");
                            Matcher matcher = reg.matcher(fileName);
                            if (!matcher.find()) {
                                state = "文件类型不允许！";
                                break;
                            }

                            realFileName = request.getParameter("meterNo") + fileName.substring(fileName.lastIndexOf("."), fileName.length());
                            url = realPath + "\\" + realFileName;
                            BufferedInputStream in = new BufferedInputStream(fis.openStream());
                            FileOutputStream a = new FileOutputStream(new File(url));
                            BufferedOutputStream output = new BufferedOutputStream(a);
                            Streams.copy(in, output, true);
                        } else {
                            String fname = fis.getFieldName();
                            if (fname.indexOf("pictitle") != -1) {
                                BufferedInputStream in = new BufferedInputStream(fis.openStream());
                                byte[] c = new byte[10];
                                int n = 0;
                                if ((n = in.read(c)) != -1) {
                                    new String(c, 0, n);
                                }
                            }
                        }
                    } catch (Exception var23) {
                        var23.printStackTrace();
                    }
                }

                response.setStatus(200);
                String retxt = "{src:'" + basePath + filePath + "/" + realFileName + "',status:success}";
                response.getWriter().print(retxt);
            }
        } catch (Exception var24) {
            var24.printStackTrace();
        }

    }

    @Override
    public void init() throws ServletException {
    }
}
