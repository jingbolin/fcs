//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import com.yinhe.ec.cpps.model.Cmd;
import com.yinhe.ec.cpps.model.NbMeterPwd;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class YinheFrame {
    private static final Logger logger = Logger.getLogger(YinheFrame.class);

    public YinheFrame() {
    }

    public static String encodeFrame(String meterNo, String commandCode, String commandValue, Cmd cmd, NbMeterPwd nbMeterPwd) {
        logger.info("====645组帧====meterNo:" + meterNo + " commandCode:" + commandCode + " commandValue:" + commandValue);
        String cmdStr = "";
        if (cmd.getCmdId() == 1) {
            cmdStr = "68AAAAAAAAAAAA68" + cmd.getCmdCode() + cmd.getDataLength() + cmd.getDataItem();
            cmdStr = cmdStr + DataConvertTools.cs(cmdStr);
            cmdStr = "FEFEFEFE" + cmdStr + "16";
        } else if (cmd.getCmdId() > 1 && cmd.getCmdId() < 7) {
            cmdStr = "68" + DataConvertTools.turnOver(meterNo, false, false) + "68" + cmd.getCmdCode() + cmd.getDataLength() + cmd.getDataItem();
            cmdStr = cmdStr + DataConvertTools.cs(cmdStr);
            cmdStr = "FEFEFEFE" + cmdStr + "16";
        } else if (cmd.getCmdId() >= 7 && cmd.getCmdId() <= 8) {
            cmdStr = "68" + DataConvertTools.turnOver(meterNo, false, false) + "68" + cmd.getCmdCode() + cmd.getDataLength() + cmd.getDataItem() + nbMeterPwd.getOperatorPwd() + nbMeterPwd.getOperatorCode();
            cmdStr = cmdStr + DataConvertTools.cs(cmdStr);
            cmdStr = "FEFEFEFE" + cmdStr + "16";
        } else if (cmd.getCmdId() >= 9 && cmd.getCmdId() <= 12) {
            cmdStr = "68" + DataConvertTools.turnOver(meterNo, false, false) + "68" + cmd.getCmdCode() + cmd.getDataLength() + nbMeterPwd.getOperatorPwd() + nbMeterPwd.getOperatorCode() + cmd.getDataItem();
            cmdStr = cmdStr + DataConvertTools.cs(cmdStr);
            cmdStr = "FEFEFEFE" + cmdStr + "16";
        } else if (cmd.getCmdId() >= 13 && cmd.getCmdId() <= 14) {
            String date = "";
            if (cmd.getCmdId() == 13) {
                date = Tools.getNowDT() + Tools.getWeek();
            } else {
                date = Tools.getNow_HHMMSS();
            }

            cmdStr = "68" + DataConvertTools.turnOver(meterNo, false, false) + "68" + cmd.getCmdCode() + cmd.getDataLength() + cmd.getDataItem() + nbMeterPwd.getOperatorPwd() + nbMeterPwd.getOperatorCode() + DataConvertTools.turnOver(date, true, false);
            cmdStr = cmdStr + DataConvertTools.cs(cmdStr);
            cmdStr = "FEFEFEFE" + cmdStr + "16";
        } else {
            cmdStr = "68" + DataConvertTools.turnOver(meterNo, false, false) + "68" + cmd.getCmdCode() + cmd.getDataLength() + cmd.getDataItem();
            cmdStr = cmdStr + DataConvertTools.cs(cmdStr);
            cmdStr = "FEFEFEFE" + cmdStr + "16";
        }

        logger.info("====组帧结果====" + cmdStr);
        return cmdStr;
    }

    public static Map<String, String> decodeFrame(String reciveFrame) {
        reciveFrame = reciveFrame.toUpperCase();
        logger.info("====645解帧====" + reciveFrame);
        String meterNo = "";
        String commandCode = "";
        String value = "";
        String reciveDesc = "";
        Frame frame = new Frame();
        if ("".equals(reciveFrame)) {
            return null;
        } else {
            byte[] buffer = DataConvertTools.hexString2Bytes(reciveFrame.replace("FE", "").replace("fe", ""));

            int i;
            for(i = 0; i < 6; ++i) {
                frame.address[i] = buffer[6 - i];
            }

            frame.c = buffer[8];
            frame.error = (255 & buffer[8]) == 145 || (255 & buffer[8]) == 147 || (255 & buffer[8]) == 148 || (255 & buffer[8]) == 156;
            frame.dataLength = buffer[9];
            if ((255 & buffer[8]) == 145) {
                frame.data = new byte[frame.dataLength - 4];
            } else {
                frame.data = new byte[frame.dataLength];
            }

            if (frame.dataLength > 0 && frame.error) {
                if ((255 & buffer[8]) == 147) {
                    for(i = 0; i < frame.dataLength; ++i) {
                        buffer[10 + i] = (byte)(buffer[10 + i] - 51);
                    }

                    for(i = 0; i < frame.dataLength; ++i) {
                        frame.data[i] = buffer[10 + frame.dataLength - 1 - i];
                    }

                    value = DataConvertTools.bytesToHexString(frame.data);
                    reciveDesc = value;
                    logger.info("读表号,回复：" + value);
                } else if ((255 & buffer[8]) == 145) {
                    for(i = 0; i < 4; ++i) {
                        frame.dataItem[i] = buffer[10 + i];
                    }

                    for(i = 0; i < frame.dataLength; ++i) {
                        buffer[10 + i] = (byte)(buffer[10 + i] - 51);
                    }

                    for(i = 0; i < frame.dataLength - 4; ++i) {
                        frame.data[i] = buffer[10 + frame.dataLength - 1 - i];
                    }

                    commandCode = DataConvertTools.bytesToHexString(frame.dataItem);
                    value = DataConvertTools.bytesToHexString(frame.data);
                    logger.info("value：" + value);
                    if ("33333333".equals(commandCode)) {
                        value = Tools.formatFloat(Double.parseDouble(value) * 0.01);
                    } else if ("33343435".equals(commandCode)) {
                        value = Tools.formatFloat(Double.parseDouble(value) * 0.1);
                    } else if ("33343535".equals(commandCode)) {
                        value = Tools.formatFloat(Double.parseDouble(value) * 0.001);
                    } else if ("36383337".equals(commandCode)) {
                        String state = DataConvertTools.hexToBinary(value);
                        value = state.substring(3, 4);
                    }

                    reciveDesc = value;
                } else {
                    value = "";
                }
            } else if ((255 & buffer[8]) == 156) {
                commandCode = "9C";
                value = "成功";
                reciveDesc = value;
            }

            meterNo = DataConvertTools.bytesToHexString(frame.address);
            Map<String, String> map = new HashMap();
            map.put("meterNo", meterNo);
            map.put("commandCode", commandCode);
            map.put("value", value);
            map.put("reciveDesc", reciveDesc);
            map.put("valid", String.valueOf(frame.error));
            logger.info(map);
            return map;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(DataConvertTools.hexToBinary("FF83"));
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    static class Frame {
        public boolean error = false;
        public byte[] address = new byte[6];
        public byte c;
        public byte[] dataItem = new byte[4];
        public byte[] data;
        public byte dataLength;

        public Frame() {
        }
    }
}
