// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Uploader.java

package com.yinhe.ec.cpps.util;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Uploader
{

    public Uploader(HttpServletRequest request)
    {
        url = "";
        fileName = "";
        state = "";
        type = "";
        originalName = "";
        size = 0L;
        this.request = null;
        title = "";
        savePath = "upload";
        maxSize = 10000;
        errorInfo = new HashMap();
        this.request = request;
        HashMap tmp = errorInfo;
        tmp.put("SUCCESS", "SUCCESS");
        tmp.put("NOFILE", "\u672A\u5305\u542B\u6587\u4EF6\u4E0A\u4F20\u57DF");
        tmp.put("TYPE", "\u4E0D\u5141\u8BB8\u7684\u6587\u4EF6\u683C\u5F0F");
        tmp.put("SIZE", "\u6587\u4EF6\u5927\u5C0F\u8D85\u51FA\u9650\u5236");
        tmp.put("ENTYPE", "\u8BF7\u6C42\u7C7B\u578BENTYPE\u9519\u8BEF");
        tmp.put("REQUEST", "\u4E0A\u4F20\u8BF7\u6C42\u5F02\u5E38");
        tmp.put("IO", "IO\u5F02\u5E38");
        tmp.put("DIR", "\u76EE\u5F55\u521B\u5EFA\u5931\u8D25");
        tmp.put("UNKNOWN", "\u672A\u77E5\u9519\u8BEF");
    }

    public void upload()
        throws Exception
    {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart)
        {
            state = (String)errorInfo.get("NOFILE");
            return;
        }
        DiskFileItemFactory dff = new DiskFileItemFactory();
        String savePath = getFolder(this.savePath);
        dff.setRepository(new File(savePath));
        try
        {
            ServletFileUpload sfu = new ServletFileUpload(dff);
            sfu.setSizeMax(maxSize * 1024);
            sfu.setHeaderEncoding("utf-8");
            FileItemIterator fii = sfu.getItemIterator(request);
            while(fii.hasNext()) 
            {
                FileItemStream fis = fii.next();
                if(!fis.isFormField())
                {
                    originalName = fis.getName().substring(fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
                    if(!checkFileType(originalName))
                    {
                        state = (String)errorInfo.get("TYPE");
                        continue;
                    }
                    fileName = getName(originalName);
                    type = getFileExt(fileName);
                    url = (new StringBuilder(String.valueOf(savePath))).append("/").append(fileName).toString();
                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
                    File file = new File(getPhysicalPath(url));
                    FileOutputStream out = new FileOutputStream(file);
                    BufferedOutputStream output = new BufferedOutputStream(out);
                    Streams.copy(in, output, true);
                    state = (String)errorInfo.get("SUCCESS");
                    size = file.length();
                    break;
                }
                String fname = fis.getFieldName();
                if(fname.equals("pictitle"))
                {
                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer result = new StringBuffer();
                    for(; reader.ready(); result.append((char)reader.read()));
                    title = new String(result.toString().getBytes(), "utf-8");
                    reader.close();
                }
            }
        }
        catch(FileUploadBase.SizeLimitExceededException e)
        {
            state = (String)errorInfo.get("SIZE");
        }
        catch(FileUploadBase.InvalidContentTypeException e)
        {
            state = (String)errorInfo.get("ENTYPE");
        }
        catch(FileUploadException e)
        {
            state = (String)errorInfo.get("REQUEST");
        }
        catch(Exception e)
        {
            state = (String)errorInfo.get("UNKNOWN");
        }
    }

    public void uploadBase64(String fieldName)
    {
        String savePath = getFolder(this.savePath);
        String base64Data = request.getParameter(fieldName);
        fileName = getName("test.png");
        url = (new StringBuilder(String.valueOf(savePath))).append("/").append(fileName).toString();
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            File outFile = new File(getPhysicalPath(url));
            OutputStream ro = new FileOutputStream(outFile);
            byte b[] = decoder.decodeBuffer(base64Data);
            for(int i = 0; i < b.length; i++)
                if(b[i] < 0)
                    b[i] += 256;

            ro.write(b);
            ro.flush();
            ro.close();
            state = (String)errorInfo.get("SUCCESS");
        }
        catch(Exception e)
        {
            state = (String)errorInfo.get("IO");
        }
    }

    private boolean checkFileType(String fileName)
    {
        for(Iterator type = Arrays.asList(allowFiles).iterator(); type.hasNext();)
        {
            String ext = (String)type.next();
            if(fileName.toLowerCase().endsWith(ext))
                return true;
        }

        return false;
    }

    private String getFileExt(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String getName(String fileName)
    {
        Random random = new Random();
        return this.fileName = (new StringBuilder()).append(random.nextInt(10000)).append(System.currentTimeMillis()).append(getFileExt(fileName)).toString();
    }

    private String getFolder(String path)
    {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path = (new StringBuilder(String.valueOf(path))).append("/").append(formater.format(new Date())).toString();
        File dir = new File(getPhysicalPath(path));
        if(!dir.exists())
            try
            {
                dir.mkdirs();
            }
            catch(Exception e)
            {
                state = (String)errorInfo.get("DIR");
                return "";
            }
        return path;
    }

    private String getPhysicalPath(String path)
    {
        String servletPath = request.getServletPath();
        String realPath = request.getSession().getServletContext().getRealPath(servletPath);
        return (new StringBuilder(String.valueOf((new File(realPath)).getParent()))).append("/").append(path).toString();
    }

    public void setSavePath(String savePath)
    {
        this.savePath = savePath;
    }

    public void setAllowFiles(String allowFiles[])
    {
        this.allowFiles = allowFiles;
    }

    public void setMaxSize(int size)
    {
        maxSize = size;
    }

    public long getSize()
    {
        return size;
    }

    public String getUrl()
    {
        return url;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getState()
    {
        return state;
    }

    public String getTitle()
    {
        return title;
    }

    public String getType()
    {
        return type;
    }

    public String getOriginalName()
    {
        return originalName;
    }

    private String url;
    private String fileName;
    private String state;
    private String type;
    private String originalName;
    private long size;
    private HttpServletRequest request;
    private String title;
    private String savePath;
    private String allowFiles[] = {
        ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", 
        ".jpg", ".jpeg", ".bmp"
    };
    private int maxSize;
    private HashMap errorInfo;
}
