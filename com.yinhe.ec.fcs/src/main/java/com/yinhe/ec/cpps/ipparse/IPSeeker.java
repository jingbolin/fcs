// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IPSeeker.java

package com.yinhe.ec.cpps.ipparse;

import org.apache.log4j.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.yinhe.ec.cpps.ipparse:
//            IPLocation, LogFactory, IPEntry, Util

public class IPSeeker
{

    public IPSeeker(String fileName, String dir)
    {
        IP_FILE = "QQWry.Dat";
        INSTALL_DIR = "f:/qqwry";
        INSTALL_DIR = dir;
        IP_FILE = fileName;
        ipCache = new HashMap();
        loc = new IPLocation();
        buf = new byte[100];
        b4 = new byte[4];
        b3 = new byte[3];
        try
        {
            ipFile = new RandomAccessFile(IP_FILE, "r");
        }
        catch(FileNotFoundException e)
        {
            String filename = (new File(IP_FILE)).getName().toLowerCase();
            File files[] = (new File(INSTALL_DIR)).listFiles();
            for(int i = 0; i < files.length; i++)
            {
                if(!files[i].isFile() || !files[i].getName().toLowerCase().equals(filename))
                    continue;
                try
                {
                    ipFile = new RandomAccessFile(files[i], "r");
                }
                catch(FileNotFoundException e1)
                {
                    LogFactory.log("IP\u5730\u5740\u4FE1\u606F\u6587\u4EF6\u6CA1\u6709\u627E\u5230\uFF0CIP\u663E\u793A\u529F\u80FD\u5C06\u65E0\u6CD5\u4F7F\u7528", Level.ERROR, e1);
                    ipFile = null;
                }
                break;
            }

        }
        if(ipFile != null)
            try
            {
                ipBegin = readLong4(0L);
                ipEnd = readLong4(4L);
                if(ipBegin == -1L || ipEnd == -1L)
                {
                    ipFile.close();
                    ipFile = null;
                }
            }
            catch(IOException e)
            {
                LogFactory.log("IP\u5730\u5740\u4FE1\u606F\u6587\u4EF6\u683C\u5F0F\u6709\u9519\u8BEF\uFF0CIP\u663E\u793A\u529F\u80FD\u5C06\u65E0\u6CD5\u4F7F\u7528", Level.ERROR, e);
                ipFile = null;
            }
    }

    public List getIPEntriesDebug(String s)
    {
        List ret = new ArrayList();
        long endOffset = ipEnd + 4L;
        for(long offset = ipBegin + 4L; offset <= endOffset; offset += 7L)
        {
            long temp = readLong3(offset);
            if(temp != -1L)
            {
                IPLocation ipLoc = getIPLocation(temp);
                if(ipLoc.getCountry().indexOf(s) != -1 || ipLoc.getArea().indexOf(s) != -1)
                {
                    IPEntry entry = new IPEntry();
                    entry.country = ipLoc.getCountry();
                    entry.area = ipLoc.getArea();
                    readIP(offset - 4L, b4);
                    entry.beginIp = Util.getIpStringFromBytes(b4);
                    readIP(temp, b4);
                    entry.endIp = Util.getIpStringFromBytes(b4);
                    ret.add(entry);
                }
            }
        }

        return ret;
    }

    public IPLocation getIPLocation(String ip)
    {
        IPLocation location = new IPLocation();
        location.setArea(getArea(ip));
        location.setCountry(getCountry(ip));
        return location;
    }

    public List getIPEntries(String s)
    {
        List ret = new ArrayList();
        try
        {
            if(mbb == null)
            {
                FileChannel fc = ipFile.getChannel();
                mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0L, ipFile.length());
                mbb.order(ByteOrder.LITTLE_ENDIAN);
            }
            int endOffset = (int)ipEnd;
            for(int offset = (int)ipBegin + 4; offset <= endOffset; offset += 7)
            {
                int temp = readInt3(offset);
                if(temp != -1)
                {
                    IPLocation ipLoc = getIPLocation(temp);
                    if(ipLoc.getCountry().indexOf(s) != -1 || ipLoc.getArea().indexOf(s) != -1)
                    {
                        IPEntry entry = new IPEntry();
                        entry.country = ipLoc.getCountry();
                        entry.area = ipLoc.getArea();
                        readIP(offset - 4, b4);
                        entry.beginIp = Util.getIpStringFromBytes(b4);
                        readIP(temp, b4);
                        entry.endIp = Util.getIpStringFromBytes(b4);
                        ret.add(entry);
                    }
                }
            }

        }
        catch(IOException e)
        {
            LogFactory.log("", Level.ERROR, e);
        }
        return ret;
    }

    private int readInt3(int offset)
    {
        mbb.position(offset);
        return mbb.getInt() & 0xffffff;
    }

    private int readInt3()
    {
        return mbb.getInt() & 0xffffff;
    }

    public String getCountry(byte ip[])
    {
        if(ipFile == null)
            return "IP\u5730\u5740\u5E93\u6587\u4EF6\u9519\u8BEF";
        String ipStr = Util.getIpStringFromBytes(ip);
        if(ipCache.containsKey(ipStr))
        {
            IPLocation ipLoc = (IPLocation)ipCache.get(ipStr);
            return ipLoc.getCountry();
        } else
        {
            IPLocation ipLoc = getIPLocation(ip);
            ipCache.put(ipStr, ipLoc.getCopy());
            return ipLoc.getCountry();
        }
    }

    public String getCountry(String ip)
    {
        return getCountry(Util.getIpByteArrayFromString(ip));
    }

    public String getArea(byte ip[])
    {
        if(ipFile == null)
            return "IP\u5730\u5740\u5E93\u6587\u4EF6\u9519\u8BEF";
        String ipStr = Util.getIpStringFromBytes(ip);
        if(ipCache.containsKey(ipStr))
        {
            IPLocation ipLoc = (IPLocation)ipCache.get(ipStr);
            return ipLoc.getArea();
        } else
        {
            IPLocation ipLoc = getIPLocation(ip);
            ipCache.put(ipStr, ipLoc.getCopy());
            return ipLoc.getArea();
        }
    }

    public String getArea(String ip)
    {
        return getArea(Util.getIpByteArrayFromString(ip));
    }

    private IPLocation getIPLocation(byte ip[])
    {
        IPLocation info = null;
        long offset = locateIP(ip);
        if(offset != -1L)
            info = getIPLocation(offset);
        if(info == null)
        {
            info = new IPLocation();
            info.setCountry("\u672A\u77E5\u56FD\u5BB6");
            info.setArea("\u672A\u77E5\u5730\u533A");
        }
        return info;
    }

    private long readLong4(long offset)
    {
        long ret = 0L;
        try
        {
            ipFile.seek(offset);
            ret |= ipFile.readByte() & 0xff;
            ret |= ipFile.readByte() << 8 & 0xff00;
            ret |= ipFile.readByte() << 16 & 0xff0000;
            ret |= ipFile.readByte() << 24 & 0xff000000;
            return ret;
        }
        catch(IOException e)
        {
            return -1L;
        }
    }

    private long readLong3(long offset)
    {
        long ret = 0L;
        try
        {
            ipFile.seek(offset);
            ipFile.readFully(b3);
            ret |= b3[0] & 0xff;
            ret |= b3[1] << 8 & 0xff00;
            ret |= b3[2] << 16 & 0xff0000;
            return ret;
        }
        catch(IOException e)
        {
            return -1L;
        }
    }

    private long readLong3()
    {
        long ret = 0L;
        try
        {
            ipFile.readFully(b3);
            ret |= b3[0] & 0xff;
            ret |= b3[1] << 8 & 0xff00;
            ret |= b3[2] << 16 & 0xff0000;
            return ret;
        }
        catch(IOException e)
        {
            return -1L;
        }
    }

    private void readIP(long offset, byte ip[])
    {
        try
        {
            ipFile.seek(offset);
            ipFile.readFully(ip);
            byte temp = ip[0];
            ip[0] = ip[3];
            ip[3] = temp;
            temp = ip[1];
            ip[1] = ip[2];
            ip[2] = temp;
        }
        catch(IOException e)
        {
            LogFactory.log("", Level.ERROR, e);
        }
    }

    private void readIP(int offset, byte ip[])
    {
        mbb.position(offset);
        mbb.get(ip);
        byte temp = ip[0];
        ip[0] = ip[3];
        ip[3] = temp;
        temp = ip[1];
        ip[1] = ip[2];
        ip[2] = temp;
    }

    private int compareIP(byte ip[], byte beginIp[])
    {
        for(int i = 0; i < 4; i++)
        {
            int r = compareByte(ip[i], beginIp[i]);
            if(r != 0)
                return r;
        }

        return 0;
    }

    private int compareByte(byte b1, byte b2)
    {
        if((b1 & 0xff) > (b2 & 0xff))
            return 1;
        return (b1 ^ b2) != 0 ? -1 : 0;
    }

    private long locateIP(byte ip[])
    {
        long m = 0L;
        readIP(ipBegin, b4);
        int r = compareIP(ip, b4);
        if(r == 0)
            return ipBegin;
        if(r < 0)
            return -1L;
        long i = ipBegin;
        for(long j = ipEnd; i < j;)
        {
            m = getMiddleOffset(i, j);
            readIP(m, b4);
            r = compareIP(ip, b4);
            if(r > 0)
                i = m;
            else
            if(r < 0)
            {
                if(m == j)
                {
                    j -= 7L;
                    m = j;
                } else
                {
                    j = m;
                }
            } else
            {
                return readLong3(m + 4L);
            }
        }

        m = readLong3(m + 4L);
        readIP(m, b4);
        r = compareIP(ip, b4);
        if(r <= 0)
            return m;
        else
            return -1L;
    }

    private long getMiddleOffset(long begin, long end)
    {
        long records = (end - begin) / 7L;
        records >>= 1;
        if(records == 0L)
            records = 1L;
        return begin + records * 7L;
    }

    private IPLocation getIPLocation(long offset)
    {
        try
        {
            ipFile.seek(offset + 4L);
            byte b = ipFile.readByte();
            if(b == 1)
            {
                long countryOffset = readLong3();
                ipFile.seek(countryOffset);
                b = ipFile.readByte();
                if(b == 2)
                {
                    loc.setCountry(readString(readLong3()));
                    ipFile.seek(countryOffset + 4L);
                } else
                {
                    loc.setCountry(readString(countryOffset));
                }
                loc.setArea(readArea(ipFile.getFilePointer()));
            } else
            if(b == 2)
            {
                loc.setCountry(readString(readLong3()));
                loc.setArea(readArea(offset + 8L));
            } else
            {
                loc.setCountry(readString(ipFile.getFilePointer() - 1L));
                loc.setArea(readArea(ipFile.getFilePointer()));
            }
            return loc;
        }
        catch(IOException e)
        {
            return null;
        }
    }

    private IPLocation getIPLocation(int offset)
    {
        mbb.position(offset + 4);
        byte b = mbb.get();
        if(b == 1)
        {
            int countryOffset = readInt3();
            mbb.position(countryOffset);
            b = mbb.get();
            if(b == 2)
            {
                loc.setCountry(readString(readInt3()));
                mbb.position(countryOffset + 4);
            } else
            {
                loc.setCountry(readString(countryOffset));
            }
            loc.setArea(readArea(mbb.position()));
        } else
        if(b == 2)
        {
            loc.setCountry(readString(readInt3()));
            loc.setArea(readArea(offset + 8));
        } else
        {
            loc.setCountry(readString(mbb.position() - 1));
            loc.setArea(readArea(mbb.position()));
        }
        return loc;
    }

    private String readArea(long offset)
        throws IOException
    {
        ipFile.seek(offset);
        byte b = ipFile.readByte();
        if(b == 1 || b == 2)
        {
            long areaOffset = readLong3(offset + 1L);
            if(areaOffset == 0L)
                return "\u672A\u77E5\u5730\u533A";
            else
                return readString(areaOffset);
        } else
        {
            return readString(offset);
        }
    }

    private String readArea(int offset)
    {
        mbb.position(offset);
        byte b = mbb.get();
        if(b == 1 || b == 2)
        {
            int areaOffset = readInt3();
            if(areaOffset == 0)
                return "\u672A\u77E5\u5730\u533A";
            else
                return readString(areaOffset);
        } else
        {
            return readString(offset);
        }
    }

    private String readString(long offset)
    {
        try
        {
            ipFile.seek(offset);
            int i = 0;
            for(buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile.readByte());
            if(i != 0)
                return Util.getString(buf, 0, i, "GBK");
        }
        catch(IOException e)
        {
            LogFactory.log("", Level.ERROR, e);
        }
        return "";
    }

    private String readString(int offset)
    {
        try
        {
            mbb.position(offset);
            int i = 0;
            for(buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get());
            if(i != 0)
                return Util.getString(buf, 0, i, "GBK");
        }
        catch(IllegalArgumentException e)
        {
            LogFactory.log("", Level.ERROR, e);
        }
        return "";
    }

    private String IP_FILE;
    private String INSTALL_DIR;
    private static final int IP_RECORD_LENGTH = 7;
    private static final byte REDIRECT_MODE_1 = 1;
    private static final byte REDIRECT_MODE_2 = 2;
    private Map ipCache;
    private RandomAccessFile ipFile;
    private MappedByteBuffer mbb;
    private long ipBegin;
    private long ipEnd;
    private IPLocation loc;
    private byte buf[];
    private byte b4[];
    private byte b3[];
}
