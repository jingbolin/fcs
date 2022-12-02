// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DtuParserList.java

package cmcc.iot.onenet.javasdk.response.dtu;

import java.util.ArrayList;
import java.util.Date;

public class DtuParserList
{
    public static class ParsersItem
    {

        public Integer getId()
        {
            return id;
        }

        public void setId(Integer id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getFileName()
        {
            return fileName;
        }

        public void setFileName(String fileName)
        {
            this.fileName = fileName;
        }

        public Date getTime()
        {
            return time;
        }

        public void setTime(Date time)
        {
            this.time = time;
        }

        private Integer id;
        private String name;
        private String fileName;
        private Date time;

        public ParsersItem(Integer id, String name, String fileName, Date time)
        {
            this.id = id;
            this.name = name;
            this.fileName = fileName;
            this.time = time;
        }
    }


    public DtuParserList()
    {
    }

    public ArrayList getParsers()
    {
        return parsers;
    }

    public void setParsers(ArrayList parsers)
    {
        this.parsers = parsers;
    }

    private ArrayList parsers;
}
