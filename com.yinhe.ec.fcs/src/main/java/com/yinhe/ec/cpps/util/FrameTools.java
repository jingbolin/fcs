// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FrameTools.java

package com.yinhe.ec.cpps.util;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            DataConvertTools

public class FrameTools
{
    static class Frame
    {

        public boolean error;
        public byte address[];
        public byte c;
        public byte data[];
        public byte dataLength;
        public int feCount;

        public Frame()
        {
            address = new byte[7];
            data = new byte[256];
            feCount = 0;
        }
    }


    public FrameTools()
    {
    }

    public static String encodeFrame(String meterNo, String commandCode, String commandValue)
    {
        String cmdStr = "";
        byte meterAddress[] = DataConvertTools.hexString2Bytes(meterNo);
        byte ctrlCode[] = DataConvertTools.hexString2Bytes("04");
        int dataLength = 0;
        byte dataItem[] = DataConvertTools.hexString2Bytes(commandCode);
        byte dataData[];
        byte data[];
        if("A01A".equals(commandCode))
        {
            String d0 = "";
            String d1 = "";
            String d2 = "";
            String ipPort = "";
            String d3 = "";
            String d4 = "";
            String hex_d0d1 = "";
            String hex_d2 = "";
            String ip1 = "";
            String ip2 = "";
            String ip3 = "";
            String ip4 = "";
            String hex_ip1 = "";
            String hex_ip2 = "";
            String hex_ip3 = "";
            String hex_ip4 = "";
            String hex_port = "";
            String tmpCmdStr = "";
            d0 = commandValue.split(",")[0];
            d1 = commandValue.split(",")[1];
            d2 = commandValue.split(",")[2].split("\\|")[0];
            ipPort = commandValue.split(",")[2].split("\\|")[1];
            d3 = ipPort.split(":")[0];
            d4 = ipPort.split(":")[1];
            int d0d1 = (Integer.parseInt(d0) << 7) + Integer.parseInt(d1);
            hex_d0d1 = DataConvertTools.Integer2HexStr(Integer.valueOf(d0d1));
            hex_d2 = DataConvertTools.Integer2HexStr(Integer.valueOf(Integer.parseInt(d2)));
            ip1 = d3.split("\\.")[0];
            ip2 = d3.split("\\.")[1];
            ip3 = d3.split("\\.")[2];
            ip4 = d3.split("\\.")[3];
            hex_port = DataConvertTools.Integer2HexStr(Integer.valueOf(Integer.parseInt(d4)));
            hex_ip1 = DataConvertTools.Integer2HexStr(Integer.valueOf(Integer.parseInt(ip1)));
            hex_ip2 = DataConvertTools.Integer2HexStr(Integer.valueOf(Integer.parseInt(ip2)));
            hex_ip3 = DataConvertTools.Integer2HexStr(Integer.valueOf(Integer.parseInt(ip3)));
            hex_ip4 = DataConvertTools.Integer2HexStr(Integer.valueOf(Integer.parseInt(ip4)));
            if(hex_port.length() == 2)
                hex_port = (new StringBuilder("00")).append(hex_port).toString();
            commandValue = (new StringBuilder(String.valueOf(hex_d0d1))).append(hex_d2).append(hex_ip1).append(hex_ip2).append(hex_ip3).append(hex_ip4).append(hex_port).append("00").append("00").toString();
            dataData = DataConvertTools.hexString2Bytes(commandValue);
            dataLength = dataData.length;
            data = new byte[3 + dataLength];
            for(int i = 0; i < 2; i++)
                data[i] = dataItem[2 - i - 1];

            data[2] = 1;
            for(int i = 0; i < dataLength; i++)
                data[3 + i] = dataData[i];

            dataLength += 3;
            Frame frame = new Frame();
            System.arraycopy(meterAddress, 0, frame.address, 0, 7);
            frame.c = ctrlCode[0];
            frame.dataLength = (byte)dataLength;
            if(dataLength > 0)
                System.arraycopy(data, 0, frame.data, 0, dataLength);
            if(frame.feCount < 0)
                frame.feCount = 0;
            if(frame.feCount > 10)
                frame.feCount = 10;
            byte buffer[] = new byte[frame.feCount + frame.dataLength + 13];
            for(int i = 0; i < frame.feCount; i++)
                buffer[i] = -2;

            buffer[frame.feCount] = 104;
            buffer[frame.feCount + 1] = 16;
            for(int i = 0; i < 7; i++)
                buffer[frame.feCount + 1 + 1 + i] = frame.address[6 - i];

            buffer[frame.feCount + 9] = 4;
            buffer[frame.feCount + 10] = frame.dataLength;
            if(frame.dataLength > 0)
                System.arraycopy(frame.data, 0, buffer, frame.feCount + 11, frame.dataLength);
            byte cs = 0;
            for(int i = 0; i < 11 + frame.dataLength; i++)
                cs += buffer[frame.feCount + i];

            buffer[frame.feCount + 11 + frame.dataLength] = cs;
            buffer[frame.feCount + 12 + frame.dataLength] = 22;
            cmdStr = DataConvertTools.bytesToHexString(buffer);
            return cmdStr;
        }
        dataData = DataConvertTools.hexString2Bytes(commandValue);
        dataLength = dataData.length;
        data = new byte[3 + dataLength];
        for(int i = 0; i < 2; i++)
            data[i] = dataItem[2 - i - 1];

        data[2] = 1;
        for(int i = 0; i < dataLength; i++)
            data[3 + i] = dataData[i];

        dataLength += 3;
        Frame frame = new Frame();
        System.arraycopy(meterAddress, 0, frame.address, 0, 7);
        frame.c = ctrlCode[0];
        frame.dataLength = (byte)dataLength;
        if(dataLength > 0)
            System.arraycopy(data, 0, frame.data, 0, dataLength);
        if(frame.feCount < 0)
            frame.feCount = 0;
        if(frame.feCount > 10)
            frame.feCount = 10;
        byte buffer[] = new byte[frame.feCount + frame.dataLength + 13];
        for(int i = 0; i < frame.feCount; i++)
            buffer[i] = -2;

        buffer[frame.feCount] = 104;
        buffer[frame.feCount + 1] = 16;
        for(int i = 0; i < 7; i++)
            buffer[frame.feCount + 1 + 1 + i] = frame.address[6 - i];

        buffer[frame.feCount + 9] = 4;
        buffer[frame.feCount + 10] = frame.dataLength;
        if(frame.dataLength > 0)
            System.arraycopy(frame.data, 0, buffer, frame.feCount + 11, frame.dataLength);
        byte cs = 0;
        for(int i = 0; i < 11 + frame.dataLength; i++)
            cs += buffer[frame.feCount + i];

        buffer[frame.feCount + 11 + frame.dataLength] = cs;
        buffer[frame.feCount + 11 + frame.dataLength + 1] = 22;
        cmdStr = DataConvertTools.bytesToHexString(buffer);
        return cmdStr;
    }

    public static void main(String args[])
    {
        System.out.println(encodeFrame("88660012345678", "A017", "99"));
        System.out.println(encodeFrame("88660012345678", "A01A", "0,3,3|192.168.0.100:98"));
    }
}
