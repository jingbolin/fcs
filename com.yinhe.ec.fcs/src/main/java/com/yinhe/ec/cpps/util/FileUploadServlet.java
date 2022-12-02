// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileUploadServlet.java

package com.yinhe.ec.cpps.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            Tools

public class FileUploadServlet extends HttpServlet
{

    public FileUploadServlet()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String rootPath = request.getParameter("rootPath");
        String param = request.getParameter("param");
        if(rootPath == null)
            rootPath = "";
        rootPath = rootPath.trim();
        if(rootPath.equals(""))
            rootPath = getServletContext().getRealPath("");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try
        {
            List items = upload.parseRequest(request);
            if(items != null)
            {
                for(Iterator itr = items.iterator(); itr.hasNext();)
                {
                    FileItem item = (FileItem)itr.next();
                    String fileName = "";
                    if(!item.isFormField())
                    {
                        fileName = (new StringBuilder(String.valueOf(Tools.getRandomString(6)))).append("_").append(item.getName().toLowerCase()).toString();
                        String path = "/swfupload_files";
                        File savedFile = new File((new StringBuilder(String.valueOf(rootPath))).append(path).toString(), fileName);
                        item.write(savedFile);
                        out.print((new StringBuilder("{\"status\":0,\"message\":\"")).append(path).append("/").append(fileName).append("\"}").toString());
                    }
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private String generateFileName(String fileName)
    {
        String uuid = UUID.randomUUID().toString();
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return (new StringBuilder(String.valueOf(uuid))).append(extension).toString();
    }

    private static final long serialVersionUID = 1L;
    private static final int UPLOAD_SUCCSSS = 0;
    private static final int UPLOAD_FAILURE = 1;
    private static final int UPLOAD_TYPE_ERROR = 2;
    private static final int UPLOAD_OVERSIZE = 3;
    private static final int UPLOAD_ZEROSIZE = 4;
    private static final int UPLOAD_NOTFOUND = 5;
}
